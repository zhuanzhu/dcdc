package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.CouponGroupDTO;
import com.egeo.components.promotion.po.CouponGroupPO;
import com.egeo.components.promotion.vo.CouponGroupVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author wyy
 * @date 2018-06-14 18:02:50
 */
public class CouponGroupConverter {

	public static CouponGroupDTO toDTO(CouponGroupVO src) {
		if (src == null)
		return null;	
		CouponGroupDTO tar = new CouponGroupDTO();
		tar.setId(src.getId());
		tar.setGroupName(src.getGroupName());	
		tar.setGroupDescp(src.getGroupDescp());	
		tar.setUpdateUser(src.getUpdateUser());
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setCouponList(src.getCouponList());
		tar.setPlatformId(src.getPlatformId());
		tar.setStoreList(src.getStoreList());
		return tar;
	}

	public static CouponGroupVO toVO(CouponGroupDTO src) {
		if (src == null)
		return null;	
		CouponGroupVO tar = new CouponGroupVO();
		tar.setId(src.getId());
		tar.setGroupName(src.getGroupName());	
		tar.setGroupDescp(src.getGroupDescp());	
		tar.setUpdateUser(src.getUpdateUser());
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCouponList(src.getCouponList());
		tar.setPlatformId(src.getPlatformId());
		tar.setStoreList(src.getStoreList());
		return tar;
	}

	public static List<CouponGroupDTO> toDTOs(List<CouponGroupVO> srcs) {
		if (srcs == null)
			return null;
		List<CouponGroupDTO> list = new ArrayList<CouponGroupDTO>();
		for (CouponGroupVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CouponGroupVO> toVO(List<CouponGroupDTO> srcs) {
		if (srcs == null)
			return null;
		List<CouponGroupVO> list = new ArrayList<CouponGroupVO>();
		for (CouponGroupDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CouponGroupDTO toDTO(CouponGroupPO src) {
		if (src == null)
		return null;	
		CouponGroupDTO tar = new CouponGroupDTO();
		tar.setId(src.getId());
		tar.setGroupName(src.getGroupName());
		tar.setGroupDescp(src.getGroupDescp());
		tar.setUpdateUser(src.getUpdateUser());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCouponList(src.getCouponList());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static CouponGroupPO toPO(CouponGroupDTO src) {
		if (src == null)
		return null;	
		CouponGroupPO tar = new CouponGroupPO();
		tar.setId(src.getId());
		tar.setGroupName(src.getGroupName());
		tar.setGroupDescp(src.getGroupDescp());
		tar.setUpdateUser(src.getUpdateUser());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCouponList(src.getCouponList());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<CouponGroupDTO> toDTO(List<CouponGroupPO> srcs) {
		if (srcs == null)
			return null;
		List<CouponGroupDTO> list = new ArrayList<CouponGroupDTO>();
		for (CouponGroupPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CouponGroupPO> toPO(List<CouponGroupDTO> srcs) {
		if (srcs == null)
			return null;
		List<CouponGroupPO> list = new ArrayList<CouponGroupPO>();
		for (CouponGroupDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	