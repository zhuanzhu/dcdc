package com.egeo.components.cms.business.impl;
	

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.cms.business.LocalParamManage;
import com.egeo.components.cms.dto.LocalParamDTO;
import com.egeo.components.cms.facade.LocalParamFacade;
import com.egeo.components.cms.vo.LocalParamVO;
import com.egeo.web.JsonResult;

@Service("localParam")
public class LocalParamManageImpl implements LocalParamManage{

	
	@Resource(name="localParamFacade")
	private LocalParamFacade localParamFacade;

	@Override
	public JsonResult<Map<String, Object>> localParamList() {
		List<LocalParamDTO> list=localParamFacade.findLocalParamAll(new LocalParamDTO());
		List<LocalParamVO> voList=new ArrayList<>();
		for(LocalParamDTO dto:list) {
			LocalParamVO vo=new LocalParamVO();
			vo.setId(dto.getId());
			vo.setName(dto.getName());
			voList.add(vo);
		}
		return JsonResult.success("paramList",voList);
	}



}
	