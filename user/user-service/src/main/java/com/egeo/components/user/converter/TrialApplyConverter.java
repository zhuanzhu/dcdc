package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.TrialApplyDTO;
import com.egeo.components.user.po.TrialApplyPO;
import com.egeo.components.user.vo.TrialApplyVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author wyy
 * @date 2018-05-31 16:09:49
 */
public class TrialApplyConverter {

	
	public static TrialApplyDTO toDTO(TrialApplyVO src) {
		if (src == null)
		return null;	
		TrialApplyDTO tar = new TrialApplyDTO();
		tar.setId(src.getId());
		tar.setCompanyName(src.getCompanyName());	
		tar.setCompanyScale(src.getCompanyScale());	
		tar.setUserName(src.getUserName());	
		tar.setPhone(src.getPhone());	
		tar.setPosition(src.getPosition());	
		tar.setMail(src.getMail());	
		tar.setMessage(src.getMessage());	
		tar.setClientType(src.getClientType());	
		tar.setStatus(src.getStatus());	
		tar.setDealUser(src.getDealUser());	
		tar.setDealTime(src.getDealTime());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static TrialApplyVO toVO(TrialApplyDTO src) {
		if (src == null)
		return null;	
		TrialApplyVO tar = new TrialApplyVO();
		tar.setId(src.getId());
		tar.setCompanyName(src.getCompanyName());	
		tar.setCompanyScale(src.getCompanyScale());	
		tar.setUserName(src.getUserName());	
		tar.setPhone(src.getPhone());	
		tar.setPosition(src.getPosition());	
		tar.setMail(src.getMail());	
		tar.setMessage(src.getMessage());	
		tar.setClientType(src.getClientType());	
		tar.setStatus(src.getStatus());	
		tar.setDealUser(src.getDealUser());	
		tar.setDealTime(src.getDealTime());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}


	public static List<TrialApplyVO> toVO(List<TrialApplyDTO> srcs) {
		if (srcs == null)
			return null;
		List<TrialApplyVO> list = new ArrayList<TrialApplyVO>();
		for (TrialApplyDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static TrialApplyDTO toDTO(TrialApplyPO src) {
		if (src == null)
		return null;	
		TrialApplyDTO tar = new TrialApplyDTO();
		tar.setId(src.getId());
		tar.setCompanyName(src.getCompanyName());
		tar.setCompanyScale(src.getCompanyScale());
		tar.setUserName(src.getUserName());
		tar.setPhone(src.getPhone());
		tar.setPosition(src.getPosition());
		tar.setMail(src.getMail());
		tar.setMessage(src.getMessage());
		tar.setClientType(src.getClientType());
		tar.setStatus(src.getStatus());
		tar.setDealUser(src.getDealUser());
		tar.setDealTime(src.getDealTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static TrialApplyPO toPO(TrialApplyDTO src) {
		if (src == null)
		return null;	
		TrialApplyPO tar = new TrialApplyPO();
		tar.setId(src.getId());
		tar.setCompanyName(src.getCompanyName());
		tar.setCompanyScale(src.getCompanyScale());
		tar.setUserName(src.getUserName());
		tar.setPhone(src.getPhone());
		tar.setPosition(src.getPosition());
		tar.setMail(src.getMail());
		tar.setMessage(src.getMessage());
		tar.setClientType(src.getClientType());
		tar.setStatus(src.getStatus());
		tar.setDealUser(src.getDealUser());
		tar.setDealTime(src.getDealTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<TrialApplyDTO> toDTO(List<TrialApplyPO> srcs) {
		if (srcs == null)
			return null;
		List<TrialApplyDTO> list = new ArrayList<TrialApplyDTO>();
		for (TrialApplyPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<TrialApplyPO> toPO(List<TrialApplyDTO> srcs) {
		if (srcs == null)
			return null;
		List<TrialApplyPO> list = new ArrayList<TrialApplyPO>();
		for (TrialApplyDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	