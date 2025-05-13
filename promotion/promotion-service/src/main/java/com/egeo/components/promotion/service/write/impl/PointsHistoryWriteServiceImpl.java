package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.PointsHistoryWriteService;
import com.egeo.components.promotion.manage.write.PointsHistoryWriteManage;
import com.egeo.components.promotion.converter.PointsHistoryConverter;
import com.egeo.components.promotion.dto.PointsHistoryDTO;
import com.egeo.components.promotion.po.PointsHistoryPO;

@Service("pointsHistoryWriteService")
public class PointsHistoryWriteServiceImpl implements PointsHistoryWriteService {
	@Autowired
	private PointsHistoryWriteManage pointsHistoryWriteManage;

	@Override
	public Long insertPointsHistoryWithTx(PointsHistoryDTO dto) {
		PointsHistoryPO po = PointsHistoryConverter.toPO(dto);
		Long rt = pointsHistoryWriteManage.insertPointsHistoryWithTx(po);		
		return rt;
	}

	@Override
	public int updatePointsHistoryWithTx(PointsHistoryDTO dto) {
		PointsHistoryPO po = PointsHistoryConverter.toPO(dto);
		int rt = pointsHistoryWriteManage.updatePointsHistoryWithTx(po);		
		return rt;
	}

	@Override
	public int deletePointsHistoryWithTx(PointsHistoryDTO dto) {
		PointsHistoryPO po = PointsHistoryConverter.toPO(dto);
		int rt = pointsHistoryWriteManage.deletePointsHistoryWithTx(po);		
		return rt;
	}
}
	