package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoInvoiceDTO;
import com.egeo.components.order.po.SoInvoicePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author ghw
 * @date 2018-02-03 17:38:35
 */
public class SoInvoiceConverter {
	
	public static SoInvoiceDTO toDTO(SoInvoicePO src) {
		if (src == null)
		return null;	
		SoInvoiceDTO tar = new SoInvoiceDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setSoId(src.getSoId());
		tar.setSoChildId(src.getSoChildId());
		tar.setInvoiceValue(src.getInvoiceValue());
		tar.setInvoiceCode(src.getInvoiceCode());
		tar.setInvoiceTaxType(src.getInvoiceTaxType());
		tar.setInvoiceFinType(src.getInvoiceFinType());
		tar.setInvoiceForm(src.getInvoiceForm());
		tar.setInvoiceStatus(src.getInvoiceStatus());
		tar.setInvoiceTitleType(src.getInvoiceTitleType());
		tar.setInvoiceTitleContent(src.getInvoiceTitleContent());
		tar.setIsNeedDetails(src.getIsNeedDetails());
		tar.setInvoiceContentType(src.getInvoiceContentType());
		tar.setInvoiceContent(src.getInvoiceContent());
		tar.setInvoiceRemark(src.getInvoiceRemark());
		tar.setTaxpayerIdentificationCode(src.getTaxpayerIdentificationCode());
		tar.setReceiverMail(src.getReceiverMail());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setRegisterAddr(src.getRegisterAddr());
		tar.setRegisterTel(src.getRegisterTel());
		tar.setDepositBank(src.getDepositBank());
		tar.setBankAccount(src.getBankAccount());
		tar.setBusinessLicenceUrl(src.getBusinessLicenceUrl());
		tar.setInvoiceAttcUrl(src.getInvoiceAttcUrl());
		tar.setInvoiceId(src.getInvoiceId());
		return tar;
	}

	public static SoInvoicePO toPO(SoInvoiceDTO src) {
		if (src == null)
		return null;	
		SoInvoicePO tar = new SoInvoicePO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setSoId(src.getSoId());
		tar.setSoChildId(src.getSoChildId());
		tar.setInvoiceValue(src.getInvoiceValue());
		tar.setInvoiceCode(src.getInvoiceCode());
		tar.setInvoiceTaxType(src.getInvoiceTaxType());
		tar.setInvoiceFinType(src.getInvoiceFinType());
		tar.setInvoiceForm(src.getInvoiceForm());
		tar.setInvoiceStatus(src.getInvoiceStatus());
		tar.setInvoiceTitleType(src.getInvoiceTitleType());
		tar.setInvoiceTitleContent(src.getInvoiceTitleContent());
		tar.setIsNeedDetails(src.getIsNeedDetails());
		tar.setInvoiceContentType(src.getInvoiceContentType());
		tar.setInvoiceContent(src.getInvoiceContent());
		tar.setInvoiceRemark(src.getInvoiceRemark());
		tar.setTaxpayerIdentificationCode(src.getTaxpayerIdentificationCode());
		tar.setReceiverMail(src.getReceiverMail());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setRegisterAddr(src.getRegisterAddr());
		tar.setRegisterTel(src.getRegisterTel());
		tar.setDepositBank(src.getDepositBank());
		tar.setBankAccount(src.getBankAccount());
		tar.setBusinessLicenceUrl(src.getBusinessLicenceUrl());
		tar.setInvoiceAttcUrl(src.getInvoiceAttcUrl());
		tar.setInvoiceId(src.getInvoiceId());
		return tar;
	}

	public static List<SoInvoiceDTO> toDTO(List<SoInvoicePO> srcs) {
		if (srcs == null)
			return null;
		List<SoInvoiceDTO> list = new ArrayList<SoInvoiceDTO>();
		for (SoInvoicePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoInvoicePO> toPO(List<SoInvoiceDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoInvoicePO> list = new ArrayList<SoInvoicePO>();
		for (SoInvoiceDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	