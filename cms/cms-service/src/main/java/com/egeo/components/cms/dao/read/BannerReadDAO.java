package com.egeo.components.cms.dao.read;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.BannerPO;
import com.egeo.components.cms.po.BannerPickPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface BannerReadDAO extends BaseReadDAO<BannerPO>{


	List<Map<String, Object>> findPickBannerOfPage(@Param("po")BannerPickPO po, @Param("page")Pagination page);

	int countPickBannerOfPage(@Param("po")BannerPickPO po);

	/**
	 * 根据实例id和公司id查询轮播图列表
	 * @param instId
	 * @param companyId
	 * @return
	 */
	List<BannerPO> queryBannerListByInstIdAndCompanyId(
			@Param("instId")Long instId, 
			@Param("companyId")Long companyId,
			@Param("companyAllId")Long companyAllId);

	/**
	 * 查询轮播图分页列表
	 * @param name
	 * @param linkType
	 * @param companyIdList
	 * @param page
	 * @return
	 */
	List<BannerPO> queryBannerPage(
			@Param("name")String name, 
			@Param("linkType")Integer linkType, 
			@Param("companyIdList")List<Long> companyIdList, 
			@Param("bannerIdList")List<Long> bannerIdList, 
			@Param("belongPage")Integer belongPage,
			@Param("page")Pagination page,
			@Param("enabled")Integer enabled,
			@Param("belongPageType")Integer belongPageType,
			@Param("platformId")Long platformId,
			@Param("companyType")Integer companyType);

	/**
	 * 查询轮播图分页列表总记录数
	 * @param name
	 * @param linkType
	 * @param companyIdList
	 * @return
	 */
	Integer queryBannerPageTotalSize(
			@Param("name")String name, 
			@Param("linkType")Integer linkType, 
			@Param("companyIdList")List<Long> companyIdList,
			@Param("bannerIdList")List<Long> bannerIdList,
			@Param("belongPage")Integer belongPage,
			@Param("enabled")Integer enabled,
			@Param("belongPageType")Integer belongPageType,
			@Param("platformId")Long platformId,
			@Param("companyType")Integer companyType);
	/**
	 * 查询我的体检轮播图列表(暂不做平台区分)
	 * @param belongPage 所属页面
	 * @param companyId 公司id
	 * @param platformId 平台id
	 * @return
	 */
	List<BannerPO> queryBannerListByBelongPageAndCompanyId(
			@Param("belongPage")Integer belongPage, 
			@Param("companyId")Long companyId, 
			@Param("platformId")Long platformId,
			@Param("companyAllId")Long companyAllId);
	
	/**
	 * 根据bannerID和所属公司查询轮播图
	 * @param bannerId
	 * @param companyId
	 * @param companyAllId
	 * @return
	 */
	BannerPO findBannerByIdAndCompanyId(
			@Param("bannerId")Long bannerId,
			@Param("companyId")Long companyId, 
			@Param("companyAllId")Long companyAllId);

}
	