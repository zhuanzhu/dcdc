package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.ElementDictPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ElementDictReadManage {

	public ElementDictPO findElementDictById(ElementDictPO po);

	public PageResult<ElementDictPO> findElementDictOfPage(ElementDictPO po,Pagination page);

	public List<ElementDictPO> findElementDictAll(ElementDictPO po);

	/**
	 * 根据类型查询组件字典
	 * @param configType
	 * @return
	 */
	public ElementDictPO queryElementDictByConfigType(Integer configType);

	/**
	 * 查询非此类组件字典列表
	 * @param type
	 * @return
	 */
	public List<ElementDictPO> queryElementDictByNotType(Integer type);
}
	