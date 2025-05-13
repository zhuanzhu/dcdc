package com.egeo.components.promotion.service.read.impl;

import com.egeo.components.promotion.converter.BuyCardBaseConverter;
import com.egeo.components.promotion.dto.BuyCardBaseDTO;
import com.egeo.components.promotion.manage.read.BuyCardBaseReadManage;
import com.egeo.components.promotion.po.BuyCardBasePO;
import com.egeo.components.promotion.service.read.BuyCardBaseReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("buyCardBaseReadService")
public class BuyCardBaseReadServiceImpl implements BuyCardBaseReadService {
	@Autowired
	private BuyCardBaseReadManage buyCardBaseReadManage;

	@Override
	public BuyCardBaseDTO findBuyCardBaseById(BuyCardBaseDTO dto) {
		BuyCardBasePO po = BuyCardBaseConverter.toPO(dto);
		BuyCardBasePO list = buyCardBaseReadManage.findBuyCardBaseById(po);
		return BuyCardBaseConverter.toDTO(list);
	}

	@Override
	public PageResult<BuyCardBaseDTO> findBuyCardBaseOfPage(BuyCardBaseDTO dto, Pagination page) {
		BuyCardBasePO po = BuyCardBaseConverter.toPO(dto);
		PageResult<BuyCardBasePO> pageResult = buyCardBaseReadManage.findBuyCardBaseOfPage(po, page);

		List<BuyCardBaseDTO> list = BuyCardBaseConverter.toDTO(pageResult.getList());
		PageResult<BuyCardBaseDTO> result = new PageResult<>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	@Override
	public List<BuyCardBaseDTO> findBuyCardBaseAll(BuyCardBaseDTO dto) {
		BuyCardBasePO po = BuyCardBaseConverter.toPO(dto);
		List<BuyCardBasePO> list = buyCardBaseReadManage.findBuyCardBaseAll(po);
		return BuyCardBaseConverter.toDTO(list);
	}

	@Override
	public int findMaxSortValue(){
		return buyCardBaseReadManage.findMaxSortValue();
	}
}
