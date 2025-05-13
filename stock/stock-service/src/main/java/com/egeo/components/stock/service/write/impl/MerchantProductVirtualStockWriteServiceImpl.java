package com.egeo.components.stock.service.write.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.service.write.MerchantProductVirtualStockWriteService;
import com.egeo.components.stock.manage.write.MerchantProductVirtualStockWriteManage;
import com.egeo.components.stock.converter.MerchantProductVirtualStockConverter;
import com.egeo.components.stock.dto.MerchantProductVirtualStockDTO;

import com.egeo.utils.cache.JedisUtil;

@Service("merchantProductVirtualStockWriteService")
public class MerchantProductVirtualStockWriteServiceImpl  implements MerchantProductVirtualStockWriteService {
	@Autowired
	private MerchantProductVirtualStockWriteManage merchantProductVirtualStockWriteManage;
	
	@Resource
	private  JedisUtil cache;

	@Override
	public int freezeStockWithTx(Long skuId, Integer puCount) {
		return merchantProductVirtualStockWriteManage.freezeStock(skuId,puCount);
	}
	
	@Override
	public int unfreezeStockWithTx(Long skuId, Integer puCount) {
		return merchantProductVirtualStockWriteManage.unfreezeStockWithTx(skuId,puCount);
	}
	
	/**
	 * 进货
	 * @param skuId
	 * @param realStockNum
	 * @return
	 */
	public int addStockWithTx(Long skuId,Long realStockNum){
		return merchantProductVirtualStockWriteManage.addStock(skuId,realStockNum);
		
	}
	/**
	 * 出货
	 * @param skuId
	 * @param realStockNum
	 * @return
	 */
	public int subtractStockWithTx(Long skuId,Long realStockNum){
		return merchantProductVirtualStockWriteManage.subtractStock(skuId,realStockNum);
		
	}
	/**
	 * 删除redis中的缓存
	 * @param merchantProductVirtualStockDTO
	 */
	@Override
	public void removeCommodityProductUnitWarehouseStockCacheWithTx(
			MerchantProductVirtualStockDTO merchantProductVirtualStockDTO) {
		String key = "sku:inventory:" + merchantProductVirtualStockDTO.getSkuId();
		cache.del(key);
		System.out.println("===========日志===========: 已删除redis中的缓存，key=" + key); 
		
	}
	/**
	 * 添加虚拟库存
	 */
	@Override
	public Long saveMerchantProductVirtualStockDTO(MerchantProductVirtualStockDTO merchantProductVirtualStockDTO) {
		// TODO Auto-generated method stub
		return merchantProductVirtualStockWriteManage.saveMerchantProductVirtualStockDTO(MerchantProductVirtualStockConverter.toPO(merchantProductVirtualStockDTO));
	}
	/**
	 * 解冻unit库存并且扣除库存
	 * @param skuId
	 * @param integer 
	 * @return
	 */
	@Override
	public int unfreezeAndDeductStockWithTx(Long skuId, Integer integer) {
		// TODO Auto-generated method stub
		return merchantProductVirtualStockWriteManage.unfreezeAndDeductStockWithTx(skuId, integer);
	}
	/**
	 * 根据skuId扣除库存
	 */
	@Override
	public int deductStockBySkuIdWithTx(Long skuId, Long count) {
		// TODO Auto-generated method stub
		return merchantProductVirtualStockWriteManage.deductStockBySkuIdWithTx(skuId, count);
	}
}
	