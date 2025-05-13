package com.egeo.components.finance.po;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author jiang
 * @date 2018-01-09 16:34:52
 */
public class UserAccountPO {


	private Long id;

	/**
	 * 账户名 默认存储用户邮箱
	 */
	private String name;	

	/**
	 * 账户类型 0积分账户 1:点赞福豆账户 2:积分冻结账户 4:现金支出账户
	 */
	private Integer type;	

	/**
	 * 余额 默认0.0
	 */
	private BigDecimal balance;	

	/**
	 * 有效性 0:有效 1:失效
	 */
	private Integer disabled;	

	/**
	 * 最后充值时间
	 */
	private Date lastRechargeTime;	

	/**
	 * 盐uuid
	 */
	private String uuid;	

	/**
	 * 余额密文
	 */
	private String ciphertext;	

	/**
	 * 用户id
	 */
	private Long userId;	

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
	 * 企业失效前金额
	 */
	private BigDecimal beforeDisableBalance;


	public BigDecimal getBeforeDisableBalance() {
		return beforeDisableBalance;
	}

	public void setBeforeDisableBalance(BigDecimal beforeDisableBalance) {
		this.beforeDisableBalance = beforeDisableBalance;
	}

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
	 * 账户名 默认存储用户邮箱
	 * @return 账户名 默认存储用户邮箱
	 */
	public String getName() {
		return name;
	}

	/**
	 * 账户名 默认存储用户邮箱
	 * @param name 账户名 默认存储用户邮箱
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 账户类型 0:积分账户 1:点赞福豆账户 2:积分冻结账户 4:现金支出账户
	 * @return 账户类型 0:积分账户 1:点赞福豆账户 2:积分冻结账户 4:现金支出账户
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 账户类型 0:积分账户 1:点赞福豆账户 2:积分冻结账户 4:现金支出账户
	 * @param type 账户类型 0:积分账户 1:点赞福豆账户 2:积分冻结账户 4:现金支出账户
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 余额 默认0.0
	 * @return 余额 默认0.0
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * 余额 默认0.0
	 * @param balance 余额 默认0.0
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/**
	 * 有效性 0:有效 1:失效
	 * @return 有效性 0:有效 1:失效
	 */
	public Integer getDisabled() {
		return disabled;
	}

	/**
	 * 有效性 0:有效 1:失效
	 * @param disabled 有效性 0:有效 1:失效
	 */
	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

	/**
	 * 最后充值时间
	 * @return 最后充值时间
	 */
	public Date getLastRechargeTime() {
		return lastRechargeTime;
	}

	/**
	 * 最后充值时间
	 * @param lastRechargeTime 最后充值时间
	 */
	public void setLastRechargeTime(Date lastRechargeTime) {
		this.lastRechargeTime = lastRechargeTime;
	}

	/**
	 * 盐uuid
	 * @return 盐uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * 盐uuid
	 * @param uuid 盐uuid
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * 余额密文
	 * @return 余额密文
	 */
	public String getCiphertext() {
		return ciphertext;
	}

	/**
	 * 余额密文
	 * @param ciphertext 余额密文
	 */
	public void setCiphertext(String ciphertext) {
		this.ciphertext = ciphertext;
	}

	/**
	 * 用户id
	 * @return 用户id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 用户id
	 * @param userId 用户id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
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
}
	