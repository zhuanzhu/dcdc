package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.SupplierProductWriteManage;
import com.egeo.components.product.dao.write.SupplierProductWriteDAO;
import com.egeo.components.product.po.SupplierProductPO;
import com.egeo.exception.BusinessException;

@Service
public class SupplierProductWriteManageImpl implements SupplierProductWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SupplierProductWriteDAO supplierProductWriteDAO;

	@Override
	public Long insertSupplierProductWithTx(SupplierProductPO po) {
		
		int i ;
		try {
				i = supplierProductWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSupplierProductWithTx(SupplierProductPO po) {
		int i;
		i = supplierProductWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSupplierProductWithTx(SupplierProductPO po) {
		int i;
		i = supplierProductWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	