package com.egeo.components.promotion.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.dao.write.PraisePointWriteDAO;
import com.egeo.components.promotion.manage.write.PraisePointWriteManage;
import com.egeo.components.promotion.po.PraisePointPO;
import com.egeo.exception.BusinessException;

@Service
public class PraisePointWriteManageImpl implements PraisePointWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PraisePointWriteDAO praisePointWriteDAO;

	@Override
	public Long insertPraisePointWithTx(PraisePointPO po) {
		
		int i ;
		try {
				i = praisePointWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updatePraisePointWithTx(PraisePointPO po) {
		int i;
		i = praisePointWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deletePraisePointWithTx(PraisePointPO po) {
		int i;
		i = praisePointWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int increasePraisePointWithTx(double delta,Long userId,String ciphertextNew) {
		return praisePointWriteDAO.increasePraisePointWithTx(delta,userId,ciphertextNew);
	}	
}
	