package com.egeo.components.product.manage.write;

import java.util.List;

import com.egeo.components.product.po.StandardUnitTagPO;


public interface StandardUnitTagWriteManage {

	Long insertStandardUnitTagWithTx(StandardUnitTagPO po);

	int updateStandardUnitTagWithTx(StandardUnitTagPO po);

	int deleteStandardUnitTagWithTx(StandardUnitTagPO po);

	/**
	 * 根据商品id和标签id集合删除商品标签关系
	 * @param standardUnitId
	 * @param tagList
	 * @return
	 */
	int delByTags(Long standardUnitId, List<Long> tagList);

	/**
	 * 根据商品id删除商品标签关系
	 * @param standardUnitId
	 * @return
	 */
	int delByStandardUnitId(Long standardUnitId);
	/**
	 * 根据标签id清除记录
	 * @param tagId
	 * @return
	 */
	int delByTagId(Long tagId);
}
	