package com.egeo.components.stock.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.egeo.components.stock.condition.StorePuStockRunningWaterCondition;
import com.egeo.components.stock.dto.StorePuStockRunningWaterDTO;
import com.egeo.components.stock.po.StorePuStockRunningWaterPO;
import com.egeo.components.stock.vo.StorePuStockRunningWaterVO;
import com.egeo.utils.EmptyUtil;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-09-13 05:39:58
 */
public class StorePuStockRunningWaterConverter {

	public static StorePuStockRunningWaterDTO toDTO(StorePuStockRunningWaterVO src) {
		if (src == null)
		return null;	
		StorePuStockRunningWaterDTO tar = new StorePuStockRunningWaterDTO();
		tar.setId(src.getId());
		tar.setStoreProductUnitId(src.getStoreProductUnitId());	
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());	
		tar.setCommodityProductUnitName(src.getCommodityProductUnitName());	
		tar.setPreoperativeStockNum(src.getPreoperativeStockNum());	
		tar.setOperationBackStockNum(src.getOperationBackStockNum());	
		tar.setStockChange(src.getStockChange());	
		tar.setCreateUserid(src.getCreateUserid());	
		tar.setCreateUsername(src.getCreateUsername());	
		tar.setCreateUserip(src.getCreateUserip());	
		tar.setCreateUsermac(src.getCreateUsermac());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setPreoperativeRealStockNum(src.getPreoperativeRealStockNum());	
		tar.setOperationBackRealStockNum(src.getOperationBackRealStockNum());	
		tar.setType(src.getType());	
		tar.setOrderCode(src.getOrderCode());	
		if(EmptyUtil.isNotEmpty(src.getCreateTimeStart()))
			tar.setCreateTimeStart(new Date(src.getCreateTimeStart()));
		if(EmptyUtil.isNotEmpty(src.getCreateTimeStop()))
			tar.setCreateTimeStop(new Date(src.getCreateTimeStop()));
		return tar;
	}

	public static StorePuStockRunningWaterVO toVO(StorePuStockRunningWaterDTO src) {
		if (src == null)
		return null;	
		StorePuStockRunningWaterVO tar = new StorePuStockRunningWaterVO();
		tar.setId(src.getId());
		tar.setStoreProductUnitId(src.getStoreProductUnitId());	
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());	
		tar.setCommodityProductUnitName(src.getCommodityProductUnitName());	
		tar.setPreoperativeStockNum(src.getPreoperativeStockNum());	
		tar.setOperationBackStockNum(src.getOperationBackStockNum());	
		tar.setStockChange(src.getStockChange());	
		tar.setCreateUserid(src.getCreateUserid());	
		tar.setCreateUsername(src.getCreateUsername());	
		tar.setCreateUserip(src.getCreateUserip());	
		tar.setCreateUsermac(src.getCreateUsermac());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setPreoperativeRealStockNum(src.getPreoperativeRealStockNum());	
		tar.setOperationBackRealStockNum(src.getOperationBackRealStockNum());	
		tar.setType(src.getType());	
		tar.setOrderCode(src.getOrderCode());	
		tar.setStoreName(src.getStoreName());
		tar.setDictName(src.getDictName());
		return tar;
	}

	public static List<StorePuStockRunningWaterDTO> toDTOs(List<StorePuStockRunningWaterVO> srcs) {
		if (srcs == null)
			return null;
		List<StorePuStockRunningWaterDTO> list = new ArrayList<StorePuStockRunningWaterDTO>();
		for (StorePuStockRunningWaterVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StorePuStockRunningWaterVO> toVO(List<StorePuStockRunningWaterDTO> srcs) {
		if (srcs == null)
			return null;
		List<StorePuStockRunningWaterVO> list = new ArrayList<StorePuStockRunningWaterVO>();
		for (StorePuStockRunningWaterDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static StorePuStockRunningWaterDTO toDTO(StorePuStockRunningWaterPO src) {
		if (src == null)
		return null;	
		StorePuStockRunningWaterDTO tar = new StorePuStockRunningWaterDTO();
		tar.setId(src.getId());
		tar.setStoreProductUnitId(src.getStoreProductUnitId());
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());
		tar.setCommodityProductUnitName(src.getCommodityProductUnitName());
		tar.setPreoperativeStockNum(src.getPreoperativeStockNum());
		tar.setOperationBackStockNum(src.getOperationBackStockNum());
		tar.setStockChange(src.getStockChange());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserip(src.getCreateUserip());
		tar.setCreateUsermac(src.getCreateUsermac());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setPreoperativeRealStockNum(src.getPreoperativeRealStockNum());
		tar.setOperationBackRealStockNum(src.getOperationBackRealStockNum());
		tar.setType(src.getType());
		tar.setOrderCode(src.getOrderCode());
		tar.setCreateTimeStart(src.getCreateTimeStart());
		tar.setCreateTimeStop(src.getCreateTimeStop());
		return tar;
	}
	
	public static StorePuStockRunningWaterDTO toDTO(StorePuStockRunningWaterCondition src) {
		if (src == null)
		return null;	
		StorePuStockRunningWaterDTO tar = new StorePuStockRunningWaterDTO();
		tar.setId(src.getId());
		tar.setStoreProductUnitId(src.getStoreProductUnitId());
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());
		tar.setCommodityProductUnitName(src.getCommodityProductUnitName());
		tar.setPreoperativeStockNum(src.getPreoperativeStockNum());
		tar.setOperationBackStockNum(src.getOperationBackStockNum());
		tar.setStockChange(src.getStockChange());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserip(src.getCreateUserip());
		tar.setCreateUsermac(src.getCreateUsermac());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setPreoperativeRealStockNum(src.getPreoperativeRealStockNum());
		tar.setOperationBackRealStockNum(src.getOperationBackRealStockNum());
		tar.setType(src.getType());
		tar.setOrderCode(src.getOrderCode());
		tar.setCreateTimeStart(src.getCreateTimeStart());
		tar.setCreateTimeStop(src.getCreateTimeStop());
		tar.setDictName(src.getDictName());
		return tar;
	}

	public static StorePuStockRunningWaterPO toPO(StorePuStockRunningWaterDTO src) {
		if (src == null)
		return null;	
		StorePuStockRunningWaterPO tar = new StorePuStockRunningWaterPO();
		tar.setId(src.getId());
		tar.setStoreProductUnitId(src.getStoreProductUnitId());
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());
		tar.setCommodityProductUnitName(src.getCommodityProductUnitName());
		tar.setPreoperativeStockNum(src.getPreoperativeStockNum());
		tar.setOperationBackStockNum(src.getOperationBackStockNum());
		tar.setStockChange(src.getStockChange());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserip(src.getCreateUserip());
		tar.setCreateUsermac(src.getCreateUsermac());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setPreoperativeRealStockNum(src.getPreoperativeRealStockNum());
		tar.setOperationBackRealStockNum(src.getOperationBackRealStockNum());
		tar.setType(src.getType());
		tar.setOrderCode(src.getOrderCode());
		tar.setCreateTimeStart(src.getCreateTimeStart());
		tar.setCreateTimeStop(src.getCreateTimeStop());
		return tar;
	}

	public static List<StorePuStockRunningWaterDTO> conditionToDTO(List<StorePuStockRunningWaterCondition> srcs) {
		if (srcs == null)
			return null;
		List<StorePuStockRunningWaterDTO> list = new ArrayList<StorePuStockRunningWaterDTO>();
		for (StorePuStockRunningWaterCondition src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StorePuStockRunningWaterDTO> toDTO(List<StorePuStockRunningWaterPO> srcs) {
		if (srcs == null)
			return null;
		List<StorePuStockRunningWaterDTO> list = new ArrayList<StorePuStockRunningWaterDTO>();
		for (StorePuStockRunningWaterPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}
	
	public static List<StorePuStockRunningWaterPO> toPO(List<StorePuStockRunningWaterDTO> srcs) {
		if (srcs == null)
			return null;
		List<StorePuStockRunningWaterPO> list = new ArrayList<StorePuStockRunningWaterPO>();
		for (StorePuStockRunningWaterDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	