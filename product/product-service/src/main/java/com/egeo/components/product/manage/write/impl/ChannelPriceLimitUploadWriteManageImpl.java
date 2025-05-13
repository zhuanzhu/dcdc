package com.egeo.components.product.manage.write.impl;

import com.egeo.components.product.dao.write.ChannelPriceLimitUploadWriteDAO;
import com.egeo.components.product.manage.write.ChannelPriceLimitUploadWriteManage;
import com.egeo.components.product.po.ChannelPriceLimitUploadPO;
import com.egeo.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class ChannelPriceLimitUploadWriteManageImpl implements ChannelPriceLimitUploadWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ChannelPriceLimitUploadWriteDAO channelPriceLimitUploadWriteDAO;

	@Override
	public Long insertChannelPriceLimitUploadWithTx(ChannelPriceLimitUploadPO po) {

		int i ;
		try {
			i = channelPriceLimitUploadWriteDAO.insert(po);
			if (i == 0)
				throw new BusinessException("未能成功插入数据!");
		} catch (DuplicateKeyException e) {
			logger.error("", e);
			throw new BusinessException("路径必须唯一!");
		}
		return po.getId();
	}

	@Override
	public int updateChannelPriceLimitUploadWithTx(ChannelPriceLimitUploadPO po) {
		int i;
		i = channelPriceLimitUploadWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteChannelPriceLimitUploadWithTx(ChannelPriceLimitUploadPO po) {
		int i;
		i = channelPriceLimitUploadWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
}
