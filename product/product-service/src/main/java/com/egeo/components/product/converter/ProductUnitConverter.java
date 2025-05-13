package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.ProductUnitDTO;
import com.egeo.components.product.po.ProductUnitPO;
import com.egeo.components.product.vo.ProductUnitVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-07 15:35:31
 */
public class ProductUnitConverter {

	public static ProductUnitDTO toDTO(ProductUnitVO src) {
		if (src == null)
		return null;	
		ProductUnitDTO tar = new ProductUnitDTO();
		tar.setId(src.getId());
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());	
		tar.setSkuId(src.getSkuId());	
		tar.setMerchantProductId(src.getMerchantProductId());	
		tar.setName(src.getName());	
		tar.setRemark(src.getRemark());	
		tar.setSalePrice(src.getSalePrice());	
		tar.setStatus(src.getStatus());	
		tar.setIsVendible(src.getIsVendible());	
		tar.setCode(src.getCode());	
		tar.setPuPicUrl(src.getPuPicUrl());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setChecked(src.isChecked());
		tar.setDemoSalePrice(src.getDemoSalePrice());
		tar.setCompetingSalePrice(src.getCompetingSalePrice());
		return tar;
	}

	public static ProductUnitVO toVO(ProductUnitDTO src) {
		if (src == null)
		return null;	
		ProductUnitVO tar = new ProductUnitVO();
		tar.setId(src.getId());
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());	
		tar.setSkuId(src.getSkuId());	
		tar.setMerchantProductId(src.getMerchantProductId());	
		tar.setName(src.getName());	
		tar.setRemark(src.getRemark());	
		tar.setSalePrice(src.getSalePrice());	
		tar.setStatus(src.getStatus());	
		tar.setIsVendible(src.getIsVendible());	
		tar.setCode(src.getCode());	
		tar.setPuPicUrl(src.getPuPicUrl());	
		tar.setPlatformId(src.getPlatformId());
		tar.setChecked(src.isChecked());
		tar.setDemoSalePrice(src.getDemoSalePrice());
		tar.setCompetingSalePrice(src.getCompetingSalePrice());
		return tar;
	}

	public static List<ProductUnitDTO> toDTOs(List<ProductUnitVO> srcs) {
		if (srcs == null)
			return null;
		List<ProductUnitDTO> list = new ArrayList<ProductUnitDTO>();
		for (ProductUnitVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ProductUnitVO> toVO(List<ProductUnitDTO> srcs) {
		if (srcs == null)
			return null;
		List<ProductUnitVO> list = new ArrayList<ProductUnitVO>();
		for (ProductUnitDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static ProductUnitDTO toDTO(ProductUnitPO src) {
		if (src == null)
		return null;	
		ProductUnitDTO tar = new ProductUnitDTO();
		tar.setId(src.getId());
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());
		tar.setSkuId(src.getSkuId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setName(src.getName());
		tar.setRemark(src.getRemark());
		tar.setSalePrice(src.getSalePrice());
		tar.setStatus(src.getStatus());
		tar.setIsVendible(src.getIsVendible());
		tar.setCode(src.getCode());
		tar.setPuPicUrl(src.getPuPicUrl());
		tar.setPlatformId(src.getPlatformId());
		tar.setChecked(src.isChecked());
		tar.setDemoSalePrice(src.getDemoSalePrice());
		tar.setCompetingSalePrice(src.getCompetingSalePrice());
		return tar;
	}

	public static ProductUnitPO toPO(ProductUnitDTO src) {
		if (src == null)
		return null;	
		ProductUnitPO tar = new ProductUnitPO();
		tar.setId(src.getId());
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());
		tar.setSkuId(src.getSkuId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setName(src.getName());
		tar.setRemark(src.getRemark());
		tar.setSalePrice(src.getSalePrice());
		tar.setStatus(src.getStatus());
		tar.setIsVendible(src.getIsVendible());
		tar.setCode(src.getCode());
		tar.setPuPicUrl(src.getPuPicUrl());
		tar.setPlatformId(src.getPlatformId());
		tar.setChecked(src.isChecked());
		tar.setDemoSalePrice(src.getDemoSalePrice());
		tar.setCompetingSalePrice(src.getCompetingSalePrice());
		return tar;
	}

	public static List<ProductUnitDTO> toDTO(List<ProductUnitPO> srcs) {
		if (srcs == null)
			return null;
		List<ProductUnitDTO> list = new ArrayList<ProductUnitDTO>();
		for (ProductUnitPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ProductUnitPO> toPO(List<ProductUnitDTO> srcs) {
		if (srcs == null)
			return null;
		List<ProductUnitPO> list = new ArrayList<ProductUnitPO>();
		for (ProductUnitDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	