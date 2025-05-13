package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.MerchantProductFeedbackPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantProductFeedbackReadManage {

	public MerchantProductFeedbackPO findMerchantProductFeedbackById(MerchantProductFeedbackPO po);

	public PageResult<MerchantProductFeedbackPO> findMerchantProductFeedbackOfPage(MerchantProductFeedbackPO po,Pagination page);

	public List<MerchantProductFeedbackPO> findMerchantProductFeedbackAll(MerchantProductFeedbackPO po);
}
	