package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.StoreAdminDTO;
import com.egeo.components.user.po.StoreAdminPO;
import com.egeo.components.user.vo.StoreAdminVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xia
 * @date 2018-09-11 12:11:00
 */
public class StoreAdminConverter {

	
	public static StoreAdminDTO toDTO(StoreAdminVO src) {
		if (src == null)
		return null;	
		StoreAdminDTO tar = new StoreAdminDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());	
		tar.setStoreId(src.getStoreId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static StoreAdminVO toVO(StoreAdminDTO src) {
		if (src == null)
		return null;	
		StoreAdminVO tar = new StoreAdminVO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());	
		tar.setStoreId(src.getStoreId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}


	public static List<StoreAdminVO> toVO(List<StoreAdminDTO> srcs) {
		if (srcs == null)
			return null;
		List<StoreAdminVO> list = new ArrayList<StoreAdminVO>();
		for (StoreAdminDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static StoreAdminDTO toDTO(StoreAdminPO src) {
		if (src == null)
		return null;	
		StoreAdminDTO tar = new StoreAdminDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setStoreId(src.getStoreId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static StoreAdminPO toPO(StoreAdminDTO src) {
		if (src == null)
		return null;	
		StoreAdminPO tar = new StoreAdminPO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setStoreId(src.getStoreId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<StoreAdminDTO> toDTO(List<StoreAdminPO> srcs) {
		if (srcs == null)
			return null;
		List<StoreAdminDTO> list = new ArrayList<StoreAdminDTO>();
		for (StoreAdminPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StoreAdminPO> toPO(List<StoreAdminDTO> srcs) {
		if (srcs == null)
			return null;
		List<StoreAdminPO> list = new ArrayList<StoreAdminPO>();
		for (StoreAdminDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	