package com.egeo.components.user.business;

import java.util.List;
	
import com.egeo.components.user.dto.InfoTemplateSendWayDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface InfoTemplateSendWayManage {

	public InfoTemplateSendWayDTO findInfoTemplateSendWayById(InfoTemplateSendWayDTO dto);	

	public PageResult<InfoTemplateSendWayDTO> findInfoTemplateSendWayOfPage(InfoTemplateSendWayDTO dto,Pagination page);

	public List<InfoTemplateSendWayDTO> findInfoTemplateSendWayAll(InfoTemplateSendWayDTO dto);

	Long insertInfoTemplateSendWayWithTx(InfoTemplateSendWayDTO dto);

	int updateInfoTemplateSendWayWithTx(InfoTemplateSendWayDTO dto);

	int deleteInfoTemplateSendWayWithTx(InfoTemplateSendWayDTO dto);
}
	