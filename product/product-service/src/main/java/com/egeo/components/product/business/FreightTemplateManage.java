package com.egeo.components.product.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.product.dto.FreightRegulationDTO;
import com.egeo.components.product.dto.FreightTemplateDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface FreightTemplateManage {
	/**
	 * 根据运费模版Id查询运费模版信息
	 * @param freightTemplateId
	 * @return
	 */
	public Map<String, Object> findFreightTemplateById(Long freightTemplateId);	

	public PageResult<Map<String, Object>> findFreightTemplateOfPage(FreightTemplateDTO dto,Pagination page);

	public List<FreightTemplateDTO> findFreightTemplateAll(FreightTemplateDTO dto);
	/**
	 * 新增运费模版
	 * @param dto
	 * @param freightRegulationList
	 * @return
	 */
	Long insertFreightTemplateWithTx(FreightTemplateDTO dto,List<FreightRegulationDTO> freightRegulationList);
	/**
	 * 根据运费模版id修改运费模版
	 * @param vo
	 * @param freightRegulations
	 * @param req
	 * @return
	 */
	int updateFreightTemplateWithTx(FreightTemplateDTO dto,List<FreightRegulationDTO> freightRegulationList);

	int deleteFreightTemplateWithTx(FreightTemplateDTO dto);
	/**
	 * 根据运费模版id启用运费模版
	 * @param freightTemplateId
	 * @return
	 */
	public int startFreightTemplateWithTx(Long freightTemplateId);
	/**
	 * 根据商家Id查询运费模版信息
	 * @param freightTemplateId
	 * @return
	 */
	public Map<String, Object> freightTemplateByMerchantId(Long merchantId,Long storeId,Long platformId);
}
	