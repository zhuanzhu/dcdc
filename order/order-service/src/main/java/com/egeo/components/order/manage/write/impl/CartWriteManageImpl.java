package com.egeo.components.order.manage.write.impl;

import com.egeo.components.order.manage.write.CartItemWriteManage;
import com.egeo.components.order.dao.read.CartItemReadDAO;
import com.egeo.components.order.dao.read.CartReadDAO;
import com.egeo.components.order.dao.write.CartItemWriteDAO;
import com.egeo.components.order.po.CartItemPO;
import com.egeo.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.CartWriteManage;
import com.egeo.components.order.dao.write.CartWriteDAO;
import com.egeo.components.order.po.CartPO;
import com.egeo.exception.BusinessException;

@Service
public class CartWriteManageImpl implements CartWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CartWriteDAO cartWriteDAO;
	@Autowired
	private CartReadDAO cartReadDAO;
	@Autowired
	private CartItemWriteDAO cartItemWriteDAO;
	@Autowired
	private CartItemReadDAO cartItemReadDAO;

	@Override
	public Long insertCartWithTx(CartPO po) {
		
		int i ;
		try {
				i = cartWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCartWithTx(CartPO po) {
		int i;
		i = cartWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCartWithTx(CartPO po) {
		int i;
		i = cartWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public Long saveCartItemWithTx(CartItemPO cartItemPO, Long userId, Integer saleWay, Long storeId,Long clientId) {
//根据用户id，平台id，客户端id查询购物车信息,判断是否
		CartPO cartPO = cartReadDAO.findByUserId(userId, cartItemPO.getPlatformId(), storeId,saleWay,clientId);
		if(EmptyUtil.isEmpty(cartPO)){
			//购物车还未创建(创建购物车,添加商品)
			CartPO cartPO2 = new CartPO();
			cartPO2.setUserid(userId);
			cartPO2.setClientId(clientId);
			cartPO2.setPlatformId(cartItemPO.getPlatformId());
			cartPO2.setSaleWay(saleWay);
			cartPO2.setStoreId(storeId);
			int i ;
			int j;
			try {
				i = cartWriteDAO.insert(cartPO2);
				cartItemPO.setCartId(cartPO2.getId());
				j=cartItemWriteDAO.insert(cartItemPO);

				if (i == 0||j==0)
					throw new BusinessException("未能成功插入数据!");

				return cartItemPO.getId();
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		}else{
			//购物车已存在(添加商品即可)
			//根据购物车id和pu商品id查询购物车信息
			CartItemPO cartItemPO2 = cartItemReadDAO.findByCartIdProductUnitId(cartPO.getId(),cartItemPO.getProductUnitId());
			if(EmptyUtil.isNotEmpty(cartItemPO2)){
				//根据购物车puid增加pu数量
				cartItemWriteDAO.updateNumById(cartItemPO2.getId(),cartItemPO.getNum());
				return cartItemPO2.getId();
			}else{
				//购物车存在,但是没有该商品
				cartItemPO.setCartId(cartPO.getId());
				int i;
				i=cartItemWriteDAO.insert(cartItemPO);
				if(i==0){
					throw new BusinessException("购物车插入失败");
				}
				return cartItemPO.getId();
			}
		}
	}
}
	