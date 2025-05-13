package com.egeo.components.promotion.service.write;

import java.util.List;
import java.util.Map;

import com.egeo.components.promotion.dto.CardBatchDTO;
import com.egeo.components.promotion.dto.ECardDTO;
import com.egeo.components.promotion.dto.ErCardRecordDTO;


public interface ECardWriteService {

	public Long insertECardWithTx(ECardDTO dto);

	public int updateECardWithTx(ECardDTO dto);

	public int deleteECardWithTx(ECardDTO dto);
	/**
	 * 批量导入
	 * @param cardBatchDTO 卡密导入批次
	 * @param erCardRecords 卡密记录集合
	 * @return
	 */
	public int importECardWithTx(
			CardBatchDTO cardBatchDTO,
			List<ErCardRecordDTO> erCardRecords);
	/**
	 * 根据spuid修改卡密类型
	 * @param spuId
	 * @param cardType 卡密类型
	 * @return
	 */
	public int updateCardTypeBySpuId(Long spuId, Integer cardType);

	/**
	 * 批量更新卡库
	 * @param keys
	 * @return
     */
	public int  updateECardList(Map<String, Object> keys);
}
	