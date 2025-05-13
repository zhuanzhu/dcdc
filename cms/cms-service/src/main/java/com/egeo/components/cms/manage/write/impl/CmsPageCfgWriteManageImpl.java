package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.CmsPageCfgWriteManage;
import com.egeo.components.cms.dao.write.CmsPageCfgWriteDAO;
import com.egeo.components.cms.po.CmsPageCfgPO;
import com.egeo.exception.BusinessException;

@Service
public class CmsPageCfgWriteManageImpl implements CmsPageCfgWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsPageCfgWriteDAO cmsPageCfgWriteDAO;

	@Override
	public Long insertCmsPageCfgWithTx(CmsPageCfgPO po) {
		
		int i ;
		try {
				i = cmsPageCfgWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCmsPageCfgWithTx(CmsPageCfgPO po) {
		int i;
		i = cmsPageCfgWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCmsPageCfgWithTx(CmsPageCfgPO po) {
		int i;
		i = cmsPageCfgWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	