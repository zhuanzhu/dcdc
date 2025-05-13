package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.JdProductInnerIdPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface JdProductInnerIdReadManage {

	public JdProductInnerIdPO findJdProductInnerIdById(JdProductInnerIdPO po);

	public PageResult<JdProductInnerIdPO> findJdProductInnerIdOfPage(JdProductInnerIdPO po,Pagination page);

	public List<JdProductInnerIdPO> findJdProductInnerIdAll(JdProductInnerIdPO po);

    List<JdProductInnerIdPO> findJdProductInnerIdAllByJdSkuIdList(List<Long> skuIdList);

    Integer findSuProfitById(Long suId);

    Long findPuIdByJdSkuId(Long skuId);
}
	