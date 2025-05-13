package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.BlessingCoinBannerReadService;
import com.egeo.components.product.manage.read.BlessingCoinBannerReadManage;
import com.egeo.components.product.converter.BlessingCoinBannerConverter;
import com.egeo.components.product.dto.BlessingCoinBannerDTO;
import com.egeo.components.product.po.BlessingCoinBannerPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("blessingCoinBannerReadService")
public class BlessingCoinBannerReadServiceImpl  implements BlessingCoinBannerReadService {
	@Autowired
	private BlessingCoinBannerReadManage blessingCoinBannerReadManage;

	@Override
	public BlessingCoinBannerDTO findBlessingCoinBannerById(BlessingCoinBannerDTO dto) {
		BlessingCoinBannerPO po = BlessingCoinBannerConverter.toPO(dto);
		BlessingCoinBannerPO list = blessingCoinBannerReadManage.findBlessingCoinBannerById(po);		
		return BlessingCoinBannerConverter.toDTO(list);
	}

	@Override
	public PageResult<BlessingCoinBannerDTO> findBlessingCoinBannerOfPage(BlessingCoinBannerDTO dto, Pagination page) {
		BlessingCoinBannerPO po = BlessingCoinBannerConverter.toPO(dto);
        PageResult<BlessingCoinBannerPO> pageResult = blessingCoinBannerReadManage.findBlessingCoinBannerOfPage(po, page);
        
        List<BlessingCoinBannerDTO> list = BlessingCoinBannerConverter.toDTO(pageResult.getList());
        PageResult<BlessingCoinBannerDTO> result = new PageResult<BlessingCoinBannerDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<BlessingCoinBannerDTO> findBlessingCoinBannerAll(BlessingCoinBannerDTO dto) {
		BlessingCoinBannerPO po = BlessingCoinBannerConverter.toPO(dto);
		List<BlessingCoinBannerPO> list = blessingCoinBannerReadManage.findBlessingCoinBannerAll(po);		
		return BlessingCoinBannerConverter.toDTO(list);
	}
	/**
	 * 客户端查询所有有效轮播图信息
	 * @param vo
	 * @param req
	 * @return
	 */
	@Override
	public List<BlessingCoinBannerDTO> findBlessingCoinBannerAllApp(BlessingCoinBannerDTO dto) {
		List<BlessingCoinBannerPO> list = blessingCoinBannerReadManage.findBlessingCoinBannerAllApp(BlessingCoinBannerConverter.toPO(dto));
		return BlessingCoinBannerConverter.toDTO(list);
	}
}
	