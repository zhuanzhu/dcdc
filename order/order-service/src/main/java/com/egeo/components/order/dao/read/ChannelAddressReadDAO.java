package com.egeo.components.order.dao.read;

import com.egeo.components.order.po.ChannelAddressPO;
import com.egeo.orm.BaseReadDAO;
import org.apache.ibatis.annotations.Param;

public interface ChannelAddressReadDAO extends BaseReadDAO<ChannelAddressPO>{

    ChannelAddressPO findByReceiverAddressIdChannel(@Param("receiverAddressId")Long receiverAddressId, @Param("channelCode")String channelCode);
}
