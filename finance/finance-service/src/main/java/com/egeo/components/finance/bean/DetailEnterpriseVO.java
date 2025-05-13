package com.egeo.components.finance.bean;

import java.util.List;

public class DetailEnterpriseVO {
	private ReconciliationVO enterprise;
	private List<ReconciliationVO> companys;
	public ReconciliationVO getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(ReconciliationVO enterprise) {
		this.enterprise = enterprise;
	}
	public List<ReconciliationVO> getCompanys() {
		return companys;
	}
	public void setCompanys(List<ReconciliationVO> companys) {
		this.companys = companys;
	}

	
	
	
	
	
}
