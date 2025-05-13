package com.egeo.components.order.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.AddressReadService;
import com.egeo.components.order.manage.read.AddressReadManage;
import com.egeo.components.order.converter.AddressConverter;
import com.egeo.components.order.dto.AddressDTO;
import com.egeo.components.order.po.AddressPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("addressReadService")
public class AddressReadServiceImpl  implements AddressReadService {
	@Autowired
	private AddressReadManage addressReadManage;

	@Override
	public AddressDTO findAddressById(AddressDTO dto) {
		AddressPO po = AddressConverter.toPO(dto);
		AddressPO list = addressReadManage.findAddressById(po);		
		return AddressConverter.toDTO(list);
	}

	@Override
	public PageResult<AddressDTO> findAddressOfPage(AddressDTO dto, Pagination page) {
		AddressPO po = AddressConverter.toPO(dto);
        PageResult<AddressPO> pageResult = addressReadManage.findAddressOfPage(po, page);
        
        List<AddressDTO> list = AddressConverter.toDTO(pageResult.getList());
        PageResult<AddressDTO> result = new PageResult<AddressDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<AddressDTO> findAddressAll(AddressDTO dto) {
		AddressPO po = AddressConverter.toPO(dto);
		List<AddressPO> list = addressReadManage.findAddressAll(po);		
		return AddressConverter.toDTO(list);
	}
}
	