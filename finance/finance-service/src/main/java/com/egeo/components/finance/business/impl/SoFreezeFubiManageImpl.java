package com.egeo.components.finance.business.impl;
	

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.finance.business.SoFreezeFubiManage;
import com.egeo.components.finance.dto.SoFreezeFubiDTO;
import com.egeo.components.finance.facade.SoFreezeFubiFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("soFreezeFubi")
public class SoFreezeFubiManageImpl implements SoFreezeFubiManage{

	
	@Resource(name="soFreezeFubiFacade")
	private SoFreezeFubiFacade soFreezeFubiFacade;

	@Override
	public SoFreezeFubiDTO findSoFreezeFubiById(SoFreezeFubiDTO dto) {
		return soFreezeFubiFacade.findSoFreezeFubiById(dto);
	}

	@Override
	public PageResult<SoFreezeFubiDTO> findSoFreezeFubiOfPage(SoFreezeFubiDTO dto, Pagination page) {
		return soFreezeFubiFacade.findSoFreezeFubiOfPage(dto, page);
	}

	@Override
	public List<SoFreezeFubiDTO> findSoFreezeFubiAll(SoFreezeFubiDTO dto) {
		return soFreezeFubiFacade.findSoFreezeFubiAll(dto);
	}

	@Override
	public Long insertSoFreezeFubiWithTx(SoFreezeFubiDTO dto) {
		return soFreezeFubiFacade.insertSoFreezeFubiWithTx(dto);
	}

	@Override
	public int updateSoFreezeFubiWithTx(SoFreezeFubiDTO dto) {
		return soFreezeFubiFacade.updateSoFreezeFubiWithTx(dto);
	}

	@Override
	public int deleteSoFreezeFubiWithTx(SoFreezeFubiDTO dto) {
		return soFreezeFubiFacade.deleteSoFreezeFubiWithTx(dto);
	}
	/**
	 * 根据订单id查询订单冻结积分
	 * @param soId
	 * @return
	 */
	@Override
	public BigDecimal findSoFreezeBalanceBySoId(Long soId) {
		// TODO Auto-generated method stub
		return soFreezeFubiFacade.findSoFreezeBalanceBySoId(soId);
	}


}
	