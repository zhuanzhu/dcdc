package com.egeo.components.cms.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.business.SuListManage;
import com.egeo.components.cms.facade.SuListFacade;
import com.egeo.components.cms.dto.SuListDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("suList")
public class SuListManageImpl implements SuListManage{

	
	@Resource(name="suListFacade")
	private SuListFacade suListFacade;


}
	