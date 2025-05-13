package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.CommodityProductUnitCompareDTO;
import com.egeo.components.product.po.CommodityProductUnitComparePO;
import com.egeo.components.product.vo.CommodityProductUnitCompareVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author paul
 * @date 2018-09-13 18:06:29
 */
public class CommodityProductUnitCompareConverter {

	
	public static CommodityProductUnitCompareDTO toDTO(CommodityProductUnitCompareVO src) {
		if (src == null)
		return null;	
		CommodityProductUnitCompareDTO tar = new CommodityProductUnitCompareDTO();
		tar.setId(src.getId());
		tar.setPlatformName(src.getPlatformName());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setStatus(src.getStatus());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setOperator(src.getOperator());	
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static CommodityProductUnitCompareVO toVO(CommodityProductUnitCompareDTO src) {
		if (src == null)
		return null;	
		CommodityProductUnitCompareVO tar = new CommodityProductUnitCompareVO();
		tar.setId(src.getId());
		tar.setPlatformName(src.getPlatformName());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setStatus(src.getStatus());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setOperator(src.getOperator());	
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<CommodityProductUnitCompareDTO> toDTOs(List<CommodityProductUnitCompareVO> srcs) {
		if (srcs == null)
			return null;
		List<CommodityProductUnitCompareDTO> list = new ArrayList<CommodityProductUnitCompareDTO>();
		for (CommodityProductUnitCompareVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CommodityProductUnitCompareVO> toVO(List<CommodityProductUnitCompareDTO> srcs) {
		if (srcs == null)
			return null;
		List<CommodityProductUnitCompareVO> list = new ArrayList<CommodityProductUnitCompareVO>();
		for (CommodityProductUnitCompareDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CommodityProductUnitCompareDTO toDTO(CommodityProductUnitComparePO src) {
		if (src == null)
		return null;	
		CommodityProductUnitCompareDTO tar = new CommodityProductUnitCompareDTO();
		tar.setId(src.getId());
		tar.setPlatformName(src.getPlatformName());
		tar.setCreateTime(src.getCreateTime());
		tar.setStatus(src.getStatus());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setOperator(src.getOperator());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static CommodityProductUnitComparePO toPO(CommodityProductUnitCompareDTO src) {
		if (src == null)
		return null;	
		CommodityProductUnitComparePO tar = new CommodityProductUnitComparePO();
		tar.setId(src.getId());
		tar.setPlatformName(src.getPlatformName());
		tar.setCreateTime(src.getCreateTime());
		tar.setStatus(src.getStatus());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setOperator(src.getOperator());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<CommodityProductUnitCompareDTO> toDTO(List<CommodityProductUnitComparePO> srcs) {
		if (srcs == null)
			return null;
		List<CommodityProductUnitCompareDTO> list = new ArrayList<CommodityProductUnitCompareDTO>();
		for (CommodityProductUnitComparePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CommodityProductUnitComparePO> toPO(List<CommodityProductUnitCompareDTO> srcs) {
		if (srcs == null)
			return null;
		List<CommodityProductUnitComparePO> list = new ArrayList<CommodityProductUnitComparePO>();
		for (CommodityProductUnitCompareDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	