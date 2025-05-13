package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.InvoiceDTO;
import com.egeo.components.order.po.InvoicePO;
import com.egeo.components.order.vo.InvoiceVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author wyy
 * @date 2018-08-10 16:53:30
 */
public class InvoiceConverter {

	public static InvoiceDTO toDTO(InvoiceVO src) {
		if (src == null)
		return null;	
		InvoiceDTO tar = new InvoiceDTO();
		tar.setId(src.getId());
		tar.setTitleContent(src.getTitleContent());	
		tar.setTaxpayerIdentificationCode(src.getTaxpayerIdentificationCode());	
		tar.setRegisterAddr(src.getRegisterAddr());	
		tar.setRegisterTel(src.getRegisterTel());	
		tar.setDepositBank(src.getDepositBank());	
		tar.setBankAccount(src.getBankAccount());	
		tar.setBusinessLicenceUrl(src.getBusinessLicenceUrl());	
		tar.setUserId(src.getUserId());	
		tar.setIsDefault(src.getIsDefault());	
		tar.setCreateType(src.getCreateType());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setTitleType(src.getTitleType());
		tar.setIsDelete(src.getIsDelete());
		return tar;
	}

	public static InvoiceVO toVO(InvoiceDTO src) {
		if (src == null)
		return null;	
		InvoiceVO tar = new InvoiceVO();
		tar.setId(src.getId());
		tar.setTitleContent(src.getTitleContent());	
		tar.setTaxpayerIdentificationCode(src.getTaxpayerIdentificationCode());	
		tar.setRegisterAddr(src.getRegisterAddr());	
		tar.setRegisterTel(src.getRegisterTel());	
		tar.setDepositBank(src.getDepositBank());	
		tar.setBankAccount(src.getBankAccount());	
		tar.setBusinessLicenceUrl(src.getBusinessLicenceUrl());	
		tar.setUserId(src.getUserId());	
		tar.setIsDefault(src.getIsDefault());	
		tar.setCreateType(src.getCreateType());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setTitleType(src.getTitleType());
		tar.setIsDelete(src.getIsDelete());
		return tar;
	}

	public static List<InvoiceDTO> toDTOs(List<InvoiceVO> srcs) {
		if (srcs == null)
			return null;
		List<InvoiceDTO> list = new ArrayList<InvoiceDTO>();
		for (InvoiceVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<InvoiceVO> toVO(List<InvoiceDTO> srcs) {
		if (srcs == null)
			return null;
		List<InvoiceVO> list = new ArrayList<InvoiceVO>();
		for (InvoiceDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static InvoiceDTO toDTO(InvoicePO src) {
		if (src == null)
		return null;	
		InvoiceDTO tar = new InvoiceDTO();
		tar.setId(src.getId());
		tar.setTitleContent(src.getTitleContent());
		tar.setTaxpayerIdentificationCode(src.getTaxpayerIdentificationCode());
		tar.setRegisterAddr(src.getRegisterAddr());
		tar.setRegisterTel(src.getRegisterTel());
		tar.setDepositBank(src.getDepositBank());
		tar.setBankAccount(src.getBankAccount());
		tar.setBusinessLicenceUrl(src.getBusinessLicenceUrl());
		tar.setUserId(src.getUserId());
		tar.setIsDefault(src.getIsDefault());
		tar.setCreateType(src.getCreateType());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setTitleType(src.getTitleType());
		tar.setIsDelete(src.getIsDelete());
		return tar;
	}

	public static InvoicePO toPO(InvoiceDTO src) {
		if (src == null)
		return null;	
		InvoicePO tar = new InvoicePO();
		tar.setId(src.getId());
		tar.setTitleContent(src.getTitleContent());
		tar.setTaxpayerIdentificationCode(src.getTaxpayerIdentificationCode());
		tar.setRegisterAddr(src.getRegisterAddr());
		tar.setRegisterTel(src.getRegisterTel());
		tar.setDepositBank(src.getDepositBank());
		tar.setBankAccount(src.getBankAccount());
		tar.setBusinessLicenceUrl(src.getBusinessLicenceUrl());
		tar.setUserId(src.getUserId());
		tar.setIsDefault(src.getIsDefault());
		tar.setCreateType(src.getCreateType());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setTitleType(src.getTitleType());
		tar.setIsDelete(src.getIsDelete());
		return tar;
	}

	public static List<InvoiceDTO> toDTO(List<InvoicePO> srcs) {
		if (srcs == null)
			return null;
		List<InvoiceDTO> list = new ArrayList<InvoiceDTO>();
		for (InvoicePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<InvoicePO> toPO(List<InvoiceDTO> srcs) {
		if (srcs == null)
			return null;
		List<InvoicePO> list = new ArrayList<InvoicePO>();
		for (InvoiceDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	