package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.ImportTemplateDictWriteManage;
import com.egeo.components.cms.dao.write.ImportTemplateDictWriteDAO;
import com.egeo.components.cms.po.ImportTemplateDictPO;
import com.egeo.exception.BusinessException;

@Service
public class ImportTemplateDictWriteManageImpl implements ImportTemplateDictWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ImportTemplateDictWriteDAO importTemplateDictWriteDAO;

	@Override
	public Long insertImportTemplateDictWithTx(ImportTemplateDictPO po) {
		
		int i ;
		try {
				i = importTemplateDictWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateImportTemplateDictWithTx(ImportTemplateDictPO po) {
		int i;
		i = importTemplateDictWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteImportTemplateDictWithTx(ImportTemplateDictPO po) {
		int i;
		i = importTemplateDictWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	