package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.MerchantProductStoreWriteManage;
import com.egeo.components.product.dao.write.MerchantProductStoreWriteDAO;
import com.egeo.components.product.po.MerchantProductStorePO;
import com.egeo.exception.BusinessException;

import java.util.List;

@Service
public class MerchantProductStoreWriteManageImpl implements MerchantProductStoreWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProductStoreWriteDAO merchantProductStoreWriteDAO;

	@Override
	public Long insertMerchantProductStoreWithTx(MerchantProductStorePO po) {
		
		int i ;
		try {
				i = merchantProductStoreWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateMerchantProductStoreWithTx(MerchantProductStorePO po) {
		int i;
		i = merchantProductStoreWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteMerchantProductStoreWithTx(MerchantProductStorePO po) {
		int i;
		i = merchantProductStoreWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public void saveMerchantProductStore(List<MerchantProductStorePO> merchantProductStorePOList) {
		try{
		merchantProductStoreWriteDAO.saveMerchantProductStore(merchantProductStorePOList);
		}catch (Exception e){
			logger.error("saveMerchantProductStore失败,e:"+e.getMessage());
		}
	}
}
	