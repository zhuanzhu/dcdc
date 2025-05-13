package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.BlessingCoinBannerCompanyWriteService;
import com.egeo.components.product.manage.write.BlessingCoinBannerCompanyWriteManage;
import com.egeo.components.product.converter.BlessingCoinBannerCompanyConverter;
import com.egeo.components.product.dto.BlessingCoinBannerCompanyDTO;
import com.egeo.components.product.po.BlessingCoinBannerCompanyPO;

@Service("blessingCoinBannerCompanyWriteService")
public class BlessingCoinBannerCompanyWriteServiceImpl  implements BlessingCoinBannerCompanyWriteService {
	@Autowired
	private BlessingCoinBannerCompanyWriteManage blessingCoinBannerCompanyWriteManage;

	@Override
	public Long insertBlessingCoinBannerCompanyWithTx(BlessingCoinBannerCompanyDTO dto) {
		BlessingCoinBannerCompanyPO po = BlessingCoinBannerCompanyConverter.toPO(dto);
		Long rt = blessingCoinBannerCompanyWriteManage.insertBlessingCoinBannerCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int updateBlessingCoinBannerCompanyWithTx(BlessingCoinBannerCompanyDTO dto) {
		BlessingCoinBannerCompanyPO po = BlessingCoinBannerCompanyConverter.toPO(dto);
		int rt = blessingCoinBannerCompanyWriteManage.updateBlessingCoinBannerCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int deleteBlessingCoinBannerCompanyWithTx(BlessingCoinBannerCompanyDTO dto) {
		BlessingCoinBannerCompanyPO po = BlessingCoinBannerCompanyConverter.toPO(dto);
		int rt = blessingCoinBannerCompanyWriteManage.deleteBlessingCoinBannerCompanyWithTx(po);		
		return rt;
	}
}
	