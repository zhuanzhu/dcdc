package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.SendWayTypePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SendWayTypeReadManage {

	public SendWayTypePO findSendWayTypeById(SendWayTypePO po);

	public PageResult<SendWayTypePO> findSendWayTypeOfPage(SendWayTypePO po,Pagination page);

	public List<SendWayTypePO> findSendWayTypeAll(SendWayTypePO po);
	/**
	 * 根据消息id查询消息发送方式
	 * @param id
	 * @return
	 */
	public List<String> findSendWayTypeByInfoId(Long infoId);
}
	