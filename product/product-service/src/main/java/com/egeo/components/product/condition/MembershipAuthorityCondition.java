package com.egeo.components.product.condition;

import com.egeo.components.product.po.MembershipAuthorityPO;

/**
 * 
 * @author whf
 * @date 2018-09-06 16:12:28
 */
public class MembershipAuthorityCondition extends MembershipAuthorityPO {
	private static final long serialVersionUID = 1L;

	private String authorityName;
	
	private String membershipName;

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getMembershipName() {
		return membershipName;
	}

	public void setMembershipName(String membershipName) {
		this.membershipName = membershipName;
	}
	
}
	