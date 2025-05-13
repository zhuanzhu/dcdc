package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.BannerInstPO;


public interface BannerInstWriteManage {

	Long insertBannerInstWithTx(BannerInstPO po);

	int updateBannerInstWithTx(BannerInstPO po);

	int deleteBannerInstWithTx(BannerInstPO po);
}
	