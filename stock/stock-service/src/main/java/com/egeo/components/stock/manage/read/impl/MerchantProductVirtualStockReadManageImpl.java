package com.egeo.components.stock.manage.read.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.read.MerchantProductVirtualStockReadManage;
import com.egeo.components.stock.dao.read.MerchantProductVirtualStockReadDAO;
import com.egeo.components.stock.po.MerchantProductVirtualStockPO;

@Service
public class MerchantProductVirtualStockReadManageImpl implements MerchantProductVirtualStockReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProductVirtualStockReadDAO merchantProductVirtualStockReadDAO;
	
	
	@Override
	public Long queryUnitStockBySkuId(Long skuId) {
		return merchantProductVirtualStockReadDAO.queryUnitStockBySkuId(skuId);
	}

	/**
	 * 根据skuId从数据库中查询最新的sku商品库存信息
	 * @param id
	 * @return
	 */
	@Override
	public MerchantProductVirtualStockPO merchantProductVirtualStockBySkuId(Long skuId) {
		// TODO Auto-generated method stub
		return merchantProductVirtualStockReadDAO.merchantProductVirtualStockBySkuId(skuId);
	}
	
}
	