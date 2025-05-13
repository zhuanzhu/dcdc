package com.egeo.components.config.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.config.dao.write.ImportRecordsWriteDAO;
import com.egeo.components.config.manage.write.ImportRecordsWriteManage;
import com.egeo.components.config.po.ImportRecordsPO;
import com.egeo.exception.BusinessException;

@Service
public class ImportRecordsWriteManageImpl implements ImportRecordsWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ImportRecordsWriteDAO importRecordsWriteDAO;

	@Override
	public Long insertImportRecordsWithTx(ImportRecordsPO po) {
		
		int i ;
		try {
				i = importRecordsWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateImportRecordsWithTx(ImportRecordsPO po) {
		int i;
		i = importRecordsWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteImportRecordsWithTx(ImportRecordsPO po) {
		return importRecordsWriteDAO.delete(po);
	}

	@Override
	public int deleteBySnWithTx(String sn) {
		
		return importRecordsWriteDAO.deleteBySn(sn);
	}

}
	