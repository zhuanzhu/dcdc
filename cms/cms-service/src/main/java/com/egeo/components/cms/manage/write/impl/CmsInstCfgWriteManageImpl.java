package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.CmsInstCfgWriteManage;
import com.egeo.components.cms.dao.write.CmsInstCfgWriteDAO;
import com.egeo.components.cms.po.CmsInstCfgPO;
import com.egeo.exception.BusinessException;

@Service
public class CmsInstCfgWriteManageImpl implements CmsInstCfgWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsInstCfgWriteDAO cmsInstCfgWriteDAO;

	@Override
	public Long insertCmsInstCfgWithTx(CmsInstCfgPO po) {
		
		int i ;
		try {
				i = cmsInstCfgWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCmsInstCfgWithTx(CmsInstCfgPO po) {
		int i;
		i = cmsInstCfgWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCmsInstCfgWithTx(CmsInstCfgPO po) {
		int i;
		i = cmsInstCfgWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	