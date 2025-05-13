package com.egeo.components.stock.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.stock.dto.MerchantProductVirtualStockDTO;
import com.egeo.components.stock.po.MerchantProductVirtualStockPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-24 09:13:19
 */
public class MerchantProductVirtualStockConverter {
	
	public static MerchantProductVirtualStockDTO toDTO(MerchantProductVirtualStockPO src) {
		if (src == null)
			return null;	
		MerchantProductVirtualStockDTO tar = new MerchantProductVirtualStockDTO();
		tar.setId(src.getId());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());
		tar.setMerchantId(src.getMerchantId());
		tar.setIsSupportVirtualstock(src.getIsSupportVirtualstock());
		tar.setIsSupportUnlimitedstock(src.getIsSupportUnlimitedstock());
		tar.setRealStockNum(src.getRealStockNum());
		tar.setRealFrozenStockNum(src.getRealFrozenStockNum());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static MerchantProductVirtualStockPO toPO(MerchantProductVirtualStockDTO src) {
		if (src == null)
			return null;	
		MerchantProductVirtualStockPO tar = new MerchantProductVirtualStockPO();
		tar.setId(src.getId());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());
		tar.setMerchantId(src.getMerchantId());
		tar.setIsSupportVirtualstock(src.getIsSupportVirtualstock());
		tar.setIsSupportUnlimitedstock(src.getIsSupportUnlimitedstock());
		tar.setRealStockNum(src.getRealStockNum());
		tar.setRealFrozenStockNum(src.getRealFrozenStockNum());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static List<MerchantProductVirtualStockDTO> toDTO(List<MerchantProductVirtualStockPO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductVirtualStockDTO> list = new ArrayList<MerchantProductVirtualStockDTO>();
		for (MerchantProductVirtualStockPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProductVirtualStockPO> toPO(List<MerchantProductVirtualStockDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductVirtualStockPO> list = new ArrayList<MerchantProductVirtualStockPO>();
		for (MerchantProductVirtualStockDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	