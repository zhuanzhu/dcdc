package com.egeo.components.cms.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.FunctionModuleCategoryWriteManage;
import com.egeo.components.cms.dao.write.FunctionModuleCategoryWriteDAO;
import com.egeo.components.cms.po.FunctionModuleCategoryPO;
import com.egeo.exception.BusinessException;

@Service
public class FunctionModuleCategoryWriteManageImpl implements FunctionModuleCategoryWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FunctionModuleCategoryWriteDAO functionModuleCategoryWriteDAO;

	@Override
	public Long insertFunctionModuleCategoryWithTx(FunctionModuleCategoryPO po) {
		
		int i ;
		try {
				i = functionModuleCategoryWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateFunctionModuleCategoryWithTx(FunctionModuleCategoryPO po) {
		int i;
		i = functionModuleCategoryWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteFunctionModuleCategoryWithTx(FunctionModuleCategoryPO po) {
		int i;
		i = functionModuleCategoryWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据功能模块及已存在的类目节点id集合批量删除功能模块类目节点关系
	 * @param functionModuleId
	 * @param categoryTreeNodeIdList
	 * @return
	 */
	@Override
	public int delByFunctionModuleIdCategoryTreeNodeIds(Long functionModuleId, List<Long> categoryTreeNodeIdList) {
		// TODO Auto-generated method stub
		return functionModuleCategoryWriteDAO.delByFunctionModuleIdCategoryTreeNodeIds(functionModuleId, categoryTreeNodeIdList);
	}
	/**
	 * 根据功能模块id删除功能模块类目节点关系
	 * @param functionModuleId
	 * @return
	 */
	@Override
	public int delByFunctionModuleId(Long functionModuleId) {
		FunctionModuleCategoryPO functionModuleCategoryPO = new FunctionModuleCategoryPO();
		functionModuleCategoryPO.setFunctionModuleId(functionModuleId);
		return functionModuleCategoryWriteDAO.deleteByPara(functionModuleCategoryPO);
	}	
}
	