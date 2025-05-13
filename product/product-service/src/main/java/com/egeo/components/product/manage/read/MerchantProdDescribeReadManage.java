package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.MerchantProdDescribePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantProdDescribeReadManage {

	public MerchantProdDescribePO findMerchantProdDescribeById(MerchantProdDescribePO po);

	public PageResult<MerchantProdDescribePO> findMerchantProdDescribeOfPage(MerchantProdDescribePO po,Pagination page);

	public List<MerchantProdDescribePO> findMerchantProdDescribeAll(MerchantProdDescribePO po);
	/**
	 * 根据su草稿id查询su草稿详情
	 * @param merchantProductId
	 * @return
	 */
	public MerchantProdDescribePO findByMerchantProdId(Long merchantProductId);
}
	