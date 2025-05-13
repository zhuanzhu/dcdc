package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoDeliveryItemDTO;
import com.egeo.components.order.po.SoDeliveryItemPO;
import com.egeo.components.order.vo.SoDeliveryItemVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2017-08-18 15:45:01
 */
public class SoDeliveryItemConverter {

	public static SoDeliveryItemDTO toDTO(SoDeliveryItemVO src) {
		SoDeliveryItemDTO tar = new SoDeliveryItemDTO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());	
		tar.setPackageNo(src.getPackageNo());	
		tar.setDeliveryExpressNbr(src.getDeliveryExpressNbr());	
		tar.setRemark(src.getRemark());	
		tar.setDeliveryDate(src.getDeliveryDate());	
		tar.setEstimateReceiveDate(src.getEstimateReceiveDate());	
		tar.setDeliveryRemark(src.getDeliveryRemark());	
		tar.setDeliverierName(src.getDeliverierName());	
		tar.setDeliverierMobile(src.getDeliverierMobile());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setCreateTime(src.getCreateTime());	
		return tar;
	}

	public static SoDeliveryItemVO toVO(SoDeliveryItemDTO src) {
		SoDeliveryItemVO tar = new SoDeliveryItemVO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());		
		tar.setPackageNo(src.getPackageNo());		
		tar.setDeliveryExpressNbr(src.getDeliveryExpressNbr());		
		tar.setRemark(src.getRemark());		
		tar.setDeliveryDate(src.getDeliveryDate());		
		tar.setEstimateReceiveDate(src.getEstimateReceiveDate());		
		tar.setDeliveryRemark(src.getDeliveryRemark());		
		tar.setDeliverierName(src.getDeliverierName());		
		tar.setDeliverierMobile(src.getDeliverierMobile());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setUpdateTime(src.getUpdateTime());		
		tar.setCreateTime(src.getCreateTime());		
		return tar;
	}

	public static List<SoDeliveryItemDTO> toDTOs(List<SoDeliveryItemVO> srcs) {
		if (srcs == null)
			return null;
		List<SoDeliveryItemDTO> list = new ArrayList<SoDeliveryItemDTO>();
		for (SoDeliveryItemVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoDeliveryItemVO> toVO(List<SoDeliveryItemDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoDeliveryItemVO> list = new ArrayList<SoDeliveryItemVO>();
		for (SoDeliveryItemDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static SoDeliveryItemDTO toDTO(SoDeliveryItemPO src) {
		SoDeliveryItemDTO tar = new SoDeliveryItemDTO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setPackageNo(src.getPackageNo());
		tar.setDeliveryExpressNbr(src.getDeliveryExpressNbr());
		tar.setRemark(src.getRemark());
		tar.setDeliveryDate(src.getDeliveryDate());
		tar.setEstimateReceiveDate(src.getEstimateReceiveDate());
		tar.setDeliveryRemark(src.getDeliveryRemark());
		tar.setDeliverierName(src.getDeliverierName());
		tar.setDeliverierMobile(src.getDeliverierMobile());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static SoDeliveryItemPO toPO(SoDeliveryItemDTO src) {
		SoDeliveryItemPO tar = new SoDeliveryItemPO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setPackageNo(src.getPackageNo());
		tar.setDeliveryExpressNbr(src.getDeliveryExpressNbr());
		tar.setRemark(src.getRemark());
		tar.setDeliveryDate(src.getDeliveryDate());
		tar.setEstimateReceiveDate(src.getEstimateReceiveDate());
		tar.setDeliveryRemark(src.getDeliveryRemark());
		tar.setDeliverierName(src.getDeliverierName());
		tar.setDeliverierMobile(src.getDeliverierMobile());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<SoDeliveryItemDTO> toDTO(List<SoDeliveryItemPO> srcs) {
		if (srcs == null)
			return null;
		List<SoDeliveryItemDTO> list = new ArrayList<SoDeliveryItemDTO>();
		for (SoDeliveryItemPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoDeliveryItemPO> toPO(List<SoDeliveryItemDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoDeliveryItemPO> list = new ArrayList<SoDeliveryItemPO>();
		for (SoDeliveryItemDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	