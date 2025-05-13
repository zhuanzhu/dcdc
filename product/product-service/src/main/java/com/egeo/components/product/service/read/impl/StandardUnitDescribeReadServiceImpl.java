package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardUnitDescribeReadService;
import com.egeo.components.product.manage.read.StandardUnitDescribeReadManage;
import com.egeo.components.product.converter.StandardUnitDescribeConverter;
import com.egeo.components.product.dto.StandardUnitDescribeDTO;
import com.egeo.components.product.po.StandardUnitDescribePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitDescribeReadService")
public class StandardUnitDescribeReadServiceImpl  implements StandardUnitDescribeReadService {
	@Autowired
	private StandardUnitDescribeReadManage standardUnitDescribeReadManage;

	@Override
	public StandardUnitDescribeDTO findStandardUnitDescribeById(StandardUnitDescribeDTO dto) {
		StandardUnitDescribePO po = StandardUnitDescribeConverter.toPO(dto);
		StandardUnitDescribePO list = standardUnitDescribeReadManage.findStandardUnitDescribeById(po);		
		return StandardUnitDescribeConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardUnitDescribeDTO> findStandardUnitDescribeOfPage(StandardUnitDescribeDTO dto, Pagination page) {
		StandardUnitDescribePO po = StandardUnitDescribeConverter.toPO(dto);
        PageResult<StandardUnitDescribePO> pageResult = standardUnitDescribeReadManage.findStandardUnitDescribeOfPage(po, page);
        
        List<StandardUnitDescribeDTO> list = StandardUnitDescribeConverter.toDTO(pageResult.getList());
        PageResult<StandardUnitDescribeDTO> result = new PageResult<StandardUnitDescribeDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardUnitDescribeDTO> findStandardUnitDescribeAll(StandardUnitDescribeDTO dto) {
		StandardUnitDescribePO po = StandardUnitDescribeConverter.toPO(dto);
		List<StandardUnitDescribePO> list = standardUnitDescribeReadManage.findStandardUnitDescribeAll(po);		
		return StandardUnitDescribeConverter.toDTO(list);
	}
	/**
	 * 根据su商品id查询su商品星详情
	 * @param standardUnitId
	 * @return
	 */
	@Override
	public String findContentByStandardUnitId(Long standardUnitId) {
		// TODO Auto-generated method stub
		return standardUnitDescribeReadManage.findContentByStandardUnitId(standardUnitId);
	}
}
	