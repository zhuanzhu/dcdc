package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.JdProductInnerIdWriteManage;
import com.egeo.components.product.dao.write.JdProductInnerIdWriteDAO;
import com.egeo.components.product.po.JdProductInnerIdPO;
import com.egeo.exception.BusinessException;

import java.util.List;

@Service
public class JdProductInnerIdWriteManageImpl implements JdProductInnerIdWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdProductInnerIdWriteDAO jdProductInnerIdWriteDAO;

	@Override
	public Long insertJdProductInnerIdWithTx(JdProductInnerIdPO po) {
		
		int i ;
		try {
				i = jdProductInnerIdWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getJdSkuId();
	}

	@Override
	public int updateJdProductInnerIdWithTx(JdProductInnerIdPO po) {
		int i;
		i = jdProductInnerIdWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteJdProductInnerIdWithTx(JdProductInnerIdPO po) {
		int i;
		i = jdProductInnerIdWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public void saveJdProductInnerIdList(List<JdProductInnerIdPO> jdProductInnerIdPOList) {
		try {
			jdProductInnerIdWriteDAO.saveJdProductInnerIdList(jdProductInnerIdPOList);
		}catch (Exception e){
			logger.error("批量保存JdProductInnerIdList出错:"+e.getMessage());
		}
	}
}
	