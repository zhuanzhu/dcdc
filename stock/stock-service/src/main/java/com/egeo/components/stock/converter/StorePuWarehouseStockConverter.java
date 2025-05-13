package com.egeo.components.stock.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.stock.dto.StorePuWarehouseStockDTO;
import com.egeo.components.stock.po.StorePuWarehouseStockPO;
import com.egeo.components.stock.vo.StorePuWarehouseStockVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-09-13 05:39:58
 */
public class StorePuWarehouseStockConverter {

	
	public static StorePuWarehouseStockDTO toDTO(StorePuWarehouseStockVO src) {
		if (src == null)
		return null;	
		StorePuWarehouseStockDTO tar = new StorePuWarehouseStockDTO();
		tar.setId(src.getId());
		tar.setStoreId(src.getStoreId());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setMerchantId(src.getMerchantId());	
		tar.setWarehouseId(src.getWarehouseId());	
		tar.setRealStockNum(src.getRealStockNum());	
		tar.setRealFrozenStockNum(src.getRealFrozenStockNum());	
		tar.setPmWarehousePriority(src.getPmWarehousePriority());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setStoreProductUnitId(src.getStoreProductUnitId());	
		return tar;
	}

	public static StorePuWarehouseStockVO toVO(StorePuWarehouseStockDTO src) {
		if (src == null)
		return null;	
		StorePuWarehouseStockVO tar = new StorePuWarehouseStockVO();
		tar.setId(src.getId());
		tar.setStoreId(src.getStoreId());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setMerchantId(src.getMerchantId());	
		tar.setWarehouseId(src.getWarehouseId());	
		tar.setRealStockNum(src.getRealStockNum());	
		tar.setRealFrozenStockNum(src.getRealFrozenStockNum());	
		tar.setPmWarehousePriority(src.getPmWarehousePriority());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setStoreProductUnitId(src.getStoreProductUnitId());	
		return tar;
	}

	public static List<StorePuWarehouseStockDTO> toDTOs(List<StorePuWarehouseStockVO> srcs) {
		if (srcs == null)
			return null;
		List<StorePuWarehouseStockDTO> list = new ArrayList<StorePuWarehouseStockDTO>();
		for (StorePuWarehouseStockVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StorePuWarehouseStockVO> toVO(List<StorePuWarehouseStockDTO> srcs) {
		if (srcs == null)
			return null;
		List<StorePuWarehouseStockVO> list = new ArrayList<StorePuWarehouseStockVO>();
		for (StorePuWarehouseStockDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static StorePuWarehouseStockDTO toDTO(StorePuWarehouseStockPO src) {
		if (src == null)
		return null;	
		StorePuWarehouseStockDTO tar = new StorePuWarehouseStockDTO();
		tar.setId(src.getId());
		tar.setStoreId(src.getStoreId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setMerchantId(src.getMerchantId());
		tar.setWarehouseId(src.getWarehouseId());
		tar.setRealStockNum(src.getRealStockNum());
		tar.setRealFrozenStockNum(src.getRealFrozenStockNum());
		tar.setPmWarehousePriority(src.getPmWarehousePriority());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setStoreProductUnitId(src.getStoreProductUnitId());
		return tar;
	}

	public static StorePuWarehouseStockPO toPO(StorePuWarehouseStockDTO src) {
		if (src == null)
		return null;	
		StorePuWarehouseStockPO tar = new StorePuWarehouseStockPO();
		tar.setId(src.getId());
		tar.setStoreId(src.getStoreId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setMerchantId(src.getMerchantId());
		tar.setWarehouseId(src.getWarehouseId());
		tar.setRealStockNum(src.getRealStockNum());
		tar.setRealFrozenStockNum(src.getRealFrozenStockNum());
		tar.setPmWarehousePriority(src.getPmWarehousePriority());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setStoreProductUnitId(src.getStoreProductUnitId());
		return tar;
	}

	public static List<StorePuWarehouseStockDTO> toDTO(List<StorePuWarehouseStockPO> srcs) {
		if (srcs == null)
			return null;
		List<StorePuWarehouseStockDTO> list = new ArrayList<StorePuWarehouseStockDTO>();
		for (StorePuWarehouseStockPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StorePuWarehouseStockPO> toPO(List<StorePuWarehouseStockDTO> srcs) {
		if (srcs == null)
			return null;
		List<StorePuWarehouseStockPO> list = new ArrayList<StorePuWarehouseStockPO>();
		for (StorePuWarehouseStockDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	