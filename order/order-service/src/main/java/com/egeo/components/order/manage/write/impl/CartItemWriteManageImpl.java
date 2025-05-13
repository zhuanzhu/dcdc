package com.egeo.components.order.manage.write.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.CartItemWriteManage;
import com.egeo.components.order.dao.read.CartItemReadDAO;
import com.egeo.components.order.dao.write.CartItemWriteDAO;
import com.egeo.components.order.po.CartItemPO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

@Service
public class CartItemWriteManageImpl implements CartItemWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CartItemWriteDAO cartItemWriteDAO;
	
	@Autowired
	private CartItemReadDAO cartItemReadDAO;

	@Override
	public Long insertCartItemWithTx(CartItemPO po) {
		
		int i ;
		try {
				i = cartItemWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCartItemWithTx(CartItemPO po) {
		int i;
		i = cartItemWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCartItemWithTx(CartItemPO po) {
		int i;
		i = cartItemWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据购物车pu商品关系id把购物车数量加一
	 * @param cartItemId
	 * @return
	 */
	@Override
	public int addNumWithTx(Long cartItemId) {
		// TODO Auto-generated method stub
		return cartItemWriteDAO.addNumWithTx(cartItemId);
	}
	/**
	 * 根据购物车pu商品关系id把购物车数量减一
	 * @param cartItemId
	 * @return
	 */
	@Override
	public int minusNumWithTx(Long cartItemId) {
		//根据购物车商品id查询购物车商品
		CartItemPO cartItemPO = new CartItemPO();
		cartItemPO.setId(cartItemId);
		CartItemPO cartItemPO2 = cartItemReadDAO.findById(cartItemPO);
		if(EmptyUtil.isNotEmpty(cartItemPO2)){
			if(cartItemPO2.getNum().equals(1L)){
				//如果数量为一就删除根据购物车商品id删除购物车商品信息
				List<Long> cartItemIds = new ArrayList<>();
				cartItemIds.add(cartItemId);
				cartItemWriteDAO.batchDeleteCartItemsByIds(cartItemIds);
				return 1;
			}else{
				return cartItemWriteDAO.minusNumWithTx(cartItemId);
			}
			
		}
		return 0;
		
	}
	/**
	 * 根据购物车puid增加pu数量
	 * @param id
	 * @param num
	 * @return
	 */
	@Override
	public int updateNumById(Long cartItemId, Long num) {
		// TODO Auto-generated method stub
		return cartItemWriteDAO.updateNumById(cartItemId, num);
	}

	@Override
	public int deleteCartItemByIdsWithTx(List<Long> cartItemIdList) {
		return cartItemWriteDAO.batchDeleteCartItemsByIds(cartItemIdList);
	}	
}
	