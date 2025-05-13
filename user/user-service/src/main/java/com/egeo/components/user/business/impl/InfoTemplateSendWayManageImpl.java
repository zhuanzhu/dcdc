package com.egeo.components.user.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.user.business.InfoTemplateSendWayManage;
import com.egeo.components.user.facade.InfoTemplateSendWayFacade;
import com.egeo.components.user.dto.InfoTemplateSendWayDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("infoTemplateSendWay")
public class InfoTemplateSendWayManageImpl implements InfoTemplateSendWayManage{

	
	@Resource(name="infoTemplateSendWayFacade")
	private InfoTemplateSendWayFacade infoTemplateSendWayFacade;

	@Override
	public InfoTemplateSendWayDTO findInfoTemplateSendWayById(InfoTemplateSendWayDTO dto) {
		return infoTemplateSendWayFacade.findInfoTemplateSendWayById(dto);
	}

	@Override
	public PageResult<InfoTemplateSendWayDTO> findInfoTemplateSendWayOfPage(InfoTemplateSendWayDTO dto, Pagination page) {
		return infoTemplateSendWayFacade.findInfoTemplateSendWayOfPage(dto, page);
	}

	@Override
	public List<InfoTemplateSendWayDTO> findInfoTemplateSendWayAll(InfoTemplateSendWayDTO dto) {
		return infoTemplateSendWayFacade.findInfoTemplateSendWayAll(dto);
	}

	@Override
	public Long insertInfoTemplateSendWayWithTx(InfoTemplateSendWayDTO dto) {
		return infoTemplateSendWayFacade.insertInfoTemplateSendWayWithTx(dto);
	}

	@Override
	public int updateInfoTemplateSendWayWithTx(InfoTemplateSendWayDTO dto) {
		return infoTemplateSendWayFacade.updateInfoTemplateSendWayWithTx(dto);
	}

	@Override
	public int deleteInfoTemplateSendWayWithTx(InfoTemplateSendWayDTO dto) {
		return infoTemplateSendWayFacade.deleteInfoTemplateSendWayWithTx(dto);
	}


}
	