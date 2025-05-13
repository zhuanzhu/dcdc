package com.egeo.components.third.strategy.factory;

import com.egeo.components.third.common.EncryptTypeEnum;
import com.egeo.components.third.strategy.RemoteHttpService;
import com.egeo.components.third.strategy.SignService;
import com.egeo.exception.BusinessException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service
public class RemoteHttpFactory implements ApplicationContextAware {

    private Map<String, RemoteHttpService> channelServiceMethodMap = new HashMap<>();

    /**
     * 通过spring方式通过构造方法得到所有的接口实现类
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, RemoteHttpService> serviceMap = applicationContext.getBeansOfType(RemoteHttpService.class);
        serviceMap.forEach((key,value)->channelServiceMethodMap.put(value.getChannelServiceMethod(), value));
    }

    /**
     * 通过加解密方式获取对应的实现策略
     * @param channelServiceMethod
     * @return
     */
    public RemoteHttpService getRemoteHttpService(String channelServiceMethod){
        if(!checkSupport(channelServiceMethod)){
            throw new BusinessException("暂不支持该请求方式");
        }
        return channelServiceMethodMap.get(channelServiceMethod);
    }

    public boolean checkSupport(String channelServiceMethod){
        if(Objects.isNull(channelServiceMethod)){
           return  false;
        }
        return channelServiceMethodMap.containsKey(channelServiceMethod);
    }

}
