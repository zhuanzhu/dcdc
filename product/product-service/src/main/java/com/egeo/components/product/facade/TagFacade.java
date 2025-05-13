package com.egeo.components.product.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.egeo.components.product.service.read.*;
import com.egeo.components.product.dto.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.write.StandardUnitCombinationTagWriteService;
import com.egeo.components.product.service.write.StandardUnitTagWriteService;
import com.egeo.components.product.service.write.TagWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class TagFacade {

    @Resource
    private TagReadService tagReadService;

    @Resource
    private TagWriteService tagWriteService;

    @Resource
    private StandardUnitTagWriteService standardUnitTagWriteService;

    @Resource
    private StandardUnitCombinationTagWriteService standardUnitCombinationTagWriteService;

    @Resource
    private StandardUnitTagReadService standardUnitTagReadService;

    @Resource
    private StandardUnitReadService standardUnitReadService;

    /**
     * 根据标签id查询标签信息
     *
     * @param tagId
     * @return
     */
    public Map<String, Object> findTagById(Long tagId) {
        Map<String, Object> map = new HashMap<>();
        TagDTO dto = new TagDTO();
        dto.setId(tagId);
        TagDTO tagDTO = tagReadService.findTagById(dto);
        map.put("id", tagDTO.getId());
        map.put("name", tagDTO.getName());
        map.put("type", tagDTO.getType());
        return map;
    }

    public PageResult<Map<String, Object>> findTagOfPage(TagDTO dto, Pagination page, List<Long> tagIds) {
        List<Map<String, Object>> list = new ArrayList<>();
        PageResult<TagDTO> pageResult = tagReadService.findTagOfPage(dto, page, tagIds);
        List<TagDTO> tagList = pageResult.getList();
        for (TagDTO tagDTO : tagList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", tagDTO.getId());
            map.put("name", tagDTO.getName());
            map.put("type", tagDTO.getType());
            map.put("isValid", tagDTO.getIsValid());
            list.add(map);
        }
        PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;

    }

    public List<TagDTO> findTagAll(TagDTO dto) {

        return tagReadService.findTagAll(dto);

    }

    public Long insertTagWithTx(TagDTO dto) {

        return tagWriteService.insertTagWithTx(dto);
    }

    public int updateTagWithTx(TagDTO dto) {

        return tagWriteService.updateTagWithTx(dto);
    }

    public int deleteTagWithTx(TagDTO dto) {

        return tagWriteService.deleteTagWithTx(dto);

    }

    /**
     * 根据标签id启用停用标签
     * @param tagId
     * @param isValid
     * @return
     */
    public int updateTagTypeByIdWithTx(Long tagId, Integer isValid) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(tagId);
        tagDTO.setIsValid(isValid);
        return tagWriteService.updateTagWithTx(tagDTO);
    }

    /**
     * 根据su商品id分页查询标签数据
     * @param dto
     * @param page
     * @param standardUnitCombinationId
     * @return
     */
    public PageResult<Map<String, Object>> findTagOfPageBystandardUnitCId(TagDTO dto, Pagination page, Long standardUnitCombinationId) {
        List<Map<String, Object>> list = new ArrayList<>();
        PageResult<TagDTO> pageResult = tagReadService.findTagOfPageBystandardUnitCId(dto, page, standardUnitCombinationId);
        List<TagDTO> tagList = pageResult.getList();
        for (TagDTO tagDTO : tagList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", tagDTO.getId());
            map.put("name", tagDTO.getName());
            map.put("type", tagDTO.getType());
            map.put("isValid", tagDTO.getIsValid());
            list.add(map);
        }
        PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    /**
     * 根据标签id清除记录
     *
     * @param tagId
     * @return
     */
    public Boolean tagClearRecordByTagId(Long tagId) {
        return tagWriteService.tagClearRecordByTagIdWithTx(tagId);
    }

    /**
     * 根据标签tagId查询商品标签列表
     * @param tagIds
     * @return
     */
    public List<StandardUnitTagDTO> findMerchantProductTagAll(List<Long> tagIds) {
        StandardUnitTagDTO dto = new StandardUnitTagDTO();
        dto.setTagIds(tagIds);
        return standardUnitTagReadService.findStandardUnitTagAll(dto);
    }

    /**
     * 分页查询商品详情
     * @param param
     * @param page
     * @return
     */
    public PageResult<StandardUnitDTO> findCommodityDetailsOfPage(Map<String, Object> param, Pagination page) {
        return standardUnitReadService.querySuByCategoryTreeNodeIds(page, param);
    }

    public int delMpTagInfo(Long standardUnitId) {
        return standardUnitTagWriteService.delSuRelationBysuIdWithTx(standardUnitId);
    }
}
	