package com.egeo.components.pay.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.pay.dto.ThirdpartyAwaitQueueDTO;
import com.egeo.components.pay.po.ThirdpartyAwaitQueuePO;
import com.egeo.components.pay.vo.ThirdpartyAwaitQueueVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xia
 * @date 2018-11-13 11:02:10
 */
public class ThirdpartyAwaitQueueConverter {

	
	public static ThirdpartyAwaitQueueDTO toDTO(ThirdpartyAwaitQueueVO src) {
		if (src == null)
		return null;	
		ThirdpartyAwaitQueueDTO tar = new ThirdpartyAwaitQueueDTO();
		tar.setId(src.getId());
		tar.setSoId(src.getSoId());	
		tar.setOrderCode(src.getOrderCode());	
		tar.setRandomNumber(src.getRandomNumber());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setCashPayType(src.getCashPayType());	
		tar.setIsPayTrue(src.getIsPayTrue());	
		tar.setThirdpartyType(src.getThirdpartyType());	
		return tar;
	}

	public static ThirdpartyAwaitQueueVO toVO(ThirdpartyAwaitQueueDTO src) {
		if (src == null)
		return null;	
		ThirdpartyAwaitQueueVO tar = new ThirdpartyAwaitQueueVO();
		tar.setId(src.getId());
		tar.setSoId(src.getSoId());	
		tar.setOrderCode(src.getOrderCode());	
		tar.setRandomNumber(src.getRandomNumber());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setCashPayType(src.getCashPayType());	
		tar.setIsPayTrue(src.getIsPayTrue());	
		tar.setThirdpartyType(src.getThirdpartyType());	
		return tar;
	}

	public static List<ThirdpartyAwaitQueueDTO> toDTOs(List<ThirdpartyAwaitQueueVO> srcs) {
		if (srcs == null)
			return null;
		List<ThirdpartyAwaitQueueDTO> list = new ArrayList<ThirdpartyAwaitQueueDTO>();
		for (ThirdpartyAwaitQueueVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ThirdpartyAwaitQueueVO> toVO(List<ThirdpartyAwaitQueueDTO> srcs) {
		if (srcs == null)
			return null;
		List<ThirdpartyAwaitQueueVO> list = new ArrayList<ThirdpartyAwaitQueueVO>();
		for (ThirdpartyAwaitQueueDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static ThirdpartyAwaitQueueDTO toDTO(ThirdpartyAwaitQueuePO src) {
		if (src == null)
		return null;	
		ThirdpartyAwaitQueueDTO tar = new ThirdpartyAwaitQueueDTO();
		tar.setId(src.getId());
		tar.setSoId(src.getSoId());
		tar.setOrderCode(src.getOrderCode());
		tar.setRandomNumber(src.getRandomNumber());
		tar.setCreateTime(src.getCreateTime());
		tar.setCashPayType(src.getCashPayType());
		tar.setIsPayTrue(src.getIsPayTrue());
		tar.setThirdpartyType(src.getThirdpartyType());
		return tar;
	}

	public static ThirdpartyAwaitQueuePO toPO(ThirdpartyAwaitQueueDTO src) {
		if (src == null)
		return null;	
		ThirdpartyAwaitQueuePO tar = new ThirdpartyAwaitQueuePO();
		tar.setId(src.getId());
		tar.setSoId(src.getSoId());
		tar.setOrderCode(src.getOrderCode());
		tar.setRandomNumber(src.getRandomNumber());
		tar.setCreateTime(src.getCreateTime());
		tar.setCashPayType(src.getCashPayType());
		tar.setIsPayTrue(src.getIsPayTrue());
		tar.setThirdpartyType(src.getThirdpartyType());
		return tar;
	}

	public static List<ThirdpartyAwaitQueueDTO> toDTO(List<ThirdpartyAwaitQueuePO> srcs) {
		if (srcs == null)
			return null;
		List<ThirdpartyAwaitQueueDTO> list = new ArrayList<ThirdpartyAwaitQueueDTO>();
		for (ThirdpartyAwaitQueuePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ThirdpartyAwaitQueuePO> toPO(List<ThirdpartyAwaitQueueDTO> srcs) {
		if (srcs == null)
			return null;
		List<ThirdpartyAwaitQueuePO> list = new ArrayList<ThirdpartyAwaitQueuePO>();
		for (ThirdpartyAwaitQueueDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	