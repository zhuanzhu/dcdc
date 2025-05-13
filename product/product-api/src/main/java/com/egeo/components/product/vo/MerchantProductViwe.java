package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.List;

import com.egeo.components.product.dto.SellPlatformDTO;

/**
 * @author Administrator
 *
 */
public class MerchantProductViwe implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1702528266308079161L;

	private ProductVO productVO;
	
	private List<SellPlatformDTO> list;

	public List<SellPlatformDTO> getList() {
		return list;
	}

	public void setList(List<SellPlatformDTO> list) {
		this.list = list;
	}

	public ProductVO getProductVO() {
		return productVO;
	}

	public void setProductVO(ProductVO productVO) {
		this.productVO = productVO;
	}

}
