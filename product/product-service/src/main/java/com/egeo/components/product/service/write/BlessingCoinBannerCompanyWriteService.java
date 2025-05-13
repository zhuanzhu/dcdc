package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.BlessingCoinBannerCompanyDTO;


public interface BlessingCoinBannerCompanyWriteService {

	public Long insertBlessingCoinBannerCompanyWithTx(BlessingCoinBannerCompanyDTO dto);

	public int updateBlessingCoinBannerCompanyWithTx(BlessingCoinBannerCompanyDTO dto);

	public int deleteBlessingCoinBannerCompanyWithTx(BlessingCoinBannerCompanyDTO dto);
}
	