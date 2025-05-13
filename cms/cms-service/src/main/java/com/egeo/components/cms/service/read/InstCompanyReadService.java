package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.InstCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface InstCompanyReadService {

	public InstCompanyDTO findInstCompanyById(InstCompanyDTO dto);

	public PageResult<InstCompanyDTO> findInstCompanyOfPage(InstCompanyDTO dto,Pagination page);

	public List<InstCompanyDTO> findInstCompanyAll(InstCompanyDTO dto);

	/**
	 * 根据实例id和公司id查询实例与公司关系列表
	 * @param id
	 * @param companyId
	 * @return
	 */
	public List<InstCompanyDTO> queryInstCompanyListByInstIdAndCompanyId(Long id, Long companyId, Long companyIdByType);
}
	