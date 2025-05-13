package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.IconGroupCompanyWriteManage;
import com.egeo.components.cms.dao.write.IconGroupCompanyWriteDAO;
import com.egeo.components.cms.po.IconGroupCompanyPO;
import com.egeo.exception.BusinessException;

@Service
public class IconGroupCompanyWriteManageImpl implements IconGroupCompanyWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IconGroupCompanyWriteDAO iconGroupCompanyWriteDAO;

	@Override
	public Long insertIconGroupCompanyWithTx(IconGroupCompanyPO po) {
		
		int i ;
		try {
				i = iconGroupCompanyWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateIconGroupCompanyWithTx(IconGroupCompanyPO po) {
		int i;
		i = iconGroupCompanyWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteIconGroupCompanyWithTx(IconGroupCompanyPO po) {
		int i;
		i = iconGroupCompanyWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	