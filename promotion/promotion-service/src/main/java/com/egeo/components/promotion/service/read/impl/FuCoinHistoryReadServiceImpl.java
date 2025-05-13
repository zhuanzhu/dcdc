package com.egeo.components.promotion.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.service.read.FuCoinHistoryReadService;
import com.egeo.components.promotion.manage.read.FuCoinHistoryReadManage;
import com.egeo.components.promotion.converter.FuCoinHistoryConverter;
import com.egeo.components.promotion.dto.FuCoinHistoryDTO;
import com.egeo.components.promotion.po.FuCoinHistoryPO;
 
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("fuCoinHistoryReadService")
public class FuCoinHistoryReadServiceImpl implements FuCoinHistoryReadService {
	@Autowired
	private FuCoinHistoryReadManage fuCoinHistoryReadManage;

	@Override
	public FuCoinHistoryDTO findFuCoinHistoryById(FuCoinHistoryDTO dto) {
		FuCoinHistoryPO po = FuCoinHistoryConverter.toPO(dto);
		FuCoinHistoryPO list = fuCoinHistoryReadManage.findFuCoinHistoryById(po);		
		return FuCoinHistoryConverter.toDTO(list);
	}

	@Override
	public PageResult<FuCoinHistoryDTO> findFuCoinHistoryOfPage(FuCoinHistoryDTO dto, Pagination page) {
		FuCoinHistoryPO po = FuCoinHistoryConverter.toPO(dto);
        PageResult<FuCoinHistoryPO> pageResult = fuCoinHistoryReadManage.findFuCoinHistoryOfPage(po, page);
        
        List<FuCoinHistoryDTO> list = FuCoinHistoryConverter.toDTO(pageResult.getList());
        PageResult<FuCoinHistoryDTO> result = new PageResult<FuCoinHistoryDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<FuCoinHistoryDTO> findFuCoinHistoryAll(FuCoinHistoryDTO dto) {
		FuCoinHistoryPO po = FuCoinHistoryConverter.toPO(dto);
		List<FuCoinHistoryPO> list = fuCoinHistoryReadManage.findFuCoinHistoryAll(po);		
		return FuCoinHistoryConverter.toDTO(list);
	}
}
	