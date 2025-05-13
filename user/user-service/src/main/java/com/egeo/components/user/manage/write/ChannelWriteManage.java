package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.ChannelPO;


public interface ChannelWriteManage {

	Long insertChannelWithTx(ChannelPO po);

	int updateChannelWithTx(ChannelPO po);

	int deleteChannelWithTx(ChannelPO po);
}
	