package com.egeo.components.user.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.condition.ChannelActivityCondition;
import com.egeo.components.user.po.ChannelActivityPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface ChannelActivityReadDAO extends BaseReadDAO<ChannelActivityPO>{

	int countChannelActivityConditionOfPage(@Param("po")ChannelActivityCondition po);

	List<ChannelActivityCondition> findChannelActivityConditionOfPage(
			@Param("po")ChannelActivityCondition po, @Param("page")Pagination page);

	ChannelActivityPO findByShortCode(@Param("shortCode")String shortCode);
}
	