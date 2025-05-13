package com.egeo.components.user.vo;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-04-13 16:31:40
 */
public class CompanyPageVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 类型：1、我的订单 2、保险订单 3、我的体检
	 */
	private Integer type;
	/**
	 * 配置项名称
	 */
	private String name;
	/**
	 * 公司id
	 */
	private Long companyId;
	/**
	 * 是否显示：0否1是 默认为显示
	 */
	private Integer showOrNot;

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
	 * 类型：1、我的订单 2、保险订单 3、我的体检
	 * @return 类型：1、我的订单 2、保险订单 3、我的体检
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 类型：1、我的订单 2、保险订单 3、我的体检
	 * @param type 类型：1、我的订单 2、保险订单 3、我的体检
	 */
	public void setType(Integer type) {
		this.type = type;
	}	
	/**
	 * 配置项名称
	 * @return 配置项名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 配置项名称
	 * @param name 配置项名称
	 */
	public void setName(String name) {
		this.name = name;
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
	/**
	 * 是否显示：0否1是 默认为显示
	 * @return 是否显示：0否1是 默认为显示
	 */
	public Integer getShowOrNot() {
		return showOrNot;
	}

	/**
	 * 是否显示：0否1是 默认为显示
	 * @param showOrNot 是否显示：0否1是 默认为显示
	 */
	public void setShowOrNot(Integer showOrNot) {
		this.showOrNot = showOrNot;
	}	
}
	