package com.egeo.components.config.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.config.condition.LogCondition;
import com.egeo.components.config.po.LogPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface LogReadDAO extends BaseReadDAO<LogPO>{

	/**
	 * 查询日志列表
	 * @param po
	 * @return
	 */
	int countLogInfoOfPage(@Param("po")LogCondition po);

	List<LogCondition> findLogInfoOfPage(@Param("po")LogCondition po, @Param("page")Pagination page);
}
	