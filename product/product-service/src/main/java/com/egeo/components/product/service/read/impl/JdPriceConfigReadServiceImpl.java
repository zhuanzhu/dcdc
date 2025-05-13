package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.JdPriceConfigReadService;
import com.egeo.components.product.manage.read.JdPriceConfigReadManage;
import com.egeo.components.product.converter.JdPriceConfigConverter;
import com.egeo.components.product.dto.JdPriceConfigDTO;
import com.egeo.components.product.po.JdPriceConfigPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("jdPriceConfigReadService")
public class JdPriceConfigReadServiceImpl  implements JdPriceConfigReadService {
	@Autowired
	private JdPriceConfigReadManage jdPriceConfigReadManage;

	@Override
	public JdPriceConfigDTO findJdPriceConfigById(JdPriceConfigDTO dto) {
		JdPriceConfigPO po = JdPriceConfigConverter.toPO(dto);
		JdPriceConfigPO list = jdPriceConfigReadManage.findJdPriceConfigById(po);		
		return JdPriceConfigConverter.toDTO(list);
	}

	@Override
	public PageResult<JdPriceConfigDTO> findJdPriceConfigOfPage(JdPriceConfigDTO dto, Pagination page) {
		JdPriceConfigPO po = JdPriceConfigConverter.toPO(dto);
        PageResult<JdPriceConfigPO> pageResult = jdPriceConfigReadManage.findJdPriceConfigOfPage(po, page);
        
        List<JdPriceConfigDTO> list = JdPriceConfigConverter.toDTO(pageResult.getList());
        PageResult<JdPriceConfigDTO> result = new PageResult<JdPriceConfigDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<JdPriceConfigDTO> findJdPriceConfigAll(JdPriceConfigDTO dto) {
		JdPriceConfigPO po = JdPriceConfigConverter.toPO(dto);
		List<JdPriceConfigPO> list = jdPriceConfigReadManage.findJdPriceConfigAll(po);		
		return JdPriceConfigConverter.toDTO(list);
	}
}
	