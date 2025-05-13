package com.egeo.components.cms.manage.write;

import java.util.List;

import com.egeo.components.cms.po.InstPO;
import com.egeo.components.cms.po.LinkableButtonPagePO;

public interface InstWriteManage {

	Long insertInstWithTx(InstPO po);

	int updateInstWithTx(InstPO po);

	int deleteInstWithTx(InstPO po);

	/**
	 * 新建/保存组件和实例
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
	boolean saveEleAndInstWithTx(
			// 通用参数
			Long templateId,String name, Integer margin, Long elementDictId, Long elementId, Long instId, List<Long> cids,
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
			List<LinkableButtonPagePO> linkableButtonPagePOs);
}
