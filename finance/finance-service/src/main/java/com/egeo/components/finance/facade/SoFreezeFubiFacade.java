package com.egeo.components.finance.facade;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.finance.dto.SoFreezeFubiDTO;
import com.egeo.components.finance.service.read.SoFreezeFubiReadService;
import com.egeo.components.finance.service.write.SoFreezeFubiWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class SoFreezeFubiFacade {
	
	@Autowired
	private SoFreezeFubiReadService soFreezeFubiReadService;
	
	@Autowired
	private SoFreezeFubiWriteService soFreezeFubiWriteService;
	
	
	public SoFreezeFubiDTO findSoFreezeFubiById(SoFreezeFubiDTO dto){
		
		return soFreezeFubiReadService.findSoFreezeFubiById(dto);
	}

	public PageResult<SoFreezeFubiDTO> findSoFreezeFubiOfPage(SoFreezeFubiDTO dto,Pagination page){
		
		return soFreezeFubiReadService.findSoFreezeFubiOfPage(dto, page);
		
	}

	public List<SoFreezeFubiDTO> findSoFreezeFubiAll(SoFreezeFubiDTO dto){
		
		return soFreezeFubiReadService.findSoFreezeFubiAll(dto);
		
	}

	public Long insertSoFreezeFubiWithTx(SoFreezeFubiDTO dto){
		
		return soFreezeFubiWriteService.insertSoFreezeFubiWithTx(dto);
	}

	public int updateSoFreezeFubiWithTx(SoFreezeFubiDTO dto){
		
		return soFreezeFubiWriteService.updateSoFreezeFubiWithTx(dto);
	}

	public int deleteSoFreezeFubiWithTx(SoFreezeFubiDTO dto){
		
		return soFreezeFubiWriteService.deleteSoFreezeFubiWithTx(dto);
		
	}
	/**
	 * 根据订单id查询订单冻结积分
	 * @param soId
	 * @return
	 */
	public BigDecimal findSoFreezeBalanceBySoId(Long soId) {
		// TODO Auto-generated method stub
		return soFreezeFubiReadService.findSoFreezeBalanceBySoId(soId);
	}

}
	