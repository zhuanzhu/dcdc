package com.egeo.components.user.controller.views.response;

/**
 * @author liwenshuai
 * @title
 * @package com.xption.user.controller.views.response
 * @date 2019/9/19 15:00
 */
public class OperatorPersonResp {
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户手机号
     */
    private String userMobile;
    /**
     * 公司名称
     */
    private String company;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
