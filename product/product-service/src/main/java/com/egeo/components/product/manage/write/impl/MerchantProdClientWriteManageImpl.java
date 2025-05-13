package com.egeo.components.product.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.MerchantProdClientWriteManage;
import com.egeo.components.product.dao.write.MerchantProdClientWriteDAO;
import com.egeo.components.product.po.MerchantProdClientPO;
import com.egeo.exception.BusinessException;

@Service
public class MerchantProdClientWriteManageImpl implements MerchantProdClientWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProdClientWriteDAO merchantProdClientWriteDAO;

	@Override
	public Long insertMerchantProdClientWithTx(MerchantProdClientPO po) {
		
		int i ;
		try {
				i = merchantProdClientWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateMerchantProdClientWithTx(MerchantProdClientPO po) {
		int i;
		i = merchantProdClientWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteMerchantProdClientWithTx(MerchantProdClientPO po) {
		int i;
		i = merchantProdClientWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据su草稿与客户端的关系id集合批量删除su草稿与客户端的关系
	 * @param merchantProdClientIds
	 * @return
	 */
	@Override
	public int deleteByMerchantProdClientIdsWithTx(List<Long> merchantProdClientIds) {
		// TODO Auto-generated method stub
		return merchantProdClientWriteDAO.deleteByMerchantProdClientIdsWithTx(merchantProdClientIds);
	}

	@Override
	public void saveMerchantProdClient(List<MerchantProdClientPO> merchantProdClientPOList) {
		try{
		merchantProdClientWriteDAO.saveMerchantProdClient(merchantProdClientPOList);
		}catch (Exception e){
			logger.error("saveMerchantProdClient失败,e:"+e.getMessage());
		}
	}
}
	