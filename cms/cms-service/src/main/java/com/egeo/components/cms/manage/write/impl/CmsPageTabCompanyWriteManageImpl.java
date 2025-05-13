package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.CmsPageTabCompanyWriteManage;
import com.egeo.components.cms.dao.write.CmsPageTabCompanyWriteDAO;
import com.egeo.components.cms.po.CmsPageTabCompanyPO;
import com.egeo.exception.BusinessException;

@Service
public class CmsPageTabCompanyWriteManageImpl implements CmsPageTabCompanyWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsPageTabCompanyWriteDAO cmsPageTabCompanyWriteDAO;

	@Override
	public Long insertCmsPageTabCompanyWithTx(CmsPageTabCompanyPO po) {
		
		int i ;
		try {
				i = cmsPageTabCompanyWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCmsPageTabCompanyWithTx(CmsPageTabCompanyPO po) {
		int i;
		i = cmsPageTabCompanyWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCmsPageTabCompanyWithTx(CmsPageTabCompanyPO po) {
		int i;
		i = cmsPageTabCompanyWriteDAO.deleteByPara(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
}
	