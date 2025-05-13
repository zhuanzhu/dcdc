package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MerchantSeriesProductAttDTO;
import com.egeo.components.product.po.MerchantSeriesProductAttPO;
import com.egeo.components.product.vo.MerchantSeriesProductAttVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class MerchantSeriesProductAttConverter {
	
	public static MerchantSeriesProductAttDTO toDTO(MerchantSeriesProductAttVO src) {
		MerchantSeriesProductAttDTO tar = new MerchantSeriesProductAttDTO();
		tar.setId(src.getId());
		tar.setMerchantSeriesId(src.getMerchantSeriesId());	
		tar.setAttNameId(src.getAttNameId());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static MerchantSeriesProductAttVO toVO(MerchantSeriesProductAttDTO src) {
		MerchantSeriesProductAttVO tar = new MerchantSeriesProductAttVO();
		tar.setId(src.getId());
		tar.setMerchantSeriesId(src.getMerchantSeriesId());		
		tar.setAttNameId(src.getAttNameId());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());		
		return tar;
	}

	public static List<MerchantSeriesProductAttDTO> toDTOs(List<MerchantSeriesProductAttVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantSeriesProductAttDTO> list = new ArrayList<MerchantSeriesProductAttDTO>();
		for (MerchantSeriesProductAttVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantSeriesProductAttVO> toVO(List<MerchantSeriesProductAttDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantSeriesProductAttVO> list = new ArrayList<MerchantSeriesProductAttVO>();
		for (MerchantSeriesProductAttDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MerchantSeriesProductAttDTO toDTO(MerchantSeriesProductAttPO src) {
		MerchantSeriesProductAttDTO tar = new MerchantSeriesProductAttDTO();
		tar.setId(src.getId());
		tar.setMerchantSeriesId(src.getMerchantSeriesId());
		tar.setAttNameId(src.getAttNameId());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static MerchantSeriesProductAttPO toPO(MerchantSeriesProductAttDTO src) {
		MerchantSeriesProductAttPO tar = new MerchantSeriesProductAttPO();
		tar.setId(src.getId());
		tar.setMerchantSeriesId(src.getMerchantSeriesId());
		tar.setAttNameId(src.getAttNameId());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<MerchantSeriesProductAttDTO> toDTO(List<MerchantSeriesProductAttPO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantSeriesProductAttDTO> list = new ArrayList<MerchantSeriesProductAttDTO>();
		for (MerchantSeriesProductAttPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantSeriesProductAttPO> toPO(List<MerchantSeriesProductAttDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantSeriesProductAttPO> list = new ArrayList<MerchantSeriesProductAttPO>();
		for (MerchantSeriesProductAttDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	