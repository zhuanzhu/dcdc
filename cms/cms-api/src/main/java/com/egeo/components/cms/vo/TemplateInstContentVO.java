package com.egeo.components.cms.vo;

import java.util.List;
import java.util.Map;

/**
 * Created by 0.0 on 2018/11/6.
 */
public class TemplateInstContentVO {
    /**
     * 实例inst集合
     */
    private List<Map<String, Object>> instMapList;

    private int totalSize;
    private int pageNo;
    private int pageSize;

    public List<Map<String, Object>> getInstMapList() {
        return instMapList;
    }

    public void setInstMapList(List<Map<String, Object>> instMapList) {
        this.instMapList = instMapList;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

    public boolean isShowFgj() {
        return showFgj;
    }

    public void setShowFgj(boolean showFgj) {
        this.showFgj = showFgj;
    }

    public boolean isShowCooperator() {
        return showCooperator;
    }

    public void setShowCooperator(boolean showCooperator) {
        this.showCooperator = showCooperator;
    }

    public String getCoopUrl() {
        return coopUrl;
    }

    public void setCoopUrl(String coopUrl) {
        this.coopUrl = coopUrl;
    }

    /**
     * 模板id
     */
    private Long templateId;

    /**
     * 模板类型 0:商城类 1:应用类
     */
    private Integer templateType;

    /**
     * 是否显示福管家logo
     */
    private boolean showFgj;

    /**
     * 是否显示合作方logo
     */
    private boolean showCooperator;

    /**
     * 合作方logo url
     */
    private String coopUrl;

}
