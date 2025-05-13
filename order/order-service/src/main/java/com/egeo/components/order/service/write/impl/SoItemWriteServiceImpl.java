package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoItemWriteService;
import com.egeo.components.order.manage.write.SoItemWriteManage;
import com.egeo.components.order.converter.SoItemConverter;
import com.egeo.components.order.dto.SoItemDTO;

@Service("soItemWriteService")
public class SoItemWriteServiceImpl  implements SoItemWriteService {
	@Autowired
	private SoItemWriteManage soItemWriteManage;

	/**
	 * 修改订单项
	 * @param dto
	 * @return
	 */
	@Override
	public Long updateSoItemWithTx(SoItemDTO dto) {

		return soItemWriteManage.updateSoItemWithTx(SoItemConverter.toPO(dto));
	}

	@Override
	public int updateSoItemRefundWithTx(SoItemDTO dto){
		return soItemWriteManage.updateSoItemRefundWithTx(SoItemConverter.toPO(dto));
	}
}
