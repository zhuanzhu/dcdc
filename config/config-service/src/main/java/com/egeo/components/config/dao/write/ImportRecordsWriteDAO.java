package com.egeo.components.config.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.config.po.ImportRecordsPO;
import com.egeo.orm.BaseWriteDAO;

public interface ImportRecordsWriteDAO extends BaseWriteDAO<ImportRecordsPO> {

	/**
	 * 根据sn删除头文件草稿
	 * @param sn
	 * @return
	 */
	int deleteBySn(@Param("sn")String sn);
}
	