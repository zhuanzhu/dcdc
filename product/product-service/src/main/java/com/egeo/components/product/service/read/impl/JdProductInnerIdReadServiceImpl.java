package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.JdProductInnerIdReadService;
import com.egeo.components.product.manage.read.JdProductInnerIdReadManage;
import com.egeo.components.product.converter.JdProductInnerIdConverter;
import com.egeo.components.product.dto.JdProductInnerIdDTO;
import com.egeo.components.product.po.JdProductInnerIdPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("jdProductInnerIdReadService")
public class JdProductInnerIdReadServiceImpl  implements JdProductInnerIdReadService {
	@Autowired
	private JdProductInnerIdReadManage jdProductInnerIdReadManage;

	@Override
	public JdProductInnerIdDTO findJdProductInnerIdById(JdProductInnerIdDTO dto) {
		JdProductInnerIdPO po = JdProductInnerIdConverter.toPO(dto);
		JdProductInnerIdPO list = jdProductInnerIdReadManage.findJdProductInnerIdById(po);		
		return JdProductInnerIdConverter.toDTO(list);
	}

	@Override
	public PageResult<JdProductInnerIdDTO> findJdProductInnerIdOfPage(JdProductInnerIdDTO dto, Pagination page) {
		JdProductInnerIdPO po = JdProductInnerIdConverter.toPO(dto);
        PageResult<JdProductInnerIdPO> pageResult = jdProductInnerIdReadManage.findJdProductInnerIdOfPage(po, page);
        
        List<JdProductInnerIdDTO> list = JdProductInnerIdConverter.toDTO(pageResult.getList());
        PageResult<JdProductInnerIdDTO> result = new PageResult<JdProductInnerIdDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<JdProductInnerIdDTO> findJdProductInnerIdAll(JdProductInnerIdDTO dto) {
		JdProductInnerIdPO po = JdProductInnerIdConverter.toPO(dto);
		List<JdProductInnerIdPO> list = jdProductInnerIdReadManage.findJdProductInnerIdAll(po);		
		return JdProductInnerIdConverter.toDTO(list);
	}

	@Override
	public List<JdProductInnerIdDTO> findJdProductInnerIdAllByJdSkuIdList(List<Long> skuIdList) {
		List<JdProductInnerIdPO> list = jdProductInnerIdReadManage.findJdProductInnerIdAllByJdSkuIdList(skuIdList);
		return JdProductInnerIdConverter.toDTO(list);
	}

	@Override
	public Integer findSuProfitById(Long suId) {
		return jdProductInnerIdReadManage.findSuProfitById(suId);
	}

	@Override
	public Long findPuIdByJdSkuId(Long skuId) {
		return jdProductInnerIdReadManage.findPuIdByJdSkuId(skuId);
	}
}
	