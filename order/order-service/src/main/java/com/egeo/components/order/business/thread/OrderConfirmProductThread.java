package com.egeo.components.order.business.thread;

import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.dto.OrderResult;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.strategy.factory.OrderConfirmFactory;
import com.egeo.components.order.strategy.vo.OrderConfirmReqVO;
import com.egeo.utils.SpringContextTool;

import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/1 18:11
 * @Version V1.0
 **/
public class OrderConfirmProductThread implements Callable<OrderResult> {
    OrderConfirmReqVO reqVO;
    public OrderConfirmProductThread(OrderConfirmReqVO reqVO){
        this.reqVO = reqVO;
    }

    @Override
    public OrderResult call(){
        OrderConfirmFactory orderConfirmFactory =SpringContextTool.getBean(OrderConfirmFactory.class);
        String channelCode = sourceTransformChannelCode(reqVO.getSource(),false);
        OrderResult orderResult = orderConfirmFactory.getSearchProductStrategy(channelCode).buyProductByCart(reqVO);
        return orderResult;
    }

    private String sourceTransformChannelCode(Integer source,boolean isJd) {
        if(isJd || (source !=null && Objects.equals(source, ThirdConst.Source.JD))){
            return ProductChannelCodeEnum.JD.getCode();
        }
        if(source !=null && Objects.equals(source,ThirdConst.Source.CAKE)){
            return ProductChannelCodeEnum.CAKE.getCode();
        }else if(source !=null && Objects.equals(source,ThirdConst.Source.WORLD)){
            return ProductChannelCodeEnum.WORLD_BUY.getCode();
        }
        return ProductChannelCodeEnum.SELF.getCode();
    }
}
