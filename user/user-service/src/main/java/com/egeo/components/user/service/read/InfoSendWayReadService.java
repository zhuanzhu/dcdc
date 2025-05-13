package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.InfoSendWayDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface InfoSendWayReadService {

	public InfoSendWayDTO findInfoSendWayById(InfoSendWayDTO dto);

	public PageResult<InfoSendWayDTO> findInfoSendWayOfPage(InfoSendWayDTO dto,Pagination page);

	public List<InfoSendWayDTO> findInfoSendWayAll(InfoSendWayDTO dto);
}
	