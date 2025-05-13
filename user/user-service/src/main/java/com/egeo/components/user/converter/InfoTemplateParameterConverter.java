package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.InfoTemplateParameterDTO;
import com.egeo.components.user.po.InfoTemplateParameterPO;
import com.egeo.components.user.vo.InfoTemplateParameterVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-08-14 10:05:59
 */
public class InfoTemplateParameterConverter {
	
	public static InfoTemplateParameterDTO toDTO(InfoTemplateParameterPO src) {
		if (src == null)
		return null;	
		InfoTemplateParameterDTO tar = new InfoTemplateParameterDTO();
		tar.setId(src.getId());
		tar.setInfoTemplateId(src.getInfoTemplateId());
		tar.setName(src.getName());
		return tar;
	}

	public static InfoTemplateParameterPO toPO(InfoTemplateParameterDTO src) {
		if (src == null)
		return null;	
		InfoTemplateParameterPO tar = new InfoTemplateParameterPO();
		tar.setId(src.getId());
		tar.setInfoTemplateId(src.getInfoTemplateId());
		tar.setName(src.getName());
		return tar;
	}

	public static List<InfoTemplateParameterDTO> toDTO(List<InfoTemplateParameterPO> srcs) {
		if (srcs == null)
			return null;
		List<InfoTemplateParameterDTO> list = new ArrayList<InfoTemplateParameterDTO>();
		for (InfoTemplateParameterPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<InfoTemplateParameterPO> toPO(List<InfoTemplateParameterDTO> srcs) {
		if (srcs == null)
			return null;
		List<InfoTemplateParameterPO> list = new ArrayList<InfoTemplateParameterPO>();
		for (InfoTemplateParameterDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}

	
	public static InfoTemplateParameterDTO toDTO(InfoTemplateParameterVO src) {
		if (src == null)
		return null;	
		InfoTemplateParameterDTO tar = new InfoTemplateParameterDTO();
		tar.setId(src.getId());
		tar.setInfoTemplateId(src.getInfoTemplateId());	
		tar.setName(src.getName());	
		return tar;
	}

	public static InfoTemplateParameterVO toVO(InfoTemplateParameterDTO src) {
		if (src == null)
		return null;	
		InfoTemplateParameterVO tar = new InfoTemplateParameterVO();
		tar.setId(src.getId());
		tar.setInfoTemplateId(src.getInfoTemplateId());	
		tar.setName(src.getName());	
		return tar;
	}

	public static List<InfoTemplateParameterVO> toVO(List<InfoTemplateParameterDTO> srcs) {
		if (srcs == null)
			return null;
		List<InfoTemplateParameterVO> list = new ArrayList<InfoTemplateParameterVO>();
		for (InfoTemplateParameterDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	