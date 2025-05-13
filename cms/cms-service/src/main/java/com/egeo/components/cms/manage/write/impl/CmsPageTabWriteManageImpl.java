package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.CmsPageTabWriteManage;
import com.egeo.components.cms.dao.write.CmsPageTabWriteDAO;
import com.egeo.components.cms.po.CmsPageTabPO;
import com.egeo.exception.BusinessException;

@Service
public class CmsPageTabWriteManageImpl implements CmsPageTabWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsPageTabWriteDAO cmsPageTabWriteDAO;

	@Override
	public Long insertCmsPageTabWithTx(CmsPageTabPO po) {
		
		int i ;
		try {
				i = cmsPageTabWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCmsPageTabWithTx(CmsPageTabPO po) {
		int i;
		i = cmsPageTabWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCmsPageTabWithTx(CmsPageTabPO po) {
		int i;
		i = cmsPageTabWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	