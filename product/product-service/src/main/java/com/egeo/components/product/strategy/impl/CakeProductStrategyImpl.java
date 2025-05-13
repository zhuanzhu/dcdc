package com.egeo.components.product.strategy.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.client.ReceiverAddressClient;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.product.bean.ChannelProductSearchBean;
import com.egeo.components.product.business.CakeProductManage;
import com.egeo.components.product.common.ProductChannelCodeEnum;
import com.egeo.components.product.condition.CategoryAndChannelCondition;
import com.egeo.components.product.condition.StandardUnitCondition;
import com.egeo.components.product.dao.read.CategoryReadDAO;
import com.egeo.components.product.dao.write.ChannelPriceDAO;
import com.egeo.components.product.dto.*;
import com.egeo.components.product.enums.ChannelPriceConstants;
import com.egeo.components.product.enums.ProductRedisKeyEnum;
import com.egeo.components.product.helper.ChannelPriceHelper;
import com.egeo.components.product.strategy.SearchProductStrategy;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.utils.CakeUtil;
import com.egeo.components.utils.FHCollectionUtils;
import com.egeo.components.utils.JsonUtils;
import com.egeo.config.RuntimeContext;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.util.security.MD5Util;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.StringUtils;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/4/30 3:27
 * @Version V1.0
 **/
@Service("cakeProductStrategyImpl")
public class CakeProductStrategyImpl implements SearchProductStrategy {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CakeProductManage cakeProductManage;

    @Autowired
    private CategoryReadDAO categoryReadDAO;

    @Autowired
    private ChannelPriceHelper channelPriceHelper;

    @Autowired
    private CompanyClient companyClient;

    @Autowired
    private ChannelPriceDAO channelPriceDAO;

    @Autowired
    private ReceiverAddressClient receiverAddressClient;

    @Resource
    private CakeUtil cakeUtil;

    @Resource
    private JedisUtil jedisUtil;

    @Override
    public String getProductCode() {
        return ProductChannelCodeEnum.CAKE.getCode();
    }

    @Override
    public boolean hasSearchNext(ChannelProductSearchBean param) {
        if(CollectionUtils.isEmpty(param.getCategoryTreeNodeIdList())){
            return true;
        }
        //查看是否有该渠道对应的后台类目
        List<CategoryAndChannelCondition> categorys = categoryReadDAO.findChannelCategoryByCategoryTreeNodes(param.getCategoryTreeNodeIdList(),getProductCode());
        addCategorys(categorys,param);
        if(CollectionUtils.isEmpty(param.getBrandIds()) && CollectionUtils.isEmpty(param.getProductTypes())){
            return false;
        }
        return true;
    }

    private void addCategorys(List<CategoryAndChannelCondition> categorys,ChannelProductSearchBean param){
        if(CollectionUtils.isEmpty(categorys)){
            return ;
        }
        for(CategoryAndChannelCondition one : categorys) {
            if(one.getChannelCategory()!=null) {
                setTypeAndBrandIds(one,param);
            }
        }
    }

    private void setTypeAndBrandIds(CategoryAndChannelCondition one,ChannelProductSearchBean param){
        if(one.getChannelCategoryType()==1){
            param.getProductTypes().add(one.getChannelCategoryId());
        }else{
            param.getBrandIds().add(one.getChannelCategoryId());
        }
    }

    /***
     * @Description 查询蛋糕叔叔列表
     **/
    @Override
    public PageResult<StandardUnitCondition> searchChannelProduct(ChannelProductSearchBean param) {
        setEnterpriseId(param);
        PageResult<StandardUnitCondition> pageResult = new PageResult<StandardUnitCondition>();
        Pagination page = param.getPage();
        setDefaultPageResult(pageResult, page);
        int cnt = param.getCnt();
        CakeProductSearchDTO search = getSearchDTO(param, page, cnt);
        //没有类目节点不允许查询
        if(CollectionUtils.isEmpty(param.getProductTypes()) && CollectionUtils.isEmpty(param.getBrandIds())){
            return pageResult;
        }
        param.setBrandIds(null);
        param.setProductTypes(null);
        JsonResult<List<CakeProductDTO>> rt =  cakeProductManage.searchOfCategoryLevel2(search);
        if(Objects.isNull(rt)){
            return pageResult;
        }
        if(!Objects.equals(rt.getCode(),0) || CollectionUtils.isEmpty(rt.getData())){
            logger.info("渠道策略处理结果不正确:{}", JsonUtils.objectToJson(rt));
            return pageResult;
        }


        List<ChannelEnterpriseConfigDTO>  channelEnterpriseConfigDTOList = channelPriceHelper.getChannelEnterpriseConfigAll(getProductCode(),param.getEnterpriseId(),false);
        List<StandardUnitCondition> list = new ArrayList<StandardUnitCondition>();
        List<CakeProductDTO> rtList =  rt.getData();
        Map<String,ChannelPriceDTO> channelPriceDTOMap = toChannelPriceDTOMap(rtList,param);
        for (CakeProductDTO cakeProductDTO : rtList) {
            calcPriceAndToList(channelPriceDTOMap,list,cakeProductDTO,channelEnterpriseConfigDTOList);
        }
        pageResult.setList(list);
        pageResult.setTotalSize(list.size()*page.getPageSize());
        return pageResult;
    }

    private Map<String,ChannelPriceDTO> toChannelPriceDTOMap(List<CakeProductDTO> rtList,ChannelProductSearchBean param){
        List<String> pid = FHCollectionUtils.listToStrList(rtList,CakeProductDTO::getId);
        List<String> specIds = getSpecIdList(rtList);
        return channelPriceHelper.getChannelPriceMap(getProductCode(),pid,specIds,param.getEnterpriseId());
    }

    private List<String> getSpecIdList(List<CakeProductDTO> rtList){
        List<String> resultList = new ArrayList<>();
        for (CakeProductDTO cakeProductDTO : rtList) {
            List<CakeSpecsDTO> specsDTOS =  cakeProductDTO.getSpecs();
            if(CollectionUtils.isEmpty(specsDTOS)){
                continue;
            }
            resultList.addAll(FHCollectionUtils.listToStrList(specsDTOS,CakeSpecsDTO::getSpec_id));
        }
        if(CollectionUtils.isEmpty(resultList)){
            return null;
        }
        return resultList;
    }

    private void calcPriceAndToList(Map<String,ChannelPriceDTO> channelPriceDTOMap,List<StandardUnitCondition> list,CakeProductDTO cakeProductDTO, List<ChannelEnterpriseConfigDTO>  channelEnterpriseConfigs){
        List<CakeSpecsDTO> specs = cakeProductDTO.getSpecs();
        if(CollectionUtils.isEmpty(specs)){
            StandardUnitCondition tmp = new StandardUnitCondition();
            tmp.fromCakeProduct(cakeProductDTO);
            if(!ChannelPriceConstants.IS_GOOD_OR_SKU){
                logger.info("产品ID：{}无规格维度且无需展示商品,不合法{}",cakeProductDTO.getId(), JSON.toJSONString(cakeProductDTO));
                return;
            }
            if(StringUtils.isEmpty(cakeProductDTO.getMarket_price()) || StringUtils.isEmpty(cakeProductDTO.getPrice())){
                logger.info("产品ID：{}价格不合法{}",cakeProductDTO.getId(), JSON.toJSONString(cakeProductDTO));
                return;
            }
            Map<String,String> priceMap = channelPriceHelper.calcPlatformPrice(cakeProductDTO.getMarket_price(),cakeProductDTO.getPrice(),channelEnterpriseConfigs,channelPriceDTOMap.get(cakeProductDTO.getId()));
            tmp.setSalePrice(new BigDecimal(priceMap.get(ChannelPriceConstants.SALE_PRICE_KEY)));
            tmp.setMarketPrice(new BigDecimal(priceMap.get(ChannelPriceConstants.MARKET_PRICE_KEY)));
            if(channelPriceHelper.checkIsVaild(priceMap) && !ChannelPriceConstants.IS_GIVE_UP_UN_FOUND_SPEC){
                list.add(tmp);
            }else{
                logger.info("是否放弃:{},产品ID：{}不合法{}",ChannelPriceConstants.IS_GIVE_UP_UN_FOUND_SPEC,cakeProductDTO.getId(), JSON.toJSONString(priceMap));
            }
            return;
        }
        List<StandardUnitCondition> tempList = new ArrayList<>();
        //有规格代表有sku
        for (CakeSpecsDTO spec : specs) {
            if(EmptyUtil.isEmpty(spec.getMarket_price()) || EmptyUtil.isEmpty(spec.getClearing_price())){
                logger.error("商品不合法，规格市场价或结算价不能为空{}",JSON.toJSONString(spec));
                continue;
            }
            StandardUnitCondition tmp = new StandardUnitCondition();
            tmp.fromCakeProduct(cakeProductDTO);
            Map<String,String> priceMap = channelPriceHelper.calcPlatformPrice(spec.getMarket_price(),spec.getClearing_price(),channelEnterpriseConfigs,channelPriceDTOMap.get(cakeProductDTO.getId()+spec.getSpec_id()));
            tmp.setSalePrice(new BigDecimal(priceMap.get(ChannelPriceConstants.SALE_PRICE_KEY)));
            tmp.setMarketPrice(new BigDecimal(priceMap.get(ChannelPriceConstants.MARKET_PRICE_KEY)));
            tmp.setId(Long.valueOf(spec.getSpec_id()));
            tmp.setName(cakeProductDTO.getTitle());
            tmp.setContent(cakeProductDTO.getBrand_name()+tmp.getName());
            if(channelPriceHelper.checkIsVaild(priceMap)){
                tempList.add(tmp);
            }else{
                logger.info("产品ID：{}规格信息:{}不合法{}",cakeProductDTO.getId(), JSON.toJSONString(spec),JSON.toJSONString(priceMap));
            }
        }
        if(!CollectionUtils.isEmpty(tempList)){
            Optional<StandardUnitCondition> minSalePriceCondition = FHCollectionUtils.findMinT(tempList,StandardUnitCondition::getSalePrice);
            minSalePriceCondition.ifPresent(condition -> list.add(condition));
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
                logger.error("根据城市名称获取城市地址失败：{}",JSON.toJSONString(checkResult));
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

    private CakeProductSearchDTO getSearchDTO(ChannelProductSearchBean param, Pagination page, int cnt) {
        CakeProductSearchDTO search = new CakeProductSearchDTO();
        int platformPage = (cnt ==0?0:((cnt / page.getPageSize())+1));
        int jdPage = page.getPageNo()-platformPage;
        search.setSize(page.getPageSize());
        search.setPage(jdPage);
        setOrderBy(page,search);
       // setBrandId(param, search);
        List<CategoryAndChannelCondition> categorys = categoryReadDAO.findChannelCategoryByCategoryTreeNodes(param.getCategoryTreeNodeIdList(),getProductCode());
        if(!CollectionUtils.isEmpty(categorys)){
            for(CategoryAndChannelCondition one : categorys) {
                setSearchNodeId(one,search);
            }
        }
        setCityIdQuery(search);
        if(EmptyUtil.isNotEmpty(param.getKeyWord()) && EmptyUtil.isNotBlank(param.getKeyWord())){
            search.setSearch_title(param.getKeyWord());
        }
        return search;
    }

    private void setCityIdQuery(CakeProductSearchDTO search) {
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

    private void setSearchNodeId(CategoryAndChannelCondition one,CakeProductSearchDTO search){
        if(one.getChannelCategory()==null) {
            return;
        }
        if(one.getChannelCategoryType()==2){
            search.setBrand_id(one.getChannelCategoryId());
            return;
        }
        if(one.getChannelCategoryLevel()==1){
            search.setProduct_type(one.getChannelCategoryId());
        }
        if(one.getChannelCategoryLevel()==2){
            search.setCat_id2(one.getChannelCategoryId());
        }
        if(one.getChannelCategoryLevel()==3){
            search.setCat_id3(one.getChannelCategoryId());
        }
    }



    private void setBrandId(ChannelProductSearchBean param, CakeProductSearchDTO search) {
        if(!CollectionUtils.isEmpty(param.getBrandIds())){
            search.setBrand_id(param.getBrandIds().get(0));
        }
        if(!CollectionUtils.isEmpty(param.getProductTypes())){
            String productType = param.getProductTypes().get(0);
            if(Integer.valueOf(productType) >0){
                search.setProduct_type(param.getProductTypes().get(0));
            }
        }
    }

    private void setDefaultPageResult(PageResult<StandardUnitCondition> pageResult, Pagination page) {
        pageResult.setList(new ArrayList<>());
        pageResult.setTotalSize(0);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
    }

    private void setOrderBy(Pagination page, CakeProductSearchDTO search) {
        //默认价格升序
        search.setSort_price_type("2");
        if(page.getOrderBy()!=null && page.getOrderBy().length()>0) {
            if(page.getOrderBy().equalsIgnoreCase("su.sale_price")) {
                search.setSort_price_type("2");
            }else if(page.getOrderBy().equalsIgnoreCase("su.sale_price desc")) {
                search.setSort_price_type("1");
            }
        }
    }

    private void setEnterpriseId(ChannelProductSearchBean param){
        if(param.getEnterpriseId() !=null){
            return;
        }
        if(RuntimeContext.cacheUser()==null || (RuntimeContext.cacheUser().isNotPlatformUser() && RuntimeContext.cacheUser().getEnterpriseId()==null)) {
            if(RuntimeContext.cacheUser().getCompanyId()!=null) {
                CompanyDTO company = companyClient.findCompanyById(RuntimeContext.cacheUser().getCompanyId());
                if(company.getEnterpriseId()!=null) {
                    logger.info("用户："+RuntimeContext.cacheUser().getLoginName()+"  登录公司id:"+RuntimeContext.cacheUser().getCompanyId().longValue()+" 代理id为空并填充成功");
                    RuntimeContext.cacheUser().setEnterpriseId(company.getEnterpriseId());
                    param.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
                }
            }
        }
    }
}
