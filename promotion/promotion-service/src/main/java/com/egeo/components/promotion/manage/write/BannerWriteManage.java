package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.BannerPO;


public interface BannerWriteManage {

	Long insertBannerWithTx(BannerPO po);

	int updateBannerWithTx(BannerPO po);

	int deleteBannerWithTx(BannerPO po);
}
	