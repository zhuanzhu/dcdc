package com.egeo.components.order.service.read;


import com.egeo.components.order.dto.ChannelAddressDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

public interface ChannelAddressReadService {

	public ChannelAddressDTO findChannelAddressById(ChannelAddressDTO dto);

	public PageResult<ChannelAddressDTO> findChannelAddressOfPage(ChannelAddressDTO dto,Pagination page);

	public List<ChannelAddressDTO> findChannelAddressAll(ChannelAddressDTO dto);

	public ChannelAddressDTO findByReceiverAddressIdChannel(Long receiverAddressId,String channelCode);
}
