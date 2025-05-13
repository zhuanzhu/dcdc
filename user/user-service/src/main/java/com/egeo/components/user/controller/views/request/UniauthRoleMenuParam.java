package com.egeo.components.user.controller.views.request;

import java.util.List;

/**
 * Copyright (C), 2017-2018, 仁辉科技有限公司
 * FileName: UniauthRoleMenuParam
 * Author:   EDZ
 * Date:     2018/10/29 9:45
 * Description: 权限菜单
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class UniauthRoleMenuParam {
    private Long rid;
    private List<String> menuCodes;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public List<String> getMenuCodes() {
        return menuCodes;
    }

    public void setMenuCodes(List<String> menuCodes) {
        this.menuCodes = menuCodes;
    }

    @Override
    public String toString() {
        return "UniauthRoleMenuParam{" +
                "rid=" + rid +
                ", menuCodes=" + menuCodes +
                '}';
    }
}
