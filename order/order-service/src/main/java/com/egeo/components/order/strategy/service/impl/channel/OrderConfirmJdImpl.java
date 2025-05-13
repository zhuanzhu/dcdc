package com.egeo.components.order.strategy.service.impl.channel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.order.dto.OrderConfirmGoodsDTO;
import com.egeo.components.order.dto.OrderResult;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.facade.MerchantFacade;
import com.egeo.components.order.facade.ProductFacade;
import com.egeo.components.order.strategy.vo.OrderConfirmReqVO;
import com.egeo.components.order.vo.jd.*;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.components.utils.JdUtils2;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.delivery.JdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description 京东商品确认
 * @Author lsl
 * @Date 2024/12/1 13:32
 * @Version V1.0
 **/
@Service("orderConfirmJdImpl")
public class OrderConfirmJdImpl extends OrderConfirmChannelBase{

    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private JdUtils jdUtils;
    @Autowired
    private JedisUtil jedisUtil;
    @Resource(name = "merchantFacade")
    private MerchantFacade merchantFacade;

    @Resource(name = "productFacade")
    private ProductFacade productFacade;

    @Autowired
    private JdUtils2 jdUtils2;

    @Override
    public String getChannelCode() {
        return ProductChannelCodeEnum.JD.getCode();
    }

    @Override
    public OrderResult buyPrductByRightNow(OrderConfirmReqVO reqVO) {
        OrderResult result = new OrderResult();
        result.setIsPayByFuBiOnly(0);
        OrderConfirmGoodsDTO gvo = new OrderConfirmGoodsDTO();
        Boolean flag = false;
        Long  puId = reqVO.getPuId();
        Integer num = reqVO.getNum();
        String address = reqVO.getAddress();
        ParseAddressJson parseAddressJson = reqVO.getParseAddressJson();
        int buyType = 1;
        //校验京东
        //1.校验京东上下架
        String externalSkuId = puId+"";
        JdProductDTO jdProductDTO = reqVO.getJdProductDTO();
        if(jdProductDTO ==null){
             jdProductDTO = productFacade.checkJdProductStatus(puId+"", reqVO.getEnterpriseId());
        }
        if(jdProductDTO == null){
            result.setError("抱歉，未找到对应的商品");
            result.setSuccess(false);
        }
        double price = jdProductDTO.getPrice().doubleValue();
        BigDecimal payByFuBiAmount = BigDecimal.ZERO;
        int goodsAccount = num;//订单商品总数
        BigDecimal orderAmount = BigDecimal.ZERO;//订单总金额
        List<OrderConfirmGoodsDTO> goodsList = new ArrayList<>();
        List<OrderConfirmGoodsDTO> jdDownGoods = new ArrayList<>();
        List<LimitRuleRecordDTO> limitRuleRecordList = new ArrayList<>();
        gvo.setCartItemId(reqVO.getCartItemId());
        gvo.setNum(num);
        gvo.setPrice(price);
        gvo.setPuId(puId);
        if(jdProductDTO.getSkuJson()!=null && jdProductDTO.getSkuJson().length()>5) {
            JSONObject skuJson = JSON.parseObject(jdProductDTO.getSkuJson());
            StringBuffer skuDesc = new StringBuffer();
            for(Map.Entry<String, Object> entry: skuJson.entrySet()) {
                if(skuDesc.length()>2) {
                    skuDesc.append("\n");
                }
                skuDesc.append(entry.getKey()).append(":").append(entry.getValue().toString());
            }
            gvo.setSkuDesc(skuDesc.toString());
        }
        String imagePath = jdProductDTO.getImagePath();
        JSONObject object = JSONObject.parseObject(imagePath);
        String assisImg = object.getString("assisImg");
        String primaryImg = object.getString("primaryImg");
        gvo.setPuImg(primaryImg);
        gvo.setPuName(jdProductDTO.getName());
        gvo.setBuyType(1);
        gvo.setExternalSkuId(externalSkuId);
        gvo.setStandardUnitId(puId);
        gvo.setLimitBuyNum(1L);
        gvo.setSource(ThirdConst.Source.JD);
        gvo.setIsOwnMerchant(0);
        if(jdProductDTO.getState()==0){
            flag = true;

            gvo.setErrMessage("抱歉，该商品已下架");
            jdDownGoods.add(gvo);
            result.setError("抱歉，该商品已下架");
            result.setSuccess(false);
            result.setJdDownGoods(jdDownGoods);

            return result;


        }
        //String token = jdUtils.getAccessToken(jedisUtil);
        String token = jdUtils2.getAccessToken(jedisUtil);
        //2.校验京东是否可售
        String skuSellStatusFromJd = jdUtils.getSkuSellStatusFromJd(token, externalSkuId, "");
        JdResponse jdSellResponse = JSON.parseObject(skuSellStatusFromJd, JdResponse.class);
        if(jdSellResponse.isSuccess()&&jdSellResponse.getResultCode().equals("0000")){
            String json = jdSellResponse.getResult();
            List<JdSkuSellStatus> jdSkuStatus = JSON.parseArray(json, JdSkuSellStatus.class);
            if(jdSkuStatus.get(0).getSaleState()==0){
                flag = true;

                gvo.setErrMessage("抱歉，该商品已下架");
                jdDownGoods.add(gvo);
                result.setError("抱歉，该商品已下架");
                result.setSuccess(false);
                result.setJdDownGoods(jdDownGoods);
                return result;

            }
        }else{
            throw new BusinessException("查询上下架失败");
        }
        //4.校验是否在可售区域
        if(EmptyUtil.isNotEmpty(address)){
            if(EmptyUtil.isNotEmpty(parseAddressJson)){
                String skuAddressSellStatusFromJd = jdUtils.getSkuAddressSellStatusFromJd(token, externalSkuId, parseAddressJson.getProvinceId(), parseAddressJson.getCityId(), parseAddressJson.getCountyId(), parseAddressJson.getTownId());
                JdResponse jdResponse1 = JSON.parseObject(skuAddressSellStatusFromJd, JdResponse.class);
                if(jdResponse1.isSuccess()&&jdResponse1.getResultCode().equals("0000")){
                    String json = jdResponse1.getResult();
                    List<JdSkuAddressSellStatus> jdSkuStatus = JSON.parseArray(json, JdSkuAddressSellStatus.class);
                    if(jdSkuStatus.get(0).getIsAreaRestrict().equals("true")){
                        flag = true;

                        gvo.setErrMessage("抱歉，该商品在收货地址区域内不可售，请重新选择收货地址");
                        jdDownGoods.add(gvo);
                        result.setError("抱歉，该商品在收货地址区域内不可售，请重新选择收货地址");
                        result.setSuccess(false);
                        result.setJdDownGoods(jdDownGoods);
                        return result;
                    }
                }else{
                    throw new BusinessException("查询上下架失败");
                }


                //3.校验库存状态
                JdProductStockSearch stockSearch = new JdProductStockSearch();
                stockSearch.setSkuId(Long.valueOf(externalSkuId));
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
                    throw new BusinessException("当前商品为商品库存存在问题");
                }else{
                    String stockStateId = jdProductStock.getString("stockStateId");
                    if(stockStateId.equals("33")||stockStateId.equals("39")||stockStateId.equals("40")){
                        logger.info("有货");
                    }else{
                        flag = true;
                        gvo.setErrMessage("抱歉，该商品无货");
                        jdDownGoods.add(gvo);
                        result.setError("抱歉，该商品无货");
                        result.setSuccess(false);
                        result.setJdDownGoods(jdDownGoods);
                        return result;
                    }
                }

            }

        }

        if(!flag){
            orderAmount=orderAmount.add(BigDecimal.valueOf(num).multiply(BigDecimal.valueOf(price)));
            //立即购买,单个商品,设置仅现金支付金额
            BigDecimal orderAmountOnlyCash = new BigDecimal(0);
            //如果商品是积分+现金则设置为0
            result.setOrderAmountOnlyCash(orderAmountOnlyCash);
            goodsList.add(gvo);
        }
        MerchantDTO merchant = merchantFacade.findMerchantById(ThirdConst.Merchant.JD);
        setCommonResult(result, payByFuBiAmount, goodsAccount, orderAmount, goodsList, jdDownGoods, limitRuleRecordList,merchant);
        return result;
    }



    @Override
    public OrderResult buyProductByCart(OrderConfirmReqVO reqVO) {

         return buyPrductByRightNow(reqVO);
    }
}
