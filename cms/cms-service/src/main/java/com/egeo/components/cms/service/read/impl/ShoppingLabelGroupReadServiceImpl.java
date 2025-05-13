package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.ShoppingLabelGroupReadService;
import com.egeo.components.cms.manage.read.ShoppingLabelGroupReadManage;
import com.egeo.components.cms.converter.ShoppingLabelGroupConverter;
import com.egeo.components.cms.dto.ShoppingLabelGroupDTO;
import com.egeo.components.cms.po.ShoppingLabelGroupPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("shoppingLabelGroupReadService")
public class ShoppingLabelGroupReadServiceImpl  implements ShoppingLabelGroupReadService {
	@Autowired
	private ShoppingLabelGroupReadManage shoppingLabelGroupReadManage;

	@Override
	public ShoppingLabelGroupDTO findShoppingLabelGroupById(ShoppingLabelGroupDTO dto) {
		ShoppingLabelGroupPO po = ShoppingLabelGroupConverter.toPO(dto);
		ShoppingLabelGroupPO list = shoppingLabelGroupReadManage.findShoppingLabelGroupById(po);		
		return ShoppingLabelGroupConverter.toDTO(list);
	}

	@Override
	public PageResult<ShoppingLabelGroupDTO> findShoppingLabelGroupOfPage(ShoppingLabelGroupDTO dto, Pagination page) {
		ShoppingLabelGroupPO po = ShoppingLabelGroupConverter.toPO(dto);
        PageResult<ShoppingLabelGroupPO> pageResult = shoppingLabelGroupReadManage.findShoppingLabelGroupOfPage(po, page);
        
        List<ShoppingLabelGroupDTO> list = ShoppingLabelGroupConverter.toDTO(pageResult.getList());
        PageResult<ShoppingLabelGroupDTO> result = new PageResult<ShoppingLabelGroupDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ShoppingLabelGroupDTO> findShoppingLabelGroupAll(ShoppingLabelGroupDTO dto) {
		ShoppingLabelGroupPO po = ShoppingLabelGroupConverter.toPO(dto);
		List<ShoppingLabelGroupPO> list = shoppingLabelGroupReadManage.findShoppingLabelGroupAll(po);		
		return ShoppingLabelGroupConverter.toDTO(list);
	}

	@Override
	public ShoppingLabelGroupDTO queryShoppingLabelGroupByInstId(Long instId) {
		
		return ShoppingLabelGroupConverter.toDTO(shoppingLabelGroupReadManage.queryShoppingLabelGroupByInstId(instId));
	}
}
	