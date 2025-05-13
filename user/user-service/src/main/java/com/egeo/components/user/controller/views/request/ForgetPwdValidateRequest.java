package com.egeo.components.user.controller.views.request;

/**
 * @author liwenshuai
 * @title
 * @package com.egeo.components.user.controller.views.request
 * @date 2019/9/21 17:58
 */
public class ForgetPwdValidateRequest {
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 短信验证码
     */
    private String idfyCode;
    /**
	 * 用户类型
	 */
	private String userType;
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdfyCode() {
        return idfyCode;
    }

    public void setIdfyCode(String idfyCode) {
        this.idfyCode = idfyCode;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
