package com.egeo.components.cms.service.write;

import java.util.List;

import com.egeo.components.cms.dto.InstDTO;
import com.egeo.components.cms.dto.LinkableButtonPageDTO;

public interface InstWriteService {

	public Long insertInstWithTx(InstDTO dto);

	public int updateInstWithTx(InstDTO dto);

	public int deleteInstWithTx(InstDTO dto);

	/**
	 * 新建/保存组件和实例
	 * 
	 * @return
	 */
	public boolean saveEleAndInst(
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
			
			List<LinkableButtonPageDTO> linkableButtonPageDTOs);
}
