package com.egeo.components.user.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.condition.FunctionTreeNodeUrlCondition;
import com.egeo.components.user.converter.FunctionTreeNodeUrlConverter;
import com.egeo.components.user.dao.read.FunctionTreeNodeUrlReadDAO;
import com.egeo.components.user.dto.FunctionTreeNodeUrlDTO;
import com.egeo.components.user.po.FunctionTreeNodeUrlPO;
import com.egeo.components.user.service.ActiveMQListenerService;
import com.egeo.components.user.service.read.FunctionTreeNodeUrlReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.log.XLogger;

@Service("functionTreeNodeUrlReadService")
public class FunctionTreeNodeUrlReadServiceImpl implements FunctionTreeNodeUrlReadService {
	private static final XLogger LOG = XLogger.getLogger(FunctionTreeNodeUrlReadServiceImpl.class);
	@Autowired
	private FunctionTreeNodeUrlReadDAO functionTreeNodeUrlReadDAO;

	@Override
	public FunctionTreeNodeUrlDTO findFunctionTreeNodeUrlById(FunctionTreeNodeUrlDTO dto) {
		FunctionTreeNodeUrlPO po = FunctionTreeNodeUrlConverter.toPO(dto);
		FunctionTreeNodeUrlPO functionTreeNodeUrlpo = new FunctionTreeNodeUrlPO();
		functionTreeNodeUrlpo.setId(po.getId());
		FunctionTreeNodeUrlPO list = functionTreeNodeUrlReadDAO.findById(functionTreeNodeUrlpo);

		return FunctionTreeNodeUrlConverter.toDTO(list);
	}

	@Override
	public PageResult<FunctionTreeNodeUrlDTO> findFunctionTreeNodeUrlOfPage(FunctionTreeNodeUrlDTO dto, Pagination page) {
		FunctionTreeNodeUrlCondition condition = FunctionTreeNodeUrlConverter.toCondition(dto);
		LOG.info("platformId:"+condition.getPlatformId() );
		PageResult<FunctionTreeNodeUrlCondition> pageResult = new PageResult<FunctionTreeNodeUrlCondition>();
		List<FunctionTreeNodeUrlCondition> listT = null;

		int cnt = functionTreeNodeUrlReadDAO.countFunctionTreeNodeUrlConditionOfPage(condition);
		LOG.info("cnt = "+cnt);
		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = functionTreeNodeUrlReadDAO.findFunctionTreeNodeUrlConditionOfPage(condition, page);
		} else {
			listT = new ArrayList<FunctionTreeNodeUrlCondition>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
        List<FunctionTreeNodeUrlDTO> list = FunctionTreeNodeUrlConverter.conditionToDTO(pageResult.getList());
        PageResult<FunctionTreeNodeUrlDTO> result = new PageResult<FunctionTreeNodeUrlDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<FunctionTreeNodeUrlDTO> findFunctionTreeNodeUrlAll(FunctionTreeNodeUrlDTO dto) {
		FunctionTreeNodeUrlPO po = FunctionTreeNodeUrlConverter.toPO(dto);
		List<FunctionTreeNodeUrlPO> list = functionTreeNodeUrlReadDAO.findAll(po,null);	
		return FunctionTreeNodeUrlConverter.toDTO(list);
	}


	@Override
	public List<FunctionTreeNodeUrlDTO> findFunctionTreeNodeUrlByFunctionTreeNodeId(Long functionTreeNodeId) {
		//List<FunctionTreeNodeUrlCondition> poList = functionTreeNodeUrlReadManage.findFunctionTreeNodeUrlByFunctionTreeNodeId(functionTreeNodeId);
		List<FunctionTreeNodeUrlCondition> poList = functionTreeNodeUrlReadDAO.findFunctionTreeNodeUrlByFunctionTreeNodeId(functionTreeNodeId);
        /*List<FunctionTreeNodeUrlPO> poList = new ArrayList<FunctionTreeNodeUrlPO>();
        for (FunctionTreeNodeUrlCondition condition : conditions) {
            if (EmptyUtil.isNotEmpty(condition)) {
                FunctionTreeNodeUrlPO po = new FunctionTreeNodeUrlPO();
                po.setUrlId(condition.getUrlId());
                po.setUrl(condition.getUrl());
                po.setUrlName(condition.getUrlName());
                poList.add(po);
            }
        }*/
		return FunctionTreeNodeUrlConverter.conditionToDTO(poList);
	}
}
	