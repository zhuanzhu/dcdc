package com.egeo.components.order.service.read.impl;

import com.egeo.components.order.converter.ChannelAddressConverter;
import com.egeo.components.order.dto.ChannelAddressDTO;
import com.egeo.components.order.manage.read.ChannelAddressReadManage;
import com.egeo.components.order.po.ChannelAddressPO;
import com.egeo.components.order.service.read.ChannelAddressReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("channelAddressReadService")
public class ChannelAddressReadServiceImpl implements ChannelAddressReadService {
	@Autowired
	private ChannelAddressReadManage addressReadManage;

	@Override
	public ChannelAddressDTO findChannelAddressById(ChannelAddressDTO dto) {
		ChannelAddressPO po = ChannelAddressConverter.toPO(dto);
		ChannelAddressPO list = addressReadManage.findChannelAddressById(po);
		return ChannelAddressConverter.toDTO(list);
	}

	@Override
	public PageResult<ChannelAddressDTO> findChannelAddressOfPage(ChannelAddressDTO dto, Pagination page) {
		ChannelAddressPO po = ChannelAddressConverter.toPO(dto);
        PageResult<ChannelAddressPO> pageResult = addressReadManage.findChannelAddressOfPage(po, page);

        List<ChannelAddressDTO> list = ChannelAddressConverter.toDTO(pageResult.getList());
        PageResult<ChannelAddressDTO> result = new PageResult<>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ChannelAddressDTO> findChannelAddressAll(ChannelAddressDTO dto) {
		ChannelAddressPO po = ChannelAddressConverter.toPO(dto);
		List<ChannelAddressPO> list = addressReadManage.findChannelAddressAll(po);
		return ChannelAddressConverter.toDTO(list);
	}

	@Override
	public ChannelAddressDTO findByReceiverAddressIdChannel(Long receiverAddressId,String channelCode){
		ChannelAddressPO po = addressReadManage.findByReceiverAddressIdChannel(receiverAddressId,channelCode);
		return ChannelAddressConverter.toDTO(po);
	}
}
