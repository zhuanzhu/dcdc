package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.SellPlatformStandardUnitWriteManage;
import com.egeo.components.product.dao.write.SellPlatformStandardUnitWriteDAO;
import com.egeo.components.product.po.SellPlatformStandardUnitPO;
import com.egeo.exception.BusinessException;

@Service
public class SellPlatformStandardUnitWriteManageImpl implements SellPlatformStandardUnitWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SellPlatformStandardUnitWriteDAO sellPlatformStandardUnitWriteDAO;

	@Override
	public Long insertSellPlatformStandardUnitWithTx(SellPlatformStandardUnitPO po) {
		
		int i ;
		try {
				i = sellPlatformStandardUnitWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSellPlatformStandardUnitWithTx(SellPlatformStandardUnitPO po) {
		int i;
		i = sellPlatformStandardUnitWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSellPlatformStandardUnitWithTx(SellPlatformStandardUnitPO po) {
		int i;
		i = sellPlatformStandardUnitWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据suid删除su比价平台信息
	 * @param merchantProdId
	 * @return
	 */
	@Override
	public int deleteByStandardUnitIdWithTx(Long merchantProdId) {
		SellPlatformStandardUnitPO po = new SellPlatformStandardUnitPO();
		po.setStandardUnitId(merchantProdId);
		return sellPlatformStandardUnitWriteDAO.deleteByPara(po);
	}
	/**
	 * 根据suid和比价平台id修改比较平台信息
	 * @param sellPlatformStandardUnitPO
	 * @return
	 */
	@Override
	public int updateSellPlatformStandardUnitByStandardUnitIdWithTx(
			SellPlatformStandardUnitPO sellPlatformStandardUnitPO) {
		// TODO Auto-generated method stub
		return sellPlatformStandardUnitWriteDAO.updateSellPlatformStandardUnitByStandardUnitIdWithTx(sellPlatformStandardUnitPO);
	}	
}
	