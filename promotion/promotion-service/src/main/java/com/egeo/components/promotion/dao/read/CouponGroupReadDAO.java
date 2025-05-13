package com.egeo.components.promotion.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.promotion.po.CouponGroupPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface CouponGroupReadDAO extends BaseReadDAO<CouponGroupPO>{

	/**
	 * 查询优惠卷列表(模糊查询)
	 * @param dto
	 * @param page
	 * @return
	 */
	int countOfPageByBlurry(@Param("po")CouponGroupPO po);

	List<CouponGroupPO> findOfPageByBlurry(@Param("po")CouponGroupPO po, @Param("page")Pagination page);
}
	