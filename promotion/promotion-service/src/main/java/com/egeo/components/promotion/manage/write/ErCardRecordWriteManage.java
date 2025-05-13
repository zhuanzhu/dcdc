package com.egeo.components.promotion.manage.write;

import java.util.List;

import com.egeo.components.promotion.po.CardBatchPO;
import com.egeo.components.promotion.po.ErCardRecordPO;


public interface ErCardRecordWriteManage {

	Long insertErCardRecordWithTx(ErCardRecordPO po);

	int updateErCardRecordWithTx(ErCardRecordPO po);

	int deleteErCardRecordWithTx(ErCardRecordPO po);
	/**
	 * 根据记录id删除unit
	 * @param dto
	 * @return
	 */
	int findErCardRecordByImportRecordsId(ErCardRecordPO po);
	/**
	 * 批量导入卡密数据
	 * @param erCardRecordPOs
	 * @return
	 */
	int insertErCardRecordsWithTx(
			CardBatchPO cardBatchPO,
			List<ErCardRecordPO> erCardRecords);
}
	