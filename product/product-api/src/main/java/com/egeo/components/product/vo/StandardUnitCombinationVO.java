package com.egeo.components.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author min
 * @date 2018-04-06 16:43:25
 */
public class StandardUnitCombinationVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 组合名称
	 */
	private String combinationName;
	/**
	 * 组合类型：1、su商品组合 2、关联前台类目节点 3、关联标签 4、关联后台目录树节点
	 */
	private Integer type;
	/**
	 * 备注
	 */
	private String content;
	/**
	 * 用户id
	 */
	private Long createUserid;
	/**
	 * 用户名称
	 */
	private String createUsername;
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;
	/**
	 * 用户id
	 */
	private Long updateUserid;
	/**
	 * 用户名称
	 */
	private String updateUsername;
	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;
	/**
	 * 平台id
	 */
	private Long platformId;

	/**组合设置最小毛利**/
	private BigDecimal combinationMinProfit;

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
	 * 组合名称
	 * @return 组合名称
	 */
	public String getCombinationName() {
		return combinationName;
	}

	/**
	 * 组合名称
	 * @param combinationName 组合名称
	 */
	public void setCombinationName(String combinationName) {
		this.combinationName = combinationName;
	}
	/**
	 * 组合类型：1、su 2、前台类目节点 3、标签
	 * @return 组合类型：1、su 2、前台类目节点 3、标签
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 组合类型：1、su 2、前台类目节点 3、标签
	 * @param type 组合类型：1、su 2、前台类目节点 3、标签
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 备注
	 * @return 备注
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 备注
	 * @param content 备注
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 用户id
	 * @return 用户id
	 */
	public Long getCreateUserid() {
		return createUserid;
	}

	/**
	 * 用户id
	 * @param createUserid 用户id
	 */
	public void setCreateUserid(Long createUserid) {
		this.createUserid = createUserid;
	}
	/**
	 * 用户名称
	 * @return 用户名称
	 */
	public String getCreateUsername() {
		return createUsername;
	}

	/**
	 * 用户名称
	 * @param createUsername 用户名称
	 */
	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
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
	 * 用户id
	 * @return 用户id
	 */
	public Long getUpdateUserid() {
		return updateUserid;
	}

	/**
	 * 用户id
	 * @param updateUserid 用户id
	 */
	public void setUpdateUserid(Long updateUserid) {
		this.updateUserid = updateUserid;
	}
	/**
	 * 用户名称
	 * @return 用户名称
	 */
	public String getUpdateUsername() {
		return updateUsername;
	}

	/**
	 * 用户名称
	 * @param updateUsername 用户名称
	 */
	public void setUpdateUsername(String updateUsername) {
		this.updateUsername = updateUsername;
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

	public BigDecimal getCombinationMinProfit() {
		return combinationMinProfit;
	}

	public void setCombinationMinProfit(BigDecimal combinationMinProfit) {
		this.combinationMinProfit = combinationMinProfit;
	}
}
