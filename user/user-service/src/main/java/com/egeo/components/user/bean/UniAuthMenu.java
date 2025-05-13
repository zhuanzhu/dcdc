package com.egeo.components.user.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: Menu 
 * @Description: 页面菜单
 * @author: jane
 * @date: 2017年11月16日 下午1:19:19
 */
public class UniAuthMenu implements Serializable {
	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	public static final int MENU_TYPE_SYS = 1;
	public static final int MENU_TYPE_MENU = 2;
	public static final int MENU_TYPE_ACTION = 3;
	public static final int MENU_TYPE_INTERFACE = 4;

	public static final int MENU_STATE_ENABLE = 1;
	public static final int MENU_STATE_DISABLE = 0;
	public static final int MENU_STATE_DELETE = -1;

	/**
	 *	菜单ID
	 */
	private java.lang.Integer mid;

	/**
	 *	菜单编号
	 */
	private java.lang.String menuCode;

	/**
	 *	菜单名称
	 */
	private java.lang.String menuName;

	/**
	 *	菜单上级编号
	 */
	private java.lang.String menuParentCode;

	/**
	 *	菜单所有上级编号
	 */
	private java.lang.String menuParentCodes;

	/**
	 *	菜单图片
	 */
	private java.lang.String menuImg;

	/**
	 *
	 */
	private java.lang.String menuUrl;

	/**
	 *
	 */
	private java.lang.Integer id;

	/**
	 *	菜单状态 1-启用; 0-停用; -1-删除;
	 */
	private java.lang.Integer menuState;

	/**
	 *	菜单类型 1-系统; 2-菜单; 3-动作; 4-接口;
	 */
	private java.lang.Integer menuType;

	/**
	 *	菜单显示序号
	 */
	private java.lang.Integer menuIndex;

	/**
	 *	菜单参数
	 */
	private java.lang.String menuParams;

	/**
	 *	创建时间
	 */
	private java.lang.String createTime;

	/**
	 *	修改时间
	 */
	private java.lang.String updateTime;

	/**
	 *	创建人
	 */
	private java.lang.String createBy;

	/**
	 *	修改人
	 */
	private java.lang.String updateBy;

	/**
	 *	备注
	 */
	private java.lang.String remark;
	
	
	private List<UniAuthMenu> children;

	private Boolean selected;

	private String domain;

	/**
	 *	菜单ID
	 */
	public java.lang.Integer getMid()
	{
		return mid;
	}

	/**
	 *	菜单ID
	 */
	public void setMid(java.lang.Integer mid)
	{
		this.mid = mid;
	}

	/**
	 *	菜单编号
	 */
	public java.lang.String getMenuCode()
	{
		return menuCode;
	}

	/**
	 *	菜单编号
	 */
	public void setMenuCode(java.lang.String menuCode)
	{
		this.menuCode = menuCode;
	}

	/**
	 *	菜单名称
	 */
	public java.lang.String getMenuName()
	{
		return menuName;
	}

	/**
	 *	菜单名称
	 */
	public void setMenuName(java.lang.String menuName)
	{
		this.menuName = menuName;
	}

	/**
	 *	菜单上级编号
	 */
	public java.lang.String getMenuParentCode()
	{
		return menuParentCode;
	}

	/**
	 *	菜单上级编号
	 */
	public void setMenuParentCode(java.lang.String menuParentCode)
	{
		this.menuParentCode = menuParentCode;
	}

	/**
	 *	菜单所有上级编号
	 */
	public java.lang.String getMenuParentCodes()
	{
		return menuParentCodes;
	}

	/**
	 *	菜单所有上级编号
	 */
	public void setMenuParentCodes(java.lang.String menuParentCodes)
	{
		this.menuParentCodes = menuParentCodes;
	}

	/**
	 *	菜单图片
	 */
	public java.lang.String getMenuImg()
	{
		return menuImg;
	}

	/**
	 *	菜单图片
	 */
	public void setMenuImg(java.lang.String menuImg)
	{
		this.menuImg = menuImg;
	}

	/**
	 *
	 */
	public java.lang.String getMenuUrl()
	{
		return menuUrl;
	}

	/**
	 *
	 */
	public void setMenuUrl(java.lang.String menuUrl)
	{
		this.menuUrl = menuUrl;
	}

	/**
	 *
	 */
	public java.lang.Integer getId()
	{
		return id;
	}

	/**
	 *
	 */
	public void setId(java.lang.Integer id)
	{
		this.id = id;
	}


	/**
	 *	菜单状态 1-启用; 0-停用; -1-删除;
	 */
	public java.lang.Integer getMenuState()
	{
		return menuState;
	}

	/**
	 *	菜单状态 1-启用; 0-停用; -1-删除;
	 */
	public void setMenuState(java.lang.Integer menuState)
	{
		this.menuState = menuState;
	}

	/**
	 *	菜单类型 1-系统; 2-菜单; 3-动作; 4-接口;
	 */
	public java.lang.Integer getMenuType()
	{
		return menuType;
	}

	/**
	 *	菜单类型 1-系统; 2-菜单; 3-动作; 4-接口;
	 */
	public void setMenuType(java.lang.Integer menuType)
	{
		this.menuType = menuType;
	}

	/**
	 *	菜单显示序号
	 */
	public java.lang.Integer getMenuIndex()
	{
		return menuIndex;
	}

	/**
	 *	菜单显示序号
	 */
	public void setMenuIndex(java.lang.Integer menuIndex)
	{
		this.menuIndex = menuIndex;
	}

	/**
	 *	菜单参数
	 */
	public java.lang.String getMenuParams()
	{
		return menuParams;
	}

	/**
	 *	菜单参数
	 */
	public void setMenuParams(java.lang.String menuParams)
	{
		this.menuParams = menuParams;
	}

	/**
	 *	创建时间
	 */
	public java.lang.String getCreateTime()
	{
		return createTime;
	}

	/**
	 *	创建时间
	 */
	public void setCreateTime(java.lang.String createTime)
	{
		this.createTime = createTime;
	}

	/**
	 *	修改时间
	 */
	public java.lang.String getUpdateTime()
	{
		return updateTime;
	}

	/**
	 *	修改时间
	 */
	public void setUpdateTime(java.lang.String updateTime)
	{
		this.updateTime = updateTime;
	}

	/**
	 *	创建人
	 */
	public java.lang.String getCreateBy()
	{
		return createBy;
	}

	/**
	 *	创建人
	 */
	public void setCreateBy(java.lang.String createBy)
	{
		this.createBy = createBy;
	}

	/**
	 *	修改人
	 */
	public java.lang.String getUpdateBy()
	{
		return updateBy;
	}

	/**
	 *	修改人
	 */
	public void setUpdateBy(java.lang.String updateBy)
	{
		this.updateBy = updateBy;
	}

	/**
	 *	备注
	 */
	public java.lang.String getRemark()
	{
		return remark;
	}

	/**
	 *	备注
	 */
	public void setRemark(java.lang.String remark)
	{
		this.remark = remark;
	}

	public List<UniAuthMenu> getChildren() {
		return children;
	}

	public void setChildren(List<UniAuthMenu> children) {
		this.children = children;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Override
	public String toString() {
		return "UniAuthMenu{" +
				"mid=" + mid +
				", menuCode='" + menuCode + '\'' +
				", menuName='" + menuName + '\'' +
				", menuParentCode='" + menuParentCode + '\'' +
				", menuParentCodes='" + menuParentCodes + '\'' +
				", menuImg='" + menuImg + '\'' +
				", menuUrl='" + menuUrl + '\'' +
				", id=" + id +
				", menuState=" + menuState +
				", menuType=" + menuType +
				", menuIndex=" + menuIndex +
				", menuParams='" + menuParams + '\'' +
				", createTime='" + createTime + '\'' +
				", updateTime='" + updateTime + '\'' +
				", createBy='" + createBy + '\'' +
				", updateBy='" + updateBy + '\'' +
				", remark='" + remark + '\'' +
				", children=" + children +
				", selected=" + selected +
				", domain='" + domain + '\'' +
				'}';
	}
}
