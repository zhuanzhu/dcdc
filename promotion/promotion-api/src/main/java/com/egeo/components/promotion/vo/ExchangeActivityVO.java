package com.egeo.components.promotion.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author feng
 * @date 2018-12-14 10:57:17
 */
public class ExchangeActivityVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Long> oldBatchIdList;
	private List<ExchangeBatchVO> newBatchList;

	private Long id;
	/**
	 * 活动排序
	 */
	private Long sort;
	/**
	 * 活动名
	 */
	private String exchangeName;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 活动截止日期
	 */
	private Date endTime;
	/**
	 * 活动状态
	 */
	private Integer status;

	/**
	 * 允许兑换的Unit状态
	 */
	private List<Integer> unitStatus;

	/**
	 * 已兑换次数
	 * @return
	 */
	private Integer num ;

	/**
	 * ID排序icon  0:默认id自增长排序 1:根据活动ID从大到小排序
	 *
	 */
	private Integer idIcon;

	/**
	 * 活动排序icon  0:默认排序 1:根据活动排序值从小到大进行排序
	 * @return
	 */
	private Integer sortIcon;

	private String createTimeStr;

	private String updateTimeStr;

	private String endTimeStr;

	/**
	 * 平台id
	 */
	private Long platformId;

	/**
	 * 平台id
	 * @return
	 */
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
	 * 活动id
	 * @param id 活动id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 活动排序
	 * @return 活动排序
	 */
	public Long getSort() {
		return sort;
	}

	/**
	 * 活动排序
	 * @param sort 活动排序
	 */
	public void setSort(Long sort) {
		this.sort = sort;
	}	
	/**
	 * 活动名
	 * @return 活动名
	 */
	public String getExchangeName() {
		return exchangeName;
	}

	/**
	 * 活动名
	 * @param exchangeName 活动名
	 */
	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
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
	/**
	 * 活动截止日期
	 * @return 活动截止日期
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * 活动截止日期
	 * @param endTime 活动截止日期
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}	
	/**
	 * 活动状态
	 * @return 活动状态
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 活动状态
	 * @param status 活动状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 允许兑换的Unit状态
	 */
	public List<Integer>  getUnitStatus() {
		return unitStatus;
	}

	public void setUnitStatus(List<Integer> unitStatus)  {
		this.unitStatus = unitStatus;
	}

	/**
	 * 已兑换次数
	 * @return
	 */
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	/**
	 * ID排序icon  0:默认id自增长排序 1:根据活动ID从大到小排序
	 *
	 */
	public Integer getIdIcon() {
		return idIcon;
	}

	public void setIdIcon(Integer idIcon) {
		this.idIcon = idIcon;
	}

	/**
	 * 活动排序icon  0:默认排序 1:根据活动排序值从小到大进行排序
	 * @return
	 */
	public Integer getSortIcon() {
		return sortIcon;
	}

	public void setSortIcon(Integer sortIcon) {
		this.sortIcon = sortIcon;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public List<Long> getOldBatchIdList() {
		return oldBatchIdList;
	}

	public void setOldBatchIdList(List<Long> oldBatchIdList) {
		this.oldBatchIdList = oldBatchIdList;
	}


	public List<ExchangeBatchVO> getNewBatchList() {
		return newBatchList;
	}

	public void setNewBatchList(List<ExchangeBatchVO> newBatchList) {
		this.newBatchList = newBatchList;
	}

}
	