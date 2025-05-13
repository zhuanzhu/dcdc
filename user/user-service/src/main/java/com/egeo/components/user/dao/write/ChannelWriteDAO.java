package com.egeo.components.user.dao.write;


import com.egeo.components.user.po.ChannelPO;
import com.egeo.orm.BaseWriteDAO;

public interface ChannelWriteDAO extends BaseWriteDAO<ChannelPO> {

	void refreshChannelDailyDownloadCount();
}
	