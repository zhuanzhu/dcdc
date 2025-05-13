package com.egeo.components.user.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-08-14 09:46:37
 */
public class SendWayTypeVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 发送方式名称
	 */
	private String name;
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;

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
	 * 发送方式名称
	 * @return 发送方式名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 发送方式名称
	 * @param name 发送方式名称
	 */
	public void setName(String name) {
		this.name = name;
	}	
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @return 创建时间:创建记录时数据库会自动set值
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @param createTime 创建时间:创建记录时数据库会自动set值
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
}
	