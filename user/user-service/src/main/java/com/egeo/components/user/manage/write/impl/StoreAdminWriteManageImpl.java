package com.egeo.components.user.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.StoreAdminWriteManage;
import com.egeo.components.user.dao.read.StoreAdminReadDAO;
import com.egeo.components.user.dao.write.StoreAdminWriteDAO;
import com.egeo.components.user.po.StoreAdminPO;
import com.egeo.exception.BusinessException;

@Service
public class StoreAdminWriteManageImpl implements StoreAdminWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StoreAdminWriteDAO storeAdminWriteDAO;
	
	@Autowired
	private StoreAdminReadDAO storeAdminReadDAO;

	@Override
	public Long insertStoreAdminWithTx(StoreAdminPO po) {
		
		int i ;
		StoreAdminPO sPo = new StoreAdminPO();
		sPo.setUserId(po.getUserId());
		sPo.setStoreId(po.getStoreId());
		List<StoreAdminPO> findAll = storeAdminReadDAO.findAll(sPo,null);
		if(!findAll.isEmpty()) {
			throw new BusinessException("该管理员已经关联店铺!");
		}
		
		try {
				i = storeAdminWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStoreAdminWithTx(StoreAdminPO po) {
		int i;
		i = storeAdminWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStoreAdminWithTx(StoreAdminPO po) {
		int i;
		i = storeAdminWriteDAO.deleteByPara(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	