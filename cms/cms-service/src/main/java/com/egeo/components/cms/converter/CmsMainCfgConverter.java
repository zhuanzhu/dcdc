package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.CmsMainCfgDTO;
import com.egeo.components.cms.po.CmsMainCfgPO;
import com.egeo.components.cms.vo.CmsMainCfgVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author tan
 * @date 2018-12-13 17:01:38
 */
public class CmsMainCfgConverter {
	
	public static CmsMainCfgDTO toDTO(CmsMainCfgPO src) {
		if (src == null)
		return null;	
		CmsMainCfgDTO tar = new CmsMainCfgDTO();
		tar.setId(src.getId());
		tar.setCompanyId(src.getCompanyId());
		tar.setTempType(src.getTempType());
		tar.setBannerHeader(src.getBannerHeader());
		tar.setBannerMiddle(src.getBannerMiddle());
		tar.setSelfProduct(src.getSelfProduct());
		tar.setRecommendation(src.getRecommendation());
		tar.setUpdateUser(src.getUpdateUser());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static CmsMainCfgDTO toDTO(CmsMainCfgVO src) {
		if (src == null)
		return null;	
		CmsMainCfgDTO tar = new CmsMainCfgDTO();
		tar.setId(src.getId());
		tar.setCompanyId(src.getCompanyId());
		tar.setTempType(src.getTempType());
		tar.setBannerHeader(src.getBannerHeader());
		tar.setBannerMiddle(src.getBannerMiddle());
		tar.setSelfProduct(src.getSelfProduct());
		tar.setRecommendation(src.getRecommendation());
		tar.setUpdateUser(src.getUpdateUser());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}
	public static CmsMainCfgPO toPO(CmsMainCfgDTO src) {
		if (src == null)
		return null;	
		CmsMainCfgPO tar = new CmsMainCfgPO();
		tar.setId(src.getId());
		tar.setCompanyId(src.getCompanyId());
		tar.setTempType(src.getTempType());
		tar.setBannerHeader(src.getBannerHeader());
		tar.setBannerMiddle(src.getBannerMiddle());
		tar.setSelfProduct(src.getSelfProduct());
		tar.setRecommendation(src.getRecommendation());
		tar.setUpdateUser(src.getUpdateUser());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}
	public static CmsMainCfgVO toVO(CmsMainCfgDTO src) {
		if (src == null)
		return null;	
		CmsMainCfgVO tar = new CmsMainCfgVO();
		tar.setId(src.getId());
		tar.setCompanyId(src.getCompanyId());
		tar.setTempType(src.getTempType());
		tar.setBannerHeader(src.getBannerHeader());
		tar.setBannerMiddle(src.getBannerMiddle());
		tar.setSelfProduct(src.getSelfProduct());
		tar.setRecommendation(src.getRecommendation());
		tar.setUpdateUser(src.getUpdateUser());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<CmsMainCfgDTO> toDTO(List<CmsMainCfgPO> srcs) {
		if (srcs == null)
			return null;
		List<CmsMainCfgDTO> list = new ArrayList<CmsMainCfgDTO>();
		for (CmsMainCfgPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CmsMainCfgPO> toPO(List<CmsMainCfgDTO> srcs) {
		if (srcs == null)
			return null;
		List<CmsMainCfgPO> list = new ArrayList<CmsMainCfgPO>();
		for (CmsMainCfgDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	