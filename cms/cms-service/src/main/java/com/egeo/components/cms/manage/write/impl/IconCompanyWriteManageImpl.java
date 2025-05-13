package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.IconCompanyWriteManage;
import com.egeo.components.cms.dao.write.IconCompanyWriteDAO;
import com.egeo.components.cms.po.IconCompanyPO;
import com.egeo.exception.BusinessException;

@Service
public class IconCompanyWriteManageImpl implements IconCompanyWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IconCompanyWriteDAO iconCompanyWriteDAO;

	@Override
	public Long insertIconCompanyWithTx(IconCompanyPO po) {
		
		int i ;
		try {
				i = iconCompanyWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateIconCompanyWithTx(IconCompanyPO po) {
		int i;
		i = iconCompanyWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteIconCompanyWithTx(IconCompanyPO po) {
		int i;
		i = iconCompanyWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	