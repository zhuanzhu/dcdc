package com.egeo.components.cms.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.BannerCompanyPO;
import com.egeo.orm.BaseWriteDAO;

public interface BannerCompanyWriteDAO extends BaseWriteDAO<BannerCompanyPO> {

	/**
	 * 根据instId删除轮播图实例关系
	 * @param instId
	 * @return
	 */
	int updown(@Param("po")BannerCompanyPO po); 
}
	