package com.egeo.components.order.strategy.factory;

import com.egeo.components.order.common.BusinessException;
import com.egeo.components.order.strategy.service.OrderConfirmChannelStrategy;
import com.egeo.components.order.strategy.service.OrderConfirmMerchantDeliveryStrategy;
import com.egeo.utils.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 订单确认工厂
 * @Author lsl
 * @Date 2024/12/1 12:54
 * @Version V1.0
 **/
@Service
public class OrderConfirmFactory  implements ApplicationContextAware {
    private Map<String, OrderConfirmChannelStrategy> managerServiceMap = new HashMap<>();

    private Map<String, OrderConfirmMerchantDeliveryStrategy> deliveryServiceMap = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        /***供应商产品查询策略***/
        Map<String, OrderConfirmChannelStrategy> map = applicationContext.getBeansOfType(OrderConfirmChannelStrategy.class);
        map.forEach((key,value)->managerServiceMap.put(value.getChannelCode(), value));

        /***供应商产品查询策略***/
        Map<String, OrderConfirmMerchantDeliveryStrategy> merchantDeliveryMap = applicationContext.getBeansOfType(OrderConfirmMerchantDeliveryStrategy.class);
        merchantDeliveryMap.forEach((key,value)->deliveryServiceMap.put(value.getChannelCode(), value));

    }

    public boolean checkHasSupportSearchProduct(String channelCode){
        if(StringUtils.isEmpty(channelCode)){
            throw new BusinessException("缺少商品查找code");
        }
        return managerServiceMap.containsKey(channelCode);
    }

    public OrderConfirmChannelStrategy getSearchProductStrategy(String channelCode){
        if(!checkHasSupportSearchProduct(channelCode)){
            throw new BusinessException("未定义对应的商品渠道策略");
        }
        return managerServiceMap.get(channelCode);
    }

    public boolean checkHasSupportDelivery(String channelCode){
        if(StringUtils.isEmpty(channelCode)){
            throw new BusinessException("缺少商品查找code");
        }
        return deliveryServiceMap.containsKey(channelCode);
    }

    public OrderConfirmMerchantDeliveryStrategy getDeliveryStrategy(String channelCode){
        if(!checkHasSupportDelivery(channelCode)){
            throw new BusinessException("未定义对应的商品渠道策略");
        }
        return deliveryServiceMap.get(channelCode);
    }
}
