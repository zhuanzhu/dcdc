package com.egeo.components.order.notice;

import com.egeo.components.order.common.BusinessException;
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
 * @Version V1.0
 **/
@Service
public class OrderProcessFactory implements ApplicationContextAware {

    private Map<String, OrderNoticeStrategy> managerServiceMap = new HashMap<>();


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String,OrderNoticeStrategy> map = applicationContext.getBeansOfType(OrderNoticeStrategy.class);
        map.forEach((key,value)->managerServiceMap.put(value.getChannelCode(), value));
    }


    public OrderNoticeStrategy getOrderNoticeStrategy(String channelCode){
        if(!checkHasSupportOrderNotice(channelCode)){
            throw new BusinessException("未定义对应的渠道策略");
        }
        return managerServiceMap.get(channelCode);
    }

    public boolean checkHasSupportOrderNotice(String channelCode){
        if(StringUtils.isEmpty(channelCode)){
            throw new BusinessException("缺少渠道code");
        }
        return managerServiceMap.containsKey(channelCode);
    }
}
