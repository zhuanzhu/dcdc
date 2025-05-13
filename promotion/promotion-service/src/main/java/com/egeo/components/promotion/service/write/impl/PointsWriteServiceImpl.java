package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.PointsWriteService;
import com.egeo.components.promotion.manage.write.PointsWriteManage;
import com.egeo.components.promotion.converter.PointsConverter;
import com.egeo.components.promotion.dto.PointsDTO;
import com.egeo.components.promotion.po.PointsPO;

@Service("pointsWriteService")
public class PointsWriteServiceImpl implements PointsWriteService {
	@Autowired
	private PointsWriteManage pointsWriteManage;

	@Override
	public Long insertPointsWithTx(PointsDTO dto) {
		PointsPO po = PointsConverter.toPO(dto);
		Long rt = pointsWriteManage.insertPointsWithTx(po);		
		return rt;
	}

	@Override
	public int updatePointsWithTx(PointsDTO dto) {
		PointsPO po = PointsConverter.toPO(dto);
		int rt = pointsWriteManage.updatePointsWithTx(po);		
		return rt;
	}

	@Override
	public int deletePointsWithTx(PointsDTO dto) {
		PointsPO po = PointsConverter.toPO(dto);
		int rt = pointsWriteManage.deletePointsWithTx(po);		
		return rt;
	}
}
	