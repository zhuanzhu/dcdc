package com.egeo.components.finance.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.converter.SoFreezeFubiConverter;
import com.egeo.components.finance.dto.SoFreezeFubiDTO;
import com.egeo.components.finance.manage.write.SoFreezeFubiWriteManage;
import com.egeo.components.finance.po.SoFreezeFubiPO;
import com.egeo.components.finance.service.write.SoFreezeFubiWriteService;

@Service("soFreezeFubiWriteService")
public class SoFreezeFubiWriteServiceImpl  implements SoFreezeFubiWriteService {
	@Autowired
	private SoFreezeFubiWriteManage soFreezeFubiWriteManage;

	@Override
	public Long insertSoFreezeFubiWithTx(SoFreezeFubiDTO dto) {
		SoFreezeFubiPO po = SoFreezeFubiConverter.toPO(dto);
		Long rt = soFreezeFubiWriteManage.insertSoFreezeFubiWithTx(po);		
		return rt;
	}

	@Override
	public int updateSoFreezeFubiWithTx(SoFreezeFubiDTO dto) {
		SoFreezeFubiPO po = SoFreezeFubiConverter.toPO(dto);
		int rt = soFreezeFubiWriteManage.updateSoFreezeFubiWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSoFreezeFubiWithTx(SoFreezeFubiDTO dto) {
		SoFreezeFubiPO po = SoFreezeFubiConverter.toPO(dto);
		int rt = soFreezeFubiWriteManage.deleteSoFreezeFubiWithTx(po);		
		return rt;
	}
	/**
	 * 根据订单id删除订单冻结积分
	 * @param soId
	 * @return
	 */
	@Override
	public int delBySoId(Long soId) {
		// TODO Auto-generated method stub
		return soFreezeFubiWriteManage.delBySoId(soId);
	}
}
	