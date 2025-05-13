package com.egeo.components.product.manage.read;

import java.util.List;

import com.egeo.components.product.condition.MerchantProductTagCondition;
import com.egeo.components.product.po.MerchantProductTagPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantProductTagReadManage {

	public MerchantProductTagPO findMerchantProductTagById(MerchantProductTagPO po);

	public PageResult<MerchantProductTagPO> findMerchantProductTagOfPage(MerchantProductTagPO po,Pagination page);

	public List<MerchantProductTagPO> findMerchantProductTagAll(MerchantProductTagPO po);
	/**
	 * 根据su草稿id查询标签信息集合
	 * @param id
	 * @return
	 */
	public List<MerchantProductTagCondition> findTagAllByMerchantProductId(Long merchantProductId);
}
	