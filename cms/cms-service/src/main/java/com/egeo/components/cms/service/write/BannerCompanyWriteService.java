package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.BannerCompanyDTO;


public interface BannerCompanyWriteService {

	public Long insertBannerCompanyWithTx(BannerCompanyDTO dto);

	public int updateBannerCompanyWithTx(BannerCompanyDTO dto);

	public int deleteBannerCompanyWithTx(BannerCompanyDTO dto);
}
	