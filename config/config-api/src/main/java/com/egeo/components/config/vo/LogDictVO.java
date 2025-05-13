package com.egeo.components.config.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author wyy
 * @date 2018-05-24 09:34:12
 */
public class LogDictVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 父级编号
	 */
	private Long parentId;
	/**
	 * 字典名称
	 */
	private String name;
	/**
	 * 短码
	 */
	private String shortCode;
	/**
	 * 操作类型 0:增加  1: 修改  2:删除
	 */
	private Integer operatortype;
	/**
	 * 类型：1.功能模块、2.页面名称、3.具体操作 4.messageID
	 */
	private Integer arealevel;
	/**
	 * 操作短码id
	 */
	private Long operCodeId;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;
	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;
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
	 * 父级编号
	 * @return 父级编号
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 父级编号
	 * @param parentId 父级编号
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}	
	/**
	 * 字典名称
	 * @return 字典名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 字典名称
	 * @param name 字典名称
	 */
	public void setName(String name) {
		this.name = name;
	}	
	/**
	 * 短码
	 * @return 短码
	 */
	public String getShortCode() {
		return shortCode;
	}

	/**
	 * 短码
	 * @param shortCode 短码
	 */
	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}	
	/**
	 * 操作类型 0:增加  1: 修改  2:删除
	 * @return 操作类型 0:增加  1: 修改  2:删除
	 */
	public Integer getOperatortype() {
		return operatortype;
	}

	/**
	 * 操作类型 0:增加  1: 修改  2:删除
	 * @param operatortype 操作类型 0:增加  1: 修改  2:删除
	 */
	public void setOperatortype(Integer operatortype) {
		this.operatortype = operatortype;
	}	
	/**
	 * 类型：1.功能模块、2.页面名称、3.具体操作 4.messageID
	 * @return 类型：1.功能模块、2.页面名称、3.具体操作 4.messageID
	 */
	public Integer getArealevel() {
		return arealevel;
	}

	/**
	 * 类型：1.功能模块、2.页面名称、3.具体操作 4.messageID
	 * @param arealevel 类型：1.功能模块、2.页面名称、3.具体操作 4.messageID
	 */
	public void setArealevel(Integer arealevel) {
		this.arealevel = arealevel;
	}	
	/**
	 * 操作短码id
	 * @return 操作短码id
	 */
	public Long getOperCodeId() {
		return operCodeId;
	}

	/**
	 * 操作短码id
	 * @param operCodeId 操作短码id
	 */
	public void setOperCodeId(Long operCodeId) {
		this.operCodeId = operCodeId;
	}	
	/**
	 * 描述
	 * @return 描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 描述
	 * @param description 描述
	 */
	public void setDescription(String description) {
		this.description = description;
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
	/**
	 * 修改时间:更新时数据库会自动set值
	 * @return 修改时间:更新时数据库会自动set值
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间:更新时数据库会自动set值
	 * @param updateTime 修改时间:更新时数据库会自动set值
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
	