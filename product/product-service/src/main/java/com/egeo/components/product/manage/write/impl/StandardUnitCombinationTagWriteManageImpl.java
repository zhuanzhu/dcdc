package com.egeo.components.product.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StandardUnitCombinationTagWriteManage;
import com.egeo.components.product.dao.write.StandardUnitCombinationTagWriteDAO;
import com.egeo.components.product.dao.write.StandardUnitCombinationWriteDAO;
import com.egeo.components.product.po.StandardUnitCombinationPO;
import com.egeo.components.product.po.StandardUnitCombinationTagPO;
import com.egeo.exception.BusinessException;

@Service
public class StandardUnitCombinationTagWriteManageImpl implements StandardUnitCombinationTagWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitCombinationTagWriteDAO standardUnitCombinationTagWriteDAO;
	
	@Autowired
	private StandardUnitCombinationWriteDAO standardUnitCombinationWriteDAO;

	@Override
	public Long insertStandardUnitCombinationTagWithTx(StandardUnitCombinationTagPO po) {
		
		int i ;
		try {
				i = standardUnitCombinationTagWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStandardUnitCombinationTagWithTx(StandardUnitCombinationTagPO po) {
		int i;
		i = standardUnitCombinationTagWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStandardUnitCombinationTagWithTx(StandardUnitCombinationTagPO po) {
		int i;
		i = standardUnitCombinationTagWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 批量保存su组合id与标签关系信息
	 * @param standardUnitCombinationId
	 * @return
	 */
	@Override
	public Integer saveStandardUnitCombinationTagWithTx(StandardUnitCombinationPO po, List<Long> tagIdList) {
		// 根据su组合id删除标签关系
		StandardUnitCombinationTagPO standardUnitCombinationTag = new StandardUnitCombinationTagPO();
		standardUnitCombinationTag.setStandardUnitCombinationId(po.getId());
		standardUnitCombinationTagWriteDAO.deleteByPara(standardUnitCombinationTag);
		for (Long tagId : tagIdList) {
			StandardUnitCombinationTagPO standardUnitCombinationTagPO = new StandardUnitCombinationTagPO();
			standardUnitCombinationTagPO.setStandardUnitCombinationId(po.getId());
			standardUnitCombinationTagPO.setTagId(tagId);
			standardUnitCombinationTagWriteDAO.insert(standardUnitCombinationTagPO);
		}
		return standardUnitCombinationWriteDAO.update(po);
	}
	/**
	 * 根据标签id清除记录
	 * @param tagId
	 * @return
	 */
	@Override
	public int delByTagId(Long tagId) {
		StandardUnitCombinationTagPO standardUnitCombinationTagPO = new StandardUnitCombinationTagPO();
		standardUnitCombinationTagPO.setTagId(tagId);
		return standardUnitCombinationTagWriteDAO.deleteByPara(standardUnitCombinationTagPO);
	}	
}
	