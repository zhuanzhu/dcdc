package com.egeo.components.product.manage.write;

import java.util.List;

import com.egeo.components.product.po.BlessingCoinBannerCompanyPO;


public interface BlessingCoinBannerCompanyWriteManage {

	Long insertBlessingCoinBannerCompanyWithTx(BlessingCoinBannerCompanyPO po);

	int updateBlessingCoinBannerCompanyWithTx(BlessingCoinBannerCompanyPO po);

	int deleteBlessingCoinBannerCompanyWithTx(BlessingCoinBannerCompanyPO po);
	/**
	 * 根据公司id集合和轮播图id删除轮播图公司集合删除非公司集合中的关系
	 * @param id
	 * @param companyList
	 * @return
	 */
	int delByBlessingCoinBannerIdCompanyIds(Long id, List<Long> companyList);
}
	