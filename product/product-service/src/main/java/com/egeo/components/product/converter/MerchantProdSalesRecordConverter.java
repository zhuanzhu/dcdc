package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MerchantProdSalesRecordDTO;
import com.egeo.components.product.po.MerchantProdSalesRecordPO;
import com.egeo.components.product.vo.MerchantProdSalesRecordVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-08 13:16:55
 */
public class MerchantProdSalesRecordConverter {
	
	public static MerchantProdSalesRecordDTO toDTO(MerchantProdSalesRecordVO src) {
		if (src == null)
		return null;	
		MerchantProdSalesRecordDTO tar = new MerchantProdSalesRecordDTO();
		tar.setId(src.getId());
		tar.setSalesVolume(src.getSalesVolume());	
		tar.setSalesDate(src.getSalesDate());	
		tar.setStandardProductUnitId(src.getStandardProductUnitId());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setMerchantId(src.getMerchantId());	
		tar.setChineseName(src.getChineseName());	
		tar.setEnglishName(src.getEnglishName());	
		tar.setCommodityProductUnitId(src.getCommodityProductUnitId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static MerchantProdSalesRecordVO toVO(MerchantProdSalesRecordDTO src) {
		if (src == null)
		return null;	
		MerchantProdSalesRecordVO tar = new MerchantProdSalesRecordVO();
		tar.setId(src.getId());
		tar.setSalesVolume(src.getSalesVolume());	
		tar.setSalesDate(src.getSalesDate());	
		tar.setStandardProductUnitId(src.getStandardProductUnitId());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setMerchantId(src.getMerchantId());	
		tar.setChineseName(src.getChineseName());	
		tar.setEnglishName(src.getEnglishName());	
		tar.setCommodityProductUnitId(src.getCommodityProductUnitId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<MerchantProdSalesRecordDTO> toDTOs(List<MerchantProdSalesRecordVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdSalesRecordDTO> list = new ArrayList<MerchantProdSalesRecordDTO>();
		for (MerchantProdSalesRecordVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProdSalesRecordVO> toVO(List<MerchantProdSalesRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdSalesRecordVO> list = new ArrayList<MerchantProdSalesRecordVO>();
		for (MerchantProdSalesRecordDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MerchantProdSalesRecordDTO toDTO(MerchantProdSalesRecordPO src) {
		if (src == null)
		return null;	
		MerchantProdSalesRecordDTO tar = new MerchantProdSalesRecordDTO();
		tar.setId(src.getId());
		tar.setSalesVolume(src.getSalesVolume());
		tar.setSalesDate(src.getSalesDate());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setMerchantId(src.getMerchantId());
		tar.setChineseName(src.getChineseName());
		tar.setEnglishName(src.getEnglishName());
		tar.setCommodityProductUnitId(src.getCommodityProductUnitId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static MerchantProdSalesRecordPO toPO(MerchantProdSalesRecordDTO src) {
		if (src == null)
		return null;	
		MerchantProdSalesRecordPO tar = new MerchantProdSalesRecordPO();
		tar.setId(src.getId());
		tar.setSalesVolume(src.getSalesVolume());
		tar.setSalesDate(src.getSalesDate());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setMerchantId(src.getMerchantId());
		tar.setChineseName(src.getChineseName());
		tar.setEnglishName(src.getEnglishName());
		tar.setCommodityProductUnitId(src.getCommodityProductUnitId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<MerchantProdSalesRecordDTO> toDTO(List<MerchantProdSalesRecordPO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdSalesRecordDTO> list = new ArrayList<MerchantProdSalesRecordDTO>();
		for (MerchantProdSalesRecordPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProdSalesRecordPO> toPO(List<MerchantProdSalesRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdSalesRecordPO> list = new ArrayList<MerchantProdSalesRecordPO>();
		for (MerchantProdSalesRecordDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	