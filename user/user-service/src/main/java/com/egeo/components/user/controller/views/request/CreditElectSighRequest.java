package com.egeo.components.user.controller.views.request;

/**
 * @author liwenshuai
 * @title
 * @package com.egeo.components.user.controller.views.request
 * @date 2019/9/24 19:36
 */
public class CreditElectSighRequest {
    /**
     * 进件订单号
     */
    private String orderId;
    /**
     * 借款人/共同还款人uuid
     */
    private String userUuid;
    /**
     * png格式手写签名照片url
     */
    private String image;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
