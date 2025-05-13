package com.egeo.components.finance.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.finance.dto.ReasonCompanyDTO;
import com.egeo.components.finance.po.ReasonCompanyPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jzh
 * @date 2018-05-10 13:49:03
 */
public class ReasonCompanyConverter {
	
	public static ReasonCompanyDTO toDTO(ReasonCompanyPO src) {
		if (src == null)
		return null;	
		ReasonCompanyDTO tar = new ReasonCompanyDTO();
		tar.setId(src.getId());
		tar.setReasonId(src.getReasonId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static ReasonCompanyPO toPO(ReasonCompanyDTO src) {
		if (src == null)
		return null;	
		ReasonCompanyPO tar = new ReasonCompanyPO();
		tar.setId(src.getId());
		tar.setReasonId(src.getReasonId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<ReasonCompanyDTO> toDTO(List<ReasonCompanyPO> srcs) {
		if (srcs == null)
			return null;
		List<ReasonCompanyDTO> list = new ArrayList<ReasonCompanyDTO>();
		for (ReasonCompanyPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ReasonCompanyPO> toPO(List<ReasonCompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<ReasonCompanyPO> list = new ArrayList<ReasonCompanyPO>();
		for (ReasonCompanyDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	