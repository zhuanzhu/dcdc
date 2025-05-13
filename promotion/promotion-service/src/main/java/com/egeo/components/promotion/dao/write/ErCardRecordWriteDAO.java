package com.egeo.components.promotion.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.promotion.po.ErCardRecordPO;
import com.egeo.orm.BaseWriteDAO;

public interface ErCardRecordWriteDAO extends BaseWriteDAO<ErCardRecordPO> {
	/**
	 * 批量导入卡密数据
	 * @param erCardRecordPOs
	 * @return
	 */
	int insertAll(@Param("poList")List<ErCardRecordPO> erCardRecordPOs);
}
	