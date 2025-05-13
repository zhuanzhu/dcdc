package com.egeo.components.product.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import com.egeo.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.CategoryTreeNodeReadService;
import com.egeo.components.product.manage.read.CategoryTreeNodeReadManage;
import com.egeo.components.product.condition.CategoryTreeNodeCondition;
import com.egeo.components.product.converter.CategoryTreeNodeConverter;
import com.egeo.components.product.dto.CategoryTreeNodeDTO;
import com.egeo.components.product.po.CategoryTreeNodePO;


@Service("categoryTreeNodeReadService")
public class CategoryTreeNodeReadServiceImpl  implements CategoryTreeNodeReadService {
	@Autowired
	private CategoryTreeNodeReadManage categoryTreeNodeReadManage;

	@Override
	public List<CategoryTreeNodeDTO> getCategoryTreeByCatId(Long categoryId, Long platformId) {
		List<CategoryTreeNodeCondition> po = categoryTreeNodeReadManage.getCategoryTreeByCatId(categoryId,platformId);
		/*List<CategoryTreeNodeDTO> lists = CategoryTreeNodeConverter.categoryConditiontoDTO(po);
        //递归成树结构
        List<CategoryTreeNodeDTO> sortList = new ArrayList<CategoryTreeNodeDTO>();
        for (CategoryTreeNodeDTO tree : lists) {
            for (CategoryTreeNodeDTO t : lists) {
                if (t.getParentId().equals(tree.getId())) {
                    if (tree.getLists() == null) {
                        List<CategoryTreeNodeDTO> mylistss = new ArrayList<CategoryTreeNodeDTO>();
                        mylistss.add(t);
                        tree.setLists(mylistss);
                    } else {
                        tree.getLists().add(t);
                    }
                }
            }
            if (tree.getParentId().equals(PlatformKeyConstant.PRODUCT_PLATFORMID)) {
                sortList.add(tree);
            }
        }*/
		return CategoryTreeNodeConverter.categoryConditiontoDTO(po);
	}

        @Override
        public CategoryTreeNodeDTO CategoryTreeNodeByCategoryId(Long parentId) {
            CategoryTreeNodePO po = categoryTreeNodeReadManage.CategoryTreeNodeByCategoryId(parentId);
            return CategoryTreeNodeConverter.toDTO(po);
        }

        @Override
        public CategoryTreeNodeDTO findById(CategoryTreeNodeDTO dto) {
            CategoryTreeNodePO categoryTreeNodePO = categoryTreeNodeReadManage.findById(CategoryTreeNodeConverter.toPO(dto));
            return CategoryTreeNodeConverter.toDTO(categoryTreeNodePO);
        }

        @Override
        public List<CategoryTreeNodeDTO> findAll(CategoryTreeNodeDTO dto) {
            List<CategoryTreeNodePO> list = categoryTreeNodeReadManage.findAll(CategoryTreeNodeConverter.toPO(dto));
            return CategoryTreeNodeConverter.toDTO(list);
        }

		@Override
		public CategoryTreeNodeDTO findCategoryTreeNodeInofByNodeId(Long ctnId) {
            CategoryTreeNodePO po = categoryTreeNodeReadManage.findCategoryTreeNodeInofByNodeId(ctnId);
            return CategoryTreeNodeConverter.toDTO(po);
		}

		@Override
		public Integer listSortMax() {
			return categoryTreeNodeReadManage.listSortMax();
		}
		/**
		 * 根据条件查询类目节点信息
		 * @param categoryTreeNodeDTO
		 * @return
		 */
		@Override
		public List<CategoryTreeNodeDTO> findCategoryTreeNodeAll(CategoryTreeNodeDTO categoryTreeNodeDTO) {
			List<CategoryTreeNodeDTO> CategoryTreeNodeDTOList = new ArrayList<>();
			List<CategoryTreeNodeCondition> list = categoryTreeNodeReadManage.findCategoryTreeNodeAll(CategoryTreeNodeConverter.toPO(categoryTreeNodeDTO));
			for (CategoryTreeNodeCondition categoryTreeNodeCondition : list) {
				CategoryTreeNodeDTO categoryTreeNodeDTO2 = CategoryTreeNodeConverter.toDTO(categoryTreeNodeCondition);
				categoryTreeNodeDTO2.setName(categoryTreeNodeCondition.getName());
				categoryTreeNodeDTO2.setBannerImg(categoryTreeNodeCondition.getBannerImg());;
				categoryTreeNodeDTO2.setLinkType(categoryTreeNodeCondition.getLinkType());
				categoryTreeNodeDTO2.setLinkUrl(categoryTreeNodeCondition.getLinkUrl());
				categoryTreeNodeDTO2.setLinkParam(categoryTreeNodeCondition.getLinkParam());
				categoryTreeNodeDTO2.setExternalLinkId(categoryTreeNodeCondition.getExternalLinkId());
				categoryTreeNodeDTO2.setStandardUnitCombinationId(categoryTreeNodeCondition.getStandardUnitCombinationId());
				categoryTreeNodeDTO2.setStandardUnitId(categoryTreeNodeCondition.getStandardUnitId());
				categoryTreeNodeDTO2.setLinkableId(categoryTreeNodeCondition.getLinkableId());
				categoryTreeNodeDTO2.setDescription(categoryTreeNodeCondition.getDescription());
				categoryTreeNodeDTO2.setLocalParamId(categoryTreeNodeCondition.getLocalParamId());
				CategoryTreeNodeDTOList.add(categoryTreeNodeDTO2);
			}
			return CategoryTreeNodeDTOList;
		}

	/**
	 * 根据前台类目节点id查询前台类目节点信息
	 * @param categoryTreeNodeId
	 * @return
	 */
		@Override
		public CategoryTreeNodeDTO findByCategoryTreeNodeId(Long categoryTreeNodeId) {
			CategoryTreeNodeCondition categoryTreeNodeCondition = categoryTreeNodeReadManage.findByCategoryTreeNodeId(categoryTreeNodeId);
			if (EmptyUtil.isNotEmpty(categoryTreeNodeCondition)) {
				CategoryTreeNodeDTO categoryTreeNodeDTO2 = CategoryTreeNodeConverter.conditionToDTO(categoryTreeNodeCondition);
				categoryTreeNodeDTO2.setName(categoryTreeNodeCondition.getName());
				categoryTreeNodeDTO2.setBannerImg(categoryTreeNodeCondition.getBannerImg());
				categoryTreeNodeDTO2.setLinkType(categoryTreeNodeCondition.getLinkType());
				categoryTreeNodeDTO2.setLinkUrl(categoryTreeNodeCondition.getLinkUrl());
				categoryTreeNodeDTO2.setLinkParam(categoryTreeNodeCondition.getLinkParam());
				categoryTreeNodeDTO2.setExternalLinkId(categoryTreeNodeCondition.getExternalLinkId());
				categoryTreeNodeDTO2.setStandardUnitCombinationId(categoryTreeNodeCondition.getStandardUnitCombinationId());
				categoryTreeNodeDTO2.setStandardUnitId(categoryTreeNodeCondition.getStandardUnitId());
				categoryTreeNodeDTO2.setLocalParamId(categoryTreeNodeCondition.getLocalParamId());
				categoryTreeNodeDTO2.setDescription(categoryTreeNodeCondition.getDescription());
				categoryTreeNodeDTO2.setLinkableId(categoryTreeNodeCondition.getLinkableId());
				return categoryTreeNodeDTO2;
			}
			return null;
		}

		@Override
		public List<CategoryTreeNodeDTO> findWebCategoryTreeByCategoryTreeId(CategoryTreeNodeDTO categoryTreeNodeDTO) {
			List<CategoryTreeNodeDTO> CategoryTreeNodeDTOList = new ArrayList<>();
			List<CategoryTreeNodeCondition> list = categoryTreeNodeReadManage.findWebCategoryTreeByCategoryTreeId(CategoryTreeNodeConverter.toPO(categoryTreeNodeDTO));
			for (CategoryTreeNodeCondition categoryTreeNodeCondition : list) {
				CategoryTreeNodeDTO categoryTreeNodeDTO2 = CategoryTreeNodeConverter.toDTO(categoryTreeNodeCondition);
				categoryTreeNodeDTO2.setName(categoryTreeNodeCondition.getName());
				CategoryTreeNodeDTOList.add(categoryTreeNodeDTO2);
			}
			return CategoryTreeNodeDTOList;
		}

		@Override
		public String findMaxSubSerialNumber(Long categoryTreeId, Long parentId) {
			return categoryTreeNodeReadManage.findMaxSubSerialNumber(categoryTreeId, parentId);
		}

	@Override
	public Long findParentidById(Long id) {
		return categoryTreeNodeReadManage.findParentidById(id);
	}

}
	