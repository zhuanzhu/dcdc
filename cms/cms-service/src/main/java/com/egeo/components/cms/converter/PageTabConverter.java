package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.egeo.components.cms.dto.PageTabDTO;
import com.egeo.components.cms.po.PageTabPO;
import com.egeo.components.cms.vo.PageTabVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author wyy
 * @date 2018-08-01 12:25:54
 */
public class PageTabConverter {

	public static PageTabVO toVO(PageTabDTO src) {
		if (src == null)
		return null;	
		PageTabVO tar = new PageTabVO();
		tar.setId(src.getId());
		tar.setPageTabName(src.getPageTabName());	
		tar.setPageTabRemark(src.getPageTabRemark());	
		tar.setPageTabSort(src.getPageTabSort());	
		tar.setIsHomePage(src.getIsHomePage());	
		tar.setPageTabStatus(src.getPageTabStatus());	
		tar.setWebTemplateId(src.getWebTemplateId());	
		tar.setCompanyId(src.getCompanyId());
		tar.setUpdateUser(src.getUpdateUser());
		tar.setUpdateTime(src.getUpdateTime() != null ? src.getUpdateTime().getTime() : null);	
		tar.setCreateTime(src.getCreateTime());	
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}
	public static List<PageTabVO> toVO(List<PageTabDTO> srcs) {
		if (srcs == null)
			return null;
		List<PageTabVO> list = new ArrayList<PageTabVO>();
		for (PageTabDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}

	public static PageTabDTO toDTO(PageTabVO src) {
		if (src == null)
		return null;	
		PageTabDTO tar = new PageTabDTO();
		tar.setId(src.getId());
		tar.setPageTabName(src.getPageTabName());	
		tar.setPageTabRemark(src.getPageTabRemark());	
		tar.setPageTabSort(src.getPageTabSort());	
		tar.setIsHomePage(src.getIsHomePage());	
		tar.setPageTabStatus(src.getPageTabStatus());	
		tar.setWebTemplateId(src.getWebTemplateId());	
		tar.setCompanyId(src.getCompanyId());	
		tar.setUpdateUser(src.getUpdateUser());
		tar.setUpdateTime(src.getUpdateTime() != null ? new Date(src.getUpdateTime()) : null);	
		tar.setCreateTime(src.getCreateTime());	
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}
	public static PageTabDTO toDTO(PageTabPO src) {
		if (src == null)
		return null;	
		PageTabDTO tar = new PageTabDTO();
		tar.setId(src.getId());
		tar.setPageTabName(src.getPageTabName());
		tar.setPageTabRemark(src.getPageTabRemark());
		tar.setPageTabSort(src.getPageTabSort());
		tar.setIsHomePage(src.getIsHomePage());
		tar.setPageTabStatus(src.getPageTabStatus());
		tar.setWebTemplateId(src.getWebTemplateId());
		tar.setCompanyId(src.getCompanyId());
		tar.setUpdateUser(src.getUpdateUser());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static PageTabPO toPO(PageTabDTO src) {
		if (src == null)
		return null;	
		PageTabPO tar = new PageTabPO();
		tar.setId(src.getId());
		tar.setPageTabName(src.getPageTabName());
		tar.setPageTabRemark(src.getPageTabRemark());
		tar.setPageTabSort(src.getPageTabSort());
		tar.setIsHomePage(src.getIsHomePage());
		tar.setPageTabStatus(src.getPageTabStatus());
		tar.setWebTemplateId(src.getWebTemplateId());
		tar.setCompanyId(src.getCompanyId());
		tar.setUpdateUser(src.getUpdateUser());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<PageTabDTO> toDTO(List<PageTabPO> srcs) {
		if (srcs == null)
			return null;
		List<PageTabDTO> list = new ArrayList<PageTabDTO>();
		for (PageTabPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PageTabPO> toPO(List<PageTabDTO> srcs) {
		if (srcs == null)
			return null;
		List<PageTabPO> list = new ArrayList<PageTabPO>();
		for (PageTabDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	