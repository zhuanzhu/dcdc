package com.egeo.components.order.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.CartReadService;
import com.egeo.components.order.service.write.CartWriteService;
import com.egeo.components.order.dto.CartDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class CartFacade {
	
	@Resource
	private CartReadService cartReadService;
	
	@Resource
	private CartWriteService cartWriteService;
	
	
	public CartDTO findCartById(CartDTO dto){
		
		return cartReadService.findCartById(dto);
	}

	public PageResult<CartDTO> findCartOfPage(CartDTO dto,Pagination page){
		
		return cartReadService.findCartOfPage(dto, page);
		
	}

	public List<CartDTO> findCartAll(CartDTO dto){
		
		return cartReadService.findCartAll(dto);
		
	}

	public Long insertCartWithTx(CartDTO dto){
		
		return cartWriteService.insertCartWithTx(dto);
	}

	public int updateCartWithTx(CartDTO dto){
		
		return cartWriteService.updateCartWithTx(dto);
	}

	public int deleteCartWithTx(CartDTO dto){
		
		return cartWriteService.deleteCartWithTx(dto);
		
	}

}
	