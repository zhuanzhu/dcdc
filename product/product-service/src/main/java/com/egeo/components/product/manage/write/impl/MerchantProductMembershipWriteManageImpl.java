package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.MerchantProductMembershipWriteManage;
import com.egeo.components.product.dao.write.MerchantProductMembershipWriteDAO;
import com.egeo.components.product.po.MerchantProductMembershipPO;
import com.egeo.exception.BusinessException;

@Service
public class MerchantProductMembershipWriteManageImpl implements MerchantProductMembershipWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProductMembershipWriteDAO merchantProductMembershipWriteDAO;

	@Override
	public Long insertMerchantProductMembershipWithTx(MerchantProductMembershipPO po) {
		
		int i ;
		try {
				i = merchantProductMembershipWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateMerchantProductMembershipWithTx(MerchantProductMembershipPO po) {
		int i;
		i = merchantProductMembershipWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteMerchantProductMembershipWithTx(MerchantProductMembershipPO po) {
		int i;
		i = merchantProductMembershipWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	