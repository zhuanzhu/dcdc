package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.BlessingCoinBannerDTO;
import com.egeo.components.product.po.BlessingCoinBannerPO;
import com.egeo.components.product.vo.BlessingCoinBannerVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-25 16:03:21
 */
public class BlessingCoinBannerConverter {
	
	public static BlessingCoinBannerDTO toDTO(BlessingCoinBannerVO src) {
		if (src == null)
		return null;	
		BlessingCoinBannerDTO tar = new BlessingCoinBannerDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setType(src.getType());	
		tar.setSortValue(src.getSortValue());	
		tar.setLocation(src.getLocation());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setActivityId(src.getActivityId());	
		tar.setPictureUrl(src.getPictureUrl());	
		tar.setPath(src.getPath());	
		tar.setIsShow(src.getIsShow());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static BlessingCoinBannerVO toVO(BlessingCoinBannerDTO src) {
		if (src == null)
		return null;	
		BlessingCoinBannerVO tar = new BlessingCoinBannerVO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setType(src.getType());	
		tar.setSortValue(src.getSortValue());	
		tar.setLocation(src.getLocation());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setActivityId(src.getActivityId());	
		tar.setPictureUrl(src.getPictureUrl());	
		tar.setPath(src.getPath());	
		tar.setIsShow(src.getIsShow());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<BlessingCoinBannerDTO> toDTOs(List<BlessingCoinBannerVO> srcs) {
		if (srcs == null)
			return null;
		List<BlessingCoinBannerDTO> list = new ArrayList<BlessingCoinBannerDTO>();
		for (BlessingCoinBannerVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<BlessingCoinBannerVO> toVO(List<BlessingCoinBannerDTO> srcs) {
		if (srcs == null)
			return null;
		List<BlessingCoinBannerVO> list = new ArrayList<BlessingCoinBannerVO>();
		for (BlessingCoinBannerDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static BlessingCoinBannerDTO toDTO(BlessingCoinBannerPO src) {
		if (src == null)
		return null;	
		BlessingCoinBannerDTO tar = new BlessingCoinBannerDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setType(src.getType());
		tar.setSortValue(src.getSortValue());
		tar.setLocation(src.getLocation());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setActivityId(src.getActivityId());
		tar.setPictureUrl(src.getPictureUrl());
		tar.setPath(src.getPath());
		tar.setIsShow(src.getIsShow());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setCompanyId(src.getCompanyId());
		return tar;
	}

	public static BlessingCoinBannerPO toPO(BlessingCoinBannerDTO src) {
		if (src == null)
		return null;	
		BlessingCoinBannerPO tar = new BlessingCoinBannerPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setType(src.getType());
		tar.setSortValue(src.getSortValue());
		tar.setLocation(src.getLocation());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setActivityId(src.getActivityId());
		tar.setPictureUrl(src.getPictureUrl());
		tar.setPath(src.getPath());
		tar.setIsShow(src.getIsShow());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setCompanyId(src.getCompanyId());
		return tar;
	}

	public static List<BlessingCoinBannerDTO> toDTO(List<BlessingCoinBannerPO> srcs) {
		if (srcs == null)
			return null;
		List<BlessingCoinBannerDTO> list = new ArrayList<BlessingCoinBannerDTO>();
		for (BlessingCoinBannerPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<BlessingCoinBannerPO> toPO(List<BlessingCoinBannerDTO> srcs) {
		if (srcs == null)
			return null;
		List<BlessingCoinBannerPO> list = new ArrayList<BlessingCoinBannerPO>();
		for (BlessingCoinBannerDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	