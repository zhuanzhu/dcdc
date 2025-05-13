package com.egeo.components.cms.business.impl;
	
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.cms.business.ElementManage;
import com.egeo.components.cms.dto.ElementDictDTO;
import com.egeo.components.cms.facade.ElementFacade;
import com.egeo.components.cms.facade.UserFacade;
import com.egeo.components.cms.vo.ElementDictVO;
import com.egeo.components.user.dto.VersionsDTO;
import com.egeo.web.JsonResult;

@Service("element")
public class ElementManageImpl implements ElementManage{

	
	@Resource(name="elementFacade")
	private ElementFacade elementFacade;
	
	@Resource(name="userFacade")
	private UserFacade userFacade;

	@Override
	public JsonResult<Map<String, Object>> elementDict(Integer type, Long platformId) {
		if(type==null) {
			return JsonResult.fail("请指定类型");
		}

		List<ElementDictDTO> dtoList=elementFacade.queryElementDictByNotType(type);
		List<ElementDictVO> voList=new ArrayList<>();
		for(ElementDictDTO dto:dtoList) {
			ElementDictVO vo=new ElementDictVO();
			Integer clientVersionA=dto.getClientVersionA();
			String cva=null;
			if(clientVersionA!=null) {
				VersionsDTO v=userFacade.queryVersionByCodeAndType(clientVersionA, 0,platformId);
				if(v==null) {
					cva="全部";
				}else {
					cva=v.getVersionsMark();
				}
			}
			vo.setClientVersionA(cva);
			Integer clientVersionI=dto.getClientVersionI();
			String cvi=null;
			if(clientVersionI!=null) {
				VersionsDTO v=userFacade.queryVersionByCodeAndType(clientVersionI, 1,platformId);
				if(v==null) {
					cvi="全部";
				}else {
					cvi=v.getVersionsMark();
				}
			}
			vo.setClientVersionI(cvi);
			vo.setDictId(dto.getId());
			vo.setDictName(dto.getName());
			vo.setImgUrl(dto.getImgUrl());
			vo.setConfigType(dto.getConfigType());
			voList.add(vo);
		}
		return JsonResult.success("dictList",voList);
	}

	@Override
	public JsonResult<Map<String, Object>> deleteElement(Long elementId) {
		if(elementId==null) {
			return JsonResult.fail("请选择组件");
		}
		elementFacade.deleteElementById(elementId);
		return JsonResult.success();
	}

}
	