package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.SerachSortReadService;
import com.egeo.components.product.manage.read.SerachSortReadManage;
import com.egeo.components.product.converter.SerachSortConverter;
import com.egeo.components.product.dto.SerachSortDTO;
import com.egeo.components.product.po.SerachSortPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("serachSortReadService")
public class SerachSortReadServiceImpl  implements SerachSortReadService {
	@Autowired
	private SerachSortReadManage serachSortReadManage;

	@Override
	public SerachSortDTO findSerachSortById(SerachSortDTO dto) {
		SerachSortPO po = SerachSortConverter.toPO(dto);
		SerachSortPO list = serachSortReadManage.findSerachSortById(po);		
		return SerachSortConverter.toDTO(list);
	}

	@Override
	public PageResult<SerachSortDTO> findSerachSortOfPage(SerachSortDTO dto, Pagination page) {
		SerachSortPO po = SerachSortConverter.toPO(dto);
        PageResult<SerachSortPO> pageResult = serachSortReadManage.findSerachSortOfPage(po, page);
        
        List<SerachSortDTO> list = SerachSortConverter.toDTO(pageResult.getList());
        PageResult<SerachSortDTO> result = new PageResult<SerachSortDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SerachSortDTO> findSerachSortAll(SerachSortDTO dto) {
		SerachSortPO po = SerachSortConverter.toPO(dto);
		List<SerachSortPO> list = serachSortReadManage.findSerachSortAll(po);		
		return SerachSortConverter.toDTO(list);
	}
}
	