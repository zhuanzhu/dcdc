package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StandardProductUnitAttNameWriteManage;
import com.egeo.components.product.dao.write.StandardProductUnitAttNameWriteDAO;
import com.egeo.components.product.po.StandardProductUnitAttNamePO;
import com.egeo.exception.BusinessException;

import java.util.List;

@Service
public class StandardProductUnitAttNameWriteManageImpl implements StandardProductUnitAttNameWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardProductUnitAttNameWriteDAO standardProductUnitAttNameWriteDAO;

	@Override
	public Long insertStandardProductUnitAttNameWithTx(StandardProductUnitAttNamePO po) {
		
		int i ;
		try {
				i = standardProductUnitAttNameWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStandardProductUnitAttNameWithTx(StandardProductUnitAttNamePO po) {
		int i;
		i = standardProductUnitAttNameWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStandardProductUnitAttNameWithTx(StandardProductUnitAttNamePO po) {
		int i;
		i = standardProductUnitAttNameWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public void saveStandardProductUnitAttName(List<StandardProductUnitAttNamePO> standardProductUnitAttNamePOList) {
		try {
			standardProductUnitAttNameWriteDAO.saveStandardProductUnitAttName(standardProductUnitAttNamePOList);
		}catch (Exception e){
			logger.error("saveStandardProductUnitAttName失败,e:"+e.getMessage());
		}
	}
}
	