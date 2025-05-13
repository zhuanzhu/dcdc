package com.egeo.components.stock.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.stock.dto.StoreStockChangeApplyPictureDTO;
import com.egeo.components.stock.po.StoreStockChangeApplyPicturePO;
import com.egeo.components.stock.vo.StoreStockChangeApplyPictureVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-09-13 15:30:19
 */
public class StoreStockChangeApplyPictureConverter {

	
	public static StoreStockChangeApplyPictureDTO toDTO(StoreStockChangeApplyPictureVO src) {
		if (src == null)
		return null;	
		StoreStockChangeApplyPictureDTO tar = new StoreStockChangeApplyPictureDTO();
		tar.setId(src.getId());
		tar.setStoreStockChangeApplyId(src.getStoreStockChangeApplyId());	
		tar.setPicturePath(src.getPicturePath());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static StoreStockChangeApplyPictureVO toVO(StoreStockChangeApplyPictureDTO src) {
		if (src == null)
		return null;	
		StoreStockChangeApplyPictureVO tar = new StoreStockChangeApplyPictureVO();
		tar.setId(src.getId());
		tar.setStoreStockChangeApplyId(src.getStoreStockChangeApplyId());	
		tar.setPicturePath(src.getPicturePath());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<StoreStockChangeApplyPictureDTO> toDTOs(List<StoreStockChangeApplyPictureVO> srcs) {
		if (srcs == null)
			return null;
		List<StoreStockChangeApplyPictureDTO> list = new ArrayList<StoreStockChangeApplyPictureDTO>();
		for (StoreStockChangeApplyPictureVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StoreStockChangeApplyPictureVO> toVO(List<StoreStockChangeApplyPictureDTO> srcs) {
		if (srcs == null)
			return null;
		List<StoreStockChangeApplyPictureVO> list = new ArrayList<StoreStockChangeApplyPictureVO>();
		for (StoreStockChangeApplyPictureDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static StoreStockChangeApplyPictureDTO toDTO(StoreStockChangeApplyPicturePO src) {
		if (src == null)
		return null;	
		StoreStockChangeApplyPictureDTO tar = new StoreStockChangeApplyPictureDTO();
		tar.setId(src.getId());
		tar.setStoreStockChangeApplyId(src.getStoreStockChangeApplyId());
		tar.setPicturePath(src.getPicturePath());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static StoreStockChangeApplyPicturePO toPO(StoreStockChangeApplyPictureDTO src) {
		if (src == null)
		return null;	
		StoreStockChangeApplyPicturePO tar = new StoreStockChangeApplyPicturePO();
		tar.setId(src.getId());
		tar.setStoreStockChangeApplyId(src.getStoreStockChangeApplyId());
		tar.setPicturePath(src.getPicturePath());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<StoreStockChangeApplyPictureDTO> toDTO(List<StoreStockChangeApplyPicturePO> srcs) {
		if (srcs == null)
			return null;
		List<StoreStockChangeApplyPictureDTO> list = new ArrayList<StoreStockChangeApplyPictureDTO>();
		for (StoreStockChangeApplyPicturePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StoreStockChangeApplyPicturePO> toPO(List<StoreStockChangeApplyPictureDTO> srcs) {
		if (srcs == null)
			return null;
		List<StoreStockChangeApplyPicturePO> list = new ArrayList<StoreStockChangeApplyPicturePO>();
		for (StoreStockChangeApplyPictureDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	