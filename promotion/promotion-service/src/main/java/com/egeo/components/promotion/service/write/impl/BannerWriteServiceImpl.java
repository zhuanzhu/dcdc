package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.BannerWriteService;
import com.egeo.components.promotion.manage.write.BannerWriteManage;
import com.egeo.components.promotion.converter.BannerConverter;
import com.egeo.components.promotion.dto.BannerDTO;
import com.egeo.components.promotion.po.BannerPO;

@Service("bannerWriteService")
public class BannerWriteServiceImpl implements BannerWriteService {
	@Autowired
	private BannerWriteManage bannerWriteManage;

	@Override
	public Long insertBannerWithTx(BannerDTO dto) {
		BannerPO po = BannerConverter.toPO(dto);
		Long rt = bannerWriteManage.insertBannerWithTx(po);		
		return rt;
	}

	@Override
	public int updateBannerWithTx(BannerDTO dto) {
		BannerPO po = BannerConverter.toPO(dto);
		int rt = bannerWriteManage.updateBannerWithTx(po);		
		return rt;
	}

	@Override
	public int deleteBannerWithTx(BannerDTO dto) {
		BannerPO po = BannerConverter.toPO(dto);
		int rt = bannerWriteManage.deleteBannerWithTx(po);		
		return rt;
	}
}
	