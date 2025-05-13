package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.egeo.components.user.dto.UserTempDTO;
import com.egeo.components.user.po.UserTempPO;
import com.egeo.components.user.vo.UserTempVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author ghw
 * @date 2018-01-16 10:21:04
 */
public class UserTempConverter {


	public static List<UserTempDTO> toDTOs(List<UserTempVO> srcs) {
		if (srcs == null)
			return null;
		List<UserTempDTO> list = new ArrayList<UserTempDTO>();
		for (UserTempVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}
	public static UserTempDTO toDTO(UserTempVO src) {
		if (src == null)
		return null;	
		UserTempDTO tar = new UserTempDTO();
		tar.setId(src.getId());
		tar.setCompanyId(src.getCompanyId());
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setDepartmentId(src.getDepartmentId());	
		tar.setName(src.getName());	
		tar.setMemberCode(src.getMemberCode());	
		tar.setMail(src.getMail());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setBirthday(src.getBirthday());
		tar.setSex(src.getSex());
		tar.setMobile(src.getMobile());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserip(src.getCreateUserip());
		tar.setCreateUsermac(src.getCreateUsermac());
		tar.setType(src.getType());
		return tar;
	}

	public static UserTempVO toVO(UserTempDTO src) {
		if (src == null)
		return null;	
		UserTempVO tar = new UserTempVO();
		tar.setId(src.getId());
		tar.setCompanyId(src.getCompanyId());	
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setDepartmentId(src.getDepartmentId());	
		tar.setName(src.getName());	
		tar.setMemberCode(src.getMemberCode());	
		tar.setMail(src.getMail());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setBirthday(src.getBirthday());
		tar.setSex(src.getSex());
		tar.setMobile(src.getMobile());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserip(src.getCreateUserip());
		tar.setCreateUsermac(src.getCreateUsermac());
		tar.setType(src.getType());
		return tar;
	}

	public static List<UserTempDTO> toDTO(Set<UserTempVO> srcs) {
		if (srcs == null)
			return null;
		List<UserTempDTO> list = new ArrayList<UserTempDTO>();
		for (UserTempVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}
	public static List<UserTempVO> toVO(List<UserTempDTO> srcs) {
		if (srcs == null)
			return null;
		List<UserTempVO> list = new ArrayList<UserTempVO>();
		for (UserTempDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static UserTempDTO toDTO(UserTempPO src) {
		if (src == null)
		return null;	
		UserTempDTO tar = new UserTempDTO();
		tar.setId(src.getId());
		tar.setCompanyId(src.getCompanyId());
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setDepartmentId(src.getDepartmentId());
		tar.setName(src.getName());
		tar.setMemberCode(src.getMemberCode());
		tar.setMail(src.getMail());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setBirthday(src.getBirthday());
		tar.setSex(src.getSex());
		tar.setMobile(src.getMobile());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserip(src.getCreateUserip());
		tar.setCreateUsermac(src.getCreateUsermac());
		tar.setImportRecordsId(src.getImportRecordsId());
		tar.setType(src.getType());
		return tar;
	}

	public static UserTempPO toPO(UserTempDTO src) {
		if (src == null)
		return null;	
		UserTempPO tar = new UserTempPO();
		tar.setId(src.getId());
		tar.setCompanyId(src.getCompanyId());
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setDepartmentId(src.getDepartmentId());
		tar.setName(src.getName());
		tar.setMemberCode(src.getMemberCode());
		tar.setMail(src.getMail());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setBirthday(src.getBirthday());
		tar.setSex(src.getSex());
		tar.setMobile(src.getMobile());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserip(src.getCreateUserip());
		tar.setCreateUsermac(src.getCreateUsermac());
		tar.setImportRecordsId(src.getImportRecordsId());
		tar.setType(src.getType());
		return tar;
	}

	public static List<UserTempDTO> toDTO(List<UserTempPO> srcs) {
		if (srcs == null)
			return null;
		List<UserTempDTO> list = new ArrayList<UserTempDTO>();
		for (UserTempPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<UserTempPO> toPO(List<UserTempDTO> srcs) {
		if (srcs == null)
			return null;
		List<UserTempPO> list = new ArrayList<UserTempPO>();
		for (UserTempDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	