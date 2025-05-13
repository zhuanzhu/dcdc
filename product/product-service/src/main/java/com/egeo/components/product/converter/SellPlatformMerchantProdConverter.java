package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.SellPlatformMerchantProdDTO;
import com.egeo.components.product.po.SellPlatformMerchantProdPO;
import com.egeo.components.product.vo.SellPlatformMerchantProdVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-06 14:42:44
 */
public class SellPlatformMerchantProdConverter {
	
	public static SellPlatformMerchantProdDTO toDTO(SellPlatformMerchantProdVO src) {
		if (src == null)
		return null;	
		SellPlatformMerchantProdDTO tar = new SellPlatformMerchantProdDTO();
		tar.setId(src.getId());
		tar.setSellPlatformId(src.getSellPlatformId());	
		tar.setMerchantProductId(src.getMerchantProductId());	
		tar.setSalePrice(src.getSalePrice());	
		tar.setPath(src.getPath());	
		return tar;
	}

	public static SellPlatformMerchantProdVO toVO(SellPlatformMerchantProdDTO src) {
		if (src == null)
		return null;	
		SellPlatformMerchantProdVO tar = new SellPlatformMerchantProdVO();
		tar.setId(src.getId());
		tar.setSellPlatformId(src.getSellPlatformId());	
		tar.setMerchantProductId(src.getMerchantProductId());	
		tar.setSalePrice(src.getSalePrice());	
		tar.setPath(src.getPath());	
		return tar;
	}

	public static List<SellPlatformMerchantProdDTO> toDTOs(List<SellPlatformMerchantProdVO> srcs) {
		if (srcs == null)
			return null;
		List<SellPlatformMerchantProdDTO> list = new ArrayList<SellPlatformMerchantProdDTO>();
		for (SellPlatformMerchantProdVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SellPlatformMerchantProdVO> toVO(List<SellPlatformMerchantProdDTO> srcs) {
		if (srcs == null)
			return null;
		List<SellPlatformMerchantProdVO> list = new ArrayList<SellPlatformMerchantProdVO>();
		for (SellPlatformMerchantProdDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static SellPlatformMerchantProdDTO toDTO(SellPlatformMerchantProdPO src) {
		if (src == null)
		return null;	
		SellPlatformMerchantProdDTO tar = new SellPlatformMerchantProdDTO();
		tar.setId(src.getId());
		tar.setSellPlatformId(src.getSellPlatformId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setSalePrice(src.getSalePrice());
		tar.setPath(src.getPath());
		return tar;
	}

	public static SellPlatformMerchantProdPO toPO(SellPlatformMerchantProdDTO src) {
		if (src == null)
		return null;	
		SellPlatformMerchantProdPO tar = new SellPlatformMerchantProdPO();
		tar.setId(src.getId());
		tar.setSellPlatformId(src.getSellPlatformId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setSalePrice(src.getSalePrice());
		tar.setPath(src.getPath());
		return tar;
	}

	public static List<SellPlatformMerchantProdDTO> toDTO(List<SellPlatformMerchantProdPO> srcs) {
		if (srcs == null)
			return null;
		List<SellPlatformMerchantProdDTO> list = new ArrayList<SellPlatformMerchantProdDTO>();
		for (SellPlatformMerchantProdPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SellPlatformMerchantProdPO> toPO(List<SellPlatformMerchantProdDTO> srcs) {
		if (srcs == null)
			return null;
		List<SellPlatformMerchantProdPO> list = new ArrayList<SellPlatformMerchantProdPO>();
		for (SellPlatformMerchantProdDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	