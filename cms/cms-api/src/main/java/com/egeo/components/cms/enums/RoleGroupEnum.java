package com.egeo.components.cms.enums;

public enum RoleGroupEnum {
	LO_GROUP("lo_group", "助贷机构组"), 
	LO_YW("lo_yw", "助贷机构业务组"), 
	LO_ADMIN("lo_admin", "助贷机构管理组"),
	LO_FST_VERIFY("lo_fst_verify", "助贷机构初审组"),
	LO_RECHECK("lo_recheck", "助贷机构复审组"),
	LO_ENDCHECK("lo_endcheck", "助贷机构终审组"),
	LO_FINANCE_CHECK("lo_finance_check","助贷机构财务复核组"),
	CAPITAL_GROUP("capital_group","资金方组"),
	CAPITAL_RISK("capital_risk","资金方风控组"), 
	CAPITAL_FACE_CHECK("capital_face_check","资金方面审组"), 
	OPERATIONS_MANAGER("operations_manager", "运营管理组"), 
	PLATFORM_FST_CHECK("platform_fst_check", "平台初审组"),
	PLATFORM_RECHECK("platform_recheck", "平台复审组"),
	PLATFORM_ENDCHECK("platform_endcheck", "平台终审组"),
	FINANCE_RECHECK("finance_recheck", "平台财务复核组"),
	
	;

	private String roleGroupCode;
	private String roleGroupName;

	private RoleGroupEnum(String roleGroupCode, String roleGroupName) {
		this.roleGroupCode = roleGroupCode;
		this.roleGroupName = roleGroupName;
	}

	public String getRoleGroupCode() {
		return roleGroupCode;
	}

	public void setRoleGroupCode(String roleGroupCode) {
		this.roleGroupCode = roleGroupCode;
	}

	public String getRoleGroupName() {
		return roleGroupName;
	}

	public void setRoleGroupName(String roleGroupName) {
		this.roleGroupName = roleGroupName;
	}

}
