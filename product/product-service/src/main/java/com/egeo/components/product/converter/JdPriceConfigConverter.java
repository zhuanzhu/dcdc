package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.JdPriceConfigDTO;
import com.egeo.components.product.po.JdPriceConfigPO;
import com.egeo.components.product.vo.JdPriceConfigVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author tan
 * @date 2019-03-29 10:17:51
 */
public class JdPriceConfigConverter {

	
	public static JdPriceConfigDTO toDTO(JdPriceConfigVO src) {
		if (src == null)
		return null;	
		JdPriceConfigDTO tar = new JdPriceConfigDTO();
		tar.setId(src.getId());
		tar.setStandardCompanyRate(src.getStandardCompanyRate());	
		tar.setDemocompanysCompanyRate(src.getDemocompanysCompanyRate());	
		tar.setCompetingCompanyRate(src.getCompetingCompanyRate());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static JdPriceConfigVO toVO(JdPriceConfigDTO src) {
		if (src == null)
		return null;	
		JdPriceConfigVO tar = new JdPriceConfigVO();
		tar.setId(src.getId());
		tar.setStandardCompanyRate(src.getStandardCompanyRate());	
		tar.setDemocompanysCompanyRate(src.getDemocompanysCompanyRate());	
		tar.setCompetingCompanyRate(src.getCompetingCompanyRate());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static List<JdPriceConfigDTO> toDTOs(List<JdPriceConfigVO> srcs) {
		if (srcs == null)
			return null;
		List<JdPriceConfigDTO> list = new ArrayList<JdPriceConfigDTO>();
		for (JdPriceConfigVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<JdPriceConfigVO> toVO(List<JdPriceConfigDTO> srcs) {
		if (srcs == null)
			return null;
		List<JdPriceConfigVO> list = new ArrayList<JdPriceConfigVO>();
		for (JdPriceConfigDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static JdPriceConfigDTO toDTO(JdPriceConfigPO src) {
		if (src == null)
		return null;	
		JdPriceConfigDTO tar = new JdPriceConfigDTO();
		tar.setId(src.getId());
		tar.setStandardCompanyRate(src.getStandardCompanyRate());
		tar.setDemocompanysCompanyRate(src.getDemocompanysCompanyRate());
		tar.setCompetingCompanyRate(src.getCompetingCompanyRate());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static JdPriceConfigPO toPO(JdPriceConfigDTO src) {
		if (src == null)
		return null;	
		JdPriceConfigPO tar = new JdPriceConfigPO();
		tar.setId(src.getId());
		tar.setStandardCompanyRate(src.getStandardCompanyRate());
		tar.setDemocompanysCompanyRate(src.getDemocompanysCompanyRate());
		tar.setCompetingCompanyRate(src.getCompetingCompanyRate());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<JdPriceConfigDTO> toDTO(List<JdPriceConfigPO> srcs) {
		if (srcs == null)
			return null;
		List<JdPriceConfigDTO> list = new ArrayList<JdPriceConfigDTO>();
		for (JdPriceConfigPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<JdPriceConfigPO> toPO(List<JdPriceConfigDTO> srcs) {
		if (srcs == null)
			return null;
		List<JdPriceConfigPO> list = new ArrayList<JdPriceConfigPO>();
		for (JdPriceConfigDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	