package com.egeo.components.stock.service.write;

import com.egeo.components.stock.dto.MerchantProductVirtualStockDTO;

public interface MerchantProductVirtualStockWriteService {

	/**
	 * 冻结unit库存
	 * @param skuId
	 * @param integer 
	 * @return
	 */
	int freezeStockWithTx(Long skuId, Integer puCount);
	
	/**
	 * 解冻unit库存
	 * @param skuId
	 * @param puCount
	 */
	int unfreezeStockWithTx(Long skuId, Integer puCount);
	
	/**
	 * 进货
	 * @param skuId
	 * @param realStockNum
	 * @return
	 */
	int addStockWithTx(Long skuId,Long realStockNum);
	/**
	 * 出货
	 * @param skuId
	 * @param realStockNum
	 * @return
	 */
	int subtractStockWithTx(Long skuId,Long realStockNum);
	/**
	 * 删除redis中的缓存
	 * @param merchantProductVirtualStockDTO
	 */
	void removeCommodityProductUnitWarehouseStockCacheWithTx(MerchantProductVirtualStockDTO merchantProductVirtualStockDTO);
	
	/**
	 * 添加虚拟库存
	 */
	Long saveMerchantProductVirtualStockDTO(MerchantProductVirtualStockDTO merchantProductVirtualStockDTO);
	
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
	