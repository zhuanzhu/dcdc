package com.egeo.components.cms.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.business.IconManage;
import com.egeo.components.cms.facade.IconFacade;
import com.egeo.components.cms.dto.IconDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("icon")
public class IconManageImpl implements IconManage{

	
	@Resource(name="iconFacade")
	private IconFacade iconFacade;

	@Override
	public IconDTO findIconById(IconDTO dto) {
		return iconFacade.findIconById(dto);
	}

	@Override
	public PageResult<IconDTO> findIconOfPage(IconDTO dto, Pagination page) {
		return iconFacade.findIconOfPage(dto, page);
	}

	@Override
	public List<IconDTO> findIconAll(IconDTO dto) {
		return iconFacade.findIconAll(dto);
	}

	@Override
	public Long insertIconWithTx(IconDTO dto) {
		return iconFacade.insertIconWithTx(dto);
	}

	@Override
	public int updateIconWithTx(IconDTO dto) {
		return iconFacade.updateIconWithTx(dto);
	}

	@Override
	public int deleteIconWithTx(IconDTO dto) {
		return iconFacade.deleteIconWithTx(dto);
	}


}
	