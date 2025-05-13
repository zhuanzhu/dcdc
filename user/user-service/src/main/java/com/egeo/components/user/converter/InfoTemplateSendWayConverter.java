package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.InfoTemplateSendWayDTO;
import com.egeo.components.user.po.InfoTemplateSendWayPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-08-14 09:46:38
 */
public class InfoTemplateSendWayConverter {
	
	public static InfoTemplateSendWayDTO toDTO(InfoTemplateSendWayPO src) {
		if (src == null)
		return null;	
		InfoTemplateSendWayDTO tar = new InfoTemplateSendWayDTO();
		tar.setId(src.getId());
		tar.setInfoTemplateId(src.getInfoTemplateId());
		tar.setSendWayId(src.getSendWayId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static InfoTemplateSendWayPO toPO(InfoTemplateSendWayDTO src) {
		if (src == null)
		return null;	
		InfoTemplateSendWayPO tar = new InfoTemplateSendWayPO();
		tar.setId(src.getId());
		tar.setInfoTemplateId(src.getInfoTemplateId());
		tar.setSendWayId(src.getSendWayId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<InfoTemplateSendWayDTO> toDTO(List<InfoTemplateSendWayPO> srcs) {
		if (srcs == null)
			return null;
		List<InfoTemplateSendWayDTO> list = new ArrayList<InfoTemplateSendWayDTO>();
		for (InfoTemplateSendWayPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<InfoTemplateSendWayPO> toPO(List<InfoTemplateSendWayDTO> srcs) {
		if (srcs == null)
			return null;
		List<InfoTemplateSendWayPO> list = new ArrayList<InfoTemplateSendWayPO>();
		for (InfoTemplateSendWayDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	