package com.egeo.components.finance.service.read;


import java.util.List;

import com.egeo.components.finance.dto.ReasonCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ReasonCompanyReadService {

	public ReasonCompanyDTO findReasonCompanyById(ReasonCompanyDTO dto);

	public PageResult<ReasonCompanyDTO> findReasonCompanyOfPage(ReasonCompanyDTO dto,Pagination page);

	public List<ReasonCompanyDTO> findReasonCompanyAll(ReasonCompanyDTO dto);
}
	