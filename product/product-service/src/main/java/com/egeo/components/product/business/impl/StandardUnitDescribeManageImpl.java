package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardUnitDescribeManage;
import com.egeo.components.product.facade.StandardUnitDescribeFacade;
import com.egeo.components.product.dto.StandardUnitDescribeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitDescribe")
public class StandardUnitDescribeManageImpl implements StandardUnitDescribeManage{

	
	@Resource(name="standardUnitDescribeFacade")
	private StandardUnitDescribeFacade standardUnitDescribeFacade;

	@Override
	public StandardUnitDescribeDTO findStandardUnitDescribeById(StandardUnitDescribeDTO dto) {
		return standardUnitDescribeFacade.findStandardUnitDescribeById(dto);
	}

	@Override
	public PageResult<StandardUnitDescribeDTO> findStandardUnitDescribeOfPage(StandardUnitDescribeDTO dto, Pagination page) {
		return standardUnitDescribeFacade.findStandardUnitDescribeOfPage(dto, page);
	}

	@Override
	public List<StandardUnitDescribeDTO> findStandardUnitDescribeAll(StandardUnitDescribeDTO dto) {
		return standardUnitDescribeFacade.findStandardUnitDescribeAll(dto);
	}

	@Override
	public Long insertStandardUnitDescribeWithTx(StandardUnitDescribeDTO dto) {
		return standardUnitDescribeFacade.insertStandardUnitDescribeWithTx(dto);
	}

	@Override
	public int updateStandardUnitDescribeWithTx(StandardUnitDescribeDTO dto) {
		return standardUnitDescribeFacade.updateStandardUnitDescribeWithTx(dto);
	}

	@Override
	public int deleteStandardUnitDescribeWithTx(StandardUnitDescribeDTO dto) {
		return standardUnitDescribeFacade.deleteStandardUnitDescribeWithTx(dto);
	}
	/**
	 * 根据su商品id查询su商品星详情
	 * @param standardUnitId
	 * @return
	 */
	@Override
	public String findContentByStandardUnitId(Long standardUnitId) {
		// TODO Auto-generated method stub
		return standardUnitDescribeFacade.findContentByStandardUnitId(standardUnitId);
	}


}
	