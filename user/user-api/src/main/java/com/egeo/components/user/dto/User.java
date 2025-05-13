package com.egeo.components.user.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class User implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private Integer isWorkWechat=0;
    private Long id;
    private String name;
    private String cookieValue;
    private String mail;
    private Integer type;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * 当前用户是否是超级管理员,0：否  1：是
     */
    private Integer isSuperAdministrator;
    private Long platformId; // 平台id
    private String wxAppId;
    private String openId;// 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
    // 慢有优多账户返回结果集
    private List<Map<String, Object>> userList;
    
    public String getWxAppId() {
		return wxAppId;
	}

	public void setWxAppId(String wxAppId) {
		this.wxAppId = wxAppId;
	}

	public List<Map<String, Object>> getUserList() {
		return userList;
	}
	public void setUserList(List<Map<String, Object>> userList) {
		this.userList = userList;
	}
	public Long getPlatformId() {
		return platformId;
	}
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCookieValue() {
        return cookieValue;
    }
    public void setCookieValue(String cookieValue) {
        this.cookieValue = cookieValue;
    }
	public Integer getIsSuperAdministrator() {
		return isSuperAdministrator;
	}
	public void setIsSuperAdministrator(Integer isSuperAdministrator) {
		this.isSuperAdministrator = isSuperAdministrator;
	}

    public Integer getIsWorkWechat() {
        return isWorkWechat;
    }

    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setIsWorkWechat(Integer isWorkWechat) {
        this.isWorkWechat = isWorkWechat;
    }
}
