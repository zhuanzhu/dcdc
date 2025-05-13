package com.egeo.components.product.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.product.dto.BlessingCoinBannerDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface BlessingCoinBannerManage {

	public Map<String, Object> findBlessingCoinBannerById(BlessingCoinBannerDTO dto);	

	public PageResult<Map<String, Object>> findBlessingCoinBannerOfPage(BlessingCoinBannerDTO dto,Pagination page);

	public List<BlessingCoinBannerDTO> findBlessingCoinBannerAll(BlessingCoinBannerDTO dto);

	Long insertBlessingCoinBannerWithTx(BlessingCoinBannerDTO dto,List<Long> companyIds);

	int updateBlessingCoinBannerWithTx(BlessingCoinBannerDTO dto,List<Long> companyIds);

	int deleteBlessingCoinBannerWithTx(BlessingCoinBannerDTO dto);
	/**
	 * 客户端查询所有有效轮播图信息
	 * @param vo
	 * @param req
	 * @return
	 */
	public List<Map<String, Object>> findBlessingCoinBannerAllApp(BlessingCoinBannerDTO dto);
}
	