package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.ProductVideoDTO;
import com.egeo.components.product.po.ProductVideoPO;
import com.egeo.components.product.vo.ProductVideoVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:26
 */
public class ProductVideoConverter {
	
	public static ProductVideoDTO toDTO(ProductVideoVO src) {
		ProductVideoDTO tar = new ProductVideoDTO();
		tar.setId(src.getId());
		tar.setProductId(src.getProductId());	
		tar.setVideoId(src.getVideoId());	
		tar.setName(src.getName());	
		tar.setType(src.getType());	
		tar.setThumbnailUrl(src.getThumbnailUrl());	
		tar.setVideoUrl(src.getVideoUrl());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static ProductVideoVO toVO(ProductVideoDTO src) {
		ProductVideoVO tar = new ProductVideoVO();
		tar.setId(src.getId());
		tar.setProductId(src.getProductId());		
		tar.setVideoId(src.getVideoId());		
		tar.setName(src.getName());		
		tar.setType(src.getType());		
		tar.setThumbnailUrl(src.getThumbnailUrl());		
		tar.setVideoUrl(src.getVideoUrl());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());		
		return tar;
	}

	public static List<ProductVideoDTO> toDTOs(List<ProductVideoVO> srcs) {
		if (srcs == null)
			return null;
		List<ProductVideoDTO> list = new ArrayList<ProductVideoDTO>();
		for (ProductVideoVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ProductVideoVO> toVO(List<ProductVideoDTO> srcs) {
		if (srcs == null)
			return null;
		List<ProductVideoVO> list = new ArrayList<ProductVideoVO>();
		for (ProductVideoDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static ProductVideoDTO toDTO(ProductVideoPO src) {
		ProductVideoDTO tar = new ProductVideoDTO();
		tar.setId(src.getId());
		tar.setProductId(src.getProductId());
		tar.setVideoId(src.getVideoId());
		tar.setName(src.getName());
		tar.setType(src.getType());
		tar.setThumbnailUrl(src.getThumbnailUrl());
		tar.setVideoUrl(src.getVideoUrl());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static ProductVideoPO toPO(ProductVideoDTO src) {
		ProductVideoPO tar = new ProductVideoPO();
		tar.setId(src.getId());
		tar.setProductId(src.getProductId());
		tar.setVideoId(src.getVideoId());
		tar.setName(src.getName());
		tar.setType(src.getType());
		tar.setThumbnailUrl(src.getThumbnailUrl());
		tar.setVideoUrl(src.getVideoUrl());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<ProductVideoDTO> toDTO(List<ProductVideoPO> srcs) {
		if (srcs == null)
			return null;
		List<ProductVideoDTO> list = new ArrayList<ProductVideoDTO>();
		for (ProductVideoPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ProductVideoPO> toPO(List<ProductVideoDTO> srcs) {
		if (srcs == null)
			return null;
		List<ProductVideoPO> list = new ArrayList<ProductVideoPO>();
		for (ProductVideoDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	