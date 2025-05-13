package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MerchantProdFlashDTO;
import com.egeo.components.product.po.MerchantProdFlashPO;
import com.egeo.components.product.vo.MerchantProdFlashVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class MerchantProdFlashConverter {
	
	
	public static MerchantProdFlashDTO toDTO(MerchantProdFlashVO src) {
		MerchantProdFlashDTO tar = new MerchantProdFlashDTO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());	
		tar.setIsGroupFlash(src.getIsGroupFlash());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static MerchantProdFlashVO toVO(MerchantProdFlashDTO src) {
		MerchantProdFlashVO tar = new MerchantProdFlashVO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());		
		tar.setIsGroupFlash(src.getIsGroupFlash());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());		
		return tar;
	}

	public static List<MerchantProdFlashDTO> toDTOs(List<MerchantProdFlashVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdFlashDTO> list = new ArrayList<MerchantProdFlashDTO>();
		for (MerchantProdFlashVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProdFlashVO> toVO(List<MerchantProdFlashDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdFlashVO> list = new ArrayList<MerchantProdFlashVO>();
		for (MerchantProdFlashDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MerchantProdFlashDTO toDTO(MerchantProdFlashPO src) {
		MerchantProdFlashDTO tar = new MerchantProdFlashDTO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setIsGroupFlash(src.getIsGroupFlash());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static MerchantProdFlashPO toPO(MerchantProdFlashDTO src) {
		MerchantProdFlashPO tar = new MerchantProdFlashPO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setIsGroupFlash(src.getIsGroupFlash());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<MerchantProdFlashDTO> toDTO(List<MerchantProdFlashPO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdFlashDTO> list = new ArrayList<MerchantProdFlashDTO>();
		for (MerchantProdFlashPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProdFlashPO> toPO(List<MerchantProdFlashDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdFlashPO> list = new ArrayList<MerchantProdFlashPO>();
		for (MerchantProdFlashDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	