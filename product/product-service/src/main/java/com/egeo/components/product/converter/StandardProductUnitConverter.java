package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.components.product.po.StandardProductUnitPO;
import com.egeo.components.product.vo.StandardProductUnitVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-05 18:51:01
 */
public class StandardProductUnitConverter {
	
	public static StandardProductUnitDTO toDTO(StandardProductUnitVO src) {
		if (src == null)
		return null;	
		StandardProductUnitDTO tar = new StandardProductUnitDTO();
		tar.setId(src.getId());
		tar.setProductSerialNumber(src.getProductSerialNumber());	
		tar.setName(src.getName());	
		tar.setChineseName(src.getChineseName());	
		tar.setProductCategory(src.getProductCategory());
		tar.setSupplierId(src.getSupplierId());
		tar.setReferlink(src.getReferlink());
		tar.setExtend(src.getExtend());
		return tar;
	}

	public static StandardProductUnitVO toVO(StandardProductUnitDTO src) {
		if (src == null)
		return null;	
		StandardProductUnitVO tar = new StandardProductUnitVO();
		tar.setId(src.getId());
		tar.setProductSerialNumber(src.getProductSerialNumber());	
		tar.setName(src.getName());	
		tar.setChineseName(src.getChineseName());	
		tar.setProductCategory(src.getProductCategory());
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setSupplierId(src.getSupplierId());
		tar.setReferlink(src.getReferlink());
		tar.setExtend(src.getExtend());
		return tar;
	}

	public static List<StandardProductUnitDTO> toDTOs(List<StandardProductUnitVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitDTO> list = new ArrayList<StandardProductUnitDTO>();
		for (StandardProductUnitVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardProductUnitVO> toVO(List<StandardProductUnitDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitVO> list = new ArrayList<StandardProductUnitVO>();
		for (StandardProductUnitDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static StandardProductUnitDTO toDTO(StandardProductUnitPO src) {
		if (src == null)
		return null;	
		StandardProductUnitDTO tar = new StandardProductUnitDTO();
		tar.setId(src.getId());
		tar.setProductSerialNumber(src.getProductSerialNumber());
		tar.setBrandId(src.getBrandId());
		tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());
		tar.setTitle(src.getTitle());
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setName(src.getName());
		tar.setReferlink(src.getReferlink());
		tar.setExtend(src.getExtend());
		tar.setChineseName(src.getChineseName());
		tar.setMarketPrice(src.getMarketPrice());
		tar.setTaxNo(src.getTaxNo());
		tar.setEanNo(src.getEanNo());
		tar.setPlaceOfOrigin(src.getPlaceOfOrigin());
		tar.setCalculationUnit(src.getCalculationUnit());
		tar.setSupplierId(src.getSupplierId());
		tar.setStatus(src.getStatus());
		tar.setIsAvailable(src.getIsAvailable());
		tar.setProductDetails(src.getProductDetails());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setContent(src.getContent());
		tar.setProductCategory(src.getProductCategory());
		tar.setCommodityTemplateId(src.getCommodityTemplateId());
		return tar;
	}

	public static StandardProductUnitPO toPO(StandardProductUnitDTO src) {
		if (src == null)
		return null;	
		StandardProductUnitPO tar = new StandardProductUnitPO();
		tar.setId(src.getId());
		tar.setProductSerialNumber(src.getProductSerialNumber());
		tar.setBrandId(src.getBrandId());
		tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());
		tar.setTitle(src.getTitle());
		tar.setName(src.getName());
		tar.setChineseName(src.getChineseName());
		tar.setMarketPrice(src.getMarketPrice());
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setTaxNo(src.getTaxNo());
		tar.setEanNo(src.getEanNo());
		tar.setPlaceOfOrigin(src.getPlaceOfOrigin());
		tar.setReferlink(src.getReferlink());
		tar.setExtend(src.getExtend());
		tar.setCalculationUnit(src.getCalculationUnit());
		tar.setStatus(src.getStatus());
		tar.setIsAvailable(src.getIsAvailable());
		tar.setProductDetails(src.getProductDetails());
		tar.setSupplierId(src.getSupplierId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setContent(src.getContent());
		tar.setProductCategory(src.getProductCategory());
		tar.setCommodityTemplateId(src.getCommodityTemplateId());
		return tar;
	}

	public static List<StandardProductUnitDTO> toDTO(List<StandardProductUnitPO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitDTO> list = new ArrayList<StandardProductUnitDTO>();
		for (StandardProductUnitPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardProductUnitPO> toPO(List<StandardProductUnitDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitPO> list = new ArrayList<StandardProductUnitPO>();
		for (StandardProductUnitDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	