package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.condition.StoreTreeNodeCondition;
import com.egeo.components.product.po.StoreTreeNodePO;
import com.egeo.orm.BaseReadDAO;

public interface StoreTreeNodeReadDAO extends BaseReadDAO<StoreTreeNodePO>{

	Integer getMaxListSort(@Param("po")StoreTreeNodePO po);

	List<Long> findStoreTreeNodeAllByPlatformId(@Param("platformId")Long platformId);
	/**
	 * 根据pid查询所有门店（包含id和名称）
	 * @param pids
	 * @return
	 */
	List<String> findByPids(@Param("pids")String pids);
	
	Long findHeadStoreByStoreId(@Param("storeId")Long storeId);

	/**
	 * 根据id和name查询门店节点
	 * @param storeIds
	 * @param name
	 * @return
	 */
	List<StoreTreeNodeCondition> findByStoreIdAndName(@Param("storeIds")List<Long> storeIds, @Param("name")String name, @Param("platformId")Long platformId);
	
}
	