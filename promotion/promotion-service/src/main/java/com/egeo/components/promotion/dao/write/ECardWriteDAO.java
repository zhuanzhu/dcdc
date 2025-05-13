package com.egeo.components.promotion.dao.write;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.promotion.po.ECardPO;
import com.egeo.orm.BaseWriteDAO;

public interface ECardWriteDAO extends BaseWriteDAO<ECardPO> {
	/**
	 * 批量保存卡密信息
	 * @param eCardPOs
	 * @return
	 */
	int insertAll(@Param("poList")List<ECardPO> eCardPOs);
	/**
	 * 根据spuid修改卡密类型
	 * @param spuId
	 * @param cardType 卡密类型
	 * @return
	 */
	int updateCardTypeBySpuId(@Param("spuId")Long spuId, @Param("cardType")Integer cardType);

	/**
	 * 批量更新卡库
	 * @param keys
	 * @return
     */
	public int updateECardByKey(Map<String, Object> keys);
}
	