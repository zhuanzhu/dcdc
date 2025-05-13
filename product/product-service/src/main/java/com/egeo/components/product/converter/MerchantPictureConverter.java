package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MerchantPictureDTO;
import com.egeo.components.product.po.MerchantPicturePO;
import com.egeo.components.product.vo.MerchantPictureVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-03 15:31:27
 */
public class MerchantPictureConverter {

	
	public static MerchantPictureDTO toDTO(MerchantPictureVO src) {
		if (src == null)
		return null;	
		MerchantPictureDTO tar = new MerchantPictureDTO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());	
		tar.setPictureId(src.getPictureId());	
		tar.setSortValue(src.getSortValue());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static MerchantPictureVO toVO(MerchantPictureDTO src) {
		if (src == null)
		return null;	
		MerchantPictureVO tar = new MerchantPictureVO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());	
		tar.setPictureId(src.getPictureId());	
		tar.setSortValue(src.getSortValue());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<MerchantPictureDTO> toDTOs(List<MerchantPictureVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantPictureDTO> list = new ArrayList<MerchantPictureDTO>();
		for (MerchantPictureVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantPictureVO> toVO(List<MerchantPictureDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantPictureVO> list = new ArrayList<MerchantPictureVO>();
		for (MerchantPictureDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MerchantPictureDTO toDTO(MerchantPicturePO src) {
		if (src == null)
		return null;	
		MerchantPictureDTO tar = new MerchantPictureDTO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());
		tar.setPictureId(src.getPictureId());
		tar.setSortValue(src.getSortValue());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static MerchantPicturePO toPO(MerchantPictureDTO src) {
		if (src == null)
		return null;	
		MerchantPicturePO tar = new MerchantPicturePO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());
		tar.setPictureId(src.getPictureId());
		tar.setSortValue(src.getSortValue());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<MerchantPictureDTO> toDTO(List<MerchantPicturePO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantPictureDTO> list = new ArrayList<MerchantPictureDTO>();
		for (MerchantPicturePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantPicturePO> toPO(List<MerchantPictureDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantPicturePO> list = new ArrayList<MerchantPicturePO>();
		for (MerchantPictureDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	