package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.PraisePointDTO;
import com.egeo.components.promotion.po.PraisePointPO;
import com.egeo.components.promotion.vo.PraisePointVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2017-12-25 16:20:37
 */
public class PraisePointConverter {

	
	public static PraisePointDTO toDTO(PraisePointVO src) {
		if (src == null)
		return null;	
		PraisePointDTO tar = new PraisePointDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());	
		tar.setPraisePoint(src.getPraisePoint());
		tar.setCiphertext(src.getCiphertext());
		tar.setUuid(src.getUuid());
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static PraisePointVO toVO(PraisePointDTO src) {
		if (src == null)
		return null;	
		PraisePointVO tar = new PraisePointVO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());	
		tar.setPraisePoint(src.getPraisePoint());
		tar.setCiphertext(src.getCiphertext());
		tar.setUuid(src.getUuid());
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static List<PraisePointDTO> toDTOs(List<PraisePointVO> srcs) {
		if (srcs == null)
			return null;
		List<PraisePointDTO> list = new ArrayList<PraisePointDTO>();
		for (PraisePointVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PraisePointVO> toVO(List<PraisePointDTO> srcs) {
		if (srcs == null)
			return null;
		List<PraisePointVO> list = new ArrayList<PraisePointVO>();
		for (PraisePointDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static PraisePointDTO toDTO(PraisePointPO src) {
		if (src == null)
		return null;	
		PraisePointDTO tar = new PraisePointDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setPraisePoint(src.getPraisePoint());
		tar.setCiphertext(src.getCiphertext());
		tar.setUuid(src.getUuid());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static PraisePointPO toPO(PraisePointDTO src) {
		if (src == null)
		return null;	
		PraisePointPO tar = new PraisePointPO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setPraisePoint(src.getPraisePoint());
		tar.setCiphertext(src.getCiphertext());
		tar.setUuid(src.getUuid());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<PraisePointDTO> toDTO(List<PraisePointPO> srcs) {
		if (srcs == null)
			return null;
		List<PraisePointDTO> list = new ArrayList<PraisePointDTO>();
		for (PraisePointPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PraisePointPO> toPO(List<PraisePointDTO> srcs) {
		if (srcs == null)
			return null;
		List<PraisePointPO> list = new ArrayList<PraisePointPO>();
		for (PraisePointDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	