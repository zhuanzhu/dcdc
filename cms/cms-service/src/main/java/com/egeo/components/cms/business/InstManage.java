package com.egeo.components.cms.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.cms.vo.LinkableButtonPageVO;
import com.egeo.web.JsonResult;	

public interface InstManage {

	/**
	 * 查询实例内容
	 * @param elementId
	 * @param instId
	 * @param companyId
	 * @return
	 */
	public JsonResult<Map<String, Object>> instContent(Long storeId,Long elementId, Long instId, Long companyId,Long platformId,Long clientId);

	/**
	 * 新增/编辑组件和实例
	 * @param name
	 * @param margin
	 * @param elementDictId
	 * @param elementId
	 * @param instId
	 * @param companyIds
	 * @param bannerIds
	 * @param titleName
	 * @param sortType
	 * @param maxShow
	 * @param sucId
	 * @param bannerUrl
	 * @param linkType
	 * @param linkId
	 * @param linkUrl
	 * @param linkParam
	 * @param count
	 * @param iconArr
	 * @param iconTitle
	 * @param imagetextArr
	 * @param lableIconUrl
	 * @param lableArr
	 * @return
	 */
	public JsonResult<Map<String, Object>> saveEleAndInst(
			// 通用参数
			Long templateId,String name, Integer margin, Long elementDictId, Long elementId, Long instId, String companyIds,
			Long pageTabId,
			// banner参数
			String bannerIds,
			// su列表参数
			Long titleColor, String titleName, Integer maxShow, Long sucId, String bannerUrl, Integer linkType,
			Long linkId, String linkUrl,
			// icon参数
			Integer count, String iconArr, String iconTitle,
			// 图文参数
			String imagetextTitle,String imagetextArr, String imagetextBannerArr,
			// 标签参数
			String lableIconUrl, String lableArr,
			List<LinkableButtonPageVO> linkableButtonPageVOs
			);

	/**
	 * 查询组件和实例详情
	 * @param elementId
	 * @return
	 */
	public JsonResult<Map<String, Object>> eleAndInstDetail(Long elementId);
}
	