package com.egeo.components.user.manage.read;

import java.util.List;

import com.egeo.components.user.condition.ChannelActivityCondition;
import com.egeo.components.user.po.ChannelActivityPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ChannelActivityReadManage {

	public ChannelActivityPO findChannelActivityById(ChannelActivityPO po);

	public PageResult<ChannelActivityCondition> findChannelActivityOfPage(ChannelActivityCondition po,Pagination page);

	public List<ChannelActivityPO> findChannelActivityAll(ChannelActivityPO po);
	/**
	 * 据活动短码查询活动
	 * @param shortCode
	 * @return
	 */
	public ChannelActivityPO findByShortCode(String shortCode);
}
	