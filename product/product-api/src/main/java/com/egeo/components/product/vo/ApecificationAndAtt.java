package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.List;

import com.egeo.components.product.dto.CategoryAttNameValuse;

public class ApecificationAndAtt implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -230749377957743354L;

	private List<CategoryAttNameValuse> attList;
	
	private List<AttributeNameVO> apecificationList;
	
	private List<CategoryAttNameValuse> parameterAttList;

	public List<CategoryAttNameValuse> getAttList() {
		return attList;
	}

	public void setAttList(List<CategoryAttNameValuse> attList) {
		this.attList = attList;
	}

	public List<AttributeNameVO> getApecificationList() {
		return apecificationList;
	}

	public void setApecificationList(List<AttributeNameVO> apecificationList) {
		this.apecificationList = apecificationList;
	}

	public List<CategoryAttNameValuse> getParameterAttList() {
		return parameterAttList;
	}

	public void setParameterAttList(List<CategoryAttNameValuse> parameterAttList) {
		this.parameterAttList = parameterAttList;
	}
	

}
