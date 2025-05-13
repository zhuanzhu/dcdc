package com.egeo.components.order.service.write.impl;

import com.egeo.components.order.converter.SoRefundItemConverter;
import com.egeo.components.order.manage.write.SoRefundItemWriteManage;
import com.egeo.components.order.po.SoRefundItemPO;
import com.egeo.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoRefundWriteService;
import com.egeo.components.order.manage.write.SoRefundWriteManage;
import com.egeo.components.order.converter.SoRefundConverter;
import com.egeo.components.order.dto.SoRefundDTO;
import com.egeo.components.order.po.SoRefundPO;

import java.util.List;

@Service("soRefundWriteService")
public class SoRefundWriteServiceImpl  implements SoRefundWriteService {
	@Autowired
	private SoRefundWriteManage soRefundWriteManage;
	@Autowired
	private SoRefundItemWriteManage soRefundItemWriteManage;

	@Override
	public Long insertSoRefundWithTx(SoRefundDTO dto) {
		SoRefundPO po = SoRefundConverter.toPO(dto);
		Long rt = soRefundWriteManage.insertSoRefundWithTx(po);
		if (EmptyUtil.isNotEmpty(dto.getSoRefundItemDTOS())){
			List<SoRefundItemPO> itemPOList=SoRefundItemConverter.toPO(dto.getSoRefundItemDTOS());
			for (SoRefundItemPO itemPO:itemPOList){
				itemPO.setRefundId(rt);
				itemPO.setRefundCode(po.getSoRefundCode());
				soRefundItemWriteManage.insertSoRefundItemWithTx(itemPO);
			}
		}
		return rt;
	}

	@Override
	public int updateSoRefundWithTx(SoRefundDTO dto) {
		SoRefundPO po = SoRefundConverter.toPO(dto);
		int rt = soRefundWriteManage.updateSoRefundWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSoRefundWithTx(SoRefundDTO dto) {
		SoRefundPO po = SoRefundConverter.toPO(dto);
		int rt = soRefundWriteManage.deleteSoRefundWithTx(po);		
		return rt;
	}
}
	