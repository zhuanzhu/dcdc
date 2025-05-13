package com.egeo.components.user.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.user.dto.InfoTemplateDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface InfoTemplateManage {

	public Map<String, Object> findInfoTemplateById(Long infoTemplateId);	

	public PageResult<Map<String, Object>> findInfoTemplateOfPage(InfoTemplateDTO dto,Pagination page);

	public List<InfoTemplateDTO> findInfoTemplateAll(InfoTemplateDTO dto);

	Long insertInfoTemplateWithTx(InfoTemplateDTO dto);

	int updateInfoTemplateWithTx(InfoTemplateDTO dto,List<Long> sendWayIds);

	int deleteInfoTemplateWithTx(InfoTemplateDTO dto);
	/**
	 * 根据消息模版id启用停用消息模版
	 * @param infoTemplateId
	 * @return
	 */
	public int isStartByIdWithTx(Long infoTemplateId);
}
	