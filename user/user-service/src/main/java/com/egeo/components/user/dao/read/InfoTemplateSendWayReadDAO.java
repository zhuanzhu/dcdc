package com.egeo.components.user.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.InfoTemplateSendWayPO;
import com.egeo.orm.BaseReadDAO;

public interface InfoTemplateSendWayReadDAO extends BaseReadDAO<InfoTemplateSendWayPO>{
	/**
	 * 根据消息模版id查询发送方式
	 * @param infoTemplateId
	 * @return
	 */
	List<String> sendWayNameByInfoTemplateId(@Param("infoTemplateId")Long infoTemplateId);
}
	