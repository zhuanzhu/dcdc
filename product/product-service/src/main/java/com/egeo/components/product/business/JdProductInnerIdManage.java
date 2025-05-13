package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.JdProductInnerIdDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface JdProductInnerIdManage {

	public JdProductInnerIdDTO findJdProductInnerIdById(JdProductInnerIdDTO dto);	

	public PageResult<JdProductInnerIdDTO> findJdProductInnerIdOfPage(JdProductInnerIdDTO dto,Pagination page);

	public List<JdProductInnerIdDTO> findJdProductInnerIdAll(JdProductInnerIdDTO dto);

	Long insertJdProductInnerIdWithTx(JdProductInnerIdDTO dto);

	int updateJdProductInnerIdWithTx(JdProductInnerIdDTO dto);

	int deleteJdProductInnerIdWithTx(JdProductInnerIdDTO dto);

    void updateJdProductStatus(Long jdSkuId);
}
	