package com.egeo.components.promotion.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author wyy
 * @date 2018-06-19 14:19:37
 */
public class CouponBatchVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long channelId;

	public Long getChannelActivityId() {
		return channelActivityId;
	}

	public void setChannelActivityId(Long channelActivityId) {
		this.channelActivityId = channelActivityId;
	}

	private Long channelActivityId;

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}


	/**
	 * 页面跳转配置
	 */
	private String linkableButtonPageList;

	private Long id;
	/**
	 * 优惠卷批次编号
	 */
	private String couponBatchCode;
	/**
	 * 优惠卷批次的类型   0:优惠卷  1:优惠卷分组
	 */
	private Integer couponRelType;
	/**
	 * 优惠卷或优惠卷分组的id
	 */
	private Long couponRelId;
	/**
	 * 发放类型   0:系统发放   1:用户领取
	 */
	private Integer grantType;
	/**
	 * 优惠券领取方式 0：手工领取/扫码领取，1：注册领取,2:unit领取
	 */
	private Integer getType;
	/**
	 * 选择员工方式  0:企业   1:员工
	 */
	private Integer chooseWay;
	/**
	 * 选择公司id
	 */
	private Long companyId;
	/**
	 * 发放数量    -1:不限
	 */
	private Integer grantCount;
	/**
	 * 试用/过期后是否可再次领用   0:否 1:是
	 */
	private Integer isRepeat;
	/**
	 * 是否前端展示
	 */
	private Integer isDisplay;
	/**
	 * 可领取日期(开始)
	 */
	private Long receiveStartTime;
	/**
	 * 可领取日期(结束)
	 */
	private Long receiveEndTime;
	/**
	 * 有效期(开始)
	 */
	private Long effectStartTime;
	/**
	 * 有效期(结束)
	 */
	private Long effectEndTime;
	/**
	 * 有效周期  -1:未设置有效周期
	 */
	private Integer effectDays;
	/**
	 * 是否失效   0:否 1:是
	 */
	private Integer isEffect;
	/**
	 * 创建人
	 */
	private Long creator;
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
	 * 选择的员工
	 */
	private List<Long> empList;
	
	/**
	 * 选择的企业
	 */
	private List<Long> compList;
	
	/**
	 * 优惠卷标题或优惠卷分组名称
	 */
	private String title;

	/**
	 * 批次名称
	 * @return
	 */
	private String couponBatchName;
	
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
	 * 优惠卷批次编号
	 * @return 优惠卷批次编号
	 */
	public String getCouponBatchCode() {
		return couponBatchCode;
	}

	/**
	 * 优惠卷批次编号
	 * @param couponBatchCode 优惠卷批次编号
	 */
	public void setCouponBatchCode(String couponBatchCode) {
		this.couponBatchCode = couponBatchCode;
	}	
	/**
	 * 优惠卷批次的类型   0:优惠卷  1:优惠卷分组
	 * @return 优惠卷批次的类型   0:优惠卷  1:优惠卷分组
	 */
	public Integer getCouponRelType() {
		return couponRelType;
	}

	/**
	 * 优惠卷批次的类型   0:优惠卷  1:优惠卷分组
	 * @param couponRelType 优惠卷批次的类型   0:优惠卷  1:优惠卷分组
	 */
	public void setCouponRelType(Integer couponRelType) {
		this.couponRelType = couponRelType;
	}	
	/**
	 * 优惠卷或优惠卷分组的id
	 * @return 优惠卷或优惠卷分组的id
	 */
	public Long getCouponRelId() {
		return couponRelId;
	}

	/**
	 * 优惠卷或优惠卷分组的id
	 * @param couponRelId 优惠卷或优惠卷分组的id
	 */
	public void setCouponRelId(Long couponRelId) {
		this.couponRelId = couponRelId;
	}	
	/**
	 * 发放类型   0:系统发放   1:用户领取
	 * @return 发放类型   0:系统发放   1:用户领取
	 */
	public Integer getGrantType() {
		return grantType;
	}

	/**
	 * 发放类型   0:系统发放   1:用户领取
	 * @param grantType 发放类型   0:系统发放   1:用户领取
	 */
	public void setGrantType(Integer grantType) {
		this.grantType = grantType;
	}	
	/**
	 * 选择员工方式  0:企业   1:员工
	 * @return 选择员工方式  0:企业   1:员工
	 */
	public Integer getChooseWay() {
		return chooseWay;
	}

	/**
	 * 选择员工方式  0:企业   1:员工
	 * @param chooseWay 选择员工方式  0:企业   1:员工
	 */
	public void setChooseWay(Integer chooseWay) {
		this.chooseWay = chooseWay;
	}	
	/**
	 * 选择公司id
	 * @return 选择公司id
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * 选择公司id
	 * @param companyId 选择公司id
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}	
	/**
	 * 发放数量    -1:不限
	 * @return 发放数量    -1:不限
	 */
	public Integer getGrantCount() {
		return grantCount;
	}

	/**
	 * 发放数量    -1:不限
	 * @param grantCount 发放数量    -1:不限
	 */
	public void setGrantCount(Integer grantCount) {
		this.grantCount = grantCount;
	}	
	/**
	 * 试用/过期后是否可再次领用   0:否 1:是
	 * @return 试用/过期后是否可再次领用   0:否 1:是
	 */
	public Integer getIsRepeat() {
		return isRepeat;
	}

	/**
	 * 试用/过期后是否可再次领用   0:否 1:是
	 * @param isRepeat 试用/过期后是否可再次领用   0:否 1:是
	 */
	public void setIsRepeat(Integer isRepeat) {
		this.isRepeat = isRepeat;
	}	
	/**
	 * 是否前端展示
	 * @return 是否前端展示
	 */
	public Integer getIsDisplay() {
		return isDisplay;
	}

	/**
	 * 是否前端展示
	 * @param isDisplay 是否前端展示
	 */
	public void setIsDisplay(Integer isDisplay) {
		this.isDisplay = isDisplay;
	}	
	/**
	 * 可领取日期(开始)
	 * @return 可领取日期(开始)
	 */
	public Long getReceiveStartTime() {
		return receiveStartTime;
	}

	/**
	 * 可领取日期(开始)
	 * @param receiveStartTime 可领取日期(开始)
	 */
	public void setReceiveStartTime(Long receiveStartTime) {
		this.receiveStartTime = receiveStartTime;
	}	
	/**
	 * 可领取日期(结束)
	 * @return 可领取日期(结束)
	 */
	public Long getReceiveEndTime() {
		return receiveEndTime;
	}

	/**
	 * 可领取日期(结束)
	 * @param receiveEndTime 可领取日期(结束)
	 */
	public void setReceiveEndTime(Long receiveEndTime) {
		this.receiveEndTime = receiveEndTime;
	}	
	/**
	 * 有效期(开始)
	 * @return 有效期(开始)
	 */
	public Long getEffectStartTime() {
		return effectStartTime;
	}

	/**
	 * 有效期(开始)
	 * @param effectStartTime 有效期(开始)
	 */
	public void setEffectStartTime(Long effectStartTime) {
		this.effectStartTime = effectStartTime;
	}	
	/**
	 * 有效期(结束)
	 * @return 有效期(结束)
	 */
	public Long getEffectEndTime() {
		return effectEndTime;
	}

	/**
	 * 有效期(结束)
	 * @param effectEndTime 有效期(结束)
	 */
	public void setEffectEndTime(Long effectEndTime) {
		this.effectEndTime = effectEndTime;
	}	
	/**
	 * 有效周期  -1:未设置有效周期
	 * @return 有效周期  -1:未设置有效周期
	 */
	public Integer getEffectDays() {
		return effectDays;
	}

	/**
	 * 有效周期  -1:未设置有效周期
	 * @param effectDays 有效周期  -1:未设置有效周期
	 */
	public void setEffectDays(Integer effectDays) {
		this.effectDays = effectDays;
	}	
	/**
	 * 是否失效   0:否 1:是
	 * @return 是否失效   0:否 1:是
	 */
	public Integer getIsEffect() {
		return isEffect;
	}

	/**
	 * 是否失效   0:否 1:是
	 * @param isEffect 是否失效   0:否 1:是
	 */
	public void setIsEffect(Integer isEffect) {
		this.isEffect = isEffect;
	}	
	/**
	 * 创建人
	 * @return 创建人
	 */
	public Long getCreator() {
		return creator;
	}

	/**
	 * 创建人
	 * @param creator 创建人
	 */
	public void setCreator(Long creator) {
		this.creator = creator;
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

	public List<Long> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Long> empList) {
		this.empList = empList;
	}

	public List<Long> getCompList() {
		return compList;
	}

	public void setCompList(List<Long> compList) {
		this.compList = compList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getGetType() {
		return getType;
	}

	public void setGetType(Integer getType) {
		this.getType = getType;
	}

	public String getLinkableButtonPageList() {
		return linkableButtonPageList;
	}

	public void setLinkableButtonPageList(String linkableButtonPageList) {
		this.linkableButtonPageList = linkableButtonPageList;
	}

	public String getCouponBatchName() {
		return couponBatchName;
	}

	public void setCouponBatchName(String couponBatchName) {
		this.couponBatchName = couponBatchName;
	}
	
}
	