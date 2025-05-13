package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.AttributeNameDecimalWriteManage;
import com.egeo.components.product.dao.write.AttributeNameDecimalWriteDAO;
import com.egeo.components.product.po.AttributeNameDecimalPO;
import com.egeo.exception.BusinessException;

@Service
public class AttributeNameDecimalWriteManageImpl implements AttributeNameDecimalWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AttributeNameDecimalWriteDAO attributeNameDecimalWriteDAO;

	@Override
	public Long insertAttributeNameDecimalWithTx(AttributeNameDecimalPO po) {
		
		int i ;
		try {
				i = attributeNameDecimalWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateAttributeNameDecimalWithTx(AttributeNameDecimalPO po) {
		int i;
		i = attributeNameDecimalWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteAttributeNameDecimalWithTx(AttributeNameDecimalPO po) {
		int i;
		i = attributeNameDecimalWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据属性id更新属性范围信息
	 * @param attributeNameDecimalDTO
	 * @return
	 */
	@Override
	public int updateAttributeNameDecimalByAttNameIdWithTx(AttributeNameDecimalPO po) {
		// TODO Auto-generated method stub
		return attributeNameDecimalWriteDAO.updateAttributeNameDecimalByAttNameIdWithTx(po);
	}	
}
	