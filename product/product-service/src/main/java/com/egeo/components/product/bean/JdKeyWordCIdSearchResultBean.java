package com.egeo.components.product.bean;

import com.egeo.components.product.dto.JdProductDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/11/28 19:37
 * @Version V1.0
 **/
public class JdKeyWordCIdSearchResultBean {

    private List<JdProductDTO> rslt = new ArrayList<>();

    private Integer pageNo;

    private Integer pageSize;

    private Integer maxTotalSize;

    public JdKeyWordCIdSearchResultBean() {
    }

    public JdKeyWordCIdSearchResultBean(List<JdProductDTO> rslt, Integer pageNo, Integer pageSize) {
        this.rslt = rslt;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public List<JdProductDTO> getRslt() {
        return rslt;
    }

    public void setRslt(List<JdProductDTO> rslt) {
        this.rslt = rslt;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getMaxTotalSize() {
        return maxTotalSize;
    }

    public void setMaxTotalSize(Integer maxTotalSize) {
        this.maxTotalSize = maxTotalSize;
    }
}
