package com.egeo.components.product.manage.read;

import java.util.List;

import com.egeo.components.product.condition.StandardProductUnitCondition;
import com.egeo.components.product.po.StandardProductUnitPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardProductUnitReadManage {

	public StandardProductUnitPO findStandardProductUnitById(StandardProductUnitPO po);

	public PageResult<StandardProductUnitPO> findStandardProductUnitOfPage(StandardProductUnitPO po,Pagination page);

	public List<StandardProductUnitPO> findStandardProductUnitAll(StandardProductUnitPO po);
	/**
	 * 根据以通过的spu草稿id集合查询spu信息
	 * @param ids
	 * @return
	 */
	public List<StandardProductUnitPO> findProductByIds(List<Long> ids);
	/**
	 * 根据spuid查询spu信息及su序列号
	 * @param standardProductUnitId
	 * @return
	 */
	public StandardProductUnitCondition findSerialNumberByspuId(Long standardProductUnitId);

	/**
	 * 根据suid查询spu
	 * @param suId
	 * @return
	 */
	public StandardProductUnitPO querySpuBySuId(Long suId);
	/**
	 * 根据spuId查询spu信息
	 * @param standardProductUnitId
	 * @return
	 */
	public StandardProductUnitCondition queryStandardProductUnitById(Long standardProductUnitId);
	/**
	 * 根据suId查询spu模版id
	 * @param standardUnitId
	 * @return
	 */
	public Long findCommodityTemplateIdByStandardUnitId(Long standardUnitId);

    Long findLastId();
}
	