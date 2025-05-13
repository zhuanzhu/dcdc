package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.MerchantProductFeedbackPO;


public interface MerchantProductFeedbackWriteManage {

	Long insertMerchantProductFeedbackWithTx(MerchantProductFeedbackPO po);

	int updateMerchantProductFeedbackWithTx(MerchantProductFeedbackPO po);

	int deleteMerchantProductFeedbackWithTx(MerchantProductFeedbackPO po);
}
	