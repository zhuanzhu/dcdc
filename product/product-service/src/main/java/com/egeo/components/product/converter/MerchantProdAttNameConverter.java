package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MerchantProdAttNameDTO;
import com.egeo.components.product.po.MerchantProdAttNamePO;
import com.egeo.components.product.vo.MerchantProdAttNameVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-06 09:20:44
 */
public class MerchantProdAttNameConverter {

	
	public static MerchantProdAttNameDTO toDTO(MerchantProdAttNameVO src) {
		if (src == null)
		return null;	
		MerchantProdAttNameDTO tar = new MerchantProdAttNameDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());	
		tar.setAttNameId(src.getAttNameId());	
		tar.setSortValue(src.getSortValue());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setMerchantProductId(src.getMerchantProductId());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static MerchantProdAttNameVO toVO(MerchantProdAttNameDTO src) {
		if (src == null)
		return null;	
		MerchantProdAttNameVO tar = new MerchantProdAttNameVO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());	
		tar.setAttNameId(src.getAttNameId());	
		tar.setSortValue(src.getSortValue());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setMerchantProductId(src.getMerchantProductId());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<MerchantProdAttNameDTO> toDTOs(List<MerchantProdAttNameVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdAttNameDTO> list = new ArrayList<MerchantProdAttNameDTO>();
		for (MerchantProdAttNameVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProdAttNameVO> toVO(List<MerchantProdAttNameDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdAttNameVO> list = new ArrayList<MerchantProdAttNameVO>();
		for (MerchantProdAttNameDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MerchantProdAttNameDTO toDTO(MerchantProdAttNamePO src) {
		if (src == null)
		return null;	
		MerchantProdAttNameDTO tar = new MerchantProdAttNameDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setAttNameId(src.getAttNameId());
		tar.setSortValue(src.getSortValue());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static MerchantProdAttNamePO toPO(MerchantProdAttNameDTO src) {
		if (src == null)
		return null;	
		MerchantProdAttNamePO tar = new MerchantProdAttNamePO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setAttNameId(src.getAttNameId());
		tar.setSortValue(src.getSortValue());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<MerchantProdAttNameDTO> toDTO(List<MerchantProdAttNamePO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdAttNameDTO> list = new ArrayList<MerchantProdAttNameDTO>();
		for (MerchantProdAttNamePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProdAttNamePO> toPO(List<MerchantProdAttNameDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdAttNamePO> list = new ArrayList<MerchantProdAttNamePO>();
		for (MerchantProdAttNameDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	