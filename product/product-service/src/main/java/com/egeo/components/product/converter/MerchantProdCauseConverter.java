package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MerchantProdCauseDTO;
import com.egeo.components.product.po.MerchantProdCausePO;
import com.egeo.components.product.vo.MerchantProdCauseVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-06 09:20:44
 */
public class MerchantProdCauseConverter {

	public static MerchantProdCauseDTO toDTO(MerchantProdCauseVO src) {
		if (src == null)
		return null;	
		MerchantProdCauseDTO tar = new MerchantProdCauseDTO();
		tar.setId(src.getId());
		tar.setCause(src.getCause());	
		tar.setSortValue(src.getSortValue());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setMerchantProdId(src.getMerchantProdId());	
		tar.setType(src.getType());
		return tar;
	}

	public static MerchantProdCauseVO toVO(MerchantProdCauseDTO src) {
		if (src == null)
		return null;	
		MerchantProdCauseVO tar = new MerchantProdCauseVO();
		tar.setId(src.getId());
		tar.setCause(src.getCause());	
		tar.setSortValue(src.getSortValue());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setMerchantProdId(src.getMerchantProdId());	
		tar.setType(src.getType());
		return tar;
	}

	public static List<MerchantProdCauseDTO> toDTOs(List<MerchantProdCauseVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdCauseDTO> list = new ArrayList<MerchantProdCauseDTO>();
		for (MerchantProdCauseVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProdCauseVO> toVO(List<MerchantProdCauseDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdCauseVO> list = new ArrayList<MerchantProdCauseVO>();
		for (MerchantProdCauseDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MerchantProdCauseDTO toDTO(MerchantProdCausePO src) {
		if (src == null)
		return null;	
		MerchantProdCauseDTO tar = new MerchantProdCauseDTO();
		tar.setId(src.getId());
		tar.setCause(src.getCause());
		tar.setSortValue(src.getSortValue());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setMerchantProdId(src.getMerchantProdId());
		return tar;
	}

	public static MerchantProdCausePO toPO(MerchantProdCauseDTO src) {
		if (src == null)
		return null;	
		MerchantProdCausePO tar = new MerchantProdCausePO();
		tar.setId(src.getId());
		tar.setCause(src.getCause());
		tar.setSortValue(src.getSortValue());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setMerchantProdId(src.getMerchantProdId());
		return tar;
	}

	public static List<MerchantProdCauseDTO> toDTO(List<MerchantProdCausePO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdCauseDTO> list = new ArrayList<MerchantProdCauseDTO>();
		for (MerchantProdCausePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProdCausePO> toPO(List<MerchantProdCauseDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdCausePO> list = new ArrayList<MerchantProdCausePO>();
		for (MerchantProdCauseDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	