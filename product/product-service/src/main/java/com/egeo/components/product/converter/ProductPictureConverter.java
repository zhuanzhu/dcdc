package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.ProductPictureDTO;
import com.egeo.components.product.po.ProductPicturePO;
import com.egeo.components.product.vo.ProductPictureVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-06 13:26:51
 */
public class ProductPictureConverter {

	public static ProductPictureDTO toDTO(ProductPictureVO src) {
		if (src == null)
		return null;	
		ProductPictureDTO tar = new ProductPictureDTO();
		tar.setId(src.getId());
		tar.setProductId(src.getProductId());	
		tar.setType(src.getType());	
		tar.setPictureId(src.getPictureId());	
		tar.setSortValue(src.getSortValue());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static ProductPictureVO toVO(ProductPictureDTO src) {
		if (src == null)
		return null;	
		ProductPictureVO tar = new ProductPictureVO();
		tar.setId(src.getId());
		tar.setProductId(src.getProductId());	
		tar.setType(src.getType());	
		tar.setPictureId(src.getPictureId());	
		tar.setSortValue(src.getSortValue());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<ProductPictureDTO> toDTOs(List<ProductPictureVO> srcs) {
		if (srcs == null)
			return null;
		List<ProductPictureDTO> list = new ArrayList<ProductPictureDTO>();
		for (ProductPictureVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ProductPictureVO> toVO(List<ProductPictureDTO> srcs) {
		if (srcs == null)
			return null;
		List<ProductPictureVO> list = new ArrayList<ProductPictureVO>();
		for (ProductPictureDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static ProductPictureDTO toDTO(ProductPicturePO src) {
		if (src == null)
		return null;	
		ProductPictureDTO tar = new ProductPictureDTO();
		tar.setId(src.getId());
		tar.setProductId(src.getProductId());
		tar.setType(src.getType());
		tar.setPictureId(src.getPictureId());
		tar.setSortValue(src.getSortValue());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static ProductPicturePO toPO(ProductPictureDTO src) {
		if (src == null)
		return null;	
		ProductPicturePO tar = new ProductPicturePO();
		tar.setId(src.getId());
		tar.setProductId(src.getProductId());
		tar.setType(src.getType());
		tar.setPictureId(src.getPictureId());
		tar.setSortValue(src.getSortValue());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<ProductPictureDTO> toDTO(List<ProductPicturePO> srcs) {
		if (srcs == null)
			return null;
		List<ProductPictureDTO> list = new ArrayList<ProductPictureDTO>();
		for (ProductPicturePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ProductPicturePO> toPO(List<ProductPictureDTO> srcs) {
		if (srcs == null)
			return null;
		List<ProductPicturePO> list = new ArrayList<ProductPicturePO>();
		for (ProductPictureDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	