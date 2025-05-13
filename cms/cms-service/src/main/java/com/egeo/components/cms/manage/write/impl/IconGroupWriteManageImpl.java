package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.IconGroupWriteManage;
import com.egeo.components.cms.dao.write.IconGroupWriteDAO;
import com.egeo.components.cms.po.IconGroupPO;
import com.egeo.exception.BusinessException;

@Service
public class IconGroupWriteManageImpl implements IconGroupWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IconGroupWriteDAO iconGroupWriteDAO;

	@Override
	public Long insertIconGroupWithTx(IconGroupPO po) {
		
		int i ;
		try {
				i = iconGroupWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateIconGroupWithTx(IconGroupPO po) {
		int i;
		i = iconGroupWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteIconGroupWithTx(IconGroupPO po) {
		int i;
		i = iconGroupWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	