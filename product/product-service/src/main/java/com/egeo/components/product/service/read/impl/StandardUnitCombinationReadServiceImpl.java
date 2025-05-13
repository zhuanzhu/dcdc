package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardUnitCombinationReadService;
import com.egeo.components.product.manage.read.StandardUnitCombinationReadManage;
import com.egeo.components.product.converter.StandardUnitCombinationConverter;
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.components.product.dto.StandardUnitCombinationDTO;
import com.egeo.components.product.po.StandardUnitCombinationPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitCombinationReadService")
public class StandardUnitCombinationReadServiceImpl  implements StandardUnitCombinationReadService {
	@Autowired
	private StandardUnitCombinationReadManage standardUnitCombinationReadManage;

	@Override
	public StandardUnitCombinationDTO findStandardUnitCombinationById(StandardUnitCombinationDTO dto) {
		StandardUnitCombinationPO po = StandardUnitCombinationConverter.toPO(dto);
		StandardUnitCombinationPO list = standardUnitCombinationReadManage.findStandardUnitCombinationById(po);
		return StandardUnitCombinationConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardUnitCombinationDTO> findStandardUnitCombinationOfPage(StandardUnitCombinationDTO dto,
			Pagination page, List<Long> standardUnitCombinationIdList) {
		StandardUnitCombinationPO po = StandardUnitCombinationConverter.toPO(dto);
		PageResult<StandardUnitCombinationPO> pageResult = standardUnitCombinationReadManage
				.findStandardUnitCombinationOfPage(po, page, standardUnitCombinationIdList);

		List<StandardUnitCombinationDTO> list = StandardUnitCombinationConverter.toDTO(pageResult.getList());
		PageResult<StandardUnitCombinationDTO> result = new PageResult<StandardUnitCombinationDTO>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	@Override
	public List<StandardUnitCombinationDTO> findStandardUnitCombinationAll(StandardUnitCombinationDTO dto) {
		StandardUnitCombinationPO po = StandardUnitCombinationConverter.toPO(dto);
		List<StandardUnitCombinationPO> list = standardUnitCombinationReadManage.findStandardUnitCombinationAll(po);
		return StandardUnitCombinationConverter.toDTO(list);
	}

	/**
	 * 根据标签类型su组合id查询su商品数量
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Integer findStandardUnitSizeByTag(Long standardUnitCombinationId, Long platformId) {
		// TODO Auto-generated method stub
		return standardUnitCombinationReadManage.findStandardUnitSizeByTag(standardUnitCombinationId, platformId);
	}

	@Override
	public List<StandardUnitCombinationDTO> findStandardUnitCombinationAllByBlurry(StandardUnitCombinationDTO dto) {
		List<StandardUnitCombinationPO> list = standardUnitCombinationReadManage
				.findStandardUnitCombinationAllByBlurry(StandardUnitCombinationConverter.toPO(dto));
		return StandardUnitCombinationConverter.toDTO(list);
	}

	@Override
	public List<StandardUnitCombinationDTO> findStandardUnitCombinationAllLimit(String suCombinationName) {
		List<StandardUnitCombinationPO> list = standardUnitCombinationReadManage.findStandardUnitCombinationAllLimit(suCombinationName);
		return StandardUnitCombinationConverter.toDTO(list);
	}

}
