package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.UserInfoDTO;
import com.egeo.components.user.po.UserInfoPO;
import com.egeo.components.user.vo.UserInfoVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-08-15 10:44:50
 */
public class UserInfoConverter {

	
	public static UserInfoDTO toDTO(UserInfoVO src) {
		if (src == null)
		return null;	
		UserInfoDTO tar = new UserInfoDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());	
		tar.setInfoId(src.getInfoId());	
		tar.setIsRead(src.getIsRead());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static UserInfoVO toVO(UserInfoDTO src) {
		if (src == null)
		return null;	
		UserInfoVO tar = new UserInfoVO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());	
		tar.setInfoId(src.getInfoId());	
		tar.setIsRead(src.getIsRead());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}


	public static List<UserInfoVO> toVO(List<UserInfoDTO> srcs) {
		if (srcs == null)
			return null;
		List<UserInfoVO> list = new ArrayList<UserInfoVO>();
		for (UserInfoDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static UserInfoDTO toDTO(UserInfoPO src) {
		if (src == null)
		return null;	
		UserInfoDTO tar = new UserInfoDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setInfoId(src.getInfoId());
		tar.setIsRead(src.getIsRead());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static UserInfoPO toPO(UserInfoDTO src) {
		if (src == null)
		return null;	
		UserInfoPO tar = new UserInfoPO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setInfoId(src.getInfoId());
		tar.setIsRead(src.getIsRead());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<UserInfoDTO> toDTO(List<UserInfoPO> srcs) {
		if (srcs == null)
			return null;
		List<UserInfoDTO> list = new ArrayList<UserInfoDTO>();
		for (UserInfoPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<UserInfoPO> toPO(List<UserInfoDTO> srcs) {
		if (srcs == null)
			return null;
		List<UserInfoPO> list = new ArrayList<UserInfoPO>();
		for (UserInfoDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	