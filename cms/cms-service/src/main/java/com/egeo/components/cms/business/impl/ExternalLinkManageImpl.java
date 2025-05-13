package com.egeo.components.cms.business.impl;
	
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.cms.business.ExternalLinkManage;
import com.egeo.components.cms.dto.ExternalLinkDTO;
import com.egeo.components.cms.facade.ExternalLinkFacade;
import com.egeo.web.JsonResult;

@Service("externalLink")
public class ExternalLinkManageImpl implements ExternalLinkManage{

	
	@Resource(name="externalLinkFacade")
	private ExternalLinkFacade externalLinkFacade;

	/**
	 * 查询所有外部链接
	 * @param vo
	 * @param req
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findExternalLinkAll() {
		List<Map<String, Object>> list = new ArrayList<>();
		List<ExternalLinkDTO> externalLinkList = externalLinkFacade.findExternalLinkAll(new ExternalLinkDTO());
		for (ExternalLinkDTO externalLinkDTO : externalLinkList) {
			Map<String, Object> map = new HashMap<>();
			map.put("externalLinkId", externalLinkDTO.getId());
			map.put("externalLinkName", externalLinkDTO.getName());
			list.add(map);
		}
		return list;
	}

	@Override
	public JsonResult<Map<String, Object>> linkDetail(Long id) {
		if(id==null) {
			return JsonResult.fail("请选择外链");
		}
		ExternalLinkDTO dto=externalLinkFacade.findExternalLinkById(id);
		Map<String,Object> data=new HashMap<>();
		data.put("type", dto.getLinkType());
		data.put("extUrl", dto.getExternalLinkUrl());
		return JsonResult.success(data);
	}

}
	