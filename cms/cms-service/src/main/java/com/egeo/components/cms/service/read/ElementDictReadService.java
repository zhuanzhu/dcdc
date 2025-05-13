package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.ElementDictDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ElementDictReadService {

	public ElementDictDTO findElementDictById(Long elementDictId);

	public PageResult<ElementDictDTO> findElementDictOfPage(ElementDictDTO dto,Pagination page);

	public List<ElementDictDTO> findElementDictAll(ElementDictDTO dto);

	/**
	 * 根据类型查询组件字典
	 * @param configType
	 * @return
	 */
	public ElementDictDTO queryElementDictByConfigType(Integer configType);

	/**
	 * 查询非此类组件字典列表
	 * @param type
	 * @return
	 */
	public List<ElementDictDTO> queryElementDictByNotType(Integer type);
}
	