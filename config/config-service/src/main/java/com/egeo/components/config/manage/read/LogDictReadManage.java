package com.egeo.components.config.manage.read;

import java.util.List;

import com.egeo.components.config.po.LogDictPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface LogDictReadManage {

	public LogDictPO findLogDictById(LogDictPO po);

	public PageResult<LogDictPO> findLogDictOfPage(LogDictPO po,Pagination page);

	public List<LogDictPO> findLogDictAll(LogDictPO po);

	public List<LogDictPO> findLogDictAllById(Long msgId);
}
	