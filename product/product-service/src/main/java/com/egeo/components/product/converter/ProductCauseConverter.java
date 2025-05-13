package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.ProductCauseDTO;
import com.egeo.components.product.po.ProductCausePO;
import com.egeo.components.product.vo.ProductCauseVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:26
 */
public class ProductCauseConverter {

	public static ProductCauseDTO toDTO(ProductCauseVO src) {
		ProductCauseDTO tar = new ProductCauseDTO();
		tar.setId(src.getId());
		tar.setProductId(src.getProductId());	
		tar.setCause(src.getCause());	
		tar.setSortValue(src.getSortValue());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		return tar;
	}

	public static ProductCauseVO toVO(ProductCauseDTO src) {
		ProductCauseVO tar = new ProductCauseVO();
		tar.setId(src.getId());
		tar.setProductId(src.getProductId());		
		tar.setCause(src.getCause());		
		tar.setSortValue(src.getSortValue());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setCreateTime(src.getCreateTime());		
		return tar;
	}

	public static List<ProductCauseDTO> toDTOs(List<ProductCauseVO> srcs) {
		if (srcs == null)
			return null;
		List<ProductCauseDTO> list = new ArrayList<ProductCauseDTO>();
		for (ProductCauseVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ProductCauseVO> toVO(List<ProductCauseDTO> srcs) {
		if (srcs == null)
			return null;
		List<ProductCauseVO> list = new ArrayList<ProductCauseVO>();
		for (ProductCauseDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static ProductCauseDTO toDTO(ProductCausePO src) {
		ProductCauseDTO tar = new ProductCauseDTO();
		tar.setId(src.getId());
		tar.setProductId(src.getProductId());
		tar.setCause(src.getCause());
		tar.setSortValue(src.getSortValue());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static ProductCausePO toPO(ProductCauseDTO src) {
		ProductCausePO tar = new ProductCausePO();
		tar.setId(src.getId());
		tar.setProductId(src.getProductId());
		tar.setCause(src.getCause());
		tar.setSortValue(src.getSortValue());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<ProductCauseDTO> toDTO(List<ProductCausePO> srcs) {
		if (srcs == null)
			return null;
		List<ProductCauseDTO> list = new ArrayList<ProductCauseDTO>();
		for (ProductCausePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ProductCausePO> toPO(List<ProductCauseDTO> srcs) {
		if (srcs == null)
			return null;
		List<ProductCausePO> list = new ArrayList<ProductCausePO>();
		for (ProductCauseDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	