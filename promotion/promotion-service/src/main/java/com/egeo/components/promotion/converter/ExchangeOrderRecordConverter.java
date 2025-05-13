package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.ExchangeOrderRecordDTO;
import com.egeo.components.promotion.po.ExchangeOrderRecordPO;
import com.egeo.components.promotion.vo.ExchangeOrderRecordVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author feng
 * @date 2018-12-14 10:57:18
 */
public class ExchangeOrderRecordConverter {

	
	public static ExchangeOrderRecordDTO toDTO(ExchangeOrderRecordVO src) {
		if (src == null)
		return null;	
		ExchangeOrderRecordDTO tar = new ExchangeOrderRecordDTO();
		tar.setId(src.getId());
		tar.setExchangeId(src.getExchangeId());	
		tar.setExchangeName(src.getExchangeName());	
		tar.setOldUnitCode(src.getOldUnitCode());	
		tar.setOldUnitStatus(src.getOldUnitStatus());	
		tar.setOldCouponName(src.getOldCouponName());	
		tar.setOldBatchCode(src.getOldBatchCode());	
		tar.setOldCouponType(src.getOldCouponType());	
		tar.setNewUnitCode(src.getNewUnitCode());	
		tar.setNewBatchCode(src.getNewBatchCode());	
		tar.setNewCouponName(src.getNewCouponName());	
		tar.setNewCouponType(src.getNewCouponType());	
		tar.setUserMail(src.getUserMail());	
		tar.setExchangeTime(src.getExchangeTime());	
		tar.setOrderCode(src.getOrderCode());	
		tar.setConversionStatus(src.getConversionStatus());
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTimeStr(src.getCreateTimeStr());
		tar.setUpdateTimeStr(src.getUpdateTimeStr());
		tar.setOrderAmount(src.getOrderAmount());
		tar.setOldCouponBatchName(src.getOldCouponBatchName());
		tar.setNewCouponBatchName(src.getNewCouponBatchName());
		return tar;
	}

	public static ExchangeOrderRecordVO toVO(ExchangeOrderRecordDTO src) {
		if (src == null)
		return null;	
		ExchangeOrderRecordVO tar = new ExchangeOrderRecordVO();
		tar.setId(src.getId());
		tar.setExchangeId(src.getExchangeId());	
		tar.setExchangeName(src.getExchangeName());	
		tar.setOldUnitCode(src.getOldUnitCode());	
		tar.setOldUnitStatus(src.getOldUnitStatus());	
		tar.setOldCouponName(src.getOldCouponName());	
		tar.setOldBatchCode(src.getOldBatchCode());	
		tar.setOldCouponType(src.getOldCouponType());	
		tar.setNewUnitCode(src.getNewUnitCode());	
		tar.setNewBatchCode(src.getNewBatchCode());	
		tar.setNewCouponName(src.getNewCouponName());	
		tar.setNewCouponType(src.getNewCouponType());	
		tar.setUserMail(src.getUserMail());	
		tar.setExchangeTime(src.getExchangeTime());	
		tar.setOrderCode(src.getOrderCode());	
		tar.setConversionStatus(src.getConversionStatus());
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTimeStr(src.getCreateTimeStr());
		tar.setUpdateTimeStr(src.getUpdateTimeStr());
		tar.setOrderAmount(src.getOrderAmount());
		tar.setOldCouponBatchName(src.getOldCouponBatchName());
		tar.setNewCouponBatchName(src.getNewCouponBatchName());
		return tar;
	}

	public static List<ExchangeOrderRecordDTO> toDTOs(List<ExchangeOrderRecordVO> srcs) {
		if (srcs == null)
			return null;
		List<ExchangeOrderRecordDTO> list = new ArrayList<ExchangeOrderRecordDTO>();
		for (ExchangeOrderRecordVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ExchangeOrderRecordVO> toVO(List<ExchangeOrderRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<ExchangeOrderRecordVO> list = new ArrayList<ExchangeOrderRecordVO>();
		for (ExchangeOrderRecordDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static ExchangeOrderRecordDTO toDTO(ExchangeOrderRecordPO src) {
		if (src == null)
		return null;	
		ExchangeOrderRecordDTO tar = new ExchangeOrderRecordDTO();
		tar.setId(src.getId());
		tar.setExchangeId(src.getExchangeId());
		tar.setExchangeName(src.getExchangeName());
		tar.setOldUnitCode(src.getOldUnitCode());
		tar.setOldUnitStatus(src.getOldUnitStatus());
		tar.setOldCouponName(src.getOldCouponName());
		tar.setOldBatchCode(src.getOldBatchCode());
		tar.setOldCouponType(src.getOldCouponType());
		tar.setNewUnitCode(src.getNewUnitCode());
		tar.setNewBatchCode(src.getNewBatchCode());
		tar.setNewCouponName(src.getNewCouponName());
		tar.setNewCouponType(src.getNewCouponType());
		tar.setUserMail(src.getUserMail());
		tar.setExchangeTime(src.getExchangeTime());
		tar.setOrderCode(src.getOrderCode());
		tar.setConversionStatus(src.getConversionStatus());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setOrderAmount(src.getOrderAmount());
		tar.setOldCouponBatchName(src.getOldCouponBatchName());
		tar.setNewCouponBatchName(src.getNewCouponBatchName());
		return tar;
	}

	public static ExchangeOrderRecordPO toPO(ExchangeOrderRecordDTO src) {
		if (src == null)
		return null;	
		ExchangeOrderRecordPO tar = new ExchangeOrderRecordPO();
		tar.setId(src.getId());
		tar.setExchangeId(src.getExchangeId());
		tar.setExchangeName(src.getExchangeName());
		tar.setOldUnitCode(src.getOldUnitCode());
		tar.setOldUnitStatus(src.getOldUnitStatus());
		tar.setOldCouponName(src.getOldCouponName());
		tar.setOldBatchCode(src.getOldBatchCode());
		tar.setOldCouponType(src.getOldCouponType());
		tar.setNewUnitCode(src.getNewUnitCode());
		tar.setNewBatchCode(src.getNewBatchCode());
		tar.setNewCouponName(src.getNewCouponName());
		tar.setNewCouponType(src.getNewCouponType());
		tar.setUserMail(src.getUserMail());
		tar.setExchangeTime(src.getExchangeTime());
		tar.setOrderCode(src.getOrderCode());
		tar.setConversionStatus(src.getConversionStatus());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setOrderAmount(src.getOrderAmount());
		tar.setOldCouponBatchName(src.getOldCouponBatchName());
		tar.setNewCouponBatchName(src.getNewCouponBatchName());
		return tar;
	}

	public static List<ExchangeOrderRecordDTO> toDTO(List<ExchangeOrderRecordPO> srcs) {
		if (srcs == null)
			return null;
		List<ExchangeOrderRecordDTO> list = new ArrayList<ExchangeOrderRecordDTO>();
		for (ExchangeOrderRecordPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ExchangeOrderRecordPO> toPO(List<ExchangeOrderRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<ExchangeOrderRecordPO> list = new ArrayList<ExchangeOrderRecordPO>();
		for (ExchangeOrderRecordDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	