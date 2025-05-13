package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.AuthorityDTO;
import com.egeo.components.product.po.AuthorityPO;
import com.egeo.components.product.vo.AuthorityVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author whf
 * @date 2018-09-06 16:12:29
 */
public class AuthorityConverter {

	
	public static AuthorityDTO toDTO(AuthorityVO src) {
		if (src == null)
		return null;	
		AuthorityDTO tar = new AuthorityDTO();
		tar.setId(src.getId());
		tar.setAuthorityName(src.getAuthorityName());	
		tar.setRemarks(src.getRemarks());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static AuthorityVO toVO(AuthorityDTO src) {
		if (src == null)
		return null;	
		AuthorityVO tar = new AuthorityVO();
		tar.setId(src.getId());
		tar.setAuthorityName(src.getAuthorityName());	
		tar.setRemarks(src.getRemarks());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static List<AuthorityDTO> toDTOs(List<AuthorityVO> srcs) {
		if (srcs == null)
			return null;
		List<AuthorityDTO> list = new ArrayList<AuthorityDTO>();
		for (AuthorityVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<AuthorityVO> toVO(List<AuthorityDTO> srcs) {
		if (srcs == null)
			return null;
		List<AuthorityVO> list = new ArrayList<AuthorityVO>();
		for (AuthorityDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static AuthorityDTO toDTO(AuthorityPO src) {
		if (src == null)
		return null;	
		AuthorityDTO tar = new AuthorityDTO();
		tar.setId(src.getId());
		tar.setAuthorityName(src.getAuthorityName());
		tar.setRemarks(src.getRemarks());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static AuthorityPO toPO(AuthorityDTO src) {
		if (src == null)
		return null;	
		AuthorityPO tar = new AuthorityPO();
		tar.setId(src.getId());
		tar.setAuthorityName(src.getAuthorityName());
		tar.setRemarks(src.getRemarks());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<AuthorityDTO> toDTO(List<AuthorityPO> srcs) {
		if (srcs == null)
			return null;
		List<AuthorityDTO> list = new ArrayList<AuthorityDTO>();
		for (AuthorityPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<AuthorityPO> toPO(List<AuthorityDTO> srcs) {
		if (srcs == null)
			return null;
		List<AuthorityPO> list = new ArrayList<AuthorityPO>();
		for (AuthorityDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	