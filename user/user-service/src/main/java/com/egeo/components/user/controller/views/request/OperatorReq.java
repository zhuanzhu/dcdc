package com.egeo.components.user.controller.views.request;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author liwenshuai
 * @title
 * @package com.egeo.components.user.controller.views.request
 * @date 2019/9/19 14:53
 */
public class OperatorReq {
    @ApiModelProperty("业务员uuid")
    private String userUuid;

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }
}
