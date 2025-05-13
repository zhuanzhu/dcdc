package com.egeo.components.cms.dao.read;

import java.util.List;

import com.egeo.orm.Pagination;
import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.ElementPO;
import com.egeo.orm.BaseReadDAO;

public interface ElementReadDAO extends BaseReadDAO<ElementPO>{

	/**
	 * 根据模板id查询组件列表(按照sort排序)
	 * @param templateId
	 * @return
	 */
	List<ElementPO> queryElementListByTmlpId(@Param("templateId")Long templateId);

    List<ElementPO> queryElementListByTmlpIdByPage(@Param("id")Long id,@Param("page") Pagination page);
}
	