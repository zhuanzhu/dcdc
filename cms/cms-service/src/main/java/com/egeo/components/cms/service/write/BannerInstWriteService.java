package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.BannerInstDTO;


public interface BannerInstWriteService {

	public Long insertBannerInstWithTx(BannerInstDTO dto);

	public int updateBannerInstWithTx(BannerInstDTO dto);

	public int deleteBannerInstWithTx(BannerInstDTO dto);
}
	