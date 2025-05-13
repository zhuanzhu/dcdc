package com.egeo.components.product.dto;

import java.io.Serializable;
import java.util.List;

public class CategoryAttName implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -5177254100408702146L;
    
    private List<CategoryAttNameValuse> lists;
    
    
    private List<Long> pidList;
    
    private Long productId;

    public List<CategoryAttNameValuse> getLists() {
        return lists;
    }

    public void setLists(List<CategoryAttNameValuse> lists) {
        this.lists = lists;
    }

    public List<Long> getPidList() {
        return pidList;
    }

    public void setPidList(List<Long> pidList) {
        this.pidList = pidList;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    
}
