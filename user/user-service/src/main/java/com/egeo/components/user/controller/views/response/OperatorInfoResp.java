package com.egeo.components.user.controller.views.response;

/**
 * @author liwenshuai
 * @title
 * @package com.xption.user.controller.views.response
 * @date 2019/7/23 18:59
 */
public class OperatorInfoResp {
	private String operatorUuid;
	private String userName;
	private String loanAidInstitutions;
	private String loanAidInstitutionsCode;

	public String getOperatorUuid() {
		return operatorUuid;
	}

	public void setOperatorUuid(String operatorUuid) {
		this.operatorUuid = operatorUuid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoanAidInstitutions() {
		return loanAidInstitutions;
	}

	public void setLoanAidInstitutions(String loanAidInstitutions) {
		this.loanAidInstitutions = loanAidInstitutions;
	}

	public String getLoanAidInstitutionsCode() {
		return loanAidInstitutionsCode;
	}

	public void setLoanAidInstitutionsCode(String loanAidInstitutionsCode) {
		this.loanAidInstitutionsCode = loanAidInstitutionsCode;
	}

}
