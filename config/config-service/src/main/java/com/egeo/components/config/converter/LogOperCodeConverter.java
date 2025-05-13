package com.egeo.components.config.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.config.dto.LogOperCodeDTO;
import com.egeo.components.config.po.LogOperCodePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author wyy
 * @date 2018-05-24 09:34:53
 */
public class LogOperCodeConverter {
	
	public static LogOperCodeDTO toDTO(LogOperCodePO src) {
		if (src == null)
		return null;	
		LogOperCodeDTO tar = new LogOperCodeDTO();
		tar.setId(src.getId());
		tar.setOperCode(src.getOperCode());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static LogOperCodePO toPO(LogOperCodeDTO src) {
		if (src == null)
		return null;	
		LogOperCodePO tar = new LogOperCodePO();
		tar.setId(src.getId());
		tar.setOperCode(src.getOperCode());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<LogOperCodeDTO> toDTO(List<LogOperCodePO> srcs) {
		if (srcs == null)
			return null;
		List<LogOperCodeDTO> list = new ArrayList<LogOperCodeDTO>();
		for (LogOperCodePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<LogOperCodePO> toPO(List<LogOperCodeDTO> srcs) {
		if (srcs == null)
			return null;
		List<LogOperCodePO> list = new ArrayList<LogOperCodePO>();
		for (LogOperCodeDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	