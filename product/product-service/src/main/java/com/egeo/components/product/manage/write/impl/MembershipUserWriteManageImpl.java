package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.MembershipUserWriteManage;
import com.egeo.components.product.dao.write.MembershipUserWriteDAO;
import com.egeo.components.product.po.MembershipUserPO;
import com.egeo.exception.BusinessException;

import java.util.Date;
import java.util.List;

@Service
public class MembershipUserWriteManageImpl implements MembershipUserWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MembershipUserWriteDAO membershipUserWriteDAO;

	@Override
	public Long insertMembershipUserWithTx(MembershipUserPO po) {
		
		int i ;
		try {
				i = membershipUserWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateMembershipUserWithTx(MembershipUserPO po) {
		int i;
		i = membershipUserWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteMembershipUserWithTx(MembershipUserPO po) {
		int i;
		i = membershipUserWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public Boolean updateMembershipUserWithTx(List<Long> membewrshipUserId) {

		boolean res=true;
		for(Long l:membewrshipUserId){
			MembershipUserPO po = new MembershipUserPO();
			po.setId(l);
			po.setStatusCode(0);
			po.setEndTime(new Date());
			int i=membershipUserWriteDAO.update(po);
			if(i==0){
				res = false;
				throw new BusinessException("更新出错");
			}
		}
		return res;
	}

	@Override
	public boolean updateMembershipUserWithTx(List<Long> membewrshipUserId, Date endTime) {
		boolean res=true;
		for(Long l:membewrshipUserId){
			MembershipUserPO po = new MembershipUserPO();
			po.setId(l);
			po.setStatusCode(1);
			po.setEndTime(endTime);
			int i=membershipUserWriteDAO.update(po);
			if(i==0){
				res = false;
				throw new BusinessException("更新出错");
			}
		}
		return res;
	}
}
	