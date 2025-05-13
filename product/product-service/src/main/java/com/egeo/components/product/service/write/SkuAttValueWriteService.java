package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.SkuAttValueDTO;

import java.util.List;


public interface SkuAttValueWriteService {

	public Long insertSkuAttValueWithTx(SkuAttValueDTO dto);

	public int updateSkuAttValueWithTx(SkuAttValueDTO dto);

	public int deleteSkuAttValueWithTx(SkuAttValueDTO dto);

    void saveSkuAttValuePO(List<Long> skuAttNameIdList, List<Long> attValueIdList);
}
	