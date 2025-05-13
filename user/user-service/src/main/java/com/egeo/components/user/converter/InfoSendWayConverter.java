package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.InfoSendWayDTO;
import com.egeo.components.user.po.InfoSendWayPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-08-15 10:44:50
 */
public class InfoSendWayConverter {
	
	public static InfoSendWayDTO toDTO(InfoSendWayPO src) {
		if (src == null)
		return null;	
		InfoSendWayDTO tar = new InfoSendWayDTO();
		tar.setId(src.getId());
		tar.setInfoTemplateId(src.getInfoTemplateId());
		tar.setSendWayId(src.getSendWayId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static InfoSendWayPO toPO(InfoSendWayDTO src) {
		if (src == null)
		return null;	
		InfoSendWayPO tar = new InfoSendWayPO();
		tar.setId(src.getId());
		tar.setInfoTemplateId(src.getInfoTemplateId());
		tar.setSendWayId(src.getSendWayId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<InfoSendWayDTO> toDTO(List<InfoSendWayPO> srcs) {
		if (srcs == null)
			return null;
		List<InfoSendWayDTO> list = new ArrayList<InfoSendWayDTO>();
		for (InfoSendWayPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<InfoSendWayPO> toPO(List<InfoSendWayDTO> srcs) {
		if (srcs == null)
			return null;
		List<InfoSendWayPO> list = new ArrayList<InfoSendWayPO>();
		for (InfoSendWayDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	