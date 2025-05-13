package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoTrackDTO;
import com.egeo.components.order.po.SoTrackPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-19 15:50:25
 */
public class SoTrackConverter {
	
	public static SoTrackDTO toDTO(SoTrackPO src) {
		SoTrackDTO tar = new SoTrackDTO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setOrderCreateTime(src.getOrderCreateTime());
		tar.setUserId(src.getUserId());
		tar.setMerchantId(src.getMerchantId());
		tar.setTrackType(src.getTrackType());
		tar.setOperateNo(src.getOperateNo());
		tar.setOperateAttr(src.getOperateAttr());
		tar.setOperateContent(src.getOperateContent());
		tar.setRemark(src.getRemark());
		tar.setOperateType(src.getOperateType());
		tar.setOperatorId(src.getOperatorId());
		tar.setOperatorName(src.getOperatorName());
		tar.setSourceSystem(src.getSourceSystem());
		tar.setTrackLevel(src.getTrackLevel());
		tar.setDeliveryCompanyId(src.getDeliveryCompanyId());
		tar.setDeliveryCompanyName(src.getDeliveryCompanyName());
		tar.setDeliveryCompanyShortName(src.getDeliveryCompanyShortName());
		tar.setDeliveryCompanyUrl(src.getDeliveryCompanyUrl());
		tar.setDeliveryExpressNbr(src.getDeliveryExpressNbr());
		tar.setDeliverierName(src.getDeliverierName());
		tar.setDeliverierMobile(src.getDeliverierMobile());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static SoTrackPO toPO(SoTrackDTO src) {
		SoTrackPO tar = new SoTrackPO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setOrderCreateTime(src.getOrderCreateTime());
		tar.setUserId(src.getUserId());
		tar.setMerchantId(src.getMerchantId());
		tar.setTrackType(src.getTrackType());
		tar.setOperateNo(src.getOperateNo());
		tar.setOperateAttr(src.getOperateAttr());
		tar.setOperateContent(src.getOperateContent());
		tar.setRemark(src.getRemark());
		tar.setOperateType(src.getOperateType());
		tar.setOperatorId(src.getOperatorId());
		tar.setOperatorName(src.getOperatorName());
		tar.setSourceSystem(src.getSourceSystem());
		tar.setTrackLevel(src.getTrackLevel());
		tar.setDeliveryCompanyId(src.getDeliveryCompanyId());
		tar.setDeliveryCompanyName(src.getDeliveryCompanyName());
		tar.setDeliveryCompanyShortName(src.getDeliveryCompanyShortName());
		tar.setDeliveryCompanyUrl(src.getDeliveryCompanyUrl());
		tar.setDeliveryExpressNbr(src.getDeliveryExpressNbr());
		tar.setDeliverierName(src.getDeliverierName());
		tar.setDeliverierMobile(src.getDeliverierMobile());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<SoTrackDTO> toDTO(List<SoTrackPO> srcs) {
		if (srcs == null)
			return null;
		List<SoTrackDTO> list = new ArrayList<SoTrackDTO>();
		for (SoTrackPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoTrackPO> toPO(List<SoTrackDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoTrackPO> list = new ArrayList<SoTrackPO>();
		for (SoTrackDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	