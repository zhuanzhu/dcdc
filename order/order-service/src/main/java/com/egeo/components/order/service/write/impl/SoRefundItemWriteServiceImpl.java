package com.egeo.components.order.service.write.impl;

import com.egeo.components.order.converter.SoRefundItemConverter;
import com.egeo.components.order.dto.SoRefundItemDTO;
import com.egeo.components.order.manage.write.SoRefundItemWriteManage;
import com.egeo.components.order.po.SoRefundItemPO;
import com.egeo.components.order.service.write.SoRefundItemWriteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SoRefundItemWriteServiceImpl implements SoRefundItemWriteService {
	@Resource
	private SoRefundItemWriteManage soRefundItemWriteManage;

	@Override
	public Long insertSoRefundItemWithTx(SoRefundItemDTO dto) {
		SoRefundItemPO po = SoRefundItemConverter.toPO(dto);
		Long rt = soRefundItemWriteManage.insertSoRefundItemWithTx(po);
		return rt;
	}

	@Override
	public int updateSoRefundItemWithTx(SoRefundItemDTO dto) {
		SoRefundItemPO po = SoRefundItemConverter.toPO(dto);
		int rt = soRefundItemWriteManage.updateSoRefundItemWithTx(po);
		return rt;
	}

	@Override
	public int deleteSoRefundItemWithTx(SoRefundItemDTO dto) {
		SoRefundItemPO po = SoRefundItemConverter.toPO(dto);
		int rt = soRefundItemWriteManage.deleteSoRefundItemWithTx(po);
		return rt;
	}
}
	