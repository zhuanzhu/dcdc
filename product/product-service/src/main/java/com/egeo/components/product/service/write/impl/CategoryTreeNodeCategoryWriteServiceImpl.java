package com.egeo.components.product.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.CategoryTreeNodeCategoryWriteService;
import com.egeo.components.product.manage.write.CategoryTreeNodeCategoryWriteManage;
import com.egeo.components.product.converter.CategoryTreeNodeCategoryConverter;
import com.egeo.components.product.dto.CategoryTreeNodeCategoryDTO;
import com.egeo.components.product.po.CategoryTreeNodeCategoryPO;

@Service("categoryTreeNodeCategoryWriteService")
public class CategoryTreeNodeCategoryWriteServiceImpl  implements CategoryTreeNodeCategoryWriteService {
	@Autowired
	private CategoryTreeNodeCategoryWriteManage categoryTreeNodeCategoryWriteManage;

	@Override
	public Long insertCategoryTreeNodeCategoryWithTx(CategoryTreeNodeCategoryDTO dto) {
		CategoryTreeNodeCategoryPO po = CategoryTreeNodeCategoryConverter.toPO(dto);
		Long rt = categoryTreeNodeCategoryWriteManage.insertCategoryTreeNodeCategoryWithTx(po);		
		return rt;
	}

	@Override
	public int updateCategoryTreeNodeCategoryWithTx(CategoryTreeNodeCategoryDTO dto) {
		CategoryTreeNodeCategoryPO po = CategoryTreeNodeCategoryConverter.toPO(dto);
		int rt = categoryTreeNodeCategoryWriteManage.updateCategoryTreeNodeCategoryWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCategoryTreeNodeCategoryWithTx(CategoryTreeNodeCategoryDTO dto) {
		CategoryTreeNodeCategoryPO po = CategoryTreeNodeCategoryConverter.toPO(dto);
		int rt = categoryTreeNodeCategoryWriteManage.deleteCategoryTreeNodeCategoryWithTx(po);		
		return rt;
	}
	/**
	 * 批量添加前台类目节点与后台类目节点关系
	 * @param frontCategoryTreeNodeId
	 * @param queenCategoryTreeNodeIdList
	 * @return
	 */
	@Override
	public boolean insertCategoryTreeNodeCategoryAllWithTx(Long frontCategoryTreeNodeId,
			List<Long> queenCategoryTreeNodeIdList) {
		// TODO Auto-generated method stub
		return categoryTreeNodeCategoryWriteManage.insertCategoryTreeNodeCategoryAllWithTx(frontCategoryTreeNodeId,queenCategoryTreeNodeIdList);
	}

	@Override
	public boolean insertCtnAndSucWithTx(Long frontCategoryTreeNodeId, List<Long> queenCategoryTreeNodeIds, List<Long> standardUnitCombinationIds) {
		return categoryTreeNodeCategoryWriteManage.insertCtnAndSucWithTx(frontCategoryTreeNodeId,
				queenCategoryTreeNodeIds, standardUnitCombinationIds);
	}
}
	