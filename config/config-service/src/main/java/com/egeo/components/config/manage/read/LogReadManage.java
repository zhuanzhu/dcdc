package com.egeo.components.config.manage.read;

import java.util.List;

import com.egeo.components.config.condition.LogCondition;
import com.egeo.components.config.po.LogPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface LogReadManage {

	public LogPO findLogById(LogPO po);

	public PageResult<LogPO> findLogOfPage(LogPO po,Pagination page);

	public List<LogPO> findLogAll(LogPO po);

	/**
	 * 日志列表
	 * @param po
	 * @return
	 */
	public PageResult<LogCondition> findLogInfoOfPage(LogCondition po,Pagination page);
}
	