package com.egeo.components.product.strategy.impl.keyword;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.client.ReceiverAddressClient;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.product.bean.KeyWordSearchBean;
import com.egeo.components.product.bean.KeyWordSearchCachePageBean;
import com.egeo.components.product.business.CakeProductManage;
import com.egeo.components.product.common.ProductChannelCodeEnum;
import com.egeo.components.product.condition.StandardUnitCondition;
import com.egeo.components.product.converter.StandardUnitConverter;
import com.egeo.components.product.dto.CakeProductDTO;
import com.egeo.components.product.dto.CakeProductSearchDTO;
import com.egeo.components.product.dto.CakeSpecsDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.enums.ProductRedisKeyEnum;
import com.egeo.components.product.strategy.KeyWordSearchStrategy;
import com.egeo.components.utils.CakeUtil;
import com.egeo.components.utils.FHCollectionUtils;
import com.egeo.config.RuntimeContext;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.util.security.MD5Util;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/11/26 15:54
 * @Version V1.0
 **/
@Service("keyWordSearchCakeImpl")
public class KeyWordSearchCakeImpl extends KeyWordSearchCommonBase implements KeyWordSearchStrategy {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name="cakeProductManage")
    private CakeProductManage cakeProductManage;

    @Resource
    private JedisUtil jedisUtil;

    @Autowired
    private ReceiverAddressClient receiverAddressClient;

    @Resource
    private CakeUtil cakeUtil;


    @Override
    public String getProductCode() {
        return ProductChannelCodeEnum.CAKE.getCode();
    }

    @Override
    public PageResult<StandardUnitDTO> getProductByKeyWord(KeyWordSearchBean bean) {
        Pagination page = bean.getPage();
        String name = bean.getName();
        List<StandardUnitDTO> standardUnitDTOList = new ArrayList<>();
        PageResult<StandardUnitDTO> result = new PageResult<StandardUnitDTO>();
        int currPage = getCurrPage(getProductCode(),jedisUtil,bean);
        CakeProductSearchDTO cakeSearch = new CakeProductSearchDTO();
        //cakeSearch.setPage(page.getPageNo());
        cakeSearch.setPage(currPage);
        cakeSearch.setSize(page.getPageSize());
        cakeSearch.setSearch_title(name);
        cakeSearch.setSort_price_type("2");
        if (page.getOrderBy().equalsIgnoreCase("su.sale_price desc")) {
            cakeSearch.setSort_price_type("1");
        }
        setCityIdQuery(cakeSearch);
        JsonResult<List<CakeProductDTO>> cakeProductRt = cakeProductManage.searchList(cakeSearch);
        if (Objects.nonNull(cakeProductRt) && !CollectionUtils.isEmpty(cakeProductRt.getData())) {
            List<CakeProductDTO> cakeProductDTOS = cakeProductRt.getData();
            for (CakeProductDTO oneCake : cakeProductDTOS) {
                StandardUnitCondition tmp = new StandardUnitCondition();
                tmp.fromCakeProduct(oneCake);
                StandardUnitDTO standardUnitDTO2 = StandardUnitConverter.toDTOFromCondition(tmp);
                standardUnitDTO2.setPictureUrl(tmp.getPictureUrl());
                standardUnitDTO2.setCommodityTemplateId(tmp.getCommodityTemplateId());
                standardUnitDTO2.setSalesVolume(tmp.getSalesVolume());
                standardUnitDTO2.setCode(oneCake.getId());
                List<CakeSpecsDTO> tempList = oneCake.getSpecs();
                if (!CollectionUtils.isEmpty(tempList)) {
                    Optional<CakeSpecsDTO> minSalePriceCondition = FHCollectionUtils.findMinT(tempList, CakeSpecsDTO::getPrice);
                    minSalePriceCondition.ifPresent(condition -> {
                        standardUnitDTO2.setSalePrice(new BigDecimal(condition.getPrice()));
                        standardUnitDTO2.setMarketPrice(new BigDecimal(condition.getMarket_price()));
                        standardUnitDTO2.setId(Long.valueOf(condition.getSpec_id()));
                        standardUnitDTO2.setName(oneCake.getTitle());
                    });
                }
                standardUnitDTOList.add(standardUnitDTO2);
            }
            //缓存本次查询的分页数
            KeyWordSearchCachePageBean keyWordSearchCachePageBean = new KeyWordSearchCachePageBean();
            keyWordSearchCachePageBean.setCurrPageNo(currPage);
            keyWordSearchCachePageBean.setChannelCode(getProductCode());
            keyWordSearchCachePageBean.setTotalCount(standardUnitDTOList.size());
            keyWordSearchCachePageBean.setPageSize(page.getPageSize());
            saveRedisPage(keyWordSearchCachePageBean,jedisUtil,bean);
        }
        result.setList(standardUnitDTOList);
        result.setPageNo(page.getPageNo());
        result.setPageSize(page.getPageSize());
        return result;
    }

    private void setCityIdQuery(CakeProductSearchDTO search) {
        if(RuntimeContext.cacheUser() ==null){
            return;
        }
        ReceiverAddressDTO dto = new ReceiverAddressDTO();
        dto.setUserId(RuntimeContext.cacheUser().getId());
        dto.setPlatformId(7L);
        ReceiverAddressDTO receiverAddressDTO = receiverAddressClient.findDefaultReceiverAddress(dto);
        if(Objects.nonNull(receiverAddressDTO)){
            String cityName = receiverAddressDTO.getGoodReceiverCity();
            if("直辖市".equals(cityName) || "市辖区".equals(cityName)){
                cityName = receiverAddressDTO.getGoodReceiverProvince();
            }
            String cityId = getCityId(cityName);
            if(EmptyUtil.isNotEmpty(cityId)){
                search.setCity_id(cityId);
            }
        }
    }


    private String getCityId(String cityName){
        if(EmptyUtil.isEmpty(cityName)){
            return null;
        }
        try {
            ProductRedisKeyEnum productRedisKeyEnum = ProductRedisKeyEnum.CAKE_CITY_ID_KEY;
            String redisKey  = ProductRedisKeyEnum.getCakeCityIdKey(MD5Util.MD5(cityName));
            int seconds = productRedisKeyEnum.getExpireTime();
            Object redisValue = jedisUtil.get(redisKey);
            if(EmptyUtil.isNotEmpty(redisValue)){
                logger.info("缓存中有城市id，直接返回缓存中的城市id:{}",redisValue);
                return (String)redisValue;
            }
            JSONObject jsonObject =  cakeUtil.getCityId(cityName);
            JsonResult checkResult = cakeUtil.checkResult(jsonObject);
            if(Objects.nonNull(checkResult)){
                logger.error("根据城市名称获取城市地址失败：{}", JSON.toJSONString(checkResult));
                return null;
            }
            JSONObject data = jsonObject.getJSONObject(cakeUtil.DATA_KEY);
            if(data == null){
                return null;
            }
            String cityId = data.getString("id");
            if(EmptyUtil.isNotEmpty(cityId)){
                jedisUtil.set(redisKey, seconds, cityId);
            }
            return cityId;
        }catch (Exception e){
            logger.info("根据城市名称获取城市地址失败发生异常：{}",e);
        }
        return null;
    }
}
