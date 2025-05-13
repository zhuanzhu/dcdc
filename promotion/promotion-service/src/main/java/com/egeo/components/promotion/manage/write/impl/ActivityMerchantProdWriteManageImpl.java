package com.egeo.components.promotion.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.write.ActivityMerchantProdWriteManage;
import com.egeo.components.promotion.dao.write.ActivityMerchantProdWriteDAO;
import com.egeo.components.promotion.po.ActivityMerchantProdPO;

@Service
public class ActivityMerchantProdWriteManageImpl implements ActivityMerchantProdWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ActivityMerchantProdWriteDAO activityMerchantProdWriteDAO;
	
	@Override
	public Long saveActivityMerchantProd(ActivityMerchantProdPO po) {
		activityMerchantProdWriteDAO.insert(po);
		return po.getId();
	}

	@Override
	public Integer deleteById(ActivityMerchantProdPO po) {
		return activityMerchantProdWriteDAO.delete(po);
	}

	@Override
	public Long updateActivityMerchantProd(ActivityMerchantProdPO po) {
		activityMerchantProdWriteDAO.update(po);
		return po.getId();
	}
	/**
	 * 根据活动id删除活动商品
	 * @param activityId
	 * @return
	 */
	@Override
	public Integer deleteActivityMerchantByActivityId(Long activityId) {
		return activityMerchantProdWriteDAO.deleteActivityMerchantByActivityId(activityId);
	}
	/**
	 * 根据商品id删除活动商品关系表
	 * @param merchantProductId
	 * @return
	 */
	@Override
	public int deleteByMerchantProductIdWithTx(Long merchantProductId) {
		ActivityMerchantProdPO po = new ActivityMerchantProdPO();
		po.setMerchantProdId(merchantProductId);
		return activityMerchantProdWriteDAO.deleteByPara(po);
	}
}
	