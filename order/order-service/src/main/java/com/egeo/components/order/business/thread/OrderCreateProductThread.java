package com.egeo.components.order.business.thread;

import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.strategy.factory.OrderCreateFactory;
import com.egeo.components.order.strategy.vo.CreateOrderReqVO;
import com.egeo.components.order.strategy.vo.CreateOrderRespVO;
import com.egeo.utils.SpringContextTool;
import com.egeo.web.JsonResult;

import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/5 23:21
 * @Version V1.0
 **/
public class OrderCreateProductThread implements Callable<JsonResult<CreateOrderRespVO>> {

    private CreateOrderReqVO cartCreateOrderReqVO;

    public OrderCreateProductThread(CreateOrderReqVO cartCreateOrderReqVO){
        this.cartCreateOrderReqVO=cartCreateOrderReqVO;
    }

    @Override
    public JsonResult<CreateOrderRespVO> call(){
        OrderCreateFactory orderCreateFactory = SpringContextTool.getBean(OrderCreateFactory.class);
        JsonResult<CreateOrderRespVO> jrt = orderCreateFactory.getOrderCreateStrategy(getChannelCode(cartCreateOrderReqVO.getSource())).addItemsAndLimitRules(cartCreateOrderReqVO);
        return jrt;
    }

    private String getChannelCode(Integer source){
        String channelCode = ProductChannelCodeEnum.SELF.getCode();
        if(source !=null && Objects.equals(source, ThirdConst.Source.JD)){
            return ProductChannelCodeEnum.JD.getCode();
        }
        if(source !=null && Objects.equals(source,ThirdConst.Source.CAKE)){
            return ProductChannelCodeEnum.CAKE.getCode();
        }
        if(source !=null && Objects.equals(source,ThirdConst.Source.WORLD)){
            return ProductChannelCodeEnum.WORLD_BUY.getCode();
        }
        return channelCode;
    }
}
