package com.egeo.components.promotion.service.read.impl;

import com.egeo.components.promotion.converter.BuyCardItemRefundConverter;
import com.egeo.components.promotion.dto.BuyCardItemRefundDTO;
import com.egeo.components.promotion.manage.read.BuyCardItemRefundReadManage;
import com.egeo.components.promotion.po.BuyCardItemRefundPO;
import com.egeo.components.promotion.service.read.BuyCardItemRefundReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("buyCardItemRefundReadService")
public class BuyCardItemRefundReadServiceImpl implements BuyCardItemRefundReadService {
	@Autowired
	private BuyCardItemRefundReadManage buyCardItemRefundReadManage;

	@Override
	public BuyCardItemRefundDTO findBuyCardItemRefundById(BuyCardItemRefundDTO dto) {
		BuyCardItemRefundPO po = BuyCardItemRefundConverter.toPO(dto);
		BuyCardItemRefundPO list = buyCardItemRefundReadManage.findBuyCardItemRefundById(po);
		return BuyCardItemRefundConverter.toDTO(list);
	}

	@Override
	public PageResult<BuyCardItemRefundDTO> findBuyCardItemRefundOfPage(BuyCardItemRefundDTO dto, Pagination page) {
		BuyCardItemRefundPO po = BuyCardItemRefundConverter.toPO(dto);
		PageResult<BuyCardItemRefundPO> pageResult = buyCardItemRefundReadManage.findBuyCardItemRefundOfPage(po, page);

		List<BuyCardItemRefundDTO> list = BuyCardItemRefundConverter.toDTO(pageResult.getList());
		PageResult<BuyCardItemRefundDTO> result = new PageResult<BuyCardItemRefundDTO>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	@Override
	public List<BuyCardItemRefundDTO> findBuyCardItemRefundAll(BuyCardItemRefundDTO dto) {
		BuyCardItemRefundPO po = BuyCardItemRefundConverter.toPO(dto);
		List<BuyCardItemRefundPO> list = buyCardItemRefundReadManage.findBuyCardItemRefundAll(po);
		return BuyCardItemRefundConverter.toDTO(list);
	}
}
