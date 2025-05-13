package com.egeo.components.cms.service.write.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.write.FunctionModuleCategoryWriteService;
import com.egeo.components.cms.manage.read.FunctionModuleCategoryReadManage;
import com.egeo.components.cms.manage.write.FunctionModuleCategoryWriteManage;
import com.egeo.components.cms.converter.FunctionModuleCategoryConverter;
import com.egeo.components.cms.dto.FunctionModuleCategoryDTO;
import com.egeo.components.cms.po.FunctionModuleCategoryPO;

import com.egeo.utils.EmptyUtil;

@Service("functionModuleCategoryWriteService")
public class FunctionModuleCategoryWriteServiceImpl  implements FunctionModuleCategoryWriteService {
	@Autowired
	private FunctionModuleCategoryWriteManage functionModuleCategoryWriteManage;
	
	@Autowired
	private FunctionModuleCategoryReadManage functionModuleCategoryReadManage;

	@Override
	public Long insertFunctionModuleCategoryWithTx(FunctionModuleCategoryDTO dto) {
		FunctionModuleCategoryPO po = FunctionModuleCategoryConverter.toPO(dto);
		Long rt = functionModuleCategoryWriteManage.insertFunctionModuleCategoryWithTx(po);		
		return rt;
	}

	@Override
	public int updateFunctionModuleCategoryWithTx(FunctionModuleCategoryDTO dto) {
		FunctionModuleCategoryPO po = FunctionModuleCategoryConverter.toPO(dto);
		int rt = functionModuleCategoryWriteManage.updateFunctionModuleCategoryWithTx(po);		
		return rt;
	}

	@Override
	public int deleteFunctionModuleCategoryWithTx(FunctionModuleCategoryDTO dto) {
		FunctionModuleCategoryPO po = FunctionModuleCategoryConverter.toPO(dto);
		int rt = functionModuleCategoryWriteManage.deleteFunctionModuleCategoryWithTx(po);		
		return rt;
	}
	/**
	 * 批量更新类目节点信息
	 * @param functionModuleId
	 * @param categoryTreeNodeIdList
	 * @param req
	 * @return
	 */
	@Override
	public int updateFunctionModuleCategoryAllWithTx(Long functionModuleId, List<Long> categoryTreeNodeIds) {
		int i = 0;
		//根据功能模版id查询功能模块类目节点关系
		FunctionModuleCategoryPO functionModuleCategoryPO = new FunctionModuleCategoryPO();
		functionModuleCategoryPO.setFunctionModuleId(functionModuleId);
		List<FunctionModuleCategoryPO> functionModuleCategoryList = functionModuleCategoryReadManage.findFunctionModuleCategoryAll(functionModuleCategoryPO);
		//拼接已存在的类目节点id集合
		List<Long> categoryTreeNodeIdList = new ArrayList<>();
		for (Long long1 : categoryTreeNodeIds) {
			for (FunctionModuleCategoryPO functionModuleCategoryPO2 : functionModuleCategoryList) {
				if(long1.equals(functionModuleCategoryPO2.getCategoryTreeNodeId())){
					categoryTreeNodeIdList.add(long1);
					break;
				}
			}
		}
		//已存在的模版类目节点关系是否为空
		if(EmptyUtil.isNotEmpty(categoryTreeNodeIdList)){
			//根据功能模块及已存在的类目节点id集合批量删除功能模块类目节点关系
			functionModuleCategoryWriteManage.delByFunctionModuleIdCategoryTreeNodeIds(functionModuleId,categoryTreeNodeIdList);
		}
		//为空根据功能模块id删除功能模块类目节点关系
		else{
			functionModuleCategoryWriteManage.delByFunctionModuleId(functionModuleId);
		}
		
		
		for (Long long1 : categoryTreeNodeIds) {
			boolean isSave = true;
			for (FunctionModuleCategoryPO functionModuleCategoryPO2 : functionModuleCategoryList) {
				if(long1.equals(functionModuleCategoryPO2.getCategoryTreeNodeId())){
					isSave = false;
					break;
				}
			}
			if(isSave){
				FunctionModuleCategoryPO po = new FunctionModuleCategoryPO();
				po.setFunctionModuleId(functionModuleId);
				po.setCategoryTreeNodeId(long1);
				functionModuleCategoryWriteManage.insertFunctionModuleCategoryWithTx(po);	
				i = i +1;
			}
		}
		return i;
	}
}
	