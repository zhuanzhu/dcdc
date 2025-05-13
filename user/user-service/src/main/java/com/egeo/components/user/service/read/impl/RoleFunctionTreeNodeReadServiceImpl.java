package com.egeo.components.user.service.read.impl;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.egeo.components.user.service.read.RoleFunctionTreeNodeReadService;
import com.egeo.components.user.dto.RoleFunctionTreeNodeDTO;
import com.egeo.components.user.po.RoleFunctionTreeNodePO;
import com.egeo.components.user.dao.read.RoleFunctionTreeNodeReadDAO;
import com.egeo.components.user.converter.RoleFunctionTreeNodeConverter;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("roleFunctionTreeNodeReadService")
public class RoleFunctionTreeNodeReadServiceImpl implements RoleFunctionTreeNodeReadService {
	@Autowired
	private RoleFunctionTreeNodeReadDAO roleFunctionTreeNodeReadDAO ;

	@Override
	public RoleFunctionTreeNodeDTO findRoleFunctionTreeNodeById(RoleFunctionTreeNodeDTO dto){
		RoleFunctionTreeNodePO po = RoleFunctionTreeNodeConverter.toPO(dto);
		RoleFunctionTreeNodePO roleFunctionTreeNodepo = new RoleFunctionTreeNodePO();
		roleFunctionTreeNodepo.setId(po.getId());
		RoleFunctionTreeNodePO list = roleFunctionTreeNodeReadDAO.findById(roleFunctionTreeNodepo);
		return RoleFunctionTreeNodeConverter.toDTO(list);
	}
	@Override
	public PageResult<RoleFunctionTreeNodeDTO> findRoleFunctionTreeNodeOfPage(RoleFunctionTreeNodeDTO dto, Pagination page) {
		RoleFunctionTreeNodePO po = RoleFunctionTreeNodeConverter.toPO(dto);
		PageResult<RoleFunctionTreeNodePO> pageResult = new PageResult<RoleFunctionTreeNodePO>();
		List<RoleFunctionTreeNodePO> listT = null;
		int cnt = roleFunctionTreeNodeReadDAO.countOfPage(po);
		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = roleFunctionTreeNodeReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<RoleFunctionTreeNodePO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		
        List<RoleFunctionTreeNodeDTO> list = RoleFunctionTreeNodeConverter.toDTO(pageResult.getList());
        PageResult<RoleFunctionTreeNodeDTO> result = new PageResult<RoleFunctionTreeNodeDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<RoleFunctionTreeNodeDTO> findRoleFunctionTreeNodeAll(RoleFunctionTreeNodeDTO dto) {
		RoleFunctionTreeNodePO po = RoleFunctionTreeNodeConverter.toPO(dto);
		List<RoleFunctionTreeNodePO> list = roleFunctionTreeNodeReadDAO.findAll(po,null);
		return RoleFunctionTreeNodeConverter.toDTO(list);
	}
}
