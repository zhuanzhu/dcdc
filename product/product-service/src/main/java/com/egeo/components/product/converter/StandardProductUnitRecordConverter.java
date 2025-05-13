package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardProductUnitRecordDTO;
import com.egeo.components.product.po.StandardProductUnitRecordPO;
import com.egeo.components.product.vo.StandardProductUnitRecordVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-05 19:55:45
 */
public class StandardProductUnitRecordConverter {
	
	public static StandardProductUnitRecordDTO toDTO(StandardProductUnitRecordVO src) {
		if (src == null)
		return null;	
		StandardProductUnitRecordDTO tar = new StandardProductUnitRecordDTO();
		tar.setId(src.getId());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());	
		tar.setProductSerialNumber(src.getProductSerialNumber());	
		tar.setBrandId(src.getBrandId());	
		tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());	
		tar.setTitle(src.getTitle());	
		tar.setName(src.getName());	
		tar.setChineseName(src.getChineseName());	
		tar.setMarketPrice(src.getMarketPrice());	
		tar.setTaxNo(src.getTaxNo());	
		tar.setEanNo(src.getEanNo());	
		tar.setPlaceOfOrigin(src.getPlaceOfOrigin());	
		tar.setCalculationUnit(src.getCalculationUnit());	
		tar.setStatus(src.getStatus());	
		tar.setIsAvailable(src.getIsAvailable());	
		tar.setProductDetails(src.getProductDetails());	
		tar.setCreateUserid(src.getCreateUserid());	
		tar.setCreateUsername(src.getCreateUsername());	
		tar.setCreateUserip(src.getCreateUserip());	
		tar.setCreateUsermac(src.getCreateUsermac());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setProductCategory(src.getProductCategory());
		return tar;
	}

	public static StandardProductUnitRecordVO toVO(StandardProductUnitRecordDTO src) {
		if (src == null)
		return null;	
		StandardProductUnitRecordVO tar = new StandardProductUnitRecordVO();
		tar.setId(src.getId());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());	
		tar.setProductSerialNumber(src.getProductSerialNumber());	
		tar.setBrandId(src.getBrandId());	
		tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());	
		tar.setTitle(src.getTitle());	
		tar.setName(src.getName());	
		tar.setChineseName(src.getChineseName());	
		tar.setMarketPrice(src.getMarketPrice());	
		tar.setTaxNo(src.getTaxNo());	
		tar.setEanNo(src.getEanNo());	
		tar.setPlaceOfOrigin(src.getPlaceOfOrigin());	
		tar.setCalculationUnit(src.getCalculationUnit());	
		tar.setStatus(src.getStatus());	
		tar.setIsAvailable(src.getIsAvailable());	
		tar.setProductDetails(src.getProductDetails());	
		tar.setCreateUserid(src.getCreateUserid());	
		tar.setCreateUsername(src.getCreateUsername());	
		tar.setCreateUserip(src.getCreateUserip());	
		tar.setCreateUsermac(src.getCreateUsermac());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setProductCategory(src.getProductCategory());
		return tar;
	}

	public static List<StandardProductUnitRecordDTO> toDTOs(List<StandardProductUnitRecordVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitRecordDTO> list = new ArrayList<StandardProductUnitRecordDTO>();
		for (StandardProductUnitRecordVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardProductUnitRecordVO> toVO(List<StandardProductUnitRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitRecordVO> list = new ArrayList<StandardProductUnitRecordVO>();
		for (StandardProductUnitRecordDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static StandardProductUnitRecordDTO toDTO(StandardProductUnitRecordPO src) {
		if (src == null)
		return null;	
		StandardProductUnitRecordDTO tar = new StandardProductUnitRecordDTO();
		tar.setId(src.getId());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());
		tar.setProductSerialNumber(src.getProductSerialNumber());
		tar.setBrandId(src.getBrandId());
		tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());
		tar.setTitle(src.getTitle());
		tar.setName(src.getName());
		tar.setChineseName(src.getChineseName());
		tar.setMarketPrice(src.getMarketPrice());
		tar.setTaxNo(src.getTaxNo());
		tar.setEanNo(src.getEanNo());
		tar.setPlaceOfOrigin(src.getPlaceOfOrigin());
		tar.setCalculationUnit(src.getCalculationUnit());
		tar.setStatus(src.getStatus());
		tar.setIsAvailable(src.getIsAvailable());
		tar.setProductDetails(src.getProductDetails());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserip(src.getCreateUserip());
		tar.setCreateUsermac(src.getCreateUsermac());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setProductCategory(src.getProductCategory());
		return tar;
	}

	public static StandardProductUnitRecordPO toPO(StandardProductUnitRecordDTO src) {
		if (src == null)
		return null;	
		StandardProductUnitRecordPO tar = new StandardProductUnitRecordPO();
		tar.setId(src.getId());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());
		tar.setProductSerialNumber(src.getProductSerialNumber());
		tar.setBrandId(src.getBrandId());
		tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());
		tar.setTitle(src.getTitle());
		tar.setName(src.getName());
		tar.setChineseName(src.getChineseName());
		tar.setMarketPrice(src.getMarketPrice());
		tar.setTaxNo(src.getTaxNo());
		tar.setEanNo(src.getEanNo());
		tar.setPlaceOfOrigin(src.getPlaceOfOrigin());
		tar.setCalculationUnit(src.getCalculationUnit());
		tar.setStatus(src.getStatus());
		tar.setIsAvailable(src.getIsAvailable());
		tar.setProductDetails(src.getProductDetails());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserip(src.getCreateUserip());
		tar.setCreateUsermac(src.getCreateUsermac());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setProductCategory(src.getProductCategory());
		return tar;
	}

	public static List<StandardProductUnitRecordDTO> toDTO(List<StandardProductUnitRecordPO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitRecordDTO> list = new ArrayList<StandardProductUnitRecordDTO>();
		for (StandardProductUnitRecordPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardProductUnitRecordPO> toPO(List<StandardProductUnitRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitRecordPO> list = new ArrayList<StandardProductUnitRecordPO>();
		for (StandardProductUnitRecordDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	