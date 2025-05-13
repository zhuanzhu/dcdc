package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MerchantProductTagDTO;
import com.egeo.components.product.po.MerchantProductTagPO;
import com.egeo.components.product.vo.MerchantProductTagVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-05-11 15:04:03
 */
public class MerchantProductTagConverter {
	
	public static MerchantProductTagDTO toDTO(MerchantProductTagVO src) {
		if (src == null)
		return null;	
		MerchantProductTagDTO tar = new MerchantProductTagDTO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());	
		tar.setTagId(src.getTagId());	
		return tar;
	}

	public static MerchantProductTagVO toVO(MerchantProductTagDTO src) {
		if (src == null)
		return null;	
		MerchantProductTagVO tar = new MerchantProductTagVO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());	
		tar.setTagId(src.getTagId());	
		return tar;
	}

	public static List<MerchantProductTagDTO> toDTOs(List<MerchantProductTagVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductTagDTO> list = new ArrayList<MerchantProductTagDTO>();
		for (MerchantProductTagVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProductTagVO> toVO(List<MerchantProductTagDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductTagVO> list = new ArrayList<MerchantProductTagVO>();
		for (MerchantProductTagDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MerchantProductTagDTO toDTO(MerchantProductTagPO src) {
		if (src == null)
		return null;	
		MerchantProductTagDTO tar = new MerchantProductTagDTO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setTagId(src.getTagId());
		return tar;
	}

	public static MerchantProductTagPO toPO(MerchantProductTagDTO src) {
		if (src == null)
		return null;	
		MerchantProductTagPO tar = new MerchantProductTagPO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setTagId(src.getTagId());
		return tar;
	}

	public static List<MerchantProductTagDTO> toDTO(List<MerchantProductTagPO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductTagDTO> list = new ArrayList<MerchantProductTagDTO>();
		for (MerchantProductTagPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProductTagPO> toPO(List<MerchantProductTagDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductTagPO> list = new ArrayList<MerchantProductTagPO>();
		for (MerchantProductTagDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	