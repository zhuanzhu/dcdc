package com.egeo.components.order.vo;

import java.io.Serializable;

/**
 * 订单详情操作流水记录列表VO
 * 同时也是子订单操作流水记录列表vo
 * 同时也是订单操作流水导出excelVO
 * @author ghw
 * @date 2018-02-06 18:47:42
 */
public class SoOPFlowVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 操作 0:拆单、1:分拣、2:开票、3:发货、4:签收导入
	 */
	private Integer operate;
	/**
	 * 操作人名
	 */
	private String operatorName;
	/**
	 * 操作时间
	 */
	private String creatTime;
	
	/**
	 * 订单编号
	 */
	private String orderCode;

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

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}	
}
	