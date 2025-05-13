package com.egeo.components.order.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.AddressWriteManage;
import com.egeo.components.order.dao.write.AddressWriteDAO;
import com.egeo.components.order.po.AddressPO;
import com.egeo.exception.BusinessException;

@Service
public class AddressWriteManageImpl implements AddressWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AddressWriteDAO addressWriteDAO;

	@Override
	public Long insertAddressWithTx(AddressPO po) {
		
		int i ;
		try {
				i = addressWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateAddressWithTx(AddressPO po) {
		int i;
		i = addressWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteAddressWithTx(AddressPO po) {
		int i;
		i = addressWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	