package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.BuyCardItemRefundDTO;


public interface BuyCardItemRefundWriteService {

	public Long insertBuyCardItemRefundWithTx(BuyCardItemRefundDTO dto);

	public int updateBuyCardItemRefundWithTx(BuyCardItemRefundDTO dto);

	public int deleteBuyCardItemRefundWithTx(BuyCardItemRefundDTO dto);
}
