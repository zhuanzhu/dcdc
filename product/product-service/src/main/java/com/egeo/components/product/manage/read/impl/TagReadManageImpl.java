package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.TagReadManage;
import com.egeo.components.product.dao.read.StandardUnitCombinationTagReadDAO;
import com.egeo.components.product.dao.read.TagReadDAO;
import com.egeo.components.product.po.StandardUnitCombinationTagPO;
import com.egeo.components.product.po.TagPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Service
public class TagReadManageImpl implements TagReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TagReadDAO tagReadDAO;
	
	@Autowired
	private StandardUnitCombinationTagReadDAO standardUnitCombinationTagReadDAO;
	
	public TagPO findTagById(TagPO po) {
		TagPO tagpo = new TagPO();
		tagpo.setId(po.getId());
		return tagReadDAO.findById(tagpo);
	}

	public PageResult<TagPO> findTagOfPage(TagPO po, Pagination page,List<Long> tagIds) {
		
		if(EmptyUtil.isNotEmpty(tagIds)){
			PageResult<TagPO> pageResult = new PageResult<TagPO>();
			List<TagPO> list = null;

			int cnt = tagReadDAO.countOfPageBystandardUnitCId(po,tagIds);

			if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
				page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
				list = tagReadDAO.findTagOfPageBystandardUnitCId(po, page,tagIds);
			} else {
				list = new ArrayList<TagPO>();
			}
			pageResult.setList(list);
			pageResult.setTotalSize(cnt);
			pageResult.setPageNo(page.getPageNo());
			pageResult.setPageSize(page.getPageSize());
			return pageResult;
		}
		PageResult<TagPO> pageResult = new PageResult<TagPO>();
		List<TagPO> list = null;

		int cnt = tagReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = tagReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<TagPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<TagPO> findTagAll(TagPO po) {

		return tagReadDAO.findAll(po,null);
	}
	/**
	 * 根据su商品id分页查询标签数据(暂时不用)
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@Override
	public PageResult<TagPO> findTagOfPageBystandardUnitCId(TagPO po, Pagination page, Long standardUnitCombinationId) {
		StandardUnitCombinationTagPO standardUnitCombinationTagPO = new StandardUnitCombinationTagPO();
		standardUnitCombinationTagPO.setStandardUnitCombinationId(standardUnitCombinationId);
		List<StandardUnitCombinationTagPO> standardUnitCombinationTagList = standardUnitCombinationTagReadDAO.findAll(standardUnitCombinationTagPO,null);
		if(EmptyUtil.isEmpty(standardUnitCombinationTagList)){
			//return findTagOfPage(po, page);
		}
		List<Long> tags = new ArrayList<>();
		for (StandardUnitCombinationTagPO standardUnitCombinationTagPO2 : standardUnitCombinationTagList) {
			tags.add(standardUnitCombinationTagPO2.getTagId());
		}
		PageResult<TagPO> pageResult = new PageResult<TagPO>();
		List<TagPO> list = null;

		int cnt = tagReadDAO.countOfPageBystandardUnitCId(po,tags);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = tagReadDAO.findTagOfPageBystandardUnitCId(po, page,tags);
		} else {
			list = new ArrayList<TagPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}
	
}
	