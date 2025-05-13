package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.condition.StandardProductUnitCondition;
import com.egeo.components.product.po.StandardProductUnitPO;
import com.egeo.orm.BaseReadDAO;

public interface StandardProductUnitReadDAO extends BaseReadDAO<StandardProductUnitPO>{
	
	/**
	 * 根据以通过的spu草稿id集合查询spu信息
	 * @param ids
	 * @return
	 */
	List<StandardProductUnitPO> findProductByIds(@Param("ids")List<Long> ids);
	
	/**
	 * 根据spuid查询spu信息及su序列号
	 * @param standardProductUnitId
	 * @return
	 */
	StandardProductUnitCondition findSerialNumberByspuId(@Param("standardProductUnitId")Long standardProductUnitId);
	
	/**
	 * 根据suid查询spu
	 * @param suId
	 * @return
	 */
	StandardProductUnitPO querySpuBySuId(@Param("suId")Long suId);
	/**
	 * 根据spuId查询spu信息
	 * @param standardProductUnitId
	 * @return
	 */
	StandardProductUnitCondition queryStandardProductUnitById(@Param("standardProductUnitId")Long standardProductUnitId);
	/**
	 * 根据suId查询spu模版id
	 * @param standardUnitId
	 * @return
	 */
	Long findCommodityTemplateIdByStandardUnitId(@Param("standardUnitId")Long standardUnitId);

    Long findLastId();
}
	