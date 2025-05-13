package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.condition.LinkableButtonPageCondition;
import com.egeo.components.cms.dto.LinkableButtonPageDTO;
import com.egeo.components.cms.po.LinkableButtonPagePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author mingqiang.luo
 * @date 2018-12-14 14:35:33
 */
public class LinkableButtonPageConverter {
	
	public static LinkableButtonPageDTO toDTO(LinkableButtonPagePO src) {
		if (src == null)
		return null;	
		LinkableButtonPageDTO tar = new LinkableButtonPageDTO();
		tar.setId(src.getId());
		tar.setCmsPageId(src.getCmsPageId());
		tar.setClientType(src.getClientType());
		tar.setLinkableId(src.getLinkableId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static LinkableButtonPagePO toPO(LinkableButtonPageDTO src) {
		if (src == null)
		return null;	
		LinkableButtonPagePO tar = new LinkableButtonPagePO();
		tar.setId(src.getId());
		tar.setCmsPageId(src.getCmsPageId());
		tar.setClientType(src.getClientType());
		tar.setLinkableId(src.getLinkableId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<LinkableButtonPageDTO> toDTO(List<LinkableButtonPagePO> srcs) {
		if (srcs == null)
			return null;
		List<LinkableButtonPageDTO> list = new ArrayList<LinkableButtonPageDTO>();
		for (LinkableButtonPagePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<LinkableButtonPagePO> toPO(List<LinkableButtonPageDTO> srcs) {
		if (srcs == null)
			return null;
		List<LinkableButtonPagePO> list = new ArrayList<LinkableButtonPagePO>();
		for (LinkableButtonPageDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	
	public static LinkableButtonPageDTO conditionToDTO(LinkableButtonPageCondition src) {
		if (src == null)
		return null;	
		LinkableButtonPageDTO tar = new LinkableButtonPageDTO();
		tar.setId(src.getId());
		tar.setCmsPageId(src.getCmsPageId());
		tar.setClientType(src.getClientType());
		tar.setLinkableId(src.getLinkableId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPageName(src.getPageName());
		return tar;
	}

	public static LinkableButtonPageCondition dtoToCondition(LinkableButtonPageDTO src) {
		if (src == null)
		return null;	
		LinkableButtonPageCondition tar = new LinkableButtonPageCondition();
		tar.setId(src.getId());
		tar.setCmsPageId(src.getCmsPageId());
		tar.setClientType(src.getClientType());
		tar.setLinkableId(src.getLinkableId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPageName(src.getPageName());
		return tar;
	}

	public static List<LinkableButtonPageDTO> conditionToDTO(List<LinkableButtonPageCondition> srcs) {
		if (srcs == null)
			return null;
		List<LinkableButtonPageDTO> list = new ArrayList<LinkableButtonPageDTO>();
		for (LinkableButtonPageCondition src : srcs) {
			list.add(conditionToDTO(src));
		}
		return list;
	}

	public static List<LinkableButtonPageCondition> dtoToCondition(List<LinkableButtonPageDTO> srcs) {
		if (srcs == null)
			return null;
		List<LinkableButtonPageCondition> list = new ArrayList<LinkableButtonPageCondition>();
		for (LinkableButtonPageDTO src : srcs) {
			list.add(dtoToCondition(src));
		}
		return list;
	}
	
	
}
	