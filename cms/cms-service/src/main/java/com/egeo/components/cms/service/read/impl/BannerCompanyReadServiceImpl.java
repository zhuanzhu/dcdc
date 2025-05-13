package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.BannerCompanyReadService;
import com.egeo.components.cms.manage.read.BannerCompanyReadManage;
import com.egeo.components.cms.converter.BannerCompanyConverter;
import com.egeo.components.cms.dto.BannerCompanyDTO;
import com.egeo.components.cms.po.BannerCompanyPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("bannerCompanyReadService")
public class BannerCompanyReadServiceImpl  implements BannerCompanyReadService {
	@Autowired
	private BannerCompanyReadManage bannerCompanyReadManage;

	@Override
	public BannerCompanyDTO findBannerCompanyById(BannerCompanyDTO dto) {
		BannerCompanyPO po = BannerCompanyConverter.toPO(dto);
		BannerCompanyPO list = bannerCompanyReadManage.findBannerCompanyById(po);		
		return BannerCompanyConverter.toDTO(list);
	}

	@Override
	public PageResult<BannerCompanyDTO> findBannerCompanyOfPage(BannerCompanyDTO dto, Pagination page) {
		BannerCompanyPO po = BannerCompanyConverter.toPO(dto);
        PageResult<BannerCompanyPO> pageResult = bannerCompanyReadManage.findBannerCompanyOfPage(po, page);
        
        List<BannerCompanyDTO> list = BannerCompanyConverter.toDTO(pageResult.getList());
        PageResult<BannerCompanyDTO> result = new PageResult<BannerCompanyDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<BannerCompanyDTO> findBannerCompanyAll(BannerCompanyDTO dto) {
		BannerCompanyPO po = BannerCompanyConverter.toPO(dto);
		List<BannerCompanyPO> list = bannerCompanyReadManage.findBannerCompanyAll(po);		
		return BannerCompanyConverter.toDTO(list);
	}
}
	