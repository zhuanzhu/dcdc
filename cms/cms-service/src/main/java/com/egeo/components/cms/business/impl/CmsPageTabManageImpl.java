package com.egeo.components.cms.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.egeo.utils.EmptyUtil;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.business.CmsPageTabManage;
import com.egeo.components.cms.facade.CmsPageTabFacade;
import com.egeo.components.cms.dto.CmsPageTabDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cmsPageTab")
public class CmsPageTabManageImpl implements CmsPageTabManage{

	
	@Resource(name="cmsPageTabFacade")
	private CmsPageTabFacade cmsPageTabFacade;

	@Override
	public CmsPageTabDTO findCmsPageTabById(CmsPageTabDTO dto) {
		return cmsPageTabFacade.findCmsPageTabById(dto);
	}

	@Override
	public PageResult<CmsPageTabDTO> findCmsPageTabOfPage(CmsPageTabDTO dto, Pagination page) {
		return cmsPageTabFacade.findCmsPageTabOfPage(dto, page);
	}

	@Override
	public List<CmsPageTabDTO> findCmsPageTabAll(CmsPageTabDTO dto) {
		return cmsPageTabFacade.findCmsPageTabAll(dto);
	}

	@Override
	public Long insertCmsPageTabWithTx(CmsPageTabDTO dto) {
		return cmsPageTabFacade.insertCmsPageTabWithTx(dto);
	}

	@Override
	public void updateCmsPageTabWithTx(List<CmsPageTabDTO> cmsPageTabDTOS,Long platformId) {
		for(CmsPageTabDTO dto:cmsPageTabDTOS){
			dto.setPlatformId(platformId);
			cmsPageTabFacade.updateCmsPageTabWithTx(dto);
		}
	}

	@Override
	public int deleteCmsPageTabWithTx(CmsPageTabDTO dto) {
		return cmsPageTabFacade.deleteCmsPageTabWithTx(dto);
	}

	@Override
	public List<Map<String, Object>> findCmsPageTabFront(CmsPageTabDTO dto) {
		//查询所有启用状态的tab
		dto.setStatus(1);
		List<Map<String, Object>> list = new ArrayList<>();
		List<CmsPageTabDTO> cmsPageTabDTOs = cmsPageTabFacade.findCmsPageTabFront(dto);
		if(EmptyUtil.isEmpty(cmsPageTabDTOs)){
			cmsPageTabDTOs=cmsPageTabFacade.findDefaultCmsPageTabFront(dto.getPlatformId());
		}
		for (CmsPageTabDTO cmsPageTabDTO : cmsPageTabDTOs) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", cmsPageTabDTO.getId());
			map.put("pageTabName", cmsPageTabDTO.getName());
			map.put("pageTabValue", cmsPageTabDTO.getValue());
			map.put("sort", cmsPageTabDTO.getSort());
			map.put("pageTabUrl",cmsPageTabDTO.getPageTabUrl());
			map.put("iconUrl", cmsPageTabDTO.getIconUrl());
			map.put("iconOnclickUrl", cmsPageTabDTO.getIconOnclickUrl());
			map.put("showPlatformLogo", cmsPageTabDTO.getShowPlatformLogo() == null ? 1 : cmsPageTabDTO.getShowPlatformLogo());
			map.put("showClientLogo", cmsPageTabDTO.getShowClientLogo() == null ? 1 : cmsPageTabDTO.getShowClientLogo());
			map.put("type", cmsPageTabDTO.getType());
			list.add(map);
		}
		return list;
	}

}
	