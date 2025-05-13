package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.ElementPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ElementReadManage {

	public ElementPO findElementById(ElementPO po);

	public PageResult<ElementPO> findElementOfPage(ElementPO po,Pagination page);

	public List<ElementPO> findElementAll(ElementPO po);

	/**
	 * 根据模板id查询组件列表(按照sort排序)
	 * @param templateId
	 * @return
	 */
	public List<ElementPO> queryElementListByTmlpId(Long templateId);

    List<ElementPO> queryElementListByTmlpIdByPage(Long id, Pagination page);
}
	