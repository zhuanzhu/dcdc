package com.egeo.components.product.service.write;

import java.util.List;

import com.egeo.components.product.dto.BlessingCoinBannerDTO;


public interface BlessingCoinBannerWriteService {

	public Long insertBlessingCoinBannerWithTx(BlessingCoinBannerDTO dto,List<Long> companyIds);

	public int updateBlessingCoinBannerWithTx(BlessingCoinBannerDTO dto,List<Long> companyIds);

	public int deleteBlessingCoinBannerWithTx(BlessingCoinBannerDTO dto);
}
	