package com.egeo.components.cms.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.InstWriteService;
import com.egeo.components.cms.manage.write.InstWriteManage;
import com.egeo.components.cms.converter.InstConverter;
import com.egeo.components.cms.converter.LinkableButtonPageConverter;
import com.egeo.components.cms.dto.InstDTO;
import com.egeo.components.cms.dto.LinkableButtonPageDTO;
import com.egeo.components.cms.po.InstPO;
import com.egeo.components.cms.po.LinkableButtonPagePO;

@Service("instWriteService")
public class InstWriteServiceImpl  implements InstWriteService {
	@Autowired
	private InstWriteManage instWriteManage;

	@Override
	public Long insertInstWithTx(InstDTO dto) {
		InstPO po = InstConverter.toPO(dto);
		Long rt = instWriteManage.insertInstWithTx(po);
		return rt;
	}

	@Override
	public int updateInstWithTx(InstDTO dto) {
		InstPO po = InstConverter.toPO(dto);
		int rt = instWriteManage.updateInstWithTx(po);
		return rt;
	}

	@Override
	public int deleteInstWithTx(InstDTO dto) {
		InstPO po = InstConverter.toPO(dto);
		int rt = instWriteManage.deleteInstWithTx(po);
		return rt;
	}

	@Override
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
			List<LinkableButtonPageDTO> linkableButtonPageDTOs) {
		
			List<LinkableButtonPagePO> linkableButtonPagePOs = LinkableButtonPageConverter.toPO(linkableButtonPageDTOs);

		return instWriteManage.saveEleAndInstWithTx(templateId,name, margin, elementDictId, elementId, instId, cids, pageTabId,
				bannerIds, titleColor, titleName, maxShow, sucId, bannerUrl, linkType, linkId, linkUrl, count, iconArr, iconTitle,
				imagetextTitle, imagetextArr, imagetextBannerArr,
				lableIconUrl, lableArr,linkableButtonPagePOs);
	}
}
