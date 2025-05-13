package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.LeadingEndCategoryWriteService;
import com.egeo.components.product.manage.write.LeadingEndCategoryWriteManage;
import com.egeo.components.product.converter.LeadingEndCategoryConverter;
import com.egeo.components.product.dto.LeadingEndCategoryDTO;
import com.egeo.components.product.po.LeadingEndCategoryPO;

@Service("leadingEndCategoryWriteService")
public class LeadingEndCategoryWriteServiceImpl  implements LeadingEndCategoryWriteService {
	@Autowired
	private LeadingEndCategoryWriteManage leadingEndCategoryWriteManage;

	@Override
	public Long insertLeadingEndCategoryWithTx(LeadingEndCategoryDTO dto) {
		LeadingEndCategoryPO po = LeadingEndCategoryConverter.toPO(dto);
		Long rt = leadingEndCategoryWriteManage.insertLeadingEndCategoryWithTx(po);		
		return rt;
	}

	@Override
	public int updateLeadingEndCategoryWithTx(LeadingEndCategoryDTO dto) {
		LeadingEndCategoryPO po = LeadingEndCategoryConverter.toPO(dto);
		int rt = leadingEndCategoryWriteManage.updateLeadingEndCategoryWithTx(po);		
		return rt;
	}

	@Override
	public int deleteLeadingEndCategoryWithTx(LeadingEndCategoryDTO dto) {
		LeadingEndCategoryPO po = LeadingEndCategoryConverter.toPO(dto);
		int rt = leadingEndCategoryWriteManage.deleteLeadingEndCategoryWithTx(po);		
		return rt;
	}
}
	