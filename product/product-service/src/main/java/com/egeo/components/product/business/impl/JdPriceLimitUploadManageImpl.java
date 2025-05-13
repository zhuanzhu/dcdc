package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.JdPriceLimitUploadManage;
import com.egeo.components.product.facade.JdPriceLimitUploadFacade;
import com.egeo.components.product.dto.JdPriceLimitUploadDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("jdPriceLimitUpload")
public class JdPriceLimitUploadManageImpl implements JdPriceLimitUploadManage{

	
	@Resource(name="jdPriceLimitUploadFacade")
	private JdPriceLimitUploadFacade jdPriceLimitUploadFacade;

	@Override
	public JdPriceLimitUploadDTO findJdPriceLimitUploadById(JdPriceLimitUploadDTO dto) {
		return jdPriceLimitUploadFacade.findJdPriceLimitUploadById(dto);
	}

	@Override
	public PageResult<JdPriceLimitUploadDTO> findJdPriceLimitUploadOfPage(JdPriceLimitUploadDTO dto, Pagination page) {
		return jdPriceLimitUploadFacade.findJdPriceLimitUploadOfPage(dto, page);
	}

	@Override
	public List<JdPriceLimitUploadDTO> findJdPriceLimitUploadAll(JdPriceLimitUploadDTO dto) {
		return jdPriceLimitUploadFacade.findJdPriceLimitUploadAll(dto);
	}

	@Override
	public Long insertJdPriceLimitUploadWithTx(JdPriceLimitUploadDTO dto) {
		return jdPriceLimitUploadFacade.insertJdPriceLimitUploadWithTx(dto);
	}

	@Override
	public int updateJdPriceLimitUploadWithTx(JdPriceLimitUploadDTO dto) {
		return jdPriceLimitUploadFacade.updateJdPriceLimitUploadWithTx(dto);
	}

	@Override
	public int deleteJdPriceLimitUploadWithTx(JdPriceLimitUploadDTO dto) {
		return jdPriceLimitUploadFacade.deleteJdPriceLimitUploadWithTx(dto);
	}


}
	