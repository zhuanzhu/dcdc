package com.egeo.components.config.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.config.dao.write.HeadImportRecordsWriteDAO;
import com.egeo.components.config.manage.write.HeadImportRecordsWriteManage;
import com.egeo.components.config.po.HeadImportRecordsPO;
import com.egeo.exception.BusinessException;

@Service
public class HeadImportRecordsWriteManageImpl implements HeadImportRecordsWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private HeadImportRecordsWriteDAO headImportRecordsWriteDAO;

	@Override
	public Long insertHeadImportRecordsWithTx(HeadImportRecordsPO po) {
		Long id = po.getId();
		
		int i ;
		try {
				i = headImportRecordsWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return id;
	}

	@Override
	public int updateHeadImportRecordsWithTx(HeadImportRecordsPO po) {
		int i;
		i = headImportRecordsWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteHeadImportRecordsWithTx(HeadImportRecordsPO po) {
		int i;
		i = headImportRecordsWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	