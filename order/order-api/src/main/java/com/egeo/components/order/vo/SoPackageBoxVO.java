package com.egeo.components.order.vo;

import java.io.Serializable;

/**
 * 
 * @author ghw
 * @date 2018-02-03 19:58:59
 */
public class SoPackageBoxVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 子订单id
	 */
	private Long soChildId;
	/**
	 * 箱号
	 */
	private Long soBoxCode;
	
	private Long soPackageId;

	public Long getSoPackageId() {
		return soPackageId;
	}

	public void setSoPackageId(Long soPackageId) {
		this.soPackageId = soPackageId;
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
	 * 子订单id
	 * @return 子订单id
	 */
	public Long getSoChildId() {
		return soChildId;
	}

	/**
	 * 子订单id
	 * @param soChildId 子订单id
	 */
	public void setSoChildId(Long soChildId) {
		this.soChildId = soChildId;
	}	
	/**
	 * 箱号
	 * @return 箱号
	 */
	public Long getSoBoxCode() {
		return soBoxCode;
	}

	/**
	 * 箱号
	 * @param soBoxCode 箱号
	 */
	public void setSoBoxCode(Long soBoxCode) {
		this.soBoxCode = soBoxCode;
	}	
}
	