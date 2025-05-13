package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.components.cms.po.LinkableButtonPO;
import com.egeo.components.cms.vo.LinkableButtonVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jzh
 * @date 2018-04-04 13:24:49
 */
public class LinkableButtonConverter {
	

	
	public static LinkableButtonDTO toDTO(LinkableButtonVO src) {
		if (src == null)
		return null;	
		LinkableButtonDTO tar = new LinkableButtonDTO();
		tar.setId(src.getId());
		tar.setLinkType(src.getLinkType());	
		tar.setLinkId(src.getLinkId());	
		tar.setLinkParam(src.getLinkParam());	
		tar.setLinkUrl(src.getLinkUrl());	
		return tar;
	}

	public static LinkableButtonVO toVO(LinkableButtonDTO src) {
		if (src == null)
		return null;	
		LinkableButtonVO tar = new LinkableButtonVO();
		tar.setId(src.getId());
		tar.setLinkType(src.getLinkType());	
		tar.setLinkId(src.getLinkId());	
		tar.setLinkParam(src.getLinkParam());	
		tar.setLinkUrl(src.getLinkUrl());	
		return tar;
	}

	

	public static List<LinkableButtonVO> toVO(List<LinkableButtonDTO> srcs) {
		if (srcs == null)
			return null;
		List<LinkableButtonVO> list = new ArrayList<LinkableButtonVO>();
		for (LinkableButtonDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	/**
	 * po转DTO时做一个特殊操作,控制不同类型下的无关参数值为空
	 * @param src
	 * @return
	 */
	public static LinkableButtonDTO toDTO(LinkableButtonPO src) {
		if (src == null)
		return null;	
		LinkableButtonDTO tar = new LinkableButtonDTO();
		tar.setId(src.getId());
		tar.setLinkType(src.getLinkType());
		Integer linkType=src.getLinkType();
		if(linkType==null) {
			tar.setLinkId(null);
			tar.setLinkParam(null);
			tar.setLinkUrl(null);
		}else {
			switch (linkType) {
			case 1:
			case 3:
			case 4:
			case 5:
				tar.setLinkId(src.getLinkId());
				tar.setLinkParam(null);
				tar.setLinkUrl(null);
				break;
			case 2:
				tar.setLinkId(null);
				tar.setLinkParam(null);
				tar.setLinkUrl(src.getLinkUrl());
				break;
			case 6:
				tar.setLinkId(null);
				tar.setLinkParam(null);
				tar.setLinkUrl(null);
				break;
			case 7:
				tar.setLinkId(src.getLinkId());
				tar.setLinkParam(src.getLinkParam());
				tar.setLinkUrl(src.getLinkUrl());
				break;
			default:
				tar.setLinkId(src.getLinkId());
				tar.setLinkParam(src.getLinkParam());
				tar.setLinkUrl(src.getLinkUrl());
				break;
			}
		}
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static LinkableButtonPO toPO(LinkableButtonDTO src) {
		if (src == null)
		return null;	
		LinkableButtonPO tar = new LinkableButtonPO();
		tar.setId(src.getId());
		tar.setLinkType(src.getLinkType());
		tar.setLinkId(src.getLinkId());
		tar.setLinkParam(src.getLinkParam());
		tar.setLinkUrl(src.getLinkUrl());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<LinkableButtonDTO> toDTO(List<LinkableButtonPO> srcs) {
		if (srcs == null)
			return null;
		List<LinkableButtonDTO> list = new ArrayList<LinkableButtonDTO>();
		for (LinkableButtonPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<LinkableButtonPO> toPO(List<LinkableButtonDTO> srcs) {
		if (srcs == null)
			return null;
		List<LinkableButtonPO> list = new ArrayList<LinkableButtonPO>();
		for (LinkableButtonDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	