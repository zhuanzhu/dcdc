package com.egeo.components.config.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.converter.LogOperCodeConverter;
import com.egeo.components.config.dto.LogOperCodeDTO;
import com.egeo.components.config.manage.write.LogOperCodeWriteManage;
import com.egeo.components.config.po.LogOperCodePO;
import com.egeo.components.config.service.write.LogOperCodeWriteService;

@Service("logOperCodeWriteService")
public class LogOperCodeWriteServiceImpl implements LogOperCodeWriteService {
	@Autowired
	private LogOperCodeWriteManage logOperCodeWriteManage;

	@Override
	public Long insertLogOperCodeWithTx(LogOperCodeDTO dto) {
		LogOperCodePO po = LogOperCodeConverter.toPO(dto);
		Long rt = logOperCodeWriteManage.insertLogOperCodeWithTx(po);		
		return rt;
	}

	@Override
	public int updateLogOperCodeWithTx(LogOperCodeDTO dto) {
		LogOperCodePO po = LogOperCodeConverter.toPO(dto);
		int rt = logOperCodeWriteManage.updateLogOperCodeWithTx(po);		
		return rt;
	}

	@Override
	public int deleteLogOperCodeWithTx(LogOperCodeDTO dto) {
		LogOperCodePO po = LogOperCodeConverter.toPO(dto);
		int rt = logOperCodeWriteManage.deleteLogOperCodeWithTx(po);		
		return rt;
	}
}
	