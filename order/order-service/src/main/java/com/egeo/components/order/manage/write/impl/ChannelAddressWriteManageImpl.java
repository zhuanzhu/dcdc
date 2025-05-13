package com.egeo.components.order.manage.write.impl;

import com.egeo.components.order.dao.write.ChannelAddressWriteDAO;
import com.egeo.components.order.manage.write.ChannelAddressWriteManage;
import com.egeo.components.order.po.ChannelAddressPO;
import com.egeo.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class ChannelAddressWriteManageImpl implements ChannelAddressWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ChannelAddressWriteDAO addressWriteDAO;

	@Override
	public Long insertChannelAddressWithTx(ChannelAddressPO po) {

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
	public int updateChannelAddressWithTx(ChannelAddressPO po) {
		int i;
		i = addressWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteChannelAddressWithTx(ChannelAddressPO po) {
		int i;
		i = addressWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
}
