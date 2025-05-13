package com.egeo.components.order.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author ghw
 * @date 2018-02-06 18:47:42
 */
public class SoChildFlowDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 子订单的id
	 */
	private Long soChildId;	

	/**
	 * 操作 0:拆单、1:分拣、2:开票、3:发货、4:签收导入
	 */
	private Integer operate;	

	/**
	 * 操作人邮箱
	 */
	private String doMail;	
	
	private Long operatorId;

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date creatTime;	

	/**
	 * 更新时间
	 */
	private Date updateTime;	

	/**
	 * 母订单id
	 */
	private Long soId;	

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public Long getId() {
		return id;
	}

	/**
	 * 唯一id
	 * @param id 唯一id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 子订单的id
	 * @return 子订单的id
	 */
	public Long getSoChildId() {
		return soChildId;
	}

	/**
	 * 子订单的id
	 * @param soChildId 子订单的id
	 */
	public void setSoChildId(Long soChildId) {
		this.soChildId = soChildId;
	}
	/**
	 * 操作 0:拆单、1:分拣、2:开票、3:发货、4:签收导入
	 * @return 操作 0:拆单、1:分拣、2:开票、3:发货、4:签收导入
	 */
	public Integer getOperate() {
		return operate;
	}

	/**
	 * 操作 0:拆单、1:分拣、2:开票、3:发货、4:签收导入
	 * @param operate 操作 0:拆单、1:分拣、2:开票、3:发货、4:签收导入
	 */
	public void setOperate(Integer operate) {
		this.operate = operate;
	}
	/**
	 * 操作人邮箱
	 * @return 操作人邮箱
	 */
	public String getDoMail() {
		return doMail;
	}

	/**
	 * 操作人邮箱
	 * @param doMail 操作人邮箱
	 */
	public void setDoMail(String doMail) {
		this.doMail = doMail;
	}
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @return 创建时间:创建记录时数据库会自动set值
	 */
	public Date getCreatTime() {
		return creatTime;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @param creatTime 创建时间:创建记录时数据库会自动set值
	 */
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
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
	 * 母订单id
	 * @return 母订单id
	 */
	public Long getSoId() {
		return soId;
	}

	/**
	 * 母订单id
	 * @param soId 母订单id
	 */
	public void setSoId(Long soId) {
		this.soId = soId;
	}
}
	