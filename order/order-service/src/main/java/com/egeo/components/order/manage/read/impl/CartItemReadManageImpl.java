package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import com.egeo.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.CartItemReadManage;
import com.egeo.components.order.dao.read.CartItemReadDAO;
import com.egeo.components.order.po.CartItemPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CartItemReadManageImpl implements CartItemReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CartItemReadDAO cartItemReadDAO;
	
	public CartItemPO findCartItemById(CartItemPO po) {
		CartItemPO cartItempo = new CartItemPO();
		cartItempo.setId(po.getId());
		return cartItemReadDAO.findById(cartItempo);
	}

	public PageResult<CartItemPO> findCartItemOfPage(CartItemPO po, Pagination page) {
		
		PageResult<CartItemPO> pageResult = new PageResult<CartItemPO>();
		List<CartItemPO> list = null;

		int cnt = cartItemReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = cartItemReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CartItemPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CartItemPO> findCartItemAll(CartItemPO po) {

		return cartItemReadDAO.findAll(po,null);
	}
	/**
	 * 根据购物车id和pu商品id查询购物车信息
	 * @param id
	 * @param productUnitId
	 * @return
	 */
	@Override
	public CartItemPO findByCartIdProductUnitId(Long cartId, Long productUnitId) {
		// TODO Auto-generated method stub
		return cartItemReadDAO.findByCartIdProductUnitId(cartId, productUnitId);
	}
	/**
	 * 根据用户id查询购物车pu数量
	 * @param req
	 * @return
	 */
	@Override
	public Integer findCartItemSumByUserId(Long storeId,Long userId, Long platformId, Long clientId) {
		// TODO Auto-generated method stub
		int i=0;
		try{
			i=cartItemReadDAO.findCartItemSumByUserIds(storeId,userId, platformId, clientId);
		}catch (Exception e){
			i=0;
		}
		return i;
	}
	/**
	 * 根据用户id查询用户购物车pu种类数量
	 * @param userId
	 * @param platformId
	 * @param clientId
	 * @return
	 */
	@Override
	public int findCartItemPUSumByUserId(Long userId, Long platformId, Long storeId) {
		// TODO Auto-generated method stub
		return cartItemReadDAO.findCartItemPUSumByUserId(userId, platformId, storeId);
	}
	
}
	