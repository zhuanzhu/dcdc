package com.egeo.components.third.strategy.factory;

import com.egeo.components.third.common.EncryptTypeEnum;
import com.egeo.components.third.strategy.SignService;
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
public class SignFactory implements ApplicationContextAware {

    private Map<String, SignService> signMap = new HashMap<>();


    /**
     * 通过spring方式通过构造方法得到所有的接口实现类
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, SignService> serviceMap = applicationContext.getBeansOfType(SignService.class);
        serviceMap.forEach((key,value)->signMap.put(value.getEncryptType(), value));
    }

    /**
     * 通过加解密方式获取对应的实现策略
     * @param encryptType
     * @return
     */
    public SignService getSignService(String encryptType){
        if(Objects.isNull(encryptType)){
            return signMap.get(EncryptTypeEnum.DEFAULT.getEncryptType());
        }
        return signMap.containsKey(encryptType)?signMap.get(encryptType):signMap.get(EncryptTypeEnum.DEFAULT.getEncryptType());
    }
}
