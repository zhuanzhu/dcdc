package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MerchantProdPictureDTO;
import com.egeo.components.product.po.MerchantProdPicturePO;
import com.egeo.components.product.vo.MerchantProdPictureVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-06 09:20:45
 */
public class MerchantProdPictureConverter {
	
	public static MerchantProdPictureDTO toDTO(MerchantProdPictureVO src) {
		if (src == null)
		return null;	
		MerchantProdPictureDTO tar = new MerchantProdPictureDTO();
		tar.setId(src.getId());
		tar.setMerchantPictureId(src.getMerchantPictureId());	
		tar.setType(src.getType());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setMerchantProdId(src.getMerchantProdId());	
		return tar;
	}

	public static MerchantProdPictureVO toVO(MerchantProdPictureDTO src) {
		if (src == null)
		return null;	
		MerchantProdPictureVO tar = new MerchantProdPictureVO();
		tar.setId(src.getId());
		tar.setMerchantPictureId(src.getMerchantPictureId());	
		tar.setType(src.getType());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setMerchantProdId(src.getMerchantProdId());	
		return tar;
	}

	public static List<MerchantProdPictureDTO> toDTOs(List<MerchantProdPictureVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdPictureDTO> list = new ArrayList<MerchantProdPictureDTO>();
		for (MerchantProdPictureVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProdPictureVO> toVO(List<MerchantProdPictureDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdPictureVO> list = new ArrayList<MerchantProdPictureVO>();
		for (MerchantProdPictureDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MerchantProdPictureDTO toDTO(MerchantProdPicturePO src) {
		if (src == null)
		return null;	
		MerchantProdPictureDTO tar = new MerchantProdPictureDTO();
		tar.setId(src.getId());
		tar.setMerchantPictureId(src.getMerchantPictureId());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setMerchantProdId(src.getMerchantProdId());
		return tar;
	}

	public static MerchantProdPicturePO toPO(MerchantProdPictureDTO src) {
		if (src == null)
		return null;	
		MerchantProdPicturePO tar = new MerchantProdPicturePO();
		tar.setId(src.getId());
		tar.setMerchantPictureId(src.getMerchantPictureId());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setMerchantProdId(src.getMerchantProdId());
		return tar;
	}

	public static List<MerchantProdPictureDTO> toDTO(List<MerchantProdPicturePO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdPictureDTO> list = new ArrayList<MerchantProdPictureDTO>();
		for (MerchantProdPicturePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProdPicturePO> toPO(List<MerchantProdPictureDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdPicturePO> list = new ArrayList<MerchantProdPicturePO>();
		for (MerchantProdPictureDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	