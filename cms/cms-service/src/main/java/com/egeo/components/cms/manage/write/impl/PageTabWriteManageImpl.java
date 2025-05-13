package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.PageTabWriteManage;
import com.egeo.components.cms.dao.write.InstWriteDAO;
import com.egeo.components.cms.dao.write.PageTabWriteDAO;
import com.egeo.components.cms.po.InstPO;
import com.egeo.components.cms.po.PageTabPO;
import com.egeo.exception.BusinessException;

@Service
public class PageTabWriteManageImpl implements PageTabWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PageTabWriteDAO pageTabWriteDAO;
	
	@Autowired
	private InstWriteDAO instWriteDAO;

	@Override
	public Long insertPageTabWithTx(PageTabPO po) {
		
		int i ;
		try {
				i = pageTabWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updatePageTabWithTx(PageTabPO po) {
		int i;
		i = pageTabWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deletePageTabWithTx(PageTabPO po) {
		int i;
		i = pageTabWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		
		// 删除关系
		InstPO instPO = new InstPO();
		instPO.setPageTabId(po.getId());
		i = instWriteDAO.resetInstPageTabInfo(instPO);
		
		return i;
	}	
}
	