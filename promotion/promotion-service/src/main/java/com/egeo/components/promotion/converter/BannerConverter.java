package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.BannerDTO;
import com.egeo.components.promotion.po.BannerPO;
import com.egeo.components.promotion.vo.BannerVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2017-09-04 14:20:12
 */
public class BannerConverter {

	
	public static BannerDTO toDTO(BannerVO src) {
		BannerDTO tar = new BannerDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setType(src.getType());	
		tar.setSortValue(src.getSortValue());	
		tar.setMerchantProdId(src.getMerchantProdId());	
		tar.setActivityId(src.getActivityId());	
		tar.setPictureUrl(src.getPictureUrl());	
		tar.setPath(src.getPath());	
		tar.setStartTime(src.getStartTime());	
		tar.setFinishTime(src.getFinishTime());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static BannerVO toVO(BannerDTO src) {
		BannerVO tar = new BannerVO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setType(src.getType());	
		tar.setSortValue(src.getSortValue());	
		tar.setMerchantProdId(src.getMerchantProdId());	
		tar.setActivityId(src.getActivityId());	
		tar.setPictureUrl(src.getPictureUrl());	
		tar.setPath(src.getPath());	
		tar.setStartTime(src.getStartTime());	
		tar.setFinishTime(src.getFinishTime());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<BannerDTO> toDTOs(List<BannerVO> srcs) {
		if (srcs == null)
			return null;
		List<BannerDTO> list = new ArrayList<BannerDTO>();
		for (BannerVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<BannerVO> toVO(List<BannerDTO> srcs) {
		if (srcs == null)
			return null;
		List<BannerVO> list = new ArrayList<BannerVO>();
		for (BannerDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static BannerDTO toDTO(BannerPO src) {
		BannerDTO tar = new BannerDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setType(src.getType());
		tar.setSortValue(src.getSortValue());
		tar.setMerchantProdId(src.getMerchantProdId());
		tar.setActivityId(src.getActivityId());
		tar.setPictureUrl(src.getPictureUrl());
		tar.setPath(src.getPath());
		tar.setStartTime(src.getStartTime());
		tar.setFinishTime(src.getFinishTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setTime(src.getTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static BannerPO toPO(BannerDTO src) {
		BannerPO tar = new BannerPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setType(src.getType());
		tar.setSortValue(src.getSortValue());
		tar.setMerchantProdId(src.getMerchantProdId());
		tar.setActivityId(src.getActivityId());
		tar.setPictureUrl(src.getPictureUrl());
		tar.setPath(src.getPath());
		tar.setStartTime(src.getStartTime());
		tar.setFinishTime(src.getFinishTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setTime(src.getTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<BannerDTO> toDTO(List<BannerPO> srcs) {
		if (srcs == null)
			return null;
		List<BannerDTO> list = new ArrayList<BannerDTO>();
		for (BannerPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<BannerPO> toPO(List<BannerDTO> srcs) {
		if (srcs == null)
			return null;
		List<BannerPO> list = new ArrayList<BannerPO>();
		for (BannerDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	