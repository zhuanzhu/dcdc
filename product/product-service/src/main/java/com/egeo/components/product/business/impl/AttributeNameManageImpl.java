package com.egeo.components.product.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.egeo.common.BusinessConstant;
import com.egeo.common.BusinessExceptionConstant;
import com.egeo.components.product.business.AttributeNameManage;
import com.egeo.components.product.converter.AttributeNameConverter;
import com.egeo.components.product.dto.AttValueDTO;
import com.egeo.components.product.dto.AttributeNameDTO;
import com.egeo.components.product.dto.AttributeNameDecimalDTO;
import com.egeo.components.product.dto.AttributeValueDTO;
import com.egeo.components.product.dto.CategoryAttNameDTO;
import com.egeo.components.product.facade.AttributeNameDecimalFacade;
import com.egeo.components.product.facade.AttributeNameFacade;
import com.egeo.components.product.facade.AttributeValueFacade;
import com.egeo.components.product.facade.CategoryAttNameFacade;
import com.egeo.components.product.vo.AttributeNameVO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("attributeName")
public class AttributeNameManageImpl implements AttributeNameManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "attributeNameFacade")
	private AttributeNameFacade attributeNameFacade;

	@Resource(name = "categoryAttNameFacade")
	private CategoryAttNameFacade categoryAttNameFacade;

	@Resource(name = "attributeNameDecimalFacade")
	private AttributeNameDecimalFacade attributeNameDecimalFacade;

	@Resource(name = "attributeValueFacade")
	private AttributeValueFacade attributeValueFacade;

	@Override
	public AttributeNameVO findById(AttributeNameVO attributeNameVO) {
		AttributeNameVO vo = attributeNameFacade.findById(AttributeNameConverter.toDTO(attributeNameVO));
		//1是下拉单选框,2文本输入框，3列表复选框，4日期型、5整型、6数字型
		if (vo.getMode() == 4 || vo.getMode() == 5 || vo.getMode() == 6 || vo.getMode() == 2) {
			// 根据属性id查询属性扩展表信息
			AttributeNameDecimalDTO attributeNameDecimalDTO = new AttributeNameDecimalDTO();
			attributeNameDecimalDTO.setAttributeNameId(attributeNameVO.getId());
			List<AttributeNameDecimalDTO> list = attributeNameDecimalFacade
					.findAttributeNameDecimalAll(attributeNameDecimalDTO);
			if (EmptyUtil.isNotEmpty(list)) {
				vo.setBegin(list.get(0).getBeginDecimal().toString());
				vo.setFinish((list.get(0).getFinishDecimal().toString()));
				if (vo.getMode() == 6 || vo.getMode() == 5) {
					vo.setUnit(list.get(0).getUnit());
					vo.setImportHint(list.get(0).getImportHint());
				}

			}
		}
		return vo;
	}

	@Override
	public List<AttributeNameVO> findAll(AttributeNameVO attributeNameVO) {
		List<AttributeNameDTO> list = attributeNameFacade.findAll(AttributeNameConverter.toDTO(attributeNameVO));
		return AttributeNameConverter.toVO(list);
	}

	@Override
	public PageResult<Map<String, Object>> findPageAttributeName(Pagination page, AttributeNameVO attributeNameVO) {
		PageResult<AttributeNameDTO> rt = attributeNameFacade.findPageAttributeName(page, AttributeNameConverter.toDTO(attributeNameVO));
		List<AttributeNameDTO> attributeNameList = rt.getList();
		List<Map<String, Object>> list = new ArrayList<>(attributeNameList.size());
		for (AttributeNameDTO attributeNameDTO : attributeNameList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", attributeNameDTO.getId());
			map.put("name", attributeNameDTO.getName());
			list.add(map);
		}
		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		return result;
	}

	@Override
	public Long saveAttributeName(AttributeNameVO attributeNameVO, List<AttValueDTO> lists, String begin,
			String finish) {
		if (attValueRepeat(lists)) {
			throw new BusinessException(BusinessExceptionConstant.ATT_VALUE_EXIST, "属性值名称重复！");
		}
		return attributeNameFacade.saveAttributeName(AttributeNameConverter.toDTO(attributeNameVO), lists, begin,
				finish);
	}

	private boolean attValueRepeat(List<AttValueDTO> lists) {
		Set<String> nameSet = new HashSet<>();
		for (AttValueDTO ave : lists) {
			if (nameSet.contains(ave.getAttValue())) {

				logger.info("属性值名称重复:"+ave.getAttValue());
				return true;
			}
			nameSet.add(ave.getAttValue());
		}
		return false;
	}



	@Override
	public Long updateAttributeName(AttributeNameVO attributeNameVO, List<AttValueDTO> lists, String begin,
			String finish) {
		if (attributeNameVO.getId().equals(BusinessConstant.IS_E_CARD_ID)
				|| attributeNameVO.getId().equals(BusinessConstant.IS_UNIT_INVENTORY_ID)
				|| attributeNameVO.getId().equals(BusinessConstant.IS_APP_USE_ID)
				|| attributeNameVO.getId().equals(BusinessConstant.THIRDPARTY_BUTT_JOINT_PARAMETER)){
			throw new BusinessException("此属性为初始属性，不能编辑");
		}
		if (attValueRepeat(lists)) {
			throw new BusinessException(BusinessExceptionConstant.ATT_VALUE_EXIST, "属性值名称重复！");
		}
		return attributeNameFacade.updateAttributeName(AttributeNameConverter.toDTO(attributeNameVO), lists, begin,
					finish);
	}

	@Override
	public Long deleteById(AttributeNameVO attributeNameVO) {
		// 根据属性id查询是否有类目引用此属性，否则不能删除
		CategoryAttNameDTO categoryAttNameDTO = new CategoryAttNameDTO();
		categoryAttNameDTO.setAttNameId(attributeNameVO.getId());
		List<CategoryAttNameDTO> categoryAttNameList = categoryAttNameFacade.findAll(categoryAttNameDTO);
		if (categoryAttNameList.size() > 0) {
			throw new BusinessException("此属性以被类目引用，不能删除");
		}
		return attributeNameFacade.deleteById(AttributeNameConverter.toDTO(attributeNameVO));
	}

	@Override
	public List<AttributeNameDTO> attributeNameByCategoryId(Long categoryId, String name, Integer specificationProperty,
			Integer parameterProperty) {
		List<AttributeNameDTO> attributeNameDTOs = new ArrayList<AttributeNameDTO>();
		// 根据类目id查询类目属性
		CategoryAttNameDTO categoryAttNameDTO = new CategoryAttNameDTO();
		categoryAttNameDTO.setCategoryId(categoryId);
		List<CategoryAttNameDTO> list = categoryAttNameFacade.findAll(categoryAttNameDTO);
		StringBuffer ids = new StringBuffer();
		for (CategoryAttNameDTO categoryAttNameDTO2 : list) {
			ids.append(categoryAttNameDTO2.getAttNameId());
			ids.append(",");
		}
		if (ids.length() > 0) {
			attributeNameDTOs = attributeNameFacade.findByIds(ids.toString().substring(0, ids.toString().length() - 1),
					specificationProperty, parameterProperty, name);
		} else {
			AttributeNameDTO attributeNameDTO = new AttributeNameDTO();
			attributeNameDTO.setSpecificationProperty(specificationProperty);
			attributeNameDTO.setParameterProperty(parameterProperty);
			attributeNameDTO.setName(name);
			attributeNameDTOs = attributeNameFacade.findAll(attributeNameDTO);
		}
		return attributeNameDTOs;
	}

	@Override
	public List<AttributeNameDTO> findAllByName(Long categoryId, String name, Integer specificationProperty,
			Integer parameterProperty) {
		List<AttributeNameDTO> attributeNameList = new ArrayList<AttributeNameDTO>();

		AttributeNameDTO attributeNameDTO = new AttributeNameDTO();
		attributeNameDTO.setName(name);
		attributeNameDTO.setSpecificationProperty(specificationProperty);
		attributeNameDTO.setParameterProperty(parameterProperty);
		List<AttributeNameDTO> lists = attributeNameFacade.findAll(attributeNameDTO);
		if (lists.size() > 0) {
			for (AttributeNameDTO attributeNameDTO2 : lists) {
				// 根据属性id及类目id查询类目属性
				CategoryAttNameDTO categoryAttNameDTO = new CategoryAttNameDTO();
				categoryAttNameDTO.setCategoryId(categoryId);
				categoryAttNameDTO.setAttNameId(attributeNameDTO2.getId());
				List<CategoryAttNameDTO> list = categoryAttNameFacade.findAll(categoryAttNameDTO);
				if (list.size() > 0) {
					attributeNameDTO2.setId(list.get(0).getId());
				}
				attributeNameList.add(attributeNameDTO2);
			}
		}
		return attributeNameList;
	}

	/**
	 * 根据spu草稿id和类目id查询属性值信息
	 */
	@Override
	public List<AttributeNameDTO> attributeValueByProductIdAndCategoryId(Long productId, Long categoryId) {
		List<AttributeNameDTO> list = null;
		if (EmptyUtil.isEmpty(productId)) {
			// 根据类目id查询属性信息
			list = attributeNameFacade.findByCategoryId(categoryId);
		} else {
			// 根据spu草稿id查询属性和属性值信息
			list = attributeNameFacade.findByCategoryId(categoryId);
			for (AttributeNameDTO attributeNameDTO : list) {
				// 根据属性id查询属性值信息
				List<AttributeValueDTO> attributeValueList = attributeValueFacade
						.attributeValueByProductIdAndAttNameId(productId, attributeNameDTO.getId());

				// 根据属性id和sup草稿id查询spu属性值信息
				List<AttributeValueDTO> spuAttributeValueList = attributeValueFacade
						.spuAttributeValueByProductIdAndAttNameId(productId, attributeNameDTO.getId());
				for (AttributeValueDTO attributeValueDTO : attributeValueList) {
					for (AttributeValueDTO attributeValue : spuAttributeValueList) {
						if (attributeValueDTO.getId().equals(attributeValue.getId())) {
							attributeValueDTO.setChecked(true);
							break;
						}
					}
				}
				attributeNameDTO.setAttributeValueList(attributeValueList);
			}
		}
		return list;
	}

}
