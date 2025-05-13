package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.List;

public class Stock implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -1555048495494915377L;
    
    private Long id;

    /**
     * 属性
     */
    private List<AttNameValueVO> list;
    
    /**
     * 真实库存
     */
    private Long realStockNum;
    
    private Long stockQuantity;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AttNameValueVO> getList() {
        return list;
    }

    public void setList(List<AttNameValueVO> list) {
        this.list = list;
    }

    public Long getRealStockNum() {
        return realStockNum;
    }

    public void setRealStockNum(Long realStockNum) {
        this.realStockNum = realStockNum;
    }

    public Long getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Long stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    
}
