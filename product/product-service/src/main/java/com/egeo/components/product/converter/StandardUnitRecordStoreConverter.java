package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardUnitRecordStoreDTO;
import com.egeo.components.product.po.StandardUnitRecordStorePO;
import com.egeo.components.product.vo.StandardUnitRecordStoreVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-09-11 14:59:34
 */
public class StandardUnitRecordStoreConverter {
	
	public static StandardUnitRecordStoreDTO toDTO(StandardUnitRecordStorePO src) {
		if (src == null)
		return null;	
		StandardUnitRecordStoreDTO tar = new StandardUnitRecordStoreDTO();
		tar.setId(src.getId());
		tar.setStoreId(src.getStoreId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static StandardUnitRecordStorePO toPO(StandardUnitRecordStoreDTO src) {
		if (src == null)
		return null;	
		StandardUnitRecordStorePO tar = new StandardUnitRecordStorePO();
		tar.setId(src.getId());
		tar.setStoreId(src.getStoreId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<StandardUnitRecordStoreDTO> toDTO(List<StandardUnitRecordStorePO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitRecordStoreDTO> list = new ArrayList<StandardUnitRecordStoreDTO>();
		for (StandardUnitRecordStorePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitRecordStorePO> toPO(List<StandardUnitRecordStoreDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitRecordStorePO> list = new ArrayList<StandardUnitRecordStorePO>();
		for (StandardUnitRecordStoreDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static StandardUnitRecordStoreDTO toDTO(StandardUnitRecordStoreVO src) {
		if (src == null)
		return null;	
		StandardUnitRecordStoreDTO tar = new StandardUnitRecordStoreDTO();
		tar.setId(src.getId());
		tar.setStoreId(src.getStoreId());	
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static StandardUnitRecordStoreVO toVO(StandardUnitRecordStoreDTO src) {
		if (src == null)
		return null;	
		StandardUnitRecordStoreVO tar = new StandardUnitRecordStoreVO();
		tar.setId(src.getId());
		tar.setStoreId(src.getStoreId());	
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<StandardUnitRecordStoreDTO> toDTOs(List<StandardUnitRecordStoreVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitRecordStoreDTO> list = new ArrayList<StandardUnitRecordStoreDTO>();
		for (StandardUnitRecordStoreVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitRecordStoreVO> toVO(List<StandardUnitRecordStoreDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitRecordStoreVO> list = new ArrayList<StandardUnitRecordStoreVO>();
		for (StandardUnitRecordStoreDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	