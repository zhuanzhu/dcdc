package com.egeo.components.order.strategy.factory;

import com.egeo.components.order.common.BusinessException;
import com.egeo.components.order.strategy.service.OrderConfirmChannelStrategy;
import com.egeo.components.order.strategy.service.OrderCreateStrategy;
import com.egeo.components.order.strategy.service.SplitSoChildChildStrategy;
import com.egeo.components.order.strategy.service.SyncCreateThirdPartyOrderStrategy;
import com.egeo.utils.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/5 13:22
 * @Version V1.0
 **/
@Service
public class OrderCreateFactory implements ApplicationContextAware {

    private Map<String, OrderCreateStrategy> managerServiceMap = new HashMap<>();
    private Map<String, SplitSoChildChildStrategy> splitSoChildMap = new HashMap<>();
    private Map<String, SyncCreateThirdPartyOrderStrategy> syncThirdPartyOrderMap = new HashMap<>();
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        /***供应商产品查询策略***/
        Map<String, OrderCreateStrategy> map = applicationContext.getBeansOfType(OrderCreateStrategy.class);
        map.forEach((key,value)->managerServiceMap.put(value.getChannelCode(), value));

        Map<String, SplitSoChildChildStrategy> map1 = applicationContext.getBeansOfType(SplitSoChildChildStrategy.class);
        map1.forEach((key,value)->splitSoChildMap.put(value.getChannelCode(), value));

        Map<String, SyncCreateThirdPartyOrderStrategy> map2 = applicationContext.getBeansOfType(SyncCreateThirdPartyOrderStrategy.class);
        map2.forEach((key,value)->syncThirdPartyOrderMap.put(value.getChannelCode(), value));

    }


    public boolean checkHasSupportOrderCreate(String channelCode){
        if(StringUtils.isEmpty(channelCode)){
          return false;
        }
        return managerServiceMap.containsKey(channelCode);
    }

    public OrderCreateStrategy getOrderCreateStrategy(String channelCode){
        if(!checkHasSupportOrderCreate(channelCode)){
            throw new BusinessException("未定义对应的商品渠道策略");
        }
        return managerServiceMap.get(channelCode);
    }

    public boolean checkHasSupportSplitSoChild(String channelCode){
        if(StringUtils.isEmpty(channelCode)){
           return false;
        }
        return splitSoChildMap.containsKey(channelCode);
    }

    public SplitSoChildChildStrategy getSplitSoChildChildStrategy(String channelCode){
        if(!checkHasSupportSplitSoChild(channelCode)){
            throw new BusinessException("未定义对应的商品渠道策略");
        }
        return splitSoChildMap.get(channelCode);
    }


    public boolean checkHasSupportSyncCreateThirdPartyOrder(String channelCode){
        if(StringUtils.isEmpty(channelCode)){
            return false;
        }
        return syncThirdPartyOrderMap.containsKey(channelCode);
    }

    public SyncCreateThirdPartyOrderStrategy getSyncCreateThirdPartyOrderStrategy(String channelCode){
        if(!checkHasSupportSyncCreateThirdPartyOrder(channelCode)){
            throw new BusinessException("未定义对应的商品渠道策略");
        }
        return syncThirdPartyOrderMap.get(channelCode);
    }
}
