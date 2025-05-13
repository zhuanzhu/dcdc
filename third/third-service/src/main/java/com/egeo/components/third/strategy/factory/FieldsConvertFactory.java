package com.egeo.components.third.strategy.factory;

import com.egeo.components.third.strategy.FieldsCheckRuteService;
import com.egeo.components.third.strategy.FieldsFormatRuteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class FieldsConvertFactory implements ApplicationContextAware {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<String, FieldsCheckRuteService> fieldsCheckRuteStrategy = new HashMap<>();

    private Map<String, FieldsFormatRuteService> fieldsFormatRuteStrategy = new HashMap<>();


    /**
     * 通过spring方式通过构造方法得到所有的接口实现类
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String,FieldsCheckRuteService> checkRuteMap = applicationContext.getBeansOfType(FieldsCheckRuteService.class);
        checkRuteMap.forEach((key,value)->fieldsCheckRuteStrategy.put(value.getRuteName(), value));

        Map<String,FieldsFormatRuteService> formatRuteMap = applicationContext.getBeansOfType(FieldsFormatRuteService.class);
        formatRuteMap.forEach((key,value)->fieldsFormatRuteStrategy.put(value.getRuteName(), value));

    }

    /**
     * 通过规则名ruteName获取对应的实现策略
     * @param ruteName
     * @return
     */
    public FieldsCheckRuteService getFieldsCheckRuteService(String ruteName){
        return fieldsCheckRuteStrategy.containsKey(ruteName)?fieldsCheckRuteStrategy.get(ruteName):null;
    }

    /**
     * 通过规则名ruteName获取对应的实现策略
     * @param ruteName
     * @return
     */
    public FieldsFormatRuteService getFieldsFormatRuteService(String ruteName){
        return fieldsFormatRuteStrategy.containsKey(ruteName)?fieldsFormatRuteStrategy.get(ruteName):null;
    }
}
