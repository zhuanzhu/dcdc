package com.egeo.components.product.business.impl;
	

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.egeo.components.product.dto.*;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.TagManage;
import com.egeo.components.product.facade.TagFacade;
import com.egeo.orm.PageResult;
import com.egeo.exception.BusinessException;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("tag")
public class TagManageImpl implements TagManage{

	
	@Resource(name="tagFacade")
	private TagFacade tagFacade;
	/**
	 * 根据标签id查询标签信息
	 * @param tagId
	 * @return
	 */
	@Override
	public Map<String, Object> findTagById(Long tagId) {
		return tagFacade.findTagById(tagId);
	}

	@Override
	public PageResult<Map<String, Object>> findTagOfPage(TagDTO dto, Pagination page,List<Long> tagIds) {
		return tagFacade.findTagOfPage(dto, page,tagIds);
	}

	@Override
	public List<TagDTO> findTagAll(TagDTO dto) {
		return tagFacade.findTagAll(dto);
	}

	@Override
	public Long insertTagWithTx(TagDTO dto) {
		if(EmptyUtil.isEmpty(dto.getName())){
			throw new BusinessException("请填写标签名称");
		}
		if(dto.getName().length() > 6){
			throw new BusinessException("标签名称最多不超过6个字");
		}
		if(EmptyUtil.isEmpty(dto.getType())){
			throw new BusinessException("请选择标签类型");
		}
		TagDTO tagDTO = new TagDTO();
		tagDTO.setType(dto.getType());
		tagDTO.setName(dto.getName());
		tagDTO.setPlatformId(dto.getPlatformId());
		List<TagDTO> list = tagFacade.findTagAll(tagDTO);
		if(EmptyUtil.isNotEmpty(list)){
			throw new BusinessException("标签名称已存在，请重新输入");
		}
		return tagFacade.insertTagWithTx(dto);
	}

	@Override
	public int updateTagWithTx(TagDTO dto) {
		if(EmptyUtil.isEmpty(dto.getName())){
			throw new BusinessException("请填写标签名称");
		}
		if(dto.getName().length() > 6){
			throw new BusinessException("标签名称最多不超过6个字");
		}
		if(EmptyUtil.isEmpty(dto.getType())){
			throw new BusinessException("请选择标签类型");
		}
		TagDTO tagDTO = new TagDTO();
		tagDTO.setType(dto.getType());
		tagDTO.setName(dto.getName());
		tagDTO.setPlatformId(dto.getPlatformId());
		List<TagDTO> list = tagFacade.findTagAll(tagDTO);
		if(EmptyUtil.isNotEmpty(list)){
			if(!list.get(0).getId().equals(dto.getId())){
				throw new BusinessException("标签名称已存在，请重新输入");
			}
		}
		return tagFacade.updateTagWithTx(dto);
	}

	@Override
	public int deleteTagWithTx(TagDTO dto) {
		return tagFacade.deleteTagWithTx(dto);
	}

	/**
	 * 根据标签id启用停用标签
	 * @param tagId
	 * @param isValid
	 * @return
	 */
	@Override
	public int updateTagTypeByIdWithTx(Long tagId, Integer isValid) {
		// TODO Auto-generated method stub
		return tagFacade.updateTagTypeByIdWithTx(tagId, isValid);
	}

	/**
	 * 根据su商品id分页查询标签数据
	 * @param dto
	 * @param page
	 * @param standardUnitCombinationId
	 * @return
	 */
	@Override
	public PageResult<Map<String, Object>> findTagOfPageBystandardUnitCId(TagDTO dto, Pagination page, Long standardUnitCombinationId) {
		// TODO Auto-generated method stub
		return tagFacade.findTagOfPageBystandardUnitCId(dto, page, standardUnitCombinationId);
	}
	/**
	 * 根据标签id清除记录
	 * @param tagId
	 * @return
	 */
	@Override
	public Boolean tagClearRecordByTagId(Long tagId) {
		// TODO Auto-generated method stub
		return tagFacade.tagClearRecordByTagId(tagId);
	}

	@Override
	public PageResult<StandardUnitDTO> querySuInfoByTagId(List<Long> tagIds, Pagination page, Map<String, Object> reqParam) {
		List<Long> mpIds = new ArrayList<>();
		//根据tagId 查询出所有关联商品的suIds
		List<StandardUnitTagDTO> mpTagList = tagFacade.findMerchantProductTagAll(tagIds);
		if (!EmptyUtil.isEmpty(mpTagList)) {
			for (StandardUnitTagDTO mpTag:mpTagList) {
				mpIds.add(mpTag.getStandardUnitId());
			}
			if (EmptyUtil.isEmpty(mpIds)) {
				return null;
			}
			reqParam.put("suIds", mpIds);
			//根据是商品ids 查询列表
			return tagFacade.findCommodityDetailsOfPage(reqParam, page);
		}
		return null;
	}

	@Override
	public Boolean delMpTagInfo(Long standardUnitId) {
		int i = tagFacade.delMpTagInfo(standardUnitId);
		return i > 0;
	}
}
	