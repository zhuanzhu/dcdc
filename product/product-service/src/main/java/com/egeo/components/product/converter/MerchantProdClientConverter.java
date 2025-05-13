package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MerchantProdClientDTO;
import com.egeo.components.product.po.MerchantProdClientPO;
import com.egeo.components.product.vo.MerchantProdClientVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-06 09:20:45
 */
public class MerchantProdClientConverter {

	
	public static MerchantProdClientDTO toDTO(MerchantProdClientVO src) {
		if (src == null)
		return null;	
		MerchantProdClientDTO tar = new MerchantProdClientDTO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());	
		tar.setClientId(src.getClientId());	
		tar.setCreateTime(src.getCreateTime());	
		return tar;
	}

	public static MerchantProdClientVO toVO(MerchantProdClientDTO src) {
		if (src == null)
		return null;	
		MerchantProdClientVO tar = new MerchantProdClientVO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());	
		tar.setClientId(src.getClientId());	
		tar.setCreateTime(src.getCreateTime());	
		return tar;
	}

	public static List<MerchantProdClientDTO> toDTOs(List<MerchantProdClientVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdClientDTO> list = new ArrayList<MerchantProdClientDTO>();
		for (MerchantProdClientVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProdClientVO> toVO(List<MerchantProdClientDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdClientVO> list = new ArrayList<MerchantProdClientVO>();
		for (MerchantProdClientDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MerchantProdClientDTO toDTO(MerchantProdClientPO src) {
		if (src == null)
		return null;	
		MerchantProdClientDTO tar = new MerchantProdClientDTO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setClientId(src.getClientId());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static MerchantProdClientPO toPO(MerchantProdClientDTO src) {
		if (src == null)
		return null;	
		MerchantProdClientPO tar = new MerchantProdClientPO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setClientId(src.getClientId());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<MerchantProdClientDTO> toDTO(List<MerchantProdClientPO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdClientDTO> list = new ArrayList<MerchantProdClientDTO>();
		for (MerchantProdClientPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProdClientPO> toPO(List<MerchantProdClientDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdClientPO> list = new ArrayList<MerchantProdClientPO>();
		for (MerchantProdClientDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	