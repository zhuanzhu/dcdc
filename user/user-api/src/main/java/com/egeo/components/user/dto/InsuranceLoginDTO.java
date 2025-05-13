package com.egeo.components.user.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author jzh
 * @date 2018-05-11 14:46:11
 */
public class InsuranceLoginDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 用户id
	 */
	private Long userId;	

	/**
	 * 登录名
	 */
	private String loginName;	

	/**
	 * 密码(明文)
	 */
	private String password;	

	/**
	 * 登陆次数
	 */
	private Integer loginTimes;	

	/**
	 * 最后一次登陆时间
	 */
	private Date lastLogin;	

	/**
	 * 保交汇用户字段
	 */
	private String cardno;	

	/**
	 * 保交汇用户字段
	 */
	private String nickname;	

	/**
	 * 保交汇用户字段
	 */
	private String shopname;	

	/**
	 * 保交汇用户字段
	 */
	private String headerurl;	

	/**
	 * 保交汇用户字段
	 */
	private String dccuid;	

	/**
	 * 保交汇用户字段
	 */
	private String memberid;	

	/**
	 * 保交汇用户字段
	 */
	private String openid;	

	/**
	 * 保交汇用户字段
	 */
	private String userno;	

	/**
	 * 保交汇用户字段
	 */
	private Long mid;	

	/**
	 * 保交汇用户字段
	 */
	private String vsId;	

	/**
	 * 保交汇用户字段
	 */
	private String membercode;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	/**
	 * 保交汇用户字段
	 */
	private String dccucode;

	public String getDccucode() {
		return dccucode;
	}

	public void setDccucode(String dccucode) {
		this.dccucode = dccucode;
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
	 * 登录名
	 * @return 登录名
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * 登录名
	 * @param loginName 登录名
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * 密码(明文)
	 * @return 密码(明文)
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 密码(明文)
	 * @param password 密码(明文)
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 登陆次数
	 * @return 登陆次数
	 */
	public Integer getLoginTimes() {
		return loginTimes;
	}

	/**
	 * 登陆次数
	 * @param loginTimes 登陆次数
	 */
	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}
	/**
	 * 最后一次登陆时间
	 * @return 最后一次登陆时间
	 */
	public Date getLastLogin() {
		return lastLogin;
	}

	/**
	 * 最后一次登陆时间
	 * @param lastLogin 最后一次登陆时间
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	/**
	 * 保交汇用户字段
	 * @return 保交汇用户字段
	 */
	public String getCardno() {
		return cardno;
	}

	/**
	 * 保交汇用户字段
	 * @param cardno 保交汇用户字段
	 */
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	/**
	 * 保交汇用户字段
	 * @return 保交汇用户字段
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 保交汇用户字段
	 * @param nickname 保交汇用户字段
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 保交汇用户字段
	 * @return 保交汇用户字段
	 */
	public String getShopname() {
		return shopname;
	}

	/**
	 * 保交汇用户字段
	 * @param shopname 保交汇用户字段
	 */
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	/**
	 * 保交汇用户字段
	 * @return 保交汇用户字段
	 */
	public String getHeaderurl() {
		return headerurl;
	}

	/**
	 * 保交汇用户字段
	 * @param headerurl 保交汇用户字段
	 */
	public void setHeaderurl(String headerurl) {
		this.headerurl = headerurl;
	}
	/**
	 * 保交汇用户字段
	 * @return 保交汇用户字段
	 */
	public String getDccuid() {
		return dccuid;
	}

	/**
	 * 保交汇用户字段
	 * @param dccuid 保交汇用户字段
	 */
	public void setDccuid(String dccuid) {
		this.dccuid = dccuid;
	}
	/**
	 * 保交汇用户字段
	 * @return 保交汇用户字段
	 */
	public String getMemberid() {
		return memberid;
	}

	/**
	 * 保交汇用户字段
	 * @param memberid 保交汇用户字段
	 */
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	/**
	 * 保交汇用户字段
	 * @return 保交汇用户字段
	 */
	public String getOpenid() {
		return openid;
	}

	/**
	 * 保交汇用户字段
	 * @param openid 保交汇用户字段
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * 保交汇用户字段
	 * @return 保交汇用户字段
	 */
	public String getUserno() {
		return userno;
	}

	/**
	 * 保交汇用户字段
	 * @param userno 保交汇用户字段
	 */
	public void setUserno(String userno) {
		this.userno = userno;
	}
	/**
	 * 保交汇用户字段
	 * @return 保交汇用户字段
	 */
	public Long getMid() {
		return mid;
	}

	/**
	 * 保交汇用户字段
	 * @param mid 保交汇用户字段
	 */
	public void setMid(Long mid) {
		this.mid = mid;
	}
	/**
	 * 保交汇用户字段
	 * @return 保交汇用户字段
	 */
	public String getVsId() {
		return vsId;
	}

	/**
	 * 保交汇用户字段
	 * @param vsId 保交汇用户字段
	 */
	public void setVsId(String vsId) {
		this.vsId = vsId;
	}
	/**
	 * 保交汇用户字段
	 * @return 保交汇用户字段
	 */
	public String getMembercode() {
		return membercode;
	}

	/**
	 * 保交汇用户字段
	 * @param membercode 保交汇用户字段
	 */
	public void setMembercode(String membercode) {
		this.membercode = membercode;
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
}
	