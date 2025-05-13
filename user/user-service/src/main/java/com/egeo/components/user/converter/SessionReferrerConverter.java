package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.SessionReferrerDTO;
import com.egeo.components.user.po.SessionReferrerPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-02-04 16:22:23
 */
public class SessionReferrerConverter {
	
	public static SessionReferrerDTO toDTO(SessionReferrerPO src) {
		if (src == null)
		return null;	
		SessionReferrerDTO tar = new SessionReferrerDTO();
		tar.setId(src.getId());
		tar.setReferrerId(src.getReferrerId());
		tar.setChannelId(src.getChannelId());
		tar.setActivityId(src.getActivityId());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static SessionReferrerPO toPO(SessionReferrerDTO src) {
		if (src == null)
		return null;	
		SessionReferrerPO tar = new SessionReferrerPO();
		tar.setId(src.getId());
		tar.setReferrerId(src.getReferrerId());
		tar.setChannelId(src.getChannelId());
		tar.setActivityId(src.getActivityId());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<SessionReferrerDTO> toDTO(List<SessionReferrerPO> srcs) {
		if (srcs == null)
			return null;
		List<SessionReferrerDTO> list = new ArrayList<SessionReferrerDTO>();
		for (SessionReferrerPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SessionReferrerPO> toPO(List<SessionReferrerDTO> srcs) {
		if (srcs == null)
			return null;
		List<SessionReferrerPO> list = new ArrayList<SessionReferrerPO>();
		for (SessionReferrerDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	