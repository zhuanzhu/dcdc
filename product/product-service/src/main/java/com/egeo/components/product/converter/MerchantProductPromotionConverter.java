package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MerchantProductPromotionDTO;
import com.egeo.components.product.po.MerchantProductPromotionPO;
import com.egeo.components.product.vo.MerchantProductPromotionVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-24 16:09:32
 */
public class MerchantProductPromotionConverter {
	
	public static MerchantProductPromotionDTO toDTO(MerchantProductPromotionVO src) {
		MerchantProductPromotionDTO tar = new MerchantProductPromotionDTO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());			
		tar.setProductId(src.getProductId());			
		tar.setSkuId(src.getSkuId());			
		tar.setMerchantId(src.getMerchantId());			
		tar.setPromotionId(src.getPromotionId());			
		tar.setPromotionType(src.getPromotionType());			
		tar.setPromotionPrice(src.getPromotionPrice());			
		tar.setPromotionStartTime(src.getPromotionStartTime());			
		tar.setPromotionEndTime(src.getPromotionEndTime());			
		tar.setCreateTime(src.getCreateTime());			
		tar.setUpdateTime(src.getUpdateTime());			
		tar.setPlatformId(src.getPlatformId());			
		return tar;
	}

	public static MerchantProductPromotionVO toVO(MerchantProductPromotionDTO src) {
		MerchantProductPromotionVO tar = new MerchantProductPromotionVO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setProductId(src.getProductId());
		tar.setSkuId(src.getSkuId());
		tar.setMerchantId(src.getMerchantId());
		tar.setPromotionId(src.getPromotionId());
		tar.setPromotionType(src.getPromotionType());
		tar.setPromotionPrice(src.getPromotionPrice());
		tar.setPromotionStartTime(src.getPromotionStartTime());
		tar.setPromotionEndTime(src.getPromotionEndTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<MerchantProductPromotionDTO> toDTOs(List<MerchantProductPromotionVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductPromotionDTO> list = new ArrayList<MerchantProductPromotionDTO>();
		for (MerchantProductPromotionVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProductPromotionVO> toVO(List<MerchantProductPromotionDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductPromotionVO> list = new ArrayList<MerchantProductPromotionVO>();
		for (MerchantProductPromotionDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MerchantProductPromotionDTO toDTO(MerchantProductPromotionPO src) {
		MerchantProductPromotionDTO tar = new MerchantProductPromotionDTO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setProductId(src.getProductId());
		tar.setSkuId(src.getSkuId());
		tar.setMerchantId(src.getMerchantId());
		tar.setPromotionId(src.getPromotionId());
		tar.setPromotionType(src.getPromotionType());
		tar.setPromotionPrice(src.getPromotionPrice());
		tar.setPromotionStartTime(src.getPromotionStartTime());
		tar.setPromotionEndTime(src.getPromotionEndTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static MerchantProductPromotionPO toPO(MerchantProductPromotionDTO src) {
		MerchantProductPromotionPO tar = new MerchantProductPromotionPO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setProductId(src.getProductId());
		tar.setSkuId(src.getSkuId());
		tar.setMerchantId(src.getMerchantId());
		tar.setPromotionId(src.getPromotionId());
		tar.setPromotionType(src.getPromotionType());
		tar.setPromotionPrice(src.getPromotionPrice());
		tar.setPromotionStartTime(src.getPromotionStartTime());
		tar.setPromotionEndTime(src.getPromotionEndTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<MerchantProductPromotionDTO> toDTO(List<MerchantProductPromotionPO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductPromotionDTO> list = new ArrayList<MerchantProductPromotionDTO>();
		for (MerchantProductPromotionPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProductPromotionPO> toPO(List<MerchantProductPromotionDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductPromotionPO> list = new ArrayList<MerchantProductPromotionPO>();
		for (MerchantProductPromotionDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	