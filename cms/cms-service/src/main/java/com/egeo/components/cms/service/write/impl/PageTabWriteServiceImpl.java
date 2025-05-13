package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.exception.BusinessException;
import com.egeo.components.cms.service.write.PageTabWriteService;
import com.egeo.components.cms.manage.write.PageTabWriteManage;
import com.egeo.components.cms.converter.PageTabConverter;
import com.egeo.components.cms.dto.PageTabDTO;
import com.egeo.components.cms.po.PageTabPO;

@Service("pageTabWriteService")
public class PageTabWriteServiceImpl  implements PageTabWriteService {
	@Autowired
	private PageTabWriteManage pageTabWriteManage;
	
	@Override
	public Long insertPageTabWithTx(PageTabDTO dto) {
		PageTabPO po = PageTabConverter.toPO(dto);
		Long rt = pageTabWriteManage.insertPageTabWithTx(po);		
		if (rt == 0) 
			throw new BusinessException("新增数据失败");
		return rt;
	}

	@Override
	public int updatePageTabWithTx(PageTabDTO dto) {
		PageTabPO po = PageTabConverter.toPO(dto);
		int rt = pageTabWriteManage.updatePageTabWithTx(po);		
		if (rt == 0) 
			throw new BusinessException("更新数据失败");
		return rt;
	}

	@Override
	public int deletePageTabWithTx(PageTabDTO dto) {
		PageTabPO po = PageTabConverter.toPO(dto);
		
		return pageTabWriteManage.deletePageTabWithTx(po);
	}
}
	