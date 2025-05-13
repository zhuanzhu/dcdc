package com.egeo.components.config.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.config.dto.ProvCityAreaDTO;
import com.egeo.components.config.po.ProvCityAreaPO;
import com.egeo.components.config.vo.ProvCityAreaVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2017-11-29 21:58:45
 */
public class ProvCityAreaConverter {

	public static ProvCityAreaDTO toDTO(ProvCityAreaVO src) {
		if (src == null)
		return null;	
		ProvCityAreaDTO tar = new ProvCityAreaDTO();
		tar.setId(src.getId());
		tar.setAreaname(src.getAreaname());	
		tar.setParentno(src.getParentno());	
		tar.setAreacode(src.getAreacode());	
		tar.setArealevel(src.getArealevel());	
		tar.setTypename(src.getTypename());	
		return tar;
	}

	public static ProvCityAreaVO toVO(ProvCityAreaDTO src) {
		if (src == null)
		return null;	
		ProvCityAreaVO tar = new ProvCityAreaVO();
		tar.setId(src.getId());
		tar.setAreaname(src.getAreaname());	
		tar.setParentno(src.getParentno());	
		tar.setAreacode(src.getAreacode());	
		tar.setArealevel(src.getArealevel());	
		tar.setTypename(src.getTypename());	
		return tar;
	}

	

	public static List<ProvCityAreaVO> toVO(List<ProvCityAreaDTO> srcs) {
		if (srcs == null)
			return null;
		List<ProvCityAreaVO> list = new ArrayList<ProvCityAreaVO>();
		for (ProvCityAreaDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static ProvCityAreaDTO toDTO(ProvCityAreaPO src) {
		if (src == null)
		return null;	
		ProvCityAreaDTO tar = new ProvCityAreaDTO();
		tar.setId(src.getId());
		tar.setAreaname(src.getAreaname());
		tar.setParentno(src.getParentno());
		tar.setAreacode(src.getAreacode());
		tar.setArealevel(src.getArealevel());
		tar.setTypename(src.getTypename());
		return tar;
	}

	public static ProvCityAreaPO toPO(ProvCityAreaDTO src) {
		if (src == null)
		return null;	
		ProvCityAreaPO tar = new ProvCityAreaPO();
		tar.setId(src.getId());
		tar.setAreaname(src.getAreaname());
		tar.setParentno(src.getParentno());
		tar.setAreacode(src.getAreacode());
		tar.setArealevel(src.getArealevel());
		tar.setTypename(src.getTypename());
		return tar;
	}

	public static List<ProvCityAreaDTO> toDTO(List<ProvCityAreaPO> srcs) {
		if (srcs == null)
			return null;
		List<ProvCityAreaDTO> list = new ArrayList<ProvCityAreaDTO>();
		for (ProvCityAreaPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ProvCityAreaPO> toPO(List<ProvCityAreaDTO> srcs) {
		if (srcs == null)
			return null;
		List<ProvCityAreaPO> list = new ArrayList<ProvCityAreaPO>();
		for (ProvCityAreaDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	