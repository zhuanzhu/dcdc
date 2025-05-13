package com.egeo.components.product.dao.write;	

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.StoreMenuNodeStandardUnitPO;
import com.egeo.orm.BaseWriteDAO;

public interface StoreMenuNodeStandardUnitWriteDAO extends BaseWriteDAO<StoreMenuNodeStandardUnitPO> {
	/**
	 * 批量插入门店与su商品关系
	 * @param storeMenuNodeId
	 * @param standardUnitIds
	 * @param platformId
	 * @return
	 */
	int insertAllWithTx(
			@Param("storeMenuNodeId")Long storeMenuNodeId, 
			@Param("ids")List<Long> standardUnitIds, 
			@Param("platformId")Long platformId);
	/**
	 * 根据门店id商品id删除门店菜单商品关系
	 * @param storeId
	 * @param standardUnitId
	 * @return
	 */
	int delByStoreIdStandardUnitId(
			@Param("storeId")Long storeId, 
			@Param("standardUnitId")Long standardUnitId,
			@Param("platformId")Long platformId);
}
	