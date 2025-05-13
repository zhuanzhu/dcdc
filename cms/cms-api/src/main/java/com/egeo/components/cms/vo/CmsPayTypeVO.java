package com.egeo.components.cms.vo;

import java.io.Serializable;

/**
 * Created by 0.0 on 2018/9/4.
 */
public class CmsPayTypeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer payTypeCode;
    private String payTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPayTypeCode() {
        return payTypeCode;
    }

    public void setPayTypeCode(Integer payTypeCode) {
        this.payTypeCode = payTypeCode;
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

    public String getLogImageUrl() {
        return logImageUrl;
    }

    public void setLogImageUrl(String logImageUrl) {
        this.logImageUrl = logImageUrl;
    }

    public String getPayTypeRemarks() {
        return payTypeRemarks;
    }

    public void setPayTypeRemarks(String payTypeRemarks) {
        this.payTypeRemarks = payTypeRemarks;
    }

    private String logImageUrl;
    private String payTypeRemarks;
}
