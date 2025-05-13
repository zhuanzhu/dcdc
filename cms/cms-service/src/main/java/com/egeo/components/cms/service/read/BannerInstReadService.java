package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.BannerInstDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface BannerInstReadService {

	public BannerInstDTO findBannerInstById(BannerInstDTO dto);

	public PageResult<BannerInstDTO> findBannerInstOfPage(BannerInstDTO dto,Pagination page);

	public List<BannerInstDTO> findBannerInstAll(BannerInstDTO dto);
}
	