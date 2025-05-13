package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.InsuranceLoginConverter;
import com.egeo.components.user.dto.InsuranceLoginDTO;
import com.egeo.components.user.manage.write.InsuranceLoginWriteManage;
import com.egeo.components.user.po.InsuranceLoginPO;
import com.egeo.components.user.service.write.InsuranceLoginWriteService;

@Service("insuranceLoginWriteService")
public class InsuranceLoginWriteServiceImpl implements InsuranceLoginWriteService {
	@Autowired
	private InsuranceLoginWriteManage insuranceLoginWriteManage;

	@Override
	public Long insertInsuranceLoginWithTx(InsuranceLoginDTO dto) {
		InsuranceLoginPO po = InsuranceLoginConverter.toPO(dto);
		Long rt = insuranceLoginWriteManage.insertInsuranceLoginWithTx(po);		
		return rt;
	}

	@Override
	public int updateInsuranceLoginWithTx(InsuranceLoginDTO dto) {
		InsuranceLoginPO po = InsuranceLoginConverter.toPO(dto);
		int rt = insuranceLoginWriteManage.updateInsuranceLoginWithTx(po);		
		return rt;
	}

	@Override
	public int deleteInsuranceLoginWithTx(InsuranceLoginDTO dto) {
		InsuranceLoginPO po = InsuranceLoginConverter.toPO(dto);
		int rt = insuranceLoginWriteManage.deleteInsuranceLoginWithTx(po);		
		return rt;
	}

	@Override
	public int insuranceLogin(InsuranceLoginDTO il) {
		
		return insuranceLoginWriteManage.insuranceLoginWithTx(InsuranceLoginConverter.toPO(il));
	}

}
	