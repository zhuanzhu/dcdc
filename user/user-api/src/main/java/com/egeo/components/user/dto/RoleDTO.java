package com.egeo.components.user.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.egeo.utils.DateUtils;

/**
 * 
 * @author xiaping
 * @date 2017-08-18 18:50:29
 */
public class RoleDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 角色名称
	 */
	private String name;	

	/**
	 * 英文名称
	 */
	private String enname;	

	/**
	 * 角色类型
	 */
	private String roleType;	

	/**
	 * 是否系统数据
	 */
	private String isSys;	
	private String sysCode;

	/**
	 * 是否可用
	 */
	private Integer useable;	

	/**
	 * 备注信息
	 */
	private String remarks;	

	/**
	 * 
	 */
	private Long platformId;	

	/**
    //是否选中
    private boolean checked = false;

    /**
     * 菜单集合
	 */
    private String menus;
    
    /**
     * url集合
     */
    private String urls;	
	 /* 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	private Date maxcreateTime;	
	private Date mincreateTime;		
	private String functions;
	
	private List<Long> urlIdList;
	
	private List<Long> menuIdList;
	
	private List<Long> functionIdList;

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	
	private String updateTimeStr;	
	private String createTimeStr;
	private Long updateUserId;
	
	public Date getMaxcreateTime() {
		return maxcreateTime;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public void setMaxcreateTime(Date maxcreateTime) {
		this.maxcreateTime = maxcreateTime;
	}

	public Date getMincreateTime() {
		return mincreateTime;
	}

	public void setMincreateTime(Date mincreateTime) {
		this.mincreateTime = mincreateTime;
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

	private String updateUserName;

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}	

	private boolean checked;
	public Long getId() {
		return id;
	}

	/**
	 * 编号
	 * @param id 编号
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 角色名称
	 * @return 角色名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 角色名称
	 * @param name 角色名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 英文名称
	 * @return 英文名称
	 */
	public String getEnname() {
		return enname;
	}

	/**
	 * 英文名称
	 * @param enname 英文名称
	 */
	public void setEnname(String enname) {
		this.enname = enname;
	}
	/**
	 * 角色类型
	 * @return 角色类型
	 */
	public String getRoleType() {
		return roleType;
	}

	/**
	 * 角色类型
	 * @param roleType 角色类型
	 */
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	/**
	 * 是否系统数据
	 * @return 是否系统数据
	 */
	public String getIsSys() {
		return isSys;
	}

	/**
	 * 是否系统数据
	 * @param isSys 是否系统数据
	 */
	public void setIsSys(String isSys) {
		this.isSys = isSys;
	}
	/**
	 * 是否可用
	 * @return 是否可用
	 */
	public Integer getUseable() {
		return useable;
	}

	/**
	 * 是否可用
	 * @param useable 是否可用
	 */
	public void setUseable(Integer useable) {
		this.useable = useable;
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

  public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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
		
		
		
		this.createTime = createTime;
		if(createTime!=null) {
			setCreateTimeStr(DateUtils.formatDateTime(createTime));
		}else {
			setCreateTimeStr("");
		}
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
		if(updateTime!=null) {
			setUpdateTimeStr(DateUtils.formatDateTime(updateTime));
		}else {
			setUpdateTimeStr("");
		}
	}

    public String getMenus() {
        return menus;
    }

    public void setMenus(String menus) {
        this.menus = menus;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

	public String getFunctions() {
		return functions;
	}

	public void setFunctions(String functions) {
		this.functions = functions;
	}

	public List<Long> getUrlIdList() {
		return urlIdList;
	}

	public void setUrlIdList(List<Long> urlIdList) {
		this.urlIdList = urlIdList;
	}

	public List<Long> getMenuIdList() {
		return menuIdList;
	}

	public void setMenuIdList(List<Long> menuIdList) {
		this.menuIdList = menuIdList;
	}

	public List<Long> getFunctionIdList() {
		return functionIdList;
	}

	public void setFunctionIdList(List<Long> functionIdList) {
		this.functionIdList = functionIdList;
	}
    
 }
	