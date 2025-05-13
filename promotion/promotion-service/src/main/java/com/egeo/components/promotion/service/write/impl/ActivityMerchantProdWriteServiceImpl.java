package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.ActivityMerchantProdWriteService;
import com.egeo.components.promotion.manage.write.ActivityMerchantProdWriteManage;
import com.egeo.components.promotion.converter.ActivityMerchantProdConverter;
import com.egeo.components.promotion.dto.ActivityMerchantProdDTO;

@Service("activityMerchantProdWriteService")
public class ActivityMerchantProdWriteServiceImpl implements ActivityMerchantProdWriteService {
	@Autowired
	private ActivityMerchantProdWriteManage activityMerchantProdWriteManage;

	@Override
	public Long saveActivityMerchantProdWithTx(ActivityMerchantProdDTO dto) {
		return activityMerchantProdWriteManage.saveActivityMerchantProd(ActivityMerchantProdConverter.toPO(dto));
	}

	@Override
	public Integer deleteByIdWithTx(ActivityMerchantProdDTO dto) {
		return activityMerchantProdWriteManage.deleteById(ActivityMerchantProdConverter.toPO(dto));
	}

	@Override
	public Long updateActivityMerchantProdWithTx(ActivityMerchantProdDTO dto) {
		return activityMerchantProdWriteManage.updateActivityMerchantProd(ActivityMerchantProdConverter.toPO(dto));
	}
	/**
	 * 根据活动id删除活动商品
	 * @param activityId
	 * @return
	 */
	@Override
	public Integer deleteActivityMerchantByActivityIdWithTx(Long activityId) {
		return activityMerchantProdWriteManage.deleteActivityMerchantByActivityId(activityId);
	}
	/**
	 * 根据商品id删除活动商品关系表
	 * @param merchantProductId
	 * @return
	 */
	@Override
	public int deleteByMerchantProductIdWithTx(Long merchantProductId) {
		return activityMerchantProdWriteManage.deleteByMerchantProductIdWithTx(merchantProductId);
	}
}
	