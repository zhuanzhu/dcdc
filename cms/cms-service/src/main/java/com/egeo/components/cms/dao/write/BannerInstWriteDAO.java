package com.egeo.components.cms.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.BannerInstPO;
import com.egeo.orm.BaseWriteDAO;

public interface BannerInstWriteDAO extends BaseWriteDAO<BannerInstPO> {

	/**
	 * 根据instId删除轮播图实例关系
	 * @param instId
	 * @return
	 */
	int deleteBannerInstByinstId(@Param("instId")Long instId);
}
	