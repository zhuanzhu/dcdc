package com.egeo.components.pay.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.pay.converter.AlipayNativeSignLogConverter;
import com.egeo.components.pay.dto.AlipayNativeSignLogDTO;
import com.egeo.components.pay.manage.read.AlipayNativeSignLogReadManage;
import com.egeo.components.pay.po.AlipayNativeSignLogPO;
import com.egeo.components.pay.service.read.AlipayNativeSignLogReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("alipayNativeSignLogReadService")
public class AlipayNativeSignLogReadServiceImpl  implements AlipayNativeSignLogReadService {
	@Autowired
	private AlipayNativeSignLogReadManage alipayNativeSignLogReadManage;

	@Override
	public AlipayNativeSignLogDTO findAlipayNativeSignLogById(AlipayNativeSignLogDTO dto) {
		AlipayNativeSignLogPO po = AlipayNativeSignLogConverter.toPO(dto);
		AlipayNativeSignLogPO list = alipayNativeSignLogReadManage.findAlipayNativeSignLogById(po);		
		return AlipayNativeSignLogConverter.toDTO(list);
	}

	@Override
	public PageResult<AlipayNativeSignLogDTO> findAlipayNativeSignLogOfPage(AlipayNativeSignLogDTO dto, Pagination page) {
		AlipayNativeSignLogPO po = AlipayNativeSignLogConverter.toPO(dto);
        PageResult<AlipayNativeSignLogPO> pageResult = alipayNativeSignLogReadManage.findAlipayNativeSignLogOfPage(po, page);
        
        List<AlipayNativeSignLogDTO> list = AlipayNativeSignLogConverter.toDTO(pageResult.getList());
        PageResult<AlipayNativeSignLogDTO> result = new PageResult<AlipayNativeSignLogDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<AlipayNativeSignLogDTO> findAlipayNativeSignLogAll(AlipayNativeSignLogDTO dto) {
		AlipayNativeSignLogPO po = AlipayNativeSignLogConverter.toPO(dto);
		List<AlipayNativeSignLogPO> list = alipayNativeSignLogReadManage.findAlipayNativeSignLogAll(po);		
		return AlipayNativeSignLogConverter.toDTO(list);
	}
}
	