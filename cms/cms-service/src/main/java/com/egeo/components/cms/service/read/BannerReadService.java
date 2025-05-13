package com.egeo.components.cms.service.read;


import java.util.List;
import java.util.Map;

import com.egeo.components.cms.dto.BannerDTO;
import com.egeo.components.cms.dto.BannerPickDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface BannerReadService {

	public BannerDTO findBannerById(Long id);

	public PageResult<BannerDTO> findBannerOfPage(BannerDTO dto,Pagination page);

	public List<BannerDTO> findBannerAll(BannerDTO dto);

	public PageResult<Map<String, Object>> findPickBannerOfPage(BannerPickDTO dto, Pagination page);

	/**
	 * 根据实例id和公司id查询轮播图列表
	 * @param instId
	 * @param companyId
	 * @return
	 */
	public List<BannerDTO> queryBannerListByInstIdAndCompanyId(Long instId, Long companyId,Long companyAllId);

	/**
	 * 查询轮播图分页列表
	 * @param name
	 * @param linkType
	 * @param companyIdList
	 * @param page
	 * @return
	 */
	public PageResult<BannerDTO> queryBannerPage(String name, Integer linkType, List<Long> companyIdList,List<Long> bannerIdList,
			Integer belongPage,Pagination page, Integer enabled, Integer belongPageType,Long platformId,Integer companyType);
	/**
	 * 查询我的体检轮播图列表(暂不做平台区分)
	 * @param belongPage 所属页面
	 * @param companyId 公司id
	 * @param platformId 平台id
	 * @return
	 */
	public List<BannerDTO> queryBannerListByBelongPageAndCompanyId(Integer belongPage, Long companyId, Long platformId, Long companyAllId);
	
	/**
	 * 根据bannerID和所属公司查询轮播图
	 * @param bannerId
	 * @param companyId
	 * @param companyAllId
	 * @return
	 */
	BannerDTO findBannerByIdAndCompanyId(Long bannerId, Long companyId, Long companyAllId);

}
	