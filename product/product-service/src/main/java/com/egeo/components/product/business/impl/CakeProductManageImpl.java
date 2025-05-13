package com.egeo.components.product.business.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.product.business.CakeProductManage;
import com.egeo.components.product.common.BusinessException;
import com.egeo.components.product.common.ProductChannelCodeEnum;
import com.egeo.components.product.dao.write.ChannelPriceDAO;
import com.egeo.components.product.dto.*;
import com.egeo.components.product.enums.ChannelPriceConstants;
import com.egeo.components.product.enums.ProductRedisKeyEnum;
import com.egeo.components.product.helper.ChannelPriceHelper;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.utils.CakeUtil;
import com.egeo.components.utils.FHCollectionUtils;
import com.egeo.components.utils.JsonUtils;
import com.egeo.config.RuntimeContext;
import com.egeo.orm.PageResult;
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
import java.util.*;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/4/28 17:18
 * @Version V1.0
 **/
@Service("cakeProductManage")
public class CakeProductManageImpl implements CakeProductManage {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private CakeUtil cakeUtil;

    @Autowired
    private ChannelPriceHelper channelPriceHelper;

    @Autowired
    private ChannelPriceDAO channelPriceDAO;

    @Autowired
    private CompanyClient companyClient;

    @Resource
    private JedisUtil jedisUtil;

    /**
     * @Description 商品列表
     **/
    @Override
    public JsonResult<List<CakeProductDTO>> searchOfCategoryLevel2(CakeProductSearchDTO search){
        return searchProduct(search);
    }

    /**
     * 按标题搜索
     * @param search
     * @return
     */
    @Override
    public JsonResult<List<CakeProductDTO>> searchOfKeyWord(CakeProductSearchDTO search){
        return searchList(search);
    }

    /**
     * 按标题搜索
     * @param search
     * @return
     */
    @Override
    public JsonResult<List<CakeProductDTO>> searchList(CakeProductSearchDTO search){
        JsonResult<List<CakeProductDTO>> result = searchProduct(search);
        if(result ==null || result.getData()==null || result.getData().size() ==0){
            return result;
        }
        Long enterpriseId = RuntimeContext.cacheUser().getEnterpriseId();
        List<CakeProductDTO> resultList = new ArrayList<>();
        List<CakeProductDTO> list = result.getData();
        List<ChannelEnterpriseConfigDTO>  channelEnterpriseConfigs = channelPriceHelper.getChannelEnterpriseConfigAll(ProductChannelCodeEnum.CAKE.getCode(),enterpriseId,false);
        Map<String,ChannelPriceDTO> priceDTOMap =  toChannelPriceDTOMap(list,enterpriseId);
        for (CakeProductDTO cakeProductDTO : list) {
            List<CakeSpecsDTO>  specsDTOList = cakeProductDTO.getSpecs();
            if(CollectionUtils.isEmpty(specsDTOList)){
                continue;
            }
            List<CakeSpecsDTO> tempList = new ArrayList<>();
            for (CakeSpecsDTO spec : specsDTOList) {
                if(EmptyUtil.isEmpty(spec.getMarket_price()) || EmptyUtil.isEmpty(spec.getClearing_price())){
                    logger.error("商品不合法，规格市场价或结算价不能为空{}",JSON.toJSONString(spec));
                    continue;
                }
                Map<String, String>  priceMap= channelPriceHelper.calcPlatformPrice(spec.getMarket_price(),spec.getClearing_price(),channelEnterpriseConfigs,priceDTOMap.get(cakeProductDTO.getId()+spec.getSpec_id()));
                spec.setPrice(priceMap.get(ChannelPriceConstants.SALE_PRICE_KEY));
                tempList.add(spec);
            }
            if(!CollectionUtils.isEmpty(tempList)){
                Optional<CakeSpecsDTO> minSalePriceCondition = FHCollectionUtils.findMinT(tempList,CakeSpecsDTO::getPrice);
                minSalePriceCondition.ifPresent(condition -> {
                    cakeProductDTO.setPrice(condition.getPrice());
                    cakeProductDTO.setMarket_price(condition.getMarket_price());
                });
            }
            resultList.add(cakeProductDTO);
        }
        result.setData(resultList);
        return result;
    }

    private Map<String,ChannelPriceDTO> toChannelPriceDTOMap(List<CakeProductDTO> rtList,Long enterpriseId){
        List<String> pid = FHCollectionUtils.listToStrList(rtList,CakeProductDTO::getId);
        List<String> specIds = getSpecIdList(rtList);
        return channelPriceHelper.getChannelPriceMap(ProductChannelCodeEnum.CAKE.getCode(),pid,specIds,enterpriseId);
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

    /**
     * 查询商品详情
     * @param search
     * @return
     */
    @Override
    public JsonResult<CakeProductDetailDTO> searchProductDetail(CakeProductDetailSearchDTO search){
        if(StringUtils.isEmpty(search.getUser_id())){
            String uid = getUid();
            search.setUser_id(uid);
        }
        logger.info("蛋糕叔叔查询商品详情请求参数:{}",JSON.toJSONString(search));
        JSONObject jSONObject = cakeUtil.getProductDetail(search.getProduct_id(),search.getCity_id(),search.getUser_id());
        //logger.info("蛋糕叔叔查询商品详情结果:{}",JSON.toJSONString(jSONObject));
        JsonResult checkResult = cakeUtil.checkResult(jSONObject);
        if(Objects.nonNull(checkResult)){
            return checkResult;
        }
        //
        jSONObject.remove("extra_data");
        String data = jSONObject.getString(cakeUtil.DATA_KEY);
        JsonResult<CakeProductDetailDTO> result = new JsonResult<>();
        if(StringUtils.isEmpty(data)){
            return result;
        }
        CakeProductDetailDTO rt = JSONObject.parseObject(data,CakeProductDetailDTO.class);
        result.setData(rt);
        return result;
    }

    /**
     * @Description 商品状态查询接口
     * @Param productId 商品ID
     * @return 商品状态，1是上架，0是下架
     **/
    @Override
    public JsonResult<String> searchProductStatus(String productId){
        JSONObject jSONObject = cakeUtil.getProductStatus(productId);
        JsonResult checkResult = cakeUtil.checkResult(jSONObject);
        if(Objects.nonNull(checkResult)){
            return checkResult;
        }
        String data = jSONObject.getString(cakeUtil.DATA_KEY);
        JSONObject dataJson = JSONObject.parseObject(data);
        String status = dataJson.getString("status");
        return JsonResult.success(status);
    }


    @Override
    public List<CakeProductDetailDTO> getCakeProductDetailDTOList(List<String> productIdList,String cityId,Long enterpriseId){
        if(CollectionUtils.isEmpty(productIdList)){
            return null;
        }
        List<CakeProductDetailDTO> rtList = new ArrayList<>();
        String uid = getUid();
        CakeProductDetailSearchDTO search=null;
        for (String productId : productIdList) {
            search = new CakeProductDetailSearchDTO();
            if(StringUtils.isNotEmpty(cityId)){
                search.setCity_id(cityId);
            }
            search.setUser_id(uid);
            search.setProduct_id(productId);
            search.setEnterpriseId(enterpriseId);
            JsonResult<CakeProductDetailDTO> searchResult = searchProductCalcPriceDetail(search);
            if(Objects.isNull(searchResult) || !Objects.equals(searchResult.getCode(),0) || Objects.isNull(searchResult.getData())){
                continue;
            }
            CakeProductDetailDTO repDTO =  searchResult.getData();
            rtList.add(repDTO);
        }
        return rtList;
    }

    private String getUid() {
        ProductRedisKeyEnum productRedisKeyEnum = ProductRedisKeyEnum.CAKE_USER_ID_KEY;
        String redisKey = ProductRedisKeyEnum.getCakeUserIdKey(cakeUtil.getUid());
        Object userId = jedisUtil.get(redisKey);
        if(EmptyUtil.isNotEmpty(userId)){
            logger.info("缓存中有蛋糕叔叔的用户id，直接返回缓存中的蛋糕叔叔的用户id{}",userId);
            return (String)userId;
        }
        JSONObject loginResult = cakeUtil.userLogin(cakeUtil.getUid());
        JsonResult checkResult = cakeUtil.checkResult(loginResult);
        if(Objects.nonNull(checkResult)){
            throw new BusinessException(checkResult.getError() !=null?checkResult.getError():"登录第三方失败");
        }
        JSONObject uidObject = loginResult.getJSONObject(cakeUtil.DATA_KEY);
        String uid = uidObject.getString("id");
        if(EmptyUtil.isNotEmpty(uid)){
            jedisUtil.set(redisKey, productRedisKeyEnum.getExpireTime(),uid);
        }
        return uid;
    }

    /**
     * 查询商品详情
     * @param search
     * @return
     */
    @Override
    public JsonResult<CakeProductDetailDTO> searchProductDetail(CakeSPUIdSearchProductDetailDTO search){
        CakeProductDetailSearchDTO searchProduct = new CakeProductDetailSearchDTO();
        searchProduct.setProduct_id(search.getSpuId());
        searchProduct.setCity_id(getCityId(search.getCityName(),search.getCityId()));
        JsonResult<CakeProductDetailDTO>  detailDTOJsonResult = this.searchProductDetail(searchProduct);
        if(Objects.isNull(detailDTOJsonResult) || Objects.isNull(detailDTOJsonResult.getData())){
            return null;
        }
        return detailDTOJsonResult;
        //return calcSingleProductPrice(detailDTOJsonResult);
    }
    /***
     * @Description 以sku为维度的商品信息列表
     **/
    @Override
    public JsonResult<PageResult<CakeSpecsProductDetailDTO>>  searchPlatform(CakeProductSearchDTO search){
        PageResult<CakeSpecsProductDetailDTO> pageResult = new PageResult<>();
        JsonResult<List<CakeSpecsProductDetailDTO>> listRt = searchSkuProductList(search);
        pageResult.setList(listRt.getData());
        pageResult.setPageNo(search.getPage());
        pageResult.setPageSize(search.getSize());
        pageResult.setTotalSize(0);
        if(Objects.nonNull(listRt) && !CollectionUtils.isEmpty(listRt.getData())){
            pageResult.setTotalSize(listRt.getData().size()+50);
        }
        return JsonResult.success(pageResult);
    }


    public JsonResult<List<CakeSpecsProductDetailDTO>>  searchSkuProductList(CakeProductSearchDTO search){
        JsonResult<List<CakeSpecsProductDetailDTO>> pageResultJsonResult = new JsonResult<>();
        List<CakeSpecsProductDetailDTO> rtList = new ArrayList<>();
        JsonResult<List<CakeProductDTO>> dataRT = searchProduct(search);
        List<CakeProductDTO> dataList = dataRT.getData();
        CakeProductToSkuProductList(rtList,dataList);
        pageResultJsonResult.setData(rtList);
        return pageResultJsonResult;
    }



    private void CakeProductToSkuProductList(List<CakeSpecsProductDetailDTO> rtList,List<CakeProductDTO> cakeProductDTOList){
        if(rtList == null){
            rtList = new ArrayList<>();
        }
        if(CollectionUtils.isEmpty(cakeProductDTOList)){
            return;
        }
        for (CakeProductDTO cakeProductDTO : cakeProductDTOList) {
            List<CakeSpecsDTO> specs = cakeProductDTO.getSpecs();
            if(CollectionUtils.isEmpty(specs)){
                continue;
            }
            for (CakeSpecsDTO spec : specs) {
                rtList.add(new CakeSpecsProductDetailDTO(cakeProductDTO,spec));
            }
        }
    }



    private JsonResult searchProduct(CakeProductSearchDTO search) {
        JsonResult<List<CakeProductDTO>> pageResultJsonResult = new JsonResult<>();
        try{
            Map<String,String> paramMap = JsonUtils.beanToMap(search);
            logger.info("请求蛋糕叔叔参数:"+JsonUtils.objectToJson(paramMap));
            JSONObject jSONObject = cakeUtil.getProductHotList(paramMap);
            JsonResult checkResult = cakeUtil.checkResult(jSONObject);
            if(Objects.nonNull(checkResult)){
                return checkResult;
            }
            JSONObject data = jSONObject.getJSONObject(cakeUtil.DATA_KEY);
//            String dataJsonString = data.getString("product_list");
//            pageResultJsonResult.setData(JsonUtils.jsonToList(dataJsonString,CakeProductDTO.class));
            pageResultJsonResult.setData(jsonToList(data));
            //logger.info("请求蛋糕叔叔结果:{}",JsonUtils.objectToJson(pageResultJsonResult));
        }catch (Exception e){
            logger.error("请求蛋糕叔叔商品列表发生异常:{}",e);
            return JsonResult.fail("查询发生异常:"+e.getMessage());
        }
        return pageResultJsonResult;
    }

    public List<CakeProductDTO> jsonToList(JSONObject jsonObject) {
        // 将JSONObject转换为JSONArray
        // JSON中包含一个名为"product_list"的数组
        JSONArray jsonArray = jsonObject.getJSONArray("product_list");
        // 使用fastjson的JSON.parseArray方法将JSONArray转换为List<CakeProductDTO>
        List<CakeProductDTO> productList = JSON.parseArray(jsonArray.toJSONString(), CakeProductDTO.class);
        if(Objects.nonNull(productList)){
            logger.info("蛋糕叔叔列表返回:{}条数据",productList.size());
        }
        return productList;
    }

    private String getCityId(String cityName,String cityId){
        if(EmptyUtil.isNotEmpty(cityId)){
            return cityId;
        }
        if(EmptyUtil.isEmpty(cityName)){
            return null;
        }
        ProductRedisKeyEnum productRedisKeyEnum = ProductRedisKeyEnum.CAKE_CITY_ID_KEY;
        String redisKey = ProductRedisKeyEnum.getCakeCityIdKey(MD5Util.MD5(cityName));
        int seconds = productRedisKeyEnum.getExpireTime();
        Object redisValue = jedisUtil.get(redisKey);
        if(EmptyUtil.isNotEmpty(redisValue)){
            logger.info("缓存中有城市id，直接返回缓存中的城市id");
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
        cityId = data.getString("id");
        if(EmptyUtil.isNotEmpty(cityId)){
            jedisUtil.set(redisKey, seconds, cityId);
        }
        return cityId;
    }

    @Override
    public JsonResult<CakeProductDetailDTO> searchProductDetailCalcPrice(CakeSPUIdSearchProductDetailDTO search){
        JsonResult<CakeProductDetailDTO>  detailDTOJsonResult = this.searchProductDetail(search);
        if(Objects.isNull(detailDTOJsonResult) || Objects.isNull(detailDTOJsonResult.getData())){
            return null;
        }
        return  calcSingleProductPrice(detailDTOJsonResult,search.getEnterpriseId());
    }

    @Override
    public JsonResult<CakeProductDetailDTO> searchProductCalcPriceDetail(CakeProductDetailSearchDTO search){
        JsonResult<CakeProductDetailDTO>  jsonResult = this.searchProductDetail(search);
        if(jsonResult.getData()==null){
            return jsonResult;
        }
        JsonResult<CakeProductDetailDTO> jsonResult1 = calcSingleProductPrice(jsonResult,search.getEnterpriseId());
        if (jsonResult1 != null){
            return jsonResult1;
        }
        return jsonResult;
    }

    private JsonResult<CakeProductDetailDTO> calcSingleProductPrice(JsonResult<CakeProductDetailDTO> jsonResult,Long enterpriseId) {
        CakeProductDetailDTO cakeProductDetailDTO = jsonResult.getData();
        calcSingleProductPriceDetail(cakeProductDetailDTO,enterpriseId);
        return jsonResult;
    }

    private CakeProductDetailDTO calcSingleProductPriceDetail(CakeProductDetailDTO cakeProductDetailDTO,Long enterpriseId_) {
        if(cakeProductDetailDTO == null){
            return null;
        }
        CakeProductDetailProductsDTO productDTO = cakeProductDetailDTO.getProduct();
        List<CakeProductDetailSpecsDTO> specsDTOS = cakeProductDetailDTO.getSpecs();
        List<String> specIds = FHCollectionUtils.listToStrList(specsDTOS,CakeProductDetailSpecsDTO::getId);
        String productId = productDTO.getId();
        String channelCode = ProductChannelCodeEnum.CAKE.getCode();
        Long enterpriseId = enterpriseId_ !=null?enterpriseId_:getEnterpriseId();
        List<ChannelEnterpriseConfigDTO>  channelEnterpriseConfigDTOList = channelPriceHelper.getChannelEnterpriseConfigAll(channelCode, enterpriseId,false);
        Map<String,ChannelPriceDTO> priceDTOMap =channelPriceHelper.getChannelPriceMap(channelCode, Arrays.asList(productId),specIds,enterpriseId);
        if(CollectionUtils.isEmpty(specsDTOS)){
            Map<String, String>  calcPriceMap = channelPriceHelper.calcPlatformPrice(productDTO.getMarket_price(),productDTO.getPrice(),channelEnterpriseConfigDTOList,priceDTOMap.get(productDTO.getId()));
            productDTO.setPrice(calcPriceMap.get(ChannelPriceConstants.SALE_PRICE_KEY));
            return cakeProductDetailDTO;
        }
        for (CakeProductDetailSpecsDTO specsDTO : specsDTOS) {
            Map<String, String>  calcPriceMap = channelPriceHelper.calcPlatformPrice(specsDTO.getMarket_price(),specsDTO.getClearing_price(),channelEnterpriseConfigDTOList,priceDTOMap.get(productDTO.getId()+specsDTO.getId()));
            specsDTO.setPrice(calcPriceMap.get(ChannelPriceConstants.SALE_PRICE_KEY));
        }
        if(!CollectionUtils.isEmpty(specsDTOS)){
            Optional<CakeProductDetailSpecsDTO> minSalePriceCondition =FHCollectionUtils.findMinT(specsDTOS,CakeProductDetailSpecsDTO::getPrice);
            minSalePriceCondition.ifPresent(condition -> {
                productDTO.setPrice(condition.getPrice());
                productDTO.setMarket_price(condition.getMarket_price());
            });
        }
        return cakeProductDetailDTO;
    }

    private Long getEnterpriseId() {
        if(RuntimeContext.cacheUser()==null || (RuntimeContext.cacheUser().isNotPlatformUser() && RuntimeContext.cacheUser().getEnterpriseId()==null)) {
            if(RuntimeContext.cacheUser().getCompanyId()!=null) {
                CompanyDTO company = companyClient.findCompanyById(RuntimeContext.cacheUser().getCompanyId());
                if(company.getEnterpriseId()!=null) {
                    logger.info("用户："+RuntimeContext.cacheUser().getLoginName()+"  登录公司id:"+RuntimeContext.cacheUser().getCompanyId().longValue()+" 代理id为空并填充成功");
                    RuntimeContext.cacheUser().setEnterpriseId(company.getEnterpriseId());
                }
            }
        }
        return RuntimeContext.cacheUser().getEnterpriseId();
    }
}
