package com.egeo.components.config.manage.read;

import java.util.List;

import com.egeo.components.config.po.LogOperCodePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface LogOperCodeReadManage {

	public LogOperCodePO findLogOperCodeById(LogOperCodePO po);

	public PageResult<LogOperCodePO> findLogOperCodeOfPage(LogOperCodePO po,Pagination page);

	public List<LogOperCodePO> findLogOperCodeAll(LogOperCodePO po);
}
	