package com.egeo.components.stock.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.stock.po.ContactGroupStockPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContactGroupStockReadDAO extends BaseReadDAO<ContactGroupStockPO>{
	
    List<ContactGroupStockPO> findMapOfPage(@Param("po") ContactGroupStockPO po, @Param("page") Pagination page, @Param("spuIds")List<Long> spuIds, @Param("merchantIds")List<Long> merchantIds); 
    
    int countMapOfPage(@Param("po") ContactGroupStockPO po, @Param("spuIds")List<Long> spuIds, @Param("merchantIds")List<Long> merchantIds);   
    
    List<ContactGroupStockPO> findContactGroupStockByMerchantIdAndSuId(@Param("po") ContactGroupStockPO po, @Param("suId") Long suId);

	public ContactGroupStockPO findByPuId(@Param("puId") Long commodityProductUnitId);
	
	/**
	 * 根据名称查询
	 * @param po
	 * @return
	 */
	List<ContactGroupStockPO> findAllByName(@Param("po") ContactGroupStockPO po , @Param("page") Pagination page);
	
}	
