package com.egeo.components.product.condition;

import com.egeo.components.product.po.MembershipPO;

/**
 * 
 * @author whf
 * @date 2018-09-06 16:12:28
 */
public class MembershipCondition extends MembershipPO {
	
	private static final long serialVersionUID = 1L;

	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
	