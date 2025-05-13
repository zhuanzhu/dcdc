package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.MerchantProdAttValueWriteManage;
import com.egeo.components.product.dao.write.MerchantProdAttValueWriteDAO;
import com.egeo.components.product.po.MerchantProdAttValuePO;
import com.egeo.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MerchantProdAttValueWriteManageImpl implements MerchantProdAttValueWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProdAttValueWriteDAO merchantProdAttValueWriteDAO;

	@Override
	public Long insertMerchantProdAttValueWithTx(MerchantProdAttValuePO po) {
		
		int i ;
		try {
				i = merchantProdAttValueWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateMerchantProdAttValueWithTx(MerchantProdAttValuePO po) {
		int i;
		i = merchantProdAttValueWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteMerchantProdAttValueWithTx(MerchantProdAttValuePO po) {
		int i;
		i = merchantProdAttValueWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public void insertList(List<String> my) {
		int i=0;
		List<MerchantProdAttValuePO> list = new ArrayList<>();
		for(String str:my){
			i++;
			MerchantProdAttValuePO po = new MerchantProdAttValuePO();
			po.setAttValueCustom(str);
			po.setMerchantProdAttNameId(1L);
			po.setId(Long.valueOf(i));
			list.add(po);
		}
		logger.info("***********************开始更新数据");
		merchantProdAttValueWriteDAO.insertList(list);
		logger.info("***********************更新数据成功");
	}
}
	