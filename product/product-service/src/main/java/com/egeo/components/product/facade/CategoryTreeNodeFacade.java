package com.egeo.components.product.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.cms.client.LinkableButtonClient;
import com.egeo.components.cms.client.LinkableButtonPageClient;
import com.egeo.components.cms.client.LinkableParamClient;
import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.components.cms.dto.LinkableButtonPageDTO;
import com.egeo.components.cms.dto.LinkableParamDTO;
import com.egeo.components.product.dto.CategoryBannerDTO;
import com.egeo.components.product.dto.CategoryDTO;
import com.egeo.components.product.dto.CategoryTreeDTO;
import com.egeo.components.product.dto.CategoryTreeNodeDTO;
import com.egeo.components.product.service.read.CategoryBannerReadService;
import com.egeo.components.product.service.read.CategoryReadService;
import com.egeo.components.product.service.read.CategoryTreeNodeReadService;
import com.egeo.components.product.service.read.CategoryTreeReadService;
import com.egeo.components.product.service.read.ProductReadService;
import com.egeo.components.product.service.write.CategoryTreeNodeWriteService;
import com.egeo.core.Constant.CmsConstant;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Component
public class CategoryTreeNodeFacade {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryTreeNodeFacade.class);

    @Resource
    private CategoryTreeNodeReadService categoryTreeNodeReadService;

    @Resource
    private CategoryTreeNodeWriteService categoryTreeNodeWriteService;

    @Resource
    private CategoryReadService categoryReadService;

    @Resource
    private ProductReadService productReadService;

    @Resource
    private CategoryTreeReadService categoryTreeReadService;
    
    @Autowired
    private LinkableParamClient linkableParamReadService;
    @Autowired
    private LinkableParamClient linkableParamWriteService;
    
    @Autowired
    private LinkableButtonPageClient linkableButtonPageWriteService;
    @Autowired
    private LinkableButtonPageClient linkableButtonPageReadService;
    @Autowired
    private LinkableButtonClient linkableButtonWriteService;
    @Autowired
    private LinkableButtonClient linkableButtonReadService;
    @Resource
    private CategoryBannerReadService categoryBannerReadService;

    public List<Map<String, Object>> getCategoryTreeByCatId(Long categoryId, Long platformId) {
        List<CategoryTreeNodeDTO> categoryTreeNodeList = categoryTreeNodeReadService.getCategoryTreeByCatId(categoryId, platformId);
        List<Map<String, Object>> categoryTreeNodeDTOList = new ArrayList<Map<String, Object>>();
        //新增接口,参数可做后期条件过滤
        //List<Long> idList = productReadService.getProductIdsByCondition(new ProductDTO());

        Map<Long, CategoryTreeNodeDTO> checkMap = new HashMap<>();
        for(CategoryTreeNodeDTO categoryTreeNodeDTO2 : categoryTreeNodeList){
            checkMap.put(categoryTreeNodeDTO2.getId(),categoryTreeNodeDTO2);
        }
        List<Long> catTreeList=productReadService.findAllCategoryTreeNodeId();
        for (CategoryTreeNodeDTO categoryTreeNodeDTO2 : categoryTreeNodeList) {
            Map<String, Object> map = new HashMap<>();
            map.put("categoryTreeNodeId", categoryTreeNodeDTO2.getId());
            map.put("name", categoryTreeNodeDTO2.getName());
            map.put("parentId", categoryTreeNodeDTO2.getParentId());
            map.put("serialNumber", categoryTreeNodeDTO2.getSerialNumber());
            map.put("listSort", categoryTreeNodeDTO2.getListSort());
            map.put("categoryId", categoryTreeNodeDTO2.getCategoryId());
            map.put("children", null);

            //根据类目节点id查询spu草稿信息、有为true、没有为false
            /*ProductDTO productDTO =  new ProductDTO();
            productDTO.setCategoryTreeNodeId(categoryTreeNodeDTO2.getId());
            List<Long> idList = productReadService.getProductIdsByCondition(productDTO);
            if (idList.size() > 0 && EmptyUtil.isNotEmpty(idList)) {
                map.put("show", 0);
            } else {
                map.put("show", 1);
            }*/
            if(catTreeList.contains(categoryTreeNodeDTO2.getId())){
                map.put("show", 0);
            }else{
                map.put("show", 1);
            }





            //判断当前类目级数
            int level = 1;
            map.put("add",1);
           level = judgeCategoryLevel(categoryTreeNodeDTO2.getParentId(),level,checkMap);

            if (level >= 4 ) {
                map.put("add",0);
            }

            categoryTreeNodeDTOList.add(map);
        }
        return recursionOrganizationData(categoryTreeNodeDTOList);// 递归组织数  据;
    }

    public CategoryTreeNodeDTO CategoryTreeNodeByCategoryId(Long parentId) {
        return categoryTreeNodeReadService.CategoryTreeNodeByCategoryId(parentId);
    }
    private int judgeCategoryLevel(Long parentId, int level, Map<Long, CategoryTreeNodeDTO> checkMap) {
        if (parentId != 0) {
            level++;

            CategoryTreeNodeDTO categoryTreeNodeDTO = checkMap.get(parentId);
            if (EmptyUtil.isNotEmpty(categoryTreeNodeDTO)) {
                return judgeCategoryLevel(categoryTreeNodeDTO.getParentId(),level, checkMap);
            }
        }
        return level;
    }

    public String addCategoryTreeNodeWithTx(CategoryTreeNodeDTO dto, List<Long> tagIdList) {
        return categoryTreeNodeWriteService.addCategoryTreeNodeWithTx(dto, tagIdList);
    }

    public CategoryTreeNodeDTO findById(CategoryTreeNodeDTO dto) {
        return categoryTreeNodeReadService.findById(dto);
    }

    public List<CategoryTreeNodeDTO> findAll(CategoryTreeNodeDTO dto) {
        return categoryTreeNodeReadService.findAll(dto);
    }

    public CategoryTreeNodeDTO findCategoryTreeNodeInofByNodeId(Long ctnId) {
        return categoryTreeNodeReadService.findCategoryTreeNodeInofByNodeId(ctnId);
    }

    /**
     * 查询该表排序最大的一个值
     *
     * @return
     */
    public Integer listSortMax() {
        return categoryTreeNodeReadService.listSortMax();
    }

    public int updateCategoryTreeNodeWithTx(CategoryTreeNodeDTO categoryTreeNodeDTO) {
        return categoryTreeNodeWriteService.updateCategoryTreeNodeWithTx(categoryTreeNodeDTO);
    }

    /**
     * 根据id删除类目节点信息
     *
     * @param categoryTreeNodeDTO
     * @return
     */
    public int deleteByIdWithTx(CategoryTreeNodeDTO categoryTreeNodeDTO) {
        return categoryTreeNodeWriteService.deleteByIdWithTx(categoryTreeNodeDTO);
    }

    /**
     * 根据上级节点信息查询类目信息
     *
     * @param parentId
     * @return
     */
    public CategoryDTO categoryByPIdNode(Long parentId) {
        // TODO Auto-generated method stub
        return categoryReadService.categoryByPIdNode(parentId);
    }

    /**
     * 根据类目节点id查询子类目节点信息
     *
     * @param categoryTreeNodeId
     * @return
     */
    public List<CategoryTreeNodeDTO> findSeedCategoryTreeNodeAll(Long categoryTreeNodeId) {
        CategoryTreeNodeDTO categoryTreeNodeDTO = new CategoryTreeNodeDTO();
        categoryTreeNodeDTO.setParentId(categoryTreeNodeId);
        return categoryTreeNodeReadService.findCategoryTreeNodeAll(categoryTreeNodeDTO);
    }

    /**
     * 添加前台类目节点信息
     * @param dto
     * @return
     */
    public Long saveCategoryTreeNodeWithTx(CategoryTreeNodeDTO dto,
			String linkableButtonPageList,String extParam) {
    	
    	//保存linkbuttonpage
    	LinkableButtonDTO linkableButton = new LinkableButtonDTO();
		linkableButton.setLinkType(dto.getLinkType());
		Long linkableId = linkableButtonWriteService.insertLinkableButtonWithTx(linkableButton);
		
		dto.setLinkableId(linkableId);
        //保存跳转
    	if(dto.getLinkType() != null && dto.getLinkType() == CmsConstant.CMS_LINK_TYPE_SU_LIST) {
    		if(StringUtils.isNotBlank(linkableButtonPageList)) {
    			List<LinkableButtonPageDTO> list = JSON.parseArray(linkableButtonPageList, LinkableButtonPageDTO.class);
        		if(list != null && list.size() > 0) {
        			for (LinkableButtonPageDTO linkableButtonPageDTO : list) {
						if(linkableButtonPageDTO.getCmsPageId() != null) {
							linkableButtonPageDTO.setLinkableId(linkableId);
							linkableButtonPageWriteService.insertLinkableButtonPageWithTx(linkableButtonPageDTO);
						}
					}
        		}
        	}
    	}
    	//保存linkparam
    	saveLinkParam(linkableId,extParam);
    	
        return categoryTreeNodeWriteService.saveCategoryTreeNodeWithTx(dto);
    }
    
    
    public void saveLinkParam(Long linkId,String extParam) {
    	
		if(StringUtils.isNotBlank(extParam)) {
			JSONArray array = JSON.parseArray(extParam);
			if(array != null && array.size() > 0) {
				for(int i = 0 ; i<array.size() ; i++) {
					JSONObject object = array.getJSONObject(i);
					LinkableParamDTO dto = new LinkableParamDTO();
					dto.setLinkButtonId(linkId);
					dto.setName(object.getString("name"));
					dto.setValue(object.getString("value"));
					linkableParamWriteService.insertLinkableParamWithTx(dto);
				}
				
			}
		}
	}

    /**
     * 根据类目节点id修改前台类目节点信息
     * @param dto
     * @return
     */
    public boolean updateCategoryTreeNodeCategoryWithTx(CategoryTreeNodeDTO dto,String linkableButtonPageList,String extParam) {
    	
    	
    	CategoryTreeNodeDTO categoryTreeNodeDTO = categoryTreeNodeReadService.findById(dto);
    	if(categoryTreeNodeDTO == null) {
    		return false;
    	}
		//根据类目id查询类目信息
		CategoryDTO categoryDTO = categoryReadService.findCategoryById(categoryTreeNodeDTO.getCategoryId());
		if(categoryDTO !=null && categoryDTO.getCategoryBannerId() != null) {
			CategoryBannerDTO categoryBannerDTO = new CategoryBannerDTO();
			categoryBannerDTO.setId(categoryDTO.getCategoryBannerId());
			categoryBannerDTO = categoryBannerReadService.findCategoryBannerById(categoryBannerDTO);
			logger.info("categoryBannerDTO :{}",JSON.toJSONString(categoryBannerDTO));
			if(categoryBannerDTO != null && categoryBannerDTO.getLinkableId() != null) {
				LinkableParamDTO linkableParamDTO = new LinkableParamDTO();
				linkableParamDTO.setLinkButtonId(categoryBannerDTO.getLinkableId());
				List<LinkableParamDTO> list = linkableParamReadService.findLinkableParamAll(linkableParamDTO);
				logger.info("linkableParamDTO:{}",JSON.toJSONString(linkableParamDTO));
				if(list != null && list.size() > 0) {
					linkableParamWriteService.deleteByPara(linkableParamDTO);
				}
				
				//保存linkparam
		    	saveLinkParam(categoryBannerDTO.getLinkableId(),extParam);
		    	
		    	List<LinkableButtonPageDTO> listpage = linkableButtonPageReadService.findLinkableButtonPageAll(new LinkableButtonPageDTO(categoryBannerDTO.getLinkableId()));
		    	if(listpage != null && listpage.size() > 0) {
		    		linkableButtonPageWriteService.deleteLinkableButtonPageByLinkableIdWithTx(new LinkableButtonPageDTO(categoryBannerDTO.getLinkableId()));
		    	}
		    	 //保存跳转
		    	if(dto.getLinkType() != null && dto.getLinkType() == CmsConstant.CMS_LINK_TYPE_SU_LIST) {
		    		if(StringUtils.isNotBlank(linkableButtonPageList)) {
		    			listpage = JSON.parseArray(linkableButtonPageList, LinkableButtonPageDTO.class);
		        		if(listpage != null && listpage.size() > 0) {
		        			for (LinkableButtonPageDTO linkableButtonPageDTO : listpage) {
								if(linkableButtonPageDTO.getCmsPageId() != null) {
									linkableButtonPageDTO.setLinkableId(categoryBannerDTO.getLinkableId());
									linkableButtonPageWriteService.insertLinkableButtonPageWithTx(linkableButtonPageDTO);
								}
							}
		        		}
		        	}
		    		dto.setLinkableId(categoryBannerDTO.getLinkableId());
		    	}
		    	
			}else {
				
				//保存linkbuttonpage
		    	LinkableButtonDTO linkableButton = new LinkableButtonDTO();
				linkableButton.setLinkType(dto.getLinkType());
				Long linkableId = linkableButtonWriteService.insertLinkableButtonWithTx(linkableButton);
				
				dto.setLinkableId(linkableId);
		        //保存跳转
		    	if(dto.getLinkType() != null && dto.getLinkType() == CmsConstant.CMS_LINK_TYPE_SU_LIST) {
		    		if(StringUtils.isNotBlank(linkableButtonPageList)) {
		    			List<LinkableButtonPageDTO> list = JSON.parseArray(linkableButtonPageList, LinkableButtonPageDTO.class);
		        		if(list != null && list.size() > 0) {
		        			for (LinkableButtonPageDTO linkableButtonPageDTO : list) {
								if(linkableButtonPageDTO.getCmsPageId() != null) {
									linkableButtonPageDTO.setLinkableId(linkableButton.getId());
									linkableButtonPageWriteService.insertLinkableButtonPageWithTx(linkableButtonPageDTO);
								}
							}
		        		}
		        	}
		    	}
		    	//保存linkparam
		    	saveLinkParam(linkableId,extParam);
			}
			
		}
        
        return categoryTreeNodeWriteService.updateCategoryTreeNodeCategoryWithTx(dto);
    }

    /**
     * 根据前台类目节点id查询前台类目节点信息
     * @param categoryTreeNodeId
     * @return
     */
    public CategoryTreeNodeDTO findByCategoryTreeNodeId(Long categoryTreeNodeId) {
        // TODO Auto-generated method stub
        return categoryTreeNodeReadService.findByCategoryTreeNodeId(categoryTreeNodeId);
    }

    /**
     * 根据前台类目节点id删除前台类目节点及子节点信息
     * @param categoryTreeNodeId
     * @return
     */
    public boolean delByCategoryTreeNodeIdWithTx(Long categoryTreeNodeId) {
        // TODO Auto-generated method stub
        return categoryTreeNodeWriteService.delByCategoryTreeNodeIdWithTx(categoryTreeNodeId);
    }

    /**
     * 根据类目树id查询前台类目节点信息
     * @param categoryTreeId
     * @return
     */
    public List<Map<String, Object>> findByCategoryTreeId(Long categoryTreeId) {
        CategoryTreeNodeDTO categoryTreeNodeDTO = new CategoryTreeNodeDTO();
        categoryTreeNodeDTO.setCategoryTreeId(categoryTreeId);
        List<CategoryTreeNodeDTO> categoryTreeNodeList = categoryTreeNodeReadService.findCategoryTreeNodeAll(categoryTreeNodeDTO);
        List<Map<String, Object>> categoryTreeNodeDTOList = new ArrayList<Map<String, Object>>();
        for (CategoryTreeNodeDTO categoryTreeNodeDTO2 : categoryTreeNodeList) {
            categoryTreeNodeDTOList.add(createMap(categoryTreeNodeDTO2));
        }
        return recursionOrganizationData(categoryTreeNodeDTOList);// 递归组织数据
    }

    /**
     * 递归成树结构
     *
     * @param categoryTreeNodeDTOList
     * @return
     */
    private List<Map<String, Object>> recursionOrganizationData(List<Map<String, Object>> categoryTreeNodeDTOList) {
        //递归成树结构
        List<Map<String, Object>> sortList = new ArrayList<Map<String, Object>>();

        Map<Object, List<Map<String, Object>>> map = new HashMap<>();
        for (Map<String, Object> t : categoryTreeNodeDTOList) {

            if (!map.containsKey(t.get("parentId"))) {
                List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
                newList.add(t);
                map.put(t.get("parentId"), newList);
            } else {
                List<Map<String, Object>> oldList = map.get(t.get("parentId"));
                oldList.add(t);
            }
        }
        for (Map<String, Object> tree : categoryTreeNodeDTOList) {
            if(map.get(tree.get("categoryTreeNodeId")) != null){
                if (tree.get("children") ==  null) {
                    List<Map<String, Object>> mylistss = new ArrayList<Map<String, Object>>();
                    //mylistss.add((Map<String, Object>) map.get(tree.get("categoryTreeNodeId")));
                    mylistss.addAll(map.get(tree.get("categoryTreeNodeId")));
                    tree.put("children", mylistss);

                } else {
                    List<Map<String, Object>> object = (List<Map<String, Object>>) tree.get("children");
                    object.addAll(map.get(tree.get("categoryTreeNodeId")));
                    tree.put("children", object);
                }
            }
            //当类目下有子类目时，该类目的[编辑]按钮置灰。
            if(EmptyUtil.isNotEmpty(tree.get("show"))){
                int isShow = (int)tree.get("show");
                if (isShow == 1) {
                    if (tree.get("children") != null) {
                        tree.put("show" , 0);
                    }
                }
            }

            if (tree.get("parentId").equals(PlatformKeyConstant.PRODUCT_PLATFORMID)) {
                sortList.add(tree);
            }
        }

        /*for (Map<String, Object> tree : categoryTreeNodeDTOList) {
            for (Map<String, Object> t : categoryTreeNodeDTOList) {
                if (t.get("parentId").equals(tree.get("categoryTreeNodeId"))) {
                    if (tree.get("children") == null) {
                        List<Map<String, Object>> mylistss = new ArrayList<Map<String, Object>>();
                        mylistss.add(t);
                        tree.put("children", mylistss);
                    } else {
                        List<Map<String, Object>> object = (List<Map<String, Object>>) tree.get("children");
                        object.add(t);
                        tree.put("children", object);

                    }
                }
            }
            if (tree.get("parentId").equals(PlatformKeyConstant.PRODUCT_PLATFORMID)) {
                sortList.add(tree);
            }
        }*/
        return sortList;
    }

    /**
     * 根据类目树id查询类目树信息
     *
     * @param categoryTreeId
     * @return
     */
    public CategoryTreeDTO findCategoryTreeByCategoryTreeNodeId(Long categoryTreeId) {
        // TODO Auto-generated method stub
        return categoryTreeReadService.findByCategoryTreeId(categoryTreeId);
    }

    /**
     * 根据类目树id查询所有类目信息
     * @param categoryTreeId
     * @param platformId
     * @param page
     * @return
     */
    public PageResult<Map<String, Object>> findWebCategoryTreeByCategoryTreeId(Long categoryTreeId, Long platformId, Pagination page) {
        CategoryTreeNodeDTO categoryTreeNodeDTO = new CategoryTreeNodeDTO();
        categoryTreeNodeDTO.setCategoryTreeId(categoryTreeId);
        categoryTreeNodeDTO.setPlatformId(platformId);
        List<CategoryTreeNodeDTO> list = categoryTreeNodeReadService.findWebCategoryTreeByCategoryTreeId(categoryTreeNodeDTO);
        List<Map<String, Object>> categoryTreeNodeDTOList = new ArrayList<Map<String, Object>>();
        for (CategoryTreeNodeDTO categoryTreeNodeDTO2 : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("categoryTreeNodeId", categoryTreeNodeDTO2.getId());
            map.put("categoryTreeNodeName", categoryTreeNodeDTO2.getName());
            map.put("parentId", categoryTreeNodeDTO2.getParentId());
            map.put("children", null);
            categoryTreeNodeDTOList.add(map);
        }
        List<Map<String, Object>> data = recursionOrganizationData(categoryTreeNodeDTOList);// 递归组织数据

        List<Map<String, Object>> maps = new ArrayList<>();
        if (data.size() >= (page.getPageNo() - 1) * page.getPageSize()) {
            if (data.size() >= page.getPageNo() * page.getPageSize()) {
                maps = data.subList((page.getPageNo() - 1) * page.getPageSize(), (page.getPageNo() * page.getPageSize()));
            } else {
                maps = data.subList((page.getPageNo() - 1) * page.getPageSize(), data.size());
            }

        } else {
            maps = new ArrayList<Map<String, Object>>();
        }
        PageResult<Map<String, Object>> result = new PageResult<>();
        result.setList(maps);
        result.setPageNo(page.getPageNo());
        result.setPageSize(page.getPageSize());
        result.setTotalSize(data.size());
        return result;
    }


    public List<Map<String, Object>> queryAllCategoryTreeNodeListVoByType(Long categoryTreeId, Long platformId) {
        List<CategoryTreeNodeDTO> categoryTreeNodeList = categoryTreeNodeReadService.getCategoryTreeByCatId(categoryTreeId, platformId);
        List<Map<String, Object>> categoryTreeNodeDTOList = new ArrayList<Map<String, Object>>();
        for (CategoryTreeNodeDTO categoryTreeNodeDTO2 : categoryTreeNodeList) {
            categoryTreeNodeDTOList.add(createMap(categoryTreeNodeDTO2));
        }
        return recursionOrganizationData(categoryTreeNodeDTOList);// 递归组织数据
    }

    private Map<String, Object> createMap(CategoryTreeNodeDTO categoryTreeNodeDTO2) {
        Map<String, Object> map = new HashMap<>();
        map.put("categoryTreeNodeId", categoryTreeNodeDTO2.getId());
        map.put("categoryTreeNodeName", categoryTreeNodeDTO2.getName());
        map.put("parentId", categoryTreeNodeDTO2.getParentId());
        map.put("listSort", categoryTreeNodeDTO2.getListSort());
        map.put("description", categoryTreeNodeDTO2.getDescription());
        map.put("categoryId", categoryTreeNodeDTO2.getCategoryId());
        map.put("pids", categoryTreeNodeDTO2.getPids());
        map.put("children", null);
        return map;
    }
    
    /**
     * 查找子类目中的最大序列号
     * @param categoryTreeId
     * @param parentId
     * @return
     */
    public String findMaxSubSerialNumber(Long categoryTreeId, Long parentId) {
    	return categoryTreeNodeReadService.findMaxSubSerialNumber(categoryTreeId, parentId);
    }

	public List<LinkableParamDTO> findLinkParamByLinkableId(Long linkableId) {
		LinkableParamDTO dto = new LinkableParamDTO();
		dto.setLinkButtonId(linkableId);
		
		return linkableParamReadService.findLinkableParamAll(dto);
	}

	public List<LinkableButtonPageDTO>  queryLinkableButtonPagByLinkableId(Long linkableId) {
		LinkableButtonPageDTO dto = new LinkableButtonPageDTO();
		dto.setLinkableId(linkableId);
		
		return linkableButtonPageReadService.findLinkableButtonPageAll(dto);
	}


}
	