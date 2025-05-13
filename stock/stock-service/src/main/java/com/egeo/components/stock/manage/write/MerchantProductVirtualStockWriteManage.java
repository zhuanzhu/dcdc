package com.egeo.components.stock.manage.write;

import com.egeo.components.stock.po.MerchantProductVirtualStockPO;

public interface MerchantProductVirtualStockWriteManage {

	/**
	 * 冻结unit库存
	 * @param skuId
	 * @param count
	 * @return
	 */
	int freezeStock(Long skuId, Integer count);
	
	/**
	 * 解冻unit库存
	 * @param skuId
	 * @param puCount
	 * @return
	 */
	int unfreezeStockWithTx(Long skuId, Integer puCount);
	/**
	 * 进货
	 * @param skuId
	 * @param realStockNum
	 * @return
	 */
	int addStock(Long skuId, Long realStockNum);
	/**
	 * 出货
	 * @param skuId
	 * @param realStockNum
	 * @return
	 */
	int subtractStock(Long skuId, Long realStockNum);
	/**
	 * 添加虚拟库存
	 */
	Long saveMerchantProductVirtualStockDTO(MerchantProductVirtualStockPO po);
	/**
	 * 解冻unit库存并且扣除库存
	 * @param skuId
	 * @param integer 
	 * @return
	 */
	int unfreezeAndDeductStockWithTx(Long skuId, Integer integer);
	/**
	 * 根据skuId扣除库存
	 * @param skuId
	 * @param count
	 * @return
	 */
	int deductStockBySkuIdWithTx(Long skuId, Long count);

}
	