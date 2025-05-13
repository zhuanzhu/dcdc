package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoThirdpartyDTO;
import com.egeo.components.order.po.SoThirdpartyPO;
import com.egeo.components.order.vo.SoThirdpartyVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author wyy
 * @date 2018-06-08 11:21:08
 */
public class SoThirdpartyConverter {

	public static SoThirdpartyDTO toDTO(SoThirdpartyVO src) {
		if (src == null)
		return null;	
		SoThirdpartyDTO tar = new SoThirdpartyDTO();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());	
		tar.setThirdpartyId(src.getThirdpartyId());	
		tar.setThirdpartyStatus(src.getThirdpartyStatus());	
		tar.setUserName(src.getUserName());	
		tar.setIdCardNum(src.getIdCardNum());	
		tar.setPhone(src.getPhone());	
		tar.setOidCardNum(src.getOidCardNum());	
		tar.setComment(src.getComment());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());
		tar.setThirdpartyPayTime(src.getThirdpartyPayTime());
		tar.setThirdpartyPayAmount(src.getThirdpartyPayAmount());
		return tar;
	}

	public static SoThirdpartyVO toVO(SoThirdpartyDTO src) {
		if (src == null)
		return null;	
		SoThirdpartyVO tar = new SoThirdpartyVO();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());	
		tar.setThirdpartyId(src.getThirdpartyId());	
		tar.setThirdpartyStatus(src.getThirdpartyStatus());	
		tar.setUserName(src.getUserName());	
		tar.setIdCardNum(src.getIdCardNum());	
		tar.setPhone(src.getPhone());	
		tar.setOidCardNum(src.getOidCardNum());	
		tar.setComment(src.getComment());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());
		tar.setThirdpartyPayTime(src.getThirdpartyPayTime());
		tar.setThirdpartyPayAmount(src.getThirdpartyPayAmount());
		return tar;
	}

	public static List<SoThirdpartyDTO> toDTOs(List<SoThirdpartyVO> srcs) {
		if (srcs == null)
			return null;
		List<SoThirdpartyDTO> list = new ArrayList<SoThirdpartyDTO>();
		for (SoThirdpartyVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoThirdpartyVO> toVO(List<SoThirdpartyDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoThirdpartyVO> list = new ArrayList<SoThirdpartyVO>();
		for (SoThirdpartyDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static SoThirdpartyDTO toDTO(SoThirdpartyPO src) {
		if (src == null)
		return null;	
		SoThirdpartyDTO tar = new SoThirdpartyDTO();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());
		tar.setThirdpartyId(src.getThirdpartyId());
		tar.setThirdpartyStatus(src.getThirdpartyStatus());
		tar.setUserName(src.getUserName());
		tar.setIdCardNum(src.getIdCardNum());
		tar.setPhone(src.getPhone());
		tar.setOidCardNum(src.getOidCardNum());
		tar.setComment(src.getComment());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSoChildCode(src.getSoChildCode());
		tar.setThirdpartyPayTime(src.getThirdpartyPayTime());
		tar.setThirdpartyPayAmount(src.getThirdpartyPayAmount());

		return tar;
	}

	public static SoThirdpartyPO toPO(SoThirdpartyDTO src) {
		if (src == null)
		return null;	
		SoThirdpartyPO tar = new SoThirdpartyPO();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());
		tar.setThirdpartyId(src.getThirdpartyId());
		tar.setThirdpartyStatus(src.getThirdpartyStatus());
		tar.setUserName(src.getUserName());
		tar.setIdCardNum(src.getIdCardNum());
		tar.setPhone(src.getPhone());
		tar.setOidCardNum(src.getOidCardNum());
		tar.setComment(src.getComment());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSoChildCode(src.getSoChildCode());
		tar.setThirdpartyPayTime(src.getThirdpartyPayTime());
		tar.setThirdpartyPayAmount(src.getThirdpartyPayAmount());
		return tar;
	}

	public static List<SoThirdpartyDTO> toDTO(List<SoThirdpartyPO> srcs) {
		if (srcs == null)
			return null;
		List<SoThirdpartyDTO> list = new ArrayList<SoThirdpartyDTO>();
		for (SoThirdpartyPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoThirdpartyPO> toPO(List<SoThirdpartyDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoThirdpartyPO> list = new ArrayList<SoThirdpartyPO>();
		for (SoThirdpartyDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	