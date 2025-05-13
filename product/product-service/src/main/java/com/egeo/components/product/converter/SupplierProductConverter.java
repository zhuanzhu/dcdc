package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.SupplierProductDTO;
import com.egeo.components.product.po.SupplierProductPO;
import com.egeo.components.product.vo.SupplierProductVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-03-07 11:31:34
 */
public class SupplierProductConverter {

	public static SupplierProductDTO toDTO(SupplierProductVO src) {
		if (src == null)
		return null;	
		SupplierProductDTO tar = new SupplierProductDTO();
		tar.setId(src.getId());
		tar.setSupplierId(src.getSupplierId());	
		tar.setProductId(src.getProductId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static SupplierProductVO toVO(SupplierProductDTO src) {
		if (src == null)
		return null;	
		SupplierProductVO tar = new SupplierProductVO();
		tar.setId(src.getId());
		tar.setSupplierId(src.getSupplierId());	
		tar.setProductId(src.getProductId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<SupplierProductDTO> toDTOs(List<SupplierProductVO> srcs) {
		if (srcs == null)
			return null;
		List<SupplierProductDTO> list = new ArrayList<SupplierProductDTO>();
		for (SupplierProductVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SupplierProductVO> toVO(List<SupplierProductDTO> srcs) {
		if (srcs == null)
			return null;
		List<SupplierProductVO> list = new ArrayList<SupplierProductVO>();
		for (SupplierProductDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static SupplierProductDTO toDTO(SupplierProductPO src) {
		if (src == null)
		return null;	
		SupplierProductDTO tar = new SupplierProductDTO();
		tar.setId(src.getId());
		tar.setSupplierId(src.getSupplierId());
		tar.setProductId(src.getProductId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static SupplierProductPO toPO(SupplierProductDTO src) {
		if (src == null)
		return null;	
		SupplierProductPO tar = new SupplierProductPO();
		tar.setId(src.getId());
		tar.setSupplierId(src.getSupplierId());
		tar.setProductId(src.getProductId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<SupplierProductDTO> toDTO(List<SupplierProductPO> srcs) {
		if (srcs == null)
			return null;
		List<SupplierProductDTO> list = new ArrayList<SupplierProductDTO>();
		for (SupplierProductPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SupplierProductPO> toPO(List<SupplierProductDTO> srcs) {
		if (srcs == null)
			return null;
		List<SupplierProductPO> list = new ArrayList<SupplierProductPO>();
		for (SupplierProductDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	