package com.egeo.components.product.strategy.factory;

import com.egeo.components.product.common.BusinessException;
import com.egeo.components.product.strategy.ChannelPlatformProductStrategy;
import com.egeo.components.product.strategy.ProductUpOrDownStrategy;
import com.egeo.components.product.strategy.ReceiveProductStrategy;
import com.egeo.components.product.strategy.SearchProductStrategy;
import com.egeo.utils.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 后台商品查询工厂
 * @Author lsl
 * @Version V1.0
 **/
@Service
public class ChannelPlatformProductFactory  implements ApplicationContextAware {
    /***供应商产品查询策略***/
    private Map<String, ChannelPlatformProductStrategy> managerServiceMap = new HashMap<>();


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        /***供应商产品查询策略***/
        Map<String, ChannelPlatformProductStrategy> map = applicationContext.getBeansOfType(ChannelPlatformProductStrategy.class);
        map.forEach((key,value)->managerServiceMap.put(value.getProductCode(), value));

     }

    public boolean checkHasSupportSearchProduct(String channelCode){
        if(StringUtils.isEmpty(channelCode)){
            throw new BusinessException("缺少商品查找code");
        }
        return managerServiceMap.containsKey(channelCode);
    }

    public ChannelPlatformProductStrategy getSearchProductStrategy(String channelCode){
        if(!checkHasSupportSearchProduct(channelCode)){
            throw new BusinessException("未定义对应的商品渠道策略");
        }
        return managerServiceMap.get(channelCode);
    }


}
