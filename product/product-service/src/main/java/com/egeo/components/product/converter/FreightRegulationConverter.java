package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.FreightRegulationDTO;
import com.egeo.components.product.po.FreightRegulationPO;
import com.egeo.components.product.vo.FreightRegulationVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-05-11 10:35:35
 */
public class FreightRegulationConverter {

	public static FreightRegulationDTO toDTO(FreightRegulationVO src) {
		if (src == null)
		return null;	
		FreightRegulationDTO tar = new FreightRegulationDTO();
		tar.setId(src.getId());
		tar.setFreightTemplateId(src.getFreightTemplateId());	
		tar.setOrderMoney(src.getOrderMoney());	
		tar.setFreightMoney(src.getFreightMoney());	
		return tar;
	}

	public static FreightRegulationVO toVO(FreightRegulationDTO src) {
		if (src == null)
		return null;	
		FreightRegulationVO tar = new FreightRegulationVO();
		tar.setId(src.getId());
		tar.setFreightTemplateId(src.getFreightTemplateId());	
		tar.setOrderMoney(src.getOrderMoney());	
		tar.setFreightMoney(src.getFreightMoney());	
		return tar;
	}

	public static List<FreightRegulationDTO> toDTOs(List<FreightRegulationVO> srcs) {
		if (srcs == null)
			return null;
		List<FreightRegulationDTO> list = new ArrayList<FreightRegulationDTO>();
		for (FreightRegulationVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<FreightRegulationVO> toVO(List<FreightRegulationDTO> srcs) {
		if (srcs == null)
			return null;
		List<FreightRegulationVO> list = new ArrayList<FreightRegulationVO>();
		for (FreightRegulationDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static FreightRegulationDTO toDTO(FreightRegulationPO src) {
		if (src == null)
		return null;	
		FreightRegulationDTO tar = new FreightRegulationDTO();
		tar.setId(src.getId());
		tar.setFreightTemplateId(src.getFreightTemplateId());
		tar.setOrderMoney(src.getOrderMoney());
		tar.setFreightMoney(src.getFreightMoney());
		return tar;
	}

	public static FreightRegulationPO toPO(FreightRegulationDTO src) {
		if (src == null)
		return null;	
		FreightRegulationPO tar = new FreightRegulationPO();
		tar.setId(src.getId());
		tar.setFreightTemplateId(src.getFreightTemplateId());
		tar.setOrderMoney(src.getOrderMoney());
		tar.setFreightMoney(src.getFreightMoney());
		return tar;
	}

	public static List<FreightRegulationDTO> toDTO(List<FreightRegulationPO> srcs) {
		if (srcs == null)
			return null;
		List<FreightRegulationDTO> list = new ArrayList<FreightRegulationDTO>();
		for (FreightRegulationPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<FreightRegulationPO> toPO(List<FreightRegulationDTO> srcs) {
		if (srcs == null)
			return null;
		List<FreightRegulationPO> list = new ArrayList<FreightRegulationPO>();
		for (FreightRegulationDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	