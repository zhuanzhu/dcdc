package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.BlessingCoinBannerDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface BlessingCoinBannerReadService {

	public BlessingCoinBannerDTO findBlessingCoinBannerById(BlessingCoinBannerDTO dto);

	public PageResult<BlessingCoinBannerDTO> findBlessingCoinBannerOfPage(BlessingCoinBannerDTO dto,Pagination page);

	public List<BlessingCoinBannerDTO> findBlessingCoinBannerAll(BlessingCoinBannerDTO dto);
	/**
	 * 客户端查询所有有效轮播图信息
	 * @param vo
	 * @param req
	 * @return
	 */
	public List<BlessingCoinBannerDTO> findBlessingCoinBannerAllApp(BlessingCoinBannerDTO dto);
}
	