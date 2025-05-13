package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.PayTypeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface PayTypeReadService {

	public PayTypeDTO findPayTypeById(PayTypeDTO dto);

	public PageResult<PayTypeDTO> findPayTypeOfPage(PayTypeDTO dto,Pagination page);

	public List<PayTypeDTO> findPayTypeAll(PayTypeDTO dto);
	public PayTypeDTO findPayTypeByCode(Integer code);
	public List<PayTypeDTO> findPayTypeByCodes(List<Integer> codes);
}
	