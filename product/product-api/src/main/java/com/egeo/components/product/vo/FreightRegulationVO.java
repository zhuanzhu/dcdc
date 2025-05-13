package com.egeo.components.product.vo;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-05-11 10:35:36
 */
public class FreightRegulationVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 运费模版id
	 */
	private Long freightTemplateId;
	/**
	 * 订单金额
	 */
	private Long orderMoney;
	/**
	 * 运费金额
	 */
	private Long freightMoney;

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
	 * 运费模版id
	 * @return 运费模版id
	 */
	public Long getFreightTemplateId() {
		return freightTemplateId;
	}

	/**
	 * 运费模版id
	 * @param freightTemplateId 运费模版id
	 */
	public void setFreightTemplateId(Long freightTemplateId) {
		this.freightTemplateId = freightTemplateId;
	}	
	/**
	 * 订单金额
	 * @return 订单金额
	 */
	public Long getOrderMoney() {
		return orderMoney;
	}

	/**
	 * 订单金额
	 * @param orderMoney 订单金额
	 */
	public void setOrderMoney(Long orderMoney) {
		this.orderMoney = orderMoney;
	}	
	/**
	 * 运费金额
	 * @return 运费金额
	 */
	public Long getFreightMoney() {
		return freightMoney;
	}

	/**
	 * 运费金额
	 * @param freightMoney 运费金额
	 */
	public void setFreightMoney(Long freightMoney) {
		this.freightMoney = freightMoney;
	}	
}
	