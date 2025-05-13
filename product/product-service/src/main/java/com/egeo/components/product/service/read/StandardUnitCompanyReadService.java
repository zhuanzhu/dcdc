package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardUnitCompanyReadService {

	public StandardUnitCompanyDTO findStandardUnitCompanyById(StandardUnitCompanyDTO dto);

	public PageResult<StandardUnitCompanyDTO> findStandardUnitCompanyOfPage(StandardUnitCompanyDTO dto,Pagination page);

	public List<StandardUnitCompanyDTO> findStandardUnitCompanyAll(StandardUnitCompanyDTO dto);

	/**
	 * 查询su是否对公司、客户端可见
	 * @param suId
	 * @param companyId
	 * @return true可见  false不可见
	 */
	public boolean querySuCompanyAvailable(Long suId, Long companyId,Long clientId, Integer companyType);

	public List<StandardUnitCompanyDTO> findSuCompanyByCompanyIdAndTypeAndSuIds(StandardUnitCompanyDTO dto,List<Long> suIds);
}
	