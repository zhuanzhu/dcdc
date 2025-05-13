package com.egeo.components.order.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.order.dto.SoDeliveryDTO;
import com.egeo.components.order.dto.SoPackageDTO;
import com.egeo.components.order.vo.Steps;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;	

public interface SoDeliveryManage {

	public SoDeliveryDTO findSoDeliveryById(SoDeliveryDTO dto);	

	public PageResult<SoDeliveryDTO> findSoDeliveryOfPage(SoDeliveryDTO dto,Pagination page);

	public List<SoDeliveryDTO> findSoDeliveryAll(SoDeliveryDTO dto);

	int insertSoDeliveryWithTx(SoDeliveryDTO dto);

	int updateSoDeliveryWithTx(SoDeliveryDTO dto);

	int deleteSoDeliveryWithTx(SoDeliveryDTO dto);

	public List<Steps> openDeliveryTrace(String waybillNum, String shipCompanyCode);
	public List<Steps> openDeliveryTrace(SoPackageDTO pkg, String shipCompanyCode);

	/**
	 * 开放物流信息查询(web端)
	 * @param orderCode
	 * @return
	 */
	public JsonResult<Map<String, Object>> queryWebOpenDeliveryTrace(String orderCode);
}
	