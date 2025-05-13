package com.egeo.components.order.manage.read;

import com.egeo.components.order.po.ChannelAddressPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

public interface ChannelAddressReadManage {

	public ChannelAddressPO findChannelAddressById(ChannelAddressPO po);

	public PageResult<ChannelAddressPO> findChannelAddressOfPage(ChannelAddressPO po,Pagination page);

	public List<ChannelAddressPO> findChannelAddressAll(ChannelAddressPO po);

	public ChannelAddressPO findByReceiverAddressIdChannel(Long receiverAddressId,String channelCode);
}
