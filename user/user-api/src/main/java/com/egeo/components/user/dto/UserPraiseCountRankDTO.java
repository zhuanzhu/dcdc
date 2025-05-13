package com.egeo.components.user.dto;

import java.io.Serializable;

/**
 * 
 * @author GRACIA
 *
 */
public class UserPraiseCountRankDTO implements Serializable{

	private static final long serialVersionUID = -8937744690761283234L;
	
	private Long userId;
	private Integer praiseCount;
	private Integer rank;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getPraiseCount() {
		return praiseCount;
	}
	public void setPraiseCount(Integer praiseCount) {
		this.praiseCount = praiseCount;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	
}
