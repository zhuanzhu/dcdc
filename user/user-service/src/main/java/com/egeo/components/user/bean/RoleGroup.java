package com.egeo.components.user.bean;

import lombok.Data;

@Data
public class RoleGroup {
	private String roleGroupCode;
	private String roleGroupName;

	public RoleGroup(String roleGroupCode, String roleGroupName) {
		super();
		this.roleGroupCode = roleGroupCode;
		this.roleGroupName = roleGroupName;
	}

	public RoleGroup() {
		super();
	}

}
