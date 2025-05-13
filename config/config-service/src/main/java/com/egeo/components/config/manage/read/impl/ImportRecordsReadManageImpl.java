package com.egeo.components.config.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.dao.read.ImportRecordsReadDAO;
import com.egeo.components.config.manage.read.ImportRecordsReadManage;
import com.egeo.components.config.po.ImportRecordsPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ImportRecordsReadManageImpl implements ImportRecordsReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ImportRecordsReadDAO importRecordsReadDAO;
	
	public ImportRecordsPO findImportRecordsById(ImportRecordsPO po) {
		ImportRecordsPO importRecordspo = new ImportRecordsPO();
		importRecordspo.setId(po.getId());
		return importRecordsReadDAO.findById(importRecordspo);
	}

	public PageResult<ImportRecordsPO> findImportRecordsOfPage(ImportRecordsPO po, Pagination page) {
		
		PageResult<ImportRecordsPO> pageResult = new PageResult<ImportRecordsPO>();
		List<ImportRecordsPO> list = null;

		int cnt = importRecordsReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = importRecordsReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ImportRecordsPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ImportRecordsPO> findImportRecordsAll(ImportRecordsPO po) {

		return importRecordsReadDAO.findAll(po,null);
	}

	@Override
	public ImportRecordsPO queryRecordTempBySn(String sn) {
		return importRecordsReadDAO.queryRecordTempBySn(sn);
	}

}
	