package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.SkuAttValueWriteManage;
import com.egeo.components.product.dao.write.SkuAttValueWriteDAO;
import com.egeo.components.product.po.SkuAttValuePO;
import com.egeo.exception.BusinessException;

import java.util.List;

@Service
public class SkuAttValueWriteManageImpl implements SkuAttValueWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SkuAttValueWriteDAO skuAttValueWriteDAO;

	@Override
	public Long insertSkuAttValueWithTx(SkuAttValuePO po) {
		
		int i ;
		try {
				i = skuAttValueWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSkuAttValueWithTx(SkuAttValuePO po) {
		int i;
		i = skuAttValueWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSkuAttValueWithTx(SkuAttValuePO po) {
		int i;
		i = skuAttValueWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public void saveSkuAttValuePO(List<SkuAttValuePO> skuAttValuePOList) {
		try{
		skuAttValueWriteDAO.saveSkuAttValuePO(skuAttValuePOList);
		}catch (Exception e){
			logger.error("saveSkuAttValuePO失败,e:"+e.getMessage());
		}
	}
}
	