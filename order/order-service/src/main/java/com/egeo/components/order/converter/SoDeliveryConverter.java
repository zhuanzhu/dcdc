package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoDeliveryDTO;
import com.egeo.components.order.po.SoDeliveryPO;
import com.egeo.components.order.vo.SoDeliveryVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jiang
 * @date 2018-01-29 09:59:05
 */
public class SoDeliveryConverter {

	
	public static SoDeliveryDTO toDTO(SoDeliveryVO src) {
		if (src == null)
		return null;	
		SoDeliveryDTO tar = new SoDeliveryDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());	
		tar.setMerchantId(src.getMerchantId());	
		tar.setType(src.getType());	
		tar.setSoId(src.getSoId());	
		tar.setSoChildId(src.getSoChildId());	
		tar.setOrderCode(src.getOrderCode());	
		tar.setDeliveryCompanyId(src.getDeliveryCompanyId());	
		tar.setDeliveryExpressNbr(src.getDeliveryExpressNbr());	
		tar.setActualStorageTime(src.getActualStorageTime());	
		tar.setActualReceiptTime(src.getActualReceiptTime());	
		tar.setStockOutNo(src.getStockOutNo());	
		tar.setRemark(src.getRemark());	
		tar.setDeliveryType(src.getDeliveryType());	
		tar.setDeliveryRemark(src.getDeliveryRemark());	
		tar.setPackageCode(src.getPackageCode());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static SoDeliveryVO toVO(SoDeliveryDTO src) {
		if (src == null)
		return null;	
		SoDeliveryVO tar = new SoDeliveryVO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());	
		tar.setMerchantId(src.getMerchantId());	
		tar.setType(src.getType());	
		tar.setSoId(src.getSoId());	
		tar.setSoChildId(src.getSoChildId());	
		tar.setOrderCode(src.getOrderCode());	
		tar.setDeliveryCompanyId(src.getDeliveryCompanyId());	
		tar.setDeliveryExpressNbr(src.getDeliveryExpressNbr());	
		tar.setActualStorageTime(src.getActualStorageTime());	
		tar.setActualReceiptTime(src.getActualReceiptTime());	
		tar.setStockOutNo(src.getStockOutNo());	
		tar.setRemark(src.getRemark());	
		tar.setDeliveryType(src.getDeliveryType());	
		tar.setDeliveryRemark(src.getDeliveryRemark());	
		tar.setPackageCode(src.getPackageCode());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<SoDeliveryDTO> toDTOs(List<SoDeliveryVO> srcs) {
		if (srcs == null)
			return null;
		List<SoDeliveryDTO> list = new ArrayList<SoDeliveryDTO>();
		for (SoDeliveryVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoDeliveryVO> toVO(List<SoDeliveryDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoDeliveryVO> list = new ArrayList<SoDeliveryVO>();
		for (SoDeliveryDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static SoDeliveryDTO toDTO(SoDeliveryPO src) {
		if (src == null)
		return null;	
		SoDeliveryDTO tar = new SoDeliveryDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setMerchantId(src.getMerchantId());
		tar.setType(src.getType());
		tar.setSoId(src.getSoId());
		tar.setSoChildId(src.getSoChildId());
		tar.setOrderCode(src.getOrderCode());
		tar.setDeliveryCompanyId(src.getDeliveryCompanyId());
		tar.setDeliveryExpressNbr(src.getDeliveryExpressNbr());
		tar.setActualStorageTime(src.getActualStorageTime());
		tar.setActualReceiptTime(src.getActualReceiptTime());
		tar.setStockOutNo(src.getStockOutNo());
		tar.setRemark(src.getRemark());
		tar.setDeliveryType(src.getDeliveryType());
		tar.setDeliveryRemark(src.getDeliveryRemark());
		tar.setPackageCode(src.getPackageCode());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static SoDeliveryPO toPO(SoDeliveryDTO src) {
		if (src == null)
		return null;	
		SoDeliveryPO tar = new SoDeliveryPO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setMerchantId(src.getMerchantId());
		tar.setType(src.getType());
		tar.setSoId(src.getSoId());
		tar.setSoChildId(src.getSoChildId());
		tar.setOrderCode(src.getOrderCode());
		tar.setDeliveryCompanyId(src.getDeliveryCompanyId());
		tar.setDeliveryExpressNbr(src.getDeliveryExpressNbr());
		tar.setActualStorageTime(src.getActualStorageTime());
		tar.setActualReceiptTime(src.getActualReceiptTime());
		tar.setStockOutNo(src.getStockOutNo());
		tar.setRemark(src.getRemark());
		tar.setDeliveryType(src.getDeliveryType());
		tar.setDeliveryRemark(src.getDeliveryRemark());
		tar.setPackageCode(src.getPackageCode());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<SoDeliveryDTO> toDTO(List<SoDeliveryPO> srcs) {
		if (srcs == null)
			return null;
		List<SoDeliveryDTO> list = new ArrayList<SoDeliveryDTO>();
		for (SoDeliveryPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoDeliveryPO> toPO(List<SoDeliveryDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoDeliveryPO> list = new ArrayList<SoDeliveryPO>();
		for (SoDeliveryDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	