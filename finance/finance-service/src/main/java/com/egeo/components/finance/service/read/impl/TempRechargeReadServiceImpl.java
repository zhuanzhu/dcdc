package com.egeo.components.finance.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.converter.TempRechargeConverter;
import com.egeo.components.finance.dto.TempRechargeDTO;
import com.egeo.components.finance.manage.read.TempRechargeReadManage;
import com.egeo.components.finance.po.TempRechargePO;
import com.egeo.components.finance.service.read.TempRechargeReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("tempRechargeReadService")
public class TempRechargeReadServiceImpl  implements TempRechargeReadService {
	@Autowired
	private TempRechargeReadManage tempRechargeReadManage;

	@Override
	public TempRechargeDTO findTempRechargeById(TempRechargeDTO dto) {
		TempRechargePO po = TempRechargeConverter.toPO(dto);
		TempRechargePO list = tempRechargeReadManage.findTempRechargeById(po);		
		return TempRechargeConverter.toDTO(list);
	}

	@Override
	public PageResult<TempRechargeDTO> findTempRechargeOfPage(TempRechargeDTO dto, Pagination page) {
		TempRechargePO po = TempRechargeConverter.toPO(dto);
        PageResult<TempRechargePO> pageResult = tempRechargeReadManage.findTempRechargeOfPage(po, page);
        
        List<TempRechargeDTO> list = TempRechargeConverter.toDTO(pageResult.getList());
        PageResult<TempRechargeDTO> result = new PageResult<TempRechargeDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<TempRechargeDTO> findTempRechargeAll(TempRechargeDTO dto) {
		TempRechargePO po = TempRechargeConverter.toPO(dto);
		List<TempRechargePO> list = tempRechargeReadManage.findTempRechargeAll(po);		
		return TempRechargeConverter.toDTO(list);
	}

	@Override
	public double queryTempRechargeSummaryBySn(String sn) {
		return tempRechargeReadManage.queryTempRechargeSummaryBySn(sn);
	}

	@Override
	public List<TempRechargeDTO> queryTempRechargeBySn(String sn) {
		return TempRechargeConverter.toDTO(tempRechargeReadManage.queryTempRechargeBySn(sn));
	}

	@Override
	public int queryTempRechargeCountBySn(String sn) {
		// TODO Auto-generated method stub
		return tempRechargeReadManage.queryTempRechargeCountBySn(sn);
	}
}
	