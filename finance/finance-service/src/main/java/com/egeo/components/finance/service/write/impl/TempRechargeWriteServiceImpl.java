package com.egeo.components.finance.service.write.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.converter.CompanyAccountConverter;
import com.egeo.components.finance.converter.TempRechargeConverter;
import com.egeo.components.finance.dto.CompanyAccountDTO;
import com.egeo.components.finance.dto.TempRechargeDTO;
import com.egeo.components.finance.manage.write.TempRechargeWriteManage;
import com.egeo.components.finance.po.TempRechargePO;
import com.egeo.components.finance.service.write.TempRechargeWriteService;

@Service("tempRechargeWriteService")
public class TempRechargeWriteServiceImpl  implements TempRechargeWriteService {
	@Autowired
	private TempRechargeWriteManage tempRechargeWriteManage;

	@Override
	public Long insertTempRechargeWithTx(TempRechargeDTO dto) {
		TempRechargePO po = TempRechargeConverter.toPO(dto);
		Long rt = tempRechargeWriteManage.insertTempRechargeWithTx(po);		
		return rt;
	}

	@Override
	public int updateTempRechargeWithTx(TempRechargeDTO dto) {
		TempRechargePO po = TempRechargeConverter.toPO(dto);
		int rt = tempRechargeWriteManage.updateTempRechargeWithTx(po);		
		return rt;
	}

	@Override
	public int deleteTempRechargeWithTx(TempRechargeDTO dto) {
		TempRechargePO po = TempRechargeConverter.toPO(dto);
		int rt = tempRechargeWriteManage.deleteTempRechargeWithTx(po);		
		return rt;
	}

	@Override
	public int batchInsertTempRecharge(List<TempRechargeDTO> batchInsertList) {
		return tempRechargeWriteManage.batchInsertTempRecharge(TempRechargeConverter.toPO(batchInsertList));
	}

	@Override
	public int ca2uaBatchUpdate(List<TempRechargeDTO> trList, 
			String companySalt,
			CompanyAccountDTO ca,
			BigDecimal summary,
			String batchNo,
			String finBatch,
			Long operatorId,
			Long reasonId,
			String remark,
			Integer accountType) {
		
		return tempRechargeWriteManage.ca2uaBatchUpdate(
				TempRechargeConverter.toPO(trList), 
				companySalt, 
				CompanyAccountConverter.toPO(ca), summary, batchNo, finBatch, operatorId, reasonId, remark, accountType);
	}

	@Override
	public int updateTempRechargeStatus(String sn, int status) {
		return tempRechargeWriteManage.updateTempRechargeStatus(sn,status);
	}

	@Override
	public int deleteTempRechargeBySn(String sn) {
		return tempRechargeWriteManage.deleteTempRechargeBySnWithTx(sn);
	}
}
	