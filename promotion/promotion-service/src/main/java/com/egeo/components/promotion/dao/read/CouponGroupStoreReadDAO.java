package com.egeo.components.promotion.dao.read;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.promotion.po.CouponGroupStorePO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface CouponGroupStoreReadDAO extends BaseReadDAO<CouponGroupStorePO>{

    List<CouponGroupStorePO> findCouponGroupStoreByKey(Map<String, Object> key , @Param("page") Pagination page);

}
	
