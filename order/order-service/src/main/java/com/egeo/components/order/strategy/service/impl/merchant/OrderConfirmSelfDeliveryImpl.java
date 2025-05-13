package com.egeo.components.order.strategy.service.impl.merchant;

import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.dto.OrderResult;
import com.egeo.components.order.facade.ProductFacade;
import com.egeo.components.order.facade.PromotionFacade;
import com.egeo.components.order.strategy.vo.OrderConfirmChannelDeliveryReqVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/1 21:55
 * @Version V1.0
 **/
@Service("orderConfirmSelfDeliveryImpl")
public class OrderConfirmSelfDeliveryImpl extends OrderConfirmChannelDeliveryBase{
    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Resource(name = "promotionFacade")
    private PromotionFacade promotionFacade;
    @Resource(name = "productFacade")
    private ProductFacade productFacade;

    @Override
    public String getChannelCode() {
        return ProductChannelCodeEnum.SELF.getCode();
    }

    @Override
    public Map<String, Object> getDelivery(OrderConfirmChannelDeliveryReqVO reqVO) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", "1");
        map.put("deliveryPrice","0");
        Long storeId = reqVO.getStoreId();
        Long platformId = reqVO.getPlatformId();
        OrderResult orderResult = reqVO.getOrderResult();
        //判断不是零售门店且不是电子卡券商品，且不是通过兑换券直接购买，计算运费
        //计算门店折扣后的金额，再来计算运费
        BigDecimal dicount=BigDecimal.valueOf(promotionFacade.getDiscount(storeId,platformId)).divide(BigDecimal.valueOf(100));//折扣率*100
        //门店折扣后金额
        BigDecimal afterStoreDiscount = (orderResult.getOrderAmount().multiply(dicount)).setScale(2,BigDecimal.ROUND_DOWN);
        double freightAmount = productFacade.freightAmountByMerchantId(afterStoreDiscount.doubleValue(), storeId, platformId, orderResult.getMerchant().getId());
        map.put("deliveryPrice",freightAmount);
        if(freightAmount >0){
            map.put("deliveryMethod", 1);
        }
        return map;
    }
}
