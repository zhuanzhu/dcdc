package com.egeo.components.user.controller.views.response;

/**
 * @author liwenshuai
 * @title
 * @package com.xption.user.controller.views.response
 * @date 2019/9/21 16:34
 */
public class OperatorLoginResponse {

    private String accessToken;
    private String accessExpire;
    private String refreshToken;
    private String refreshExpire;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessExpire() {
        return accessExpire;
    }

    public void setAccessExpire(String accessExpire) {
        this.accessExpire = accessExpire;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshExpire() {
        return refreshExpire;
    }

    public void setRefreshExpire(String refreshExpire) {
        this.refreshExpire = refreshExpire;
    }
}
