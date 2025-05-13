package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.CouponGroupStoreDTO;
import com.egeo.components.promotion.po.CouponGroupStorePO;
import com.egeo.components.promotion.vo.CouponGroupStoreVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author paul
 * @date 2018-09-18 11:29:53
 */
public class CouponGroupStoreConverter {

	public static CouponGroupStoreDTO toDTO(CouponGroupStoreVO src) {
		if (src == null)
		return null;	
		CouponGroupStoreDTO tar = new CouponGroupStoreDTO();
		tar.setId(src.getId());
		tar.setCouponGroupId(src.getCouponGroupId());	
		tar.setStoreId(src.getStoreId());	
		tar.setCreateTime(src.getCreateTime());	
		return tar;
	}

	public static CouponGroupStoreVO toVO(CouponGroupStoreDTO src) {
		if (src == null)
		return null;	
		CouponGroupStoreVO tar = new CouponGroupStoreVO();
		tar.setId(src.getId());
		tar.setCouponGroupId(src.getCouponGroupId());	
		tar.setStoreId(src.getStoreId());	
		tar.setCreateTime(src.getCreateTime());	
		return tar;
	}

	public static List<CouponGroupStoreDTO> toDTOs(List<CouponGroupStoreVO> srcs) {
		if (srcs == null)
			return null;
		List<CouponGroupStoreDTO> list = new ArrayList<CouponGroupStoreDTO>();
		for (CouponGroupStoreVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CouponGroupStoreVO> toVO(List<CouponGroupStoreDTO> srcs) {
		if (srcs == null)
			return null;
		List<CouponGroupStoreVO> list = new ArrayList<CouponGroupStoreVO>();
		for (CouponGroupStoreDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CouponGroupStoreDTO toDTO(CouponGroupStorePO src) {
		if (src == null)
		return null;	
		CouponGroupStoreDTO tar = new CouponGroupStoreDTO();
		tar.setId(src.getId());
		tar.setCouponGroupId(src.getCouponGroupId());
		tar.setStoreId(src.getStoreId());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static CouponGroupStorePO toPO(CouponGroupStoreDTO src) {
		if (src == null)
		return null;	
		CouponGroupStorePO tar = new CouponGroupStorePO();
		tar.setId(src.getId());
		tar.setCouponGroupId(src.getCouponGroupId());
		tar.setStoreId(src.getStoreId());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<CouponGroupStoreDTO> toDTO(List<CouponGroupStorePO> srcs) {
		if (srcs == null)
			return null;
		List<CouponGroupStoreDTO> list = new ArrayList<CouponGroupStoreDTO>();
		for (CouponGroupStorePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CouponGroupStorePO> toPO(List<CouponGroupStoreDTO> srcs) {
		if (srcs == null)
			return null;
		List<CouponGroupStorePO> list = new ArrayList<CouponGroupStorePO>();
		for (CouponGroupStoreDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	