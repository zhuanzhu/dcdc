package com.egeo.components.promotion.manage.write;

import java.util.List;
import java.util.Map;

import com.egeo.components.promotion.po.ECardPO;


public interface ECardWriteManage {

	Long insertECardWithTx(ECardPO po);

	int updateECardWithTx(ECardPO po);

	int deleteECardWithTx(ECardPO po);

	int insertECardAllWithTx(List<ECardPO> eCardPOs);
	/**
	 * 根据spuid修改卡密类型
	 * @param spuId
	 * @param cardType 卡密类型
	 * @return
	 */
	int updateCardTypeBySpuId(Long spuId, Integer cardType);
	/**
	 * 批量更新卡库
	 * @param keys
	 * @return
	 */
	public int updateECardByKeyWithTx(Map<String, Object> keys);
}
	