package com.egeo.components.promotion.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.service.read.BannerReadService;
import com.egeo.components.promotion.manage.read.BannerReadManage;
import com.egeo.components.promotion.converter.BannerConverter;
import com.egeo.components.promotion.dto.BannerDTO;
import com.egeo.components.promotion.po.BannerPO;
 
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("bannerReadService")
public class BannerReadServiceImpl implements BannerReadService {
	@Autowired
	private BannerReadManage bannerReadManage;

	@Override
	public BannerDTO findBannerById(BannerDTO dto) {
		BannerPO po = BannerConverter.toPO(dto);
		BannerPO list = bannerReadManage.findBannerById(po);		
		return BannerConverter.toDTO(list);
	}

	@Override
	public PageResult<BannerDTO> findBannerOfPage(BannerDTO dto, Pagination page) {
		BannerPO po = BannerConverter.toPO(dto);
        PageResult<BannerPO> pageResult = bannerReadManage.findBannerOfPage(po, page);
        
        List<BannerDTO> list = BannerConverter.toDTO(pageResult.getList());
        PageResult<BannerDTO> result = new PageResult<BannerDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<BannerDTO> findBannerAll(BannerDTO dto) {
		BannerPO po = BannerConverter.toPO(dto);
		List<BannerPO> list = bannerReadManage.findBannerAll(po);		
		return BannerConverter.toDTO(list);
	}
	/**
	 * 查询Banner最大排序值
	 * @return
	 */
	@Override
	public Integer maxSortValue() {
		return bannerReadManage.maxSortValue();
	}
}
	