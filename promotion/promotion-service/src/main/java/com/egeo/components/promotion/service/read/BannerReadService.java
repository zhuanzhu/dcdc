package com.egeo.components.promotion.service.read;


import java.util.List;
	
import com.egeo.components.promotion.dto.BannerDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface BannerReadService {

	public BannerDTO findBannerById(BannerDTO dto);

	public PageResult<BannerDTO> findBannerOfPage(BannerDTO dto,Pagination page);

	public List<BannerDTO> findBannerAll(BannerDTO dto);
	/**
	 * 查询Banner最大排序值
	 * @return
	 */
	public Integer maxSortValue();
}
	