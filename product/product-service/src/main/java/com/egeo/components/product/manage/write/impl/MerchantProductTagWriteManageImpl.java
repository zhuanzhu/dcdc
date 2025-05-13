package com.egeo.components.product.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.MerchantProductTagWriteManage;
import com.egeo.components.product.dao.write.MerchantProductTagWriteDAO;
import com.egeo.components.product.po.MerchantProductTagPO;
import com.egeo.exception.BusinessException;

@Service
public class MerchantProductTagWriteManageImpl implements MerchantProductTagWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProductTagWriteDAO merchantProductTagWriteDAO;

	@Override
	public Long insertMerchantProductTagWithTx(MerchantProductTagPO po) {
		
		int i ;
		try {
				i = merchantProductTagWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateMerchantProductTagWithTx(MerchantProductTagPO po) {
		int i;
		i = merchantProductTagWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteMerchantProductTagWithTx(MerchantProductTagPO po) {
		int i;
		i = merchantProductTagWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据商品id和标签id集合删除商品标签关系
	 * @param tags
	 * @return
	 */
	@Override
	public int delByTags(Long merchantProductId,List<Long> tags) {
		// TODO Auto-generated method stub
		return merchantProductTagWriteDAO.delByTags(merchantProductId,tags);
	}
	/**
	 * 根据商品id删除商品标签关系
	 * @param id
	 * @return
	 */
	@Override
	public int delByMerchantProductId(Long merchantProductId) {
		MerchantProductTagPO merchantProductTagPO = new MerchantProductTagPO();
		merchantProductTagPO.setMerchantProductId(merchantProductId);
		return merchantProductTagWriteDAO.deleteByPara(merchantProductTagPO);
	}	
}
	