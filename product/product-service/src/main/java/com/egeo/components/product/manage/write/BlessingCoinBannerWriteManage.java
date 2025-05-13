package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.BlessingCoinBannerPO;


public interface BlessingCoinBannerWriteManage {

	Long insertBlessingCoinBannerWithTx(BlessingCoinBannerPO po);

	int updateBlessingCoinBannerWithTx(BlessingCoinBannerPO po);

	int deleteBlessingCoinBannerWithTx(BlessingCoinBannerPO po);
}
	