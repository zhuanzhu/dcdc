package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.SkuAttNameWriteManage;
import com.egeo.components.product.dao.write.SkuAttNameWriteDAO;
import com.egeo.components.product.po.SkuAttNamePO;
import com.egeo.exception.BusinessException;

import java.util.List;

@Service
public class SkuAttNameWriteManageImpl implements SkuAttNameWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SkuAttNameWriteDAO skuAttNameWriteDAO;

	@Override
	public Long insertSkuAttNameWithTx(SkuAttNamePO po) {
		
		int i ;
		try {
				i = skuAttNameWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSkuAttNameWithTx(SkuAttNamePO po) {
		int i;
		i = skuAttNameWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSkuAttNameWithTx(SkuAttNamePO po) {
		int i;
		i = skuAttNameWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public void saveSkuAttName(List<SkuAttNamePO> skuAttNamePOList) {
		try{
		skuAttNameWriteDAO.saveSkuAttName(skuAttNamePOList);
		}catch (Exception e){
			logger.error("saveSkuAttName失败,e:"+e.getMessage());
		}
	}
}
	