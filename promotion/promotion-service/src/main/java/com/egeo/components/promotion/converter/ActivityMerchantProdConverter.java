package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.ActivityMerchantProdDTO;
import com.egeo.components.promotion.po.ActivityMerchantProdPO;
import com.egeo.components.promotion.vo.ActivityMerchantProdVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-24 16:42:11
 */
public class ActivityMerchantProdConverter {

	public static ActivityMerchantProdDTO toDTO(ActivityMerchantProdVO src) {
		ActivityMerchantProdDTO tar = new ActivityMerchantProdDTO();
		tar.setId(src.getId());
		tar.setActivityId(src.getActivityId());			
		tar.setSortValue(src.getSortValue());			
		tar.setCreateTime(src.getCreateTime());			
		tar.setUpdateTime(src.getUpdateTime());			
		tar.setPlatformId(src.getPlatformId());			
		tar.setMerchantProdId(src.getMerchantProdId());			
		return tar;
	}

	public static ActivityMerchantProdVO toVO(ActivityMerchantProdDTO src) {
		ActivityMerchantProdVO tar = new ActivityMerchantProdVO();
		tar.setId(src.getId());
		tar.setActivityId(src.getActivityId());
		tar.setSortValue(src.getSortValue());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setMerchantProdId(src.getMerchantProdId());
		return tar;
	}



	public static List<ActivityMerchantProdVO> toVO(List<ActivityMerchantProdDTO> srcs) {
		if (srcs == null)
			return null;
		List<ActivityMerchantProdVO> list = new ArrayList<ActivityMerchantProdVO>();
		for (ActivityMerchantProdDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static ActivityMerchantProdDTO toDTO(ActivityMerchantProdPO src) {
		ActivityMerchantProdDTO tar = new ActivityMerchantProdDTO();
		tar.setId(src.getId());
		tar.setActivityId(src.getActivityId());
		tar.setSortValue(src.getSortValue());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setMerchantProdId(src.getMerchantProdId());
		return tar;
	}

	public static ActivityMerchantProdPO toPO(ActivityMerchantProdDTO src) {
		ActivityMerchantProdPO tar = new ActivityMerchantProdPO();
		tar.setId(src.getId());
		tar.setActivityId(src.getActivityId());
		tar.setSortValue(src.getSortValue());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setMerchantProdId(src.getMerchantProdId());
		return tar;
	}

	public static List<ActivityMerchantProdDTO> toDTO(List<ActivityMerchantProdPO> srcs) {
		if (srcs == null)
			return null;
		List<ActivityMerchantProdDTO> list = new ArrayList<ActivityMerchantProdDTO>();
		for (ActivityMerchantProdPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ActivityMerchantProdPO> toPO(List<ActivityMerchantProdDTO> srcs) {
		if (srcs == null)
			return null;
		List<ActivityMerchantProdPO> list = new ArrayList<ActivityMerchantProdPO>();
		for (ActivityMerchantProdDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	