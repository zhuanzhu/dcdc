package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MerchantProdAttValueDTO;
import com.egeo.components.product.po.MerchantProdAttValuePO;
import com.egeo.components.product.vo.MerchantProdAttValueVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-06 09:20:44
 */
public class MerchantProdAttValueConverter {

	
	public static MerchantProdAttValueDTO toDTO(MerchantProdAttValueVO src) {
		if (src == null)
		return null;	
		MerchantProdAttValueDTO tar = new MerchantProdAttValueDTO();
		tar.setId(src.getId());
		tar.setMerchantProdAttNameId(src.getMerchantProdAttNameId());	
		tar.setAttValueId(src.getAttValueId());	
		tar.setAttValueCustom(src.getAttValueCustom());	
		tar.setSortValue(src.getSortValue());	
		tar.setPictureUrl(src.getPictureUrl());	
		tar.setIsWith(src.getIsWith());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static MerchantProdAttValueVO toVO(MerchantProdAttValueDTO src) {
		if (src == null)
		return null;	
		MerchantProdAttValueVO tar = new MerchantProdAttValueVO();
		tar.setId(src.getId());
		tar.setMerchantProdAttNameId(src.getMerchantProdAttNameId());	
		tar.setAttValueId(src.getAttValueId());	
		tar.setAttValueCustom(src.getAttValueCustom());	
		tar.setSortValue(src.getSortValue());	
		tar.setPictureUrl(src.getPictureUrl());	
		tar.setIsWith(src.getIsWith());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static List<MerchantProdAttValueDTO> toDTOs(List<MerchantProdAttValueVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdAttValueDTO> list = new ArrayList<MerchantProdAttValueDTO>();
		for (MerchantProdAttValueVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProdAttValueVO> toVO(List<MerchantProdAttValueDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdAttValueVO> list = new ArrayList<MerchantProdAttValueVO>();
		for (MerchantProdAttValueDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MerchantProdAttValueDTO toDTO(MerchantProdAttValuePO src) {
		if (src == null)
		return null;	
		MerchantProdAttValueDTO tar = new MerchantProdAttValueDTO();
		tar.setId(src.getId());
		tar.setMerchantProdAttNameId(src.getMerchantProdAttNameId());
		tar.setAttValueId(src.getAttValueId());
		tar.setAttValueCustom(src.getAttValueCustom());
		tar.setSortValue(src.getSortValue());
		tar.setPictureUrl(src.getPictureUrl());
		tar.setIsWith(src.getIsWith());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static MerchantProdAttValuePO toPO(MerchantProdAttValueDTO src) {
		if (src == null)
		return null;	
		MerchantProdAttValuePO tar = new MerchantProdAttValuePO();
		tar.setId(src.getId());
		tar.setMerchantProdAttNameId(src.getMerchantProdAttNameId());
		tar.setAttValueId(src.getAttValueId());
		tar.setAttValueCustom(src.getAttValueCustom());
		tar.setSortValue(src.getSortValue());
		tar.setPictureUrl(src.getPictureUrl());
		tar.setIsWith(src.getIsWith());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<MerchantProdAttValueDTO> toDTO(List<MerchantProdAttValuePO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdAttValueDTO> list = new ArrayList<MerchantProdAttValueDTO>();
		for (MerchantProdAttValuePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProdAttValuePO> toPO(List<MerchantProdAttValueDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdAttValuePO> list = new ArrayList<MerchantProdAttValuePO>();
		for (MerchantProdAttValueDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	