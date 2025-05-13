package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.CmsLocalParamWriteManage;
import com.egeo.components.cms.dao.write.CmsLocalParamWriteDAO;
import com.egeo.components.cms.po.CmsLocalParamPO;
import com.egeo.exception.BusinessException;

@Service
public class CmsLocalParamWriteManageImpl implements CmsLocalParamWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsLocalParamWriteDAO cmsLocalParamWriteDAO;

	@Override
	public Long insertCmsLocalParamWithTx(CmsLocalParamPO po) {
		
		int i ;
		try {
				i = cmsLocalParamWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCmsLocalParamWithTx(CmsLocalParamPO po) {
		int i;
		i = cmsLocalParamWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCmsLocalParamWithTx(CmsLocalParamPO po) {
		int i;
		i = cmsLocalParamWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	