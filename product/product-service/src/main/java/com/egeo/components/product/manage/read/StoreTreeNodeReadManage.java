package com.egeo.components.product.manage.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.condition.StoreTreeNodeCondition;
import com.egeo.components.product.po.StoreTreeNodePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StoreTreeNodeReadManage {

	public StoreTreeNodePO findStoreTreeNodeById(StoreTreeNodePO po);

	public PageResult<StoreTreeNodePO> findStoreTreeNodeOfPage(StoreTreeNodePO po,Pagination page);

	public List<StoreTreeNodePO> findStoreTreeNodeAll(StoreTreeNodePO po);
	/**
	 * 根据平台查询所有门店
	 * @param platformId
	 * @return
	 */
	public List<Long> findStoreTreeNodeAllByPlatformId(Long platformId);
	/**
	 * 根据pid查询所有门店（包含id和名称）
	 * @param pids
	 * @return
	 */
	public List<String> findByPids(String pids);
	/**
	 * 根据门店id查询是否是总店
	 * @param storeId
	 * @return
	 */
	public boolean findHeadStoreByStoreId(Long storeId);
	
	/**
	 * 根据id和name查询门店节点
	 * @param storeIds
	 * @param name
	 * @return
	 */
	List<StoreTreeNodeCondition> findByStoreIdAndName(@Param("storeIds")List<Long> storeIds, @Param("name")String name, Long platformId);
}
	