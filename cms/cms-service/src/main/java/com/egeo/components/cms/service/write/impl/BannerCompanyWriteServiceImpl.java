package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.converter.BannerCompanyConverter;
import com.egeo.components.cms.dto.BannerCompanyDTO;
import com.egeo.components.cms.manage.write.BannerCompanyWriteManage;
import com.egeo.components.cms.po.BannerCompanyPO;
import com.egeo.components.cms.service.write.BannerCompanyWriteService;

@Service("bannerCompanyWriteService")
public class BannerCompanyWriteServiceImpl  implements BannerCompanyWriteService {
	@Autowired
	private BannerCompanyWriteManage bannerCompanyWriteManage;

	@Override
	public Long insertBannerCompanyWithTx(BannerCompanyDTO dto) {
		BannerCompanyPO po = BannerCompanyConverter.toPO(dto);
		Long rt = bannerCompanyWriteManage.insertBannerCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int updateBannerCompanyWithTx(BannerCompanyDTO dto) {
		BannerCompanyPO po = BannerCompanyConverter.toPO(dto);
		int rt = bannerCompanyWriteManage.updateBannerCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int deleteBannerCompanyWithTx(BannerCompanyDTO dto) {
		BannerCompanyPO po = BannerCompanyConverter.toPO(dto);
		int rt = bannerCompanyWriteManage.deleteBannerCompanyWithTx(po);		
		return rt;
	}
}
	