package com.egeo.components.stock.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.stock.dto.MerchantProductActivityStockDTO;
import com.egeo.components.stock.po.MerchantProductActivityStockPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-24 09:13:19
 */
public class MerchantProductActivityStockConverter {
	
	public static MerchantProductActivityStockDTO toDTO(MerchantProductActivityStockPO src) {
		if (src == null)
			return null;	
		MerchantProductActivityStockDTO tar = new MerchantProductActivityStockDTO();
		tar.setId(src.getId());
		tar.setActivityId(src.getActivityId());
		tar.setActivityName(src.getActivityName());
		tar.setActivityType(src.getActivityType());
		tar.setThresholdNum(src.getThresholdNum());
		tar.setAlreadySaleNum(src.getAlreadySaleNum());
		tar.setParentId(src.getParentId());
		tar.setSingleProductNum(src.getSingleProductNum());
		tar.setWarehouseId(src.getWarehouseId());
		tar.setStockLimit(src.getStockLimit());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static MerchantProductActivityStockPO toPO(MerchantProductActivityStockDTO src) {
		if (src == null)
			return null;	
		MerchantProductActivityStockPO tar = new MerchantProductActivityStockPO();
		tar.setId(src.getId());
		tar.setActivityId(src.getActivityId());
		tar.setActivityName(src.getActivityName());
		tar.setActivityType(src.getActivityType());
		tar.setThresholdNum(src.getThresholdNum());
		tar.setAlreadySaleNum(src.getAlreadySaleNum());
		tar.setParentId(src.getParentId());
		tar.setSingleProductNum(src.getSingleProductNum());
		tar.setWarehouseId(src.getWarehouseId());
		tar.setStockLimit(src.getStockLimit());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static List<MerchantProductActivityStockDTO> toDTO(List<MerchantProductActivityStockPO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductActivityStockDTO> list = new ArrayList<MerchantProductActivityStockDTO>();
		for (MerchantProductActivityStockPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProductActivityStockPO> toPO(List<MerchantProductActivityStockDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductActivityStockPO> list = new ArrayList<MerchantProductActivityStockPO>();
		for (MerchantProductActivityStockDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	