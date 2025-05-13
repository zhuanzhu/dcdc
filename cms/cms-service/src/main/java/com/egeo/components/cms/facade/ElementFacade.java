package com.egeo.components.cms.facade;

import java.util.List;

import com.egeo.orm.Pagination;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.cms.service.read.ElementDictReadService;
import com.egeo.components.cms.service.read.ElementReadService;
import com.egeo.components.cms.service.write.ElementWriteService;
import com.egeo.components.cms.dto.ElementDTO;
import com.egeo.components.cms.dto.ElementDictDTO;


@Component
public class ElementFacade {
	
	@Resource
	private ElementReadService elementReadService;
	
	@Resource
	private ElementWriteService elementWriteService;
	
	@Resource
	private ElementDictReadService elementDictReadService;

	/**
	 * 查询非此类组件字典列表
	 * @return
	 */
	public List<ElementDictDTO> queryElementDictByNotType(Integer type) {
		return elementDictReadService.queryElementDictByNotType(type);
	}

	/**
	 * 根据id删除组件
	 * @param elementId
	 * @return
	 */
	public int deleteElementById(Long elementId) {
		return elementWriteService.deleteElementById(elementId);
	}

	/**
	 * 根据模板id查询组件列表(按照sort排序)
	 * @param templateId
	 * @return
	 */
	public List<ElementDTO> queryElementListByTmlpId(Long templateId) {
		return elementReadService.queryElementListByTmlpId(templateId);
	}

	/**
	 * 根据类型查询组件字典
	 * @param configType
	 * @return
	 */
	public ElementDictDTO queryElementDictByConfigType(Integer configType) {
		return elementDictReadService.queryElementDictByConfigType(configType);
	}

	/**
	 * 根据id查询组件
	 * @param elementId
	 * @return
	 */
	public ElementDTO queryElementById(Long elementId) {
		return elementReadService.findElementById(elementId);
	}

	/**
	 * 根据id查询组件字典
	 * @param elementDictId
	 * @return
	 */
	public ElementDictDTO queryElementDictById(Long elementDictId) {
		return elementDictReadService.findElementDictById(elementDictId);
	}

	public List<ElementDTO> queryElementListByTmlpIdByPage(Long id, Pagination page) {
		return elementReadService.queryElementListByTmlpIdByPage(id,page);
	}
}
	