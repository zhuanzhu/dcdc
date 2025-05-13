package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.IconCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface IconCompanyReadService {

	public IconCompanyDTO findIconCompanyById(IconCompanyDTO dto);

	public PageResult<IconCompanyDTO> findIconCompanyOfPage(IconCompanyDTO dto,Pagination page);

	public List<IconCompanyDTO> findIconCompanyAll(IconCompanyDTO dto);

	/**
	 * 根据iconId和公司id查询icon与公司关系列表
	 * @param id
	 * @param companyId
	 * @return
	 */
	public List<IconCompanyDTO> queryIconCompanysByIconIdAndCompanyId(Long id, Long companyId);
}
	