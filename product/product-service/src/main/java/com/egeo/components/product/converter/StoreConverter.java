package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.condition.StoreCondition;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.product.po.StorePO;
import com.egeo.components.product.vo.StoreVO;
import com.egeo.utils.EmptyUtil;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xia
 * @date 2018-09-11 15:11:44
 */
public class StoreConverter {

	public static StoreDTO toDTO(StoreVO src) {
		if (src == null)
		return null;	
		StoreDTO tar = new StoreDTO();
		tar.setId(src.getId());
		tar.setSerialNumber(src.getSerialNumber());	
		tar.setCompanyId(src.getCompanyId());	
		tar.setActivityCode(src.getActivityCode());	
		tar.setName(src.getName());	
		tar.setIsDetail(src.getIsDetail());	
		tar.setProvinceId(src.getProvinceId());	
		tar.setProvince(src.getProvince());	
		tar.setCityId(src.getCityId());	
		tar.setCity(src.getCity());	
		tar.setCountyId(src.getCountyId());	
		tar.setCounty(src.getCounty());	
		tar.setAreaId(src.getAreaId());	
		tar.setArea(src.getArea());	
		tar.setDetailAddress(src.getDetailAddress());	
		tar.setDiscount(src.getDiscount());	
		tar.setHasStoreMenu(src.getHasStoreMenu());	
		tar.setDescription(src.getDescription());	
		tar.setCategoryBannerId(src.getCategoryBannerId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());
		tar.setIds(src.getIds());
		tar.setCouponId(src.getCouponId());
		return tar;
	}

	public static StoreVO toVO(StoreDTO src) {
		if (src == null)
		return null;	
		StoreVO tar = new StoreVO();
		tar.setId(src.getId());
		tar.setSerialNumber(src.getSerialNumber());	
		tar.setCompanyId(src.getCompanyId());	
		tar.setActivityCode(src.getActivityCode());	
		tar.setName(src.getName());	
		tar.setIsDetail(src.getIsDetail());	
		tar.setProvinceId(src.getProvinceId());	
		tar.setProvince(src.getProvince());	
		tar.setCityId(src.getCityId());	
		tar.setCity(src.getCity());	
		tar.setCountyId(src.getCountyId());	
		tar.setCounty(src.getCounty());	
		tar.setAreaId(src.getAreaId());	
		tar.setArea(src.getArea());	
		tar.setDetailAddress(src.getDetailAddress());	
		tar.setDiscount(src.getDiscount());	
		tar.setHasStoreMenu(src.getHasStoreMenu());	
		tar.setDescription(src.getDescription());	
		tar.setCategoryBannerId(src.getCategoryBannerId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setStoreTreeId(src.getStoreTreeId());
		tar.setNodeId(src.getNodeId());
		if(src.getActivityCode()!=null) {
			String[] s = src.getActivityCode().split("-");
			tar.setNodeCode(s[s.length-1]);
		}		
		return tar;
	}

	public static List<StoreDTO> toDTOs(List<StoreVO> srcs) {
		if (srcs == null)
			return null;
		List<StoreDTO> list = new ArrayList<StoreDTO>();
		for (StoreVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StoreVO> toVO(List<StoreDTO> srcs) {
		if (srcs == null)
			return null;
		List<StoreVO> list = new ArrayList<StoreVO>();
		for (StoreDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static StoreDTO toDTO(StorePO src) {
		if (src == null)
		return null;	
		StoreDTO tar = new StoreDTO();
		tar.setId(src.getId());
		tar.setSerialNumber(src.getSerialNumber());
		tar.setCompanyId(src.getCompanyId());
		tar.setActivityCode(src.getActivityCode());
		tar.setName(src.getName());
		tar.setIsDetail(src.getIsDetail());
		tar.setProvinceId(src.getProvinceId());
		tar.setProvince(src.getProvince());
		tar.setCityId(src.getCityId());
		tar.setCity(src.getCity());
		tar.setCountyId(src.getCountyId());
		tar.setCounty(src.getCounty());
		tar.setAreaId(src.getAreaId());
		tar.setArea(src.getArea());
		tar.setDetailAddress(src.getDetailAddress());
		tar.setDiscount(src.getDiscount());
		tar.setHasStoreMenu(src.getHasStoreMenu());
		tar.setDescription(src.getDescription());
		tar.setCategoryBannerId(src.getCategoryBannerId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());		
		return tar;
	}
	
	public static StoreDTO toExeDTO(StoreCondition src) {
		if (src == null)
		return null;	
		StoreDTO tar = new StoreDTO();
		tar.setId(src.getId());
		tar.setSerialNumber(src.getSerialNumber());
		tar.setCompanyId(src.getCompanyId());
		tar.setActivityCode(src.getActivityCode());
		tar.setName(src.getName());
		tar.setIsDetail(src.getIsDetail());
		tar.setProvinceId(src.getProvinceId());
		tar.setProvince(src.getProvince());
		tar.setCityId(src.getCityId());
		tar.setCity(src.getCity());
		tar.setCountyId(src.getCountyId());
		tar.setCounty(src.getCounty());
		tar.setAreaId(src.getAreaId());
		tar.setArea(src.getArea());
		tar.setDetailAddress(src.getDetailAddress());
		tar.setDiscount(src.getDiscount());
		tar.setHasStoreMenu(src.getHasStoreMenu());
		tar.setDescription(src.getDescription());
		tar.setCategoryBannerId(src.getCategoryBannerId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setStoreTreeId(src.getStoreTreeId());
		tar.setNodeId(src.getNodeId());
		tar.setParentId(src.getParentId());
		tar.setListSort(src.getListSort());
		String pids = src.getPids();
		if(EmptyUtil.isEmpty(pids)) {
			tar.setPids(new ArrayList<Long>());
		}else {
			String[] split = src.getPids().split(",");
			List<Long> m = new ArrayList<Long>();
			for (String string : split) {
				if(!string.isEmpty()) {
					m.add(Long.valueOf(string));
				}
				
			}
			tar.setPids(m);
		}

		return tar;
	}	

	public static StorePO toPO(StoreDTO src) {
		if (src == null)
		return null;	
		StorePO tar = new StorePO();
		tar.setId(src.getId());
		tar.setSerialNumber(src.getSerialNumber());
		tar.setCompanyId(src.getCompanyId());
		tar.setActivityCode(src.getActivityCode());
		tar.setName(src.getName());
		tar.setIsDetail(src.getIsDetail());
		tar.setProvinceId(src.getProvinceId());
		tar.setProvince(src.getProvince());
		tar.setCityId(src.getCityId());
		tar.setCity(src.getCity());
		tar.setCountyId(src.getCountyId());
		tar.setCounty(src.getCounty());
		tar.setAreaId(src.getAreaId());
		tar.setArea(src.getArea());
		tar.setDetailAddress(src.getDetailAddress());
		tar.setDiscount(src.getDiscount());
		tar.setHasStoreMenu(src.getHasStoreMenu());
		tar.setDescription(src.getDescription());
		tar.setCategoryBannerId(src.getCategoryBannerId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setIds(src.getIds());
		tar.setCouponId(src.getCouponId());
		return tar;
	}

	public static List<StoreDTO> toDTO(List<StorePO> srcs) {
		if (srcs == null)
			return null;
		List<StoreDTO> list = new ArrayList<StoreDTO>();
		for (StorePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	
	public static List<StoreDTO> toExeDTO(List<StoreCondition> srcs) {
		if (srcs == null)
			return null;
		List<StoreDTO> list = new ArrayList<StoreDTO>();
		for (StoreCondition src : srcs) {
			list.add(toExeDTO(src));
		}
		return list;
	}
	public static List<StorePO> toPO(List<StoreDTO> srcs) {
		if (srcs == null)
			return null;
		List<StorePO> list = new ArrayList<StorePO>();
		for (StoreDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	