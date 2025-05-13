package com.egeo.components.config.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.config.po.LogDictPO;
import com.egeo.orm.BaseReadDAO;

public interface LogDictReadDAO extends BaseReadDAO<LogDictPO>{

	/**
	 * 通过3级字典id查询其所在模块的所有3级字典数据
	 * @param msgId
	 * @return
	 */
	List<LogDictPO> findLogDictAllById(@Param("id")Long msgId);
}
	