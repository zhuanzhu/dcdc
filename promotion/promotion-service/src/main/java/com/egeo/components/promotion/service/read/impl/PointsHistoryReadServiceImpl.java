package com.egeo.components.promotion.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.service.read.PointsHistoryReadService;
import com.egeo.components.promotion.manage.read.PointsHistoryReadManage;
import com.egeo.components.promotion.converter.PointsHistoryConverter;
import com.egeo.components.promotion.dto.PointsHistoryDTO;
import com.egeo.components.promotion.po.PointsHistoryPO;
 
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("pointsHistoryReadService")
public class PointsHistoryReadServiceImpl implements PointsHistoryReadService {
	@Autowired
	private PointsHistoryReadManage pointsHistoryReadManage;

	@Override
	public PointsHistoryDTO findPointsHistoryById(PointsHistoryDTO dto) {
		PointsHistoryPO po = PointsHistoryConverter.toPO(dto);
		PointsHistoryPO list = pointsHistoryReadManage.findPointsHistoryById(po);		
		return PointsHistoryConverter.toDTO(list);
	}

	@Override
	public PageResult<PointsHistoryDTO> findPointsHistoryOfPage(PointsHistoryDTO dto, Pagination page) {
		PointsHistoryPO po = PointsHistoryConverter.toPO(dto);
        PageResult<PointsHistoryPO> pageResult = pointsHistoryReadManage.findPointsHistoryOfPage(po, page);
        
        List<PointsHistoryDTO> list = PointsHistoryConverter.toDTO(pageResult.getList());
        PageResult<PointsHistoryDTO> result = new PageResult<PointsHistoryDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<PointsHistoryDTO> findPointsHistoryAll(PointsHistoryDTO dto) {
		PointsHistoryPO po = PointsHistoryConverter.toPO(dto);
		List<PointsHistoryPO> list = pointsHistoryReadManage.findPointsHistoryAll(po);		
		return PointsHistoryConverter.toDTO(list);
	}
}
	