package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.ProductLimitProfitWriteManage;
import com.egeo.components.product.dao.write.ProductLimitProfitWriteDAO;
import com.egeo.components.product.po.ProductLimitProfitPO;
import com.egeo.exception.BusinessException;

@Service
public class ProductLimitProfitWriteManageImpl implements ProductLimitProfitWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductLimitProfitWriteDAO productLimitProfitWriteDAO;

	@Override
	public Long insertProductLimitProfitWithTx(ProductLimitProfitPO po) {
		
		int i ;
		try {
				i = productLimitProfitWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateProductLimitProfitWithTx(ProductLimitProfitPO po) {
		int i;
		i = productLimitProfitWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteProductLimitProfitWithTx(ProductLimitProfitPO po) {
		int i;
		i = productLimitProfitWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	