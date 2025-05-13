package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MerchantCateTreeDTO;
import com.egeo.components.product.po.MerchantCateTreePO;
import com.egeo.components.product.vo.MerchantCateTreeVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class MerchantCateTreeConverter {
	
	
	public static MerchantCateTreeDTO toDTO(MerchantCateTreeVO src) {
		MerchantCateTreeDTO tar = new MerchantCateTreeDTO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());	
		tar.setType(src.getType());	
		tar.setName(src.getName());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static MerchantCateTreeVO toVO(MerchantCateTreeDTO src) {
		MerchantCateTreeVO tar = new MerchantCateTreeVO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());		
		tar.setType(src.getType());		
		tar.setName(src.getName());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());		
		return tar;
	}

	public static List<MerchantCateTreeDTO> toDTOs(List<MerchantCateTreeVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantCateTreeDTO> list = new ArrayList<MerchantCateTreeDTO>();
		for (MerchantCateTreeVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantCateTreeVO> toVO(List<MerchantCateTreeDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantCateTreeVO> list = new ArrayList<MerchantCateTreeVO>();
		for (MerchantCateTreeDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MerchantCateTreeDTO toDTO(MerchantCateTreePO src) {
		MerchantCateTreeDTO tar = new MerchantCateTreeDTO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());
		tar.setType(src.getType());
		tar.setName(src.getName());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static MerchantCateTreePO toPO(MerchantCateTreeDTO src) {
		MerchantCateTreePO tar = new MerchantCateTreePO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());
		tar.setType(src.getType());
		tar.setName(src.getName());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<MerchantCateTreeDTO> toDTO(List<MerchantCateTreePO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantCateTreeDTO> list = new ArrayList<MerchantCateTreeDTO>();
		for (MerchantCateTreePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantCateTreePO> toPO(List<MerchantCateTreeDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantCateTreePO> list = new ArrayList<MerchantCateTreePO>();
		for (MerchantCateTreeDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	