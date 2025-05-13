package com.egeo.components.order.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoChildFlowManage;
import com.egeo.components.order.facade.SoFlowFacade;
import com.egeo.components.order.dto.SoChildFlowDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("soChildFlow")
public class SoChildFlowManageImpl implements SoChildFlowManage{

	
	@Resource(name="soFlowFacade")
	private SoFlowFacade soFlowFacade;

	@Override
	public SoChildFlowDTO findSoChildFlowById(SoChildFlowDTO dto) {
		return soFlowFacade.findSoChildFlowById(dto);
	}

	@Override
	public PageResult<SoChildFlowDTO> findSoChildFlowOfPage(SoChildFlowDTO dto, Pagination page) {
		return soFlowFacade.findSoChildFlowOfPage(dto, page);
	}

	@Override
	public List<SoChildFlowDTO> findSoChildFlowAll(SoChildFlowDTO dto) {
		return soFlowFacade.findSoChildFlowAll(dto);
	}

	@Override
	public Long insertSoChildFlowWithTx(SoChildFlowDTO dto) {
		return soFlowFacade.insertSoChildFlowWithTx(dto);
	}

	@Override
	public int updateSoChildFlowWithTx(SoChildFlowDTO dto) {
		return soFlowFacade.updateSoChildFlowWithTx(dto);
	}

	@Override
	public int deleteSoChildFlowWithTx(SoChildFlowDTO dto) {
		return soFlowFacade.deleteSoChildFlowWithTx(dto);
	}


}
	