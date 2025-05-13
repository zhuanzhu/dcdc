package com.egeo.components.user.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.FunctionTreeNodeConverter;
import com.egeo.components.user.converter.UrlConverter;
import com.egeo.components.user.dao.read.FunctionTreeNodeReadDAO;
import com.egeo.components.user.dto.FunctionTreeNodeDTO;
import com.egeo.components.user.dto.UrlDTO;
import com.egeo.components.user.po.FunctionTreeNodePO;
import com.egeo.components.user.po.UrlPO;
import com.egeo.components.user.service.read.FunctionTreeNodeReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.StringUtils;

@Service("functionTreeNodeReadService")
public class FunctionTreeNodeReadServiceImpl implements FunctionTreeNodeReadService {
	@Autowired
	private FunctionTreeNodeReadDAO functionTreeNodeReadDAO;

	@Override
	public FunctionTreeNodeDTO findFunctionTreeNodeById(FunctionTreeNodeDTO dto) {
		FunctionTreeNodePO po = FunctionTreeNodeConverter.toPO(dto);
		FunctionTreeNodePO functionTreeNodepo = new FunctionTreeNodePO();
		functionTreeNodepo.setId(po.getId());
		FunctionTreeNodePO list = functionTreeNodeReadDAO.findById(functionTreeNodepo);
		return FunctionTreeNodeConverter.toDTO(list);
	}

	@Override
	public PageResult<FunctionTreeNodeDTO> findFunctionTreeNodeOfPage(FunctionTreeNodeDTO dto, Pagination page) {
		FunctionTreeNodePO po = FunctionTreeNodeConverter.toPO(dto);
		
		PageResult<FunctionTreeNodePO> pageResult = new PageResult<FunctionTreeNodePO>();
		List<FunctionTreeNodePO> listT = null;

		int cnt = functionTreeNodeReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = functionTreeNodeReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<FunctionTreeNodePO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
        
        List<FunctionTreeNodeDTO> list = FunctionTreeNodeConverter.toDTO(pageResult.getList());
        PageResult<FunctionTreeNodeDTO> result = new PageResult<FunctionTreeNodeDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<FunctionTreeNodeDTO> findFunctionTreeNodeAll(FunctionTreeNodeDTO dto) {
		FunctionTreeNodePO po = FunctionTreeNodeConverter.toPO(dto);
		List<FunctionTreeNodePO> list = functionTreeNodeReadDAO.findAll(po,null);	
		return FunctionTreeNodeConverter.toDTO(list);
	}

	@Override
	public List<UrlDTO> findUrlList(Long id) {
		List<UrlPO> list = functionTreeNodeReadDAO.findUrlList(id);
		return UrlConverter.toDTO(list);
	}

	@Override
	public Boolean hasLeaf(Long id) {
		FunctionTreeNodePO po = new FunctionTreeNodePO();
		po.setParentId(id);
		List<FunctionTreeNodePO> poList =functionTreeNodeReadDAO.findAll(po,null);
		if (StringUtils.isEmpty(poList)) {
			return false;
		}
		return true;
	}
}
	