package com.egeo.components.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author xia
 * @date 2019-04-23 17:39:29
 */
public class ChannelPriceLimitUploadVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 京东sku价格门槛起始
	 */
	private BigDecimal jdPriceLimitStart;
	/**
	 * 京东sku价格门槛终止
	 */
	private BigDecimal jdPriceLimitEnd;

	private String channelCode;

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
	 * 京东sku价格门槛起始
	 * @return 京东sku价格门槛起始
	 */
	public BigDecimal getJdPriceLimitStart() {
		return jdPriceLimitStart;
	}

	/**
	 * 京东sku价格门槛起始
	 * @param jdPriceLimitStart 京东sku价格门槛起始
	 */
	public void setJdPriceLimitStart(BigDecimal jdPriceLimitStart) {
		this.jdPriceLimitStart = jdPriceLimitStart;
	}
	/**
	 * 京东sku价格门槛终止
	 * @return 京东sku价格门槛终止
	 */
	public BigDecimal getJdPriceLimitEnd() {
		return jdPriceLimitEnd;
	}

	/**
	 * 京东sku价格门槛终止
	 * @param jdPriceLimitEnd 京东sku价格门槛终止
	 */
	public void setJdPriceLimitEnd(BigDecimal jdPriceLimitEnd) {
		this.jdPriceLimitEnd = jdPriceLimitEnd;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
}
