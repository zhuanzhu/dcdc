package com.egeo.components.order.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.CartManage;
import com.egeo.components.order.facade.CartFacade;
import com.egeo.components.order.dto.CartDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cart")
public class CartManageImpl implements CartManage{

	
	@Resource(name="cartFacade")
	private CartFacade cartFacade;

	@Override
	public CartDTO findCartById(CartDTO dto) {
		return cartFacade.findCartById(dto);
	}

	@Override
	public PageResult<CartDTO> findCartOfPage(CartDTO dto, Pagination page) {
		return cartFacade.findCartOfPage(dto, page);
	}

	@Override
	public List<CartDTO> findCartAll(CartDTO dto) {
		return cartFacade.findCartAll(dto);
	}

	@Override
	public Long insertCartWithTx(CartDTO dto) {
		return cartFacade.insertCartWithTx(dto);
	}

	@Override
	public int updateCartWithTx(CartDTO dto) {
		return cartFacade.updateCartWithTx(dto);
	}

	@Override
	public int deleteCartWithTx(CartDTO dto) {
		return cartFacade.deleteCartWithTx(dto);
	}


}
	