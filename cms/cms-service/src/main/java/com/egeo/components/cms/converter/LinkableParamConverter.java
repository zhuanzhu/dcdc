package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.LinkableParamDTO;
import com.egeo.components.cms.po.LinkableParamPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author tan
 * @date 2019-01-04 17:04:48
 */
public class LinkableParamConverter {
	
	public static LinkableParamDTO toDTO(LinkableParamPO src) {
		if (src == null)
		return null;	
		LinkableParamDTO tar = new LinkableParamDTO();
		tar.setId(src.getId());
		tar.setLinkButtonId(src.getLinkButtonId());
		tar.setName(src.getName());
		tar.setValue(src.getValue());
		tar.setRemark(src.getRemark());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static LinkableParamPO toPO(LinkableParamDTO src) {
		if (src == null)
		return null;	
		LinkableParamPO tar = new LinkableParamPO();
		tar.setId(src.getId());
		tar.setLinkButtonId(src.getLinkButtonId());
		tar.setName(src.getName());
		tar.setValue(src.getValue());
		tar.setRemark(src.getRemark());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<LinkableParamDTO> toDTO(List<LinkableParamPO> srcs) {
		if (srcs == null)
			return null;
		List<LinkableParamDTO> list = new ArrayList<LinkableParamDTO>();
		for (LinkableParamPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<LinkableParamPO> toPO(List<LinkableParamDTO> srcs) {
		if (srcs == null)
			return null;
		List<LinkableParamPO> list = new ArrayList<LinkableParamPO>();
		for (LinkableParamDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	