package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.AuthorityWriteManage;
import com.egeo.components.product.dao.write.AuthorityWriteDAO;
import com.egeo.components.product.po.AuthorityPO;
import com.egeo.exception.BusinessException;

@Service
public class AuthorityWriteManageImpl implements AuthorityWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AuthorityWriteDAO authorityWriteDAO;

	@Override
	public Long insertAuthorityWithTx(AuthorityPO po) {
		
		int i ;
		try {
				i = authorityWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateAuthorityWithTx(AuthorityPO po) {
		int i;
		i = authorityWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteAuthorityWithTx(AuthorityPO po) {
		int i;
		i = authorityWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	