package com.egeo.components.order.strategy.service.impl.merchant;

import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.strategy.vo.OrderConfirmChannelDeliveryReqVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/1 21:56
 * @Version V1.0
 **/
@Service("orderConfirmWorldDeliveryImpl")
public class OrderConfirmWorldDeliveryImpl extends OrderConfirmChannelDeliveryBase{
    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public String getChannelCode() {
        return ProductChannelCodeEnum.WORLD_BUY.getCode();
    }

    @Override
    public Map<String, Object> getDelivery(OrderConfirmChannelDeliveryReqVO reqVO) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", "1");
        map.put("deliveryPrice","0");
        return map;
    }
}
