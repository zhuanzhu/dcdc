package com.egeo.components.config.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.config.po.CardSaltPO;
import com.egeo.orm.BaseReadDAO;

public interface CardSaltReadDAO extends BaseReadDAO<CardSaltPO>{

	/**
	 * 根据uuid查询卡券盐
	 * @param uuid
	 * @return
	 */
	CardSaltPO queryCardSaltByUUID(@Param("uuid")String uuid);
}
	