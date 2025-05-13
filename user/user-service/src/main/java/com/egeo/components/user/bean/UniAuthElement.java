package com.egeo.components.user.bean;

/**
 * @ClassName: Element 
 * @Description: 页面属性
 * @author: jane
 * @date: 2017年11月17日 上午11:38:07
 */
public class UniAuthElement {
	private Integer eleId;
	/**属性名称*/
	private String eleName;
	/**菜单编号*/
	private String menuCode;
	/**属性类型*/
	private String eleType;
	/**属性ref*/
	private String eleRef;
	
	private Integer rid;
	private Integer appId;
	private String appCode;
	
	
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public UniAuthElement() {
		super();
	}
	public UniAuthElement(Integer eleId, Integer rid) {
		super();
		this.eleId = eleId;
		this.rid = rid;
	}
	public Integer getEleId() {
		return eleId;
	}
	public void setEleId(Integer eleId) {
		this.eleId = eleId;
	}
	public String getEleName() {
		return eleName;
	}
	public void setEleName(String eleName) {
		this.eleName = eleName;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getEleType() {
		return eleType;
	}
	public void setEleType(String eleType) {
		this.eleType = eleType;
	}
	public String getEleRef() {
		return eleRef;
	}
	public void setEleRef(String eleRef) {
		this.eleRef = eleRef;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
}
