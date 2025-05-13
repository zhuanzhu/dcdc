package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.JdProductReadService;
import com.egeo.components.product.manage.read.JdProductReadManage;
import com.egeo.components.product.converter.JdProductConverter;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.po.JdProductPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("jdProductReadService")
public class JdProductReadServiceImpl  implements JdProductReadService {
	@Autowired
	private JdProductReadManage jdProductReadManage;

	@Override
	public JdProductDTO findJdProductById(JdProductDTO dto) {
		JdProductPO po = JdProductConverter.toPO(dto);
		JdProductPO list = jdProductReadManage.findJdProductById(po);		
		return JdProductConverter.toDTO(list);
	}

	@Override
	public PageResult<JdProductDTO> findJdProductOfPage(JdProductDTO dto, Pagination page) {
		JdProductPO po = JdProductConverter.toPO(dto);
        PageResult<JdProductPO> pageResult = jdProductReadManage.findJdProductOfPage(po, page);
        
        List<JdProductDTO> list = JdProductConverter.toDTO(pageResult.getList());
        PageResult<JdProductDTO> result = new PageResult<JdProductDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<JdProductDTO> findJdProductAll(JdProductDTO dto) {
		JdProductPO po = JdProductConverter.toPO(dto);
		List<JdProductPO> list = jdProductReadManage.findJdProductAll(po);		
		return JdProductConverter.toDTO(list);
	}

	@Override
	public List<Long> findAllIdList() {
		return jdProductReadManage.findAllIdList();
	}

	@Override
	public List<JdProductDTO> findJdProductListByIds(List<Long> skuIdList) {
		return JdProductConverter.toDTO(jdProductReadManage.findJdProductListByIds(skuIdList));
	}

	@Override
	public PageResult<JdProductDTO> getJdProductListByParams(Pagination page, Long skuId, List<String> skuNameList, Long updateTimeStart, Long updateTimeEnd, Integer profitStart, Integer profitEnd, Integer state, Integer sycStatus, Long catId, Integer isShow) {


		PageResult<JdProductPO> pageResult = jdProductReadManage.getJdProductListByParams(page, skuId, skuNameList, updateTimeStart, updateTimeEnd,
				profitStart, profitEnd, state, sycStatus, catId, isShow);
		List<JdProductDTO> list = JdProductConverter.toDTO(pageResult.getList());
		PageResult<JdProductDTO> result = new PageResult<JdProductDTO>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	@Override
	public Integer findJdProductCountByProfit(Integer profit) {
		return jdProductReadManage.findJdProductCountByProfit(profit);
	}

	@Override
	public List<JdProductDTO> findJdProductListByProfit(Integer profit, Integer start, Integer pageSize) {
		return JdProductConverter.toDTO(jdProductReadManage.findJdProductListByProfit(profit,start,pageSize));
	}

	@Override
	public List<Long> findHavedIdListByIdList(List<Long> ids) {
		return jdProductReadManage.findHavedIdListByIdList(ids);
	}
}
	