package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.CmsElementWriteManage;
import com.egeo.components.cms.dao.write.CmsElementWriteDAO;
import com.egeo.components.cms.po.CmsElementPO;
import com.egeo.exception.BusinessException;

@Service
public class CmsElementWriteManageImpl implements CmsElementWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsElementWriteDAO cmsElementWriteDAO;

	@Override
	public Long insertCmsElementWithTx(CmsElementPO po) {
		
		int i ;
		try {
				i = cmsElementWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCmsElementWithTx(CmsElementPO po) {
		int i;
		i = cmsElementWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCmsElementWithTx(CmsElementPO po) {
		int i;
		i = cmsElementWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	