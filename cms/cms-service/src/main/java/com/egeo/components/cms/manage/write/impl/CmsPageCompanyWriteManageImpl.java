package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.CmsPageCompanyWriteManage;
import com.egeo.components.cms.dao.write.CmsPageCompanyWriteDAO;
import com.egeo.components.cms.po.CmsPageCompanyPO;
import com.egeo.exception.BusinessException;

@Service
public class CmsPageCompanyWriteManageImpl implements CmsPageCompanyWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsPageCompanyWriteDAO cmsPageCompanyWriteDAO;

	@Override
	public Long insertCmsPageCompanyWithTx(CmsPageCompanyPO po) {
		
		int i ;
		try {
				i = cmsPageCompanyWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCmsPageCompanyWithTx(CmsPageCompanyPO po) {
		int i;
		i = cmsPageCompanyWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCmsPageCompanyWithTx(CmsPageCompanyPO po) {
		int i;
		i = cmsPageCompanyWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	