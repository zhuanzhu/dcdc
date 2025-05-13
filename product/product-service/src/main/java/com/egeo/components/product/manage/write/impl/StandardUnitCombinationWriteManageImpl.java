package com.egeo.components.product.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StandardUnitCombinationWriteManage;
import com.egeo.components.product.dao.read.StandardUnitCombinationReadDAO;
import com.egeo.components.product.dao.write.StandardUnitCombinationWriteDAO;
import com.egeo.components.product.po.StandardUnitCombinationPO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

@Service
public class StandardUnitCombinationWriteManageImpl implements StandardUnitCombinationWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitCombinationWriteDAO standardUnitCombinationWriteDAO;
	
	@Autowired
	private StandardUnitCombinationReadDAO standardUnitCombinationReadDAO;

	@Override
	public Long insertStandardUnitCombinationWithTx(StandardUnitCombinationPO po) {
		// 根据su组合名称查询su组合信息
		StandardUnitCombinationPO standardUnitCombinationPO = new StandardUnitCombinationPO();
		standardUnitCombinationPO.setCombinationName(po.getCombinationName());
		List<StandardUnitCombinationPO> list = standardUnitCombinationReadDAO.findAll(standardUnitCombinationPO,null);
		if(EmptyUtil.isNotEmpty(list)){
			throw new BusinessException("su组合名称重复");
		}
		int i ;
		try {
				i = standardUnitCombinationWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStandardUnitCombinationWithTx(StandardUnitCombinationPO po) {
		// 根据su组合名称查询su组合信息
		StandardUnitCombinationPO standardUnitCombinationPO = new StandardUnitCombinationPO();
		standardUnitCombinationPO.setCombinationName(po.getCombinationName());
		List<StandardUnitCombinationPO> list = standardUnitCombinationReadDAO.findAll(standardUnitCombinationPO,null);
		if(EmptyUtil.isNotEmpty(list)){
			if(!po.getId().equals(list.get(0).getId())){
				throw new BusinessException("su组合名称重复");
			}
		}
		int i;
		i = standardUnitCombinationWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStandardUnitCombinationWithTx(StandardUnitCombinationPO po) {
		int i;
		i = standardUnitCombinationWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	