package com.egeo.components.user.bean;

/**
 * @ClassName: Element 
 * @Description: 页面属性
 * @author: jane
 * @date: 2017年11月17日 上午11:38:07
 */
public class UniAuthApp {
	private Integer domain_id;
	/**属性名称*/
	private String name;
	/**菜单编号*/
	private String code;
	/**属性类型*/
	private String desc;
	
	private Integer id;
	
	
	public UniAuthApp() {
		super();
	}


	public Integer getDomain_id() {
		return domain_id;
	}


	public void setDomain_id(Integer domain_id) {
		this.domain_id = domain_id;
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
}
