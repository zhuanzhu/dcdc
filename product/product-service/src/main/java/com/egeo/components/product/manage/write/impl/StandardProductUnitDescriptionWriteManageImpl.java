package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StandardProductUnitDescriptionWriteManage;
import com.egeo.components.product.dao.write.StandardProductUnitDescriptionWriteDAO;
import com.egeo.components.product.po.StandardProductUnitDescriptionPO;
import com.egeo.exception.BusinessException;

import java.util.List;

@Service
public class StandardProductUnitDescriptionWriteManageImpl implements StandardProductUnitDescriptionWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardProductUnitDescriptionWriteDAO standardProductUnitDescriptionWriteDAO;

	@Override
	public Long insertStandardProductUnitDescriptionWithTx(StandardProductUnitDescriptionPO po) {
		
		int i ;
		try {
				i = standardProductUnitDescriptionWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStandardProductUnitDescriptionWithTx(StandardProductUnitDescriptionPO po) {
		int i;
		i = standardProductUnitDescriptionWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStandardProductUnitDescriptionWithTx(StandardProductUnitDescriptionPO po) {
		int i;
		i = standardProductUnitDescriptionWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public void saveStandardProductUnitDescription(List<StandardProductUnitDescriptionPO> standardProductUnitDescriptionPOList) {
	try{
			standardProductUnitDescriptionWriteDAO.saveStandardProductUnitDescription(standardProductUnitDescriptionPOList);
	}catch (Exception e){
		logger.error("saveStandardProductUnitDescription失败,e:"+e.getMessage());
	}
	}

	@Override
	public void updateStandardProductUnitDescriptionSWithTx(StandardProductUnitDescriptionPO standardProductUnitDescriptionPO) {
		standardProductUnitDescriptionWriteDAO.updateStandardProductUnitDescriptionSWithTx( standardProductUnitDescriptionPO);
	}
}
	