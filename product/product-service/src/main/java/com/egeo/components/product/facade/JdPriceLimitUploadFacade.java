package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.JdPriceLimitUploadReadService;
import com.egeo.components.product.service.write.JdPriceLimitUploadWriteService;
import com.egeo.components.product.dto.JdPriceLimitUploadDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class JdPriceLimitUploadFacade {
	
	@Resource
	private JdPriceLimitUploadReadService jdPriceLimitUploadReadService;
	
	@Resource
	private JdPriceLimitUploadWriteService jdPriceLimitUploadWriteService;
	
	
	public JdPriceLimitUploadDTO findJdPriceLimitUploadById(JdPriceLimitUploadDTO dto){
		
		return jdPriceLimitUploadReadService.findJdPriceLimitUploadById(dto);
	}

	public PageResult<JdPriceLimitUploadDTO> findJdPriceLimitUploadOfPage(JdPriceLimitUploadDTO dto,Pagination page){
		
		return jdPriceLimitUploadReadService.findJdPriceLimitUploadOfPage(dto, page);
		
	}

	public List<JdPriceLimitUploadDTO> findJdPriceLimitUploadAll(JdPriceLimitUploadDTO dto){
		
		return jdPriceLimitUploadReadService.findJdPriceLimitUploadAll(dto);
		
	}

	public Long insertJdPriceLimitUploadWithTx(JdPriceLimitUploadDTO dto){
		
		return jdPriceLimitUploadWriteService.insertJdPriceLimitUploadWithTx(dto);
	}

	public int updateJdPriceLimitUploadWithTx(JdPriceLimitUploadDTO dto){
		
		return jdPriceLimitUploadWriteService.updateJdPriceLimitUploadWithTx(dto);
	}

	public int deleteJdPriceLimitUploadWithTx(JdPriceLimitUploadDTO dto){
		
		return jdPriceLimitUploadWriteService.deleteJdPriceLimitUploadWithTx(dto);
		
	}

}
	