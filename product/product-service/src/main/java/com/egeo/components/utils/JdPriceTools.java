package com.egeo.components.utils;

import java.util.List;

import com.egeo.components.product.dto.JdEnterpriseConfigDTO;
import com.egeo.utils.StringUtils;

public class JdPriceTools {
	/**
	 * @param configs
	 * @param price
	 * @return
	 *//*
	public static String price(List<JdMerchantConfigDTO> configs,String price) {
		//平台缺省配置
		JdMerchantConfigDTO defaultConfig = null;
		//平台给代理商设置
		JdMerchantConfigDTO merchantConfig = null;
		//代理商自己的配置
		JdMerchantConfigDTO selfConfig = null;
		for(JdMerchantConfigDTO one : configs) {
			if(one.getType().intValue()==1) {
				defaultConfig = one;
			}else if(one.getType().intValue()==2) {
				merchantConfig = one;
			}else {
				selfConfig = one;
			}
		}
	}*/
	
	/**
	 * 平台缺省配置
	 * @param jdEnterpriseConfigs
	 * @return
	 */
	public static boolean hasPlatformConfig(List<JdEnterpriseConfigDTO> jdEnterpriseConfigs) {
		if(jdEnterpriseConfigs!=null && jdEnterpriseConfigs.size()>0) {
			for(JdEnterpriseConfigDTO one :jdEnterpriseConfigs) {
				if(one.getType()!=null && 1==one.getType().intValue()) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 平台缺省配置
	 * @param jdEnterpriseConfigs
	 * @return
	 */
	public static JdEnterpriseConfigDTO getPlatformConfig(List<JdEnterpriseConfigDTO> jdEnterpriseConfigs) {
		if(jdEnterpriseConfigs!=null && jdEnterpriseConfigs.size()>0) {
			for(JdEnterpriseConfigDTO one :jdEnterpriseConfigs) {
				if(one.getType()!=null && 1==one.getType().intValue()) {
					return one;
				}
			}
		}
		return null;
	}

	/**
	 * 企业缺省配置
	 * @param jdEnterpriseConfigs
	 * @return
	 */
	public static boolean hasEnterpriseDefaultConfig(List<JdEnterpriseConfigDTO> jdEnterpriseConfigs) {
		if(jdEnterpriseConfigs!=null && jdEnterpriseConfigs.size()>0) {
			for(JdEnterpriseConfigDTO one :jdEnterpriseConfigs) {
				if(one.getType()!=null && 2==one.getType().intValue()) {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean hasEnterpriseDefaultConfig(List<JdEnterpriseConfigDTO> jdEnterpriseConfigs,Long enterpriseId) {
		if(jdEnterpriseConfigs!=null && jdEnterpriseConfigs.size()>0) {
			for(JdEnterpriseConfigDTO one :jdEnterpriseConfigs) {
				if(one.getEnterpriseId()!=null &&one.getEnterpriseId().equals(enterpriseId) && one.getType()!=null && 2==one.getType().intValue()) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 企业缺省配置
	 * @param jdEnterpriseConfigs
	 * @return
	 */
	public static JdEnterpriseConfigDTO getEnterpriseDefaultConfig(List<JdEnterpriseConfigDTO> jdEnterpriseConfigs) {
		if(jdEnterpriseConfigs!=null && jdEnterpriseConfigs.size()>0) {
			for(JdEnterpriseConfigDTO one :jdEnterpriseConfigs) {
				if(one.getType()!=null && 2==one.getType().intValue()) {
					return one;
				}
			}
		}
		return null;
	}
	public static JdEnterpriseConfigDTO getEnterpriseDefaultConfig(List<JdEnterpriseConfigDTO> jdEnterpriseConfigs,Long enterpriseId) {
		if(jdEnterpriseConfigs!=null && jdEnterpriseConfigs.size()>0) {
			for(JdEnterpriseConfigDTO one :jdEnterpriseConfigs) {
				if(one.getEnterpriseId()!=null &&one.getEnterpriseId().equals(enterpriseId) &&one.getType()!=null && 2==one.getType().intValue()) {
					return one;
				}
			}
		}
		return null;
	}
	public static String getConfigPriceMax(List<JdEnterpriseConfigDTO> jdEnterpriseConfigs) {
		if(jdEnterpriseConfigs!=null && jdEnterpriseConfigs.size()>0) {
			String max = null;
			if(hasEnterpriseSellConfig(jdEnterpriseConfigs)) {
				JdEnterpriseConfigDTO enterpriseSellConfig = getEnterpriseSellConfig(jdEnterpriseConfigs);
				max = enterpriseSellConfig.getJdPriceMax();
			}
			if(max == null || max.length()==0) {
				JdEnterpriseConfigDTO enterpriseDefaultonfig = getEnterpriseDefaultConfig(jdEnterpriseConfigs);
				if(enterpriseDefaultonfig!=null) {
					max = enterpriseDefaultonfig.getJdPriceMax();
				}				
			}/*else {
				//企业定义的最大值>平台给企业定义的最大值。取平台的，否则取企业的
				JdEnterpriseConfigDTO enterpriseDefaultonfig = getEnterpriseDefaultConfig(jdEnterpriseConfigs);
				String enterpriseDefaultMax = enterpriseDefaultonfig.getJdPriceMax();
				if(StringUtils.isEmpty(enterpriseDefaultMax)) {
					enterpriseDefaultMax = "0";
				}
				
				
			}*/
			if(max == null || max.length()==0) {
				JdEnterpriseConfigDTO plateformConfig = getPlatformConfig(jdEnterpriseConfigs);
				max = plateformConfig.getJdPriceMax();
			}
			return max;
		}
		return null;
	}
	public static String getConfigPriceMin(List<JdEnterpriseConfigDTO> jdEnterpriseConfigs) {
		if(jdEnterpriseConfigs!=null && jdEnterpriseConfigs.size()>0) {
			String min = null;
			if(hasEnterpriseSellConfig(jdEnterpriseConfigs)) {
				JdEnterpriseConfigDTO enterpriseSellConfig = getEnterpriseSellConfig(jdEnterpriseConfigs);
				min = enterpriseSellConfig.getJdPriceMin();
			}
			if(min == null || min.length()==0) {
				JdEnterpriseConfigDTO enterpriseDefaultonfig = getEnterpriseDefaultConfig(jdEnterpriseConfigs);
				if(enterpriseDefaultonfig!=null) {
					min = enterpriseDefaultonfig.getJdPriceMin();
				}
				
			}/*else {
				//企业定义的最大值>平台给企业定义的最大值。取平台的，否则取企业的
				JdEnterpriseConfigDTO enterpriseDefaultonfig = getEnterpriseDefaultConfig(jdEnterpriseConfigs);
				String enterpriseDefaultMax = enterpriseDefaultonfig.getJdPriceMax();
				if(StringUtils.isEmpty(enterpriseDefaultMax)) {
					enterpriseDefaultMax = "0";
				}
				
				
			}*/
			if(min == null || min.length()==0) {
				JdEnterpriseConfigDTO plateformConfig = getPlatformConfig(jdEnterpriseConfigs);
				min = plateformConfig.getJdPriceMin();
			}
			return min;
		}
		return null;
	}
	/**
	 * 企业缺省配置
	 * @param jdEnterpriseConfigs
	 * @return
	 */
	public static boolean hasEnterpriseSellConfig(List<JdEnterpriseConfigDTO> jdEnterpriseConfigs) {
		if(jdEnterpriseConfigs!=null && jdEnterpriseConfigs.size()>0) {
			for(JdEnterpriseConfigDTO one :jdEnterpriseConfigs) {
				if(one.getType()!=null && 3==one.getType().intValue()) {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean hasEnterpriseSellConfig(List<JdEnterpriseConfigDTO> jdEnterpriseConfigs,Long enterpriseId) {
		if(jdEnterpriseConfigs!=null && jdEnterpriseConfigs.size()>0) {
			for(JdEnterpriseConfigDTO one :jdEnterpriseConfigs) {
				if(one.getEnterpriseId()!=null && enterpriseId.equals(one.getEnterpriseId()) && one.getType()!=null && 3==one.getType().intValue()) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 企业缺省配置
	 * @param jdEnterpriseConfigs
	 * @return
	 */
	public static JdEnterpriseConfigDTO getEnterpriseSellConfig(List<JdEnterpriseConfigDTO> jdEnterpriseConfigs,Long enterpriseId) {
		if(jdEnterpriseConfigs!=null && jdEnterpriseConfigs.size()>0) {
			for(JdEnterpriseConfigDTO one :jdEnterpriseConfigs) {
				if(one.getEnterpriseId()!=null && enterpriseId.equals(one.getEnterpriseId())&& one.getType()!=null && 3==one.getType().intValue()) {
					return one;
				}
			}
		}
		return null;
	}
	public static JdEnterpriseConfigDTO getEnterpriseSellConfig(List<JdEnterpriseConfigDTO> jdEnterpriseConfigs) {
		if(jdEnterpriseConfigs!=null && jdEnterpriseConfigs.size()>0) {
			for(JdEnterpriseConfigDTO one :jdEnterpriseConfigs) {
				if(one.getType()!=null && 3==one.getType().intValue()) {
					return one;
				}
			}
		}
		return null;
	}
	
}
