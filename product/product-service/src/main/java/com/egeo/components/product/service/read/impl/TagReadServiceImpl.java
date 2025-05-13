package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.TagReadService;
import com.egeo.components.product.manage.read.TagReadManage;
import com.egeo.components.product.converter.TagConverter;
import com.egeo.components.product.dto.TagDTO;
import com.egeo.components.product.po.TagPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("tagReadService")
public class TagReadServiceImpl  implements TagReadService {
	@Autowired
	private TagReadManage tagReadManage;

	@Override
	public TagDTO findTagById(TagDTO dto) {
		TagPO po = TagConverter.toPO(dto);
		TagPO list = tagReadManage.findTagById(po);		
		return TagConverter.toDTO(list);
	}

	@Override
	public PageResult<TagDTO> findTagOfPage(TagDTO dto, Pagination page,List<Long> tagIds) {
		TagPO po = TagConverter.toPO(dto);
        PageResult<TagPO> pageResult = tagReadManage.findTagOfPage(po, page,tagIds);
        
        List<TagDTO> list = TagConverter.toDTO(pageResult.getList());
        PageResult<TagDTO> result = new PageResult<TagDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<TagDTO> findTagAll(TagDTO dto) {
		TagPO po = TagConverter.toPO(dto);
		List<TagPO> list = tagReadManage.findTagAll(po);		
		return TagConverter.toDTO(list);
	}
	/**
	 * 根据su商品id分页查询标签数据
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@Override
	public PageResult<TagDTO> findTagOfPageBystandardUnitCId(TagDTO dto, Pagination page,
			Long standardUnitCombinationId) {
		TagPO po = TagConverter.toPO(dto);
        PageResult<TagPO> pageResult = tagReadManage.findTagOfPageBystandardUnitCId(po, page,standardUnitCombinationId);
        
        List<TagDTO> list = TagConverter.toDTO(pageResult.getList());
        PageResult<TagDTO> result = new PageResult<TagDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result; 
	}
}
	