package com.egeo.components.promotion.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.PraisePointManage;
import com.egeo.components.promotion.facade.PraisePointFacade;
import com.egeo.components.promotion.dto.PraisePointDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("praisePoint")
public class PraisePointManageImpl implements PraisePointManage{

	
	@Resource(name="praisePointFacade")
	private PraisePointFacade praisePointFacade;




	@Override
	public Long insertPraisePointWithTx(PraisePointDTO dto) {
		return praisePointFacade.insertPraisePointWithTx(dto);
	}

	@Override
	public int updatePraisePointWithTx(PraisePointDTO dto) {
		return praisePointFacade.updatePraisePointWithTx(dto);
	}

	@Override
	public int deletePraisePointWithTx(PraisePointDTO dto) {
		return praisePointFacade.deletePraisePointWithTx(dto);
	}


}
	