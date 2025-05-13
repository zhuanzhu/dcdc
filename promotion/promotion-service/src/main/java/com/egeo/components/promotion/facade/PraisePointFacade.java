package com.egeo.components.promotion.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.PraisePointReadService;
import com.egeo.components.promotion.service.write.PraisePointWriteService;
import com.egeo.components.promotion.dto.PraisePointDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class PraisePointFacade {
	
	@Autowired
	private PraisePointReadService praisePointReadService;
	
	@Autowired
	private PraisePointWriteService praisePointWriteService;
	
	



	public Long insertPraisePointWithTx(PraisePointDTO dto){
		
		return praisePointWriteService.insertPraisePointWithTx(dto);
	}

	public int updatePraisePointWithTx(PraisePointDTO dto){
		
		return praisePointWriteService.updatePraisePointWithTx(dto);
	}

	public int deletePraisePointWithTx(PraisePointDTO dto){
		
		return praisePointWriteService.deletePraisePointWithTx(dto);
		
	}

}
	