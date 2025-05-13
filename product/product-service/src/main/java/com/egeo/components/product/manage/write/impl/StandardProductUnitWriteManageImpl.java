package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StandardProductUnitWriteManage;
import com.egeo.components.product.dao.write.StandardProductUnitWriteDAO;
import com.egeo.components.product.po.StandardProductUnitPO;
import com.egeo.exception.BusinessException;

import java.util.List;

@Service
public class StandardProductUnitWriteManageImpl implements StandardProductUnitWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardProductUnitWriteDAO standardProductUnitWriteDAO;

	@Override
	public Long insertStandardProductUnitWithTx(StandardProductUnitPO po) {
		
		int i ;
		try {
				i = standardProductUnitWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStandardProductUnitWithTx(StandardProductUnitPO po) {
		return standardProductUnitWriteDAO.update(po);
	}

	@Override
	public int deleteStandardProductUnitWithTx(StandardProductUnitPO po) {
		int i;
		i = standardProductUnitWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public void saveSPU(List<StandardProductUnitPO> standardProductUnitPOList) {
		try{
		standardProductUnitWriteDAO.saveSPU(standardProductUnitPOList);
		}catch (Exception e){
			logger.error("saveSPU失败,e:"+e.getMessage());
		}
	}

	@Override
	public void updateStandardProductUnitList(List<StandardProductUnitPO> standardProductUnitPOList) {
		try{
			standardProductUnitWriteDAO.updateStandardProductUnitList(standardProductUnitPOList);
		}catch (Exception e){
			logger.error("updateStandardProductUnitList失败,e:"+e.getMessage());
		}
	}
}
	