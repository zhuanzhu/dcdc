package com.egeo.components.config.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.converter.LogDictConverter;
import com.egeo.components.config.dto.LogDictDTO;
import com.egeo.components.config.manage.write.LogDictWriteManage;
import com.egeo.components.config.po.LogDictPO;
import com.egeo.components.config.service.write.LogDictWriteService;

@Service("logDictWriteService")
public class LogDictWriteServiceImpl  implements LogDictWriteService {
	@Autowired
	private LogDictWriteManage logDictWriteManage;

	@Override
	public Long insertLogDictWithTx(LogDictDTO dto) {
		LogDictPO po = LogDictConverter.toPO(dto);
		Long rt = logDictWriteManage.insertLogDictWithTx(po);		
		return rt;
	}

	@Override
	public int updateLogDictWithTx(LogDictDTO dto) {
		LogDictPO po = LogDictConverter.toPO(dto);
		int rt = logDictWriteManage.updateLogDictWithTx(po);		
		return rt;
	}

	@Override
	public int deleteLogDictWithTx(LogDictDTO dto) {
		LogDictPO po = LogDictConverter.toPO(dto);
		int rt = logDictWriteManage.deleteLogDictWithTx(po);		
		return rt;
	}
}
	