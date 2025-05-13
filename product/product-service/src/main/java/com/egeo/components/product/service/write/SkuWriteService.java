package com.egeo.components.product.service.write;

import java.util.List;

import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.SkuDTO;


public interface SkuWriteService {

	public Long insertSkuWithTx(SkuDTO dto);

	public int updateSkuWithTx(SkuDTO dto);

	public int deleteSkuWithTx(SkuDTO dto);
	/**
	 * 批量启用停用
	 * @param ids
	 * @param type
	 * @return
	 */
	public int isAvailableWithTx(List<Long> ids, int type);
	/**
	 * 根据spuid更新supu信息
	 * @param standardProductUnitId
	 * @param id
	 */
	public List<CommodityProductUnitDTO> updateSuPuWithTx(Long standardProductUnitId, Long merchantProductId);

    void updateSkuParamsWithTx(SkuDTO skuDTO);

    void saveSku(List<Long> skuIdList, List<Long> spuIdList, List<String> skuSerialNumberList, List<String> nameList, List<String> jdSkuIdList);

    void updateSkuList(List<Long> skuIdList, List<String> nameList, List<Long> jdSkuIdList);
}
	