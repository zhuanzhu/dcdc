package com.egeo.components.product.vo;

import com.egeo.components.product.dto.ChannelMerchantConfigDTO;
import com.egeo.components.product.dto.JdMerchantConfigDTO;

import java.io.Serializable;

/**
 *
 * @author tan
 * @date 2019-03-29 10:17:52
 */
public class ChannelPriceEnterpriseConfigVO implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 正式公司价格比例
	 */
	private ChannelMerchantConfigDTO enterpriseConfig;
	private ChannelMerchantConfigDTO platformConfig;
	private Integer radio;
	public ChannelMerchantConfigDTO getEnterpriseConfig() {
		return enterpriseConfig;
	}
	public void setEnterpriseConfig(ChannelMerchantConfigDTO enterpriseConfig) {
		this.enterpriseConfig = enterpriseConfig;
	}

	public ChannelMerchantConfigDTO getPlatformConfig() {
		return platformConfig;
	}

	public void setPlatformConfig(ChannelMerchantConfigDTO platformConfig) {
		this.platformConfig = platformConfig;
	}

	public Integer getRadio() {
		return radio;
	}
	public void setRadio(Integer radio) {
		this.radio = radio;
	}
}
