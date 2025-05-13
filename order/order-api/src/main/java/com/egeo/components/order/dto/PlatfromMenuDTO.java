package com.egeo.components.order.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:52
 */
public class PlatfromMenuDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 菜单code
	 */
	private String menuCode;	

	/**
	 * 菜单名称
	 */
	private String menuName;	

	/**
	 * jason格式{"fieldShow":,"displayShow","exportShow":}(查询页，展示页，导出页)
	 */
	private String tabShow;	

	/**
	 * 平台id
	 */
	private Long platformId;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 菜单code
	 * @return 菜单code
	 */
	public String getMenuCode() {
		return menuCode;
	}

	/**
	 * 菜单code
	 * @param menuCode 菜单code
	 */
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	/**
	 * 菜单名称
	 * @return 菜单名称
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * 菜单名称
	 * @param menuName 菜单名称
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	/**
	 * jason格式{"fieldShow":,"displayShow","exportShow":}(查询页，展示页，导出页)
	 * @return jason格式{"fieldShow":,"displayShow","exportShow":}(查询页，展示页，导出页)
	 */
	public String getTabShow() {
		return tabShow;
	}

	/**
	 * jason格式{"fieldShow":,"displayShow","exportShow":}(查询页，展示页，导出页)
	 * @param tabShow jason格式{"fieldShow":,"displayShow","exportShow":}(查询页，展示页，导出页)
	 */
	public void setTabShow(String tabShow) {
		this.tabShow = tabShow;
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
	