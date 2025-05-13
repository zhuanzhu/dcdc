package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.ElementWriteManage;
import com.egeo.components.cms.dao.write.ElementWriteDAO;
import com.egeo.components.cms.po.ElementPO;
import com.egeo.exception.BusinessException;

@Service
public class ElementWriteManageImpl implements ElementWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ElementWriteDAO elementWriteDAO;

	@Override
	public Long insertElementWithTx(ElementPO po) {
		
		int i ;
		try {
				i = elementWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateElementWithTx(ElementPO po) {
		int i;
		i = elementWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteElementWithTx(ElementPO po) {
		int i;
		i = elementWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	