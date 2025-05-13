package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.ChannelActivityPO;


public interface ChannelActivityWriteManage {

	Long insertChannelActivityWithTx(ChannelActivityPO po);

	int updateChannelActivityWithTx(ChannelActivityPO po);

	int deleteChannelActivityWithTx(ChannelActivityPO po);

	Long insertWithTx(ChannelActivityPO channelActivityPO2);
}
	