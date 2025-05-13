package com.egeo.components.product.strategy.impl.platform;

import com.alibaba.fastjson.JSON;
import com.egeo.components.product.bean.ChannelProductSearchBean;
import com.egeo.components.product.business.CakeProductManage;
import com.egeo.components.product.common.ProductChannelCodeEnum;
import com.egeo.components.product.dao.write.ChannelPriceDAO;
import com.egeo.components.product.dto.*;
import com.egeo.components.product.enums.ChannelPriceConstants;
import com.egeo.components.product.facade.ChannelCategoryFacade;
import com.egeo.components.product.helper.ChannelPriceHelper;
import com.egeo.components.product.vo.ChannelSupplierProductRequestVO;
import com.egeo.components.product.vo.ChannelSupplierProductResponseVO;
import com.egeo.components.utils.JsonUtils;
import com.egeo.components.utils.StringUtil;
import com.egeo.config.RuntimeContext;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.StringUtils;
import com.egeo.web.JsonResult;
import lombok.val;
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
 * @Version V1.0
 **/
@Service("cakePlatformProductStrategyImpl")
public  class CakePlatformProductStrategyImpl extends ChannelPlatformProductAbstractImpl {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CakeProductManage cakeProductManage;

    @Autowired
    private ChannelPriceHelper channelPriceHelper;

    @Autowired
    private ChannelCategoryFacade channelCategoryFacade;

    @Resource
    private ChannelPriceDAO channelPrice;

    private static Integer TOTAL_SIZE = 30000;


    @Override
    public String getProductCode() {
        return ProductChannelCodeEnum.CAKE.getCode();
    }

    @Override
    public JsonResult<PageResult<ChannelSupplierProductResponseVO>> platform(ChannelSupplierProductRequestVO vo,Pagination page) {
        JsonResult<PageResult<ChannelSupplierProductResponseVO>> result = new JsonResult<>();
        PageResult<ChannelSupplierProductResponseVO> pageResult = super.setDefaultPageResult(vo,page);
        CakeProductSearchDTO search = getSearchDTO(vo,page);
        JsonResult<List<CakeProductDTO>> rt =  cakeProductManage.searchOfCategoryLevel2(search);
        if(Objects.isNull(rt)){
            return JsonResult.success(pageResult);
        }
        if(!Objects.equals(rt.getCode(),0)){
            logger.info("渠道策略处理结果不正确:{}", JsonUtils.objectToJson(rt));
            result.setCode(rt.getCode());
            return JsonResult.fail(rt.getError(),rt.getCode());
        }
        if(CollectionUtils.isEmpty(rt.getData())){
            logger.info("渠道策略查询结果为空:{}", JsonUtils.objectToJson(rt));
            result.setCode(rt.getCode());
            return JsonResult.success(pageResult);
        }
        Long enterpriseId = EmptyUtil.isNotEmpty(vo.getEnterpriseId())?vo.getEnterpriseId():RuntimeContext.cacheUser().getEnterpriseId();
        //List<ChannelEnterpriseConfigDTO> channelEnterpriseConfigs = channelPriceHelper.getEnterpriseDefaultConfig();
        List<ChannelEnterpriseConfigDTO> channelEnterpriseConfigs = channelPriceHelper.getChannelEnterpriseConfigAll(getProductCode(),false);
        List<CakeProductDTO> list = rt.getData();
        List<ChannelSupplierProductResponseVO> rtList = new ArrayList<>(list.size());
        for (CakeProductDTO dto : list) {
            addToList(channelEnterpriseConfigs,vo,rtList,dto,null);
        }
        pageResult.setList(rtList);
        pageResult.setTotalSize(rtList.size()+TOTAL_SIZE);
        if(CollectionUtils.isEmpty(rtList)){
            pageResult.setTotalSize(0);
        }
        result.setData(pageResult);
        return result;
    }

    private ChannelSupplierProductResponseVO setCommonFields(ChannelSupplierProductRequestVO vo,CakeProductDTO dto) {
        ChannelSupplierProductResponseVO temp = new ChannelSupplierProductResponseVO();
        temp.setChannelCode(vo.getChannelCode());
        temp.setProductId(dto.getId());
        temp.setProductName(dto.getTitle());
        temp.setStatus(1);
        temp.setImagePath(dto.getImage_path());
        return temp;
    }

    private void addToList(List<ChannelEnterpriseConfigDTO> channelEnterpriseConfigs,ChannelSupplierProductRequestVO vo,List<ChannelSupplierProductResponseVO> rtList,CakeProductDTO dto,Map<String, ChannelPriceDTO> customPriceMap){
        Long enterpriseId = EmptyUtil.isNotEmpty(vo.getEnterpriseId())?vo.getEnterpriseId():RuntimeContext.cacheUser().getEnterpriseId();
        List<CakeSpecsDTO> specs = dto.getSpecs();
        if(CollectionUtils.isEmpty(specs)){
            if(!ChannelPriceConstants.IS_GOOD_OR_SKU){
                logger.info("产品ID：{}无规格维度且无需展示商品,不合法{}",dto.getId(), JSON.toJSONString(dto));
                return;
            }
            if(StringUtils.isEmpty(dto.getMarket_price()) || StringUtils.isEmpty(dto.getPrice())){
                logger.info("产品ID：{}价格不合法{}",dto.getId(), JSON.toJSONString(dto));
                return;
            }
            Map<String,ChannelPriceDTO> priceDTOMap =channelPriceHelper.getChannelPriceMap(getProductCode(), Arrays.asList(dto.getId()),null,enterpriseId);
            ChannelSupplierProductResponseVO temp =  setCommonFields(vo,dto);
            temp.setSalePrice(StringUtil.isEmpty(dto.getPrice())?null:new BigDecimal(dto.getPrice()));
            temp.setMarketPrice(StringUtil.isEmpty(dto.getMarket_price())?null:new BigDecimal(dto.getMarket_price()));
            Map<String, String>  priceMap = channelPriceHelper.calcPlatformPrice(dto.getMarket_price(),dto.getPrice(),channelEnterpriseConfigs,priceDTOMap.get(dto.getId()));
            if(EmptyUtil.isNotEmpty(priceMap) && !ChannelPriceConstants.IS_GIVE_UP_UN_FOUND_SPEC){
                temp.setSalePrice(new BigDecimal(priceMap.get(ChannelPriceConstants.CHANNEL_PRICE_KEY)));
                temp.setMarketPrice(new BigDecimal(priceMap.get(ChannelPriceConstants.MARKET_PRICE_KEY)));
                temp.setProfit(new BigDecimal(priceMap.get(ChannelPriceConstants.PROFIT_KEY)));
                if(priceMap.containsKey(ChannelPriceConstants.PRICE_AUDIT) && null !=priceMap.get(ChannelPriceConstants.PRICE_AUDIT)){
                    temp.setPriceAudit(Integer.valueOf(priceMap.get(ChannelPriceConstants.PRICE_AUDIT)));
                }
                if(priceMap.containsKey(ChannelPriceConstants.PRICE_TYPE) && null !=priceMap.get(ChannelPriceConstants.PRICE_TYPE)){
                    temp.setPriceType(Integer.valueOf(priceMap.get(ChannelPriceConstants.PRICE_TYPE)));
                }
                if(priceMap.containsKey(ChannelPriceConstants.PRICE_VALUE) && null !=priceMap.get(ChannelPriceConstants.PRICE_VALUE)){
                    temp.setPriceValue(priceMap.get(ChannelPriceConstants.PRICE_VALUE));
                }
                temp.setSource(4);
                rtList.add(temp);
            }else{
                logger.info("是否放弃:{},产品ID：{}不合法{}",ChannelPriceConstants.IS_GIVE_UP_UN_FOUND_SPEC,dto.getId(), JSON.toJSONString(priceMap));
            }

            return;
        }

        //若有规格
        for (CakeSpecsDTO spec : specs) {
            if(EmptyUtil.isEmpty(spec.getMarket_price()) || EmptyUtil.isEmpty(spec.getClearing_price())){
                logger.error("商品不合法，规格市场价或结算价不能为空{}",JSON.toJSONString(spec));
                continue;
            }
            Map<String,ChannelPriceDTO> priceDTOMap =channelPriceHelper.getChannelPriceMap(getProductCode(), Arrays.asList(dto.getId()),Arrays.asList(spec.getSpec_id()),enterpriseId);
            ChannelSupplierProductResponseVO temp =  setCommonFields(vo,dto);
            Map<String, String>  specsPriceMap= channelPriceHelper.calcPlatformPrice(spec.getMarket_price(),spec.getClearing_price(),channelEnterpriseConfigs,priceDTOMap.get(dto.getId()+spec.getSpec_id()));
            temp.setProductName(temp.getProductName()+spec.getName());
            temp.setSkuId(spec.getSpec_id());
            temp.setPrice(new BigDecimal(spec.getClearing_price()).setScale(2));
            temp.setSalePrice(new BigDecimal(specsPriceMap.get(ChannelPriceConstants.SALE_PRICE_KEY)).setScale(2));
            temp.setMarketPrice(new BigDecimal(specsPriceMap.get(ChannelPriceConstants.MARKET_PRICE_KEY)).setScale(2));
            temp.setProfit(new BigDecimal(specsPriceMap.get(ChannelPriceConstants.PROFIT_KEY)));
            if(specsPriceMap.containsKey(ChannelPriceConstants.PRICE_AUDIT) && null !=specsPriceMap.get(ChannelPriceConstants.PRICE_AUDIT)){
                temp.setPriceAudit(Integer.valueOf(specsPriceMap.get(ChannelPriceConstants.PRICE_AUDIT)));
            }
            if(specsPriceMap.containsKey(ChannelPriceConstants.PRICE_TYPE) && null !=specsPriceMap.get(ChannelPriceConstants.PRICE_TYPE)){
                temp.setPriceType(Integer.valueOf(specsPriceMap.get(ChannelPriceConstants.PRICE_TYPE)));
            }
            if(specsPriceMap.containsKey(ChannelPriceConstants.PRICE_VALUE) && null !=specsPriceMap.get(ChannelPriceConstants.PRICE_VALUE)){
                temp.setPriceValue(specsPriceMap.get(ChannelPriceConstants.PRICE_VALUE));
            }
            temp.setSource(4);
            //if(channelPriceHelper.checkIsVaild(specsPriceMap)){
                rtList.add(temp);
            /*}else{
                logger.info("产品ID：{}规格信息:{}不合法{}",dto.getId(), JSON.toJSONString(spec),JSON.toJSONString(specsPriceMap));
            }*/
        }
    }

    private CakeProductSearchDTO getSearchDTO(ChannelSupplierProductRequestVO vo,Pagination page) {
        CakeProductSearchDTO search = new CakeProductSearchDTO();
        search.setSize(page.getPageSize());
        search.setPage(page.getPageNo());
        if(EmptyUtil.isNotEmpty(vo.getKeyword())){
            search.setSearch_title(vo.getKeyword());
        }
        search.setProduct_type(vo.getProductType());
        if(StringUtils.isNotEmpty(vo.getCatId1())){
            search.setProduct_type(vo.getCatId1());
            if(Objects.equals(vo.getCatId1(),"5")){
                search.setProduct_type("2");
            }else if(Objects.equals(vo.getCatId1(),"8")){
                search.setProduct_type("3");
            }
        }
        if(StringUtils.isNotEmpty(vo.getCatId2())){
            search.setCat_id2(vo.getCatId2());
        }
        if(StringUtils.isNotEmpty(vo.getCatId3())){
            search.setCat_id3(vo.getCatId3());
        }
        if(StringUtils.isNotEmpty(vo.getChannelCategoryId()) && StringUtils.isEmpty(vo.getCatId2())){
            search.setCat_id2(vo.getChannelCategoryId());
        }
        //setBrandOrType(vo,search);
        return search;
    }

    private void setBrandOrType(ChannelSupplierProductRequestVO vo,CakeProductSearchDTO search){
        if(StringUtils.isEmpty(vo.getChannelCategoryId())){
           return;
        }
        ChannelCategoryDTO dto = new ChannelCategoryDTO();
        dto.setId(Long.valueOf(vo.getChannelCategoryId()));
        ChannelCategoryDTO channelCategoryDTO = channelCategoryFacade.findChannelCategoryById(dto);
        if(channelCategoryDTO ==null){
            dto.setId(null);
            dto.setChannelCategoryId(vo.getChannelCategoryId());
            List<ChannelCategoryDTO> list = channelCategoryFacade.findChannelCategoryAll(dto);
            if(CollectionUtils.isEmpty(list)){
                return;
            }
            channelCategoryDTO = list.get(0);
        }
        if(channelCategoryDTO.getChannelCategoryType()==1){
            search.setProduct_type(channelCategoryDTO.getChannelCategoryId());
        }else{
            search.setBrand_id(channelCategoryDTO.getChannelCategoryId());
        }

    }

    @Override
    public JsonResult<PageResult<ChannelSupplierProductResponseVO>> enterprise(ChannelSupplierProductRequestVO vo,Pagination page){
        JsonResult<PageResult<ChannelSupplierProductResponseVO>> result = new JsonResult<>();
        PageResult<ChannelSupplierProductResponseVO> pageResult = super.setDefaultPageResult(vo,page);
        CakeProductSearchDTO search = getSearchDTO(vo,page);
        JsonResult<List<CakeProductDTO>> rt =  cakeProductManage.searchOfCategoryLevel2(search);
        if(Objects.isNull(rt)){
            return JsonResult.success(pageResult);
        }
        if(!Objects.equals(rt.getCode(),0)){
            logger.info("渠道策略处理结果不正确:{}", JsonUtils.objectToJson(rt));
            result.setCode(rt.getCode());
            return JsonResult.fail(rt.getError(),rt.getCode());
        }
        if(CollectionUtils.isEmpty(rt.getData())){
            logger.info("渠道策略查询结果为空:{}", JsonUtils.objectToJson(rt));
            result.setCode(rt.getCode());
            return JsonResult.success(pageResult);
        }
        Long enterpriseId = EmptyUtil.isNotEmpty(vo.getEnterpriseId())?vo.getEnterpriseId():RuntimeContext.cacheUser().getEnterpriseId();
        List<ChannelEnterpriseConfigDTO> channelEnterpriseConfigs = channelPriceHelper.getChannelEnterpriseConfigAll(vo.getChannelCode(), enterpriseId,false);
        List<CakeProductDTO> list = rt.getData();
        List<ChannelSupplierProductResponseVO> rtList = new ArrayList<>(list.size());
        for (CakeProductDTO dto : list) {
            addToList(channelEnterpriseConfigs,vo,rtList,dto,null);
        }
        pageResult.setList(rtList);
        pageResult.setTotalSize(rtList.size()+TOTAL_SIZE);
        if(CollectionUtils.isEmpty(rtList)){
            pageResult.setTotalSize(0);
        }
        result.setData(pageResult);
        return result;
    }

}
