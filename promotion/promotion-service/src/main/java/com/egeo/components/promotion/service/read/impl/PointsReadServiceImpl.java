package com.egeo.components.promotion.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.service.read.PointsReadService;
import com.egeo.components.promotion.manage.read.PointsReadManage;
import com.egeo.components.promotion.converter.PointsConverter;
import com.egeo.components.promotion.dto.PointsDTO;
import com.egeo.components.promotion.po.PointsPO;
 
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("pointsReadService")
public class PointsReadServiceImpl implements PointsReadService {
	@Autowired
	private PointsReadManage pointsReadManage;

	@Override
	public PointsDTO findPointsById(PointsDTO dto) {
		PointsPO po = PointsConverter.toPO(dto);
		PointsPO list = pointsReadManage.findPointsById(po);		
		return PointsConverter.toDTO(list);
	}

	@Override
	public PageResult<PointsDTO> findPointsOfPage(PointsDTO dto, Pagination page) {
		PointsPO po = PointsConverter.toPO(dto);
        PageResult<PointsPO> pageResult = pointsReadManage.findPointsOfPage(po, page);
        
        List<PointsDTO> list = PointsConverter.toDTO(pageResult.getList());
        PageResult<PointsDTO> result = new PageResult<PointsDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<PointsDTO> findPointsAll(PointsDTO dto) {
		PointsPO po = PointsConverter.toPO(dto);
		List<PointsPO> list = pointsReadManage.findPointsAll(po);		
		return PointsConverter.toDTO(list);
	}
}
	