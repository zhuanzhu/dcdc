package com.egeo.components.promotion.service.read.impl;

import com.egeo.components.promotion.converter.BuyCardItemConverter;
import com.egeo.components.promotion.dto.BuyCardItemDTO;
import com.egeo.components.promotion.manage.read.BuyCardItemReadManage;
import com.egeo.components.promotion.po.BuyCardItemPO;
import com.egeo.components.promotion.service.read.BuyCardItemReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("buyCardItemReadService")
public class BuyCardItemReadServiceImpl implements BuyCardItemReadService {
	@Autowired
	private BuyCardItemReadManage buyCardItemReadManage;

	@Override
	public BuyCardItemDTO findBuyCardItemById(BuyCardItemDTO dto) {
		BuyCardItemPO po = BuyCardItemConverter.toPO(dto);
		BuyCardItemPO list = buyCardItemReadManage.findBuyCardItemById(po);
		return BuyCardItemConverter.toDTO(list);
	}

	@Override
	public PageResult<BuyCardItemDTO> findBuyCardItemOfPage(BuyCardItemDTO dto, Pagination page) {
		BuyCardItemPO po = BuyCardItemConverter.toPO(dto);
		PageResult<BuyCardItemPO> pageResult = buyCardItemReadManage.findBuyCardItemOfPage(po, page);

		List<BuyCardItemDTO> list = BuyCardItemConverter.toDTO(pageResult.getList());
		PageResult<BuyCardItemDTO> result = new PageResult<BuyCardItemDTO>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	@Override
	public List<BuyCardItemDTO> findBuyCardItemAll(BuyCardItemDTO dto) {
		BuyCardItemPO po = BuyCardItemConverter.toPO(dto);
		List<BuyCardItemPO> list = buyCardItemReadManage.findBuyCardItemAll(po);
		return BuyCardItemConverter.toDTO(list);
	}
}
