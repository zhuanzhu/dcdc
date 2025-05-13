package com.egeo.components.product.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.product.controller.api.CategoryAction;
import com.egeo.components.product.dto.CategoryDTO;
import com.egeo.components.product.dto.CategoryTagDTO;
import com.egeo.components.product.dto.CategoryTreeNodeDTO;
import com.egeo.components.product.dto.ProductDTO;
import com.egeo.components.product.service.read.CategoryReadService;
import com.egeo.components.product.service.read.CategoryTagReadService;
import com.egeo.components.product.service.read.CategoryTreeNodeReadService;
import com.egeo.components.product.service.read.ProductReadService;
import com.egeo.components.product.service.write.CategoryWriteService;
import com.egeo.utils.log.XLogger;


@Component
public class CategoryFacade {

	public XLogger logger = XLogger.getLogger(CategoryFacade.class);
	@Resource
	private CategoryReadService categoryReadService;
	
	@Resource
	private CategoryWriteService categoryWriteService;
	
	@Resource
	private  CategoryTreeNodeReadService categoryTreeNodeReadService;
	
	@Resource
	private ProductReadService productReadService;
	
	@Resource
	private CategoryTagReadService categoryTagReadService;
	
        public List<Map<String, Object>> findAll(CategoryDTO dto) {
        	long start = System.currentTimeMillis();
        	List<CategoryDTO> categoryList = categoryReadService.findAll(dto);
        	logger.info("查询所有类目-1:"+(System.currentTimeMillis()-start));
        	start = System.currentTimeMillis();
        	List<Map<String, Object>> categoryTreeNodeDTOList = new ArrayList<>(categoryList.size());
        	for (CategoryDTO categoryDTO : categoryList) {
				Map<String, Object> map = new HashMap<>();
				map.put("id", categoryDTO.getId());
				map.put("categoryTreeNodeId", categoryDTO.getCategoryTreeNodeId());
				map.put("parentId", categoryDTO.getParentId());
				map.put("name", categoryDTO.getName());
				map.put("lists", null);
				categoryTreeNodeDTOList.add(map);
			}
            return recursionOrganizationData(categoryTreeNodeDTOList);
        }
        /**
         * 递归成树结构
         *
         * @param categoryTreeNodeDTOList
         * @return
         */
        private List<Map<String, Object>> recursionOrganizationData(List<Map<String, Object>> categoryTreeNodeDTOList) {
            //递归成树结构
        	long start = System.currentTimeMillis();
            List<Map<String, Object>> sortList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> sortList2 = new ArrayList<Map<String, Object>>();
            HashMap<Long , Map<String, Object>> categoryMap = new HashMap<Long , Map<String, Object>>();
            HashMap<Long , List<Map<String, Object>>> categoryParentMap = new HashMap<Long , List<Map<String, Object>>>();
            for (Map<String, Object> t : categoryTreeNodeDTOList) {
            	Long categoryTreeNodeId = (Long) t.get("categoryTreeNodeId");
            	categoryMap.put(categoryTreeNodeId, t);
            	if(t.get("parentId")==null) {
            		continue;
            	}
            	Long parentId = (Long) t.get("parentId");
            	List<Map<String, Object>> mylistss = categoryParentMap.get(parentId);
            	if(mylistss==null) {
            		mylistss = new ArrayList<Map<String, Object>>();
            		
            		categoryParentMap.put(parentId, mylistss);
            	}
            	mylistss.add(t);           	
            }
            List<Map<String, Object>> level1s = categoryParentMap.get(0l);
            for(Map<String, Object> level1:level1s) {
            	Long level1Id = (Long) level1.get("categoryTreeNodeId");
            	List<Map<String, Object>> level2s = categoryParentMap.get(level1Id);
            	level1.put("lists", level2s);
            	if(level2s!=null && level2s.size()>0) {
            		for(Map<String, Object> level2:level2s) {
                		Long level2Id = (Long) level2.get("categoryTreeNodeId");
                    	List<Map<String, Object>> level3s = categoryParentMap.get(level2Id);
                    	level2.put("lists", level3s);
                    	if(level3s!=null && level3s.size()>0) {
	                    	for(Map<String, Object> level3:level3s) {
	                    		Long level3Id = (Long) level3.get("categoryTreeNodeId");
	                        	List<Map<String, Object>> level4s = categoryParentMap.get(level3Id);
	                        	level3.put("lists", level4s);
	                        	if(level4s!=null && level4s.size()>0) {
		                        	for(Map<String, Object> level4:level4s) {
		                        		Long level4Id = (Long) level4.get("categoryTreeNodeId");
		                            	List<Map<String, Object>> level5s = categoryParentMap.get(level4Id);
		                            	level4.put("lists", level5s);
		                        		
		                        	}
	                        	}
	                    	}
                    	}
                	}
            	}
            	
            	
            }
            
            
            /*categoryParentMap.get(0l);
            for (Map<String, Object> tree : categoryTreeNodeDTOList) {
            	Long categoryTreeNodeId = (Long) tree.get("categoryTreeNodeId");
            	Long parentId = (Long) tree.get("parentId");
            	
            	
                for (Map<String, Object> t : categoryTreeNodeDTOList) {
                    if (t.get("parentId").equals(tree.get("categoryTreeNodeId"))) {
                        if (tree.get("lists") == null) {
                            List<Map<String, Object>> mylistss = new ArrayList<Map<String, Object>>();
                            mylistss.add(t);
                            tree.put("lists", mylistss);
                        } else {
                            List<Map<String, Object>> object = (List<Map<String, Object>>) tree.get("lists");
                            object.add(t);
                            tree.put("lists", object);

                        }
                    }
                }
                if (tree.get("parentId").equals(PlatformKeyConstant.PRODUCT_PLATFORMID)) {
                    sortList2.add(tree);
                }
            }*/
            
/*
        	logger.info("查询所有类目-2:"+(System.currentTimeMillis()-start));
            start = System.currentTimeMillis();*/
            
           /*
            for (Map<String, Object> tree : categoryTreeNodeDTOList) {
            	Long categoryTreeNodeId = (Long) tree.get("categoryTreeNodeId");
            	Long parentId = (Long) tree.get("parentId");
                for (Map<String, Object> t : categoryTreeNodeDTOList) {
                    if (t.get("parentId").equals(tree.get("categoryTreeNodeId"))) {
                        if (tree.get("lists") == null) {
                            List<Map<String, Object>> mylistss = new ArrayList<Map<String, Object>>();
                            mylistss.add(t);
                            tree.put("lists", mylistss);
                        } else {
                            List<Map<String, Object>> object = (List<Map<String, Object>>) tree.get("lists");
                            object.add(t);
                            tree.put("lists", object);

                        }
                    }
                }
                if (tree.get("parentId").equals(PlatformKeyConstant.PRODUCT_PLATFORMID)) {
                    sortList.add(tree);
                }
            }*/
        	logger.info("查询所有类目-3:"+(System.currentTimeMillis()-start));
        	boolean ee = sortList.equals(categoryParentMap.get(0l));
            return categoryParentMap.get(0l);
        }

        public List<String> idToName(String ids) {
            return categoryReadService.idToName(ids);
        }

        public List<CategoryDTO> findAllList(CategoryDTO dto) {
            return categoryReadService.findAllList(dto);
        }

		public CategoryDTO findCategoryById(Long id) {
			return categoryReadService.findCategoryById(id);
		}

		public String modifyCategory(CategoryDTO dto,List<Long> tagIdList) {
			return categoryWriteService.modifyCategoryWithTX(dto,tagIdList);
		}

		public String deleteCategoryByIdWithTx(Long categoryId) {
			return categoryWriteService.deleteCategoryByIdWithTx(categoryId);
		}
		/**
		 * 根据类目id查询产品
		 * 
		 * @param categoryId
		 * @return
		 */
		public List<ProductDTO> productListByCategoryId(Long categoryId){
			List<ProductDTO> productList = new ArrayList<ProductDTO>();
			CategoryTreeNodeDTO dto = new CategoryTreeNodeDTO();
			dto.setCategoryId(categoryId);
			List<CategoryTreeNodeDTO> categoryTreeNodeList = categoryTreeNodeReadService.findAll(dto);
			if(categoryTreeNodeList.size() > 0){
				//根据节点id查询产品信息
				ProductDTO productDTO = new ProductDTO();
				productDTO.setCategoryTreeNodeId(categoryTreeNodeList.get(0).getId());
				productList = productReadService.findAll(productDTO);
				if(productList.size() > 0){
					return productList;
				}
			}
			return productList;
			
		}
		/**
		 * 根据类目id查询类目标签信息
		 * @param categoryId
		 * @return
		 */
		public List<CategoryTagDTO> categoryTagAllByCategoryId(Long categoryId){
			CategoryTagDTO categoryTagDTO = new CategoryTagDTO();
			categoryTagDTO.setCategoryId(categoryId);
			return categoryTagReadService.findCategoryTagAll(categoryTagDTO);
		}

}
	