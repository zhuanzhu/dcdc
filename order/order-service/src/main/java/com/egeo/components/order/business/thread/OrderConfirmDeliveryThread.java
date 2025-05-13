package com.egeo.components.order.business.thread;

import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.dto.OrderResult;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.strategy.factory.OrderConfirmFactory;
import com.egeo.components.order.strategy.vo.OrderConfirmChannelDeliveryReqVO;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.SpringContextTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/1 22:05
 * @Version V1.0
 **/
public class OrderConfirmDeliveryThread implements Callable<Map<String,Object>> {
    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private OrderConfirmChannelDeliveryReqVO reqVO;

    public OrderConfirmDeliveryThread(OrderConfirmChannelDeliveryReqVO reqVO){
        this.reqVO = reqVO;
    }

    @Override
    public Map<String, Object> call(){
        Long time1 = System.currentTimeMillis();
        OrderConfirmFactory orderConfirmFactory =  SpringContextTool.getBean(OrderConfirmFactory.class);
        Map<String,Object> resultMap = orderConfirmFactory.getDeliveryStrategy(merchantTransformChannelCode()).getDelivery(reqVO);
        if(EmptyUtil.isNotEmpty(resultMap) && !resultMap.containsKey("merchantId")){
            resultMap.put("merchantId",reqVO.getMerchantId());
        }
        Long time2 = System.currentTimeMillis();
        logger.info("商户确认订单时获取运费耗时情况商户id:{}耗时:{}",reqVO.getMerchantId(),(time2-time1));
        return resultMap;
    }

    private String merchantTransformChannelCode(){
        OrderResult orderResult = reqVO.getOrderResult();
        Long merchantId = orderResult.getMerchant().getId();
        String channelCode = ProductChannelCodeEnum.SELF.getCode();
        if(Objects.equals(merchantId, ThirdConst.Merchant.JD)){
            return ProductChannelCodeEnum.JD.getCode();
        }
        if(Objects.equals(merchantId,ThirdConst.Merchant.CAKE)){
            return ProductChannelCodeEnum.CAKE.getCode();
        }
        if(Objects.equals(merchantId,ThirdConst.Merchant.WORLD)){
            return ProductChannelCodeEnum.WORLD_BUY.getCode();
        }
        return channelCode;
    }

}
