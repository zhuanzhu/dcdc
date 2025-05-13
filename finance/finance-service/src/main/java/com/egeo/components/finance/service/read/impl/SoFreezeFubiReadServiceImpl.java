package com.egeo.components.finance.service.read.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.converter.SoFreezeFubiConverter;
import com.egeo.components.finance.dto.SoFreezeFubiDTO;
import com.egeo.components.finance.manage.read.SoFreezeFubiReadManage;
import com.egeo.components.finance.po.SoFreezeFubiPO;
import com.egeo.components.finance.service.read.SoFreezeFubiReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("soFreezeFubiReadService")
public class SoFreezeFubiReadServiceImpl  implements SoFreezeFubiReadService {
	@Autowired
	private SoFreezeFubiReadManage soFreezeFubiReadManage;

	@Override
	public SoFreezeFubiDTO findSoFreezeFubiById(SoFreezeFubiDTO dto) {
		SoFreezeFubiPO po = SoFreezeFubiConverter.toPO(dto);
		SoFreezeFubiPO list = soFreezeFubiReadManage.findSoFreezeFubiById(po);		
		return SoFreezeFubiConverter.toDTO(list);
	}

	@Override
	public PageResult<SoFreezeFubiDTO> findSoFreezeFubiOfPage(SoFreezeFubiDTO dto, Pagination page) {
		SoFreezeFubiPO po = SoFreezeFubiConverter.toPO(dto);
        PageResult<SoFreezeFubiPO> pageResult = soFreezeFubiReadManage.findSoFreezeFubiOfPage(po, page);
        
        List<SoFreezeFubiDTO> list = SoFreezeFubiConverter.toDTO(pageResult.getList());
        PageResult<SoFreezeFubiDTO> result = new PageResult<SoFreezeFubiDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SoFreezeFubiDTO> findSoFreezeFubiAll(SoFreezeFubiDTO dto) {
		SoFreezeFubiPO po = SoFreezeFubiConverter.toPO(dto);
		List<SoFreezeFubiPO> list = soFreezeFubiReadManage.findSoFreezeFubiAll(po);		
		return SoFreezeFubiConverter.toDTO(list);
	}
	/**
	 * 根据订单id查询订单冻结积分
	 * @param soId
	 * @return
	 */
	@Override
	public BigDecimal findSoFreezeBalanceBySoId(Long soId) {
		return soFreezeFubiReadManage.findSoFreezeBalanceBySoId(soId);
	}
}
	