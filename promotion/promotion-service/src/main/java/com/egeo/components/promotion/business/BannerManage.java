package com.egeo.components.promotion.business;

import java.util.List;
	
import com.egeo.components.promotion.dto.BannerDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface BannerManage {

	public BannerDTO findBannerById(BannerDTO dto);	

	public PageResult<BannerDTO> findBannerOfPage(BannerDTO dto,Pagination page);

	public List<BannerDTO> findBannerAll(BannerDTO dto);

	Long insertBannerWithTx(BannerDTO dto);

	int updateBannerWithTx(BannerDTO dto);

	int deleteBannerWithTx(BannerDTO dto);
}
	