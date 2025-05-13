package com.egeo.components.product.manage.write.impl;

import com.egeo.components.product.dao.write.MembershipWriteDAO;
import com.egeo.components.product.po.MembershipPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.MembershipAuthorityWriteManage;
import com.egeo.components.product.dao.write.MembershipAuthorityWriteDAO;
import com.egeo.components.product.po.MembershipAuthorityPO;
import com.egeo.exception.BusinessException;

import java.util.List;

@Service
public class MembershipAuthorityWriteManageImpl implements MembershipAuthorityWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MembershipAuthorityWriteDAO membershipAuthorityWriteDAO;
	@Autowired
	private MembershipWriteDAO membershipWriteDAO;

	@Override
	public Long insertMembershipAuthorityWithTx(MembershipAuthorityPO po) {
		
		int i ;
		try {
				i = membershipAuthorityWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateMembershipAuthorityWithTx(MembershipAuthorityPO po) {
		int i;
		i = membershipAuthorityWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteMembershipAuthorityWithTx(MembershipAuthorityPO po) {
		int i;
		i = membershipAuthorityWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public void updateMembershipAuthorityWithTx(List<MembershipAuthorityPO> membershipAuthorityPOS, MembershipPO membershipPO) {
		int i;
		int j;
		i=membershipWriteDAO.update(membershipPO);
		if(i==0){
			throw new BusinessException("未能成功更新数据!");
		}
		for(MembershipAuthorityPO po:membershipAuthorityPOS){
			j=membershipAuthorityWriteDAO.update(po);
			if(j==0){
				throw new BusinessException("未能成功更新数据!");
			}
		}



	}

	@Override
	public void insertMembershipAuthorityWithTx(List<MembershipAuthorityPO> membershipAuthorityPOS, MembershipPO membershipPO) {
		int i;
		int j;
		i=membershipWriteDAO.insert(membershipPO);
		if(i==0){
			throw new BusinessException("未能成功插入会籍数据!");
		}
		for(MembershipAuthorityPO po:membershipAuthorityPOS){
			po.setMembershipId(membershipPO.getId());
			j=membershipAuthorityWriteDAO.insert(po);
			if(j==0){
				throw new BusinessException("未能成功插入会籍权限数据!");
			}
		}

	}
}
	