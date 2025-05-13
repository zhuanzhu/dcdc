package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.ProductDescriptionDTO;
import com.egeo.components.product.po.ProductDescriptionPO;
import com.egeo.components.product.vo.ProductDescriptionVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:26
 */
public class ProductDescriptionConverter {

	public static ProductDescriptionDTO toDTO(ProductDescriptionVO src) {
		ProductDescriptionDTO tar = new ProductDescriptionDTO();
		tar.setId(src.getId());
		tar.setProductId(src.getProductId());	
		tar.setContent(src.getContent());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static ProductDescriptionVO toVO(ProductDescriptionDTO src) {
		ProductDescriptionVO tar = new ProductDescriptionVO();
		tar.setId(src.getId());
		tar.setProductId(src.getProductId());		
		tar.setContent(src.getContent());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());		
		return tar;
	}

	public static List<ProductDescriptionDTO> toDTOs(List<ProductDescriptionVO> srcs) {
		if (srcs == null)
			return null;
		List<ProductDescriptionDTO> list = new ArrayList<ProductDescriptionDTO>();
		for (ProductDescriptionVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ProductDescriptionVO> toVO(List<ProductDescriptionDTO> srcs) {
		if (srcs == null)
			return null;
		List<ProductDescriptionVO> list = new ArrayList<ProductDescriptionVO>();
		for (ProductDescriptionDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static ProductDescriptionDTO toDTO(ProductDescriptionPO src) {
		ProductDescriptionDTO tar = new ProductDescriptionDTO();
		tar.setId(src.getId());
		tar.setProductId(src.getProductId());
		tar.setContent(src.getContent());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static ProductDescriptionPO toPO(ProductDescriptionDTO src) {
		ProductDescriptionPO tar = new ProductDescriptionPO();
		tar.setId(src.getId());
		tar.setProductId(src.getProductId());
		tar.setContent(src.getContent());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<ProductDescriptionDTO> toDTO(List<ProductDescriptionPO> srcs) {
		if (srcs == null)
			return null;
		List<ProductDescriptionDTO> list = new ArrayList<ProductDescriptionDTO>();
		for (ProductDescriptionPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ProductDescriptionPO> toPO(List<ProductDescriptionDTO> srcs) {
		if (srcs == null)
			return null;
		List<ProductDescriptionPO> list = new ArrayList<ProductDescriptionPO>();
		for (ProductDescriptionDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	