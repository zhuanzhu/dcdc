package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.BannerCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface BannerCompanyReadService {

	public BannerCompanyDTO findBannerCompanyById(BannerCompanyDTO dto);

	public PageResult<BannerCompanyDTO> findBannerCompanyOfPage(BannerCompanyDTO dto,Pagination page);

	public List<BannerCompanyDTO> findBannerCompanyAll(BannerCompanyDTO dto);
}
	