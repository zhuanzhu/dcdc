package com.egeo.components.cms.manage.read;

import java.util.List;
import java.util.Map;

import com.egeo.components.cms.po.BannerPO;
import com.egeo.components.cms.po.BannerPickPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface BannerReadManage {

	public BannerPO findBannerById(BannerPO po);

	public PageResult<BannerPO> findBannerOfPage(BannerPO po,Pagination page);

	public List<BannerPO> findBannerAll(BannerPO po);

	public PageResult<Map<String, Object>> findPickBannerOfPage(BannerPickPO po, Pagination page);

	/**
	 * 根据实例id和公司id查询轮播图列表
	 * @param instId
	 * @param companyId
	 * @return
	 */
	public List<BannerPO> queryBannerListByInstIdAndCompanyId(Long instId, Long companyId,Long companyAllId);

	/**
	 * 查询轮播图分页列表
	 * @param name
	 * @param linkType
	 * @param companyIdList
	 * @param page
	 * @return
	 */
	public PageResult<BannerPO> queryBannerPage(String name, Integer linkType, List<Long> companyIdList,List<Long> bannerIdList,
			Integer belongPage,Pagination page, Integer enabled, Integer belongPageType,Long platformId,Integer companyType);
	/**
	 * 查询我的体检轮播图列表(暂不做平台区分)
	 * @param belongPage 所属页面
	 * @param companyId 公司id
	 * @param platformId 平台id
	 * @return
	 */
	public List<BannerPO> queryBannerListByBelongPageAndCompanyId(Integer belongPage, Long companyId, Long platformId, Long companyAllId);
	
	/**
	 * 根据bannerID和所属公司查询轮播图
	 * @param bannerId
	 * @param companyId
	 * @param companyAllId
	 * @return
	 */
	BannerPO findBannerByIdAndCompanyId(Long bannerId, Long companyId, Long companyAllId);
}
	