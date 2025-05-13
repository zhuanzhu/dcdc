package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.SkuAttNameDTO;

import java.util.List;


public interface SkuAttNameWriteService {

	public Long insertSkuAttNameWithTx(SkuAttNameDTO dto);

	public int updateSkuAttNameWithTx(SkuAttNameDTO dto);

	public int deleteSkuAttNameWithTx(SkuAttNameDTO dto);

    void saveSkuAttName(List<Long> skuAttNameIdList, List<Long> skuIdList);
}
	