package com.egeo.components.product.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardProductUnitAttValueReadService;
import com.egeo.components.product.manage.read.StandardProductUnitAttValueReadManage;
import com.egeo.components.product.condition.StandardProductUnitAttValueCondition;
import com.egeo.components.product.converter.StandardProductUnitAttValueConverter;
import com.egeo.components.product.dto.StandardProductUnitAttValueDTO;
import com.egeo.components.product.po.StandardProductUnitAttValuePO;

import com.egeo.orm.PageResult;
import com.egeo.exception.BusinessException;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("standardProductUnitAttValueReadService")
public class StandardProductUnitAttValueReadServiceImpl  implements StandardProductUnitAttValueReadService {
	@Autowired
	private StandardProductUnitAttValueReadManage standardProductUnitAttValueReadManage;

	@Override
	public StandardProductUnitAttValueDTO findStandardProductUnitAttValueById(StandardProductUnitAttValueDTO dto) {
		StandardProductUnitAttValuePO po = StandardProductUnitAttValueConverter.toPO(dto);
		StandardProductUnitAttValuePO list = standardProductUnitAttValueReadManage.findStandardProductUnitAttValueById(po);		
		return StandardProductUnitAttValueConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardProductUnitAttValueDTO> findStandardProductUnitAttValueOfPage(StandardProductUnitAttValueDTO dto, Pagination page) {
		StandardProductUnitAttValuePO po = StandardProductUnitAttValueConverter.toPO(dto);
        PageResult<StandardProductUnitAttValuePO> pageResult = standardProductUnitAttValueReadManage.findStandardProductUnitAttValueOfPage(po, page);
        
        List<StandardProductUnitAttValueDTO> list = StandardProductUnitAttValueConverter.toDTO(pageResult.getList());
        PageResult<StandardProductUnitAttValueDTO> result = new PageResult<StandardProductUnitAttValueDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardProductUnitAttValueDTO> findStandardProductUnitAttValueAll(StandardProductUnitAttValueDTO dto) {
		StandardProductUnitAttValuePO po = StandardProductUnitAttValueConverter.toPO(dto);
		List<StandardProductUnitAttValuePO> list = standardProductUnitAttValueReadManage.findStandardProductUnitAttValueAll(po);		
		return StandardProductUnitAttValueConverter.toDTO(list);
	}
	/**
	 * 根据su属性id查询属性值信息
	 * @param id
	 * @return
	 */
	@Override
	public List<StandardProductUnitAttValueDTO> findByStandardProductUnitAttNameId(Long standardProductUnitAttNameId) {
		List<StandardProductUnitAttValueDTO> standardProductUnitAttValueDTOs = new ArrayList<>();
		List<StandardProductUnitAttValueCondition> list = standardProductUnitAttValueReadManage.findByStandardProductUnitAttNameId(standardProductUnitAttNameId);
		for (StandardProductUnitAttValueCondition standardProductUnitAttValueCondition : list) {
			StandardProductUnitAttValueDTO standardProductUnitAttValueDTO = StandardProductUnitAttValueConverter.toDTO(standardProductUnitAttValueCondition);
			standardProductUnitAttValueDTO.setAttValue(standardProductUnitAttValueCondition.getAttValue());
			standardProductUnitAttValueDTOs.add(standardProductUnitAttValueDTO);
		}
		return standardProductUnitAttValueDTOs;
	}
	/**
	 * 根据spuid查询spu关键词
	 */
	@Override
	public List<String> keyWordByStandardProductUnitId(Long standardProductUnitId, Long platformId) {
		if(EmptyUtil.isEmpty(standardProductUnitId))
			throw new BusinessException("spuid不能为空");
		if(EmptyUtil.isEmpty(platformId))
			throw new BusinessException("平台id不能为空");
		return standardProductUnitAttValueReadManage.keyWordByStandardProductUnitId(standardProductUnitId, platformId);
	}
	/**
	 * 根据skuid查询是否在app内使用
	 */
	@Override
	public boolean isAppUseBySkuId(Long skuId) {
		// TODO Auto-generated method stub
		return standardProductUnitAttValueReadManage.isAppUseBySkuId(skuId);
	}
	/**
	 * 根据skuid和属性id查询属性值Id
	 */
	@Override
	public Long findAttValueIdBySkuIdAndAttNameId(Long skuId,Long attNameId) {
		// TODO Auto-generated method stub
		return standardProductUnitAttValueReadManage.findAttValueIdBySkuIdAndAttNameId(skuId,attNameId);
	}

	@Override
	public int findThirdpartyAttBySpuId(Long id) {
		return standardProductUnitAttValueReadManage.findThirdpartyAttBySpuId(id);
	}
}
	