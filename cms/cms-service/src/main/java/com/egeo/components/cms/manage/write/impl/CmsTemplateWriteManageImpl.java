package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.CmsTemplateWriteManage;
import com.egeo.components.cms.dao.write.CmsTemplateWriteDAO;
import com.egeo.components.cms.po.CmsTemplatePO;
import com.egeo.exception.BusinessException;

@Service
public class CmsTemplateWriteManageImpl implements CmsTemplateWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsTemplateWriteDAO cmsTemplateWriteDAO;

	@Override
	public Long insertCmsTemplateWithTx(CmsTemplatePO po) {
		
		int i ;
		try {
				i = cmsTemplateWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCmsTemplateWithTx(CmsTemplatePO po) {
		int i;
		i = cmsTemplateWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCmsTemplateWithTx(CmsTemplatePO po) {
		int i;
		i = cmsTemplateWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	