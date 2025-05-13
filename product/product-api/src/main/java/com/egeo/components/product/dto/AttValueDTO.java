package com.egeo.components.product.dto;

import java.io.Serializable;

public class AttValueDTO implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -6434433295250547703L;

    private Long id;
    
    private Long parentId;
    
    private String attValue;
    
    private Integer sortValue;
    
    /**
	 * 规格码
	 */
	private String specificationCode;	
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttValue() {
        return attValue;
    }

    public void setAttValue(String attValue) {
        this.attValue = attValue;
    }

    public Integer getSortValue() {
        return sortValue;
    }

    public void setSortValue(Integer sortValue) {
        this.sortValue = sortValue;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

	public String getSpecificationCode() {
		return specificationCode;
	}

	public void setSpecificationCode(String specificationCode) {
		this.specificationCode = specificationCode;
	}
    

}
