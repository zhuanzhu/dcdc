package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.BlessingCoinBannerCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface BlessingCoinBannerCompanyManage {

	public BlessingCoinBannerCompanyDTO findBlessingCoinBannerCompanyById(BlessingCoinBannerCompanyDTO dto);	

	public PageResult<BlessingCoinBannerCompanyDTO> findBlessingCoinBannerCompanyOfPage(BlessingCoinBannerCompanyDTO dto,Pagination page);

	public List<BlessingCoinBannerCompanyDTO> findBlessingCoinBannerCompanyAll(BlessingCoinBannerCompanyDTO dto);

	Long insertBlessingCoinBannerCompanyWithTx(BlessingCoinBannerCompanyDTO dto);

	int updateBlessingCoinBannerCompanyWithTx(BlessingCoinBannerCompanyDTO dto);

	int deleteBlessingCoinBannerCompanyWithTx(BlessingCoinBannerCompanyDTO dto);
}
	