package com.egeo.components.user.bean;

import lombok.Data;

@Data
public class UniauthRoleGroup {
	private Integer rid;
	private String roleGroupCode;

	public UniauthRoleGroup(Integer rid, String roleGroupCode) {
		super();
		this.rid = rid;
		this.roleGroupCode = roleGroupCode;
	}

	public UniauthRoleGroup() {
		super();
	}

}
