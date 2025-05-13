package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.InfoTemplateSendWayDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface InfoTemplateSendWayReadService {

	public InfoTemplateSendWayDTO findInfoTemplateSendWayById(InfoTemplateSendWayDTO dto);

	public PageResult<InfoTemplateSendWayDTO> findInfoTemplateSendWayOfPage(InfoTemplateSendWayDTO dto,Pagination page);

	public List<InfoTemplateSendWayDTO> findInfoTemplateSendWayAll(InfoTemplateSendWayDTO dto);
	/**
	 * 根据消息模版id查询发送方式
	 * @param infoTemplateId
	 * @return
	 */
	public List<String> sendWayNameByInfoTemplateId(Long infoTemplateId);
}
	