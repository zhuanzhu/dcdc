package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.SerachSortWriteService;
import com.egeo.components.product.manage.write.SerachSortWriteManage;
import com.egeo.components.product.converter.SerachSortConverter;
import com.egeo.components.product.dto.SerachSortDTO;
import com.egeo.components.product.po.SerachSortPO;

@Service("serachSortWriteService")
public class SerachSortWriteServiceImpl  implements SerachSortWriteService {
	@Autowired
	private SerachSortWriteManage serachSortWriteManage;

	@Override
	public Long insertSerachSortWithTx(SerachSortDTO dto) {
		SerachSortPO po = SerachSortConverter.toPO(dto);
		Long rt = serachSortWriteManage.insertSerachSortWithTx(po);		
		return rt;
	}

	@Override
	public int updateSerachSortWithTx(SerachSortDTO dto) {
		SerachSortPO po = SerachSortConverter.toPO(dto);
		int rt = serachSortWriteManage.updateSerachSortWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSerachSortWithTx(SerachSortDTO dto) {
		SerachSortPO po = SerachSortConverter.toPO(dto);
		int rt = serachSortWriteManage.deleteSerachSortWithTx(po);		
		return rt;
	}
}
	