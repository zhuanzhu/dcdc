package com.egeo.components.cms.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.CmsTemplateCfgWriteManage;
import com.egeo.components.cms.dao.write.CmsTemplateCfgWriteDAO;
import com.egeo.components.cms.po.CmsTemplateCfgPO;
import com.egeo.exception.BusinessException;

@Service
public class CmsTemplateCfgWriteManageImpl implements CmsTemplateCfgWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsTemplateCfgWriteDAO cmsTemplateCfgWriteDAO;

	@Override
	public Long insertCmsTemplateCfgWithTx(CmsTemplateCfgPO po) {
		
		int i ;
		try {
				i = cmsTemplateCfgWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCmsTemplateCfgWithTx(CmsTemplateCfgPO po) {
		int i;
		i = cmsTemplateCfgWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCmsTemplateCfgWithTx(CmsTemplateCfgPO po) {
		int i;
		i = cmsTemplateCfgWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int insertBatchWithTx(List<CmsTemplateCfgPO> pos) {
		int i = cmsTemplateCfgWriteDAO.insertBatch(pos);
		return i;
	}	
}
	