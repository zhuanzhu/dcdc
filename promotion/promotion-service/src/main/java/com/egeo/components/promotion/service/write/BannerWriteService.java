package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.BannerDTO;


public interface BannerWriteService {

	public Long insertBannerWithTx(BannerDTO dto);

	public int updateBannerWithTx(BannerDTO dto);

	public int deleteBannerWithTx(BannerDTO dto);
}
	