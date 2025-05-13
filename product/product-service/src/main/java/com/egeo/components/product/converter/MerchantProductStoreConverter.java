package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MerchantProductStoreDTO;
import com.egeo.components.product.po.MerchantProductStorePO;
import com.egeo.components.product.vo.MerchantProductStoreVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-09-11 14:59:32
 */
public class MerchantProductStoreConverter {
	
	public static MerchantProductStoreDTO toDTO(MerchantProductStoreVO src) {
		if (src == null)
		return null;	
		MerchantProductStoreDTO tar = new MerchantProductStoreDTO();
		tar.setId(src.getId());
		tar.setStoreId(src.getStoreId());	
		tar.setMerchantProductId(src.getMerchantProductId());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static MerchantProductStoreVO toVO(MerchantProductStoreDTO src) {
		if (src == null)
		return null;	
		MerchantProductStoreVO tar = new MerchantProductStoreVO();
		tar.setId(src.getId());
		tar.setStoreId(src.getStoreId());	
		tar.setMerchantProductId(src.getMerchantProductId());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<MerchantProductStoreDTO> toDTOs(List<MerchantProductStoreVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductStoreDTO> list = new ArrayList<MerchantProductStoreDTO>();
		for (MerchantProductStoreVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProductStoreVO> toVO(List<MerchantProductStoreDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductStoreVO> list = new ArrayList<MerchantProductStoreVO>();
		for (MerchantProductStoreDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MerchantProductStoreDTO toDTO(MerchantProductStorePO src) {
		if (src == null)
		return null;	
		MerchantProductStoreDTO tar = new MerchantProductStoreDTO();
		tar.setId(src.getId());
		tar.setStoreId(src.getStoreId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static MerchantProductStorePO toPO(MerchantProductStoreDTO src) {
		if (src == null)
		return null;	
		MerchantProductStorePO tar = new MerchantProductStorePO();
		tar.setId(src.getId());
		tar.setStoreId(src.getStoreId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<MerchantProductStoreDTO> toDTO(List<MerchantProductStorePO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductStoreDTO> list = new ArrayList<MerchantProductStoreDTO>();
		for (MerchantProductStorePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProductStorePO> toPO(List<MerchantProductStoreDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductStorePO> list = new ArrayList<MerchantProductStorePO>();
		for (MerchantProductStoreDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	