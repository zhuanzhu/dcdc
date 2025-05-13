package com.egeo.components.finance.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.converter.AdjustReasonConverter;
import com.egeo.components.finance.dto.AdjustReasonDTO;
import com.egeo.components.finance.manage.read.AdjustReasonReadManage;
import com.egeo.components.finance.po.AdjustReasonPO;
import com.egeo.components.finance.service.read.AdjustReasonReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("adjustReasonReadService")
public class AdjustReasonReadServiceImpl  implements AdjustReasonReadService {
	@Autowired
	private AdjustReasonReadManage adjustReasonReadManage;

	@Override
	public AdjustReasonDTO findAdjustReasonById(AdjustReasonDTO dto) {
		AdjustReasonPO po = AdjustReasonConverter.toPO(dto);
		AdjustReasonPO list = adjustReasonReadManage.findAdjustReasonById(po);		
		return AdjustReasonConverter.toDTO(list);
	}

	@Override
	public PageResult<AdjustReasonDTO> findAdjustReasonOfPage(AdjustReasonDTO dto, Pagination page) {
		AdjustReasonPO po = AdjustReasonConverter.toPO(dto);
        PageResult<AdjustReasonPO> pageResult = adjustReasonReadManage.findAdjustReasonOfPage(po, page);
        
        List<AdjustReasonDTO> list = AdjustReasonConverter.toDTO(pageResult.getList());
        PageResult<AdjustReasonDTO> result = new PageResult<AdjustReasonDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<AdjustReasonDTO> findAdjustReasonAll(AdjustReasonDTO dto) {
		AdjustReasonPO po = AdjustReasonConverter.toPO(dto);
		List<AdjustReasonPO> list = adjustReasonReadManage.findAdjustReasonAll(po);		
		return AdjustReasonConverter.toDTO(list);
	}

	@Override
	public List<AdjustReasonDTO> queryAdjustReasons(Long companyId) {
		return AdjustReasonConverter.toDTO(adjustReasonReadManage.queryAdjustReasons(companyId));
	}

	@Override
	public PageResult<AdjustReasonDTO> queryAdjustReasonPage(Integer type, Long companyId, Integer disabled,
			Long platformId,Pagination page) {
		PageResult<AdjustReasonPO> poPage=adjustReasonReadManage.queryAdjustReasonPage(type, companyId, disabled,platformId, page);
		PageResult<AdjustReasonDTO> dtoPage=new PageResult<>();
		dtoPage.copy(poPage);
		dtoPage.setList(AdjustReasonConverter.toDTO(poPage.getList()));
		return dtoPage;
	}

	@Override
	public List<AdjustReasonDTO> queryAdjustReasonsByTypes(Long platformId, List<Integer> typeList, Long accountId, Long companyId) {
		return AdjustReasonConverter.toDTO(adjustReasonReadManage.queryAdjustReasonsByTypes(platformId,typeList, accountId,companyId));
	}

}
	