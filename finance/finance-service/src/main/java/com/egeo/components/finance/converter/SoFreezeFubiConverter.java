package com.egeo.components.finance.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.finance.dto.SoFreezeFubiDTO;
import com.egeo.components.finance.po.SoFreezeFubiPO;
import com.egeo.components.finance.vo.SoFreezeFubiVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-02-07 17:53:03
 */
public class SoFreezeFubiConverter {
	
	
	public static SoFreezeFubiDTO toDTO(SoFreezeFubiVO src) {
		if (src == null)
		return null;	
		SoFreezeFubiDTO tar = new SoFreezeFubiDTO();
		tar.setId(src.getId());
		tar.setSoId(src.getSoId());	
		tar.setBalance(src.getBalance());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUserId(src.getUserId());	
		return tar;
	}

	public static SoFreezeFubiVO toVO(SoFreezeFubiDTO src) {
		if (src == null)
		return null;	
		SoFreezeFubiVO tar = new SoFreezeFubiVO();
		tar.setId(src.getId());
		tar.setSoId(src.getSoId());	
		tar.setBalance(src.getBalance());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUserId(src.getUserId());	
		return tar;
	}
/*
	public static List<SoFreezeFubiDTO> toDTO(List<SoFreezeFubiVO> srcs) {
		if (srcs == null)
			return null;
		List<SoFreezeFubiDTO> list = new ArrayList<SoFreezeFubiDTO>();
		for (SoFreezeFubiVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}*/

	public static List<SoFreezeFubiVO> toVO(List<SoFreezeFubiDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoFreezeFubiVO> list = new ArrayList<SoFreezeFubiVO>();
		for (SoFreezeFubiDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static SoFreezeFubiDTO toDTO(SoFreezeFubiPO src) {
		if (src == null)
		return null;	
		SoFreezeFubiDTO tar = new SoFreezeFubiDTO();
		tar.setId(src.getId());
		tar.setSoId(src.getSoId());
		tar.setBalance(src.getBalance());
		tar.setCreateTime(src.getCreateTime());
		tar.setUserId(src.getUserId());
		return tar;
	}

	public static SoFreezeFubiPO toPO(SoFreezeFubiDTO src) {
		if (src == null)
		return null;	
		SoFreezeFubiPO tar = new SoFreezeFubiPO();
		tar.setId(src.getId());
		tar.setSoId(src.getSoId());
		tar.setBalance(src.getBalance());
		tar.setCreateTime(src.getCreateTime());
		tar.setUserId(src.getUserId());
		return tar;
	}

	public static List<SoFreezeFubiDTO> toDTO(List<SoFreezeFubiPO> srcs) {
		if (srcs == null)
			return null;
		List<SoFreezeFubiDTO> list = new ArrayList<SoFreezeFubiDTO>();
		for (SoFreezeFubiPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoFreezeFubiPO> toPO(List<SoFreezeFubiDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoFreezeFubiPO> list = new ArrayList<SoFreezeFubiPO>();
		for (SoFreezeFubiDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	