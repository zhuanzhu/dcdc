package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.ChannelAddressPO;


public interface ChannelAddressWriteManage {

	Long insertChannelAddressWithTx(ChannelAddressPO po);

	int updateChannelAddressWithTx(ChannelAddressPO po);

	int deleteChannelAddressWithTx(ChannelAddressPO po);
}
