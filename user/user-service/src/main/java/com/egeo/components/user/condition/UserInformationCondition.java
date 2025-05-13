package com.egeo.components.user.condition;

import com.egeo.components.user.po.UserInformationPO;

/**
 * 
 * @author min
 * @date 2018-02-01 11:32:50
 */
public class UserInformationCondition extends UserInformationPO {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 是否逻辑删除 0、否 1、是
	 */
	private Integer isDeleted;

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
	