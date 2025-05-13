package com.egeo.components.promotion.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.ErCardRecordManage;
import com.egeo.components.promotion.facade.ErCardRecordFacade;
import com.egeo.components.promotion.dto.ErCardRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("erCardRecord")
public class ErCardRecordManageImpl implements ErCardRecordManage{

	
	@Resource(name="erCardRecordFacade")
	private ErCardRecordFacade erCardRecordFacade;

	@Override
	public ErCardRecordDTO findErCardRecordById(ErCardRecordDTO dto) {
		return erCardRecordFacade.findErCardRecordById(dto);
	}

	@Override
	public PageResult<ErCardRecordDTO> findErCardRecordOfPage(ErCardRecordDTO dto, Pagination page) {
		return erCardRecordFacade.findErCardRecordOfPage(dto, page);
	}

	@Override
	public List<ErCardRecordDTO> findErCardRecordAll(ErCardRecordDTO dto) {
		return erCardRecordFacade.findErCardRecordAll(dto);
	}

	@Override
	public Long insertErCardRecordWithTx(ErCardRecordDTO dto) {
		return erCardRecordFacade.insertErCardRecordWithTx(dto);
	}

	@Override
	public int updateErCardRecordWithTx(ErCardRecordDTO dto) {
		return erCardRecordFacade.updateErCardRecordWithTx(dto);
	}

	@Override
	public int deleteErCardRecordWithTx(ErCardRecordDTO dto) {
		return erCardRecordFacade.deleteErCardRecordWithTx(dto);
	}
	/**
	 * 确认导入
	 * @return
	 */
	@Override
	public int confirmTheImport(ErCardRecordDTO dto,Integer addStock) {
		// TODO Auto-generated method stub
		return erCardRecordFacade.confirmTheImport(dto,addStock);
	}


}
	