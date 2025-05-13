package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.ProductAttValueDTO;
import com.egeo.components.product.po.ProductAttValuePO;
import com.egeo.components.product.vo.ProductAttValueVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:26
 */
public class ProductAttValueConverter {

	public static ProductAttValueDTO toDTO(ProductAttValueVO src) {
		ProductAttValueDTO tar = new ProductAttValueDTO();
		tar.setId(src.getId());
		tar.setProductAttNameId(src.getProductAttNameId());	
		tar.setAttValueId(src.getAttValueId());	
		tar.setAttValueCustom(src.getAttValueCustom());	
		tar.setSortValue(src.getSortValue());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setPictureUrl(src.getPictureUrl());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static ProductAttValueVO toVO(ProductAttValueDTO src) {
		ProductAttValueVO tar = new ProductAttValueVO();
		tar.setId(src.getId());
		tar.setProductAttNameId(src.getProductAttNameId());		
		tar.setAttValueId(src.getAttValueId());		
		tar.setAttValueCustom(src.getAttValueCustom());		
		tar.setSortValue(src.getSortValue());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setPictureUrl(src.getPictureUrl());	
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());		
		return tar;
	}

	public static List<ProductAttValueDTO> toDTOs(List<ProductAttValueVO> srcs) {
		if (srcs == null)
			return null;
		List<ProductAttValueDTO> list = new ArrayList<ProductAttValueDTO>();
		for (ProductAttValueVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ProductAttValueVO> toVO(List<ProductAttValueDTO> srcs) {
		if (srcs == null)
			return null;
		List<ProductAttValueVO> list = new ArrayList<ProductAttValueVO>();
		for (ProductAttValueDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static ProductAttValueDTO toDTO(ProductAttValuePO src) {
		ProductAttValueDTO tar = new ProductAttValueDTO();
		tar.setId(src.getId());
		tar.setProductAttNameId(src.getProductAttNameId());
		tar.setAttValueId(src.getAttValueId());
		tar.setAttValueCustom(src.getAttValueCustom());
		tar.setSortValue(src.getSortValue());
		tar.setPictureUrl(src.getPictureUrl());	
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static ProductAttValuePO toPO(ProductAttValueDTO src) {
		ProductAttValuePO tar = new ProductAttValuePO();
		tar.setId(src.getId());
		tar.setProductAttNameId(src.getProductAttNameId());
		tar.setAttValueId(src.getAttValueId());
		tar.setAttValueCustom(src.getAttValueCustom());
		tar.setSortValue(src.getSortValue());
		tar.setPlatformId(src.getPlatformId());
		tar.setPictureUrl(src.getPictureUrl());	
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<ProductAttValueDTO> toDTO(List<ProductAttValuePO> srcs) {
		if (srcs == null)
			return null;
		List<ProductAttValueDTO> list = new ArrayList<ProductAttValueDTO>();
		for (ProductAttValuePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ProductAttValuePO> toPO(List<ProductAttValueDTO> srcs) {
		if (srcs == null)
			return null;
		List<ProductAttValuePO> list = new ArrayList<ProductAttValuePO>();
		for (ProductAttValueDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	