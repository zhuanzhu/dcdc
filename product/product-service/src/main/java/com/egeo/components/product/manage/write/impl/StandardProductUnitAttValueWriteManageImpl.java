package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StandardProductUnitAttValueWriteManage;
import com.egeo.components.product.dao.write.StandardProductUnitAttValueWriteDAO;
import com.egeo.components.product.po.StandardProductUnitAttValuePO;
import com.egeo.exception.BusinessException;

import java.util.List;

@Service
public class StandardProductUnitAttValueWriteManageImpl implements StandardProductUnitAttValueWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardProductUnitAttValueWriteDAO standardProductUnitAttValueWriteDAO;

	@Override
	public Long insertStandardProductUnitAttValueWithTx(StandardProductUnitAttValuePO po) {
		
		int i ;
		try {
				i = standardProductUnitAttValueWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStandardProductUnitAttValueWithTx(StandardProductUnitAttValuePO po) {
		int i;
		i = standardProductUnitAttValueWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStandardProductUnitAttValueWithTx(StandardProductUnitAttValuePO po) {
		int i;
		i = standardProductUnitAttValueWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据spu属性id删除spu属性值信息
	 * @param id
	 * @return
	 */
	@Override
	public int deleteByStandardProductUnitAttNameId(Long standardProductUnitAttNameId) {
		StandardProductUnitAttValuePO standardProductUnitAttValuePO = new StandardProductUnitAttValuePO();
		standardProductUnitAttValuePO.setStandardProductUnitAttNameId(standardProductUnitAttNameId);
		return standardProductUnitAttValueWriteDAO.deleteByPara(standardProductUnitAttValuePO);
	}

	@Override
	public void saveSPUValue(List<StandardProductUnitAttValuePO> standardProductUnitAttValuePOList) {
		try{
		standardProductUnitAttValueWriteDAO.saveSPUValue(standardProductUnitAttValuePOList);
	}catch (Exception e){
		logger.error("saveSPUValue失败,e:"+e.getMessage());
	}
	}
}
	