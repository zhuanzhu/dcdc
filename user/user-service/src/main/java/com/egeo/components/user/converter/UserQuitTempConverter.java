package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.UserQuitTempDTO;
import com.egeo.components.user.po.UserQuitTempPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author ghw
 * @date 2018-01-17 10:10:02
 */
public class UserQuitTempConverter {
	
	public static UserQuitTempDTO toDTO(UserQuitTempPO src) {
		if (src == null)
		return null;	
		UserQuitTempDTO tar = new UserQuitTempDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setCompanyId(src.getCompanyId());
		tar.setMemberCode(src.getMemberCode());
		tar.setName(src.getName());
		tar.setMail(src.getMail());
		tar.setQuitTime(src.getQuitTime());
		tar.setInvalidTime(src.getInvalidTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static UserQuitTempPO toPO(UserQuitTempDTO src) {
		if (src == null)
		return null;	
		UserQuitTempPO tar = new UserQuitTempPO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setCompanyId(src.getCompanyId());
		tar.setMemberCode(src.getMemberCode());
		tar.setName(src.getName());
		tar.setMail(src.getMail());
		tar.setQuitTime(src.getQuitTime());
		tar.setInvalidTime(src.getInvalidTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<UserQuitTempDTO> toDTO(List<UserQuitTempPO> srcs) {
		if (srcs == null)
			return null;
		List<UserQuitTempDTO> list = new ArrayList<UserQuitTempDTO>();
		for (UserQuitTempPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<UserQuitTempPO> toPO(List<UserQuitTempDTO> srcs) {
		if (srcs == null)
			return null;
		List<UserQuitTempPO> list = new ArrayList<UserQuitTempPO>();
		for (UserQuitTempDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	