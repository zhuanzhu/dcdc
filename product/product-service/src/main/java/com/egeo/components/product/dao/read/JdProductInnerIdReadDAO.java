package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.JdProductInnerIdPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface JdProductInnerIdReadDAO extends BaseReadDAO<JdProductInnerIdPO>{
    List<JdProductInnerIdPO> findJdProductInnerIdAllByJdSkuIdList(@Param("skuIdList")List<Long> skuIdList , @Param("page") Pagination page);

    Integer findSuProfitById(@Param("suId")Long suId);

    Long findPuIdByJdSkuId(@Param("skuId")Long skuId);
}
	
