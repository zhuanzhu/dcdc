package com.egeo.components.user.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.dao.read.ChannelActivityReadDAO;
import com.egeo.components.user.dao.write.ChannelActivityWriteDAO;
import com.egeo.components.user.dao.write.ChannelWriteDAO;
import com.egeo.components.user.manage.write.ChannelWriteManage;
import com.egeo.components.user.po.ChannelActivityPO;
import com.egeo.components.user.po.ChannelPO;
import com.egeo.exception.BusinessException;

@Service
public class ChannelWriteManageImpl implements ChannelWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ChannelWriteDAO channelWriteDAO;
	
	@Autowired
	private ChannelActivityWriteDAO channelActivityWriteDAO;
	
	@Autowired
	private ChannelActivityReadDAO channelActivityReadDAO;

	@Override
	public Long insertChannelWithTx(ChannelPO po) {
		
		int i ;
		try {
				i = channelWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
				
				ChannelActivityPO channelActivityPO = new ChannelActivityPO();
				channelActivityPO.setName(po.getChannelActivityName());
				channelActivityPO.setShortCode(po.getShortCode() + "-1");
				channelActivityPO.setPlatformId(po.getPlatformId());
				channelActivityPO.setChannelId(po.getId());
				channelActivityWriteDAO.insert(channelActivityPO);
			} catch (DuplicateKeyException e) {
				logger.error("添加渠道信息失败", e);
				throw new BusinessException("添加渠道信息失败");
			}
		return po.getId();
	}

	@Override
	public int updateChannelWithTx(ChannelPO po) {
		int i;
		i = channelWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		
		ChannelActivityPO channelActivityPO = new ChannelActivityPO();
		channelActivityPO.setChannelId(po.getId());
		channelActivityPO.setPlatformId(po.getPlatformId());
		List<ChannelActivityPO> list = channelActivityReadDAO.findAll(channelActivityPO,null);
		ChannelActivityPO channelActivityPO2 = list.get(0);
		channelActivityPO2.setName(po.getChannelActivityName());
		channelActivityWriteDAO.update(channelActivityPO2);
		return i;
	}

	@Override
	public int deleteChannelWithTx(ChannelPO po) {
		int i;
		i = channelWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

}
	