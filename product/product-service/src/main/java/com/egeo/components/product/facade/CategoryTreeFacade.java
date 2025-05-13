package com.egeo.components.product.facade;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.cms.client.LinkableButtonPageClient;
import com.egeo.components.cms.client.LinkableParamClient;
import com.egeo.components.cms.dto.LinkableButtonPageDTO;
import com.egeo.components.cms.dto.LinkableParamDTO;
import com.egeo.components.product.dto.CategoryTreeDTO;
import com.egeo.components.product.dto.CategoryTreeNodeDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.service.read.CategoryTreeNodeReadService;
import com.egeo.components.product.service.read.CategoryTreeReadService;
import com.egeo.components.product.service.read.StandardUnitReadService;
import com.egeo.components.product.service.write.CategoryTreeWriteService;
import com.egeo.components.user.client.CompanyCoreClient;
import com.egeo.config.RuntimeContext;
import com.egeo.core.Constant.BusinessExceptionConstant;
import com.egeo.core.Constant.CmsConstant;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Component
public class CategoryTreeFacade {
	
	@Resource
	private CategoryTreeReadService categoryTreeReadService;
	
	@Resource
	private CategoryTreeWriteService categoryTreeWriteService;
	
	@Resource
	private  CategoryTreeNodeReadService categoryTreeNodeReadService;
	
	@Resource
	private StandardUnitReadService standardUnitReadService;
	
	@Autowired
	private CompanyCoreClient companyCoreReadService;
	@Autowired
	private LinkableButtonPageClient linkableButtonPageReadService;
	
	@Autowired
	private LinkableParamClient linkableParamReadService;

	public List<CategoryTreeDTO> queryAllCategoryTreeByParam(CategoryTreeDTO dto) {
		return categoryTreeReadService.queryAllCategoryTreeByParam(dto);
	}

	public String addCategoryTree(CategoryTreeDTO dto) {
		return categoryTreeWriteService.addCategoryTreeWithTx(dto);
	}
	/**
	 * 查询前台类目树（目前启用只可能有一棵）
	 */
	public CategoryTreeDTO findCategoryByType(Long companyId,Long enterpriseId,Long clientId, Long platformId,Integer isDefault) {
		CategoryTreeDTO categoryTreeDTO = new CategoryTreeDTO();
		categoryTreeDTO.setCompanyId(companyId);
		categoryTreeDTO.setEnterpriseId(enterpriseId);
        Integer companyType;
    	if(Objects.isNull(RuntimeContext.cacheUser()) || Objects.isNull(RuntimeContext.cacheUser().getCompanyType())) {
    		companyType = companyCoreReadService.findCompanyTypeById(companyId);
    	}else {
    		companyType = RuntimeContext.cacheUser().getCompanyType();
    	}
		//前台类目
		categoryTreeDTO.setType(2);
		// web客户端类目树为启用 
		if(clientId.intValue() == 3){
			categoryTreeDTO.setWebStart(1);
		}else{
			// app类目树状态为启用
			categoryTreeDTO.setStartUsing(1);
		}
		categoryTreeDTO.setCompanyType(companyType);
		categoryTreeDTO.setPlatformId(platformId);
		List<CategoryTreeDTO> list = categoryTreeReadService.findCategoryAll(categoryTreeDTO);
		if(EmptyUtil.isEmpty(list)){
			//查询默认前台类目树
			if (Objects.equals(isDefault,1)){
				categoryTreeDTO.setCompanyId(null);
				categoryTreeDTO.setIsDefault(isDefault);
				list = categoryTreeReadService.findCategoryAll(categoryTreeDTO);
			}
		}
		if (EmptyUtil.isEmpty(list)){
			throw new BusinessException(BusinessExceptionConstant.FRONT_CATEGORYTREE_NO_SERIES,"前台类目树未启用");
		}
		if(list.size() > 1){
			throw new BusinessException(BusinessExceptionConstant.FRONT_CATEGORYTREE_COMB,"前台类目树启用多个，请联系大厨管家客服");
		}
		return list.get(0);
	}
	/**
	 * 根据类目树id查询所有一级类目信息(一级类目上级节点为0)
	 * @param id
	 * @param i
	 * @param platformId
	 * @return
	 */
	public List<CategoryTreeNodeDTO> findCategoryTreeByCatIdAndParentId(Long categoryTreeId, Long parentId, Long platformId) {
		CategoryTreeNodeDTO categoryTreeNodeDTO = new CategoryTreeNodeDTO();
		categoryTreeNodeDTO.setCategoryTreeId(categoryTreeId);
		categoryTreeNodeDTO.setParentId(parentId);
		categoryTreeNodeDTO.setPlatformId(platformId);
		return categoryTreeNodeReadService.findCategoryTreeNodeAll(categoryTreeNodeDTO);
	}
	/**
	 * 分页查询前台类目树
	 * @param req
	 * @return
	 */
	public PageResult<CategoryTreeDTO> findCategoryTreeOfPage(CategoryTreeDTO dto, Pagination page) {
		// TODO Auto-generated method stub
		return categoryTreeReadService.findCategoryTreeOfPage(dto, page);
	}
	/**
	 * 根据类目树id将类目树设为启用
	 */
	public boolean categoryTreeStartUsingWithTx(Long categoryTreeId,Integer companyType,Integer clientType, Long platformId) {
		// TODO Auto-generated method stub
		return categoryTreeWriteService.categoryTreeStartUsingWithTx(categoryTreeId,companyType,clientType, platformId);
	}

	/**
	 * 根据类目树id将类目树设为启用
	 */
	public boolean categoryTreeStopUsingWithTx(Long categoryTreeId) {
		// TODO Auto-generated method stub
		return categoryTreeWriteService.categoryTreeStopUsingWithTx(categoryTreeId);
	}
	/**
	 * 根据类目树id修改类目树信息
	 * @param vo
	 * @param req
	 * @return
	 */
	public boolean updateCategoryTreeWithTx(CategoryTreeDTO dto) {
		// TODO Auto-generated method stub
		return categoryTreeWriteService.updateCategoryTreeWithTx(dto);
	}
	/**
	 * 根据类目树id查询类目树信息
	 */
	public CategoryTreeDTO findByCategoryTreeId(Long categoryTreeId) {
		
		return categoryTreeReadService.findByCategoryTreeId(categoryTreeId);
	}
	/**
	 * 查询所有类目树信息
	 */
	public List<CategoryTreeDTO> findCategoryTreeAll(Long platformId) {
		return categoryTreeReadService.findCategoryTreeAll(platformId);
	}
	/**
	 * 根据suid查询su详情
	 * @param standardUnitId
	 * @return
	 */
	public StandardUnitDTO findStandardUnitById(Long standardUnitId){
		return standardUnitReadService.findStandardUnitById(standardUnitId);
		
	}
	
	public void findExtParamByLinkableId(Long linkableId,Map<String, Object> map) {
		
		if(EmptyUtil.isEmpty(linkableId)) {
			return ;
		}
		LinkableParamDTO dto = new LinkableParamDTO();
		dto.setLinkButtonId(linkableId);
		List<LinkableParamDTO> list = linkableParamReadService.findLinkableParamAll(dto);
		if(list != null && list.size() > 0) {
			for (LinkableParamDTO linkableParamDTO : list) {
				map.put(linkableParamDTO.getName(), linkableParamDTO.getValue());
			}
		}
		
	}

	public Long findCmsPageIdByLinkableId(Long linkableId,Integer linkType,Long clinetId,Long platformId) {
		
		return queryCmsPageIdByLinkbaleId(linkableId,linkType,clinetId,platformId);
	}

	/***
	 * 根据linkableId 查询跳转pageid
	 * @param linkableId
	 * @return
	 */
	private Long queryCmsPageIdByLinkbaleId(Long linkableId, Integer linkType,Long clinetId,Long platformId){
		
		Long cmsPageId = null;
		
		if(linkType == null || linkType != CmsConstant.CMS_LINK_TYPE_SU_LIST) {
			return null;
		}
		
		Integer clinetType = clinetId == 3 ? CmsConstant.CMS_CLINTE_TYPE_PC : CmsConstant.CMS_CLINTE_TYPE_MOBILE;
		
		List<LinkableButtonPageDTO> listDto = linkableButtonPageReadService.findLinkableButtonPageAll(new LinkableButtonPageDTO(linkableId));
		
		// 没有配置的情况下 获取默认页面
		if(listDto != null && listDto.size() > 0) {
			for (LinkableButtonPageDTO linkableButtonPageDTO : listDto) {
				
				if(clinetType == linkableButtonPageDTO.getClientType()) {
					cmsPageId = linkableButtonPageDTO.getCmsPageId();
				}
			}
		}
		
		if(cmsPageId == null) {
			if(platformId == CmsConstant.CMS_PLATFORM_FGJ ) {
				if(clinetType == CmsConstant.CMS_CLINTE_TYPE_MOBILE) {
					cmsPageId = CmsConstant.CMS_DEFAULT_PAGE_SU_1;
				}else if(clinetType == CmsConstant.CMS_CLINTE_TYPE_PC) {
					cmsPageId = CmsConstant.CMS_DEFAULT_PAGE_SU_2;
				}
			}else if(platformId == CmsConstant.CMS_PLATFORM_MYY ) {
				if(clinetType == CmsConstant.CMS_CLINTE_TYPE_MOBILE) {
					cmsPageId = CmsConstant.CMS_DEFAULT_PAGE_SU_9;
				}else if(clinetType == CmsConstant.CMS_CLINTE_TYPE_PC) {
					cmsPageId = CmsConstant.CMS_DEFAULT_PAGE_SU_10;
				}

			}
		}
			
		return cmsPageId;
	}

}
	