package com.egeo.components.config.manage.read;

import java.util.List;

import com.egeo.components.config.po.HeadImportRecordsPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface HeadImportRecordsReadManage {

	public HeadImportRecordsPO findHeadImportRecordsById(HeadImportRecordsPO po);

	public PageResult<HeadImportRecordsPO> findHeadImportRecordsOfPage(HeadImportRecordsPO po,Pagination page);

	public List<HeadImportRecordsPO> findHeadImportRecordsAll(HeadImportRecordsPO po);

	/**
	 * 根据sn查询正式导入记录
	 * @param sn
	 * @return
	 */
	public HeadImportRecordsPO queryRecordBySn(String sn);

	/**
	 * 根据sn查询头记录列表
	 * @param sn
	 * @return
	 */
	public List<HeadImportRecordsPO> queryRecordsBySn(String sn);
}
	