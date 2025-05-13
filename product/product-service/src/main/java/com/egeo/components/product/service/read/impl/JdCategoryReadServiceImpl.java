package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.JdCategoryReadService;
import com.egeo.components.product.manage.read.JdCategoryReadManage;
import com.egeo.components.product.converter.JdCategoryConverter;
import com.egeo.components.product.dto.JdCategoryDTO;
import com.egeo.components.product.po.JdCategoryPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("jdCategoryReadService")
public class JdCategoryReadServiceImpl  implements JdCategoryReadService {
	@Autowired
	private JdCategoryReadManage jdCategoryReadManage;

	@Override
	public JdCategoryDTO findJdCategoryById(JdCategoryDTO dto) {
		JdCategoryPO po = JdCategoryConverter.toPO(dto);
		JdCategoryPO list = jdCategoryReadManage.findJdCategoryById(po);		
		return JdCategoryConverter.toDTO(list);
	}

	@Override
	public PageResult<JdCategoryDTO> findJdCategoryOfPage(JdCategoryDTO dto, Pagination page) {
		JdCategoryPO po = JdCategoryConverter.toPO(dto);
        PageResult<JdCategoryPO> pageResult = jdCategoryReadManage.findJdCategoryOfPage(po, page);
        
        List<JdCategoryDTO> list = JdCategoryConverter.toDTO(pageResult.getList());
        PageResult<JdCategoryDTO> result = new PageResult<JdCategoryDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<JdCategoryDTO> findJdCategoryAll(JdCategoryDTO dto) {
		JdCategoryPO po = JdCategoryConverter.toPO(dto);
		List<JdCategoryPO> list = jdCategoryReadManage.findJdCategoryAll(po);		
		return JdCategoryConverter.toDTO(list);
	}

	@Override
	public List<Long> findJdCategoryIdByCatClass(int catClass) {
		return jdCategoryReadManage.findJdCategoryIdByCatClass(catClass);
	}


}
	