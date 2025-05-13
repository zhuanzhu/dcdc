package com.egeo.components.product.business.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardUnitCombinationManage;
import com.egeo.components.product.facade.StandardUnitCombinationFacade;
import com.egeo.components.product.dto.StandardUnitCombinationCategoryDTO;
import com.egeo.components.product.dto.StandardUnitCombinationDTO;
import com.egeo.components.product.dto.StandardUnitCombinationTagDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitCombination")
public class StandardUnitCombinationManageImpl implements StandardUnitCombinationManage {


    @Resource(name = "standardUnitCombinationFacade")
    private StandardUnitCombinationFacade standardUnitCombinationFacade;

    @Override
    public Map<String, Object> findStandardUnitCombinationById(StandardUnitCombinationDTO dto) {
        Map<String, Object> map = new HashMap<>();
        StandardUnitCombinationDTO standardUnitCombinationDTO = standardUnitCombinationFacade.findStandardUnitCombinationById(dto);
        map.put("standardUnitCombinationId", standardUnitCombinationDTO.getId());
        map.put("combinationName", standardUnitCombinationDTO.getCombinationName());
        map.put("type", standardUnitCombinationDTO.getType());
        map.put("categoryTreeId", standardUnitCombinationDTO.getCategoryTreeId());
        map.put("sortType", standardUnitCombinationDTO.getSortType());
        map.put("combinationMinProfit", standardUnitCombinationDTO.getCombinationMinProfit());
        map.put("categoryTreeNodeIdList", null);
        map.put("tagidList", null);
        if (standardUnitCombinationDTO.getType() == 2) {
            List<Long> categoryTreeNodeIdList = new ArrayList<>();
            // 根据su组合id查询关联前台类目节点信息
            List<StandardUnitCombinationCategoryDTO> standardUnitCombinationCategoryDTOs = standardUnitCombinationFacade.categoryTreeNodeByStandardUnitCombinationId(standardUnitCombinationDTO.getId());
            for (StandardUnitCombinationCategoryDTO standardUnitCombinationCategoryDTO : standardUnitCombinationCategoryDTOs) {
                categoryTreeNodeIdList.add(standardUnitCombinationCategoryDTO.getCategoryTreeNodeId());
            }
            map.put("categoryTreeNodeIdList", categoryTreeNodeIdList);
        }
        if (standardUnitCombinationDTO.getType() == 3) {
            List<Long> tagidList = new ArrayList<>();
            // 根据su组合id查询关联标签信息
            List<StandardUnitCombinationTagDTO> standardUnitCombinationTags = standardUnitCombinationFacade.tagByStandardUnitCombinationId(standardUnitCombinationDTO.getId());
            for (StandardUnitCombinationTagDTO standardUnitCombinationTagDTO : standardUnitCombinationTags) {
                tagidList.add(standardUnitCombinationTagDTO.getTagId());
            }
            map.put("tagidList", tagidList);
        }
        if (standardUnitCombinationDTO.getType() == 4) {
            List<Long> categoryTreeNodeIdList = new ArrayList<>();
            // 根据su组合id查询关联后台类目节点信息，关联表type =1 ,则是关联后台类目树信息
            List<StandardUnitCombinationCategoryDTO> standardUnitCombinationCategoryDTOs =
                    standardUnitCombinationFacade.queryCategoryTreeNodeByStandardUnitCombinationId(standardUnitCombinationDTO.getId(), 1);
            for (StandardUnitCombinationCategoryDTO standardUnitCombinationCategoryDTO : standardUnitCombinationCategoryDTOs) {
                categoryTreeNodeIdList.add(standardUnitCombinationCategoryDTO.getCategoryTreeNodeId());
            }
            map.put("categoryTreeNodeIdList", categoryTreeNodeIdList);
        }
        return map;
    }

    @Override
    public PageResult<Map<String, Object>> findStandardUnitCombinationOfPage(StandardUnitCombinationDTO dto, Pagination page, List<Long> standardUnitCombinationIdList) {
        List<Map<String, Object>> list = new ArrayList<>();
        PageResult<StandardUnitCombinationDTO> pageResult = standardUnitCombinationFacade.findStandardUnitCombinationOfPage(dto, page, standardUnitCombinationIdList);
        List<StandardUnitCombinationDTO> standardUnitCombinationList = pageResult.getList();
        for (StandardUnitCombinationDTO standardUnitCombinationDTO : standardUnitCombinationList) {
            Map<String, Object> map = new HashMap<>();
            map.put("standardUnitCombinationId", standardUnitCombinationDTO.getId());
            map.put("combinationName", standardUnitCombinationDTO.getCombinationName());
            map.put("type", standardUnitCombinationDTO.getType());
            map.put("combinationMinProfit", standardUnitCombinationDTO.getCombinationMinProfit());
            Integer standardUnitSize = 0;
            if (standardUnitCombinationDTO.getType() == 1) {
                //根据su商品类型su组合id查询su商品数量
                standardUnitSize = standardUnitCombinationFacade.findStandardUnitSizeBySUType(standardUnitCombinationDTO.getId());
            } else if (standardUnitCombinationDTO.getType() == 2) {
                //根据类目类型su组合id查询su商品数量
                standardUnitSize = standardUnitCombinationFacade.findStandardUnitSizeByCategoryType(standardUnitCombinationDTO.getId());
            } else if (standardUnitCombinationDTO.getType() == 3) {
                //根据标签类型su组合id查询su商品数量
                standardUnitSize = standardUnitCombinationFacade.findStandardUnitSizeByTag(standardUnitCombinationDTO.getId(), dto.getPlatformId());
            }else if (standardUnitCombinationDTO.getType() == 6) {
                //根据标签类型su组合id查询su商品数量
                standardUnitSize = standardUnitCombinationFacade.findStandardUnitSizeBySUType(standardUnitCombinationDTO.getId());
            }else if (standardUnitCombinationDTO.getType() == 10) {
                //根据标签类型su组合id查询su商品数量
                standardUnitSize = standardUnitCombinationFacade.findStandardUnitSizeBySUType(standardUnitCombinationDTO.getId());
            }

            map.put("standardUnitSize", standardUnitSize);
            map.put("updateUsername", standardUnitCombinationDTO.getUpdateUsername());
            map.put("updateTime", standardUnitCombinationDTO.getUpdateTime());
            list.add(map);
        }
        PageResult<Map<String, Object>> result = new PageResult<>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public List<StandardUnitCombinationDTO> findStandardUnitCombinationAll(StandardUnitCombinationDTO dto) {
        return standardUnitCombinationFacade.findStandardUnitCombinationAll(dto);
    }

    @Override
    public Long insertStandardUnitCombinationWithTx(StandardUnitCombinationDTO dto) {
        return standardUnitCombinationFacade.insertStandardUnitCombinationWithTx(dto);
    }

    @Override
    public int updateStandardUnitCombinationWithTx(StandardUnitCombinationDTO dto) {
        return standardUnitCombinationFacade.updateStandardUnitCombinationWithTx(dto);
    }

    @Override
    public int deleteStandardUnitCombinationWithTx(StandardUnitCombinationDTO dto) {
        return standardUnitCombinationFacade.deleteStandardUnitCombinationWithTx(dto);
    }

    /**
     * 查询所有su分组信息
     *
     * @param dto
     * @return
     */
    @Override
    public List<Map<String, Object>> queryStandardUnitCombinationAll(StandardUnitCombinationDTO dto) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<StandardUnitCombinationDTO> standardUnitCombinationList = standardUnitCombinationFacade.findStandardUnitCombinationAll(dto);
        for (StandardUnitCombinationDTO standardUnitCombinationDTO : standardUnitCombinationList) {
            Map<String, Object> map = new HashMap<>();
            map.put("standardUnitCombinationId", standardUnitCombinationDTO.getId());
            map.put("combinationName", standardUnitCombinationDTO.getCombinationName());
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> findStandardUnitCombinationAllByBlurry(StandardUnitCombinationDTO dto) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<StandardUnitCombinationDTO> standardUnitCombinationList = standardUnitCombinationFacade.findStandardUnitCombinationAllByBlurry(dto);
        for (StandardUnitCombinationDTO standardUnitCombinationDTO : standardUnitCombinationList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", standardUnitCombinationDTO.getId());
            map.put("name", standardUnitCombinationDTO.getCombinationName());
            list.add(map);
        }
        return list;
    }

    @Override
    public PageResult<Map<String, Object>> findSucTitleOfPage(StandardUnitCombinationDTO dto, Pagination page) {
        PageResult<Map<String, Object>> result = new PageResult<>();
        List<Map<String, Object>> list = new ArrayList<>();
        PageResult<StandardUnitCombinationDTO> pageResult = standardUnitCombinationFacade.
                findStandardUnitCombinationOfPage(dto, page, null);
        List<StandardUnitCombinationDTO> standardUnitCombinationList = pageResult.getList();
        for (StandardUnitCombinationDTO standardUnitCombinationDTO : standardUnitCombinationList) {
            Map<String, Object> map = new HashMap<>();
            map.put("standardUnitCombinationId", standardUnitCombinationDTO.getId());
            map.put("combinationName", standardUnitCombinationDTO.getCombinationName());
            map.put("type", standardUnitCombinationDTO.getType());
            list.add(map);
        }
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

}
