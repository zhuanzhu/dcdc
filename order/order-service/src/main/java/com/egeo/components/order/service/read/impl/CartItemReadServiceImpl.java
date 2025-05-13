package com.egeo.components.order.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.CartItemReadService;
import com.egeo.components.order.manage.read.CartItemReadManage;
import com.egeo.components.order.converter.CartItemConverter;
import com.egeo.components.order.dto.CartItemDTO;
import com.egeo.components.order.po.CartItemPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cartItemReadService")
public class CartItemReadServiceImpl  implements CartItemReadService {
	@Autowired
	private CartItemReadManage cartItemReadManage;

	@Override
	public CartItemDTO findCartItemById(CartItemDTO dto) {
		CartItemPO po = CartItemConverter.toPO(dto);
		CartItemPO list = cartItemReadManage.findCartItemById(po);		
		return CartItemConverter.toDTO(list);
	}

	@Override
	public PageResult<CartItemDTO> findCartItemOfPage(CartItemDTO dto, Pagination page) {
		CartItemPO po = CartItemConverter.toPO(dto);
        PageResult<CartItemPO> pageResult = cartItemReadManage.findCartItemOfPage(po, page);
        
        List<CartItemDTO> list = CartItemConverter.toDTO(pageResult.getList());
        PageResult<CartItemDTO> result = new PageResult<CartItemDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CartItemDTO> findCartItemAll(CartItemDTO dto) {
		CartItemPO po = CartItemConverter.toPO(dto);
		List<CartItemPO> list = cartItemReadManage.findCartItemAll(po);		
		return CartItemConverter.toDTO(list);
	}
	/**
	 * 根据用户id查询购物车pu数量
	 * @param req
	 * @return
	 */
	@Override
	public Integer findCartItemSumByUserId(Long storeId,Long userId, Long platformId, Long clientId) {
		// TODO Auto-generated method stub
		return cartItemReadManage.findCartItemSumByUserId(storeId,userId, platformId, clientId);
	}
	/**
	 * 根据用户id查询用户购物车数量
	 * @param userId
	 * @param platformId
	 * @param clientId
	 * @return
	 */
	@Override
	public int findCartItemPUSumByUserId(Long userId, Long platformId,Long storeId) {
		// TODO Auto-generated method stub
		return cartItemReadManage.findCartItemPUSumByUserId(userId, platformId,storeId);
	}
}
	