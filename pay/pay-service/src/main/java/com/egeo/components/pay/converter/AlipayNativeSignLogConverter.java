package com.egeo.components.pay.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.pay.dto.AlipayNativeSignLogDTO;
import com.egeo.components.pay.po.AlipayNativeSignLogPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jzh
 * @date 2018-05-17 18:19:01
 */
public class AlipayNativeSignLogConverter {
	
	public static AlipayNativeSignLogDTO toDTO(AlipayNativeSignLogPO src) {
		if (src == null)
		return null;	
		AlipayNativeSignLogDTO tar = new AlipayNativeSignLogDTO();
		tar.setId(src.getId());
		tar.setSign(src.getSign());
		tar.setCode(src.getCode());
		tar.setMsg(src.getMsg());
		tar.setOutTradeNo(src.getOutTradeNo());
		tar.setTradeNo(src.getTradeNo());
		tar.setTotalAmount(src.getTotalAmount());
		tar.setSellerId(src.getSellerId());
		tar.setSubCode(src.getSubCode());
		tar.setSubMsg(src.getSubMsg());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static AlipayNativeSignLogPO toPO(AlipayNativeSignLogDTO src) {
		if (src == null)
		return null;	
		AlipayNativeSignLogPO tar = new AlipayNativeSignLogPO();
		tar.setId(src.getId());
		tar.setSign(src.getSign());
		tar.setCode(src.getCode());
		tar.setMsg(src.getMsg());
		tar.setOutTradeNo(src.getOutTradeNo());
		tar.setTradeNo(src.getTradeNo());
		tar.setTotalAmount(src.getTotalAmount());
		tar.setSellerId(src.getSellerId());
		tar.setSubCode(src.getSubCode());
		tar.setSubMsg(src.getSubMsg());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<AlipayNativeSignLogDTO> toDTO(List<AlipayNativeSignLogPO> srcs) {
		if (srcs == null)
			return null;
		List<AlipayNativeSignLogDTO> list = new ArrayList<AlipayNativeSignLogDTO>();
		for (AlipayNativeSignLogPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<AlipayNativeSignLogPO> toPO(List<AlipayNativeSignLogDTO> srcs) {
		if (srcs == null)
			return null;
		List<AlipayNativeSignLogPO> list = new ArrayList<AlipayNativeSignLogPO>();
		for (AlipayNativeSignLogDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	