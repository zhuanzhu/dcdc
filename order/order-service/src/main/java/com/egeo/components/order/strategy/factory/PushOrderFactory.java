package com.egeo.components.order.strategy.factory;


import com.egeo.components.order.common.BusinessException;
import com.egeo.components.order.strategy.service.PushUserOrderStrategy;
import com.egeo.components.order.strategy.service.SplitSoChildChildStrategy;
import com.egeo.utils.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 客户推送订单工厂
 * @Author lsl
 * @Date 2024/12/30 14:38
 * @Version V1.0
 **/
@Service
public class PushOrderFactory implements ApplicationContextAware {
    private Map<String, PushUserOrderStrategy> managerServiceMap = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        /***客户推送订单策略***/
        Map<String, PushUserOrderStrategy> map = applicationContext.getBeansOfType(PushUserOrderStrategy.class);
        map.forEach((key,value)->managerServiceMap.put(value.getChannelCode(), value));


    }

    public boolean checkHasSupportPushOrder(String channelCode){
        if(StringUtils.isEmpty(channelCode)){
            return false;
        }
        return managerServiceMap.containsKey(channelCode);
    }

    public PushUserOrderStrategy getPushUserOrderStrategy(String channelCode){
        if(!checkHasSupportPushOrder(channelCode)){
            throw new BusinessException("未定义对应的商品渠道策略");
        }
        return managerServiceMap.get(channelCode);
    }
}
