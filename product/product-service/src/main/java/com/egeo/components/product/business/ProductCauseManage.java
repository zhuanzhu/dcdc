package com.egeo.components.product.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.egeo.components.product.vo.ProductCauseVO;

public interface ProductCauseManage {

    List<ProductCauseVO> findByProductId(Long productId);

    Long saveProductCause(ProductCauseVO productCauseVO, HttpServletRequest req);
	

}
	