package com.egeo.components.utils.weixin;
import com.belerweb.social.weixin.bean.AccessToken;

public class AccessToken2 extends AccessToken{

    private String unionid;

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
