package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.DeliveryCompanyDTO;
import com.egeo.components.order.po.DeliveryCompanyPO;
import com.egeo.components.order.vo.DeliveryCompanyVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2017-08-18 15:45:02
 */
public class DeliveryCompanyConverter {

	public static DeliveryCompanyDTO toDTO(DeliveryCompanyVO src) {
		DeliveryCompanyDTO tar = new DeliveryCompanyDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setCoding(src.getCoding());	
		tar.setTrajectoryQuerying(src.getTrajectoryQuerying());	
		tar.setElectronicSurface(src.getElectronicSurface());	
		tar.setPickUp(src.getPickUp());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static DeliveryCompanyVO toVO(DeliveryCompanyDTO src) {
		DeliveryCompanyVO tar = new DeliveryCompanyVO();
		tar.setId(src.getId());
		tar.setName(src.getName());		
		tar.setCoding(src.getCoding());		
		tar.setTrajectoryQuerying(src.getTrajectoryQuerying());		
		tar.setElectronicSurface(src.getElectronicSurface());		
		tar.setPickUp(src.getPickUp());		
		tar.setUpdateTime(src.getUpdateTime());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setPlatformId(src.getPlatformId());		
		return tar;
	}

	public static List<DeliveryCompanyDTO> toDTOs(List<DeliveryCompanyVO> srcs) {
		if (srcs == null)
			return null;
		List<DeliveryCompanyDTO> list = new ArrayList<DeliveryCompanyDTO>();
		for (DeliveryCompanyVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<DeliveryCompanyVO> toVO(List<DeliveryCompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<DeliveryCompanyVO> list = new ArrayList<DeliveryCompanyVO>();
		for (DeliveryCompanyDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static DeliveryCompanyDTO toDTO(DeliveryCompanyPO src) {
		if(src==null) {
			return null;
		}
		DeliveryCompanyDTO tar = new DeliveryCompanyDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setCoding(src.getCoding());
		tar.setTrajectoryQuerying(src.getTrajectoryQuerying());
		tar.setElectronicSurface(src.getElectronicSurface());
		tar.setPickUp(src.getPickUp());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static DeliveryCompanyPO toPO(DeliveryCompanyDTO src) {
		if(src==null) {
			return null;
		}
		DeliveryCompanyPO tar = new DeliveryCompanyPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setCoding(src.getCoding());
		tar.setTrajectoryQuerying(src.getTrajectoryQuerying());
		tar.setElectronicSurface(src.getElectronicSurface());
		tar.setPickUp(src.getPickUp());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<DeliveryCompanyDTO> toDTO(List<DeliveryCompanyPO> srcs) {
		if (srcs == null)
			return null;
		List<DeliveryCompanyDTO> list = new ArrayList<DeliveryCompanyDTO>();
		for (DeliveryCompanyPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<DeliveryCompanyPO> toPO(List<DeliveryCompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<DeliveryCompanyPO> list = new ArrayList<DeliveryCompanyPO>();
		for (DeliveryCompanyDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	