package com.egeo.components.product.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.product.vo.SkuVO;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;

public interface SkuManage {

	public SkuDTO findSkuById(SkuDTO dto);	

	public PageResult<SkuDTO> findSkuOfPage(SkuDTO dto,Pagination page);

	public List<SkuDTO> findSkuAll(SkuDTO dto);

	Long insertSkuWithTx(SkuDTO dto);

	int updateSkuWithTx(SkuDTO dto);

	int deleteSkuWithTx(SkuDTO dto);
	/**
	 * 分页查询所有电子卡券sku
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	public PageResult<Map<String, Object>> findSkuECardOfPage(SkuDTO dto, Pagination page);
	/**
	 * 批量启用停用
	 * @param ids
	 * @param type
	 * @return
	 */
	public int isAvailableWithTx(List<Long> ids, int type);

    JsonResult<List<SkuVO>> getMembershipSku(Long platformId);

    void updateExternalSkuIdBackStage(Long skuId, String externalSkuId, String barCode);
}
	