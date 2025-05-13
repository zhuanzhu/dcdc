package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.ElementDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ElementReadService {

	public ElementDTO findElementById(Long id);

	public PageResult<ElementDTO> findElementOfPage(ElementDTO dto,Pagination page);

	public List<ElementDTO> findElementAll(ElementDTO dto);

	/**
	 * 根据模板id查询组件列表(按照sort排序)
	 * @param templateId
	 * @return
	 */
	public List<ElementDTO> queryElementListByTmlpId(Long templateId);

    List<ElementDTO> queryElementListByTmlpIdByPage(Long id, Pagination page);
}
	