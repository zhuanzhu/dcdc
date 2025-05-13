package com.egeo.components.product.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.MerchantProductCompanyWriteManage;
import com.egeo.components.product.dao.write.MerchantProductCompanyWriteDAO;
import com.egeo.components.product.po.MerchantProductCompanyPO;
import com.egeo.exception.BusinessException;

@Service
public class MerchantProductCompanyWriteManageImpl implements MerchantProductCompanyWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProductCompanyWriteDAO merchantProductCompanyWriteDAO;

	@Override
	public Long insertMerchantProductCompanyWithTx(MerchantProductCompanyPO po) {
		
		int i ;
		try {
				i = merchantProductCompanyWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateMerchantProductCompanyWithTx(MerchantProductCompanyPO po) {
		int i;
		i = merchantProductCompanyWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteMerchantProductCompanyWithTx(MerchantProductCompanyPO po) {
		int i;
		i = merchantProductCompanyWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据拼接的su草稿企业关系id集合批量删除
	 * @param merchantProductCompanyIds
	 * @return
	 */
	@Override
	public int deleteByMerchantProductCompanyIdsWithTx(List<Long> merchantProductCompanyIds) {
		// TODO Auto-generated method stub
		return merchantProductCompanyWriteDAO.deleteByMerchantProductCompanyIdsWithTx(merchantProductCompanyIds);
	}

	@Override
	public void saveMerchantProductCompany(List<MerchantProductCompanyPO> merchantProductCompanyPOList) {
		try{
		merchantProductCompanyWriteDAO.saveMerchantProductCompany( merchantProductCompanyPOList);
		}catch (Exception e){
			logger.error("saveMerchantProductCompany失败,e:"+e.getMessage());
		}
	}
}
	