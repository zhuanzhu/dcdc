package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MerchantProductMembershipDTO;
import com.egeo.components.product.po.MerchantProductMembershipPO;
import com.egeo.components.product.vo.MerchantProductMembershipVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-09-11 14:59:30
 */
public class MerchantProductMembershipConverter {
	
	public static MerchantProductMembershipDTO toDTO(MerchantProductMembershipVO src) {
		if (src == null)
		return null;	
		MerchantProductMembershipDTO tar = new MerchantProductMembershipDTO();
		tar.setId(src.getId());
		tar.setMembershipId(src.getMembershipId());	
		tar.setMerchantProductId(src.getMerchantProductId());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static MerchantProductMembershipVO toVO(MerchantProductMembershipDTO src) {
		if (src == null)
		return null;	
		MerchantProductMembershipVO tar = new MerchantProductMembershipVO();
		tar.setId(src.getId());
		tar.setMembershipId(src.getMembershipId());	
		tar.setMerchantProductId(src.getMerchantProductId());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<MerchantProductMembershipDTO> toDTOs(List<MerchantProductMembershipVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductMembershipDTO> list = new ArrayList<MerchantProductMembershipDTO>();
		for (MerchantProductMembershipVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProductMembershipVO> toVO(List<MerchantProductMembershipDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductMembershipVO> list = new ArrayList<MerchantProductMembershipVO>();
		for (MerchantProductMembershipDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MerchantProductMembershipDTO toDTO(MerchantProductMembershipPO src) {
		if (src == null)
		return null;	
		MerchantProductMembershipDTO tar = new MerchantProductMembershipDTO();
		tar.setId(src.getId());
		tar.setMembershipId(src.getMembershipId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static MerchantProductMembershipPO toPO(MerchantProductMembershipDTO src) {
		if (src == null)
		return null;	
		MerchantProductMembershipPO tar = new MerchantProductMembershipPO();
		tar.setId(src.getId());
		tar.setMembershipId(src.getMembershipId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<MerchantProductMembershipDTO> toDTO(List<MerchantProductMembershipPO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductMembershipDTO> list = new ArrayList<MerchantProductMembershipDTO>();
		for (MerchantProductMembershipPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProductMembershipPO> toPO(List<MerchantProductMembershipDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductMembershipPO> list = new ArrayList<MerchantProductMembershipPO>();
		for (MerchantProductMembershipDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	