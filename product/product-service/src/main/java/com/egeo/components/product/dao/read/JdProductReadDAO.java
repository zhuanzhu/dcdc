package com.egeo.components.product.dao.read;

import com.egeo.components.product.po.JdProductPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JdProductReadDAO extends BaseReadDAO<JdProductPO>{
    List<Long> findAllIdList();

    List<JdProductPO> findJdProductListByIds(@Param("ids")List<Long> skuIdList);

    List<JdProductPO> getJdProductListByParams(@Param("page")Pagination page, @Param("skuId") Long skuId, @Param("skuNameList") List<String> skuNameList, @Param("updateTimeStart") Long updateTimeStart,
                                               @Param("updateTimeEnd") Long updateTimeEnd, @Param("profitStart") Integer profitStart,
                                               @Param("profitEnd") Integer profitEnd, @Param("state") Integer state,
                                               @Param("sycStatus") Integer sycStatus, @Param("catId") Long catId, @Param("isShow") Integer isShow);

    Integer getJdProductListByParamsCount(@Param("skuId") Long skuId, @Param("skuNameList") List<String> skuNameList, @Param("updateTimeStart") Long updateTimeStart,
                                       @Param("updateTimeEnd") Long updateTimeEnd, @Param("profitStart") Integer profitStart,
                                       @Param("profitEnd") Integer profitEnd, @Param("state") Integer state,
                                       @Param("sycStatus") Integer sycStatus, @Param("catId") Long catId, @Param("isShow") Integer isShow);

    Integer findJdProductCountByProfit(@Param("profit")Integer profit);

    List<JdProductPO> findJdProductListByProfit(@Param("profit")Integer profit,@Param("start") Integer start, @Param("pageSize")Integer pageSize);

    List<Long> findHavedIdListByIdList(@Param("ids")List<Long> ids);
}
	
