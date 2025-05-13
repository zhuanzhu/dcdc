package com.egeo.components.config.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.converter.LogConverter;
import com.egeo.components.config.dto.LogDTO;
import com.egeo.components.config.manage.write.LogWriteManage;
import com.egeo.components.config.po.LogPO;
import com.egeo.components.config.service.write.LogWriteService;

@Service("logWriteService")
public class LogWriteServiceImpl implements LogWriteService {
	@Autowired
	private LogWriteManage logWriteManage;

	@Override
	public Long insertLogWithTx(LogDTO dto) {
		LogPO po = LogConverter.toPO(dto);
		Long rt = logWriteManage.insertLogWithTx(po);		
		return rt;
	}

	@Override
	public int updateLogWithTx(LogDTO dto) {
		LogPO po = LogConverter.toPO(dto);
		int rt = logWriteManage.updateLogWithTx(po);		
		return rt;
	}

	@Override
	public int deleteLogWithTx(LogDTO dto) {
		LogPO po = LogConverter.toPO(dto);
		int rt = logWriteManage.deleteLogWithTx(po);		
		return rt;
	}
}
	