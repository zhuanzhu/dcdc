package com.egeo.components.promotion.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author wyy
 * @date 2018-06-14 18:02:51
 */
public class CouponGroupVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 分组名称
	 */
	private String groupName;
	/**
	 * 分组描述
	 */
	private String groupDescp;
	
	/**
	 * 最进更新人
	 */
	private Long updateUser;
	
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 平台id
	 */
	private Long platformId;
	
	/**
	 * 分组包含的优惠卷
	 */
	private List<Long> couponList;
	/**
	 * 门店id标识集合
	 */
	private List<Long> storeList;

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

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
	 * 分组名称
	 * @return 分组名称
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * 分组名称
	 * @param groupName 分组名称
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}	
	/**
	 * 分组描述
	 * @return 分组描述
	 */
	public String getGroupDescp() {
		return groupDescp;
	}

	/**
	 * 分组描述
	 * @param groupDescp 分组描述
	 */
	public void setGroupDescp(String groupDescp) {
		this.groupDescp = groupDescp;
	}	
	/**
	 * 
	 * @return 
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 
	 * @param createTime 
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
	/**
	 * 
	 * @return 
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 
	 * @param updateTime 
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<Long> getCouponList() {
		return couponList;
	}

	public void setCouponList(List<Long> couponList) {
		this.couponList = couponList;
	}

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	public List<Long> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<Long> storeList) {
		this.storeList = storeList;
	}
}
	