package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MerchantProdVideoDTO;
import com.egeo.components.product.po.MerchantProdVideoPO;
import com.egeo.components.product.vo.MerchantProdVideoVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class MerchantProdVideoConverter {
	
	public static MerchantProdVideoDTO toDTO(MerchantProdVideoVO src) {
		MerchantProdVideoDTO tar = new MerchantProdVideoDTO();
		tar.setId(src.getId());
		tar.setMerchantProdId(src.getMerchantProdId());	
		tar.setMerchantVideoId(src.getMerchantVideoId());	
		tar.setName(src.getName());	
		tar.setType(src.getType());	
		tar.setThumbnailUrl(src.getThumbnailUrl());	
		tar.setVideoUrl(src.getVideoUrl());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static MerchantProdVideoVO toVO(MerchantProdVideoDTO src) {
		MerchantProdVideoVO tar = new MerchantProdVideoVO();
		tar.setId(src.getId());
		tar.setMerchantProdId(src.getMerchantProdId());		
		tar.setMerchantVideoId(src.getMerchantVideoId());		
		tar.setName(src.getName());		
		tar.setType(src.getType());		
		tar.setThumbnailUrl(src.getThumbnailUrl());		
		tar.setVideoUrl(src.getVideoUrl());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());		
		return tar;
	}

	public static List<MerchantProdVideoDTO> toDTOs(List<MerchantProdVideoVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdVideoDTO> list = new ArrayList<MerchantProdVideoDTO>();
		for (MerchantProdVideoVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProdVideoVO> toVO(List<MerchantProdVideoDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdVideoVO> list = new ArrayList<MerchantProdVideoVO>();
		for (MerchantProdVideoDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MerchantProdVideoDTO toDTO(MerchantProdVideoPO src) {
		MerchantProdVideoDTO tar = new MerchantProdVideoDTO();
		tar.setId(src.getId());
		tar.setMerchantProdId(src.getMerchantProdId());
		tar.setMerchantVideoId(src.getMerchantVideoId());
		tar.setName(src.getName());
		tar.setType(src.getType());
		tar.setThumbnailUrl(src.getThumbnailUrl());
		tar.setVideoUrl(src.getVideoUrl());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static MerchantProdVideoPO toPO(MerchantProdVideoDTO src) {
		MerchantProdVideoPO tar = new MerchantProdVideoPO();
		tar.setId(src.getId());
		tar.setMerchantProdId(src.getMerchantProdId());
		tar.setMerchantVideoId(src.getMerchantVideoId());
		tar.setName(src.getName());
		tar.setType(src.getType());
		tar.setThumbnailUrl(src.getThumbnailUrl());
		tar.setVideoUrl(src.getVideoUrl());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<MerchantProdVideoDTO> toDTO(List<MerchantProdVideoPO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdVideoDTO> list = new ArrayList<MerchantProdVideoDTO>();
		for (MerchantProdVideoPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProdVideoPO> toPO(List<MerchantProdVideoDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdVideoPO> list = new ArrayList<MerchantProdVideoPO>();
		for (MerchantProdVideoDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	