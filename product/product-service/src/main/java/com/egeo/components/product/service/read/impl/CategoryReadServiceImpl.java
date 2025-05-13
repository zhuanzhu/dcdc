package com.egeo.components.product.service.read.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.CategoryReadService;
import com.egeo.components.product.manage.read.CategoryReadManage;
import com.egeo.components.product.condition.CategoryCondition;
import com.egeo.components.product.converter.CategoryConverter;
import com.egeo.components.product.dto.CategoryDTO;
import com.egeo.components.product.po.CategoryPO;


@Service("categoryReadService")
public class CategoryReadServiceImpl  implements CategoryReadService {
	@Autowired
	private CategoryReadManage categoryReadManage;
	
	/*@Autowired
	private CategoryTreeNodeReadService categoryTreeNodeReadService;*/

        @Override
        public List<CategoryDTO> findAll(CategoryDTO dto) {
            List<CategoryCondition> list = categoryReadManage.findAll(CategoryConverter.toPO(dto));
            List<CategoryDTO> lists = new ArrayList<CategoryDTO>();
            for (CategoryCondition src : list) {
                CategoryDTO tar = new CategoryDTO();
                tar.setId(src.getId());
                tar.setName(src.getName());
                tar.setParentId(src.getParentId());
                tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());
                //根据类目节点id查询类目id
                /*if(!src.getParentId().equals(0L)){
                	CategoryTreeNodeDTO categoryTreeNodeDTO = new CategoryTreeNodeDTO();
                    categoryTreeNodeDTO.setId(src.getParentId());
                    CategoryTreeNodeDTO categoryTreeNodeDTO2 = categoryTreeNodeReadService.findById(categoryTreeNodeDTO);
                    tar.setParentId(categoryTreeNodeDTO2.getCategoryId());
                }else{
                	tar.setParentId(src.getParentId());
                }*/
                
                lists.add(tar);
            }
            return lists;
        }

        @Override
        public List<String> idToName(String ids) {
            List<String> lists = new ArrayList<String>();
            String substring = ids.substring(1, ids.length()-1);
            List<String> list = Arrays.asList(substring.split(","));
            for (String string : list) {
                CategoryPO categoryPO = new CategoryPO();
                categoryPO.setId(Long.valueOf(string));
                CategoryPO po = categoryReadManage.findById(categoryPO);
                lists.add(po.getName());
            }
            return lists;
        }

        @Override
        public List<CategoryDTO> findAllList(CategoryDTO dto) {
            List<CategoryPO> list = categoryReadManage.findAllList(CategoryConverter.toPO(dto));
            return CategoryConverter.toDTO(list);
        }

		@Override
		public CategoryDTO findCategoryById(Long id) {
			return CategoryConverter.toDTO(categoryReadManage.findCategoryById(id));
		}
		/**
		 * 根据上级节点信息查询类目信息
		 * @param parentId
		 * @return
		 */
		@Override
		public CategoryDTO categoryByPIdNode(Long parentId) {
			CategoryPO categoryPO = categoryReadManage.categoryByPIdNode(parentId);
			return CategoryConverter.toDTO(categoryPO);
		}


}
	