package com.egeo.components.user.po;



/**
 * 
 * @author min
 * @date 2018-08-14 10:05:59
 */
public class InfoTemplateParameterPO {


	private Long id;

	/**
	 * 消息模版id
	 */
	private Long infoTemplateId;	

	/**
	 * 参数名称
	 */
	private String name;	

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
	 * 消息模版id
	 * @return 消息模版id
	 */
	public Long getInfoTemplateId() {
		return infoTemplateId;
	}

	/**
	 * 消息模版id
	 * @param infoTemplateId 消息模版id
	 */
	public void setInfoTemplateId(Long infoTemplateId) {
		this.infoTemplateId = infoTemplateId;
	}

	/**
	 * 参数名称
	 * @return 参数名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 参数名称
	 * @param name 参数名称
	 */
	public void setName(String name) {
		this.name = name;
	}
}
	