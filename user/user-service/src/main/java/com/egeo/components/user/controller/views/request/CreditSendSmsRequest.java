package com.egeo.components.user.controller.views.request;

/**
 * @author liwenshuai
 * @title
 * @package com.egeo.components.user.controller.views.request
 * @date 2019/9/24 21:00
 */
public class CreditSendSmsRequest {
    /**
     * 进件订单号
     */
    private String orderId;
    /**
     * 借款人/共同还款人uuid
     */
    private String userUuid;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }
}
