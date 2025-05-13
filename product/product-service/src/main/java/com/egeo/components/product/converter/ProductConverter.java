package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.ProductDTO;
import com.egeo.components.product.po.ProductPO;
import com.egeo.components.product.vo.ProductVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 16:11:33
 */
public class ProductConverter {

	public static ProductDTO toDTO(ProductVO src) {
		ProductDTO tar = new ProductDTO();
		tar.setId(src.getId());
		tar.setBrandId(src.getBrandId());	
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());	
		tar.setName(src.getName());	
		tar.setChineseName(src.getChineseName());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setStatus(src.getStatus());	
		tar.setProductDetails(src.getProductDetails());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setBeginTime(src.getBeginTime());
		tar.setFinishTime(src.getFinishTime());
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setReferlink(src.getReferlink());
		tar.setExtend(src.getExtend());
		tar.setCategoryId(src.getCategoryId());
		tar.setCause(src.getCause());
		tar.setProductSerialNumber(src.getProductSerialNumber());
		tar.setProductCategory(src.getProductCategory());
		tar.setIsAvailable(src.getIsAvailable());
		tar.setPlatformId(src.getPlatformId());
		tar.setCommodityTemplateId(src.getCommodityTemplateId());
		tar.setPriceDetail(src.getPriceDetail());
		tar.setPrecautiousLineIds(src.getPrecautiousLineIds());
		tar.setPrecautiousStart(src.getPrecautiousStart());
		tar.setPrecautiousEnd(src.getPrecautiousEnd());
		tar.setSupplierId(src.getSupplierId());
		return tar;
	}

	public static ProductVO toVO(ProductDTO src) {
		ProductVO tar = new ProductVO();
		tar.setId(src.getId());
		tar.setBrandId(src.getBrandId());		
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());		
		tar.setName(src.getName());		
		tar.setChineseName(src.getChineseName());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setStatus(src.getStatus());		
		tar.setProductDetails(src.getProductDetails());		
		tar.setCreateTime(src.getCreateTime());	
		tar.setBeginTime(src.getBeginTime());
		tar.setPriceDetail(src.getPriceDetail());
		tar.setFinishTime(src.getFinishTime());
		tar.setUpdateTime(src.getUpdateTime());		
		tar.setCategoryId(src.getCategoryId());
		tar.setCause(src.getCause());
		tar.setReferlink(src.getReferlink());
		tar.setExtend(src.getExtend());
		tar.setProductSerialNumber(src.getProductSerialNumber());
		tar.setProductCategory(src.getProductCategory());
		tar.setIsAvailable(src.getIsAvailable());
		tar.setPlatformId(src.getPlatformId());
		tar.setCommodityTemplateId(src.getCommodityTemplateId());
		tar.setSupplierId(src.getSupplierId());
		return tar;
	}

	public static List<ProductDTO> toDTOs(List<ProductVO> srcs) {
		if (srcs == null)
			return null;
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		for (ProductVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ProductVO> toVO(List<ProductDTO> srcs) {
		if (srcs == null)
			return null;
		List<ProductVO> list = new ArrayList<ProductVO>();
		for (ProductDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static ProductDTO toDTO(ProductPO src) {
		ProductDTO tar = new ProductDTO();
		tar.setId(src.getId());
		tar.setBrandId(src.getBrandId());
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());
		tar.setTitle(src.getTitle());
		tar.setName(src.getName());
		tar.setChineseName(src.getChineseName());
		tar.setTaxNo(src.getTaxNo());
		tar.setEanNo(src.getEanNo());
		tar.setPlaceOfOrigin(src.getPlaceOfOrigin());
		tar.setCalculationUnit(src.getCalculationUnit());
		tar.setPlatformId(src.getPlatformId());
		tar.setStatus(src.getStatus());
		tar.setReferlink(src.getReferlink());
		tar.setExtend(src.getExtend());
		tar.setProductDetails(src.getProductDetails());
		tar.setCreateTime(src.getCreateTime());
		tar.setBeginTime(src.getBeginTime());
		tar.setFinishTime(src.getFinishTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setIds(src.getIds());
		tar.setProductSerialNumber(src.getProductSerialNumber());
		tar.setMarketPrice(src.getMarketPrice());
		tar.setIsAvailable(src.getIsAvailable());
		tar.setProductCategory(src.getProductCategory());
		tar.setIsAvailable(src.getIsAvailable());
		tar.setCategoryId(src.getCategoryId());
		tar.setContent(src.getContent());
		tar.setPriceDetail(src.getPriceDetail());
		tar.setCommodityTemplateId(src.getCommodityTemplateId());
		tar.setPrecautiousLineDays(src.getPrecautiousLineDays());
		tar.setSupplierId(src.getSupplierId());
		return tar;
	}

	public static ProductPO toPO(ProductDTO src) {
		ProductPO tar = new ProductPO();
		tar.setId(src.getId());
		tar.setBrandId(src.getBrandId());
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());
		tar.setTitle(src.getTitle());
		tar.setName(src.getName());
		tar.setChineseName(src.getChineseName());
		tar.setTaxNo(src.getTaxNo());
		tar.setEanNo(src.getEanNo());
		tar.setPlaceOfOrigin(src.getPlaceOfOrigin());
		tar.setCalculationUnit(src.getCalculationUnit());
		tar.setPlatformId(src.getPlatformId());
		tar.setStatus(src.getStatus());
		tar.setReferlink(src.getReferlink());
		tar.setExtend(src.getExtend());
		tar.setProductDetails(src.getProductDetails());
		tar.setCreateTime(src.getCreateTime());
		tar.setBeginTime(src.getBeginTime());
		tar.setFinishTime(src.getFinishTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setIds(src.getIds());
		tar.setProductSerialNumber(src.getProductSerialNumber());
		tar.setMarketPrice(src.getMarketPrice());
		tar.setIsAvailable(src.getIsAvailable());
		tar.setProductCategory(src.getProductCategory());
		tar.setIsAvailable(src.getIsAvailable());
		tar.setCategoryId(src.getCategoryId());
		tar.setPriceDetail(src.getPriceDetail());
		tar.setContent(src.getContent());
		tar.setCommodityTemplateId(src.getCommodityTemplateId());
		tar.setPrecautiousStart(src.getPrecautiousStart());
		tar.setPrecautiousEnd(src.getPrecautiousEnd());
		tar.setSupplierId(src.getSupplierId());
		return tar;
	}

	public static List<ProductDTO> toDTO(List<ProductPO> srcs) {
		if (srcs == null)
			return null;
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		for (ProductPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ProductPO> toPO(List<ProductDTO> srcs) {
		if (srcs == null)
			return null;
		List<ProductPO> list = new ArrayList<ProductPO>();
		for (ProductDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	