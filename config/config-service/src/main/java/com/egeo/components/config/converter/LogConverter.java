package com.egeo.components.config.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.config.dto.LogDTO;
import com.egeo.components.config.po.LogPO;
import com.egeo.components.config.vo.LogVO;
import com.egeo.log.EgeoLog;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xia
 * @date 2018-05-25 10:21:14
 */
public class LogConverter {

	
	public static LogDTO toDTO(LogVO src) {
		if (src == null)
		return null;	
		LogDTO tar = new LogDTO();
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
		tar.setType(src.getType());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static LogVO toVO(LogDTO src) {
		if (src == null)
		return null;	
		LogVO tar = new LogVO();
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
		tar.setType(src.getType());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<LogDTO> toDTOs(List<LogVO> srcs) {
		if (srcs == null)
			return null;
		List<LogDTO> list = new ArrayList<LogDTO>();
		for (LogVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<LogVO> toVO(List<LogDTO> srcs) {
		if (srcs == null)
			return null;
		List<LogVO> list = new ArrayList<LogVO>();
		for (LogDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static LogDTO toDTO(LogPO src) {
		if (src == null)
		return null;	
		LogDTO tar = new LogDTO();
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
		tar.setType(src.getType());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static LogPO toPO(LogDTO src) {
		if (src == null)
		return null;	
		LogPO tar = new LogPO();
		tar.setId(src.getId());
		tar.setMsgId(src.getMsgId());
		tar.setMsgContent(src.getMsgContent());
		tar.setOperatorName(src.getOperatorName());
		tar.setOperatorId(src.getOperatorId());
		tar.setType(src.getType());
		tar.setOperObject(src.getOperObject());
		tar.setModuleName(src.getModuleName());
		tar.setTime(src.getTime());
		tar.setIp(src.getIp());
		tar.setClientType(src.getClientType());
		tar.setMobileVersion(src.getMobileVersion());
		tar.setOperatorObjId(src.getOperatorObjId());
		tar.setOperatorObjName(src.getOperatorObjName());
		tar.setOperatorObjCode(src.getOperatorObjCode());
		tar.setType(src.getType());
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}
	
	public static LogDTO egeoLogtoDTO(EgeoLog src) {
		if (src == null)
			return null;	
			LogDTO tar = new LogDTO();
//			tar.setId(src.getId());
			tar.setMsgId(src.getMsgId());
			tar.setMsgContent(src.getMsgContent());
			tar.setOperatorName(src.getOperatorName());
			tar.setOperatorId(src.getOperatorId());
			tar.setOperObject(src.getOperObject());
			tar.setModuleName(src.getModuleName());
			tar.setTime(src.getTime());
			tar.setIp(src.getIp());
			tar.setClientType(src.getClientType());
//			tar.setOperatorType(src.getOperatorType());
			tar.setMobileVersion(src.getMobileVersion());
			tar.setOperatorObjId(src.getOperatorObjId());
			tar.setType(src.getType());
			tar.setOperatorObjName(src.getOperatorObjName());
			tar.setOperatorObjCode(src.getOperatorObjCode());
			tar.setPlatformId(src.getPlatformId());
			return tar;
	}

	public static List<LogDTO> toDTO(List<LogPO> srcs) {
		if (srcs == null)
			return null;
		List<LogDTO> list = new ArrayList<LogDTO>();
		for (LogPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<LogPO> toPO(List<LogDTO> srcs) {
		if (srcs == null)
			return null;
		List<LogPO> list = new ArrayList<LogPO>();
		for (LogDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	