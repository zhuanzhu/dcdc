package com.egeo.components.user.controller.views.request;

/**
 * @author liwenshuai
 * @title
 * @package com.egeo.components.user.controller.views.request
 * @date 2019/9/24 21:21
 */
public class CreditResultRequest {
    /**
     * 进件订单号
     */
    private String orderId;
    /**
     * 借款人/共同还款人uuid
     */
    private String userUuid;
    /**
     * 身份证正面
     */
    private String positiveImgUrl;
    /**
     * 份证反面
     */
    private String negativeImgUrl;
    /**
     *身份证活体
     */
    private String assayImgUrl;
    /**
     *身份证活体
     */
    private Integer type;

    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

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

    public String getPositiveImgUrl() {
        return positiveImgUrl;
    }

    public void setPositiveImgUrl(String positiveImgUrl) {
        this.positiveImgUrl = positiveImgUrl;
    }

    public String getNegativeImgUrl() {
        return negativeImgUrl;
    }

    public void setNegativeImgUrl(String negativeImgUrl) {
        this.negativeImgUrl = negativeImgUrl;
    }

    public String getAssayImgUrl() {
        return assayImgUrl;
    }

    public void setAssayImgUrl(String assayImgUrl) {
        this.assayImgUrl = assayImgUrl;
    }
}
