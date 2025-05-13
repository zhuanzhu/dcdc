package com.egeo.components.order.strategy.service.impl.channel;

import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.order.dto.OrderConfirmGoodsDTO;
import com.egeo.components.order.dto.OrderResult;
import com.egeo.components.order.strategy.service.OrderConfirmChannelStrategy;
import com.egeo.components.order.strategy.vo.OrderConfirmReqVO;
import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.str.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/1 13:19
 * @Version V1.0
 **/
public abstract class OrderConfirmChannelBase implements OrderConfirmChannelStrategy {

    @Override
    public OrderResult checkBefore(OrderConfirmReqVO reqVO){
        OrderResult checkResult = new OrderResult();
        Integer type = reqVO.getType();
        if(EmptyUtil.isEmpty(reqVO.getType())){
            checkResult.setSuccess(false);
            checkResult.setError("参数为空");
            return checkResult;
        }
        if(type.intValue()==0){
            return checkBuyPrductByRightNow(reqVO);
        }
        checkResult.setSuccess(true);
        return checkResult;
    }

    private OrderResult checkBuyPrductByRightNow(OrderConfirmReqVO reqVO){
        OrderResult result = new OrderResult();
        Long puId = reqVO.getPuId();
        Integer num = reqVO.getNum();
        Integer couponType = reqVO.getCouponType();
        Long couponUnitId = reqVO.getCouponUnitId();
        String phone = reqVO.getPhone();
        Long commodityTemplateId = reqVO.getCommodityTemplateId();
        // 参数校验
        if (puId == null || num == null || puId == 0 || num == 0) {

            result.setSuccess(false);
            result.setError("参数为空");
            return result;
        }

        result.setIsPayByFuBiOnly(0);
        // 兑换卷购买su数量必须为1
        if ((EmptyUtil.isNotEmpty(couponType)) && (EmptyUtil.isNotEmpty(couponUnitId)) && couponType == 1 && num != 1) {
            result.setSuccess(false);
            result.setError("兑换数量必须是1");
            return result;
        }
        // 判断是否是话费充值
        if (commodityTemplateId != null && (commodityTemplateId.equals(4L)||commodityTemplateId.equals(9L))) {
            if (EmptyUtil.isEmpty(phone)) {
                result.setSuccess(false);
                result.setError("手机号码不能为空");
                return result;
            }
            if (!StringUtils.validMobile(phone)) {
                result.setSuccess(false);
                result.setError("请输入正确的手机号码");
                return result;
            }
        }

        result.setSuccess(true);
        return result;
    }


    public void setCommonResult(OrderResult result, BigDecimal payByFuBiAmount, int goodsAccount, BigDecimal orderAmount, List<OrderConfirmGoodsDTO> goodsList, List<OrderConfirmGoodsDTO> jdDownGoods, List<LimitRuleRecordDTO> limitRuleRecordList, MerchantDTO merchant) {
        result.setMerchant(merchant);
        result.setGoodsList(goodsList);
        result.setLimitRuleRecordList(limitRuleRecordList);
        result.setOrderAmount(orderAmount);
        result.setGoodsAccount(goodsAccount);
        result.setPayByFuBiAmount(payByFuBiAmount);
        result.setJdDownGoods(jdDownGoods);
        result.setSuccess(true);
    }
}
