package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.ContractDTO;
import com.egeo.components.order.po.ContractPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:52
 */
public class ContractConverter {
	
	public static ContractDTO toDTO(ContractPO src) {
		ContractDTO tar = new ContractDTO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setContractingPlace(src.getContractingPlace());
		tar.setSellersType(src.getSellersType());
		tar.setUserId(src.getUserId());
		tar.setPurchaserCompanyName(src.getPurchaserCompanyName());
		tar.setPurchaserPhone(src.getPurchaserPhone());
		tar.setPurchaserFax(src.getPurchaserFax());
		tar.setPurchaserAddress(src.getPurchaserAddress());
		tar.setSellersCompanyName(src.getSellersCompanyName());
		tar.setSellersPhone(src.getSellersPhone());
		tar.setSellersFax(src.getSellersFax());
		tar.setSellersAddress(src.getSellersAddress());
		tar.setDeliveryDate(src.getDeliveryDate());
		tar.setDeliveryAddress(src.getDeliveryAddress());
		tar.setFreightAndDeliveryCharges(src.getFreightAndDeliveryCharges());
		tar.setTermsOfPaymentAndTimeLimit(src.getTermsOfPaymentAndTimeLimit());
		tar.setProductAmount(src.getProductAmount());
		tar.setRemarks(src.getRemarks());
		tar.setPlatformId(src.getPlatformId());
		tar.setMerchantId(src.getMerchantId());
		tar.setContractCode(src.getContractCode());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static ContractPO toPO(ContractDTO src) {
		ContractPO tar = new ContractPO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setContractingPlace(src.getContractingPlace());
		tar.setSellersType(src.getSellersType());
		tar.setUserId(src.getUserId());
		tar.setPurchaserCompanyName(src.getPurchaserCompanyName());
		tar.setPurchaserPhone(src.getPurchaserPhone());
		tar.setPurchaserFax(src.getPurchaserFax());
		tar.setPurchaserAddress(src.getPurchaserAddress());
		tar.setSellersCompanyName(src.getSellersCompanyName());
		tar.setSellersPhone(src.getSellersPhone());
		tar.setSellersFax(src.getSellersFax());
		tar.setSellersAddress(src.getSellersAddress());
		tar.setDeliveryDate(src.getDeliveryDate());
		tar.setDeliveryAddress(src.getDeliveryAddress());
		tar.setFreightAndDeliveryCharges(src.getFreightAndDeliveryCharges());
		tar.setTermsOfPaymentAndTimeLimit(src.getTermsOfPaymentAndTimeLimit());
		tar.setProductAmount(src.getProductAmount());
		tar.setRemarks(src.getRemarks());
		tar.setPlatformId(src.getPlatformId());
		tar.setMerchantId(src.getMerchantId());
		tar.setContractCode(src.getContractCode());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<ContractDTO> toDTO(List<ContractPO> srcs) {
		if (srcs == null)
			return null;
		List<ContractDTO> list = new ArrayList<ContractDTO>();
		for (ContractPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ContractPO> toPO(List<ContractDTO> srcs) {
		if (srcs == null)
			return null;
		List<ContractPO> list = new ArrayList<ContractPO>();
		for (ContractDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	