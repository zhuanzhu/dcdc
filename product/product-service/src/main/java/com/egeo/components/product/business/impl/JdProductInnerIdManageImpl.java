package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.JdProductInnerIdManage;
import com.egeo.components.product.facade.JdProductInnerIdFacade;
import com.egeo.components.product.dto.JdProductInnerIdDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("jdProductInnerId")
public class JdProductInnerIdManageImpl implements JdProductInnerIdManage{

	
	@Resource(name="jdProductInnerIdFacade")
	private JdProductInnerIdFacade jdProductInnerIdFacade;

	@Override
	public JdProductInnerIdDTO findJdProductInnerIdById(JdProductInnerIdDTO dto) {
		return jdProductInnerIdFacade.findJdProductInnerIdById(dto);
	}

	@Override
	public PageResult<JdProductInnerIdDTO> findJdProductInnerIdOfPage(JdProductInnerIdDTO dto, Pagination page) {
		return jdProductInnerIdFacade.findJdProductInnerIdOfPage(dto, page);
	}

	@Override
	public List<JdProductInnerIdDTO> findJdProductInnerIdAll(JdProductInnerIdDTO dto) {
		return jdProductInnerIdFacade.findJdProductInnerIdAll(dto);
	}

	@Override
	public Long insertJdProductInnerIdWithTx(JdProductInnerIdDTO dto) {
		return jdProductInnerIdFacade.insertJdProductInnerIdWithTx(dto);
	}

	@Override
	public int updateJdProductInnerIdWithTx(JdProductInnerIdDTO dto) {
		return jdProductInnerIdFacade.updateJdProductInnerIdWithTx(dto);
	}

	@Override
	public int deleteJdProductInnerIdWithTx(JdProductInnerIdDTO dto) {
		return jdProductInnerIdFacade.deleteJdProductInnerIdWithTx(dto);
	}

	@Override
	public void updateJdProductStatus(Long jdSkuId) {
		jdProductInnerIdFacade.updateJdProductStatus(jdSkuId);
	}


}
	