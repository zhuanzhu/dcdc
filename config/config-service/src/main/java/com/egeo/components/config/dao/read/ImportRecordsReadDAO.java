package com.egeo.components.config.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.config.po.ImportRecordsPO;
import com.egeo.orm.BaseReadDAO;

public interface ImportRecordsReadDAO extends BaseReadDAO<ImportRecordsPO>{

	
	/**
	 * 查询头信息草稿
	 * @param sn
	 * @return
	 */
	ImportRecordsPO queryRecordTempBySn(@Param("sn")String sn);

}
	