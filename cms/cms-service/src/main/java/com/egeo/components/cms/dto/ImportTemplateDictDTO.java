package com.egeo.components.cms.dto;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-06-08 18:51:59
 */
public class ImportTemplateDictDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 导入模版名称
	 */
	private String name;	

	/**
	 * 导入模版类型：0、组织架构导入 1、离职员工导入 2、新员工导入 3、员工部门导入 4、员工充值 5、SKU_Unit导入 6、发货信息导入 7、签收信息导入
	 */
	private Integer type;	

	/**
	 * 模版地址
	 */
	private String url;	

	/**
	 * 备注
	 */
	private String content;	

	/**
	 * 平台id
	 */
	private Long platformId;	

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
	 * 导入模版名称
	 * @return 导入模版名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 导入模版名称
	 * @param name 导入模版名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 导入模版类型：0、组织架构导入 1、离职员工导入 2、新员工导入 3、员工部门导入 4、员工充值 5、SKU_Unit导入 6、发货信息导入 7、签收信息导入
	 * @return 导入模版类型：0、组织架构导入 1、离职员工导入 2、新员工导入 3、员工部门导入 4、员工充值 5、SKU_Unit导入 6、发货信息导入 7、签收信息导入
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 导入模版类型：0、组织架构导入 1、离职员工导入 2、新员工导入 3、员工部门导入 4、员工充值 5、SKU_Unit导入 6、发货信息导入 7、签收信息导入
	 * @param type 导入模版类型：0、组织架构导入 1、离职员工导入 2、新员工导入 3、员工部门导入 4、员工充值 5、SKU_Unit导入 6、发货信息导入 7、签收信息导入
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 模版地址
	 * @return 模版地址
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 模版地址
	 * @param url 模版地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 备注
	 * @return 备注
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 备注
	 * @param content 备注
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 平台id
	 * @return 平台id
	 */
	public Long getPlatformId() {
		return platformId;
	}

	/**
	 * 平台id
	 * @param platformId 平台id
	 */
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
}
	