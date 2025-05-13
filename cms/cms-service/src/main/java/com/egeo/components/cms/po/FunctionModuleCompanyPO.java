package com.egeo.components.cms.po;



/**
 * 
 * @author min
 * @date 2018-01-26 14:56:07
 */
public class FunctionModuleCompanyPO {


	private Long id;

	/**
	 * 功能模块id
	 */
	private Long functionModuleId;	

	/**
	 * 公司id
	 */
	private Long companyId;	

	public Long getId() {
		return id;
	}

	/**
	 * 主键
	 * @param id 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 功能模块id
	 * @return 功能模块id
	 */
	public Long getFunctionModuleId() {
		return functionModuleId;
	}

	/**
	 * 功能模块id
	 * @param functionModuleId 功能模块id
	 */
	public void setFunctionModuleId(Long functionModuleId) {
		this.functionModuleId = functionModuleId;
	}

	/**
	 * 公司id
	 * @return 公司id
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * 公司id
	 * @param companyId 公司id
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
}
	