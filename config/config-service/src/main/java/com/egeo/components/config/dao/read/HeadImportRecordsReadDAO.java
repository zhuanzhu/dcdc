package com.egeo.components.config.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.config.po.HeadImportRecordsPO;
import com.egeo.orm.BaseReadDAO;

public interface HeadImportRecordsReadDAO extends BaseReadDAO<HeadImportRecordsPO>{

	/**
	 * 根据sn查询正式导入记录
	 * @param sn
	 * @return
	 */
	HeadImportRecordsPO queryRecordBySn(@Param("sn")String sn);

	/**
	 * 根据sn查询头记录列表
	 * @param sn
	 * @return
	 */
	List<HeadImportRecordsPO> queryRecordsBySn(@Param("sn")String sn);
}
	