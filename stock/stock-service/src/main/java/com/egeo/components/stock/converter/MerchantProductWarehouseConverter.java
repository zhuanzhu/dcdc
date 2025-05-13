package com.egeo.components.stock.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.stock.dto.MerchantProductWarehouseDTO;
import com.egeo.components.stock.po.MerchantProductWarehousePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-24 09:13:19
 */
public class MerchantProductWarehouseConverter {
	
	public static MerchantProductWarehouseDTO toDTO(MerchantProductWarehousePO src) {
		if (src == null)
			return null;	
		MerchantProductWarehouseDTO tar = new MerchantProductWarehouseDTO();
		tar.setId(src.getId());
		tar.setProductId(src.getProductId());
		tar.setMerchantId(src.getMerchantId());
		tar.setWarehouseId(src.getWarehouseId());
		tar.setIsMainWarehouse(src.getIsMainWarehouse());
		tar.setRemark(src.getRemark());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static MerchantProductWarehousePO toPO(MerchantProductWarehouseDTO src) {
		if (src == null)
			return null;	
		MerchantProductWarehousePO tar = new MerchantProductWarehousePO();
		tar.setId(src.getId());
		tar.setProductId(src.getProductId());
		tar.setMerchantId(src.getMerchantId());
		tar.setWarehouseId(src.getWarehouseId());
		tar.setIsMainWarehouse(src.getIsMainWarehouse());
		tar.setRemark(src.getRemark());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static List<MerchantProductWarehouseDTO> toDTO(List<MerchantProductWarehousePO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductWarehouseDTO> list = new ArrayList<MerchantProductWarehouseDTO>();
		for (MerchantProductWarehousePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProductWarehousePO> toPO(List<MerchantProductWarehouseDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductWarehousePO> list = new ArrayList<MerchantProductWarehousePO>();
		for (MerchantProductWarehouseDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	