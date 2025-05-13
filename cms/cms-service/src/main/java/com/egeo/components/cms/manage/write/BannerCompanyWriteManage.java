package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.BannerCompanyPO;


public interface BannerCompanyWriteManage {

	Long insertBannerCompanyWithTx(BannerCompanyPO po);

	int updateBannerCompanyWithTx(BannerCompanyPO po);

	int deleteBannerCompanyWithTx(BannerCompanyPO po);
}
	