package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.JdProductInnerIdDTO;

import java.util.List;


public interface JdProductInnerIdWriteService {

	public Long insertJdProductInnerIdWithTx(JdProductInnerIdDTO dto);

	public int updateJdProductInnerIdWithTx(JdProductInnerIdDTO dto);

	public int deleteJdProductInnerIdWithTx(JdProductInnerIdDTO dto);

    void saveJdProductInnerIdList(List<String> jdSkuIdList, List<Long> productIdList, List<Long> skuIdList, List<Long> suIdList, List<Long> productUnitIdList, List<Long> puIdList, List<Long> pictureIdList, List<Long> attValueIdList);
}
	