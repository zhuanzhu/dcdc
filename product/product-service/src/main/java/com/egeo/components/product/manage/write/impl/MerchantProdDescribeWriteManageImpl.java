package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.MerchantProdDescribeWriteManage;
import com.egeo.components.product.dao.write.MerchantProdDescribeWriteDAO;
import com.egeo.components.product.po.MerchantProdDescribePO;
import com.egeo.exception.BusinessException;

import java.util.List;

@Service
public class MerchantProdDescribeWriteManageImpl implements MerchantProdDescribeWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProdDescribeWriteDAO merchantProdDescribeWriteDAO;

	@Override
	public Long insertMerchantProdDescribeWithTx(MerchantProdDescribePO po) {
		
		int i ;
		try {
				i = merchantProdDescribeWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateMerchantProdDescribeWithTx(MerchantProdDescribePO po) {
		int i;
		i = merchantProdDescribeWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteMerchantProdDescribeWithTx(MerchantProdDescribePO po) {
		int i;
		i = merchantProdDescribeWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public void saveMerchantProdDescribe(List<MerchantProdDescribePO> merchantProdDescribePOList) {
		try{
		merchantProdDescribeWriteDAO.saveMerchantProdDescribe(merchantProdDescribePOList);
		}catch (Exception e){
			logger.error("saveMerchantProdDescribe失败,e:"+e.getMessage());
		}
	}

	@Override
	public void updateMerchantProdDescribeSWithTx(MerchantProdDescribePO merchantProdDescribePO) {
		merchantProdDescribeWriteDAO.updateMerchantProdDescribeSWithTx(merchantProdDescribePO);
	}
}
	