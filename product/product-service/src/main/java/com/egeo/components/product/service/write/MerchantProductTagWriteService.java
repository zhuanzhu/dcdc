package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.MerchantProductTagDTO;


public interface MerchantProductTagWriteService {

	public Long insertMerchantProductTagWithTx(MerchantProductTagDTO dto);

	public int updateMerchantProductTagWithTx(MerchantProductTagDTO dto);

	public int deleteMerchantProductTagWithTx(MerchantProductTagDTO dto);

	/**
	 * 根据商品id删除商品标签关系
	 * @param merchantProductId
	 * @return
	 */
	public int delByMerchantProductId(Long merchantProductId);
}
	