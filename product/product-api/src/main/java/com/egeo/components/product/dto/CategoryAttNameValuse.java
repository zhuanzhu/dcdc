package com.egeo.components.product.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryAttNameValuse implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = -2249220359006039458L;

    private AttributeNameDTO attributeNameDTO;
    
    private List<AttributeValueDTO> lists;
    
    private List<Object> values = new ArrayList<Object>();
    
    private Object value;

	public AttributeNameDTO getAttributeNameDTO() {
		return attributeNameDTO;
	}

	public void setAttributeNameDTO(AttributeNameDTO attributeNameDTO) {
		this.attributeNameDTO = attributeNameDTO;
	}

	public List<AttributeValueDTO> getLists() {
		return lists;
	}

	public void setLists(List<AttributeValueDTO> lists) {
		this.lists = lists;
	}

	public List<Object> getValues() {
		return values;
	}

	public void setValues(List<Object> values) {
		this.values = values;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
