package com.egeo.components.user.business;

import java.util.List;
	
import com.egeo.components.user.dto.InfoSendWayDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface InfoSendWayManage {

	public InfoSendWayDTO findInfoSendWayById(InfoSendWayDTO dto);	

	public PageResult<InfoSendWayDTO> findInfoSendWayOfPage(InfoSendWayDTO dto,Pagination page);

	public List<InfoSendWayDTO> findInfoSendWayAll(InfoSendWayDTO dto);

	Long insertInfoSendWayWithTx(InfoSendWayDTO dto);

	int updateInfoSendWayWithTx(InfoSendWayDTO dto);

	int deleteInfoSendWayWithTx(InfoSendWayDTO dto);
}
	