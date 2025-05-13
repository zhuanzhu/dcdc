package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.ElementDictWriteManage;
import com.egeo.components.cms.dao.write.ElementDictWriteDAO;
import com.egeo.components.cms.po.ElementDictPO;
import com.egeo.exception.BusinessException;

@Service
public class ElementDictWriteManageImpl implements ElementDictWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ElementDictWriteDAO elementDictWriteDAO;

	@Override
	public Long insertElementDictWithTx(ElementDictPO po) {
		
		int i ;
		try {
				i = elementDictWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateElementDictWithTx(ElementDictPO po) {
		int i;
		i = elementDictWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteElementDictWithTx(ElementDictPO po) {
		int i;
		i = elementDictWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	