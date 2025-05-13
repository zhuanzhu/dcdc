package com.egeo.components.stock.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.stock.condition.CommodityProductUnitStockRunningWaterCondition;
import com.egeo.components.stock.dto.CommodityProductUnitStockRunningWaterDTO;
import com.egeo.components.stock.po.CommodityProductUnitStockRunningWaterPO;
import com.egeo.components.stock.vo.CommodityProductUnitStockRunningWaterVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-05-17 17:00:02
 */
public class CommodityProductUnitStockRunningWaterConverter {

	
	public static CommodityProductUnitStockRunningWaterDTO toDTO(CommodityProductUnitStockRunningWaterVO src) {
		if (src == null)
		return null;	
		CommodityProductUnitStockRunningWaterDTO tar = new CommodityProductUnitStockRunningWaterDTO();
		tar.setId(src.getId());
		tar.setCommodityProductUnitId(src.getCommodityProductUnitId());	
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
		return tar;
	}

	public static CommodityProductUnitStockRunningWaterVO toVO(CommodityProductUnitStockRunningWaterDTO src) {
		if (src == null)
		return null;	
		CommodityProductUnitStockRunningWaterVO tar = new CommodityProductUnitStockRunningWaterVO();
		tar.setId(src.getId());
		tar.setCommodityProductUnitId(src.getCommodityProductUnitId());	
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
		return tar;
	}

	public static List<CommodityProductUnitStockRunningWaterDTO> toDTOs(List<CommodityProductUnitStockRunningWaterVO> srcs) {
		if (srcs == null)
			return null;
		List<CommodityProductUnitStockRunningWaterDTO> list = new ArrayList<CommodityProductUnitStockRunningWaterDTO>();
		for (CommodityProductUnitStockRunningWaterVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CommodityProductUnitStockRunningWaterVO> toVO(List<CommodityProductUnitStockRunningWaterDTO> srcs) {
		if (srcs == null)
			return null;
		List<CommodityProductUnitStockRunningWaterVO> list = new ArrayList<CommodityProductUnitStockRunningWaterVO>();
		for (CommodityProductUnitStockRunningWaterDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CommodityProductUnitStockRunningWaterDTO toDTO(CommodityProductUnitStockRunningWaterPO src) {
		if (src == null)
		return null;	
		CommodityProductUnitStockRunningWaterDTO tar = new CommodityProductUnitStockRunningWaterDTO();
		tar.setId(src.getId());
		tar.setCommodityProductUnitId(src.getCommodityProductUnitId());
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
		tar.setCreateTimeFront(src.getCreateTimeFront());
		tar.setCreateTimeBack(src.getCreateTimeBack());
		tar.setContactGroupStockId(src.getContactGroupStockId());
		return tar;
	}

	public static CommodityProductUnitStockRunningWaterPO toPO(CommodityProductUnitStockRunningWaterDTO src) {
		if (src == null)
		return null;	
		CommodityProductUnitStockRunningWaterPO tar = new CommodityProductUnitStockRunningWaterPO();
		tar.setId(src.getId());
		tar.setCommodityProductUnitId(src.getCommodityProductUnitId());
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
		tar.setCreateTimeFront(src.getCreateTimeFront());
		tar.setCreateTimeBack(src.getCreateTimeBack());
		tar.setContactGroupStockId(src.getContactGroupStockId());
		return tar;
	}

	public static List<CommodityProductUnitStockRunningWaterDTO> toDTO(List<CommodityProductUnitStockRunningWaterPO> srcs) {
		if (srcs == null)
			return null;
		List<CommodityProductUnitStockRunningWaterDTO> list = new ArrayList<CommodityProductUnitStockRunningWaterDTO>();
		for (CommodityProductUnitStockRunningWaterPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CommodityProductUnitStockRunningWaterPO> toPO(List<CommodityProductUnitStockRunningWaterDTO> srcs) {
		if (srcs == null)
			return null;
		List<CommodityProductUnitStockRunningWaterPO> list = new ArrayList<CommodityProductUnitStockRunningWaterPO>();
		for (CommodityProductUnitStockRunningWaterDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}

	public static CommodityProductUnitStockRunningWaterDTO conditionToDTO(CommodityProductUnitStockRunningWaterCondition src) {
		if (src == null)
		return null;	
		CommodityProductUnitStockRunningWaterDTO tar = new CommodityProductUnitStockRunningWaterDTO();
		tar.setId(src.getId());
		tar.setCommodityProductUnitId(src.getCommodityProductUnitId());
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
		tar.setCreateTimeFront(src.getCreateTimeFront());
		tar.setCreateTimeBack(src.getCreateTimeBack());
		tar.setContactGroupStockId(src.getContactGroupStockId());
		tar.setContactGroupStockName(src.getContactGroupStockName());
		return tar;
	}
	public static List<CommodityProductUnitStockRunningWaterDTO> conditionToDTO(
			List<CommodityProductUnitStockRunningWaterCondition> srcs) {
		
		if (srcs == null)
			return null;
		List<CommodityProductUnitStockRunningWaterDTO> list = new ArrayList<CommodityProductUnitStockRunningWaterDTO>();
		for (CommodityProductUnitStockRunningWaterCondition src : srcs) {
			list.add(conditionToDTO(src));
		}
		return list;
	}
}
	