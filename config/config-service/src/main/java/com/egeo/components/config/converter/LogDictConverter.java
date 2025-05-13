package com.egeo.components.config.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.config.dto.LogDictDTO;
import com.egeo.components.config.po.LogDictPO;
import com.egeo.components.config.vo.LogDictSimpleVO;
import com.egeo.components.config.vo.LogDictVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author wyy
 * @date 2018-05-24 09:34:11
 */
public class LogDictConverter {

	
	public static LogDictDTO toDTO(LogDictVO src) {
		if (src == null)
		return null;	
		LogDictDTO tar = new LogDictDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());	
		tar.setName(src.getName());	
		tar.setShortCode(src.getShortCode());	
		tar.setOperatortype(src.getOperatortype());	
		tar.setArealevel(src.getArealevel());	
		tar.setOperCodeId(src.getOperCodeId());	
		tar.setDescription(src.getDescription());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static LogDictVO toVO(LogDictDTO src) {
		if (src == null)
		return null;	
		LogDictVO tar = new LogDictVO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());	
		tar.setName(src.getName());	
		tar.setShortCode(src.getShortCode());	
		tar.setOperatortype(src.getOperatortype());	
		tar.setArealevel(src.getArealevel());	
		tar.setOperCodeId(src.getOperCodeId());	
		tar.setDescription(src.getDescription());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<LogDictDTO> toDTOs(List<LogDictVO> srcs) {
		if (srcs == null)
			return null;
		List<LogDictDTO> list = new ArrayList<LogDictDTO>();
		for (LogDictVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<LogDictVO> toVO(List<LogDictDTO> srcs) {
		if (srcs == null)
			return null;
		List<LogDictVO> list = new ArrayList<LogDictVO>();
		for (LogDictDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	
	public static LogDictSimpleVO toSimpleVO(LogDictDTO src) {
		if (src == null)
		return null;	
		LogDictSimpleVO tar = new LogDictSimpleVO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		return tar;
	}
	
	public static List<LogDictSimpleVO> toSimpleVO(List<LogDictDTO> srcs) {
		if (srcs == null)
			return null;
		List<LogDictSimpleVO> list = new ArrayList<LogDictSimpleVO>();
		for (LogDictDTO src : srcs) {
			list.add(toSimpleVO(src));
		}
		return list;
	}
	public static LogDictDTO toDTO(LogDictPO src) {
		if (src == null)
		return null;	
		LogDictDTO tar = new LogDictDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setName(src.getName());
		tar.setShortCode(src.getShortCode());
		tar.setOperatortype(src.getOperatortype());
		tar.setArealevel(src.getArealevel());
		tar.setOperCodeId(src.getOperCodeId());
		tar.setDescription(src.getDescription());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static LogDictPO toPO(LogDictDTO src) {
		if (src == null)
		return null;	
		LogDictPO tar = new LogDictPO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setName(src.getName());
		tar.setShortCode(src.getShortCode());
		tar.setOperatortype(src.getOperatortype());
		tar.setArealevel(src.getArealevel());
		tar.setOperCodeId(src.getOperCodeId());
		tar.setDescription(src.getDescription());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<LogDictDTO> toDTO(List<LogDictPO> srcs) {
		if (srcs == null)
			return null;
		List<LogDictDTO> list = new ArrayList<LogDictDTO>();
		for (LogDictPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<LogDictPO> toPO(List<LogDictDTO> srcs) {
		if (srcs == null)
			return null;
		List<LogDictPO> list = new ArrayList<LogDictPO>();
		for (LogDictDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	