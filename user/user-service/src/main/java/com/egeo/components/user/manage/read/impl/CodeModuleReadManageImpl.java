package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.dao.read.CodeModuleReadDAO;
import com.egeo.components.user.manage.read.CodeModuleReadManage;
import com.egeo.components.user.po.CodeModulePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CodeModuleReadManageImpl implements CodeModuleReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CodeModuleReadDAO codeModuleReadDAO;
	
	public CodeModulePO findCodeModuleById(CodeModulePO po) {
		CodeModulePO codeModulepo = new CodeModulePO();
		codeModulepo.setId(po.getId());
		return codeModuleReadDAO.findById(codeModulepo);
	}

	public PageResult<CodeModulePO> findCodeModuleOfPage(CodeModulePO po, Pagination page) {
		
		PageResult<CodeModulePO> pageResult = new PageResult<CodeModulePO>();
		List<CodeModulePO> list = null;

		int cnt = codeModuleReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = codeModuleReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CodeModulePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CodeModulePO> findCodeModuleAll(CodeModulePO po) {

		return codeModuleReadDAO.findAll(po,null);
	}
	
}
	