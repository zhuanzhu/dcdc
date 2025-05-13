package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.ProductAttNameDTO;
import com.egeo.components.product.po.ProductAttNamePO;
import com.egeo.components.product.vo.ProductAttNameVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:26
 */
public class ProductAttNameConverter {

	public static ProductAttNameDTO toDTO(ProductAttNameVO src) {
		ProductAttNameDTO tar = new ProductAttNameDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());	
		tar.setProductId(src.getProductId());	
		tar.setAttNameId(src.getAttNameId());	
		tar.setSortValue(src.getSortValue());	
		tar.setType(src.getType());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static ProductAttNameVO toVO(ProductAttNameDTO src) {
		ProductAttNameVO tar = new ProductAttNameVO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());		
		tar.setProductId(src.getProductId());		
		tar.setAttNameId(src.getAttNameId());		
		tar.setSortValue(src.getSortValue());		
		tar.setType(src.getType());	
		tar.setPlatformId(src.getPlatformId());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());		
		return tar;
	}

	public static List<ProductAttNameDTO> toDTOs(List<ProductAttNameVO> srcs) {
		if (srcs == null)
			return null;
		List<ProductAttNameDTO> list = new ArrayList<ProductAttNameDTO>();
		for (ProductAttNameVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ProductAttNameVO> toVO(List<ProductAttNameDTO> srcs) {
		if (srcs == null)
			return null;
		List<ProductAttNameVO> list = new ArrayList<ProductAttNameVO>();
		for (ProductAttNameDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static ProductAttNameDTO toDTO(ProductAttNamePO src) {
		ProductAttNameDTO tar = new ProductAttNameDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setProductId(src.getProductId());
		tar.setAttNameId(src.getAttNameId());
		tar.setSortValue(src.getSortValue());
		tar.setType(src.getType());	
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setShowPicture(src.getShowPicture());
		return tar;
	}

	public static ProductAttNamePO toPO(ProductAttNameDTO src) {
		ProductAttNamePO tar = new ProductAttNamePO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setProductId(src.getProductId());
		tar.setAttNameId(src.getAttNameId());
		tar.setSortValue(src.getSortValue());
		tar.setType(src.getType());	
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setShowPicture(src.getShowPicture());
		return tar;
	}

	public static List<ProductAttNameDTO> toDTO(List<ProductAttNamePO> srcs) {
		if (srcs == null)
			return null;
		List<ProductAttNameDTO> list = new ArrayList<ProductAttNameDTO>();
		for (ProductAttNamePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ProductAttNamePO> toPO(List<ProductAttNameDTO> srcs) {
		if (srcs == null)
			return null;
		List<ProductAttNamePO> list = new ArrayList<ProductAttNamePO>();
		for (ProductAttNameDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	