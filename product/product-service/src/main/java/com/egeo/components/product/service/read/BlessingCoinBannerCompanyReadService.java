package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.BlessingCoinBannerCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface BlessingCoinBannerCompanyReadService {

	public BlessingCoinBannerCompanyDTO findBlessingCoinBannerCompanyById(BlessingCoinBannerCompanyDTO dto);

	public PageResult<BlessingCoinBannerCompanyDTO> findBlessingCoinBannerCompanyOfPage(BlessingCoinBannerCompanyDTO dto,Pagination page);

	public List<BlessingCoinBannerCompanyDTO> findBlessingCoinBannerCompanyAll(BlessingCoinBannerCompanyDTO dto);
}
	