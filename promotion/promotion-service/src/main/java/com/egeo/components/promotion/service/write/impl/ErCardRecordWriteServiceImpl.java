package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.ErCardRecordWriteService;
import com.egeo.components.promotion.manage.write.ErCardRecordWriteManage;
import com.egeo.components.promotion.converter.ErCardRecordConverter;
import com.egeo.components.promotion.dto.ErCardRecordDTO;
import com.egeo.components.promotion.po.ErCardRecordPO;

@Service("erCardRecordWriteService")
public class ErCardRecordWriteServiceImpl implements ErCardRecordWriteService {
	@Autowired
	private ErCardRecordWriteManage erCardRecordWriteManage;

	@Override
	public Long insertErCardRecordWithTx(ErCardRecordDTO dto) {
		ErCardRecordPO po = ErCardRecordConverter.toPO(dto);
		Long rt = erCardRecordWriteManage.insertErCardRecordWithTx(po);		
		return rt;
	}

	@Override
	public int updateErCardRecordWithTx(ErCardRecordDTO dto) {
		ErCardRecordPO po = ErCardRecordConverter.toPO(dto);
		int rt = erCardRecordWriteManage.updateErCardRecordWithTx(po);		
		return rt;
	}

	@Override
	public int deleteErCardRecordWithTx(ErCardRecordDTO dto) {
		ErCardRecordPO po = ErCardRecordConverter.toPO(dto);
		int rt = erCardRecordWriteManage.deleteErCardRecordWithTx(po);		
		return rt;
	}
	/**
	 * 根据记录id删除unit
	 * @param dto
	 * @return
	 */
	@Override
	public int findErCardRecordByImportRecordsId(ErCardRecordDTO dto) {
		// TODO Auto-generated method stub
		return erCardRecordWriteManage.findErCardRecordByImportRecordsId(ErCardRecordConverter.toPO(dto));
	}
}
	