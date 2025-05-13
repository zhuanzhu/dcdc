package com.egeo.components.user.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.egeo.components.user.business.FunctionTreeNodeUrlManage;
import com.egeo.components.user.dto.FunctionTreeNodeUrlDTO;
import com.egeo.components.user.dto.UrlDTO;
import com.egeo.components.user.facade.FunctionTreeNodeUrlFacade;
import com.egeo.components.user.facade.UrlFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("functionTreeNodeUrl")
public class FunctionTreeNodeUrlManageImpl implements FunctionTreeNodeUrlManage{

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="functionTreeNodeUrlFacade")
	private FunctionTreeNodeUrlFacade functionTreeNodeUrlFacade;

	@Resource(name="urlFacade")
	private UrlFacade urlFacade;

	@Override
	public FunctionTreeNodeUrlDTO findFunctionTreeNodeUrlById(FunctionTreeNodeUrlDTO dto) {
		FunctionTreeNodeUrlDTO functionTreeNodeUrlDTO = functionTreeNodeUrlFacade.findFunctionTreeNodeUrlById(dto);
		UrlDTO urlDTO = urlFacade.findUrlById(functionTreeNodeUrlDTO.getUrlId());
		logger.info("urlDTO.url"+urlDTO.getUrl()+",urlDTO.getName:"+urlDTO.getName());
		functionTreeNodeUrlDTO.setUrl(urlDTO.getUrl());
		functionTreeNodeUrlDTO.setUrlName(urlDTO.getName());
		return functionTreeNodeUrlDTO;
	}

	@Override
	public PageResult<FunctionTreeNodeUrlDTO> findFunctionTreeNodeUrlOfPage(FunctionTreeNodeUrlDTO dto, Pagination page) {
		return functionTreeNodeUrlFacade.findFunctionTreeNodeUrlOfPage(dto, page);
	}

	@Override
	public List<FunctionTreeNodeUrlDTO> findFunctionTreeNodeUrlAll(FunctionTreeNodeUrlDTO dto) {
		return functionTreeNodeUrlFacade.findFunctionTreeNodeUrlAll(dto);
	}

	@Override
	public Long insertFunctionTreeNodeUrlWithTx(FunctionTreeNodeUrlDTO dto) {
		return functionTreeNodeUrlFacade.insertFunctionTreeNodeUrlWithTx(dto);
	}

	@Override
	public int updateFunctionTreeNodeUrlWithTx(FunctionTreeNodeUrlDTO dto) {
		return functionTreeNodeUrlFacade.updateFunctionTreeNodeUrlWithTx(dto);
	}

	@Override
	public int deleteFunctionTreeNodeUrlWithTx(FunctionTreeNodeUrlDTO dto) {
		return functionTreeNodeUrlFacade.deleteFunctionTreeNodeUrlWithTx(dto);
	}


}
	