package com.egeo.components.cms.enums;

public enum DataPermTypeEnum {
	LOAN_ORGANIZATION(1, "助贷机构"), CAPITAL(2, "资金方"), BUS_TYPE(3, "业务类型");

	private int type;
	private String name;

	private DataPermTypeEnum(int type, String name) {
		this.type = type;
		this.name = name; 
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
