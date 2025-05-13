package com.egeo.components.config.po;


import java.util.Date;

/**
 * 
 * @author wyy
 * @date 2018-05-24 09:34:53
 */
public class LogOperCodePO {


	private Long id;

	/**
	 * 操作短码
	 */
	private String operCode;	

	/**
	 * 
	 */
	private Date createTime;	

	/**
	 * 
	 */
	private Date updateTime;	

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
	 * 操作短码
	 * @return 操作短码
	 */
	public String getOperCode() {
		return operCode;
	}

	/**
	 * 操作短码
	 * @param operCode 操作短码
	 */
	public void setOperCode(String operCode) {
		this.operCode = operCode;
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
}
	