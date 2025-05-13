package com.egeo.components.stock.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.stock.po.MerchantProductVirtualStockPO;
import com.egeo.orm.BaseWriteDAO;

public interface MerchantProductVirtualStockWriteDAO extends BaseWriteDAO<MerchantProductVirtualStockPO> {

	
	/**
	 * 冻结unit库存
	 * @param skuId
	 * @param count
	 * @return
	 */
	int freezeStock(@Param("skuId")Long skuId, @Param("count")Integer count);
	
	/**
	 * 解冻unit库存
	 * @param skuId
	 * @param count
	 * @return
	 */
	int unfreezeStockWithTx(@Param("skuId")Long skuId, @Param("count")Integer count);
	
	/**
	 * 进货
	 * @param skuId
	 * @param realStockNum
	 * @return
	 */
	int addStock(@Param("skuId")Long skuId, @Param("realStockNum")Long realStockNum);
	/**
	 * 出货(减真实库存和解冻冻结库存)
	 * @param skuId
	 * @param realStockNum
	 * @return
	 */
	int subtractStock(@Param("skuId")Long skuId, @Param("realStockNum")Long realStockNum);
	/**
	 * 解冻unit库存并且扣除库存
	 * @param skuId
	 * @param integer 
	 * @return
	 */
	int unfreezeAndDeductStockWithTx(@Param("skuId")Long skuId, @Param("count")Integer count);
	/**
	 * 根据skuId扣除库存
	 * @param skuId
	 * @param count
	 * @return
	 */
	int deductStockBySkuId(@Param("skuId")Long skuId, @Param("count")Long count);
	
}
	