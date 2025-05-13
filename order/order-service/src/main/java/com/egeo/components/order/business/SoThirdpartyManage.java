package com.egeo.components.order.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.order.dto.SoThirdpartyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;	

public interface SoThirdpartyManage {

	public SoThirdpartyDTO findSoThirdpartyById(SoThirdpartyDTO dto);	

	public PageResult<SoThirdpartyDTO> findSoThirdpartyOfPage(SoThirdpartyDTO dto,Pagination page);

	public List<SoThirdpartyDTO> findSoThirdpartyAll(SoThirdpartyDTO dto);

	Long insertSoThirdpartyWithTx(SoThirdpartyDTO dto);

	int updateSoThirdpartyWithTx(SoThirdpartyDTO dto);

	int deleteSoThirdpartyWithTx(SoThirdpartyDTO dto);

	/**
     * 通过订单id查询第三方订单列表
     * @param soId
     * @return
     */
	public JsonResult<List<Map<String, Object>>> findSoThirdpartyBySoId(Long soId);
}
	