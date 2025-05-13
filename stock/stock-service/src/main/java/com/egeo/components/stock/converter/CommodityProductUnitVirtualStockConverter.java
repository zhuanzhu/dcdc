package com.egeo.components.stock.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.stock.dto.CommodityProductUnitVirtualStockDTO;
import com.egeo.components.stock.po.CommodityProductUnitVirtualStockPO;
import com.egeo.components.stock.vo.CommodityProductUnitVirtualStockVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-08 09:55:37
 */
public class CommodityProductUnitVirtualStockConverter {

	
	public static CommodityProductUnitVirtualStockDTO toDTO(CommodityProductUnitVirtualStockVO src) {
		if (src == null)
		return null;	
		CommodityProductUnitVirtualStockDTO tar = new CommodityProductUnitVirtualStockDTO();
		tar.setId(src.getId());
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setMerchantId(src.getMerchantId());	
		tar.setIsSupportVirtualstock(src.getIsSupportVirtualstock());	
		tar.setIsSupportUnlimitedstock(src.getIsSupportUnlimitedstock());	
		tar.setRealStockNum(src.getRealStockNum());	
		tar.setRealFrozenStockNum(src.getRealFrozenStockNum());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCommodityProductUnitId(src.getCommodityProductUnitId());	
		return tar;
	}

	public static CommodityProductUnitVirtualStockVO toVO(CommodityProductUnitVirtualStockDTO src) {
		if (src == null)
		return null;	
		CommodityProductUnitVirtualStockVO tar = new CommodityProductUnitVirtualStockVO();
		tar.setId(src.getId());
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setMerchantId(src.getMerchantId());	
		tar.setIsSupportVirtualstock(src.getIsSupportVirtualstock());	
		tar.setIsSupportUnlimitedstock(src.getIsSupportUnlimitedstock());	
		tar.setRealStockNum(src.getRealStockNum());	
		tar.setRealFrozenStockNum(src.getRealFrozenStockNum());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCommodityProductUnitId(src.getCommodityProductUnitId());	
		return tar;
	}

	public static List<CommodityProductUnitVirtualStockDTO> toDTOs(List<CommodityProductUnitVirtualStockVO> srcs) {
		if (srcs == null)
			return null;
		List<CommodityProductUnitVirtualStockDTO> list = new ArrayList<CommodityProductUnitVirtualStockDTO>();
		for (CommodityProductUnitVirtualStockVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CommodityProductUnitVirtualStockVO> toVO(List<CommodityProductUnitVirtualStockDTO> srcs) {
		if (srcs == null)
			return null;
		List<CommodityProductUnitVirtualStockVO> list = new ArrayList<CommodityProductUnitVirtualStockVO>();
		for (CommodityProductUnitVirtualStockDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CommodityProductUnitVirtualStockDTO toDTO(CommodityProductUnitVirtualStockPO src) {
		if (src == null)
		return null;	
		CommodityProductUnitVirtualStockDTO tar = new CommodityProductUnitVirtualStockDTO();
		tar.setId(src.getId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setMerchantId(src.getMerchantId());
		tar.setIsSupportVirtualstock(src.getIsSupportVirtualstock());
		tar.setIsSupportUnlimitedstock(src.getIsSupportUnlimitedstock());
		tar.setRealStockNum(src.getRealStockNum());
		tar.setRealFrozenStockNum(src.getRealFrozenStockNum());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setCommodityProductUnitId(src.getCommodityProductUnitId());
		return tar;
	}

	public static CommodityProductUnitVirtualStockPO toPO(CommodityProductUnitVirtualStockDTO src) {
		if (src == null)
		return null;	
		CommodityProductUnitVirtualStockPO tar = new CommodityProductUnitVirtualStockPO();
		tar.setId(src.getId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setMerchantId(src.getMerchantId());
		tar.setIsSupportVirtualstock(src.getIsSupportVirtualstock());
		tar.setIsSupportUnlimitedstock(src.getIsSupportUnlimitedstock());
		tar.setRealStockNum(src.getRealStockNum());
		tar.setRealFrozenStockNum(src.getRealFrozenStockNum());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setCommodityProductUnitId(src.getCommodityProductUnitId());
		return tar;
	}

	public static List<CommodityProductUnitVirtualStockDTO> toDTO(List<CommodityProductUnitVirtualStockPO> srcs) {
		if (srcs == null)
			return null;
		List<CommodityProductUnitVirtualStockDTO> list = new ArrayList<CommodityProductUnitVirtualStockDTO>();
		for (CommodityProductUnitVirtualStockPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CommodityProductUnitVirtualStockPO> toPO(List<CommodityProductUnitVirtualStockDTO> srcs) {
		if (srcs == null)
			return null;
		List<CommodityProductUnitVirtualStockPO> list = new ArrayList<CommodityProductUnitVirtualStockPO>();
		for (CommodityProductUnitVirtualStockDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	