package com.egeo.components.user.po;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author xiaping
 * @date 2017-08-18 18:50:29
 */
public class MenuPO {


	private Long id;

	/**
	 * 父级编号
	 */
	private Long parentId;	

	/**
	 * 名称
	 */
	private String name;	

	/**
	 * 排序
	 */
	private BigDecimal sort;	

	/**
	 * 链接
	 */
	private String href;	

	/**
	 * 图标
	 */
	private String icon;	

	/**
	 * 是否在菜单中显示
	 */
	private String isShow;	

	/**
	 * 权限标识
	 */
	private String permission;	

	/**
	 * 备注信息
	 */
	private String remarks;	

	private String sysCode;	

	/**
	 * 
	 */
	private Long platformId;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

   private String parentName;

    private List<Long> pid;
	public Long getId() {
		return id;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	/**
	 * 编号
	 * @param id 编号
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
	 * 名称
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名称
	 * @param name 名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 排序
	 * @return 排序
	 */
	public BigDecimal getSort() {
		return sort;
	}

	/**
	 * 排序
	 * @param sort 排序
	 */
	public void setSort(BigDecimal sort) {
		this.sort = sort;
	}

	/**
	 * 链接
	 * @return 链接
	 */
	public String getHref() {
		return href;
	}

	/**
	 * 链接
	 * @param href 链接
	 */
	public void setHref(String href) {
		this.href = href;
	}

	/**
	 * 图标
	 * @return 图标
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * 图标
	 * @param icon 图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 是否在菜单中显示
	 * @return 是否在菜单中显示
	 */
	public String getIsShow() {
		return isShow;
	}

	/**
	 * 是否在菜单中显示
	 * @param isShow 是否在菜单中显示
	 */
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	/**
	 * 权限标识
	 * @return 权限标识
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * 权限标识
	 * @param permission 权限标识
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}

	/**
	 * 备注信息
	 * @return 备注信息
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 备注信息
	 * @param remarks 备注信息
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * 
	 * @return 
	 */
	public Long getPlatformId() {
		return platformId;
	}

	/**
	 * 
	 * @param platformId 
	 */
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
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


    public List<Long> getPid() {
        return pid;
    }

    public void setPid(List<Long> pid) {
        this.pid = pid;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
	