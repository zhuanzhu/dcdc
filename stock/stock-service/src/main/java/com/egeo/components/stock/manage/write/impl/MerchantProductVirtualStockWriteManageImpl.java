package com.egeo.components.stock.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.write.MerchantProductVirtualStockWriteManage;
import com.egeo.components.stock.dao.write.MerchantProductVirtualStockWriteDAO;
import com.egeo.components.stock.po.MerchantProductVirtualStockPO;

@Service
public class MerchantProductVirtualStockWriteManageImpl implements MerchantProductVirtualStockWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProductVirtualStockWriteDAO merchantProductVirtualStockWriteDAO;
	
	
	@Override
	public int freezeStock(Long skuId, Integer count) {
		return merchantProductVirtualStockWriteDAO.freezeStock(skuId,count);
	}
	

	@Override
	public int unfreezeStockWithTx(Long skuId, Integer puCount) {
		return merchantProductVirtualStockWriteDAO.unfreezeStockWithTx(skuId, puCount);
	}

	/**
	 * 进货
	 * @param skuId
	 * @param realStockNum
	 * @return
	 */
	@Override
	public int addStock(Long skuId, Long realStockNum) {
		// TODO Auto-generated method stub
		return merchantProductVirtualStockWriteDAO.addStock(skuId, realStockNum);
	}
	/**
	 * 出货(减真实库存和解冻冻结库存)
	 * @param skuId
	 * @param realStockNum
	 * @return
	 */
	@Override
	public int subtractStock(Long skuId, Long realStockNum) {
		// TODO Auto-generated method stub
		return merchantProductVirtualStockWriteDAO.subtractStock(skuId, realStockNum);
	}
	/**
	 * 添加虚拟库存
	 */
	@Override
	public Long saveMerchantProductVirtualStockDTO(MerchantProductVirtualStockPO po) {
		int i = merchantProductVirtualStockWriteDAO.insert(po);
		if(i != 0){
			return po.getId();
		}
		return null;
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
		return merchantProductVirtualStockWriteDAO.unfreezeAndDeductStockWithTx(skuId, integer);
	}

	
	@Override
	public int deductStockBySkuIdWithTx(Long skuId, Long count) {
		return merchantProductVirtualStockWriteDAO.deductStockBySkuId(skuId, count);
	}

}
	