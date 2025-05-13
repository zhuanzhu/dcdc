package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.condition.CardStampsAdministrationCondition;
import com.egeo.components.product.po.CardStampsAdministrationPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface CardStampsAdministrationReadDAO extends BaseReadDAO<CardStampsAdministrationPO>{
	/**
	 * 分页查询卡券模版信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	List<CardStampsAdministrationCondition> findCardStampsAdministrationConditionOfPage(@Param("po")CardStampsAdministrationPO po,
			@Param("page")Pagination page);
}
	