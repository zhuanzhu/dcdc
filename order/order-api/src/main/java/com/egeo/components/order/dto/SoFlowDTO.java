package com.egeo.components.order.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author jzh
 * @date 2018-03-12 18:11:12
 */
public class SoFlowDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 母订单id
	 */
	private Long soId;	

	/**
	 * 操作 0:拆单、1:分拣、2:开票、3:发货、4:签收导入
	 */
	private Integer operate;	

	/**
	 * 操作人id
	 */
	private Long operatorId;	

	/**
	 * 创建时间
	 */
	private Date creatTime;	

	/**
	 * 更新时间
	 */
	private Date updateTime;	

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
	 * 操作人id
	 * @return 操作人id
	 */
	public Long getOperatorId() {
		return operatorId;
	}

	/**
	 * 操作人id
	 * @param operatorId 操作人id
	 */
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}
	/**
	 * 创建时间
	 * @return 创建时间
	 */
	public Date getCreatTime() {
		return creatTime;
	}

	/**
	 * 创建时间
	 * @param creatTime 创建时间
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
}
	