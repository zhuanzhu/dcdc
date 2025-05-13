package com.egeo.components.product.manage.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.dao.write.MerchantProdCauseWriteDAO;
import com.egeo.components.product.dao.write.MerchantProductWriteDAO;
import com.egeo.components.product.enums.SUConstant;
import com.egeo.components.product.manage.write.MerchantProdCauseWriteManage;
import com.egeo.components.product.po.MerchantProdCausePO;
import com.egeo.components.product.po.MerchantProductPO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.log.XLogger;

@Service
public class MerchantProdCauseWriteManageImpl implements MerchantProdCauseWriteManage {
	private static final XLogger logger = XLogger.getLogger(MerchantProdCauseWriteManageImpl.class);
	@Autowired
	private MerchantProdCauseWriteDAO merchantProdCauseWriteDAO;
	
	@Autowired
	private MerchantProductWriteDAO merchantProductWriteDAO;

	@Override
	public Long insertMerchantProdCauseWithTx(MerchantProdCausePO po) {
		//更改su草稿状态
		MerchantProductPO merchantProductPO = new MerchantProductPO();
		merchantProductPO.setId(po.getMerchantProdId());
		merchantProductPO.setStatus(SUConstant.SU_STATUS_PRE_RETURN_CASH.getStatus());
		merchantProductWriteDAO.update(merchantProductPO);
		int i ;
		try {
				i = merchantProdCauseWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateMerchantProdCauseWithTx(MerchantProdCausePO po) {
		int i;
		i = merchantProdCauseWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteMerchantProdCauseWithTx(MerchantProdCausePO po) {
		int i;
		i = merchantProdCauseWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	