package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.BannerInstWriteService;
import com.egeo.components.cms.manage.write.BannerInstWriteManage;
import com.egeo.components.cms.converter.BannerInstConverter;
import com.egeo.components.cms.dto.BannerInstDTO;
import com.egeo.components.cms.po.BannerInstPO;

@Service("bannerInstWriteService")
public class BannerInstWriteServiceImpl  implements BannerInstWriteService {
	@Autowired
	private BannerInstWriteManage bannerInstWriteManage;

	@Override
	public Long insertBannerInstWithTx(BannerInstDTO dto) {
		BannerInstPO po = BannerInstConverter.toPO(dto);
		Long rt = bannerInstWriteManage.insertBannerInstWithTx(po);		
		return rt;
	}

	@Override
	public int updateBannerInstWithTx(BannerInstDTO dto) {
		BannerInstPO po = BannerInstConverter.toPO(dto);
		int rt = bannerInstWriteManage.updateBannerInstWithTx(po);		
		return rt;
	}

	@Override
	public int deleteBannerInstWithTx(BannerInstDTO dto) {
		BannerInstPO po = BannerInstConverter.toPO(dto);
		int rt = bannerInstWriteManage.deleteBannerInstWithTx(po);		
		return rt;
	}
}
	