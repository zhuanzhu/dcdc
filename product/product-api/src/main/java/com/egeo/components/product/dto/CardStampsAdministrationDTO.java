package com.egeo.components.product.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-01-26 09:06:16
 */
public class CardStampsAdministrationDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 电子卡券模版名称
	 */
	private String name;	

	/**
	 * 前端类目id
	 */
	private Long leadingEndCategoryId;	

	/**
	 * 电子卡券模版图片路径
	 */
	private String pictureUrl;	

	/**
	 * su商品id
	 */
	private Long standardUnitId;	

	/**
	 * 排序
	 */
	private Integer sortValue;	

	/**
	 * 是否显示:默认0否;1是
	 */
	private Integer isShow;	

	/**
	 * 创建人ID
	 */
	private Long createUserid;	

	/**
	 * 创建人姓名
	 */
	private String createUsername;	

	/**
	 * 创建人IP
	 */
	private String createUserip;	

	/**
	 * 创建人MAC地址
	 */
	private String createUsermac;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 最后修改人ID
	 */
	private Long updateUserid;	

	/**
	 * 最后修改人姓名
	 */
	private String updateUsername;	

	/**
	 * 最后修改人IP
	 */
	private String updateUserip;	

	/**
	 * 最后修改人MAC
	 */
	private String updateUsermac;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

	/**
	 * 客户端程序的版本号
	 */
	private String clientVersionno;	

	/**
	 * 平台id
	 */
	private Long platformId;	
	/**
	 * su商品名称
	 */
	private String standardUnitName;
	/**
	 * 前端类目名称
	 */
	private String leadingEndCategoryName;

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
	 * 电子卡券模版名称
	 * @return 电子卡券模版名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 电子卡券模版名称
	 * @param name 电子卡券模版名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 前端类目id
	 * @return 前端类目id
	 */
	public Long getLeadingEndCategoryId() {
		return leadingEndCategoryId;
	}

	/**
	 * 前端类目id
	 * @param leadingEndCategoryId 前端类目id
	 */
	public void setLeadingEndCategoryId(Long leadingEndCategoryId) {
		this.leadingEndCategoryId = leadingEndCategoryId;
	}
	/**
	 * 电子卡券模版图片路径
	 * @return 电子卡券模版图片路径
	 */
	public String getPictureUrl() {
		return pictureUrl;
	}

	/**
	 * 电子卡券模版图片路径
	 * @param pictureUrl 电子卡券模版图片路径
	 */
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	/**
	 * su商品id
	 * @return su商品id
	 */
	public Long getStandardUnitId() {
		return standardUnitId;
	}

	/**
	 * su商品id
	 * @param standardUnitId su商品id
	 */
	public void setStandardUnitId(Long standardUnitId) {
		this.standardUnitId = standardUnitId;
	}
	/**
	 * 排序
	 * @return 排序
	 */
	public Integer getSortValue() {
		return sortValue;
	}

	/**
	 * 排序
	 * @param sortValue 排序
	 */
	public void setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
	}
	/**
	 * 是否显示:默认0否;1是
	 * @return 是否显示:默认0否;1是
	 */
	public Integer getIsShow() {
		return isShow;
	}

	/**
	 * 是否显示:默认0否;1是
	 * @param isShow 是否显示:默认0否;1是
	 */
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	/**
	 * 创建人ID
	 * @return 创建人ID
	 */
	public Long getCreateUserid() {
		return createUserid;
	}

	/**
	 * 创建人ID
	 * @param createUserid 创建人ID
	 */
	public void setCreateUserid(Long createUserid) {
		this.createUserid = createUserid;
	}
	/**
	 * 创建人姓名
	 * @return 创建人姓名
	 */
	public String getCreateUsername() {
		return createUsername;
	}

	/**
	 * 创建人姓名
	 * @param createUsername 创建人姓名
	 */
	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}
	/**
	 * 创建人IP
	 * @return 创建人IP
	 */
	public String getCreateUserip() {
		return createUserip;
	}

	/**
	 * 创建人IP
	 * @param createUserip 创建人IP
	 */
	public void setCreateUserip(String createUserip) {
		this.createUserip = createUserip;
	}
	/**
	 * 创建人MAC地址
	 * @return 创建人MAC地址
	 */
	public String getCreateUsermac() {
		return createUsermac;
	}

	/**
	 * 创建人MAC地址
	 * @param createUsermac 创建人MAC地址
	 */
	public void setCreateUsermac(String createUsermac) {
		this.createUsermac = createUsermac;
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
	 * 最后修改人ID
	 * @return 最后修改人ID
	 */
	public Long getUpdateUserid() {
		return updateUserid;
	}

	/**
	 * 最后修改人ID
	 * @param updateUserid 最后修改人ID
	 */
	public void setUpdateUserid(Long updateUserid) {
		this.updateUserid = updateUserid;
	}
	/**
	 * 最后修改人姓名
	 * @return 最后修改人姓名
	 */
	public String getUpdateUsername() {
		return updateUsername;
	}

	/**
	 * 最后修改人姓名
	 * @param updateUsername 最后修改人姓名
	 */
	public void setUpdateUsername(String updateUsername) {
		this.updateUsername = updateUsername;
	}
	/**
	 * 最后修改人IP
	 * @return 最后修改人IP
	 */
	public String getUpdateUserip() {
		return updateUserip;
	}

	/**
	 * 最后修改人IP
	 * @param updateUserip 最后修改人IP
	 */
	public void setUpdateUserip(String updateUserip) {
		this.updateUserip = updateUserip;
	}
	/**
	 * 最后修改人MAC
	 * @return 最后修改人MAC
	 */
	public String getUpdateUsermac() {
		return updateUsermac;
	}

	/**
	 * 最后修改人MAC
	 * @param updateUsermac 最后修改人MAC
	 */
	public void setUpdateUsermac(String updateUsermac) {
		this.updateUsermac = updateUsermac;
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
	 * 客户端程序的版本号
	 * @return 客户端程序的版本号
	 */
	public String getClientVersionno() {
		return clientVersionno;
	}

	/**
	 * 客户端程序的版本号
	 * @param clientVersionno 客户端程序的版本号
	 */
	public void setClientVersionno(String clientVersionno) {
		this.clientVersionno = clientVersionno;
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

	public String getStandardUnitName() {
		return standardUnitName;
	}

	public void setStandardUnitName(String standardUnitName) {
		this.standardUnitName = standardUnitName;
	}

	public String getLeadingEndCategoryName() {
		return leadingEndCategoryName;
	}

	public void setLeadingEndCategoryName(String leadingEndCategoryName) {
		this.leadingEndCategoryName = leadingEndCategoryName;
	}
	
}
	