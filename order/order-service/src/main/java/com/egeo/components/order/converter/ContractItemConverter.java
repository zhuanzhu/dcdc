package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.ContractItemDTO;
import com.egeo.components.order.po.ContractItemPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-24 17:02:13
 */
public class ContractItemConverter {
	
	public static ContractItemDTO toDTO(ContractItemPO src) {
		ContractItemDTO tar = new ContractItemDTO();
		tar.setId(src.getId());
		tar.setContractId(src.getContractId());
		tar.setOrderCode(src.getOrderCode());
		tar.setUserId(src.getUserId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setMerchantProductName(src.getMerchantProductName());
		tar.setMerchantProductStandard(src.getMerchantProductStandard());
		tar.setMerchantProductMaterial(src.getMerchantProductMaterial());
		tar.setMerchantProductPlaceOfOrigin(src.getMerchantProductPlaceOfOrigin());
		tar.setMerchantProductItemAmount(src.getMerchantProductItemAmount());
		tar.setMerchantProductPriceFinal(src.getMerchantProductPriceFinal());
		tar.setMerchantProductRemark(src.getMerchantProductRemark());
		tar.setMerchantProductItemNum(src.getMerchantProductItemNum());
		tar.setMerchantId(src.getMerchantId());
		tar.setContractCode(src.getContractCode());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static ContractItemPO toPO(ContractItemDTO src) {
		ContractItemPO tar = new ContractItemPO();
		tar.setId(src.getId());
		tar.setContractId(src.getContractId());
		tar.setOrderCode(src.getOrderCode());
		tar.setUserId(src.getUserId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setMerchantProductName(src.getMerchantProductName());
		tar.setMerchantProductStandard(src.getMerchantProductStandard());
		tar.setMerchantProductMaterial(src.getMerchantProductMaterial());
		tar.setMerchantProductPlaceOfOrigin(src.getMerchantProductPlaceOfOrigin());
		tar.setMerchantProductItemAmount(src.getMerchantProductItemAmount());
		tar.setMerchantProductPriceFinal(src.getMerchantProductPriceFinal());
		tar.setMerchantProductRemark(src.getMerchantProductRemark());
		tar.setMerchantProductItemNum(src.getMerchantProductItemNum());
		tar.setMerchantId(src.getMerchantId());
		tar.setContractCode(src.getContractCode());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static List<ContractItemDTO> toDTO(List<ContractItemPO> srcs) {
		if (srcs == null)
			return null;
		List<ContractItemDTO> list = new ArrayList<ContractItemDTO>();
		for (ContractItemPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ContractItemPO> toPO(List<ContractItemDTO> srcs) {
		if (srcs == null)
			return null;
		List<ContractItemPO> list = new ArrayList<ContractItemPO>();
		for (ContractItemDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	