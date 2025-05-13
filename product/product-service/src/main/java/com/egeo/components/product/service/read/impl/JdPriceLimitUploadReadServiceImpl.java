package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.JdPriceLimitUploadReadService;
import com.egeo.components.product.manage.read.JdPriceLimitUploadReadManage;
import com.egeo.components.product.converter.JdPriceLimitUploadConverter;
import com.egeo.components.product.dto.JdPriceLimitUploadDTO;
import com.egeo.components.product.po.JdPriceLimitUploadPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("jdPriceLimitUploadReadService")
public class JdPriceLimitUploadReadServiceImpl  implements JdPriceLimitUploadReadService {
	@Autowired
	private JdPriceLimitUploadReadManage jdPriceLimitUploadReadManage;

	@Override
	public JdPriceLimitUploadDTO findJdPriceLimitUploadById(JdPriceLimitUploadDTO dto) {
		JdPriceLimitUploadPO po = JdPriceLimitUploadConverter.toPO(dto);
		JdPriceLimitUploadPO list = jdPriceLimitUploadReadManage.findJdPriceLimitUploadById(po);		
		return JdPriceLimitUploadConverter.toDTO(list);
	}

	@Override
	public PageResult<JdPriceLimitUploadDTO> findJdPriceLimitUploadOfPage(JdPriceLimitUploadDTO dto, Pagination page) {
		JdPriceLimitUploadPO po = JdPriceLimitUploadConverter.toPO(dto);
        PageResult<JdPriceLimitUploadPO> pageResult = jdPriceLimitUploadReadManage.findJdPriceLimitUploadOfPage(po, page);
        
        List<JdPriceLimitUploadDTO> list = JdPriceLimitUploadConverter.toDTO(pageResult.getList());
        PageResult<JdPriceLimitUploadDTO> result = new PageResult<JdPriceLimitUploadDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<JdPriceLimitUploadDTO> findJdPriceLimitUploadAll(JdPriceLimitUploadDTO dto) {
		JdPriceLimitUploadPO po = JdPriceLimitUploadConverter.toPO(dto);
		List<JdPriceLimitUploadPO> list = jdPriceLimitUploadReadManage.findJdPriceLimitUploadAll(po);		
		return JdPriceLimitUploadConverter.toDTO(list);
	}
}
	