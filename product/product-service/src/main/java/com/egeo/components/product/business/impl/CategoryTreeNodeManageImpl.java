package com.egeo.components.product.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.product.business.CategoryTreeNodeManage;
import com.egeo.components.product.converter.CategoryTreeNodeConverter;
import com.egeo.components.product.dto.CategoryDTO;
import com.egeo.components.product.dto.CategoryTreeDTO;
import com.egeo.components.product.dto.CategoryTreeNodeDTO;
import com.egeo.components.product.facade.CategoryFacade;
import com.egeo.components.product.facade.CategoryTreeFacade;
import com.egeo.components.product.facade.CategoryTreeNodeFacade;
import com.egeo.components.product.vo.CategoryTreeNodeVO;
import com.egeo.core.Constant.CmsConstant;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("categoryTreeNode")
public class CategoryTreeNodeManageImpl implements CategoryTreeNodeManage{

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="categoryTreeNodeFacade")
	private CategoryTreeNodeFacade categoryTreeNodeFacade;
	
	@Resource(name="categoryTreeFacade")
	private CategoryTreeFacade categoryTreeFacade;

	@Resource(name="categoryFacade")
	private CategoryFacade categoryFacade;

	@Override
	public List<Map<String, Object>> getCategoryTree(Long categoryId, Long platformId) {
		 return categoryTreeNodeFacade.getCategoryTreeByCatId(categoryId,platformId);
	}

        @Override
        public CategoryTreeNodeDTO CategoryTreeNodeByCategoryId(Long parentId) {
            return categoryTreeNodeFacade.CategoryTreeNodeByCategoryId(parentId);
        }

		@Override
		public String addCategoryTreeNode(CategoryTreeNodeVO categoryTreeNodeVO,String listSort,List<Long> tagIdList, HttpServletRequest req) {
			CategoryTreeNodeDTO dto = CategoryTreeNodeConverter.toDTO(categoryTreeNodeVO);
			if(EmptyUtil.isNotEmpty(listSort)){
				dto.setListSort(Integer.valueOf(listSort));
			}else{
				//查询该表排序最大的一个值加一
				dto.setListSort(categoryTreeNodeFacade.listSortMax()+1);
			}
			/*if(dto.getSerialNumber().length() == 1){
				dto.setSerialNumber("0"+categoryTreeNodeVO.getSerialNumber());
			}
			if(!dto.getParentId().equals(0L)){
				//根据上级节点信息查询类目信息
				CategoryDTO categoryDTO = categoryTreeNodeFacade.categoryByPIdNode(dto.getParentId());
				dto.setSerialNumber(categoryDTO.getSerialNumber()+dto.getSerialNumber());
			}*/
			//校验数据库中是否已存在此序列号，如果已存在，重新获取
			CategoryTreeNodeDTO findDTO = new CategoryTreeNodeDTO();
			findDTO.setSerialNumber(dto.getSerialNumber());
			List<CategoryTreeNodeDTO> dbList = categoryTreeNodeFacade.findAll(dto);
			if (EmptyUtil.isNotEmpty(dbList)) {
				dto.setSerialNumber(categoryTreeNodeFacade.findMaxSubSerialNumber(dto.getCategoryTreeId(), dto.getParentId()));
			}
			return categoryTreeNodeFacade.addCategoryTreeNodeWithTx(dto,tagIdList);
		}

	@Override
        public CategoryTreeNodeDTO findById(CategoryTreeNodeVO categoryTreeNodeVO) {
            
            return categoryTreeNodeFacade.findById(CategoryTreeNodeConverter.toDTO(categoryTreeNodeVO));
        }

        @Override
        public List<CategoryTreeNodeDTO> findAll(CategoryTreeNodeVO categoryTreeNodeVO) {
            return categoryTreeNodeFacade.findAll(CategoryTreeNodeConverter.toDTO(categoryTreeNodeVO));
        }

		@Override
		public CategoryTreeNodeDTO findCategoryTreeNodeInofByNodeId(Long ctnId) {
			return categoryTreeNodeFacade.findCategoryTreeNodeInofByNodeId(ctnId);
		}

		@Override
		public int listSortMax() {
			
			return categoryTreeNodeFacade.listSortMax();
		}

		@Override
		public int updateCategoryTreeNodeWithTx(CategoryTreeNodeDTO categoryTreeNodeDTO) {
			if(EmptyUtil.isEmpty(categoryTreeNodeDTO.getId())){
				throw new BusinessException("类目节点id不能为空");
			}
			return categoryTreeNodeFacade.updateCategoryTreeNodeWithTx(categoryTreeNodeDTO);
		}
		/**
		 * 根据id删除类目节点信息
		 */
		@Override
		public int deleteByIdWithTx(CategoryTreeNodeDTO categoryTreeNodeDTO) {
			return categoryTreeNodeFacade.deleteByIdWithTx(categoryTreeNodeDTO);
		}
		/**
		 * 根据类目节点id查询子类目节点信息
		 * @param categoryTreeNodeId
		 * @return
		 */
		@Override
		public List<Map<String, Object>> findSeedCategoryTreeNodeAll(Long categoryTreeNodeId) {
			List<CategoryTreeNodeDTO> list = categoryTreeNodeFacade.findSeedCategoryTreeNodeAll(categoryTreeNodeId);
			List<Map<String, Object>> categoryList = new ArrayList<>();
			for (CategoryTreeNodeDTO categoryTreeNodeDTO : list) {
				//转map
				Map<String, Object> categoryMap = categoryTreeNodeDTOToMap(categoryTreeNodeDTO);
				categoryList.add(categoryMap);
			}
			return categoryList;
		}
		
		private Map<String, Object> categoryTreeNodeDTOToMap(CategoryTreeNodeDTO categoryTreeNodeDTO) {
			Map<String, Object> categoryMap = new HashMap<>();
			categoryMap.put("categoryTreeNodeId", categoryTreeNodeDTO.getId());
			categoryMap.put("categoryName", categoryTreeNodeDTO.getName());
			categoryMap.put("categoryUrl", categoryTreeNodeDTO.getBannerImg());
			return categoryMap;
		}
		/**
		 * 添加前台类目节点信息
		 * @param categoryTreeNodeVO
		 * @return
		 */
		@Override
		public Long saveCategoryTreeNodeWithTx(CategoryTreeNodeVO categoryTreeNodeVO,String listSort,
				String linkableButtonPageList,String extParam) {
			CategoryTreeNodeDTO dto = CategoryTreeNodeConverter.toDTO(categoryTreeNodeVO);
			if(EmptyUtil.isNotEmpty(listSort)){
				dto.setListSort(Integer.valueOf(listSort));
			}else{
				//查询该表排序最大的一个值加一
				dto.setListSort(categoryTreeNodeFacade.listSortMax()+1);
			}
			/*if(dto.getSerialNumber().length() == 1){
				dto.setSerialNumber("0"+categoryTreeNodeVO.getSerialNumber());
			}
			if(!dto.getParentId().equals(0L)){
				//根据上级节点信息查询类目信息
				CategoryDTO categoryDTO = categoryTreeNodeFacade.categoryByPIdNode(dto.getParentId());
				dto.setSerialNumber(categoryDTO.getSerialNumber()+dto.getSerialNumber());
			}*/
			return categoryTreeNodeFacade.saveCategoryTreeNodeWithTx(CategoryTreeNodeConverter.toDTO(categoryTreeNodeVO),linkableButtonPageList,extParam);
		}

	/**
	 * 根据类目节点id修改前台类目节点信息
	 * @param categoryTreeNodeVO
	 * @param listSort
	 * @return
	 */
		@Override
		public boolean updateCategoryTreeNodeCategoryWithTx(CategoryTreeNodeVO categoryTreeNodeVO, String listSort,
				String linkableButtonPageList,String extParam) {
			CategoryTreeNodeDTO dto = CategoryTreeNodeConverter.toDTO(categoryTreeNodeVO);
			if(EmptyUtil.isNotEmpty(listSort)){
				dto.setListSort(Integer.valueOf(listSort));
			}else{
				//查询该表排序最大的一个值加一
				dto.setListSort(categoryTreeNodeFacade.listSortMax()+1);
			}
			/*if(dto.getSerialNumber().length() == 1){
				dto.setSerialNumber("0"+categoryTreeNodeVO.getSerialNumber());
			}
			if(!dto.getParentId().equals(0L)){
				//根据上级节点信息查询类目信息
				CategoryDTO categoryDTO = categoryTreeNodeFacade.categoryByPIdNode(dto.getParentId());
				dto.setSerialNumber(categoryDTO.getSerialNumber()+dto.getSerialNumber());
			}*/
			return categoryTreeNodeFacade.updateCategoryTreeNodeCategoryWithTx(CategoryTreeNodeConverter.toDTO(categoryTreeNodeVO),linkableButtonPageList,extParam);
		}

	/**
	 * 根据前台类目节点id查询前台类目节点信息
	 * @param categoryTreeNodeId
	 * @return
	 */
		@Override
		public Map<String, Object> findByCategoryTreeNodeId(Long categoryTreeNodeId) {
			CategoryTreeNodeDTO categoryTreeNodeDTO = categoryTreeNodeFacade.findByCategoryTreeNodeId(categoryTreeNodeId);
			Map<String, Object> map = new HashMap<>();
			map.put("id", categoryTreeNodeDTO.getId());
			map.put("listSort", categoryTreeNodeDTO.getListSort());
			map.put("name", categoryTreeNodeDTO.getName());
			map.put("description", categoryTreeNodeDTO.getDescription());
			map.put("bannerImg", categoryTreeNodeDTO.getBannerImg());
			map.put("linkType", categoryTreeNodeDTO.getLinkType());
			map.put("linkUrl", categoryTreeNodeDTO.getLinkUrl());
			map.put("externalLinkId", categoryTreeNodeDTO.getExternalLinkId());
			map.put("standardUnitCombinationId", categoryTreeNodeDTO.getStandardUnitCombinationId());
			map.put("standardUnitId", categoryTreeNodeDTO.getStandardUnitId());
			map.put("localParamId", categoryTreeNodeDTO.getLocalParamId());
			map.put("categoryTreeId", categoryTreeNodeDTO.getCategoryTreeId());
			List<Long> pid = new ArrayList<>();
			List<Long> list = PId(categoryTreeNodeDTO,pid);
			map.put("pids", list);
			
			if(categoryTreeNodeDTO != null && categoryTreeNodeDTO.getLinkableId() != null) {
				if(CmsConstant.CMS_LINK_TYPE_SU_LIST == categoryTreeNodeDTO.getLinkType()) {
					map.put("linkableButtonPageList", categoryTreeNodeFacade.queryLinkableButtonPagByLinkableId(categoryTreeNodeDTO.getLinkableId()));
				}
				map.put("extParam", categoryTreeNodeFacade.findLinkParamByLinkableId(categoryTreeNodeDTO.getLinkableId()));
			}
			
			return map;
		}

	/**
	 * 根据前台类目节点id删除前台类目节点及子节点信息
	 * @param categoryTreeNodeId
	 * @return
	 */
		@Override
		public boolean delByCategoryTreeNodeIdWithTx(Long categoryTreeNodeId) {
			// TODO Auto-generated method stub
			return categoryTreeNodeFacade.delByCategoryTreeNodeIdWithTx(categoryTreeNodeId);
		}

	/**
	 * 根据类目树id查询前台类目节点信息
	 * @param categoryTreeId
	 * @return
	 */
		@Override
		public List<Map<String, Object>> findByCategoryTreeId(Long categoryTreeId) {
			// TODO Auto-generated method stub
			return categoryTreeNodeFacade.findByCategoryTreeId(categoryTreeId);
		}
		
		// 循环获取所有父类id
	    public List<Long> PId(CategoryTreeNodeDTO dto,List<Long> pid) {
	        if(!dto.getParentId().equals(PlatformKeyConstant.PRODUCT_PLATFORMID)){
	        	pid.add(0,dto.getParentId());
	            CategoryTreeNodeDTO categoryTreeNodeDTO = categoryTreeNodeFacade.CategoryTreeNodeByCategoryId(dto.getParentId());
	            PId(categoryTreeNodeDTO,pid);
	        }
	        return pid;
	    }
	    /**
		 * 根据类目树id查询类目树信息
		 * @param categoryTreeId
		 * @return
		 */
		@Override
		public CategoryTreeDTO findCategoryTreeByCategoryTreeNodeId(Long categoryTreeId) {
			// TODO Auto-generated method stub
			return categoryTreeNodeFacade.findCategoryTreeByCategoryTreeNodeId(categoryTreeId);
		}
		@Override
		public Map<String, Object> findWebCategoryByUserId(Long companyId,Pagination page,Long clientId, Long platformId) {
			Map<String, Object> map = new HashMap<>();
			CategoryTreeDTO categoryTreeDTO = categoryTreeFacade.findCategoryByType(companyId,null,clientId, platformId,null);
			map.put("categoryTreeId", categoryTreeDTO.getId());
			map.put("categoryTreeTemplateId", categoryTreeDTO.getCategoryTreeTemplateId());
			map.put("seriesType", categoryTreeDTO.getSeriesType());
			//根据类目树id查询所有类目信息
			PageResult<Map<String, Object>> categoryList = categoryTreeNodeFacade.findWebCategoryTreeByCategoryTreeId(categoryTreeDTO.getId(),categoryTreeDTO.getPlatformId(),page);
			map.put("pageCategoryList", categoryList);
			return map;
		}

	@Override
	public List<Map<String, Object>> queryAllCategoryTreeNodeListVoByType(Long categoryTreeId, Long platformId) {
		return categoryTreeNodeFacade.queryAllCategoryTreeNodeListVoByType(categoryTreeId, platformId);
	}


	@Override
	public String findMaxSubSerialNumber(Long categoryTreeId, Long parentId) {
		CategoryTreeNodeDTO categoryTreeNodeDTO = new CategoryTreeNodeDTO();
		categoryTreeNodeDTO.setCategoryId(parentId);

		String maxSerialNumberStr = categoryTreeNodeFacade.findMaxSubSerialNumber(categoryTreeId, parentId);
		if (EmptyUtil.isEmpty(maxSerialNumberStr)) {
			if (EmptyUtil.isEmpty(parentId)) {
				return "01";
			}else {
				CategoryTreeNodeDTO categoryTreeNodeDTO2 = new CategoryTreeNodeDTO();
				categoryTreeNodeDTO2.setId(parentId);
				CategoryTreeNodeDTO categoryTreeNodeDTO3 = categoryTreeNodeFacade.findById(categoryTreeNodeDTO2);
				long categoryId = categoryTreeNodeDTO3.getCategoryId();
				CategoryDTO categoryDTO = categoryFacade.findCategoryById(categoryId);
				String seriaNumber = categoryDTO.getSerialNumber();
				return seriaNumber + "01";
			}
		}
		Integer maxSerialNumber = Integer.parseInt(maxSerialNumberStr);
		if (parentId.equals(0L) && maxSerialNumber >= 99) {
			throw new BusinessException("一级类目数量已满,无法增加新的一级类目");
		}
		if (maxSerialNumber % 100 == 99) {
			throw new BusinessException("子类目数量已满,无法增加新的一级类目");
		}
		maxSerialNumber += 101;
		String newSerialNumberStr = String.valueOf(maxSerialNumber);
		return maxSerialNumberStr.substring(0, maxSerialNumberStr.length() - 2) + newSerialNumberStr.substring(newSerialNumberStr.length() - 2);
	}

	@Override
	public String getMaxSubSerialNumber(Long categoryTreeId, Long parentId) {
		CategoryTreeNodeDTO categoryTreeNodeDTO = new CategoryTreeNodeDTO();
		categoryTreeNodeDTO.setCategoryId(parentId);

		String maxSerialNumberStr = categoryTreeNodeFacade.findMaxSubSerialNumber(categoryTreeId, parentId);
		return maxSerialNumberStr;
	}
}
	