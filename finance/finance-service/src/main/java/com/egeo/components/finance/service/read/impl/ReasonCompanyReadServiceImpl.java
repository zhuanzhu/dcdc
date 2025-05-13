package com.egeo.components.finance.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.converter.ReasonCompanyConverter;
import com.egeo.components.finance.dto.ReasonCompanyDTO;
import com.egeo.components.finance.manage.read.ReasonCompanyReadManage;
import com.egeo.components.finance.po.ReasonCompanyPO;
import com.egeo.components.finance.service.read.ReasonCompanyReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("reasonCompanyReadService")
public class ReasonCompanyReadServiceImpl implements ReasonCompanyReadService {
	@Autowired
	private ReasonCompanyReadManage reasonCompanyReadManage;

	@Override
	public ReasonCompanyDTO findReasonCompanyById(ReasonCompanyDTO dto) {
		ReasonCompanyPO po = ReasonCompanyConverter.toPO(dto);
		ReasonCompanyPO list = reasonCompanyReadManage.findReasonCompanyById(po);		
		return ReasonCompanyConverter.toDTO(list);
	}

	@Override
	public PageResult<ReasonCompanyDTO> findReasonCompanyOfPage(ReasonCompanyDTO dto, Pagination page) {
		ReasonCompanyPO po = ReasonCompanyConverter.toPO(dto);
        PageResult<ReasonCompanyPO> pageResult = reasonCompanyReadManage.findReasonCompanyOfPage(po, page);
        
        List<ReasonCompanyDTO> list = ReasonCompanyConverter.toDTO(pageResult.getList());
        PageResult<ReasonCompanyDTO> result = new PageResult<ReasonCompanyDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ReasonCompanyDTO> findReasonCompanyAll(ReasonCompanyDTO dto) {
		ReasonCompanyPO po = ReasonCompanyConverter.toPO(dto);
		List<ReasonCompanyPO> list = reasonCompanyReadManage.findReasonCompanyAll(po);		
		return ReasonCompanyConverter.toDTO(list);
	}
}
	