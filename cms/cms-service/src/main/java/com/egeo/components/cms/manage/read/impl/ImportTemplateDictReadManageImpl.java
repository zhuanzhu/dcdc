package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.ImportTemplateDictReadManage;
import com.egeo.components.cms.dao.read.ImportTemplateDictReadDAO;
import com.egeo.components.cms.po.ImportTemplateDictPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ImportTemplateDictReadManageImpl implements ImportTemplateDictReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ImportTemplateDictReadDAO importTemplateDictReadDAO;
	
	public ImportTemplateDictPO findImportTemplateDictById(ImportTemplateDictPO po) {
		ImportTemplateDictPO importTemplateDictpo = new ImportTemplateDictPO();
		importTemplateDictpo.setId(po.getId());
		return importTemplateDictReadDAO.findById(importTemplateDictpo);
	}

	public PageResult<ImportTemplateDictPO> findImportTemplateDictOfPage(ImportTemplateDictPO po, Pagination page) {
		
		PageResult<ImportTemplateDictPO> pageResult = new PageResult<ImportTemplateDictPO>();
		List<ImportTemplateDictPO> list = null;

		int cnt = importTemplateDictReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = importTemplateDictReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ImportTemplateDictPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ImportTemplateDictPO> findImportTemplateDictAll(ImportTemplateDictPO po) {

		return importTemplateDictReadDAO.findAll(po,null);
	}
	/**
	 * 根据类型和平台id查询导入模版url
	 * @param type
	 * @return
	 */
	@Override
	public String findImportTemplateDictUrlByType(Integer type, Long platformId) {
		// TODO Auto-generated method stub
		return importTemplateDictReadDAO.findImportTemplateDictUrlByType(type, platformId);
	}
	
}
	