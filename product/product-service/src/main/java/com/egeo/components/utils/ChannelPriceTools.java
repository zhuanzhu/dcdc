package com.egeo.components.utils;

import com.egeo.components.product.dto.ChannelEnterpriseConfigDTO;
import com.egeo.components.product.dto.JdEnterpriseConfigDTO;

import java.util.List;

public class ChannelPriceTools {


	/**
	 * 平台缺省配置
	 * @param jdEnterpriseConfigs
	 * @return
	 */
	public static boolean hasPlatformConfig(List<ChannelEnterpriseConfigDTO> jdEnterpriseConfigs) {
		if(jdEnterpriseConfigs!=null && jdEnterpriseConfigs.size()>0) {
			for(ChannelEnterpriseConfigDTO one :jdEnterpriseConfigs) {
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
	public static ChannelEnterpriseConfigDTO getPlatformConfig(List<ChannelEnterpriseConfigDTO> jdEnterpriseConfigs) {
		if(jdEnterpriseConfigs!=null && jdEnterpriseConfigs.size()>0) {
			for(ChannelEnterpriseConfigDTO one :jdEnterpriseConfigs) {
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
	public static boolean hasEnterpriseDefaultConfig(List<ChannelEnterpriseConfigDTO> jdEnterpriseConfigs) {
		if(jdEnterpriseConfigs!=null && jdEnterpriseConfigs.size()>0) {
			for(ChannelEnterpriseConfigDTO one :jdEnterpriseConfigs) {
				if(one.getType()!=null && 2==one.getType().intValue()) {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean hasEnterpriseDefaultConfig(List<ChannelEnterpriseConfigDTO> jdEnterpriseConfigs,Long enterpriseId) {
		if(jdEnterpriseConfigs!=null && jdEnterpriseConfigs.size()>0) {
			for(ChannelEnterpriseConfigDTO one :jdEnterpriseConfigs) {
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
	public static ChannelEnterpriseConfigDTO getEnterpriseDefaultConfig(List<ChannelEnterpriseConfigDTO> jdEnterpriseConfigs) {
		if(jdEnterpriseConfigs!=null && jdEnterpriseConfigs.size()>0) {
			for(ChannelEnterpriseConfigDTO one :jdEnterpriseConfigs) {
				if(one.getType()!=null && 2==one.getType().intValue()) {
					return one;
				}
			}
		}
		return null;
	}
	public static ChannelEnterpriseConfigDTO getEnterpriseDefaultConfig(List<ChannelEnterpriseConfigDTO> jdEnterpriseConfigs,Long enterpriseId) {
		if(jdEnterpriseConfigs!=null && jdEnterpriseConfigs.size()>0) {
			for(ChannelEnterpriseConfigDTO one :jdEnterpriseConfigs) {
				if(one.getEnterpriseId()!=null &&one.getEnterpriseId().equals(enterpriseId) &&one.getType()!=null && 2==one.getType().intValue()) {
					return one;
				}
			}
		}
		return null;
	}
	public static String getConfigPriceMax(List<ChannelEnterpriseConfigDTO> jdEnterpriseConfigs) {
		if(jdEnterpriseConfigs!=null && jdEnterpriseConfigs.size()>0) {
			String max = null;
			if(hasEnterpriseSellConfig(jdEnterpriseConfigs)) {
				ChannelEnterpriseConfigDTO enterpriseSellConfig = getEnterpriseSellConfig(jdEnterpriseConfigs);
				max = enterpriseSellConfig.getChannelPriceMax();
			}
			if(max == null || max.length()==0) {
				ChannelEnterpriseConfigDTO enterpriseDefaultonfig = getEnterpriseDefaultConfig(jdEnterpriseConfigs);
				if(enterpriseDefaultonfig!=null) {
					max = enterpriseDefaultonfig.getChannelPriceMax();
				}
			}
			if(max == null || max.length()==0) {
				ChannelEnterpriseConfigDTO plateformConfig = getPlatformConfig(jdEnterpriseConfigs);
				max = plateformConfig.getChannelPriceMax();
			}
			return max;
		}
		return null;
	}
	public static String getConfigPriceMin(List<ChannelEnterpriseConfigDTO> jdEnterpriseConfigs) {
		if(jdEnterpriseConfigs!=null && jdEnterpriseConfigs.size()>0) {
			String min = null;
			if(hasEnterpriseSellConfig(jdEnterpriseConfigs)) {
				ChannelEnterpriseConfigDTO enterpriseSellConfig = getEnterpriseSellConfig(jdEnterpriseConfigs);
				min = enterpriseSellConfig.getChannelPriceMin();
			}
			if(min == null || min.length()==0) {
				ChannelEnterpriseConfigDTO enterpriseDefaultonfig = getEnterpriseDefaultConfig(jdEnterpriseConfigs);
				if(enterpriseDefaultonfig!=null) {
					min = enterpriseDefaultonfig.getChannelPriceMin();
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
				ChannelEnterpriseConfigDTO plateformConfig = getPlatformConfig(jdEnterpriseConfigs);
				min = plateformConfig.getChannelPriceMin();
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
	public static boolean hasEnterpriseSellConfig(List<ChannelEnterpriseConfigDTO> jdEnterpriseConfigs) {
		if(jdEnterpriseConfigs!=null && jdEnterpriseConfigs.size()>0) {
			for(ChannelEnterpriseConfigDTO one :jdEnterpriseConfigs) {
				if(one.getType()!=null && 3==one.getType().intValue()) {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean hasEnterpriseSellConfig(List<ChannelEnterpriseConfigDTO> jdEnterpriseConfigs,Long enterpriseId) {
		if(jdEnterpriseConfigs!=null && jdEnterpriseConfigs.size()>0) {
			for(ChannelEnterpriseConfigDTO one :jdEnterpriseConfigs) {
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
	public static ChannelEnterpriseConfigDTO getEnterpriseSellConfig(List<ChannelEnterpriseConfigDTO> jdEnterpriseConfigs,Long enterpriseId) {
		if(jdEnterpriseConfigs!=null && jdEnterpriseConfigs.size()>0) {
			for(ChannelEnterpriseConfigDTO one :jdEnterpriseConfigs) {
				if(one.getEnterpriseId()!=null && enterpriseId.equals(one.getEnterpriseId())&& one.getType()!=null && 3==one.getType().intValue()) {
					return one;
				}
			}
		}
		return null;
	}
	public static ChannelEnterpriseConfigDTO getEnterpriseSellConfig(List<ChannelEnterpriseConfigDTO> jdEnterpriseConfigs) {
		if(jdEnterpriseConfigs!=null && jdEnterpriseConfigs.size()>0) {
			for(ChannelEnterpriseConfigDTO one :jdEnterpriseConfigs) {
				if(one.getType()!=null && 3==one.getType().intValue()) {
					return one;
				}
			}
		}
		return null;
	}

}
