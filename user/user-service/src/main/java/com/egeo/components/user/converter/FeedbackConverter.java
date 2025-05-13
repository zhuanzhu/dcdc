package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.FeedbackDTO;
import com.egeo.components.user.po.FeedbackPO;
import com.egeo.components.user.vo.FeedbackVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-22 14:41:30
 */
public class FeedbackConverter {
	
	public static FeedbackDTO toDTO(FeedbackPO src) {
		if (src == null)
		return null;	
		FeedbackDTO tar = new FeedbackDTO();
		tar.setId(src.getId());
		tar.setFeedback(src.getFeedback());
		tar.setOrderCode(src.getOrderCode());
		tar.setSystemVersion(src.getSystemVersion());
		tar.setDeviceModel(src.getDeviceModel());
		tar.setDeviceId(src.getDeviceId());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserip(src.getCreateUserip());
		tar.setCreateUsermac(src.getCreateUsermac());
		tar.setClientVersionno(src.getClientVersionno());
		tar.setPlatformId(src.getPlatformId());
		tar.setFeedbackRslt(src.getFeedbackRslt());
		tar.setFeedStatus(src.getFeedStatus());
		tar.setUpdateUserid(src.getUpdateUserid());
		tar.setUpdateUsername(src.getUpdateUsername());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static FeedbackPO toPO(FeedbackDTO src) {
		if (src == null)
		return null;	
		FeedbackPO tar = new FeedbackPO();
		tar.setId(src.getId());
		tar.setFeedback(src.getFeedback());
		tar.setOrderCode(src.getOrderCode());
		tar.setSystemVersion(src.getSystemVersion());
		tar.setDeviceModel(src.getDeviceModel());
		tar.setDeviceId(src.getDeviceId());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserip(src.getCreateUserip());
		tar.setCreateUsermac(src.getCreateUsermac());
		tar.setClientVersionno(src.getClientVersionno());
		tar.setPlatformId(src.getPlatformId());
		tar.setFeedbackRslt(src.getFeedbackRslt());
		tar.setFeedStatus(src.getFeedStatus());
		tar.setUpdateUserid(src.getUpdateUserid());
		tar.setUpdateUsername(src.getUpdateUsername());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<FeedbackDTO> toDTO(List<FeedbackPO> srcs) {
		if (srcs == null)
			return null;
		List<FeedbackDTO> list = new ArrayList<FeedbackDTO>();
		for (FeedbackPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<FeedbackPO> toPO(List<FeedbackDTO> srcs) {
		if (srcs == null)
			return null;
		List<FeedbackPO> list = new ArrayList<FeedbackPO>();
		for (FeedbackDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	
	

	
	public static FeedbackDTO toDTO(FeedbackVO src) {
		if (src == null)
		return null;	
		FeedbackDTO tar = new FeedbackDTO();
		tar.setId(src.getId());
		tar.setFeedback(src.getFeedback());	
		tar.setOrderCode(src.getOrderCode());	
		tar.setSystemVersion(src.getSystemVersion());	
		tar.setDeviceModel(src.getDeviceModel());	
		tar.setDeviceId(src.getDeviceId());	
		tar.setCreateUserid(src.getCreateUserid());	
		tar.setCreateUsername(src.getCreateUsername());	
		tar.setCreateUserip(src.getCreateUserip());	
		tar.setCreateUsermac(src.getCreateUsermac());	
		tar.setClientVersionno(src.getClientVersionno());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setFeedbackRslt(src.getFeedbackRslt());
		tar.setFeedStatus(src.getFeedStatus());
		tar.setUpdateUserid(src.getUpdateUserid());
		tar.setUpdateUsername(src.getUpdateUsername());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static FeedbackVO toVO(FeedbackDTO src) {
		if (src == null)
		return null;	
		FeedbackVO tar = new FeedbackVO();
		tar.setId(src.getId());
		tar.setFeedback(src.getFeedback());	
		tar.setOrderCode(src.getOrderCode());	
		tar.setSystemVersion(src.getSystemVersion());	
		tar.setDeviceModel(src.getDeviceModel());	
		tar.setDeviceId(src.getDeviceId());	
		tar.setCreateUserid(src.getCreateUserid());	
		tar.setCreateUsername(src.getCreateUsername());	
		tar.setCreateUserip(src.getCreateUserip());	
		tar.setCreateUsermac(src.getCreateUsermac());	
		tar.setClientVersionno(src.getClientVersionno());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setFeedbackRslt(src.getFeedbackRslt());
		tar.setFeedStatus(src.getFeedStatus());
		tar.setUpdateUserid(src.getUpdateUserid());
		tar.setUpdateUsername(src.getUpdateUsername());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}


	public static List<FeedbackVO> toVO(List<FeedbackDTO> srcs) {
		if (srcs == null)
			return null;
		List<FeedbackVO> list = new ArrayList<FeedbackVO>();
		for (FeedbackDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	