package com.egeo.components.order.strategy.vo;

import com.egeo.components.order.dto.OrderResult;
import com.egeo.components.order.vo.DefaultReceiverInfoVo;
import com.egeo.components.order.vo.jd.ParseAddressJson;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/1 22:15
 * @Version V1.0
 **/
public class OrderConfirmChannelDeliveryReqVO implements Serializable {

    private ParseAddressJson parseAddressJson;

    private String token;

    private OrderResult orderResult;

    private DefaultReceiverInfoVo receiverInfo;

    private Long storeId;
    private Long platformId;
    private Long merchantId;
    public ParseAddressJson getParseAddressJson() {
        return parseAddressJson;
    }

    public void setParseAddressJson(ParseAddressJson parseAddressJson) {
        this.parseAddressJson = parseAddressJson;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public OrderResult getOrderResult() {
        return orderResult;
    }

    public void setOrderResult(OrderResult orderResult) {
        this.orderResult = orderResult;
    }

    public DefaultReceiverInfoVo getReceiverInfo() {
        return receiverInfo;
    }

    public void setReceiverInfo(DefaultReceiverInfoVo receiverInfo) {
        this.receiverInfo = receiverInfo;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }
}
