package com.egeo.components.user.manage.write;

import java.util.List;

import com.egeo.components.user.po.InfoTemplatePO;


public interface InfoTemplateWriteManage {

	Long insertInfoTemplateWithTx(InfoTemplatePO po);

	int updateInfoTemplateWithTx(InfoTemplatePO po,List<Long> sendWayIds);

	int deleteInfoTemplateWithTx(InfoTemplatePO po);
	/**
	 * 根据消息模版id启用停用消息模版
	 * @param infoTemplateId
	 * @return
	 */
	int isStartByIdWithTx(Long infoTemplateId);
}
	