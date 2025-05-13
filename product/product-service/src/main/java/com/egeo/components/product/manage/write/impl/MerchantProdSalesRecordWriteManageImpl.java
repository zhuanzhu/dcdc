package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.MerchantProdSalesRecordWriteManage;
import com.egeo.components.product.dao.write.MerchantProdSalesRecordWriteDAO;
import com.egeo.components.product.po.MerchantProdSalesRecordPO;
import com.egeo.exception.BusinessException;

@Service
public class MerchantProdSalesRecordWriteManageImpl implements MerchantProdSalesRecordWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProdSalesRecordWriteDAO merchantProdSalesRecordWriteDAO;

	@Override
	public Long insertMerchantProdSalesRecordWithTx(MerchantProdSalesRecordPO po) {
		
		int i ;
		try {
				i = merchantProdSalesRecordWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateMerchantProdSalesRecordWithTx(MerchantProdSalesRecordPO po) {
		int i;
		i = merchantProdSalesRecordWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteMerchantProdSalesRecordWithTx(MerchantProdSalesRecordPO po) {
		int i;
		i = merchantProdSalesRecordWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int addSalesVolumeByIdWithTx(Long merchantProdSalesRecordId, Long salesVolume) {
		return merchantProdSalesRecordWriteDAO.addSalesVolumeByIdWithTx(merchantProdSalesRecordId, salesVolume);
	}	
}
	