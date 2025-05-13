package com.egeo.components.user.controller.views.request;

import io.swagger.annotations.ApiModelProperty;

/**
 * Copyright (C), 2017-2019, 仁辉科技有限公司
 *
 * @Author: chenhui
 * Date:     19:23 2019/9/21
 * Description:
 * History:
 * Version: 1.0
 */
public class ValidateSmsCodeRequest {

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("验证码")
    private String idfyCode;

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
}
