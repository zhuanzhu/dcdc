package com.egeo.components.user.service.read.impl;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.egeo.components.user.service.read.InfoTemplateReadService;
import com.egeo.components.user.dto.InfoTemplateDTO;
import com.egeo.components.user.po.InfoTemplatePO;
import com.egeo.components.user.dao.read.InfoTemplateReadDAO;
import com.egeo.components.user.converter.InfoTemplateConverter;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("infoTemplateReadService")
public class InfoTemplateReadServiceImpl implements InfoTemplateReadService {
	@Autowired
	private InfoTemplateReadDAO infoTemplateReadDAO ;

	@Override
	public InfoTemplateDTO findInfoTemplateById(InfoTemplateDTO dto){
		InfoTemplatePO po = InfoTemplateConverter.toPO(dto);
		InfoTemplatePO infoTemplatepo = new InfoTemplatePO();
		infoTemplatepo.setId(po.getId());
		InfoTemplatePO list = infoTemplateReadDAO.findById(infoTemplatepo);
		return InfoTemplateConverter.toDTO(list);
	}
	@Override
	public PageResult<InfoTemplateDTO> findInfoTemplateOfPage(InfoTemplateDTO dto, Pagination page) {
		InfoTemplatePO po = InfoTemplateConverter.toPO(dto);
		PageResult<InfoTemplatePO> pageResult = new PageResult<InfoTemplatePO>();
		List<InfoTemplatePO> listT = null;
		int cnt = infoTemplateReadDAO.countOfPage(po);
		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = infoTemplateReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<InfoTemplatePO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		
        List<InfoTemplateDTO> list = InfoTemplateConverter.toDTO(pageResult.getList());
        PageResult<InfoTemplateDTO> result = new PageResult<InfoTemplateDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<InfoTemplateDTO> findInfoTemplateAll(InfoTemplateDTO dto) {
		InfoTemplatePO po = InfoTemplateConverter.toPO(dto);
		List<InfoTemplatePO> list = infoTemplateReadDAO.findAll(po,null);
		return InfoTemplateConverter.toDTO(list);
	}
}
	