package com.egeo.components.product.business.impl;


import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.utils.EmptyUtil;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.CategoryTreeManage;
import com.egeo.components.product.converter.CategoryTreeConverter;
import com.egeo.components.product.dto.CategoryTreeDTO;
import com.egeo.components.product.dto.CategoryTreeNodeDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.facade.CategoryTreeFacade;
import com.egeo.components.product.vo.CategoryTreeVO;
import com.egeo.core.Constant.CmsConstant;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("categoryTree")
public class CategoryTreeManageImpl implements CategoryTreeManage {

	private static final Logger logger = LoggerFactory.getLogger(CategoryTreeManageImpl.class);

    @Resource(name = "categoryTreeFacade")
    private CategoryTreeFacade categoryTreeFacade;
    @Autowired
    private CompanyClient companyClient;

    @Override
    public List<CategoryTreeVO> queryAllCategoryTreeByParam(CategoryTreeVO categoryTreeVO) {
        CategoryTreeDTO dto = CategoryTreeConverter.toDTO(categoryTreeVO);
        return CategoryTreeConverter.toVO(categoryTreeFacade.queryAllCategoryTreeByParam(dto));
    }

    @Override
    public String addCategoryTree(CategoryTreeVO categoryTreeVO, HttpServletRequest req) {

        CategoryTreeDTO dto = CategoryTreeConverter.toDTO(categoryTreeVO);

        return categoryTreeFacade.addCategoryTree(dto);
    }

    @Override
    public List<CategoryTreeVO> queryCategoryTree(Long platformId) {

        CategoryTreeDTO dto = new CategoryTreeDTO();
        dto.setPlatformId(platformId);
        return CategoryTreeConverter.toVO(categoryTreeFacade.queryAllCategoryTreeByParam(dto));
    }

    @Override
    public List<CategoryTreeVO> queryCategoryTreeByType(Long platformId, Integer type) {
        CategoryTreeDTO dto = new CategoryTreeDTO();
        //后台类目树只有一颗、无需平台id
        //dto.setPlatformId(platformId);
        dto.setType(type);
        return CategoryTreeConverter.toVO(categoryTreeFacade.queryAllCategoryTreeByParam(dto));
    }

    /**
     * 查询前台类目树（目前启用只可能有一棵）
     */
    @Override
    public Map<String, Object> findCategoryByType(Long companyId,Long enterpriseId, Long clientId, Long platformId,Integer isDefault) {
        Map<String, Object> map = new HashMap<>();
        CategoryTreeDTO categoryTreeDTO = categoryTreeFacade.findCategoryByType(companyId,enterpriseId, clientId, platformId,isDefault);
        map.put("categoryTreeId", categoryTreeDTO.getId());
        map.put("categoryTreeTemplateId", categoryTreeDTO.getCategoryTreeTemplateId());
        map.put("seriesType", categoryTreeDTO.getSeriesType());
        //根据类目树id查询所有一级类目信息(一级类目上级节点为0)
        List<CategoryTreeNodeDTO> list = categoryTreeFacade.findCategoryTreeByCatIdAndParentId(categoryTreeDTO.getId(), 0L, categoryTreeDTO.getPlatformId());
        List<Map<String, Object>> categoryList = new ArrayList<>();
        for (CategoryTreeNodeDTO categoryTreeNodeDTO : list) {
            //转map
            Map<String, Object> categoryMap = categoryTreeNodeDTOToMap(categoryTreeNodeDTO, clientId);
            if(categoryTreeNodeDTO.getLinkableId() != null) {
            	logger.info("查询前台类目树跳转配置  linkableId：" + categoryTreeNodeDTO.getLinkableId());
            	//获取商品列表页id
            	categoryMap.put("cmsPageId", categoryTreeFacade.findCmsPageIdByLinkableId(categoryTreeNodeDTO.getLinkableId(),categoryTreeNodeDTO.getLinkType(),clientId,platformId));
            	//获取extParam参数
            	categoryTreeFacade.findExtParamByLinkableId(categoryTreeNodeDTO.getLinkableId(),categoryMap);
            }
            
            categoryList.add(categoryMap);
        }
        map.put("categoryList", categoryList);
        return map;
    }

    private Map<String, Object> categoryTreeNodeDTOToMap(CategoryTreeNodeDTO categoryTreeNodeDTO,Long clientId) {
        Map<String, Object> categoryMap = new HashMap<>();
        categoryMap.put("categoryTreeNodeId", categoryTreeNodeDTO.getId());
        categoryMap.put("categoryName", categoryTreeNodeDTO.getName());
        categoryMap.put("bannerUrl", categoryTreeNodeDTO.getBannerImg());
        categoryMap.put("link_type", categoryTreeNodeDTO.getLinkType());
        categoryMap.put("commodityTemplateId", null);
        categoryMap.put("available", true);
        //Banner类型 1.本地参数、2.H5链接（内部）、3.H5链接（外部）、4.商品组合、5.商品、6.无效果
        if (categoryTreeNodeDTO.getLinkType() == 1) {
            categoryMap.put("link_id", categoryTreeNodeDTO.getLocalParamId());
        }
        if (categoryTreeNodeDTO.getLinkType() == 3) {
            categoryMap.put("link_id", categoryTreeNodeDTO.getExternalLinkId());
        }
        if (categoryTreeNodeDTO.getLinkType() == 4) {
            categoryMap.put("link_id", categoryTreeNodeDTO.getStandardUnitCombinationId());
        }
        if (categoryTreeNodeDTO.getLinkType() == 5) {
            categoryMap.put("link_id", categoryTreeNodeDTO.getStandardUnitId());
            if (categoryTreeNodeDTO.getStandardUnitId() != null) {
            	//根据spuid查询spu信息
                StandardUnitDTO standardUnitDTO = categoryTreeFacade.findStandardUnitById(categoryTreeNodeDTO.getStandardUnitId());
                categoryMap.put("commodityTemplateId", standardUnitDTO.getCommodityTemplateId());
            } else {
            	categoryMap.put("commodityTemplateId", "");
            }
        }
        if (categoryTreeNodeDTO.getLinkType() == 8) {
            categoryMap.put("link_id", categoryTreeNodeDTO.getLocalParamId());
        }
        categoryMap.put("link_url", categoryTreeNodeDTO.getLinkUrl());
        categoryMap.put("link_param", categoryTreeNodeDTO.getLinkParam());
        
        setCmsPageId(categoryMap,categoryTreeNodeDTO.getPlatformId(),clientId);
        
        return categoryMap;
    }

    /**
     * 分页查询前台类目树
     *
     * @param req
     * @return
     */
    @Override
    public PageResult<Map<String, Object>> findCategoryTreeOfPage(CategoryTreeVO categoryTreeVO, Pagination page) {
        List<Map<String, Object>> maps = new ArrayList<>();
        PageResult<CategoryTreeDTO> pageResult = categoryTreeFacade.findCategoryTreeOfPage(CategoryTreeConverter.toDTO(categoryTreeVO), page);
        List<CategoryTreeDTO> list = pageResult.getList();
        Set<Long> companyIds = new HashSet<>();
        list.forEach(item->{
            if (Objects.nonNull(item.getCompanyId())){
                companyIds.add(item.getCompanyId());
            }
        });
        Map<Long, String> companyNameMap=findCompanyByIdList(Lists.newArrayList(companyIds));
        for (CategoryTreeDTO categoryTreeDTO : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("categoryTreeId", categoryTreeDTO.getId());
            map.put("categoryTreeName", categoryTreeDTO.getName());
            map.put("seriesType", categoryTreeDTO.getSeriesType());
            map.put("startUsing", categoryTreeDTO.getStartUsing());
            map.put("content", categoryTreeDTO.getContent());
            map.put("webStart", categoryTreeDTO.getWebStart());
            map.put("companyId",categoryTreeDTO.getCompanyId());
            map.put("isDefault",categoryTreeDTO.getIsDefault());
            if (Objects.nonNull(categoryTreeDTO.getCompanyId())
                    &&companyNameMap.containsKey(categoryTreeDTO.getCompanyId())){
                map.put("companyName",companyNameMap.get(categoryTreeDTO.getCompanyId()));
            }else {
                map.put("companyName","");
            }
            maps.add(map);
        }
        PageResult<Map<String, Object>> result = new PageResult<>();
        result.setList(maps);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    /**
     * 根据类目树id将类目树设为启用
     */
    @Override
    public boolean categoryTreeStartUsingWithTx(Long categoryTreeId, Integer companyType, Integer clientType, Long platformId) {
        // TODO Auto-generated method stub
        return categoryTreeFacade.categoryTreeStartUsingWithTx(categoryTreeId, companyType, clientType, platformId);
    }

    @Override
    public boolean categoryTreeStopUsingWithTx(Long categoryTreeId) {
        return categoryTreeFacade.categoryTreeStopUsingWithTx(categoryTreeId);
    }

    /**
     * 根据类目树id修改类目树信息
     *
     * @param vo
     * @param req
     * @return
     */
    @Override
    public boolean updateCategoryTreeWithTx(CategoryTreeVO vo) {
        // TODO Auto-generated method stub
        return categoryTreeFacade.updateCategoryTreeWithTx(CategoryTreeConverter.toDTO(vo));
    }

    /**
     * 根据类目树id查询类目树信息
     */
    @Override
    public Map<String, Object> findByCategoryTreeId(Long categoryTreeId) {
        Map<String, Object> map = new HashMap<>();
        CategoryTreeDTO categoryTreeDTO = categoryTreeFacade.findByCategoryTreeId(categoryTreeId);
        map.put("categoryTreeId", categoryTreeDTO.getId());
        map.put("categoryTreeName", categoryTreeDTO.getName());
        map.put("content", categoryTreeDTO.getContent());
        map.put("seriesType", categoryTreeDTO.getSeriesType());
        map.put("categoryTreeTemplateId", categoryTreeDTO.getCategoryTreeTemplateId());
        map.put("companyType", categoryTreeDTO.getCompanyType());
        map.put("companyId",categoryTreeDTO.getCompanyId());
        map.put("isDefault",categoryTreeDTO.getIsDefault());
        return map;
    }

    /**
     * 查询所有类目树信息
     */
    @Override
    public List<Map<String, Object>> findCategoryTreeAll(Long platformId) {
        List<Map<String, Object>> maps = new ArrayList<>();
        List<CategoryTreeDTO> categoryTreeDTOs = categoryTreeFacade.findCategoryTreeAll(platformId);
        for (CategoryTreeDTO categoryTreeDTO : categoryTreeDTOs) {
            Map<String, Object> map = new HashMap<>();
            map.put("categoryTreeId", categoryTreeDTO.getId());
            map.put("categoryTreeName", categoryTreeDTO.getName());
            maps.add(map);
        }
        return maps;
    }

    @Override
    public List<Map<String, Object>> queryCategoryTreeVoByType(Long platformId, Integer type) {
        CategoryTreeDTO dto = new CategoryTreeDTO();
        List<Map<String, Object>> maps = new ArrayList<>();
        dto.setPlatformId(platformId);
        dto.setType(type);
        List<CategoryTreeDTO> categoryTreeDTOs = categoryTreeFacade.queryAllCategoryTreeByParam(dto);
        for (CategoryTreeDTO categoryTreeDTO : categoryTreeDTOs) {
            Map<String, Object> map = new HashMap<>();
            map.put("categoryTreeId", categoryTreeDTO.getId());
            map.put("categoryTreeName", categoryTreeDTO.getName());
            maps.add(map);
        }
        return maps;
    }
    
    /**
     * 目前只给初始化pageId
     */
    void setCmsPageId(Map<String, Object> map,Long platformId , Long clientId){
    	
    	Long cmsPageId = null;
    	if(clientId == null) {
    		return;
    	}
    	Integer clientType = clientId == 3 ? CmsConstant.CMS_CLINTE_TYPE_PC : CmsConstant.CMS_CLINTE_TYPE_MOBILE;
    	
    	if(platformId == CmsConstant.CMS_PLATFORM_FGJ) {
    		if(clientType == CmsConstant.CMS_CLINTE_TYPE_PC) {
    			cmsPageId = CmsConstant.CMS_DEFAULT_PAGE_SU_2;
    		}else if(clientType == CmsConstant.CMS_CLINTE_TYPE_MOBILE) {
    			cmsPageId = CmsConstant.CMS_DEFAULT_PAGE_SU_1;
    		}
    	} else if(platformId == CmsConstant.CMS_PLATFORM_MYY) {
    		if(clientType == CmsConstant.CMS_CLINTE_TYPE_PC) {
    			cmsPageId = CmsConstant.CMS_DEFAULT_PAGE_SU_10;
    		}else if(clientType == CmsConstant.CMS_CLINTE_TYPE_MOBILE) {
    			cmsPageId = CmsConstant.CMS_DEFAULT_PAGE_SU_9;
    		}
    	}
    	
    	map.put("cmsPageId", cmsPageId);
    }

    public Map<Long, String> findCompanyByIdList(List<Long> ids){
        Map<Long, String> map = new HashMap<>();
        if(EmptyUtil.isNotEmpty(ids)) {
            List<CompanyDTO> companys = companyClient.findCompanyByCompanyIds(com.egeo.utils.StringUtils.longsToStrings(ids));
            for (CompanyDTO companyDTO : companys) {
                map.put(companyDTO.getId(), companyDTO.getCompanyName());
            }
        }
        return map;
    }
}
	