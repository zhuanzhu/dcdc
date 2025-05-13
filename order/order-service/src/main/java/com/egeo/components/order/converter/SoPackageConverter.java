package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.condition.SoPackageCondition;
import com.egeo.components.order.dto.SoPackageDTO;
import com.egeo.components.order.po.SoPackagePO;
import com.egeo.components.order.vo.SoPackageVO;
import com.egeo.components.order.vo.SoPackageView;

/**
 * DTO和PO相互转换工具类
 * 
 * @author ghw
 * @date 2018-02-03 20:01:09
 */
public class SoPackageConverter {

	public static SoPackageDTO toDTO(SoPackageVO src) {
		if (src == null)
		return null;	
		SoPackageDTO tar = new SoPackageDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());	
		tar.setSoId(src.getSoId());	
		tar.setSoChildId(src.getSoChildId());	
		tar.setOrderCode(src.getOrderCode());	
		tar.setMerchantId(src.getMerchantId());	
		tar.setSoChildDeliveryFee(src.getSoChildDeliveryFee());	
		tar.setPackageType(src.getPackageType());	
		tar.setDeliveryCode(src.getDeliveryCode());	
		tar.setDeliveryStatus(src.getDeliveryStatus());	
		tar.setDeliveryMode(src.getDeliveryMode());	
		tar.setDeliveryCompanyId(src.getDeliveryCompanyId());	
		tar.setDeliveryCompanyName(src.getDeliveryCompanyName());	
		tar.setDeliveryMessage(src.getDeliveryMessage());	
//		tar.setDeliveryExpressNbr(src.getDeliveryExpressNbr());	
		tar.setOrderDeliveryMethodId(src.getOrderDeliveryMethodId());	
		tar.setGoodReceiverName(src.getGoodReceiverName());	
		tar.setGoodReceiverMobile(src.getGoodReceiverMobile());	
		tar.setProCityArea(src.getProCityArea());	
		tar.setDeliveryDate(src.getDeliveryDate());	
		tar.setGoodReceiverAddress(src.getGoodReceiverAddress());	
		tar.setDeliveryName(src.getDeliveryName());	
		tar.setDeliveryNameMobile(src.getDeliveryNameMobile());	
		tar.setSignName(src.getSignName());	
		tar.setSignDate(src.getSignDate());	
		tar.setSignRemark(src.getSignRemark());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static SoPackageVO toVO(SoPackageDTO src) {
		if (src == null)
		return null;	
		SoPackageVO tar = new SoPackageVO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());	
		tar.setSoId(src.getSoId());	
		tar.setSoChildId(src.getSoChildId());	
		tar.setOrderCode(src.getOrderCode());	
		tar.setMerchantId(src.getMerchantId());	
		tar.setSoChildDeliveryFee(src.getSoChildDeliveryFee());	
		tar.setPackageType(src.getPackageType());	
		tar.setDeliveryCode(src.getDeliveryCode());	
		tar.setDeliveryStatus(src.getDeliveryStatus());	
		tar.setDeliveryMode(src.getDeliveryMode());	
		tar.setDeliveryCompanyId(src.getDeliveryCompanyId());	
		tar.setDeliveryCompanyName(src.getDeliveryCompanyName());	
		tar.setDeliveryMessage(src.getDeliveryMessage());	
//		tar.setDeliveryExpressNbr(src.getDeliveryExpressNbr());	
		tar.setOrderDeliveryMethodId(src.getOrderDeliveryMethodId());	
		tar.setGoodReceiverName(src.getGoodReceiverName());	
		tar.setGoodReceiverMobile(src.getGoodReceiverMobile());	
		tar.setProCityArea(src.getProCityArea());	
		tar.setDeliveryDate(src.getDeliveryDate());	
		tar.setGoodReceiverAddress(src.getGoodReceiverAddress());	
		tar.setDeliveryName(src.getDeliveryName());	
		tar.setDeliveryNameMobile(src.getDeliveryNameMobile());	
		tar.setSignName(src.getSignName());	
		tar.setSignDate(src.getSignDate());	
		tar.setSignRemark(src.getSignRemark());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}
	public static SoPackageView toView(SoPackageDTO src) {
		if (src == null)
			return null;	
		SoPackageView tar = new SoPackageView();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());	
		tar.setSoChildDeliveryFee(src.getSoChildDeliveryFee());	
		tar.setDeliveryCode(src.getDeliveryCode());	
		tar.setDeliveryStatus(src.getDeliveryStatus());	
		tar.setDeliveryCompanyName(src.getDeliveryCompanyName());	
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSoBoxCode(src.getSoBoxCode()+"");
		return tar;
	}

	public static List<SoPackageDTO> toDTOs(List<SoPackageVO> srcs) {
		if (srcs == null)
			return null;
		List<SoPackageDTO> list = new ArrayList<SoPackageDTO>();
		for (SoPackageVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoPackageDTO> toDTOs2(List<SoPackagePO> srcs) {
		if (srcs == null)
			return null;
		List<SoPackageDTO> list = new ArrayList<SoPackageDTO>();
		for (SoPackagePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}
	public static List<SoPackageVO> toVO(List<SoPackageDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoPackageVO> list = new ArrayList<SoPackageVO>();
		for (SoPackageDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static List<SoPackageView> toView(List<SoPackageDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoPackageView> list = new ArrayList<SoPackageView>();
		for (SoPackageDTO src : srcs) {
			list.add(toView(src));
		}
		return list;
	}
	
	
	
	
	public static SoPackageDTO toDTO(SoPackagePO src) {
		if (src == null)
		return null;	
		SoPackageDTO tar = new SoPackageDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setSoId(src.getSoId());
		tar.setSoChildId(src.getSoChildId());
		tar.setOrderCode(src.getOrderCode());
		tar.setMerchantId(src.getMerchantId());
		tar.setSoChildDeliveryFee(src.getSoChildDeliveryFee());
		tar.setPackageType(src.getPackageType());
		tar.setDeliveryCode(src.getDeliveryCode());
		tar.setDeliveryStatus(src.getDeliveryStatus());
		tar.setDeliveryMode(src.getDeliveryMode());
		tar.setDeliveryCompanyId(src.getDeliveryCompanyId());
		tar.setDeliveryCompanyName(src.getDeliveryCompanyName());
		tar.setDeliveryMessage(src.getDeliveryMessage());
//		tar.setDeliveryExpressNbr(src.getDeliveryExpressNbr());
		tar.setOrderDeliveryMethodId(src.getOrderDeliveryMethodId());
		tar.setGoodReceiverName(src.getGoodReceiverName());
		tar.setGoodReceiverMobile(src.getGoodReceiverMobile());
		tar.setProCityArea(src.getProCityArea());
		tar.setDeliveryDate(src.getDeliveryDate());
		tar.setGoodReceiverAddress(src.getGoodReceiverAddress());
		tar.setDeliveryName(src.getDeliveryName());
		tar.setDeliveryNameMobile(src.getDeliveryNameMobile());
		tar.setSignName(src.getSignName());
		tar.setSignDate(src.getSignDate());
		tar.setSignRemark(src.getSignRemark());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}
	public static SoPackageDTO conditionToDTO(SoPackageCondition src) {
		if (src == null)
			return null;	
		SoPackageDTO tar = new SoPackageDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setSoId(src.getSoId());
		tar.setSoChildId(src.getSoChildId());
		tar.setOrderCode(src.getOrderCode());
		tar.setMerchantId(src.getMerchantId());
		tar.setSoChildDeliveryFee(src.getSoChildDeliveryFee());
		tar.setPackageType(src.getPackageType());
		tar.setDeliveryCode(src.getDeliveryCode());
		tar.setDeliveryStatus(src.getDeliveryStatus());
		tar.setDeliveryMode(src.getDeliveryMode());
		tar.setDeliveryCompanyId(src.getDeliveryCompanyId());
		tar.setDeliveryCompanyName(src.getDeliveryCompanyName());
		tar.setDeliveryMessage(src.getDeliveryMessage());
//		tar.setDeliveryExpressNbr(src.getDeliveryExpressNbr());
		tar.setOrderDeliveryMethodId(src.getOrderDeliveryMethodId());
		tar.setGoodReceiverName(src.getGoodReceiverName());
		tar.setGoodReceiverMobile(src.getGoodReceiverMobile());
		tar.setProCityArea(src.getProCityArea());
		tar.setDeliveryDate(src.getDeliveryDate());
		tar.setGoodReceiverAddress(src.getGoodReceiverAddress());
		tar.setDeliveryName(src.getDeliveryName());
		tar.setDeliveryNameMobile(src.getDeliveryNameMobile());
		tar.setSignName(src.getSignName());
		tar.setSignDate(src.getSignDate());
		tar.setSignRemark(src.getSignRemark());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSoBoxCode(src.getSoBoxCode());
		return tar;
	}

	public static SoPackagePO toPO(SoPackageDTO src) {
		if (src == null)
		return null;	
		SoPackagePO tar = new SoPackagePO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setSoId(src.getSoId());
		tar.setSoChildId(src.getSoChildId());
		tar.setOrderCode(src.getOrderCode());
		tar.setMerchantId(src.getMerchantId());
		tar.setSoChildDeliveryFee(src.getSoChildDeliveryFee());
		tar.setPackageType(src.getPackageType());
		tar.setDeliveryCode(src.getDeliveryCode());
		tar.setDeliveryStatus(src.getDeliveryStatus());
		tar.setDeliveryMode(src.getDeliveryMode());
		tar.setDeliveryCompanyId(src.getDeliveryCompanyId());
		tar.setDeliveryCompanyName(src.getDeliveryCompanyName());
		tar.setDeliveryMessage(src.getDeliveryMessage());
//		tar.setDeliveryExpressNbr(src.getDeliveryExpressNbr());
		tar.setOrderDeliveryMethodId(src.getOrderDeliveryMethodId());
		tar.setGoodReceiverName(src.getGoodReceiverName());
		tar.setGoodReceiverMobile(src.getGoodReceiverMobile());
		tar.setProCityArea(src.getProCityArea());
		tar.setDeliveryDate(src.getDeliveryDate());
		tar.setGoodReceiverAddress(src.getGoodReceiverAddress());
		tar.setDeliveryName(src.getDeliveryName());
		tar.setDeliveryNameMobile(src.getDeliveryNameMobile());
		tar.setSignName(src.getSignName());
		tar.setSignDate(src.getSignDate());
		tar.setSignRemark(src.getSignRemark());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<SoPackageDTO> toDTO(List<SoPackagePO> srcs) {
		if (srcs == null)
			return null;
		List<SoPackageDTO> list = new ArrayList<SoPackageDTO>();
		for (SoPackagePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoPackagePO> toPO(List<SoPackageDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoPackagePO> list = new ArrayList<SoPackagePO>();
		for (SoPackageDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}

	public static List<SoPackageDTO> conditionToDTO(List<SoPackageCondition> srcs) {
		if (srcs == null)
			return null;
		List<SoPackageDTO> list = new ArrayList<SoPackageDTO>();
		for (SoPackageCondition src : srcs) {
			list.add(conditionToDTO(src));
		}
		return list;
	}
}
	