package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.ProductCateTreeNodeDTO;
import com.egeo.components.product.po.ProductCateTreeNodePO;
import com.egeo.components.product.vo.ProductCateTreeNodeVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:26
 */
public class ProductCateTreeNodeConverter {
	
	public static ProductCateTreeNodeDTO toDTO(ProductCateTreeNodeVO src) {
		ProductCateTreeNodeDTO tar = new ProductCateTreeNodeDTO();
		tar.setId(src.getId());
		tar.setProductId(src.getProductId());	
		tar.setCateTreeNodeId(src.getCateTreeNodeId());	
		tar.setSortValue(src.getSortValue());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static ProductCateTreeNodeVO toVO(ProductCateTreeNodeDTO src) {
		ProductCateTreeNodeVO tar = new ProductCateTreeNodeVO();
		tar.setId(src.getId());
		tar.setProductId(src.getProductId());		
		tar.setCateTreeNodeId(src.getCateTreeNodeId());		
		tar.setSortValue(src.getSortValue());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());		
		return tar;
	}

	public static List<ProductCateTreeNodeDTO> toDTOs(List<ProductCateTreeNodeVO> srcs) {
		if (srcs == null)
			return null;
		List<ProductCateTreeNodeDTO> list = new ArrayList<ProductCateTreeNodeDTO>();
		for (ProductCateTreeNodeVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ProductCateTreeNodeVO> toVO(List<ProductCateTreeNodeDTO> srcs) {
		if (srcs == null)
			return null;
		List<ProductCateTreeNodeVO> list = new ArrayList<ProductCateTreeNodeVO>();
		for (ProductCateTreeNodeDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static ProductCateTreeNodeDTO toDTO(ProductCateTreeNodePO src) {
		ProductCateTreeNodeDTO tar = new ProductCateTreeNodeDTO();
		tar.setId(src.getId());
		tar.setProductId(src.getProductId());
		tar.setCateTreeNodeId(src.getCateTreeNodeId());
		tar.setSortValue(src.getSortValue());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static ProductCateTreeNodePO toPO(ProductCateTreeNodeDTO src) {
		ProductCateTreeNodePO tar = new ProductCateTreeNodePO();
		tar.setId(src.getId());
		tar.setProductId(src.getProductId());
		tar.setCateTreeNodeId(src.getCateTreeNodeId());
		tar.setSortValue(src.getSortValue());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<ProductCateTreeNodeDTO> toDTO(List<ProductCateTreeNodePO> srcs) {
		if (srcs == null)
			return null;
		List<ProductCateTreeNodeDTO> list = new ArrayList<ProductCateTreeNodeDTO>();
		for (ProductCateTreeNodePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ProductCateTreeNodePO> toPO(List<ProductCateTreeNodeDTO> srcs) {
		if (srcs == null)
			return null;
		List<ProductCateTreeNodePO> list = new ArrayList<ProductCateTreeNodePO>();
		for (ProductCateTreeNodeDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	