package com.egeo.components.product.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardProductUnitAttNameReadService;
import com.egeo.components.product.manage.read.StandardProductUnitAttNameReadManage;
import com.egeo.components.product.condition.StandardProductUnitAttNameCondition;
import com.egeo.components.product.converter.StandardProductUnitAttNameConverter;
import com.egeo.components.product.dto.StandardProductUnitAttNameDTO;
import com.egeo.components.product.po.StandardProductUnitAttNamePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardProductUnitAttNameReadService")
public class StandardProductUnitAttNameReadServiceImpl  implements StandardProductUnitAttNameReadService {
	@Autowired
	private StandardProductUnitAttNameReadManage standardProductUnitAttNameReadManage;

	@Override
	public StandardProductUnitAttNameDTO findStandardProductUnitAttNameById(StandardProductUnitAttNameDTO dto) {
		StandardProductUnitAttNamePO po = StandardProductUnitAttNameConverter.toPO(dto);
		StandardProductUnitAttNamePO list = standardProductUnitAttNameReadManage.findStandardProductUnitAttNameById(po);		
		return StandardProductUnitAttNameConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardProductUnitAttNameDTO> findStandardProductUnitAttNameOfPage(StandardProductUnitAttNameDTO dto, Pagination page) {
		StandardProductUnitAttNamePO po = StandardProductUnitAttNameConverter.toPO(dto);
        PageResult<StandardProductUnitAttNamePO> pageResult = standardProductUnitAttNameReadManage.findStandardProductUnitAttNameOfPage(po, page);
        
        List<StandardProductUnitAttNameDTO> list = StandardProductUnitAttNameConverter.toDTO(pageResult.getList());
        PageResult<StandardProductUnitAttNameDTO> result = new PageResult<StandardProductUnitAttNameDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardProductUnitAttNameDTO> findStandardProductUnitAttNameAll(StandardProductUnitAttNameDTO dto) {
		StandardProductUnitAttNamePO po = StandardProductUnitAttNameConverter.toPO(dto);
		List<StandardProductUnitAttNamePO> list = standardProductUnitAttNameReadManage.findStandardProductUnitAttNameAll(po);		
		return StandardProductUnitAttNameConverter.toDTO(list);
	}
	/**
	 * 根据suid查询su参数属性
	 * @param standardProductUnitId
	 * @return
	 */
	@Override
	public List<StandardProductUnitAttNameDTO> findByStandardProductUnitId(Long standardProductUnitId) {
		List<StandardProductUnitAttNameDTO> standardProductUnitAttNameDTOs = new ArrayList<>();
		List<StandardProductUnitAttNameCondition> list = standardProductUnitAttNameReadManage.findByStandardProductUnitId(standardProductUnitId);
		for (StandardProductUnitAttNameCondition standardProductUnitAttNameCondition : list) {
			StandardProductUnitAttNameDTO standardProductUnitAttNameDTO = StandardProductUnitAttNameConverter.toDTO(standardProductUnitAttNameCondition);
			standardProductUnitAttNameDTO.setAttName(standardProductUnitAttNameCondition.getAttName());
			standardProductUnitAttNameDTO.setMode(standardProductUnitAttNameCondition.getMode());
			standardProductUnitAttNameDTO.setUnit(standardProductUnitAttNameCondition.getUnit());
			standardProductUnitAttNameDTOs.add(standardProductUnitAttNameDTO);
		}
		return standardProductUnitAttNameDTOs;
	}
	/**
	 * 根据suid查询spu规格属性
	 * @param standardUnitId
	 * @param req
	 * @return
	 */
	@Override
	public List<StandardProductUnitAttNameDTO> findByStandardUnitId(Long standardUnitId) {
		List<StandardProductUnitAttNameDTO> standardProductUnitAttNameDTOs = new ArrayList<>();
		List<StandardProductUnitAttNameCondition> list = standardProductUnitAttNameReadManage.findByStandardUnitId(standardUnitId);
		for (StandardProductUnitAttNameCondition standardProductUnitAttNameCondition : list) {
			StandardProductUnitAttNameDTO standardProductUnitAttNameDTO = StandardProductUnitAttNameConverter.toDTO(standardProductUnitAttNameCondition);
			standardProductUnitAttNameDTO.setAttName(standardProductUnitAttNameCondition.getAttName());
			standardProductUnitAttNameDTO.setMode(standardProductUnitAttNameCondition.getMode());
			standardProductUnitAttNameDTO.setUnit(standardProductUnitAttNameCondition.getUnit());
			standardProductUnitAttNameDTOs.add(standardProductUnitAttNameDTO);
		}
		return standardProductUnitAttNameDTOs;
	}

	@Override
	public List<StandardProductUnitAttNameDTO> findByStandardProductUnitIdAttNameId(Long standardProductUnitId,
			Long attNameId) {
		List<StandardProductUnitAttNamePO> standardProductUnitAttNameList = standardProductUnitAttNameReadManage.findByStandardProductUnitIdAttNameId(standardProductUnitId,attNameId);
		return StandardProductUnitAttNameConverter.toDTO(standardProductUnitAttNameList);
	}
	/**
	 * 根据spuId查询spu属性信息
	 * @param standardProductUnitId
	 * @return
	 */
	@Override
	public List<StandardProductUnitAttNameDTO> findStandardProductUnitAttNameAll(Long standardProductUnitId) {
		List<StandardProductUnitAttNameDTO> standardProductUnitAttNameDTOs = new ArrayList<>();
		List<StandardProductUnitAttNameCondition> list = standardProductUnitAttNameReadManage.findStandardProductUnitAttNameAll(standardProductUnitId);		
		for (StandardProductUnitAttNameCondition standardProductUnitAttNameCondition : list) {
			StandardProductUnitAttNameDTO standardProductUnitAttNameDTO = StandardProductUnitAttNameConverter.toDTO(standardProductUnitAttNameCondition);
			standardProductUnitAttNameDTO.setAttName(standardProductUnitAttNameCondition.getAttName());
			standardProductUnitAttNameDTO.setMode(standardProductUnitAttNameCondition.getMode());
			standardProductUnitAttNameDTO.setUnit(standardProductUnitAttNameCondition.getUnit());
			standardProductUnitAttNameDTOs.add(standardProductUnitAttNameDTO);
		}
		return standardProductUnitAttNameDTOs;
	}

	@Override
	public Long findLastId() {
		return standardProductUnitAttNameReadManage.findLastId();
	}
}
	