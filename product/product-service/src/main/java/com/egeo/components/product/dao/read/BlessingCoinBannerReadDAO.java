package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.BlessingCoinBannerPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface BlessingCoinBannerReadDAO extends BaseReadDAO<BlessingCoinBannerPO>{
	/**
	 * 客户端查询所有有效轮播图信息
	 * @param vo
	 * @param req
	 * @return
	 */
	List<BlessingCoinBannerPO> findBlessingCoinBannerAllApp(@Param("po")BlessingCoinBannerPO po , @Param("page") Pagination page);
}
	
