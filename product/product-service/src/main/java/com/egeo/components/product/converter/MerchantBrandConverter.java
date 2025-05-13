package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MerchantBrandDTO;
import com.egeo.components.product.po.MerchantBrandPO;
import com.egeo.components.product.vo.MerchantBrandVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class MerchantBrandConverter {
	
	public static MerchantBrandDTO toDTO(MerchantBrandVO src) {
		MerchantBrandDTO tar = new MerchantBrandDTO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());	
		tar.setBrandId(src.getBrandId());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static MerchantBrandVO toVO(MerchantBrandDTO src) {
		MerchantBrandVO tar = new MerchantBrandVO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());		
		tar.setBrandId(src.getBrandId());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());		
		return tar;
	}

	public static List<MerchantBrandDTO> toDTOs(List<MerchantBrandVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantBrandDTO> list = new ArrayList<MerchantBrandDTO>();
		for (MerchantBrandVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantBrandVO> toVO(List<MerchantBrandDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantBrandVO> list = new ArrayList<MerchantBrandVO>();
		for (MerchantBrandDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MerchantBrandDTO toDTO(MerchantBrandPO src) {
		MerchantBrandDTO tar = new MerchantBrandDTO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());
		tar.setBrandId(src.getBrandId());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static MerchantBrandPO toPO(MerchantBrandDTO src) {
		MerchantBrandPO tar = new MerchantBrandPO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());
		tar.setBrandId(src.getBrandId());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<MerchantBrandDTO> toDTO(List<MerchantBrandPO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantBrandDTO> list = new ArrayList<MerchantBrandDTO>();
		for (MerchantBrandPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantBrandPO> toPO(List<MerchantBrandDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantBrandPO> list = new ArrayList<MerchantBrandPO>();
		for (MerchantBrandDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	