package com.egeo.components.user.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.InfoSendWayManage;
import com.egeo.components.user.dto.InfoSendWayDTO;
import com.egeo.components.user.facade.InfoSendWayFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("infoSendWay")
public class InfoSendWayManageImpl implements InfoSendWayManage{

	
	@Resource(name="infoSendWayFacade")
	private InfoSendWayFacade infoSendWayFacade;

	@Override
	public InfoSendWayDTO findInfoSendWayById(InfoSendWayDTO dto) {
		return infoSendWayFacade.findInfoSendWayById(dto);
	}

	@Override
	public PageResult<InfoSendWayDTO> findInfoSendWayOfPage(InfoSendWayDTO dto, Pagination page) {
		return infoSendWayFacade.findInfoSendWayOfPage(dto, page);
	}

	@Override
	public List<InfoSendWayDTO> findInfoSendWayAll(InfoSendWayDTO dto) {
		return infoSendWayFacade.findInfoSendWayAll(dto);
	}

	@Override
	public Long insertInfoSendWayWithTx(InfoSendWayDTO dto) {
		return infoSendWayFacade.insertInfoSendWayWithTx(dto);
	}

	@Override
	public int updateInfoSendWayWithTx(InfoSendWayDTO dto) {
		return infoSendWayFacade.updateInfoSendWayWithTx(dto);
	}

	@Override
	public int deleteInfoSendWayWithTx(InfoSendWayDTO dto) {
		return infoSendWayFacade.deleteInfoSendWayWithTx(dto);
	}


}
	