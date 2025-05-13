package com.egeo.components.pay.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.pay.dto.PayWeixinLogDTO;
import com.egeo.components.pay.po.PayWeixinLogPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jiang
 * @date 2017-09-06 11:21:45
 */
public class PayWeixinLogConverter {
	
	public static PayWeixinLogDTO toDTO(PayWeixinLogPO src) {
		if (src == null)
			return null;
		PayWeixinLogDTO tar = new PayWeixinLogDTO();
		tar.setId(src.getId());
		tar.setReturnCode(src.getReturnCode());
		tar.setReturnMsg(src.getReturnMsg());
		tar.setAppid(src.getAppid());
		tar.setMchId(src.getMchId());
		tar.setDeviceInfo(src.getDeviceInfo());
		tar.setNonceStr(src.getNonceStr());
		tar.setSign(src.getSign());
		tar.setSignType(src.getSignType());
		tar.setResultCode(src.getResultCode());
		tar.setErrCode(tar.getErrCode());
		tar.setErrCodeDes(tar.getErrCodeDes());
		tar.setOpenid(tar.getOpenid());
		tar.setIsSubscribe(tar.getIsSubscribe());
		tar.setTradeType(src.getTradeType());
		tar.setBankType(src.getBankType());
		tar.setTotalFee(src.getTotalFee());
		tar.setPrepayId(src.getPrepayId());
		tar.setMwebUrl(src.getMwebUrl());
		tar.setCashFee(src.getCashFee());
		tar.setTransactionId(src.getTransactionId());
		tar.setOutTradeNo(src.getOutTradeNo());
		tar.setTimeEnd(src.getTimeEnd());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static PayWeixinLogPO toPO(PayWeixinLogDTO src) {
		if (src == null)
			return null;
		PayWeixinLogPO tar = new PayWeixinLogPO();
		tar.setId(src.getId());
		tar.setReturnCode(src.getReturnCode());
		tar.setReturnMsg(src.getReturnMsg());
		tar.setAppid(src.getAppid());
		tar.setMchId(src.getMchId());
		tar.setDeviceInfo(src.getDeviceInfo());
		tar.setNonceStr(src.getNonceStr());
		tar.setSign(src.getSign());
		tar.setSignType(src.getSignType());
		tar.setResultCode(src.getResultCode());
		tar.setErrCode(src.getErrCode());
		tar.setErrCodeDes(src.getErrCodeDes());
		tar.setOpenid(src.getOpenid());
		tar.setIsSubscribe(src.getIsSubscribe());
		tar.setTradeType(src.getTradeType());
		tar.setBankType(src.getBankType());
		tar.setTotalFee(src.getTotalFee());
		tar.setPrepayId(src.getPrepayId());
		tar.setMwebUrl(src.getMwebUrl());
		tar.setCashFee(src.getCashFee());
		tar.setTransactionId(src.getTransactionId());
		tar.setOutTradeNo(src.getOutTradeNo());
		tar.setTimeEnd(src.getTimeEnd());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<PayWeixinLogDTO> toDTO(List<PayWeixinLogPO> srcs) {
		if (srcs == null)
			return null;
		List<PayWeixinLogDTO> list = new ArrayList<PayWeixinLogDTO>();
		for (PayWeixinLogPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PayWeixinLogPO> toPO(List<PayWeixinLogDTO> srcs) {
		if (srcs == null)
			return null;
		List<PayWeixinLogPO> list = new ArrayList<PayWeixinLogPO>();
		for (PayWeixinLogDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	