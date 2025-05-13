package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.InfoTemplateDTO;
import com.egeo.components.user.po.InfoTemplatePO;
import com.egeo.components.user.vo.InfoTemplateVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-08-14 09:46:37
 */
public class InfoTemplateConverter {
	
	public static InfoTemplateDTO toDTO(InfoTemplatePO src) {
		if (src == null)
		return null;	
		InfoTemplateDTO tar = new InfoTemplateDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setTriggers(src.getTriggers());
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
		tar.setSendWayId(src.getSendWayId());
		tar.setInfoInform(src.getInfoInform());
		return tar;
	}

	public static InfoTemplatePO toPO(InfoTemplateDTO src) {
		if (src == null)
		return null;	
		InfoTemplatePO tar = new InfoTemplatePO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setTriggers(src.getTriggers());
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
		tar.setSendWayId(src.getSendWayId());
		tar.setInfoInform(src.getInfoInform());
		return tar;
	}

	public static List<InfoTemplateDTO> toDTO(List<InfoTemplatePO> srcs) {
		if (srcs == null)
			return null;
		List<InfoTemplateDTO> list = new ArrayList<InfoTemplateDTO>();
		for (InfoTemplatePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<InfoTemplatePO> toPO(List<InfoTemplateDTO> srcs) {
		if (srcs == null)
			return null;
		List<InfoTemplatePO> list = new ArrayList<InfoTemplatePO>();
		for (InfoTemplateDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	
	

	public static InfoTemplateDTO toDTO(InfoTemplateVO src) {
		if (src == null)
		return null;	
		InfoTemplateDTO tar = new InfoTemplateDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setTriggers(src.getTriggers());	
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
		tar.setSendWayId(src.getSendWayId());
		tar.setInfoInform(src.getInfoInform());
		return tar;
	}

	public static InfoTemplateVO toVO(InfoTemplateDTO src) {
		if (src == null)
		return null;	
		InfoTemplateVO tar = new InfoTemplateVO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setTriggers(src.getTriggers());	
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
		tar.setSendWayId(src.getSendWayId());
		tar.setInfoInform(src.getInfoInform());
		return tar;
	}


	public static List<InfoTemplateVO> toVO(List<InfoTemplateDTO> srcs) {
		if (srcs == null)
			return null;
		List<InfoTemplateVO> list = new ArrayList<InfoTemplateVO>();
		for (InfoTemplateDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	