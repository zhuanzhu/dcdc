package com.egeo.components.user.controller.views.request;

/**
 * @author liwenshuai
 * @title
 * @package com.egeo.components.user.controller.views.request
 * @date 2019/9/21 16:36
 */
public class OperatorLoginRequest {

    private String mobile;

    private String passwordMd5;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPasswordMd5() {
        return passwordMd5;
    }

    public void setPasswordMd5(String passwordMd5) {
        this.passwordMd5 = passwordMd5;
    }
}
