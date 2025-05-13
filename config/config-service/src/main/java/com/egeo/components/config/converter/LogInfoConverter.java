package com.egeo.components.config.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.egeo.components.config.condition.LogCondition;
import com.egeo.components.config.dto.LogInfoDTO;
import com.egeo.components.config.vo.LogInfoVO;
import com.egeo.utils.EmptyUtil;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xia
 * @date 2018-05-25 10:21:14
 */
public class LogInfoConverter {

	
	public static LogInfoDTO toDTO(LogInfoVO src) {
		if (src == null)
		return null;	
		LogInfoDTO tar = new LogInfoDTO();
		tar.setId(src.getId());
		tar.setMsgId(src.getMsgId());	
		tar.setMsgContent(src.getMsgContent());	
		tar.setOperatorName(src.getOperatorName());	
		tar.setOperatorId(src.getOperatorId());	
		tar.setOperObject(src.getOperObject());	
		tar.setModuleName(src.getModuleName());	
		tar.setTime(src.getTime());	
		tar.setIp(src.getIp());	
		tar.setClientType(src.getClientType());	
		tar.setMobileVersion(src.getMobileVersion());	
		tar.setOperatorObjId(src.getOperatorObjId());	
		tar.setOperatorObjName(src.getOperatorObjName());	
		tar.setOperatorObjCode(src.getOperatorObjCode());
		tar.setOper(src.getOper());
		tar.setOperType(src.getOperType());
		tar.setPageName(src.getPageName());
		tar.setModule(src.getModule());
		tar.setPageNameId(src.getPageNameId());
		tar.setModuleId(src.getModuleId());
		tar.setStartTime(EmptyUtil.isNotEmpty(src.getStartTime()) ? new Date(src.getStartTime()) : null);
		tar.setEndTime(EmptyUtil.isNotEmpty(src.getEndTime()) ? new Date(src.getEndTime()) : null);
		tar.setType(src.getType());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}
	
	public static LogInfoVO toVO(LogInfoDTO src) {
		if (src == null)
			return null;	
		LogInfoVO tar = new LogInfoVO();
		tar.setId(src.getId());
		tar.setMsgId(src.getMsgId());	
//		tar.setMsgContent(src.getMsgContent());	
		tar.setOperatorName(src.getOperatorName());	
		tar.setOperatorId(src.getOperatorId());	
		tar.setOperObject(src.getOperObject());	
		tar.setModuleName(src.getModuleName());	
		tar.setTime(src.getTime());	
		tar.setIp(src.getIp());	
		tar.setClientType(src.getClientType());	
		tar.setMobileVersion(src.getMobileVersion());	
		tar.setOperatorObjId(src.getOperatorObjId());	
		tar.setOperatorObjName(src.getOperatorObjName());	
		tar.setOperatorObjCode(src.getOperatorObjCode());
		tar.setOper(src.getOper());
		tar.setOperType(src.getOperType());
		tar.setPageName(src.getPageName());
		tar.setModule(src.getModule());
		tar.setPageNameId(src.getPageNameId());
		tar.setModuleId(src.getModuleId());
		tar.setContent(JSON.parse(src.getMsgContent()));
		tar.setType(src.getType());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}
	
	public static List<LogInfoDTO> toDTOs(List<LogInfoVO> srcs) {
		if (srcs == null)
			return null;
		List<LogInfoDTO> list = new ArrayList<LogInfoDTO>();
		for (LogInfoVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<LogInfoVO> toVO(List<LogInfoDTO> srcs) {
		if (srcs == null)
			return null;
		List<LogInfoVO> list = new ArrayList<LogInfoVO>();
		for (LogInfoDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static LogInfoDTO toDTO(LogCondition src) {
		if (src == null)
		return null;	
		LogInfoDTO tar = new LogInfoDTO();
		tar.setId(src.getId());
		tar.setMsgId(src.getMsgId());
		tar.setMsgContent(src.getMsgContent());
		tar.setOperatorName(src.getOperatorName());
		tar.setOperatorId(src.getOperatorId());
		tar.setOperObject(src.getOperObject());
		tar.setModuleName(src.getModuleName());
		tar.setTime(src.getTime());
		tar.setIp(src.getIp());
		tar.setClientType(src.getClientType());
		tar.setMobileVersion(src.getMobileVersion());
		tar.setOperatorObjId(src.getOperatorObjId());
		tar.setOperatorObjName(src.getOperatorObjName());
		tar.setOperatorObjCode(src.getOperatorObjCode());
		tar.setOper(src.getOper());
		tar.setOperType(src.getOperType());
		tar.setPageName(src.getPageName());
		tar.setModule(src.getModule());
		tar.setPageNameId(src.getPageNameId());
		tar.setModuleId(src.getModuleId());
		tar.setStartTime(src.getStartTime());
		tar.setEndTime(src.getEndTime());
		tar.setType(src.getType());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static LogCondition toPO(LogInfoDTO src) {
		if (src == null)
		return null;	
		LogCondition tar = new LogCondition();
		tar.setId(src.getId());
		tar.setMsgId(src.getMsgId());
		tar.setMsgContent(src.getMsgContent());
		tar.setOperatorName(src.getOperatorName());
		tar.setOperatorId(src.getOperatorId());
		tar.setOperObject(src.getOperObject());
		tar.setModuleName(src.getModuleName());
		tar.setTime(src.getTime());
		tar.setIp(src.getIp());
		tar.setClientType(src.getClientType());
		tar.setMobileVersion(src.getMobileVersion());
		tar.setOperatorObjId(src.getOperatorObjId());
		tar.setOperatorObjName(src.getOperatorObjName());
		tar.setOperatorObjCode(src.getOperatorObjCode());
		tar.setOper(src.getOper());
		tar.setOperType(src.getOperType());
		tar.setPageName(src.getPageName());
		tar.setModule(src.getModule());
		tar.setPageNameId(src.getPageNameId());
		tar.setModuleId(src.getModuleId());
		tar.setStartTime(src.getStartTime());
		tar.setEndTime(src.getEndTime());
		tar.setType(src.getType());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}
	
	public static List<LogInfoDTO> toDTO(List<LogCondition> srcs) {
		if (srcs == null)
			return null;
		List<LogInfoDTO> list = new ArrayList<LogInfoDTO>();
		for (LogCondition src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<LogCondition> toPO(List<LogInfoDTO> srcs) {
		if (srcs == null)
			return null;
		List<LogCondition> list = new ArrayList<LogCondition>();
		for (LogInfoDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	