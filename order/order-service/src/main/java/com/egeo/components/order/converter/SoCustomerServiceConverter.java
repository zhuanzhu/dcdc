package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoCustomerServiceDTO;
import com.egeo.components.order.po.SoCustomerServicePO;
import com.egeo.components.order.vo.SoCustomerServiceVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author ghw
 * @date 2018-02-07 09:26:26
 */
public class SoCustomerServiceConverter {

	
	public static SoCustomerServiceDTO toDTO(SoCustomerServiceVO src) {
		if (src == null)
		return null;	
		SoCustomerServiceDTO tar = new SoCustomerServiceDTO();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());	
		tar.setUserName(src.getUserName());	
		tar.setOperateRemark(src.getOperateRemark());	
		tar.setSoId(src.getSoId());	
		tar.setUserRemark(src.getUserRemark());	
		return tar;
	}

	public static SoCustomerServiceVO toVO(SoCustomerServiceDTO src) {
		if (src == null)
		return null;	
		SoCustomerServiceVO tar = new SoCustomerServiceVO();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());	
		tar.setUserName(src.getUserName());	
		tar.setOperateRemark(src.getOperateRemark());	
		tar.setSoId(src.getSoId());	
		tar.setUserRemark(src.getUserRemark());	
		return tar;
	}

	public static List<SoCustomerServiceDTO> toDTOs(List<SoCustomerServiceVO> srcs) {
		if (srcs == null)
			return null;
		List<SoCustomerServiceDTO> list = new ArrayList<SoCustomerServiceDTO>();
		for (SoCustomerServiceVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoCustomerServiceVO> toVO(List<SoCustomerServiceDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoCustomerServiceVO> list = new ArrayList<SoCustomerServiceVO>();
		for (SoCustomerServiceDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static SoCustomerServiceDTO toDTO(SoCustomerServicePO src) {
		if (src == null)
		return null;	
		SoCustomerServiceDTO tar = new SoCustomerServiceDTO();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());
		tar.setUserName(src.getUserName());
		tar.setOperateRemark(src.getOperateRemark());
		tar.setCreatTime(src.getCreatTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSoId(src.getSoId());
		tar.setUserRemark(src.getUserRemark());
		return tar;
	}

	public static SoCustomerServicePO toPO(SoCustomerServiceDTO src) {
		if (src == null)
		return null;	
		SoCustomerServicePO tar = new SoCustomerServicePO();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());
		tar.setUserName(src.getUserName());
		tar.setOperateRemark(src.getOperateRemark());
		tar.setCreatTime(src.getCreatTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSoId(src.getSoId());
		tar.setUserRemark(src.getUserRemark());
		return tar;
	}

	public static List<SoCustomerServiceDTO> toDTO(List<SoCustomerServicePO> srcs) {
		if (srcs == null)
			return null;
		List<SoCustomerServiceDTO> list = new ArrayList<SoCustomerServiceDTO>();
		for (SoCustomerServicePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoCustomerServicePO> toPO(List<SoCustomerServiceDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoCustomerServicePO> list = new ArrayList<SoCustomerServicePO>();
		for (SoCustomerServiceDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	