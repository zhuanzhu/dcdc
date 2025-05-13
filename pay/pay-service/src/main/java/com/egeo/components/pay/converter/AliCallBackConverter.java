package com.egeo.components.pay.converter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import com.egeo.components.pay.vo.AliCallBackVo;
import com.egeo.components.pay.dto.PayAliLogDTO;

public class AliCallBackConverter {

	/**
	 * request vo转dto
	 * @param log_
	 * @return
	 */
	public static PayAliLogDTO toDTO(AliCallBackVo src) {
		PayAliLogDTO tar=new PayAliLogDTO();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		tar.setAppId(src.getAppId());
		tar.setBuyerId(src.getBuyerId());
		tar.setCharset(src.getCharset());
		try {
			//tar.setCreateTime(sdf.parse(src.getGmtCreate()));
			tar.setGmtCreate(sdf.parse(src.getGmtCreate()));
			tar.setGmtPayment(sdf.parse(src.getGmtPayment()));
			tar.setNotifyTime(sdf.parse(src.getNotifyTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		tar.setNotifyId(src.getNotifyId());
		tar.setNotifyType(src.getNotifyType());
		tar.setOutTradeNo(src.getOutTradeNo());
		tar.setReceiptAmount(new BigDecimal(src.getReceiptAmount()));
		tar.setSellerEmail(src.getSellerEmail());
		tar.setSellerId(src.getSellerId());
		tar.setSign(src.getSign());
		tar.setSignType(src.getSignType());
		tar.setSubject(src.getSubject());
		tar.setTotalAmount(new BigDecimal(src.getTotalAmount()));
		tar.setTradeNo(src.getTradeNo());
		tar.setTradeStatus(src.getTradeStatus());
		tar.setVersion(src.getVersion());
		return tar;
	}

	
}
