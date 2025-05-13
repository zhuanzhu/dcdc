package com.egeo.components.pay.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.pay.dto.PayAliLogDTO;
import com.egeo.components.pay.po.PayAliLogPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jiang
 * @date 2017-09-06 11:21:44
 */
public class PayAliLogConverter {
	
	public static PayAliLogDTO toDTO(PayAliLogPO src) {
		PayAliLogDTO tar = new PayAliLogDTO();
		tar.setId(src.getId());
		tar.setNotifyTime(src.getNotifyTime());
		tar.setNotifyType(src.getNotifyType());
		tar.setNotifyId(src.getNotifyId());
		tar.setAppId(src.getAppId());
		tar.setCharset(src.getCharset());
		tar.setVersion(src.getVersion());
		tar.setSignType(src.getSignType());
		tar.setSign(src.getSign());
		tar.setTradeNo(src.getTradeNo());
		tar.setOutTradeNo(src.getOutTradeNo());
		tar.setBuyerId(src.getBuyerId());
		tar.setSellerId(src.getSellerId());
		tar.setSellerEmail(src.getSellerEmail());
		tar.setTradeStatus(src.getTradeStatus());
		tar.setTotalAmount(src.getTotalAmount());
		tar.setReceiptAmount(src.getReceiptAmount());
		tar.setSubject(src.getSubject());
		tar.setGmtCreate(src.getGmtCreate());
		tar.setGmtPayment(src.getGmtPayment());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static PayAliLogPO toPO(PayAliLogDTO src) {
		PayAliLogPO tar = new PayAliLogPO();
		tar.setId(src.getId());
		tar.setNotifyTime(src.getNotifyTime());
		tar.setNotifyType(src.getNotifyType());
		tar.setNotifyId(src.getNotifyId());
		tar.setAppId(src.getAppId());
		tar.setCharset(src.getCharset());
		tar.setVersion(src.getVersion());
		tar.setSignType(src.getSignType());
		tar.setSign(src.getSign());
		tar.setTradeNo(src.getTradeNo());
		tar.setOutTradeNo(src.getOutTradeNo());
		tar.setBuyerId(src.getBuyerId());
		tar.setSellerId(src.getSellerId());
		tar.setSellerEmail(src.getSellerEmail());
		tar.setTradeStatus(src.getTradeStatus());
		tar.setTotalAmount(src.getTotalAmount());
		tar.setReceiptAmount(src.getReceiptAmount());
		tar.setSubject(src.getSubject());
		tar.setGmtCreate(src.getGmtCreate());
		tar.setGmtPayment(src.getGmtPayment());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<PayAliLogDTO> toDTO(List<PayAliLogPO> srcs) {
		if (srcs == null)
			return null;
		List<PayAliLogDTO> list = new ArrayList<PayAliLogDTO>();
		for (PayAliLogPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PayAliLogPO> toPO(List<PayAliLogDTO> srcs) {
		if (srcs == null)
			return null;
		List<PayAliLogPO> list = new ArrayList<PayAliLogPO>();
		for (PayAliLogDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	