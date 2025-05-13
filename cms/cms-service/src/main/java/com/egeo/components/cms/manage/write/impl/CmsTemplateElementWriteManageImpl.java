package com.egeo.components.cms.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.CmsTemplateElementWriteManage;
import com.egeo.components.cms.dao.write.CmsTemplateElementWriteDAO;
import com.egeo.components.cms.po.CmsTemplateElementPO;
import com.egeo.exception.BusinessException;

@Service
public class CmsTemplateElementWriteManageImpl implements CmsTemplateElementWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsTemplateElementWriteDAO cmsTemplateElementWriteDAO;

	@Override
	public Long insertCmsTemplateElementWithTx(CmsTemplateElementPO po) {
		
		int i ;
		try {
				i = cmsTemplateElementWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCmsTemplateElementWithTx(CmsTemplateElementPO po) {
		int i;
		i = cmsTemplateElementWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCmsTemplateElementWithTx(CmsTemplateElementPO po) {
		int i;
		i = cmsTemplateElementWriteDAO.deleteByPara(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public void insertBatchWithTx(List<CmsTemplateElementPO> pos) {
		int i;
		i = cmsTemplateElementWriteDAO.insertBatch(pos);
		if (i < pos.size())
			throw new BusinessException("未能全部插入成功!");
	}	
}
	