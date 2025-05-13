package com.egeo.components.stock.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.stock.dto.MerchantWarehouseDTO;
import com.egeo.components.stock.po.MerchantWarehousePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-19 16:19:21
 */
public class MerchantWarehouseConverter {
	
	public static MerchantWarehouseDTO toDTO(MerchantWarehousePO src) {
		if (src == null)
			return null;	
		MerchantWarehouseDTO tar = new MerchantWarehouseDTO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());
		tar.setSubCompanyId(src.getSubCompanyId());
		tar.setWarehouseId(src.getWarehouseId());
		tar.setIsDefaultWarehouse(src.getIsDefaultWarehouse());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static MerchantWarehousePO toPO(MerchantWarehouseDTO src) {
		if (src == null)
			return null;	
		MerchantWarehousePO tar = new MerchantWarehousePO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());
		tar.setSubCompanyId(src.getSubCompanyId());
		tar.setWarehouseId(src.getWarehouseId());
		tar.setIsDefaultWarehouse(src.getIsDefaultWarehouse());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<MerchantWarehouseDTO> toDTO(List<MerchantWarehousePO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantWarehouseDTO> list = new ArrayList<MerchantWarehouseDTO>();
		for (MerchantWarehousePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantWarehousePO> toPO(List<MerchantWarehouseDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantWarehousePO> list = new ArrayList<MerchantWarehousePO>();
		for (MerchantWarehouseDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	