package com.egeo.components.user.service.read.impl;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.egeo.components.user.service.read.InfoTemplateParameterReadService;
import com.egeo.components.user.dto.InfoTemplateParameterDTO;
import com.egeo.components.user.po.InfoTemplateParameterPO;
import com.egeo.components.user.dao.read.InfoTemplateParameterReadDAO;
import com.egeo.components.user.converter.InfoTemplateParameterConverter;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("infoTemplateParameterReadService")
public class InfoTemplateParameterReadServiceImpl implements InfoTemplateParameterReadService {
	@Autowired
	private InfoTemplateParameterReadDAO infoTemplateParameterReadDAO ;

	@Override
	public InfoTemplateParameterDTO findInfoTemplateParameterById(InfoTemplateParameterDTO dto){
		InfoTemplateParameterPO po = InfoTemplateParameterConverter.toPO(dto);
		InfoTemplateParameterPO infoTemplateParameterpo = new InfoTemplateParameterPO();
		infoTemplateParameterpo.setId(po.getId());
		InfoTemplateParameterPO list = infoTemplateParameterReadDAO.findById(infoTemplateParameterpo);
		return InfoTemplateParameterConverter.toDTO(list);
	}
	@Override
	public PageResult<InfoTemplateParameterDTO> findInfoTemplateParameterOfPage(InfoTemplateParameterDTO dto, Pagination page) {
		InfoTemplateParameterPO po = InfoTemplateParameterConverter.toPO(dto);
		PageResult<InfoTemplateParameterPO> pageResult = new PageResult<InfoTemplateParameterPO>();
		List<InfoTemplateParameterPO> listT = null;
		int cnt = infoTemplateParameterReadDAO.countOfPage(po);
		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = infoTemplateParameterReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<InfoTemplateParameterPO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		
        List<InfoTemplateParameterDTO> list = InfoTemplateParameterConverter.toDTO(pageResult.getList());
        PageResult<InfoTemplateParameterDTO> result = new PageResult<InfoTemplateParameterDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<InfoTemplateParameterDTO> findInfoTemplateParameterAll(InfoTemplateParameterDTO dto) {
		InfoTemplateParameterPO po = InfoTemplateParameterConverter.toPO(dto);
		List<InfoTemplateParameterPO> list = infoTemplateParameterReadDAO.findAll(po,null);
		return InfoTemplateParameterConverter.toDTO(list);
	}
}
	