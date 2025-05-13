package com.egeo.components.third.strategy.factory;

import com.egeo.components.third.common.RouteRuteNameEnum;
import com.egeo.components.third.strategy.EnterpriseServiceRouteStrategy;
import com.egeo.components.third.strategy.FieldsCheckRuteService;
import com.egeo.components.third.strategy.FieldsFormatRuteService;
import com.egeo.components.utils.StringUtil;
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
public class EnterpriseServiceRouteFactory implements ApplicationContextAware {


    private Map<String, EnterpriseServiceRouteStrategy> serviceRouteStrategy = new HashMap<>();


    /**
     * 通过spring方式通过构造方法得到所有的接口实现类
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, EnterpriseServiceRouteStrategy> ruteMap = applicationContext.getBeansOfType(EnterpriseServiceRouteStrategy.class);
        ruteMap.forEach((key,value)->serviceRouteStrategy.put(value.getRouteRuteName(), value));
    }

    /**
     * 通过规则名ruteName获取对应的实现策略
     * @param ruteName
     * @return
     */
    public EnterpriseServiceRouteStrategy getRouteService(String ruteName){
        if(StringUtil.isEmpty(ruteName)){
            return serviceRouteStrategy.get(RouteRuteNameEnum.LIST_SORT.getRuteName());
        }
        return serviceRouteStrategy.containsKey(ruteName)?serviceRouteStrategy.get(ruteName):serviceRouteStrategy.get(RouteRuteNameEnum.LIST_SORT.getRuteName());
    }
}
