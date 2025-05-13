package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.ChannelPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ChannelReadManage {

	public ChannelPO findChannelById(ChannelPO po);

	public PageResult<ChannelPO> findChannelOfPage(ChannelPO po,Pagination page);

	public List<ChannelPO> findChannelAll(ChannelPO po);
	/**
	 * 根据版本类型：1、安卓 2、ios查询渠道信息
	 * @param vo
	 * @param req
	 * @return
	 */
	public List<ChannelPO> findChannelByType(int type);
}
	