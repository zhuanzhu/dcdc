package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.JdPriceLimitUploadDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface JdPriceLimitUploadReadService {

	public JdPriceLimitUploadDTO findJdPriceLimitUploadById(JdPriceLimitUploadDTO dto);

	public PageResult<JdPriceLimitUploadDTO> findJdPriceLimitUploadOfPage(JdPriceLimitUploadDTO dto,Pagination page);

	public List<JdPriceLimitUploadDTO> findJdPriceLimitUploadAll(JdPriceLimitUploadDTO dto);
}
	