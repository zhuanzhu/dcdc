package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.MembershipWriteManage;
import com.egeo.components.product.dao.write.MembershipWriteDAO;
import com.egeo.components.product.po.MembershipPO;
import com.egeo.exception.BusinessException;

@Service
public class MembershipWriteManageImpl implements MembershipWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MembershipWriteDAO membershipWriteDAO;

	@Override
	public Long insertMembershipWithTx(MembershipPO po) {
		
		int i ;
		try {
				i = membershipWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateMembershipWithTx(MembershipPO po) {
		int i;
		i = membershipWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteMembershipWithTx(MembershipPO po) {
		int i;
		i = membershipWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	