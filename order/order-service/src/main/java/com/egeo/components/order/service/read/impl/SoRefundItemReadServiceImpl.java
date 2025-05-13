package com.egeo.components.order.service.read.impl;

import com.egeo.components.order.converter.SoRefundItemConverter;
import com.egeo.components.order.dto.SoRefundItemDTO;
import com.egeo.components.order.manage.read.SoRefundItemReadManage;
import com.egeo.components.order.po.SoRefundItemPO;
import com.egeo.components.order.service.read.SoRefundItemReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SoRefundItemReadServiceImpl implements SoRefundItemReadService {

	@Resource
	private SoRefundItemReadManage soRefundItemReadManage;

	@Override
	public SoRefundItemDTO findSoRefundItemById(SoRefundItemDTO dto) {
		SoRefundItemPO po = SoRefundItemConverter.toPO(dto);
		SoRefundItemPO soRefundItemPO = soRefundItemReadManage.findSoRefundItemById(po);
		return SoRefundItemConverter.toDTO(soRefundItemPO);
	}

	@Override
	public PageResult<SoRefundItemDTO> findSoRefundItemOfPage(SoRefundItemDTO dto, Pagination page) {
		SoRefundItemPO po = SoRefundItemConverter.toPO(dto);
        PageResult<SoRefundItemPO> pageResult = soRefundItemReadManage.findSoRefundItemOfPage(po, page);
        
        List<SoRefundItemDTO> list = SoRefundItemConverter.toDTO(pageResult.getList());
        PageResult<SoRefundItemDTO> result = new PageResult<SoRefundItemDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SoRefundItemDTO> findSoRefundItemAll(SoRefundItemDTO dto) {
		SoRefundItemPO po = SoRefundItemConverter.toPO(dto);
		List<SoRefundItemPO> list = soRefundItemReadManage.findSoRefundItemAll(po);
		return SoRefundItemConverter.toDTO(list);
	}

}
	