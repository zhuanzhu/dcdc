package com.egeo.components.product.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.BlessingCoinBannerCompanyPO;
import com.egeo.orm.BaseWriteDAO;

public interface BlessingCoinBannerCompanyWriteDAO extends BaseWriteDAO<BlessingCoinBannerCompanyPO> {
	/**
	 * 根据公司id集合和轮播图id删除轮播图公司集合删除非公司集合中的关系
	 * @param id
	 * @param companyList
	 * @return
	 */
	int delByBlessingCoinBannerCompanyIds(@Param("blessingCoinBannerId")Long blessingCoinBannerId, @Param("ids")List<Long> companyList);
}
	