package com.egeo.components.order.strategy.service.impl.create.item;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.facade.ProductFacade;
import com.egeo.components.order.strategy.vo.CreateOrderReqVO;
import com.egeo.components.order.strategy.vo.CreateOrderRespVO;
import com.egeo.components.order.vo.jd.*;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.delivery.JdUtils;
import com.egeo.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/5 15:29
 * @Version V1.0
 **/
@Service("addItemJdCreateOrderImpl")
public class AddItemJdCreateOrderImpl extends AddItemsCommonBase {

    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource(name = "productFacade")
    private ProductFacade productFacade;

    @Autowired
    private JdUtils jdUtils;

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public String getChannelCode() {
        return ProductChannelCodeEnum.JD.getCode();
    }

    @Override
    public JsonResult<CreateOrderRespVO> getProduct(CreateOrderReqVO createOrderReqVO) {
        CreateOrderRespVO createOrderRespVO = new CreateOrderRespVO();
        JdProductDTO jdProductDTOType0 = getJdProductDTO(createOrderReqVO);
        if(jdProductDTOType0 == null){
           return JsonResult.fail("京东商品不存在");
        }
        createOrderRespVO.setSourceProduct(3);
        createOrderRespVO.setProductObject(jdProductDTOType0);
        return JsonResult.success(createOrderRespVO);
    }

    public JdProductDTO getJdProductDTO(CreateOrderReqVO createOrderReqVO){
        JdProductDTO jdProductDTOType0 = productFacade.checkJdProductStatus(createOrderReqVO.getPuId()+"",createOrderReqVO.getEnterpriseId());
        return jdProductDTOType0;
    }

    public JsonResult<CreateOrderRespVO> addItemsAndLimitRulesType(CreateOrderReqVO createOrderReqVO,JdProductDTO jdProductDTOType0) {

        Long puId = createOrderReqVO.getPuId();
        ReceiverAddressDTO addr = createOrderReqVO.getAddr();
        Integer num = createOrderReqVO.getNum();
        Long userId = createOrderReqVO.getUserId();
        Long platformId = createOrderReqVO.getPlatformId();
        String userName = createOrderReqVO.getUserName();
        Long companyId = createOrderReqVO.getCompanyId();
        Long storeId = createOrderReqVO.getStoreId();
        UserDTO user = createOrderReqVO.getUser();
        String token = jdUtils.getAccessToken(jedisUtil);
        //2.校验京东是否可售
        String skuSellStatusFromJd = jdUtils.getSkuSellStatusFromJd(token, puId+"", "");
        JdResponse jdSellResponse = JSON.parseObject(skuSellStatusFromJd, JdResponse.class);
        if(jdSellResponse.isSuccess()&&jdSellResponse.getResultCode().equals("0000")){
            String json = jdSellResponse.getResult();
            List<JdSkuSellStatus> jdSkuStatus = JSON.parseArray(json, JdSkuSellStatus.class);
            if(jdSkuStatus.get(0).getSaleState()==0){
                return JsonResult.fail("抱歉，该商品已下架");
            }
        }else{
            return JsonResult.fail("查询上下架失败");
        }
        String addrStr = addr.getGoodReceiverProvince()+addr.getGoodReceiverCity()+addr.getGoodReceiverCounty()+addr.getGoodReceiverArea();
        ParseAddressJson parseAddressJson = getDeliveryPriceFromJd(token,addrStr);
        //4.校验是否在可售区域
        if(EmptyUtil.isNotEmpty(addr)){
            if(EmptyUtil.isNotEmpty(parseAddressJson)){
                String skuAddressSellStatusFromJd = jdUtils.getSkuAddressSellStatusFromJd(token, puId+"", parseAddressJson.getProvinceId(), parseAddressJson.getCityId(), parseAddressJson.getCountyId(), parseAddressJson.getTownId());
                JdResponse jdResponse1 = JSON.parseObject(skuAddressSellStatusFromJd, JdResponse.class);
                if(jdResponse1.isSuccess()&&jdResponse1.getResultCode().equals("0000")){
                    String json = jdResponse1.getResult();
                    List<JdSkuAddressSellStatus> jdSkuStatus = JSON.parseArray(json, JdSkuAddressSellStatus.class);
                    if(jdSkuStatus.get(0).getIsAreaRestrict().equals("true")){
                        return JsonResult.fail("抱歉，商品在收货地址区域内不可售，请重新选择收货地址");
                    }
                }else{
                    return JsonResult.fail("查询上下架失败");
                }


                //3.校验库存状态
                JdProductStockSearch stockSearch = new JdProductStockSearch();
                stockSearch.setSkuId(puId);
                stockSearch.setNum(Long.valueOf(num));
                List<JdProductStockSearch> searchList = new ArrayList<>();
                searchList.add(stockSearch);
                Integer provinceId = parseAddressJson.getProvinceId();
                Integer cityId = parseAddressJson.getCityId();
                Integer countyId = parseAddressJson.getCountyId();
                String arae = provinceId + "_" + cityId + "_" + countyId;
                String jdProductStockString= jdUtils.getJdProductStock(token, JSONObject.toJSONString(searchList), arae);
                List<JSONObject> jdProductStockArr= JSONObject.parseArray(jdProductStockString, JSONObject.class);
                JSONObject jdProductStock=JSONObject.parseObject(jdProductStockArr.get(0).toString());
                if(EmptyUtil.isEmpty(jdProductStock)){
                    return JsonResult.fail("当前商品为商品库存存在问题");
                }else{
                    String stockStateId = jdProductStock.getString("stockStateId");
                    if(stockStateId.equals("33")||stockStateId.equals("39")||stockStateId.equals("40")){
                        logger.info("有货");
                    }else{
                        return JsonResult.fail("抱歉，该商品无货");
                    }
                }

            }

        }

        CreateOrderRespVO createOrderRespVO = new CreateOrderRespVO();
        /**对应渠道对应的商品列表**/
         List<SoItemDTO> soItems = new ArrayList<>();

        // 组织限购规则记录集合
        List<LimitRuleRecordDTO> limitRuleRecordList = new ArrayList<>();

        Double orderAmount = 0D;
        //校验结束
        SoItemDTO item = new SoItemDTO();
        item.setCartType(1);
        item.setPlatformId(platformId);
        item.setPrice(jdProductDTOType0.getPrice());
        item.setPuCount(num);
        item.setPuId(puId);
        // 一期暂时认为购物车商品不存在unit属性
        item.setUnitExist(0);
        item.setUserId(userId);
        item.setPuPicUrl(jdProductDTOType0.primaryImg());
        item.setPuName(jdProductDTOType0.getName());
        item.setMerchantId(ThirdConst.Merchant.JD);
        item.setSource(3);
        item.setExternalSkuId(puId+"");
        item.setSnapshot(JSON.toJSONString(jdProductDTOType0));
        if (EmptyUtil.isNotEmpty(jdProductDTOType0)){
            String taxInfo=jdProductDTOType0.getTaxInfo();
            if (EmptyUtil.isNotEmpty(taxInfo) && com.egeo.components.utils.StringUtil.isNumeric(taxInfo)){
                item.setTaxRate(new BigDecimal(taxInfo));
            }
        }
        soItems.add(item);
        orderAmount += num * jdProductDTOType0.getPrice().doubleValue();


        // 限购规则记录
        LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
        lrro.setBuySum(Long.valueOf(num));
        lrro.setBuyMoneySum(jdProductDTOType0.getPrice());
        lrro.setProductUnitId(puId);
        lrro.setProductUnitSerialNumber("jd-"+puId);
        lrro.setStandardUnitId(puId);
        lrro.setCreateUserid(userId);
        lrro.setCreateUsername(userName);
        lrro.setCreateUserMobile(user.getMobile());
        lrro.setCompanyId(companyId);
        lrro.setStoreId(storeId);
        limitRuleRecordList.add(lrro);
        createOrderRespVO.setOrderAmount(orderAmount);
        createOrderRespVO.setSoItems(soItems);
        createOrderRespVO.setLimitRuleRecordList(limitRuleRecordList);
        createOrderRespVO.setSource(ThirdConst.Source.JD);
        createOrderRespVO.setOrderPayByCash(0D);
        createOrderRespVO.setLimitFuBiPayAmount(BigDecimal.ZERO);
        return JsonResult.success(createOrderRespVO);

    }

    @Override
    public JsonResult<CreateOrderRespVO> addItemsAndLimitRules(CreateOrderReqVO createOrderReqVO) {
        JdProductDTO jdProductDTOType0 = null;
        if(createOrderReqVO.getType().intValue() ==0){
            jdProductDTOType0 = (JdProductDTO)createOrderReqVO.getProductObject();
        }else{
            jdProductDTOType0 =  getJdProductDTO(createOrderReqVO);
        }
        if(jdProductDTOType0 ==null){
            return JsonResult.fail("抱歉，商品不存在");
        }
        if(jdProductDTOType0.getState()==0){
            return JsonResult.fail("抱歉，该商品已下架");
        }
        return addItemsAndLimitRulesType(createOrderReqVO,jdProductDTOType0);
    }

    public ParseAddressJson getDeliveryPriceFromJd(String token,String address) {

        Map<String, String> map = new HashMap<>();
        //1.将地址转换成京东地址编号
        ParseAddressJson parseAddressJson = null;

        try {
            String result = jdUtils.parseAddress(address, token);
            JdResponse jdResponse = JSON.parseObject(result, JdResponse.class);
            if (jdResponse.isSuccess() && jdResponse.getResultCode().equals("0000")) {
                String json = jdResponse.getResult();
                parseAddressJson = JSON.parseObject(json, ParseAddressJson.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取京东地址编号失败:" + e.getMessage());
        }
        return parseAddressJson;


    }
}
