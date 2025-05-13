package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.dao.read.ChannelActivityReadDAO;
import com.egeo.components.user.dao.read.ChannelReadDAO;
import com.egeo.components.user.dao.write.ChannelActivityWriteDAO;
import com.egeo.components.user.manage.write.ChannelActivityWriteManage;
import com.egeo.components.user.po.ChannelActivityPO;
import com.egeo.components.user.po.ChannelPO;
import com.egeo.exception.BusinessException;

@Service
public class ChannelActivityWriteManageImpl implements ChannelActivityWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ChannelActivityWriteDAO channelActivityWriteDAO;
	
	@Autowired
	private ChannelReadDAO channelReadDAO;
	
	@Autowired
	private ChannelActivityReadDAO channelActivityReadDAO;

	@Override
	public Long insertChannelActivityWithTx(ChannelActivityPO po) {
		// 根据渠道id查询渠道信息
		ChannelPO channelPO = new ChannelPO();
		channelPO.setId(po.getChannelId());
		ChannelPO channelPO2 = channelReadDAO.findById(channelPO);
		
		ChannelActivityPO channelActivityPO = new ChannelActivityPO();
		channelActivityPO.setChannelId(po.getChannelId());
		channelActivityPO.setPlatformId(po.getPlatformId());
		int count = channelActivityReadDAO.countOfPage(channelActivityPO);
		po.setShortCode(channelPO2.getShortCode() + "-" + (count + 1));
		int i ;
		try {
				i = channelActivityWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateChannelActivityWithTx(ChannelActivityPO po) {
		int i;
		i = channelActivityWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteChannelActivityWithTx(ChannelActivityPO po) {
		int i;
		i = channelActivityWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public Long insertWithTx(ChannelActivityPO po) {
		int i ;
		try {
				i = channelActivityWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}	
}
	