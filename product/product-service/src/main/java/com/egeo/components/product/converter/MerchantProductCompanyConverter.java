package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MerchantProductCompanyDTO;
import com.egeo.components.product.po.MerchantProductCompanyPO;
import com.egeo.components.product.vo.MerchantProductCompanyVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-06 09:20:45
 */
public class MerchantProductCompanyConverter {
	
	public static MerchantProductCompanyDTO toDTO(MerchantProductCompanyVO src) {
		if (src == null)
		return null;	
		MerchantProductCompanyDTO tar = new MerchantProductCompanyDTO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());	
		tar.setCompanyId(src.getCompanyId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setCompanyType(src.getCompanyType());
		return tar;
	}

	public static MerchantProductCompanyVO toVO(MerchantProductCompanyDTO src) {
		if (src == null)
		return null;	
		MerchantProductCompanyVO tar = new MerchantProductCompanyVO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());	
		tar.setCompanyId(src.getCompanyId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setCompanyType(src.getCompanyType());
		return tar;
	}

	public static List<MerchantProductCompanyDTO> toDTO(List<MerchantProductCompanyVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductCompanyDTO> list = new ArrayList<MerchantProductCompanyDTO>();
		for (MerchantProductCompanyVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProductCompanyVO> toVO(List<MerchantProductCompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductCompanyVO> list = new ArrayList<MerchantProductCompanyVO>();
		for (MerchantProductCompanyDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MerchantProductCompanyDTO toDTO(MerchantProductCompanyPO src) {
		if (src == null)
		return null;	
		MerchantProductCompanyDTO tar = new MerchantProductCompanyDTO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setCompanyType(src.getCompanyType());
		return tar;
	}

	public static MerchantProductCompanyPO toPO(MerchantProductCompanyDTO src) {
		if (src == null)
		return null;	
		MerchantProductCompanyPO tar = new MerchantProductCompanyPO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setCompanyType(src.getCompanyType());
		return tar;
	}

	public static List<MerchantProductCompanyDTO> toDTOs(List<MerchantProductCompanyPO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductCompanyDTO> list = new ArrayList<MerchantProductCompanyDTO>();
		for (MerchantProductCompanyPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProductCompanyPO> toPO(List<MerchantProductCompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductCompanyPO> list = new ArrayList<MerchantProductCompanyPO>();
		for (MerchantProductCompanyDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	