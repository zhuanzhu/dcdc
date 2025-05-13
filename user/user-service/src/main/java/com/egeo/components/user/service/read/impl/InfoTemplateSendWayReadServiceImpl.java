package com.egeo.components.user.service.read.impl;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.egeo.components.user.service.read.InfoTemplateSendWayReadService;
import com.egeo.components.user.dto.InfoTemplateSendWayDTO;
import com.egeo.components.user.po.InfoTemplateSendWayPO;
import com.egeo.components.user.dao.read.InfoTemplateSendWayReadDAO;
import com.egeo.components.user.converter.InfoTemplateSendWayConverter;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("infoTemplateSendWayReadService")
public class InfoTemplateSendWayReadServiceImpl implements InfoTemplateSendWayReadService {
	@Autowired
	private InfoTemplateSendWayReadDAO infoTemplateSendWayReadDAO ;

	@Override
	public InfoTemplateSendWayDTO findInfoTemplateSendWayById(InfoTemplateSendWayDTO dto){
		InfoTemplateSendWayPO po = InfoTemplateSendWayConverter.toPO(dto);
		InfoTemplateSendWayPO infoTemplateSendWaypo = new InfoTemplateSendWayPO();
		infoTemplateSendWaypo.setId(po.getId());
		InfoTemplateSendWayPO list = infoTemplateSendWayReadDAO.findById(infoTemplateSendWaypo);
		return InfoTemplateSendWayConverter.toDTO(list);
	}
	@Override
	public PageResult<InfoTemplateSendWayDTO> findInfoTemplateSendWayOfPage(InfoTemplateSendWayDTO dto, Pagination page) {
		InfoTemplateSendWayPO po = InfoTemplateSendWayConverter.toPO(dto);
		PageResult<InfoTemplateSendWayPO> pageResult = new PageResult<InfoTemplateSendWayPO>();
		List<InfoTemplateSendWayPO> listT = null;
		int cnt = infoTemplateSendWayReadDAO.countOfPage(po);
		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = infoTemplateSendWayReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<InfoTemplateSendWayPO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		
        List<InfoTemplateSendWayDTO> list = InfoTemplateSendWayConverter.toDTO(pageResult.getList());
        PageResult<InfoTemplateSendWayDTO> result = new PageResult<InfoTemplateSendWayDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<InfoTemplateSendWayDTO> findInfoTemplateSendWayAll(InfoTemplateSendWayDTO dto) {
		InfoTemplateSendWayPO po = InfoTemplateSendWayConverter.toPO(dto);
		List<InfoTemplateSendWayPO> list = infoTemplateSendWayReadDAO.findAll(po,null);
		return InfoTemplateSendWayConverter.toDTO(list);
	}
	/**
	 * 根据消息模版id查询发送方式
	 */
	@Override
	public List<String> sendWayNameByInfoTemplateId(Long infoTemplateId) {
		return infoTemplateSendWayReadDAO.sendWayNameByInfoTemplateId(infoTemplateId);
	}
}
	