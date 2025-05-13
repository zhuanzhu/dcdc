package com.egeo.components.promotion.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.dao.write.CardBatchWriteDAO;
import com.egeo.components.promotion.dao.write.ErCardRecordWriteDAO;
import com.egeo.components.promotion.manage.write.ErCardRecordWriteManage;
import com.egeo.components.promotion.po.CardBatchPO;
import com.egeo.components.promotion.po.ErCardRecordPO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

@Service
public class ErCardRecordWriteManageImpl implements ErCardRecordWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ErCardRecordWriteDAO erCardRecordWriteDAO;
	
	@Autowired
	private CardBatchWriteDAO cardBatchWriteDAO;

	@Override
	public Long insertErCardRecordWithTx(ErCardRecordPO po) {
		
		int i ;
		try {
				i = erCardRecordWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateErCardRecordWithTx(ErCardRecordPO po) {
		int i;
		i = erCardRecordWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteErCardRecordWithTx(ErCardRecordPO po) {
		int i;
		i = erCardRecordWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据记录id删除unit
	 * @param dto
	 * @return
	 */
	@Override
	public int findErCardRecordByImportRecordsId(ErCardRecordPO po) {
		int i;
		i = erCardRecordWriteDAO.deleteByPara(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int insertErCardRecordsWithTx(
			CardBatchPO cardBatchPO,
			List<ErCardRecordPO> erCardRecords) {
		cardBatchWriteDAO.insert(cardBatchPO);
		
		// 赋值批次id
		for (ErCardRecordPO erCardRecordPO : erCardRecords) {
			erCardRecordPO.setBatch(cardBatchPO.getId());
		}
		// 批量添加卡密信息
		if(EmptyUtil.isNotEmpty(erCardRecords))
			return erCardRecordWriteDAO.insertAll(erCardRecords);
		return erCardRecords.size();
	}	
}
	