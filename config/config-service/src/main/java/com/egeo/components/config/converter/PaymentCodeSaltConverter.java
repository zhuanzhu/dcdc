package com.egeo.components.config.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.config.dto.PaymentCodeSaltDTO;
import com.egeo.components.config.po.PaymentCodeSaltPO;
import com.egeo.components.config.vo.PaymentCodeSaltVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-14 10:33:05
 */
public class PaymentCodeSaltConverter {
	
	public static PaymentCodeSaltDTO toDTO(PaymentCodeSaltPO src) {
		if (src == null)
		return null;	
		PaymentCodeSaltDTO tar = new PaymentCodeSaltDTO();
		tar.setId(src.getId());
		tar.setPaymentCodeUuid(src.getPaymentCodeUuid());
		tar.setSaltValue(src.getSaltValue());
		return tar;
	}

	public static PaymentCodeSaltDTO toDTO(PaymentCodeSaltVO src) {
		if (src == null)
		return null;	
		PaymentCodeSaltDTO tar = new PaymentCodeSaltDTO();
		tar.setId(src.getId());
		tar.setPaymentCodeUuid(src.getPaymentCodeUuid());
		tar.setSaltValue(src.getSaltValue());
		return tar;
	}
	public static PaymentCodeSaltVO toVO(PaymentCodeSaltDTO src) {
		if (src == null)
		return null;	
		PaymentCodeSaltVO tar = new PaymentCodeSaltVO();
		tar.setId(src.getId());
		tar.setPaymentCodeUuid(src.getPaymentCodeUuid());
		tar.setSaltValue(src.getSaltValue());
		return tar;
	}
	public static List<PaymentCodeSaltVO> toVO(List<PaymentCodeSaltDTO> srcs) {
		if (srcs == null)
			return null;
		List<PaymentCodeSaltVO> list = new ArrayList<PaymentCodeSaltVO>();
		for (PaymentCodeSaltDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
		
	}
	public static PaymentCodeSaltPO toPO(PaymentCodeSaltDTO src) {
		if (src == null)
		return null;	
		PaymentCodeSaltPO tar = new PaymentCodeSaltPO();
		tar.setId(src.getId());
		tar.setPaymentCodeUuid(src.getPaymentCodeUuid());
		tar.setSaltValue(src.getSaltValue());
		return tar;
	}

	public static List<PaymentCodeSaltDTO> toDTO(List<PaymentCodeSaltPO> srcs) {
		if (srcs == null)
			return null;
		List<PaymentCodeSaltDTO> list = new ArrayList<PaymentCodeSaltDTO>();
		for (PaymentCodeSaltPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PaymentCodeSaltPO> toPO(List<PaymentCodeSaltDTO> srcs) {
		if (srcs == null)
			return null;
		List<PaymentCodeSaltPO> list = new ArrayList<PaymentCodeSaltPO>();
		for (PaymentCodeSaltDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	