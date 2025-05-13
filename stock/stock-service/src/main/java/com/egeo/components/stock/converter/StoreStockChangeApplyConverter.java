package com.egeo.components.stock.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.egeo.components.stock.dto.StoreStockChangeApplyDTO;
import com.egeo.components.stock.po.StoreStockChangeApplyPO;
import com.egeo.components.stock.vo.StoreStockChangeApplyVO;
import com.egeo.utils.EmptyUtil;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-09-13 16:29:10
 */
public class StoreStockChangeApplyConverter {

	
	public static StoreStockChangeApplyDTO toDTO(StoreStockChangeApplyVO src) {
		if (src == null)
		return null;	
		StoreStockChangeApplyDTO tar = new StoreStockChangeApplyDTO();
		tar.setId(src.getId());
		tar.setStoreProductUnitId(src.getStoreProductUnitId());	
		tar.setStockChange(src.getStockChange());	
		tar.setBeforeChangeValue(src.getBeforeChangeValue());	
		tar.setAfterChangeValue(src.getAfterChangeValue());	
		tar.setApplyCauseId(src.getApplyCauseId());	
		tar.setConcretenessCause(src.getConcretenessCause());	
		tar.setAfterUserId(src.getAfterUserId());	
		tar.setAfterUserName(src.getAfterUserName());	
		tar.setAfterStoreId(src.getAfterStoreId());	
		tar.setAfterStoreName(src.getAfterStoreName());	
		tar.setAfterTime(src.getAfterTime());	
		tar.setOperationUserId(src.getOperationUserId());	
		tar.setOperationUserName(src.getOperationUserName());	
		tar.setOperationTime(src.getOperationTime());	
		tar.setIsConsent(src.getIsConsent());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCommodityProductUnitName(src.getCommodityProductUnitName());	
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());	
		tar.setType(src.getType());	
		if(EmptyUtil.isNotEmpty(src.getAfterTimeStart()))
			tar.setAfterTimeStart(new Date(src.getAfterTimeStart()));
		if(EmptyUtil.isNotEmpty(src.getAfterTimeStop()))
			tar.setAfterTimeStop(new Date(src.getAfterTimeStop()));
		return tar;
	}

	public static StoreStockChangeApplyVO toVO(StoreStockChangeApplyDTO src) {
		if (src == null)
		return null;	
		StoreStockChangeApplyVO tar = new StoreStockChangeApplyVO();
		tar.setId(src.getId());
		tar.setStoreProductUnitId(src.getStoreProductUnitId());	
		tar.setStockChange(src.getStockChange());	
		tar.setBeforeChangeValue(src.getBeforeChangeValue());	
		tar.setAfterChangeValue(src.getAfterChangeValue());	
		tar.setApplyCauseId(src.getApplyCauseId());	
		tar.setApplyCauseName(translateString(Integer.valueOf(String.valueOf(src.getApplyCauseId()))));
		tar.setConcretenessCause(src.getConcretenessCause());	
		tar.setAfterUserId(src.getAfterUserId());	
		tar.setAfterUserName(src.getAfterUserName());	
		tar.setAfterStoreId(src.getAfterStoreId());	
		tar.setAfterStoreName(src.getAfterStoreName());	
		tar.setAfterTime(src.getAfterTime());	
		tar.setOperationUserId(src.getOperationUserId());	
		tar.setOperationUserName(src.getOperationUserName());	
		tar.setOperationTime(src.getOperationTime());	
		tar.setIsConsent(src.getIsConsent());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCommodityProductUnitName(src.getCommodityProductUnitName());	
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());	
		tar.setType(src.getType());	
		return tar;
	}
	
	

	private static String translateString(Integer applyCauseId) {
		switch (applyCauseId) {
		case 1:
			return "进货";
		case 2:
			return "盘点";
		case 3:
			return "库损";
		case 4:
			return "退货";
		case 5:
			return "其他";
		}
		return null;
	}

	public static List<StoreStockChangeApplyDTO> toDTOs(List<StoreStockChangeApplyVO> srcs) {
		if (srcs == null)
			return null;
		List<StoreStockChangeApplyDTO> list = new ArrayList<StoreStockChangeApplyDTO>();
		for (StoreStockChangeApplyVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StoreStockChangeApplyVO> toVO(List<StoreStockChangeApplyDTO> srcs) {
		if (srcs == null)
			return null;
		List<StoreStockChangeApplyVO> list = new ArrayList<StoreStockChangeApplyVO>();
		for (StoreStockChangeApplyDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static StoreStockChangeApplyDTO toDTO(StoreStockChangeApplyPO src) {
		if (src == null)
		return null;	
		StoreStockChangeApplyDTO tar = new StoreStockChangeApplyDTO();
		tar.setId(src.getId());
		tar.setStoreProductUnitId(src.getStoreProductUnitId());
		tar.setStockChange(src.getStockChange());
		tar.setBeforeChangeValue(src.getBeforeChangeValue());
		tar.setAfterChangeValue(src.getAfterChangeValue());
		tar.setApplyCauseId(src.getApplyCauseId());
		tar.setConcretenessCause(src.getConcretenessCause());
		tar.setAfterUserId(src.getAfterUserId());
		tar.setAfterUserName(src.getAfterUserName());
		tar.setAfterStoreId(src.getAfterStoreId());
		tar.setAfterStoreName(src.getAfterStoreName());
		tar.setAfterTime(src.getAfterTime());
		tar.setOperationUserId(src.getOperationUserId());
		tar.setOperationUserName(src.getOperationUserName());
		tar.setOperationTime(src.getOperationTime());
		tar.setIsConsent(src.getIsConsent());
		tar.setPlatformId(src.getPlatformId());
		tar.setCommodityProductUnitName(src.getCommodityProductUnitName());
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());
		tar.setType(src.getType());
		tar.setAfterTimeStart(src.getAfterTimeStart());
		tar.setAfterTimeStop(src.getAfterTimeStop());
		return tar;
	}

	public static StoreStockChangeApplyPO toPO(StoreStockChangeApplyDTO src) {
		if (src == null)
		return null;	
		StoreStockChangeApplyPO tar = new StoreStockChangeApplyPO();
		tar.setId(src.getId());
		tar.setStoreProductUnitId(src.getStoreProductUnitId());
		tar.setStockChange(src.getStockChange());
		tar.setBeforeChangeValue(src.getBeforeChangeValue());
		tar.setAfterChangeValue(src.getAfterChangeValue());
		tar.setApplyCauseId(src.getApplyCauseId());
		tar.setConcretenessCause(src.getConcretenessCause());
		tar.setAfterUserId(src.getAfterUserId());
		tar.setAfterUserName(src.getAfterUserName());
		tar.setAfterStoreId(src.getAfterStoreId());
		tar.setAfterStoreName(src.getAfterStoreName());
		tar.setAfterTime(src.getAfterTime());
		tar.setOperationUserId(src.getOperationUserId());
		tar.setOperationUserName(src.getOperationUserName());
		tar.setOperationTime(src.getOperationTime());
		tar.setIsConsent(src.getIsConsent());
		tar.setPlatformId(src.getPlatformId());
		tar.setCommodityProductUnitName(src.getCommodityProductUnitName());
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());
		tar.setType(src.getType());
		tar.setAfterTimeStart(src.getAfterTimeStart());
		tar.setAfterTimeStop(src.getAfterTimeStop());
		return tar;
	}

	public static List<StoreStockChangeApplyDTO> toDTO(List<StoreStockChangeApplyPO> srcs) {
		if (srcs == null)
			return null;
		List<StoreStockChangeApplyDTO> list = new ArrayList<StoreStockChangeApplyDTO>();
		for (StoreStockChangeApplyPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StoreStockChangeApplyPO> toPO(List<StoreStockChangeApplyDTO> srcs) {
		if (srcs == null)
			return null;
		List<StoreStockChangeApplyPO> list = new ArrayList<StoreStockChangeApplyPO>();
		for (StoreStockChangeApplyDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	