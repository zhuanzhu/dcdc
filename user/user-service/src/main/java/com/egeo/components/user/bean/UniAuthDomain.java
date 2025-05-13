package com.egeo.components.user.bean;

/**
 * @ClassName: Element 
 * @Description: 页面属性
 * @author: jane
 * @date: 2017年11月17日 上午11:38:07
 */
public class UniAuthDomain {
	private String domainUrl;
	/**属性名称*/
	private String name;
	/**菜单编号*/
	private String code;
	/**属性类型*/
	private String desc;
	
	private Integer id;
	private Integer status;
	private Long tenancyId;
	
	
	public UniAuthDomain() {
		super();
	}


	public String getDomainUrl() {
		return domainUrl;
	}


	public void setDomainUrl(String domainUrl) {
		this.domainUrl = domainUrl;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getTenancyId() {
		return tenancyId;
	}

	public void setTenancyId(Long tenancyId) {
		this.tenancyId = tenancyId;
	}
}
