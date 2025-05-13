package com.egeo.components.cms.manage.write;

import java.util.List;

import com.egeo.components.cms.po.BannerPO;
import com.egeo.components.cms.po.LinkableButtonPO;
import com.egeo.components.cms.po.LinkableButtonPagePO;


public interface BannerWriteManage {

	Long insertBannerWithTx(BannerPO po);

	int updateBannerWithTx(BannerPO po);

	int deleteBannerWithTx(BannerPO po);

	/**
	 * 新增/编辑轮播图
	 * @param bannerId
	 * @param banner
	 * @param lb
	 * @param companyIdList 
	 * @return
	 */
	boolean saveBannerWithTx(Long bannerId, BannerPO banner, LinkableButtonPO lb, List<Long> companyIdList,List<LinkableButtonPagePO> lbpPOList,String extParam);
}
	