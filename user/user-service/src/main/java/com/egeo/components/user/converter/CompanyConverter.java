package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.po.CompanyPO;
import com.egeo.components.user.vo.CompanyIdAndNameVO;
import com.egeo.components.user.vo.CompanyVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2017-12-05 12:05:16
 */
public class CompanyConverter {
	
	public static CompanyDTO toDTO(CompanyPO src) {
		if (src == null)
		return null;	
		CompanyDTO tar = new CompanyDTO();
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setUserName(src.getUserName());
		tar.setStatus(src.getStatus());
		tar.setCompanyName(src.getCompanyName());
		tar.setCompanyCelebrateTime(src.getCompanyCelebrateTime());
		tar.setCompanyMailSuffix(src.getCompanyMailSuffix());
		tar.setRegistrationStatus(src.getRegistrationStatus());
		tar.setCompanyContent(src.getCompanyContent());
		tar.setPlatformId(src.getPlatformId());
		tar.setBackgrondImg(src.getBackgrondImg());
		tar.setCompanyLogo(src.getCompanyLogo());
		tar.setCreateTime(src.getCreateTime());
		tar.setIsTest(src.getIsTest());
		tar.setCompanyType(src.getCompanyType());
		tar.setPlatformLinkableId(src.getPlatformLinkableId());
		tar.setCompanyLinkableId(src.getCompanyLinkableId());
		tar.setIsManagementCompany(src.getIsManagementCompany());
		tar.setOpenWorkWechat(src.getOpenWorkWechat());
		tar.setCorpid(src.getCorpid());
		tar.setSecret(src.getSecret());
		return tar;
	}

	public static CompanyPO toPO(CompanyDTO src) {
		if (src == null)
		return null;	
		CompanyPO tar = new CompanyPO();
		tar.setId(src.getId());
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setUserId(src.getUserId());
		tar.setUserName(src.getUserName());
		tar.setStatus(src.getStatus());
		tar.setCompanyName(src.getCompanyName());
		tar.setCompanyCelebrateTime(src.getCompanyCelebrateTime());
		tar.setCompanyMailSuffix(src.getCompanyMailSuffix());
		tar.setRegistrationStatus(src.getRegistrationStatus());
		tar.setCompanyContent(src.getCompanyContent());
		tar.setPlatformId(src.getPlatformId());
		tar.setBackgrondImg(src.getBackgrondImg());
		tar.setCompanyLogo(src.getCompanyLogo());
		tar.setCreateTime(src.getCreateTime());
		tar.setIsTest(src.getIsTest());
		tar.setCompanyType(src.getCompanyType());
		tar.setPlatformLinkableId(src.getPlatformLinkableId());
		tar.setCompanyLinkableId(src.getCompanyLinkableId());
		tar.setIsManagementCompany(src.getIsManagementCompany());
		tar.setOpenWorkWechat(src.getOpenWorkWechat());
		tar.setCorpid(src.getCorpid());
		tar.setSecret(src.getSecret());
		return tar;
	}

	public static List<CompanyDTO> toDTO(List<CompanyPO> srcs) {
		if (srcs == null)
			return null;
		List<CompanyDTO> list = new ArrayList<CompanyDTO>();
		for (CompanyPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CompanyPO> toPO(List<CompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<CompanyPO> list = new ArrayList<CompanyPO>();
		for (CompanyDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}

	public static CompanyDTO toDTO(CompanyVO src) {
		if (src == null)
		return null;	
		CompanyDTO tar = new CompanyDTO();
		tar.setId(src.getId());
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setUserId(src.getUserId());
		tar.setUserName(src.getUserName());
		tar.setStatus(src.getStatus());
		tar.setCompanyContent(src.getCompanyContent());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCompanyName(src.getCompanyName());	
		tar.setCompanyCelebrateTime(src.getCompanyCelebrateTime());	
		tar.setCompanyMailSuffix(src.getCompanyMailSuffix());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setBackgrondImg(src.getBackgrondImg());
		tar.setCompanyLogo(src.getCompanyLogo());
		tar.setIsTest(src.getIsTest());
		tar.setCompanyType(src.getCompanyType());
		tar.setCompanyLinkableId(src.getCompanyLinkableId());
		tar.setPlatformLinkableId(src.getPlatformLinkableId());
		return tar;
	}

	public static CompanyVO toVO(CompanyDTO src) {
		if (src == null)
		return null;	
		CompanyVO tar = new CompanyVO();
		tar.setId(src.getId());
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setUserId(src.getUserId());
		tar.setUserName(src.getUserName());
		tar.setStatus(src.getStatus());
		tar.setCompanyContent(src.getCompanyContent());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCompanyName(src.getCompanyName());	
		tar.setCompanyCelebrateTime(src.getCompanyCelebrateTime());	
		tar.setCompanyMailSuffix(src.getCompanyMailSuffix());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setBackgrondImg(src.getBackgrondImg());
		tar.setCompanyLogo(src.getCompanyLogo());
		tar.setIsTest(src.getIsTest());
		tar.setCompanyType(src.getCompanyType());
		tar.setCompanyLinkableId(src.getCompanyLinkableId());
		tar.setPlatformLinkableId(src.getPlatformLinkableId());
		return tar;
	}
	public static CompanyIdAndNameVO toIdAndNameVO(CompanyDTO src) {
		if (src == null)
			return null;	
		CompanyIdAndNameVO tar = new CompanyIdAndNameVO();
		tar.setId(src.getId());
		tar.setCompanyName(src.getCompanyName());	
		return tar;
	}


	public static List<CompanyVO> toVO(List<CompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<CompanyVO> list = new ArrayList<CompanyVO>();
		for (CompanyDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static List<CompanyIdAndNameVO> toIdAndNameVO(List<CompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<CompanyIdAndNameVO> list = new ArrayList<CompanyIdAndNameVO>();
		for (CompanyDTO src : srcs) {
			list.add(toIdAndNameVO(src));
		}
		return list;
	}
}
	