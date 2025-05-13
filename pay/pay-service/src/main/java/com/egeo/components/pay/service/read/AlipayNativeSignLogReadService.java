package com.egeo.components.pay.service.read;


import java.util.List;

import com.egeo.components.pay.dto.AlipayNativeSignLogDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface AlipayNativeSignLogReadService {

	public AlipayNativeSignLogDTO findAlipayNativeSignLogById(AlipayNativeSignLogDTO dto);

	public PageResult<AlipayNativeSignLogDTO> findAlipayNativeSignLogOfPage(AlipayNativeSignLogDTO dto,Pagination page);

	public List<AlipayNativeSignLogDTO> findAlipayNativeSignLogAll(AlipayNativeSignLogDTO dto);
}
	