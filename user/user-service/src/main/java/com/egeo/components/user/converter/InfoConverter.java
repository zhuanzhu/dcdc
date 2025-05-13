package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.InfoDTO;
import com.egeo.components.user.po.InfoPO;
import com.egeo.components.user.vo.InfoVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-08-15 10:44:50
 */
public class InfoConverter {
	
	public static InfoDTO toDTO(InfoPO src) {
		if (src == null)
		return null;	
		InfoDTO tar = new InfoDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setTriggers(src.getTriggers());
		tar.setType(src.getType());
		tar.setIsStart(src.getIsStart());
		tar.setSystemInfo(src.getSystemInfo());
		tar.setMoblieInfo(src.getMoblieInfo());
		tar.setWeChatOfficialInfo(src.getWeChatOfficialInfo());
		tar.setMailInfoTitle(src.getMailInfoTitle());
		tar.setMailInfo(src.getMailInfo());
		tar.setMailRemark(src.getMailRemark());
		tar.setInfoTemplateRemark(src.getInfoTemplateRemark());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setInfoInform(src.getInfoInform());
		tar.setIsAdmin(src.getIsAdmin());
		tar.setUserId(src.getUserId());
		tar.setTitle(src.getTitle());
		return tar;
	}

	public static InfoPO toPO(InfoDTO src) {
		if (src == null)
		return null;	
		InfoPO tar = new InfoPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setTriggers(src.getTriggers());
		tar.setType(src.getType());
		tar.setIsStart(src.getIsStart());
		tar.setSystemInfo(src.getSystemInfo());
		tar.setMoblieInfo(src.getMoblieInfo());
		tar.setWeChatOfficialInfo(src.getWeChatOfficialInfo());
		tar.setMailInfoTitle(src.getMailInfoTitle());
		tar.setMailInfo(src.getMailInfo());
		tar.setMailRemark(src.getMailRemark());
		tar.setInfoTemplateRemark(src.getInfoTemplateRemark());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setInfoInform(src.getInfoInform());
		tar.setIsAdmin(src.getIsAdmin());
		tar.setUserId(src.getUserId());
		tar.setTitle(src.getTitle());
		return tar;
	}

	public static List<InfoDTO> toDTO(List<InfoPO> srcs) {
		if (srcs == null)
			return null;
		List<InfoDTO> list = new ArrayList<InfoDTO>();
		for (InfoPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<InfoPO> toPO(List<InfoDTO> srcs) {
		if (srcs == null)
			return null;
		List<InfoPO> list = new ArrayList<InfoPO>();
		for (InfoDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}

	
	public static InfoDTO toDTO(InfoVO src) {
		if (src == null)
		return null;	
		InfoDTO tar = new InfoDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setTriggers(src.getTriggers());	
		tar.setType(src.getType());	
		tar.setIsStart(src.getIsStart());	
		tar.setSystemInfo(src.getSystemInfo());	
		tar.setMoblieInfo(src.getMoblieInfo());	
		tar.setWeChatOfficialInfo(src.getWeChatOfficialInfo());	
		tar.setMailInfoTitle(src.getMailInfoTitle());	
		tar.setMailInfo(src.getMailInfo());	
		tar.setMailRemark(src.getMailRemark());	
		tar.setInfoTemplateRemark(src.getInfoTemplateRemark());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setUserSum(src.getUserSum());
		tar.setInfoInform(src.getInfoInform());
		tar.setIsAdmin(src.getIsAdmin());
		tar.setTitle(src.getTitle());
		return tar;
	}

	public static InfoVO toVO(InfoDTO src) {
		if (src == null)
		return null;	
		InfoVO tar = new InfoVO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setTriggers(src.getTriggers());	
		tar.setType(src.getType());	
		tar.setIsStart(src.getIsStart());	
		tar.setSystemInfo(src.getSystemInfo());	
		tar.setMoblieInfo(src.getMoblieInfo());	
		tar.setWeChatOfficialInfo(src.getWeChatOfficialInfo());	
		tar.setMailInfoTitle(src.getMailInfoTitle());	
		tar.setMailInfo(src.getMailInfo());	
		tar.setMailRemark(src.getMailRemark());	
		tar.setInfoTemplateRemark(src.getInfoTemplateRemark());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setUserSum(src.getUserSum());
		tar.setInfoInform(src.getInfoInform());
		tar.setIsAdmin(src.getIsAdmin());
		tar.setTitle(src.getTitle());
		return tar;
	}

	public static List<InfoVO> toVO(List<InfoDTO> srcs) {
		if (srcs == null)
			return null;
		List<InfoVO> list = new ArrayList<InfoVO>();
		for (InfoDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	