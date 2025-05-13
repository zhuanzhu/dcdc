package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.RoleFunctionTreeNodeConverter;
import com.egeo.components.user.dto.RoleFunctionTreeNodeDTO;
import com.egeo.components.user.manage.write.RoleFunctionTreeNodeWriteManage;
import com.egeo.components.user.po.RoleFunctionTreeNodePO;
import com.egeo.components.user.service.write.RoleFunctionTreeNodeWriteService;

@Service("roleFunctionTreeNodeWriteService")
public class RoleFunctionTreeNodeWriteServiceImpl implements RoleFunctionTreeNodeWriteService {
	@Autowired
	private RoleFunctionTreeNodeWriteManage roleFunctionTreeNodeWriteManage;

	@Override
	public Long insertRoleFunctionTreeNodeWithTx(RoleFunctionTreeNodeDTO dto) {
		RoleFunctionTreeNodePO po = RoleFunctionTreeNodeConverter.toPO(dto);
		Long rt = roleFunctionTreeNodeWriteManage.insertRoleFunctionTreeNodeWithTx(po);		
		return rt;
	}

	@Override
	public int updateRoleFunctionTreeNodeWithTx(RoleFunctionTreeNodeDTO dto) {
		RoleFunctionTreeNodePO po = RoleFunctionTreeNodeConverter.toPO(dto);
		int rt = roleFunctionTreeNodeWriteManage.updateRoleFunctionTreeNodeWithTx(po);		
		return rt;
	}

	@Override
	public int deleteRoleFunctionTreeNodeWithTx(RoleFunctionTreeNodeDTO dto) {
		RoleFunctionTreeNodePO po = RoleFunctionTreeNodeConverter.toPO(dto);
		int rt = roleFunctionTreeNodeWriteManage.deleteRoleFunctionTreeNodeWithTx(po);		
		return rt;
	}
}
	