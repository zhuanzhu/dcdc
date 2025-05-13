package com.egeo.components.stock.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.stock.dto.MerchantProductWarehouseStockDTO;
import com.egeo.components.stock.po.MerchantProductWarehouseStockPO;
import com.egeo.components.stock.vo.MerchantProductWarehouseStockVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 11:44:55
 */
public class MerchantProductWarehouseStockConverter {

	
	public static MerchantProductWarehouseStockDTO toDTO(MerchantProductWarehouseStockVO src) {
		MerchantProductWarehouseStockDTO tar = new MerchantProductWarehouseStockDTO();
		tar.setId(src.getId());
		tar.setSkuId(src.getSkuId());	
		tar.setProductId(src.getProductId());	
		tar.setMerchantId(src.getMerchantId());	
		tar.setWarehouseId(src.getWarehouseId());	
		tar.setStandardProductUnitId(src.getStandardProductUnitId());
		tar.setRealStockNum(src.getRealStockNum());	
		tar.setRealFrozenStockNum(src.getRealFrozenStockNum());	
		tar.setPmWarehousePriority(src.getPmWarehousePriority());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static MerchantProductWarehouseStockVO toVO(MerchantProductWarehouseStockDTO src) {
		MerchantProductWarehouseStockVO tar = new MerchantProductWarehouseStockVO();
		tar.setId(src.getId());
		tar.setSkuId(src.getSkuId());			
		tar.setProductId(src.getProductId());		
		tar.setMerchantId(src.getMerchantId());		
		tar.setWarehouseId(src.getWarehouseId());		
		tar.setStandardProductUnitId(src.getStandardProductUnitId());
		tar.setRealStockNum(src.getRealStockNum());		
		tar.setRealFrozenStockNum(src.getRealFrozenStockNum());		
		tar.setPmWarehousePriority(src.getPmWarehousePriority());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());		
		return tar;
	}

	public static List<MerchantProductWarehouseStockDTO> toDTOs(List<MerchantProductWarehouseStockVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductWarehouseStockDTO> list = new ArrayList<MerchantProductWarehouseStockDTO>();
		for (MerchantProductWarehouseStockVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProductWarehouseStockVO> toVO(List<MerchantProductWarehouseStockDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductWarehouseStockVO> list = new ArrayList<MerchantProductWarehouseStockVO>();
		for (MerchantProductWarehouseStockDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MerchantProductWarehouseStockDTO toDTO(MerchantProductWarehouseStockPO src) {
		if (src == null)
			return null;	
		MerchantProductWarehouseStockDTO tar = new MerchantProductWarehouseStockDTO();
		tar.setId(src.getId());
		tar.setSkuId(src.getSkuId());
		tar.setProductId(src.getProductId());
		tar.setMerchantId(src.getMerchantId());
		tar.setWarehouseId(src.getWarehouseId());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());
		tar.setRealStockNum(src.getRealStockNum());
		tar.setRealFrozenStockNum(src.getRealFrozenStockNum());
		tar.setPmWarehousePriority(src.getPmWarehousePriority());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static MerchantProductWarehouseStockPO toPO(MerchantProductWarehouseStockDTO src) {
		if (src == null)
			return null;	
		MerchantProductWarehouseStockPO tar = new MerchantProductWarehouseStockPO();
		tar.setId(src.getId());
		tar.setSkuId(src.getSkuId());
		tar.setProductId(src.getProductId());
		tar.setMerchantId(src.getMerchantId());
		tar.setWarehouseId(src.getWarehouseId());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());
		tar.setRealStockNum(src.getRealStockNum());
		tar.setRealFrozenStockNum(src.getRealFrozenStockNum());
		tar.setPmWarehousePriority(src.getPmWarehousePriority());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<MerchantProductWarehouseStockDTO> toDTO(List<MerchantProductWarehouseStockPO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductWarehouseStockDTO> list = new ArrayList<MerchantProductWarehouseStockDTO>();
		for (MerchantProductWarehouseStockPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProductWarehouseStockPO> toPO(List<MerchantProductWarehouseStockDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductWarehouseStockPO> list = new ArrayList<MerchantProductWarehouseStockPO>();
		for (MerchantProductWarehouseStockDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	