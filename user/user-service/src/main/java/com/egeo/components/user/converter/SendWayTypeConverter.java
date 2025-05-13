package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.SendWayTypeDTO;
import com.egeo.components.user.po.SendWayTypePO;
import com.egeo.components.user.vo.SendWayTypeVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-08-14 09:46:37
 */
public class SendWayTypeConverter {
	
	public static SendWayTypeDTO toDTO(SendWayTypePO src) {
		if (src == null)
		return null;	
		SendWayTypeDTO tar = new SendWayTypeDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static SendWayTypePO toPO(SendWayTypeDTO src) {
		if (src == null)
		return null;	
		SendWayTypePO tar = new SendWayTypePO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<SendWayTypeDTO> toDTO(List<SendWayTypePO> srcs) {
		if (srcs == null)
			return null;
		List<SendWayTypeDTO> list = new ArrayList<SendWayTypeDTO>();
		for (SendWayTypePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SendWayTypePO> toPO(List<SendWayTypeDTO> srcs) {
		if (srcs == null)
			return null;
		List<SendWayTypePO> list = new ArrayList<SendWayTypePO>();
		for (SendWayTypeDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	

	
	public static SendWayTypeDTO toDTO(SendWayTypeVO src) {
		if (src == null)
		return null;	
		SendWayTypeDTO tar = new SendWayTypeDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setCreateTime(src.getCreateTime());	
		return tar;
	}

	public static SendWayTypeVO toVO(SendWayTypeDTO src) {
		if (src == null)
		return null;	
		SendWayTypeVO tar = new SendWayTypeVO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setCreateTime(src.getCreateTime());	
		return tar;
	}


	public static List<SendWayTypeVO> toVO(List<SendWayTypeDTO> srcs) {
		if (srcs == null)
			return null;
		List<SendWayTypeVO> list = new ArrayList<SendWayTypeVO>();
		for (SendWayTypeDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	