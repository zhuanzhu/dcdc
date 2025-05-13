package com.egeo.components.order.strategy.service.impl.merchant;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.dto.OrderConfirmGoodsDTO;
import com.egeo.components.order.dto.OrderResult;
import com.egeo.components.order.strategy.vo.OrderConfirmChannelDeliveryReqVO;
import com.egeo.components.order.vo.jd.JdDeliveryPrice;
import com.egeo.components.order.vo.jd.ParseAddressJson;
import com.egeo.components.order.vo.jd.SkuInfo;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.delivery.JdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 京东获取运费
 * @Author lsl
 * @Date 2024/12/1 21:50
 * @Version V1.0
 **/
@Service("orderConfirmJdDeliveryImpl")
public class OrderConfirmJdDeliveryImpl extends OrderConfirmChannelDeliveryBase{
    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private JdUtils jdUtils;

    @Override
    public String getChannelCode() {
        return ProductChannelCodeEnum.JD.getCode();
    }

    @Override
    public Map<String, Object> getDelivery(OrderConfirmChannelDeliveryReqVO reqVO) {
        OrderResult orderResult = reqVO.getOrderResult();
        ParseAddressJson parseAddressJson  = reqVO.getParseAddressJson();
        String token = reqVO.getToken();
        Map<String, Object> deliveryPriceFromJd = new HashMap<>();
        deliveryPriceFromJd.put("success", "-1");
        deliveryPriceFromJd.put("deliveryPrice","0");
        deliveryPriceFromJd.put("jdDeliveryError","失败");
        if(EmptyUtil.isEmpty(parseAddressJson)){
            deliveryPriceFromJd.put("jdDeliveryError","未设置地址，计算运费失败");
            return deliveryPriceFromJd;
        }
        //如果是京东运营方则通过接口调用获取京东的运费
        List<OrderConfirmGoodsDTO> goodsListDto = orderResult.getGoodsList();
        List<SkuInfo> skuInfoList = new ArrayList<>();
        for(OrderConfirmGoodsDTO goodsDTO:goodsListDto){
            SkuInfo skuInfo = new SkuInfo();
            String skuId=goodsDTO.getExternalSkuId();
            Integer skuNum = goodsDTO.getNum();
            skuInfo.setNum(skuNum);
            skuInfo.setSkuId(skuId);
            skuInfoList.add(skuInfo);
        }

        deliveryPriceFromJd=buildDeliveryPrice(token,skuInfoList,parseAddressJson);
        //获取运费成功
        logger.info("获取京东商品运费最终结论:{}", JSON.toJSONString(deliveryPriceFromJd));
        return deliveryPriceFromJd;
    }

    private Map<String,Object> buildDeliveryPrice(String token,List<SkuInfo> skuInfoList,ParseAddressJson parseAddressJson){
        Map<String, Object> map = new HashMap<>();
        try {
            String deliveryPriceFromJd = jdUtils.getDeliveryPriceFromJd(token,
                    JSON.toJSONString(skuInfoList),
                    parseAddressJson.getProvinceId(),
                    parseAddressJson.getCityId(),
                    parseAddressJson.getCountyId(),
                    parseAddressJson.getTownId()
            );
            logger.info("京东商品查询结果:sku列表{},结果{}",JSON.toJSONString(skuInfoList),deliveryPriceFromJd);
            if(EmptyUtil.isNotEmpty(deliveryPriceFromJd)){
                JSONObject resultObj = JSONObject.parseObject(deliveryPriceFromJd);
                Boolean success = resultObj.getBoolean("success");
                if (EmptyUtil.isNotEmpty(success) && success) {
                    JdDeliveryPrice jdDeliveryPrice = JSON.parseObject(resultObj.getString("result"), JdDeliveryPrice.class);
                    if(EmptyUtil.isEmpty(jdDeliveryPrice.getFreight())){
                        map.put("deliveryPrice","0");

                    }else {
                        map.put("deliveryPrice",jdDeliveryPrice.getFreight() );
                    }
                    map.put("success", "1");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询地址运费出错" + e.getMessage());
            map.put("success", "-1");
            map.put("jdDeliveryError","查询地址运费出错");
        }

        return map;

    }
}
