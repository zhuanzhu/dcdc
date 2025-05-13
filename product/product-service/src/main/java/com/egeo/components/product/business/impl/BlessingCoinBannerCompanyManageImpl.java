package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.BlessingCoinBannerCompanyManage;
import com.egeo.components.product.facade.BlessingCoinBannerCompanyFacade;
import com.egeo.components.product.dto.BlessingCoinBannerCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("blessingCoinBannerCompany")
public class BlessingCoinBannerCompanyManageImpl implements BlessingCoinBannerCompanyManage{

	
	@Resource(name="blessingCoinBannerCompanyFacade")
	private BlessingCoinBannerCompanyFacade blessingCoinBannerCompanyFacade;

	@Override
	public BlessingCoinBannerCompanyDTO findBlessingCoinBannerCompanyById(BlessingCoinBannerCompanyDTO dto) {
		return blessingCoinBannerCompanyFacade.findBlessingCoinBannerCompanyById(dto);
	}

	@Override
	public PageResult<BlessingCoinBannerCompanyDTO> findBlessingCoinBannerCompanyOfPage(BlessingCoinBannerCompanyDTO dto, Pagination page) {
		return blessingCoinBannerCompanyFacade.findBlessingCoinBannerCompanyOfPage(dto, page);
	}

	@Override
	public List<BlessingCoinBannerCompanyDTO> findBlessingCoinBannerCompanyAll(BlessingCoinBannerCompanyDTO dto) {
		return blessingCoinBannerCompanyFacade.findBlessingCoinBannerCompanyAll(dto);
	}

	@Override
	public Long insertBlessingCoinBannerCompanyWithTx(BlessingCoinBannerCompanyDTO dto) {
		return blessingCoinBannerCompanyFacade.insertBlessingCoinBannerCompanyWithTx(dto);
	}

	@Override
	public int updateBlessingCoinBannerCompanyWithTx(BlessingCoinBannerCompanyDTO dto) {
		return blessingCoinBannerCompanyFacade.updateBlessingCoinBannerCompanyWithTx(dto);
	}

	@Override
	public int deleteBlessingCoinBannerCompanyWithTx(BlessingCoinBannerCompanyDTO dto) {
		return blessingCoinBannerCompanyFacade.deleteBlessingCoinBannerCompanyWithTx(dto);
	}


}
	