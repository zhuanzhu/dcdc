package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.TagWriteManage;
import com.egeo.components.product.dao.write.CategoryTagWriteDAO;
import com.egeo.components.product.dao.write.MerchantProductTagWriteDAO;
import com.egeo.components.product.dao.write.StandardUnitCombinationTagWriteDAO;
import com.egeo.components.product.dao.write.StandardUnitTagWriteDAO;
import com.egeo.components.product.dao.write.TagWriteDAO;
import com.egeo.components.product.po.CategoryTagPO;
import com.egeo.components.product.po.MerchantProductTagPO;
import com.egeo.components.product.po.StandardUnitCombinationTagPO;
import com.egeo.components.product.po.StandardUnitTagPO;
import com.egeo.components.product.po.TagPO;
import com.egeo.exception.BusinessException;

@Service
public class TagWriteManageImpl implements TagWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TagWriteDAO tagWriteDAO;
	
	@Autowired
	private StandardUnitTagWriteDAO standardUnitTagWriteDAO;
	
	@Autowired
	private StandardUnitCombinationTagWriteDAO standardUnitCombinationTagWriteDAO;
	
	@Autowired
	private MerchantProductTagWriteDAO merchantProductTagWriteDAO;
	
	@Autowired
	private CategoryTagWriteDAO categoryTagWriteDAO;

	@Override
	public Long insertTagWithTx(TagPO po) {
		
		int i ;
		try {
				i = tagWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateTagWithTx(TagPO po) {
		int i;
		i = tagWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteTagWithTx(TagPO po) {
		int i;
		i = tagWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据标签id清除记录
	 * @param tagId
	 * @return
	 */
	@Override
	public Boolean tagClearRecordByTagIdWithTx(Long tagId) {
		boolean inTrue = false;
		StandardUnitTagPO standardUnitTagPO = new StandardUnitTagPO();
		standardUnitTagPO.setTagId(tagId);
		standardUnitTagWriteDAO.deleteByPara(standardUnitTagPO);
		
		StandardUnitCombinationTagPO standardUnitCombinationTagPO = new StandardUnitCombinationTagPO();
		standardUnitCombinationTagPO.setTagId(tagId);
		standardUnitCombinationTagWriteDAO.deleteByPara(standardUnitCombinationTagPO);
		
		MerchantProductTagPO merchantProductTagPO = new MerchantProductTagPO();
		merchantProductTagPO.setTagId(tagId);
		merchantProductTagWriteDAO.deleteByPara(merchantProductTagPO);
		
		CategoryTagPO categoryTagPO = new CategoryTagPO();
		categoryTagPO.setTagId(tagId);
		categoryTagWriteDAO.deleteByPara(categoryTagPO);
		inTrue = true;
		return inTrue;
	}	
}
	