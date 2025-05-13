package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.JdProductPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface JdProductReadManage {

	public JdProductPO findJdProductById(JdProductPO po);

	public PageResult<JdProductPO> findJdProductOfPage(JdProductPO po,Pagination page);

	public List<JdProductPO> findJdProductAll(JdProductPO po);

    List<Long> findAllIdList();

	List<JdProductPO> findJdProductListByIds(List<Long> skuIdList);

    PageResult<JdProductPO> getJdProductListByParams(Pagination page, Long skuId, List<String> skuNameList, Long updateTimeStart, Long updateTimeEnd, Integer profitStart, Integer profitEnd, Integer state, Integer sycStatus, Long catId, Integer isShow);

    Integer findJdProductCountByProfit(Integer profit);

    List<JdProductPO> findJdProductListByProfit(Integer integer, Integer start, Integer profit);

    List<Long> findHavedIdListByIdList(List<Long> ids);
}
	