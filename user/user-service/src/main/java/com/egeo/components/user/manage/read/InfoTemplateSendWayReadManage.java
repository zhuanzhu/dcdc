package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.InfoTemplateSendWayPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface InfoTemplateSendWayReadManage {

	public InfoTemplateSendWayPO findInfoTemplateSendWayById(InfoTemplateSendWayPO po);

	public PageResult<InfoTemplateSendWayPO> findInfoTemplateSendWayOfPage(InfoTemplateSendWayPO po,Pagination page);

	public List<InfoTemplateSendWayPO> findInfoTemplateSendWayAll(InfoTemplateSendWayPO po);
	/**
	 * 根据消息模版id查询发送方式
	 * @param infoTemplateId
	 * @return
	 */
	public List<String> sendWayNameByInfoTemplateId(Long infoTemplateId);
}
	