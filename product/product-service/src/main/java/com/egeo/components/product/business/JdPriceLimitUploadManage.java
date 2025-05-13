package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.JdPriceLimitUploadDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface JdPriceLimitUploadManage {

	public JdPriceLimitUploadDTO findJdPriceLimitUploadById(JdPriceLimitUploadDTO dto);	

	public PageResult<JdPriceLimitUploadDTO> findJdPriceLimitUploadOfPage(JdPriceLimitUploadDTO dto,Pagination page);

	public List<JdPriceLimitUploadDTO> findJdPriceLimitUploadAll(JdPriceLimitUploadDTO dto);

	Long insertJdPriceLimitUploadWithTx(JdPriceLimitUploadDTO dto);

	int updateJdPriceLimitUploadWithTx(JdPriceLimitUploadDTO dto);

	int deleteJdPriceLimitUploadWithTx(JdPriceLimitUploadDTO dto);
}
	