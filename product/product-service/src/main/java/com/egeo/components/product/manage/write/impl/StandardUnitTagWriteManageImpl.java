package com.egeo.components.product.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StandardUnitTagWriteManage;
import com.egeo.components.product.dao.write.StandardUnitTagWriteDAO;
import com.egeo.components.product.po.StandardUnitTagPO;
import com.egeo.exception.BusinessException;

@Service
public class StandardUnitTagWriteManageImpl implements StandardUnitTagWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitTagWriteDAO standardUnitTagWriteDAO;

	@Override
	public Long insertStandardUnitTagWithTx(StandardUnitTagPO po) {
		
		int i ;
		try {
				i = standardUnitTagWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStandardUnitTagWithTx(StandardUnitTagPO po) {
		int i;
		i = standardUnitTagWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStandardUnitTagWithTx(StandardUnitTagPO po) {
		int i;
		i = standardUnitTagWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据商品id和标签id集合删除商品标签关系
	 * @param merchantProductId
	 * @param tagList
	 * @return
	 */
	@Override
	public int delByTags(Long standardUnitId, List<Long> tagList) {
		
		return standardUnitTagWriteDAO.delByTags(standardUnitId, tagList);
	}

	/**
	 * 根据商品id删除商品标签关系
	 * @param standardUnitId
	 * @return
	 */
	@Override
	public int delByStandardUnitId(Long standardUnitId) {
		StandardUnitTagPO standardUnitTagPO = new StandardUnitTagPO();
		standardUnitTagPO.setStandardUnitId(standardUnitId);
		return standardUnitTagWriteDAO.deleteByPara(standardUnitTagPO);
	}

	@Override
	public int delByTagId(Long tagId) {
		StandardUnitTagPO standardUnitTagPO = new StandardUnitTagPO();
		standardUnitTagPO.setTagId(tagId);
		return standardUnitTagWriteDAO.deleteByPara(standardUnitTagPO);
	}	
}
	