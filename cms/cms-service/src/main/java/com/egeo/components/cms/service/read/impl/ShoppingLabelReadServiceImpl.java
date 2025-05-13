package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.ShoppingLabelReadService;
import com.egeo.components.cms.manage.read.ShoppingLabelReadManage;
import com.egeo.components.cms.converter.ShoppingLabelConverter;
import com.egeo.components.cms.dto.ShoppingLabelDTO;
import com.egeo.components.cms.dto.ShoppingLabelGroupDTO;
import com.egeo.components.cms.po.ShoppingLabelPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("shoppingLabelReadService")
public class ShoppingLabelReadServiceImpl  implements ShoppingLabelReadService {
	@Autowired
	private ShoppingLabelReadManage shoppingLabelReadManage;

	@Override
	public ShoppingLabelDTO findShoppingLabelById(ShoppingLabelDTO dto) {
		ShoppingLabelPO po = ShoppingLabelConverter.toPO(dto);
		ShoppingLabelPO list = shoppingLabelReadManage.findShoppingLabelById(po);		
		return ShoppingLabelConverter.toDTO(list);
	}

	@Override
	public PageResult<ShoppingLabelDTO> findShoppingLabelOfPage(ShoppingLabelDTO dto, Pagination page) {
		ShoppingLabelPO po = ShoppingLabelConverter.toPO(dto);
        PageResult<ShoppingLabelPO> pageResult = shoppingLabelReadManage.findShoppingLabelOfPage(po, page);
        
        List<ShoppingLabelDTO> list = ShoppingLabelConverter.toDTO(pageResult.getList());
        PageResult<ShoppingLabelDTO> result = new PageResult<ShoppingLabelDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ShoppingLabelDTO> findShoppingLabelAll(ShoppingLabelDTO dto) {
		ShoppingLabelPO po = ShoppingLabelConverter.toPO(dto);
		List<ShoppingLabelPO> list = shoppingLabelReadManage.findShoppingLabelAll(po);		
		return ShoppingLabelConverter.toDTO(list);
	}

	@Override
	public List<ShoppingLabelDTO> queryShoppingLabelListByGroupId(Long groupId) {
		return ShoppingLabelConverter.toDTO(shoppingLabelReadManage.queryShoppingLabelListByGroupId(groupId));
	}
}
	