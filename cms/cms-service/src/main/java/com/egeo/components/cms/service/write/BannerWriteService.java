package com.egeo.components.cms.service.write;

import java.util.List;

import com.egeo.components.cms.dto.BannerDTO;
import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.components.cms.dto.LinkableButtonPageDTO;


public interface BannerWriteService {

	public Long insertBannerWithTx(BannerDTO dto);

	public int updateBannerWithTx(BannerDTO dto);

	public int deleteBannerWithTx(BannerDTO dto);

	/**
	 * 新增/编辑轮播图
	 * @param bannerId
	 * @param banner
	 * @param lb
	 * @param companyIdList 
	 * @return
	 */
	public boolean saveBanner(Long bannerId, BannerDTO banner, LinkableButtonDTO lb, List<Long> companyIdList,List<LinkableButtonPageDTO> listLbp,String extParam);
}
	