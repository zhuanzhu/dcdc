package com.egeo.components.product.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StoreMenuNodeStandardUnitWriteManage;
import com.egeo.components.product.dao.write.StoreMenuNodeStandardUnitWriteDAO;
import com.egeo.components.product.po.StoreMenuNodeStandardUnitPO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

@Service
public class StoreMenuNodeStandardUnitWriteManageImpl implements StoreMenuNodeStandardUnitWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StoreMenuNodeStandardUnitWriteDAO storeMenuNodeStandardUnitWriteDAO;

	@Override
	public Long insertStoreMenuNodeStandardUnitWithTx(StoreMenuNodeStandardUnitPO po) {
		
		int i ;
		try {
				i = storeMenuNodeStandardUnitWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStoreMenuNodeStandardUnitWithTx(StoreMenuNodeStandardUnitPO po) {
		int i;
		i = storeMenuNodeStandardUnitWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStoreMenuNodeStandardUnitWithTx(StoreMenuNodeStandardUnitPO po) {
		int i;
		i = storeMenuNodeStandardUnitWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int insertAllWithTx(Long storeMenuNodeId, List<Long> standardUnitIds, Long platformId) {
		// 根据门店菜单id删除su商品
		StoreMenuNodeStandardUnitPO storeMenuNodeStandardUnitPO = new StoreMenuNodeStandardUnitPO();
		storeMenuNodeStandardUnitPO.setStoreMenuNodeId(storeMenuNodeId);
		storeMenuNodeStandardUnitPO.setPlatformId(platformId);
		storeMenuNodeStandardUnitWriteDAO.deleteByPara(storeMenuNodeStandardUnitPO);
		if(EmptyUtil.isNotEmpty(standardUnitIds))
			return storeMenuNodeStandardUnitWriteDAO.insertAllWithTx(storeMenuNodeId, standardUnitIds, platformId);
		return 0;
	}	
}
	