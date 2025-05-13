package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MerchantVideoDTO;
import com.egeo.components.product.po.MerchantVideoPO;
import com.egeo.components.product.vo.MerchantVideoVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class MerchantVideoConverter {
	
	
	public static MerchantVideoDTO toDTO(MerchantVideoVO src) {
		MerchantVideoDTO tar = new MerchantVideoDTO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());	
		tar.setVideoId(src.getVideoId());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static MerchantVideoVO toVO(MerchantVideoDTO src) {
		MerchantVideoVO tar = new MerchantVideoVO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());		
		tar.setVideoId(src.getVideoId());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());		
		return tar;
	}

	public static List<MerchantVideoDTO> toDTOs(List<MerchantVideoVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantVideoDTO> list = new ArrayList<MerchantVideoDTO>();
		for (MerchantVideoVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantVideoVO> toVO(List<MerchantVideoDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantVideoVO> list = new ArrayList<MerchantVideoVO>();
		for (MerchantVideoDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MerchantVideoDTO toDTO(MerchantVideoPO src) {
		MerchantVideoDTO tar = new MerchantVideoDTO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());
		tar.setVideoId(src.getVideoId());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static MerchantVideoPO toPO(MerchantVideoDTO src) {
		MerchantVideoPO tar = new MerchantVideoPO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());
		tar.setVideoId(src.getVideoId());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<MerchantVideoDTO> toDTO(List<MerchantVideoPO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantVideoDTO> list = new ArrayList<MerchantVideoDTO>();
		for (MerchantVideoPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantVideoPO> toPO(List<MerchantVideoDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantVideoPO> list = new ArrayList<MerchantVideoPO>();
		for (MerchantVideoDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	