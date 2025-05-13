package com.egeo.components.cms.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.CmsTemplatePO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface CmsTemplateReadDAO extends BaseReadDAO<CmsTemplatePO>{

	List<CmsTemplatePO> findOfPageByType(@Param("po") CmsTemplatePO po,@Param("page") Pagination page,@Param("searchType") Integer searchType);

	int countOfPageByType(@Param("po") CmsTemplatePO po, @Param("searchType") Integer searchType);

	List<CmsTemplatePO> findAllByType(@Param("po") CmsTemplatePO po, @Param("searchType") Integer searchType,@Param("page") Pagination page);
}
	