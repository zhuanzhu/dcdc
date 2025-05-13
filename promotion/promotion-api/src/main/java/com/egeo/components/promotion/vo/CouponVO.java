package com.egeo.components.promotion.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author wyy
 * @date 2018-06-14 10:56:22
 */
public class CouponVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 优惠卷编号
	 */
	private String couponCode;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 优惠卷类型 0: 满减卷 1:兑换卷
	 */
	private Integer couponType;
	/**
	 * 优惠金额
	 */
	private Integer discountAmount;
	/**
	 * 触发金额
	 */
	private Integer triggerAmount;
	/**
	 * 关联商品类型  0: 单su 1:商品组
	 */
	private Integer goodsType;
	/**
	 * 单su或商品分组id
	 */
	private Long goodsId;
	/**
	 * 试用次数
	 */
	private Integer usageCount;
	/**
	 * 跳转类型  1:单个SU 2:SU组；3:内部链接
	 */
	private Integer jumpType;
	/**
	 * 自定义icon链接
	 */
	private String iconUrl;
	/**
	 * 详情
	 */
	private String detail;
	/**
	 * 最近修改人
	 */
	private Long updateUser;
	/**
	 * 是否删除   0:否 1:是
	 */
	private Integer isDelete;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 平台id
	 */
	private Long platformId;
	
	/**
	 * 所属公司
	 */
	private List<Long> companyList;
	/**
	 * 所属门店标识集合
	 */
	private List<Long> storeList;
	/**
	 * 门店名
	 */
	private String storeName;

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
	 * 优惠卷编号
	 * @return 优惠卷编号
	 */
	public String getCouponCode() {
		return couponCode;
	}

	/**
	 * 优惠卷编号
	 * @param couponCode 优惠卷编号
	 */
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}	
	/**
	 * 标题
	 * @return 标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 标题
	 * @param title 标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}	
	/**
	 * 优惠卷类型 0: 满减卷 1:兑换卷
	 * @return 优惠卷类型 0: 满减卷 1:兑换卷
	 */
	public Integer getCouponType() {
		return couponType;
	}

	/**
	 * 优惠卷类型 0: 满减卷 1:兑换卷
	 * @param couponType 优惠卷类型 0: 满减卷 1:兑换卷
	 */
	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}	
	/**
	 * 优惠金额
	 * @return 优惠金额
	 */
	public Integer getDiscountAmount() {
		return discountAmount;
	}

	/**
	 * 优惠金额
	 * @param discountAmount 优惠金额
	 */
	public void setDiscountAmount(Integer discountAmount) {
		this.discountAmount = discountAmount;
	}	
	/**
	 * 触发金额
	 * @return 触发金额
	 */
	public Integer getTriggerAmount() {
		return triggerAmount;
	}

	/**
	 * 触发金额
	 * @param triggerAmount 触发金额
	 */
	public void setTriggerAmount(Integer triggerAmount) {
		this.triggerAmount = triggerAmount;
	}	
	/**
	 * 关联商品类型  0: 单su 1:商品组
	 * @return 关联商品类型  0: 单su 1:商品组
	 */
	public Integer getGoodsType() {
		return goodsType;
	}

	/**
	 * 关联商品类型  0: 单su 1:商品组
	 * @param goodsType 关联商品类型  0: 单su 1:商品组
	 */
	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}	
	/**
	 * 单su或商品分组id
	 * @return 单su或商品分组id
	 */
	public Long getGoodsId() {
		return goodsId;
	}

	/**
	 * 单su或商品分组id
	 * @param goodsId 单su或商品分组id
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}	
	/**
	 * 试用次数
	 * @return 试用次数
	 */
	public Integer getUsageCount() {
		return usageCount;
	}

	/**
	 * 试用次数
	 * @param usageCount 试用次数
	 */
	public void setUsageCount(Integer usageCount) {
		this.usageCount = usageCount;
	}	
	/**
	 * 跳转类型
	 * @return 跳转类型 1:单个SU 2:SU组；3:内部链接
	 */
	public Integer getJumpType() {
		return jumpType;
	}

	/**
	 * 跳转类型
	 * @param jumpType 跳转类型 1:单个SU 2:SU组；3:内部链接
	 */
	public void setJumpType(Integer jumpType) {
		this.jumpType = jumpType;
	}	
	/**
	 * 自定义icon链接
	 * @return 自定义icon链接
	 */
	public String getIconUrl() {
		return iconUrl;
	}

	/**
	 * 自定义icon链接
	 * @param iconUrl 自定义icon链接
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}	
	/**
	 * 详情
	 * @return 详情
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * 详情
	 * @param detail 详情
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}	
	/**
	 * 最近修改人
	 * @return 最近修改人
	 */
	public Long getUpdateUser() {
		return updateUser;
	}

	/**
	 * 最近修改人
	 * @param updateUser 最近修改人
	 */
	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}	
	/**
	 * 是否删除   0:否 1:是
	 * @return 是否删除   0:否 1:是
	 */
	public Integer getIsDelete() {
		return isDelete;
	}

	/**
	 * 是否删除   0:否 1:是
	 * @param isDelete 是否删除   0:否 1:是
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}	
	/**
	 * 创建时间
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
	/**
	 * 更新时间
	 * @return 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 * @param updateTime 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<Long> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Long> companyList) {
		this.companyList = companyList;
	}

	public List<Long> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<Long> storeList) {
		this.storeList = storeList;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
}
	