package com.egeo.components.user.service.read.impl;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.egeo.components.user.service.read.InfoSendWayReadService;
import com.egeo.components.user.dto.InfoSendWayDTO;
import com.egeo.components.user.po.InfoSendWayPO;
import com.egeo.components.user.dao.read.InfoSendWayReadDAO;
import com.egeo.components.user.converter.InfoSendWayConverter;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("infoSendWayReadService")
public class InfoSendWayReadServiceImpl implements InfoSendWayReadService {
	@Autowired
	private InfoSendWayReadDAO infoSendWayReadDAO ;

	@Override
	public InfoSendWayDTO findInfoSendWayById(InfoSendWayDTO dto) {
		InfoSendWayPO po = InfoSendWayConverter.toPO(dto);
		InfoSendWayPO infoSendWaypo = new InfoSendWayPO();
		infoSendWaypo.setId(po.getId());
		InfoSendWayPO list = infoSendWayReadDAO.findById(infoSendWaypo);
		return InfoSendWayConverter.toDTO(list);
	}

	@Override
	public PageResult<InfoSendWayDTO> findInfoSendWayOfPage(InfoSendWayDTO dto, Pagination page) {
		InfoSendWayPO po = InfoSendWayConverter.toPO(dto);
		PageResult<InfoSendWayPO> pageResult = new PageResult<InfoSendWayPO>();
		List<InfoSendWayPO> listT = null;
		int cnt = infoSendWayReadDAO.countOfPage(po);
		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = infoSendWayReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<InfoSendWayPO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		
        List<InfoSendWayDTO> list = InfoSendWayConverter.toDTO(pageResult.getList());
        PageResult<InfoSendWayDTO> result = new PageResult<InfoSendWayDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<InfoSendWayDTO> findInfoSendWayAll(InfoSendWayDTO dto) {
		InfoSendWayPO po = InfoSendWayConverter.toPO(dto);
		List<InfoSendWayPO> list = infoSendWayReadDAO.findAll(po,null);
		return InfoSendWayConverter.toDTO(list);
	}
}
	