package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.BannerInstReadService;
import com.egeo.components.cms.manage.read.BannerInstReadManage;
import com.egeo.components.cms.converter.BannerInstConverter;
import com.egeo.components.cms.dto.BannerInstDTO;
import com.egeo.components.cms.po.BannerInstPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("bannerInstReadService")
public class BannerInstReadServiceImpl  implements BannerInstReadService {
	@Autowired
	private BannerInstReadManage bannerInstReadManage;

	@Override
	public BannerInstDTO findBannerInstById(BannerInstDTO dto) {
		BannerInstPO po = BannerInstConverter.toPO(dto);
		BannerInstPO list = bannerInstReadManage.findBannerInstById(po);		
		return BannerInstConverter.toDTO(list);
	}

	@Override
	public PageResult<BannerInstDTO> findBannerInstOfPage(BannerInstDTO dto, Pagination page) {
		BannerInstPO po = BannerInstConverter.toPO(dto);
        PageResult<BannerInstPO> pageResult = bannerInstReadManage.findBannerInstOfPage(po, page);
        
        List<BannerInstDTO> list = BannerInstConverter.toDTO(pageResult.getList());
        PageResult<BannerInstDTO> result = new PageResult<BannerInstDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<BannerInstDTO> findBannerInstAll(BannerInstDTO dto) {
		BannerInstPO po = BannerInstConverter.toPO(dto);
		List<BannerInstPO> list = bannerInstReadManage.findBannerInstAll(po);		
		return BannerInstConverter.toDTO(list);
	}
}
	