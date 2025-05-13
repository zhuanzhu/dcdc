package com.egeo.components.order.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.order.dto.SoRefundDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SoRefundManage {

	public SoRefundDTO findSoRefundById(SoRefundDTO dto);	

	public PageResult<Map<String,Object>> findSoRefundOfPage(String soRefundCode, String orderCode,
			String mail,Integer refundState, Long platformId, Pagination page);

	public List<SoRefundDTO> findSoRefundAll(SoRefundDTO dto);

	Long insertSoRefundWithTx(SoRefundDTO dto);

	int updateSoRefundWithTx(SoRefundDTO dto);

	int deleteSoRefundWithTx(SoRefundDTO dto);
}
	