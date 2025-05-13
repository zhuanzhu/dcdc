package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.SupplierDTO;
import com.egeo.components.product.po.SupplierPO;
import com.egeo.components.product.vo.SupplierVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-03-07 11:31:33
 */
public class SupplierConverter {

	
	public static SupplierDTO toDTO(SupplierVO src) {
		if (src == null)
		return null;	
		SupplierDTO tar = new SupplierDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static SupplierVO toVO(SupplierDTO src) {
		if (src == null)
		return null;	
		SupplierVO tar = new SupplierVO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<SupplierDTO> toDTOs(List<SupplierVO> srcs) {
		if (srcs == null)
			return null;
		List<SupplierDTO> list = new ArrayList<SupplierDTO>();
		for (SupplierVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SupplierVO> toVO(List<SupplierDTO> srcs) {
		if (srcs == null)
			return null;
		List<SupplierVO> list = new ArrayList<SupplierVO>();
		for (SupplierDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static SupplierDTO toDTO(SupplierPO src) {
		if (src == null)
		return null;	
		SupplierDTO tar = new SupplierDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static SupplierPO toPO(SupplierDTO src) {
		if (src == null)
		return null;	
		SupplierPO tar = new SupplierPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<SupplierDTO> toDTO(List<SupplierPO> srcs) {
		if (srcs == null)
			return null;
		List<SupplierDTO> list = new ArrayList<SupplierDTO>();
		for (SupplierPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SupplierPO> toPO(List<SupplierDTO> srcs) {
		if (srcs == null)
			return null;
		List<SupplierPO> list = new ArrayList<SupplierPO>();
		for (SupplierDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	