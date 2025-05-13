package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.DeliveryImportExcelDTO;
import com.egeo.components.order.po.DeliveryImportExcelPO;
import com.egeo.components.order.vo.DeliveryImportExcelVO;

public class DeliveryImportExcelConverter {
	
	

	public static DeliveryImportExcelDTO toDTO(DeliveryImportExcelVO src) {
		if(src==null)
			return null;
		DeliveryImportExcelDTO tar=new DeliveryImportExcelDTO();
		tar.setBoxCode(src.getBoxCode());
		tar.setChildCode(src.getChildCode());
		tar.setDeliveryCode(src.getDeliveryCode());
		tar.setDeliveryCompany(src.getDeliveryCompany());
		tar.setDeliveryCompanyId(src.getDeliveryCompanyId());
		tar.setOrderCode(src.getOrderCode());
		tar.setSoChildId(src.getSoChildId());
		tar.setSoId(src.getSoId());
		tar.setReceiverAddressId(src.getReceiverAddressId());
		tar.setUserId(src.getUserId());
		return tar;
	}
	
	public static DeliveryImportExcelVO toVO(DeliveryImportExcelDTO src) {
		if(src==null)
			return null;
		DeliveryImportExcelVO tar=new DeliveryImportExcelVO();
		tar.setBoxCode(src.getBoxCode());
		tar.setChildCode(src.getChildCode());
		tar.setDeliveryCode(src.getDeliveryCode());
		tar.setDeliveryCompany(src.getDeliveryCompany());
		tar.setDeliveryCompanyId(src.getDeliveryCompanyId());
		tar.setOrderCode(src.getOrderCode());
		tar.setSoChildId(src.getSoChildId());
		tar.setSoId(src.getSoId());
		tar.setReceiverAddressId(src.getReceiverAddressId());
		tar.setUserId(src.getUserId());
		return tar;
	}
	
	public static List<DeliveryImportExcelDTO> toDTOs(List<DeliveryImportExcelVO> srcs) {
		List<DeliveryImportExcelDTO> list = new ArrayList<>();
		for (DeliveryImportExcelVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<DeliveryImportExcelVO> toVO(List<DeliveryImportExcelDTO> srcs) {
		List<DeliveryImportExcelVO> list = new ArrayList<>();
		for (DeliveryImportExcelDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}

	public static DeliveryImportExcelPO toPO(DeliveryImportExcelDTO src) {
		if(src==null)
			return null;
		DeliveryImportExcelPO tar=new DeliveryImportExcelPO();
		tar.setBoxCode(src.getBoxCode());
		tar.setChildCode(src.getChildCode());
		tar.setDeliveryCode(src.getDeliveryCode());
		tar.setDeliveryCompany(src.getDeliveryCompany());
		tar.setDeliveryCompanyId(src.getDeliveryCompanyId());
		tar.setOrderCode(src.getOrderCode());
		tar.setSoChildId(src.getSoChildId());
		tar.setSoId(src.getSoId());
		tar.setReceiverAddressId(src.getReceiverAddressId());
		tar.setUserId(src.getUserId());
		return tar;
	}
	
	public static List<DeliveryImportExcelPO> toPO(List<DeliveryImportExcelDTO> srcs) {
		List<DeliveryImportExcelPO> list = new ArrayList<>();
		for (DeliveryImportExcelDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}

}
