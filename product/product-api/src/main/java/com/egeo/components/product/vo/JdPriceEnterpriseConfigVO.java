package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.Date;

import com.egeo.components.product.dto.JdMerchantConfigDTO;

/**
 * 
 * @author tan
 * @date 2019-03-29 10:17:52
 */
public class JdPriceEnterpriseConfigVO implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 正式公司价格比例
	 */
	private JdMerchantConfigDTO enterpriseConfig;
	private JdMerchantConfigDTO platformConfig; 
	private Integer radio;
	public JdMerchantConfigDTO getEnterpriseConfig() {
		return enterpriseConfig;
	}
	public void setEnterpriseConfig(JdMerchantConfigDTO enterpriseConfig) {
		this.enterpriseConfig = enterpriseConfig;
	}
	public JdMerchantConfigDTO getPlatformConfig() {
		return platformConfig;
	}
	public void setPlatformConfig(JdMerchantConfigDTO platformConfig) {
		this.platformConfig = platformConfig;
	}
	public Integer getRadio() {
		return radio;
	}
	public void setRadio(Integer radio) {
		this.radio = radio;
	}
}
	