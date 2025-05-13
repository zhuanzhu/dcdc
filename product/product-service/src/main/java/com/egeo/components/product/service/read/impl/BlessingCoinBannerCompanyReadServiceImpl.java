package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.BlessingCoinBannerCompanyReadService;
import com.egeo.components.product.manage.read.BlessingCoinBannerCompanyReadManage;
import com.egeo.components.product.converter.BlessingCoinBannerCompanyConverter;
import com.egeo.components.product.dto.BlessingCoinBannerCompanyDTO;
import com.egeo.components.product.po.BlessingCoinBannerCompanyPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("blessingCoinBannerCompanyReadService")
public class BlessingCoinBannerCompanyReadServiceImpl  implements BlessingCoinBannerCompanyReadService {
	@Autowired
	private BlessingCoinBannerCompanyReadManage blessingCoinBannerCompanyReadManage;

	@Override
	public BlessingCoinBannerCompanyDTO findBlessingCoinBannerCompanyById(BlessingCoinBannerCompanyDTO dto) {
		BlessingCoinBannerCompanyPO po = BlessingCoinBannerCompanyConverter.toPO(dto);
		BlessingCoinBannerCompanyPO list = blessingCoinBannerCompanyReadManage.findBlessingCoinBannerCompanyById(po);		
		return BlessingCoinBannerCompanyConverter.toDTO(list);
	}

	@Override
	public PageResult<BlessingCoinBannerCompanyDTO> findBlessingCoinBannerCompanyOfPage(BlessingCoinBannerCompanyDTO dto, Pagination page) {
		BlessingCoinBannerCompanyPO po = BlessingCoinBannerCompanyConverter.toPO(dto);
        PageResult<BlessingCoinBannerCompanyPO> pageResult = blessingCoinBannerCompanyReadManage.findBlessingCoinBannerCompanyOfPage(po, page);
        
        List<BlessingCoinBannerCompanyDTO> list = BlessingCoinBannerCompanyConverter.toDTO(pageResult.getList());
        PageResult<BlessingCoinBannerCompanyDTO> result = new PageResult<BlessingCoinBannerCompanyDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<BlessingCoinBannerCompanyDTO> findBlessingCoinBannerCompanyAll(BlessingCoinBannerCompanyDTO dto) {
		BlessingCoinBannerCompanyPO po = BlessingCoinBannerCompanyConverter.toPO(dto);
		List<BlessingCoinBannerCompanyPO> list = blessingCoinBannerCompanyReadManage.findBlessingCoinBannerCompanyAll(po);		
		return BlessingCoinBannerCompanyConverter.toDTO(list);
	}
}
	