package com.egeo.components.order.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.order.po.SoRefundPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface SoRefundReadDAO extends BaseReadDAO<SoRefundPO>{

	int countOfPageByBlurry(@Param("po")SoRefundPO po, @Param("userIdList")List<Long> userIdList);

	List<SoRefundPO> findOfPageByBlurry(@Param("po")SoRefundPO po, @Param("userIdList")List<Long> userIdList, @Param("page")Pagination page);
}
	