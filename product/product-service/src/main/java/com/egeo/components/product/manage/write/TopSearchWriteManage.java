package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.TopSearchPO;


public interface TopSearchWriteManage {

	Long insertTopSearchWithTx(TopSearchPO po);

	int updateTopSearchWithTx(TopSearchPO po);

	int deleteTopSearchWithTx(TopSearchPO po);
	/**
	 * 根据id启用停用热搜
	 * @param topSearchId
	 * @return
	 */
	int startStopTopSearchWithTx(Long topSearchId);
}
	