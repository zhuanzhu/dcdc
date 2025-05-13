package com.egeo.components.cms.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.cms.dto.BannerDTO;
import com.egeo.web.JsonResult;

/*import com.egeo.components.user.vo.BannerVO;
import com.egeo.components.cms.dto.BannerDTO;
import com.egeo.web.JsonResult;*/

public interface BannerManage {

	JsonResult<Map<String, Object>> queryBannerList(
			String name,String companyName,Integer linkType,List<Long> bannerIdList,
			Integer belongPage,Integer pageNo,Integer pageSize, Long companyId, 
			Integer enabled, Integer belongPageType,Long platformId,Integer companyType);
	JsonResult<Map<String, Object>> queryCompanyBannerList(
			String name,String companyName,Integer linkType,List<Long> bannerIdList,
			Integer belongPage,Integer pageNo,Integer pageSize, Long companyId,
			Integer enabled, Integer belongPageType,Long platformId,Integer companyType);

	/**
	 * 查询轮播图详情
	 * @param bannerId
	 * @return
	 */
	JsonResult<Map<String, Object>> bannerDetail(Long bannerId);

	/**
	 * 新建/编辑轮播图
	 * @param bannerId
	 * @param name
	 * @param companyIds
	 * @param imgUrl
	 * @param remark
	 * @param enabled
	 * @param linkType
	 * @param linkId
	 * @param linkUrl
	 * @param linkParam
	 * @return
	 */
	JsonResult<Map<String, Object>> saveBanner(
			Long bannerId,String name,String companyIds,
			String imgUrl,String remark,Integer enabled,Integer sort,Integer companyType,
			//链接信息
			Integer linkType,Long linkId,String linkUrl,String linkParam,Integer belongPage,Long platformId,
			String linkableButtonPageList,String extParam,Integer isDefault);
	
	
	/**
	 * 查询我的体检轮播图列表
	 * @param belongPage 所属页面
	 * @param companyId 公司id
	 * @param platformId 平台id
	 * @return
	 */
	JsonResult<Map<String, Object>> queryBannerListByType(Integer belongPage, Long companyId, Long platformId, Long clientId);

	/**
	 * 重置banner状态
	 * @param dto
	 * @return
	 */
	JsonResult<Integer> resetBannerStatusById(BannerDTO dto);

	/**
	 * 查询轮播图的所属公司列表
	 * @param dto
	 * @return
	 */
	JsonResult<List<Map<String, Object>>> queryCompanyListByBannerId(BannerDTO dto);

}
	