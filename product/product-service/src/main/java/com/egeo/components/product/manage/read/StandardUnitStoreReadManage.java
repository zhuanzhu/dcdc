package com.egeo.components.product.manage.read;

import java.util.List;

import com.egeo.components.product.condition.StandardUnitStoreCondition;
import com.egeo.components.product.po.StandardUnitStorePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitStoreReadManage {

	public StandardUnitStorePO findStandardUnitStoreById(StandardUnitStorePO po);

	public PageResult<StandardUnitStoreCondition> findStandardUnitStoreOfPage(StandardUnitStorePO po,Pagination page);

	public List<StandardUnitStoreCondition> findStandardUnitStoreAll(StandardUnitStorePO po);

	public List<StandardUnitStorePO> findAll(StandardUnitStorePO standardUnitStorePO);
	/**
	 * 根据门店id查询在售商品数量(上架商品)
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	public Integer standardUnitSumByStoreId(Long storeId, Long platformId);
	/**
	 * 根据门店id查询puid集合
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	public List<Long> findByPuIdsByStoreId(Long storeId, Long platformId);
	/**
	 * 根据商品id查询所有门店（包含id和名称）
	 */
	public List<StandardUnitStoreCondition> standardUnitStoreByStandardUnitId(StandardUnitStorePO po);
	
	/**
	 * 根据su列表和门店ID查询
	 * @param suIdList
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	List<StandardUnitStorePO> findByStoreAndSu(List<Long> suIdList, Long storeId, Long platformId);

    int findStandardUnitStoreCount(Long suId, Long storeId);
}
	