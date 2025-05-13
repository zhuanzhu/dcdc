package com.egeo.components.product.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.product.business.LeadingEndCategoryManage;
import com.egeo.components.product.facade.CardStampsAdministrationFacade;
import com.egeo.components.product.facade.LeadingEndCategoryFacade;
import com.egeo.components.product.dto.CardStampsAdministrationDTO;
import com.egeo.components.product.dto.LeadingEndCategoryDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("leadingEndCategory")
public class LeadingEndCategoryManageImpl implements LeadingEndCategoryManage{

	
	@Resource(name="leadingEndCategoryFacade")
	private LeadingEndCategoryFacade leadingEndCategoryFacade;
	
	@Resource(name="cardStampsAdministrationFacade")
	private CardStampsAdministrationFacade cardStampsAdministrationFacade;

	@Override
	public LeadingEndCategoryDTO findLeadingEndCategoryById(LeadingEndCategoryDTO dto) {
		return leadingEndCategoryFacade.findLeadingEndCategoryById(dto);
	}

	@Override
	public PageResult<LeadingEndCategoryDTO> findLeadingEndCategoryOfPage(LeadingEndCategoryDTO dto, Pagination page) {
		return leadingEndCategoryFacade.findLeadingEndCategoryOfPage(dto, page);
	}

	@Override
	public List<LeadingEndCategoryDTO> findLeadingEndCategoryAll(LeadingEndCategoryDTO dto) {
		return leadingEndCategoryFacade.findLeadingEndCategoryAll(dto);
	}

	@Override
	public Long insertLeadingEndCategoryWithTx(LeadingEndCategoryDTO dto) {
		return leadingEndCategoryFacade.insertLeadingEndCategoryWithTx(dto);
	}

	@Override
	public int updateLeadingEndCategoryWithTx(LeadingEndCategoryDTO dto) {
		return leadingEndCategoryFacade.updateLeadingEndCategoryWithTx(dto);
	}

	@Override
	public int deleteLeadingEndCategoryWithTx(LeadingEndCategoryDTO dto) {
		return leadingEndCategoryFacade.deleteLeadingEndCategoryWithTx(dto);
	}
	/**
	 * 查询所有只返回id和名称
	 * @param vo
	 * @param req
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findLeadingEndCategoryIdAndName(LeadingEndCategoryDTO dto) {
		List<Map<String, Object>> maps = new ArrayList<>();
		List<LeadingEndCategoryDTO> list = leadingEndCategoryFacade.findLeadingEndCategoryAll(dto);
		for (LeadingEndCategoryDTO leadingEndCategoryDTO : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("leadingEndCategoryId", leadingEndCategoryDTO.getId());
			map.put("leadingEndCategoryName", leadingEndCategoryDTO.getName());
			maps.add(map);
		}
		return maps;
	}
	/**
	 * 客户端分页显示类目及子模块
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@Override
	public PageResult<Map<String, Object>> leadingEndCategoryOfPageApp(LeadingEndCategoryDTO dto, Pagination page) {
		List<Map<String, Object>> list = new ArrayList<>();
		PageResult<LeadingEndCategoryDTO> pageResult = leadingEndCategoryFacade.findLeadingEndCategoryOfPage(dto, page);
		List<LeadingEndCategoryDTO> leadingEndCategoryList = pageResult.getList();
		for (LeadingEndCategoryDTO leadingEndCategoryDTO : leadingEndCategoryList) {
			Map<String, Object> map = new HashMap<>();
			map.put("leadingEndCategoryId", leadingEndCategoryDTO.getId());
			map.put("leadingEndCategoryName", leadingEndCategoryDTO.getName());
			//根据前端类目查询子模块信息
			CardStampsAdministrationDTO cardStampsAdministrationDTO = new CardStampsAdministrationDTO();
			cardStampsAdministrationDTO.setLeadingEndCategoryId(leadingEndCategoryDTO.getId());
			List<CardStampsAdministrationDTO> cardStampsAdministrationDTOList = cardStampsAdministrationFacade.findCardStampsAdministrationAll(cardStampsAdministrationDTO);
			List<Map<String, Object>> cardStampsAdministrationList = new ArrayList<>();
			for (CardStampsAdministrationDTO cardStampsAdministrationDTO2 : cardStampsAdministrationDTOList) {
				Map<String, Object> map2 = new HashMap<>();
				map2.put("cardStampsAdministrationId", cardStampsAdministrationDTO2.getId());
				map2.put("cardStampsAdministrationName", cardStampsAdministrationDTO2.getName());
				map2.put("pictureUrl", cardStampsAdministrationDTO2.getPictureUrl());
				map2.put("sortValue", cardStampsAdministrationDTO2.getSortValue());
				map2.put("isShow", cardStampsAdministrationDTO2.getIsShow());
				map2.put("standardUnitId", cardStampsAdministrationDTO2.getStandardUnitId());
				cardStampsAdministrationList.add(map2);
			}
			map.put("cardStampsAdministrationList", cardStampsAdministrationList);
			list.add(map);
		}
		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
		return result;
	}


}
	