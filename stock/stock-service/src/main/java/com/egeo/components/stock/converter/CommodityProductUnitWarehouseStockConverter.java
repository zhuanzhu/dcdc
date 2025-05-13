package com.egeo.components.stock.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.stock.dto.CommodityProductUnitWarehouseStockDTO;
import com.egeo.components.stock.dto.ResidueStockNumConditionDTO;
import com.egeo.components.stock.po.CommodityProductUnitWarehouseStockPO;
import com.egeo.components.stock.po.ResidueStockNumCondition;
import com.egeo.components.stock.vo.CommodityProductUnitWarehouseStockVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-08 10:01:38
 */
public class CommodityProductUnitWarehouseStockConverter {

	
	public static CommodityProductUnitWarehouseStockDTO toDTO(CommodityProductUnitWarehouseStockVO src) {
		if (src == null)
		return null;	
		CommodityProductUnitWarehouseStockDTO tar = new CommodityProductUnitWarehouseStockDTO();
		tar.setId(src.getId());
		tar.setCommodityProductUnitId(src.getCommodityProductUnitId());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setMerchantId(src.getMerchantId());	
		tar.setWarehouseId(src.getWarehouseId());	
		tar.setMerchantProductId(src.getMerchantProductId());	
		tar.setRealStockNum(src.getRealStockNum());	
		tar.setRealFrozenStockNum(src.getRealFrozenStockNum());	
		tar.setPmWarehousePriority(src.getPmWarehousePriority());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static CommodityProductUnitWarehouseStockVO toVO(CommodityProductUnitWarehouseStockDTO src) {
		if (src == null)
		return null;	
		CommodityProductUnitWarehouseStockVO tar = new CommodityProductUnitWarehouseStockVO();
		tar.setId(src.getId());
		tar.setCommodityProductUnitId(src.getCommodityProductUnitId());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setMerchantId(src.getMerchantId());	
		tar.setWarehouseId(src.getWarehouseId());	
		tar.setMerchantProductId(src.getMerchantProductId());	
		tar.setRealStockNum(src.getRealStockNum());	
		tar.setRealFrozenStockNum(src.getRealFrozenStockNum());	
		tar.setPmWarehousePriority(src.getPmWarehousePriority());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<CommodityProductUnitWarehouseStockDTO> toDTOs(List<CommodityProductUnitWarehouseStockVO> srcs) {
		if (srcs == null)
			return null;
		List<CommodityProductUnitWarehouseStockDTO> list = new ArrayList<CommodityProductUnitWarehouseStockDTO>();
		for (CommodityProductUnitWarehouseStockVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CommodityProductUnitWarehouseStockVO> toVO(List<CommodityProductUnitWarehouseStockDTO> srcs) {
		if (srcs == null)
			return null;
		List<CommodityProductUnitWarehouseStockVO> list = new ArrayList<CommodityProductUnitWarehouseStockVO>();
		for (CommodityProductUnitWarehouseStockDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CommodityProductUnitWarehouseStockDTO toDTO(CommodityProductUnitWarehouseStockPO src) {
		if (src == null)
		return null;	
		CommodityProductUnitWarehouseStockDTO tar = new CommodityProductUnitWarehouseStockDTO();
		tar.setId(src.getId());
		tar.setCommodityProductUnitId(src.getCommodityProductUnitId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setMerchantId(src.getMerchantId());
		tar.setWarehouseId(src.getWarehouseId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setRealStockNum(src.getRealStockNum());
		tar.setRealFrozenStockNum(src.getRealFrozenStockNum());
		tar.setPmWarehousePriority(src.getPmWarehousePriority());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static CommodityProductUnitWarehouseStockPO toPO(CommodityProductUnitWarehouseStockDTO src) {
		if (src == null)
		return null;	
		CommodityProductUnitWarehouseStockPO tar = new CommodityProductUnitWarehouseStockPO();
		tar.setId(src.getId());
		tar.setCommodityProductUnitId(src.getCommodityProductUnitId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setMerchantId(src.getMerchantId());
		tar.setWarehouseId(src.getWarehouseId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setRealStockNum(src.getRealStockNum());
		tar.setRealFrozenStockNum(src.getRealFrozenStockNum());
		tar.setPmWarehousePriority(src.getPmWarehousePriority());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<CommodityProductUnitWarehouseStockDTO> toDTO(List<CommodityProductUnitWarehouseStockPO> srcs) {
		if (srcs == null)
			return null;
		List<CommodityProductUnitWarehouseStockDTO> list = new ArrayList<CommodityProductUnitWarehouseStockDTO>();
		for (CommodityProductUnitWarehouseStockPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CommodityProductUnitWarehouseStockPO> toPO(List<CommodityProductUnitWarehouseStockDTO> srcs) {
		if (srcs == null)
			return null;
		List<CommodityProductUnitWarehouseStockPO> list = new ArrayList<CommodityProductUnitWarehouseStockPO>();
		for (CommodityProductUnitWarehouseStockDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}

	public static ResidueStockNumConditionDTO toDTO(ResidueStockNumCondition src) {
		if (src == null)
			return null;
		ResidueStockNumConditionDTO tar = new ResidueStockNumConditionDTO();
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setRealStockNum(src.getRealStockNum());
		return tar;
	}

	public static List<ResidueStockNumConditionDTO> toDTOList(List<ResidueStockNumCondition> srcs) {
		if (srcs == null)
			return null;
		List<ResidueStockNumConditionDTO> list = new ArrayList<ResidueStockNumConditionDTO>();
		for (ResidueStockNumCondition src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}
}
	