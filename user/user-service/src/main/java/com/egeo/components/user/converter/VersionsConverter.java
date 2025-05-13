package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.VersionsDTO;
import com.egeo.components.user.po.VersionsPO;
import com.egeo.components.user.vo.VersionsVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-02-04 16:05:49
 */
public class VersionsConverter {

	public static VersionsDTO toDTO(VersionsVO src) {
		if (src == null)
		return null;	
		VersionsDTO tar = new VersionsDTO();
		tar.setId(src.getId());
		tar.setVersionsMark(src.getVersionsMark());	
		tar.setType(src.getType());	
		tar.setReleaseDate(src.getReleaseDate());	
		tar.setIsConstraint(src.getIsConstraint());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setUrl(src.getUrl());
		tar.setDescription(src.getDescription());
		tar.setVersionCode(src.getVersionCode());
		tar.setInstallName(src.getInstallName());
		tar.setResume(src.getResume());
		tar.setIsOfficial(src.getIsOfficial());
		tar.setVersionStatus(src.getVersionStatus());
		tar.setPlatformId(src.getPlatformId());	
		tar.setUser(src.getUser());
		return tar;
	}

	public static VersionsVO toVO(VersionsDTO src) {
		if (src == null)
		return null;	
		VersionsVO tar = new VersionsVO();
		tar.setId(src.getId());
		tar.setVersionsMark(src.getVersionsMark());	
		tar.setType(src.getType());	
		tar.setReleaseDate(src.getReleaseDate());	
		tar.setIsConstraint(src.getIsConstraint());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setUrl(src.getUrl());
		tar.setDescription(src.getDescription());
		tar.setVersionCode(src.getVersionCode());
		tar.setInstallName(src.getInstallName());
		tar.setResume(src.getResume());
		tar.setIsOfficial(src.getIsOfficial());
		tar.setVersionStatus(src.getVersionStatus());
		tar.setPlatformId(src.getPlatformId());	
		tar.setUser(src.getUser());
		return tar;
	}

	public static List<VersionsVO> toVO(List<VersionsDTO> srcs) {
		if (srcs == null)
			return null;
		List<VersionsVO> list = new ArrayList<VersionsVO>();
		for (VersionsDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static VersionsDTO toDTO(VersionsPO src) {
		if (src == null)
		return null;	
		VersionsDTO tar = new VersionsDTO();
		tar.setId(src.getId());
		tar.setVersionsMark(src.getVersionsMark());
		tar.setVersionCode(src.getVersionCode());
		tar.setType(src.getType());
		tar.setReleaseDate(src.getReleaseDate());
		tar.setIsConstraint(src.getIsConstraint());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setUrl(src.getUrl());
		tar.setDescription(src.getDescription());
	
		tar.setInstallName(src.getInstallName());
		tar.setResume(src.getResume());
		tar.setIsOfficial(src.getIsOfficial());
		tar.setVersionStatus(src.getVersionStatus());
		tar.setPlatformId(src.getPlatformId());
		tar.setUser(src.getUser());
		return tar;
	}

	public static VersionsPO toPO(VersionsDTO src) {
		if (src == null)
		return null;	
		VersionsPO tar = new VersionsPO();
		tar.setId(src.getId());
		tar.setVersionsMark(src.getVersionsMark());
		tar.setType(src.getType());
		tar.setReleaseDate(src.getReleaseDate());
		tar.setIsConstraint(src.getIsConstraint());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setUrl(src.getUrl());
		tar.setVersionCode(src.getVersionCode());
		tar.setInstallName(src.getInstallName());
		tar.setResume(src.getResume());
		tar.setIsOfficial(src.getIsOfficial());
		tar.setVersionStatus(src.getVersionStatus());
		tar.setPlatformId(src.getPlatformId());
		tar.setUser(src.getUser());
		return tar;
	}

	public static List<VersionsDTO> toDTO(List<VersionsPO> srcs) {
		if (srcs == null)
			return null;
		List<VersionsDTO> list = new ArrayList<VersionsDTO>();
		for (VersionsPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<VersionsPO> toPO(List<VersionsDTO> srcs) {
		if (srcs == null)
			return null;
		List<VersionsPO> list = new ArrayList<VersionsPO>();
		for (VersionsDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	