package com.egeo.components.promotion.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.BannerReadService;
import com.egeo.components.promotion.service.write.BannerWriteService;
import com.egeo.components.promotion.dto.BannerDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class BannerFacade {
	
	@Autowired
	private BannerReadService bannerReadService;
	
	@Autowired
	private BannerWriteService bannerWriteService;
	
	
	public BannerDTO findBannerById(BannerDTO dto){
		
		return bannerReadService.findBannerById(dto);
	}

	public PageResult<BannerDTO> findBannerOfPage(BannerDTO dto,Pagination page){
		
		return bannerReadService.findBannerOfPage(dto, page);
		
	}

	public List<BannerDTO> findBannerAll(BannerDTO dto){
		
		return bannerReadService.findBannerAll(dto);
		
	}

	public Long insertBannerWithTx(BannerDTO dto){
		
		return bannerWriteService.insertBannerWithTx(dto);
	}

	public int updateBannerWithTx(BannerDTO dto){
		
		return bannerWriteService.updateBannerWithTx(dto);
	}

	public int deleteBannerWithTx(BannerDTO dto){
		
		return bannerWriteService.deleteBannerWithTx(dto);
		
	}
	/**
	 * 查询Banner最大排序值
	 * @return
	 */
	public Integer maxSortValue() {
		return bannerReadService.maxSortValue();
	}

}
	