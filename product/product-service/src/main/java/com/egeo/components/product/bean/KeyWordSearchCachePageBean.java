package com.egeo.components.product.bean;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/11/27 1:42
 * @Version V1.0
 **/
public class KeyWordSearchCachePageBean implements Serializable {

    private String channelCode;

    private Integer totalCount;

    private Integer pageSize;

    private Integer currPageNo;

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrPageNo() {
        return currPageNo;
    }

    public void setCurrPageNo(Integer currPageNo) {
        this.currPageNo = currPageNo;
    }
}
