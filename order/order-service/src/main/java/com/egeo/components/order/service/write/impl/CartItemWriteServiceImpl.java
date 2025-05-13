package com.egeo.components.order.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.write.CartItemWriteService;
import com.egeo.components.order.manage.read.CartItemReadManage;
import com.egeo.components.order.manage.read.CartReadManage;
import com.egeo.components.order.manage.write.CartItemWriteManage;
import com.egeo.components.order.manage.write.CartWriteManage;
import com.egeo.components.order.converter.CartItemConverter;
import com.egeo.components.order.dto.CartItemDTO;
import com.egeo.components.order.po.CartItemPO;
import com.egeo.components.order.po.CartPO;

import com.egeo.utils.EmptyUtil;

@Service("cartItemWriteService")
public class CartItemWriteServiceImpl  implements CartItemWriteService {
	@Autowired
	private CartItemWriteManage cartItemWriteManage;
	
	@Autowired
	private CartItemReadManage cartItemReadManage;
	
	@Autowired
	private CartReadManage cartReadManage;
	
	@Autowired
	private CartWriteManage cartWriteManage;

	@Override
	public Long insertCartItemWithTx(CartItemDTO dto) {
		CartItemPO po = CartItemConverter.toPO(dto);
		Long rt = cartItemWriteManage.insertCartItemWithTx(po);		
		return rt;
	}

	@Override
	public int updateCartItemWithTx(CartItemDTO dto) {
		CartItemPO po = CartItemConverter.toPO(dto);
		int rt = cartItemWriteManage.updateCartItemWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCartItemWithTx(CartItemDTO dto) {
		CartItemPO po = CartItemConverter.toPO(dto);
		int rt = cartItemWriteManage.deleteCartItemWithTx(po);		
		return rt;
	}
	/**
	 * 加入购物车
	 * @param vo
	 * @param req
	 * @return  返回购物车item 关联id
	 */
	@Override
	public Long saveCartItemWithTx(CartItemDTO dto, Long userId, Integer saleWay,Long storeId,Long clientId) {
		CartItemPO cartItemPO = CartItemConverter.toPO(dto);
		return cartWriteManage.saveCartItemWithTx(cartItemPO,userId,saleWay,storeId,clientId);
		/*//根据用户id，平台id，客户端id查询购物车信息,判断是否
		CartPO cartPO = cartReadManage.findByUserId(userId, dto.getPlatformId(), storeId,saleWay);
		if(EmptyUtil.isEmpty(cartPO)){
			//购物车还未创建(创建购物车,添加商品)
			CartPO cartPO2 = new CartPO();
			cartPO2.setUserid(userId);
			cartPO2.setPlatformId(dto.getPlatformId());
			cartPO2.setSaleWay(saleWay);
			cartPO2.setStoreId(storeId);
			Long cartId = cartWriteManage.insertCartWithTx(cartPO2);

			cartItemPO.setCartId(cartId);
			return cartItemWriteManage.insertCartItemWithTx(cartItemPO);
		}else{
			//购物车已存在(添加商品即可)
			//根据购物车id和pu商品id查询购物车信息
			CartItemPO cartItemPO2 = cartItemReadManage.findByCartIdProductUnitId(cartPO.getId(),cartItemPO.getProductUnitId());
			if(EmptyUtil.isNotEmpty(cartItemPO2)){
				//根据购物车puid增加pu数量
				cartItemWriteManage.updateNumById(cartItemPO2.getId(),cartItemPO.getNum());
				return cartItemPO2.getId();
			}else{
				//购物车存在,但是没有该商品
				cartItemPO.setCartId(cartPO.getId());
				return cartItemWriteManage.insertCartItemWithTx(cartItemPO);
			}
		}*/
	}
	/**
	 * 根据购物车pu商品关系id把购物车数量加一
	 * @param cartItemId
	 * @return
	 */
	@Override
	public int addNumWithTx(Long cartItemId) {
		// TODO Auto-generated method stub
		return cartItemWriteManage.addNumWithTx(cartItemId);
	}
	/**
	 * 根据购物车pu商品关系id把购物车数量减一
	 * @param cartItemId
	 * @return
	 */
	@Override
	public int minusNumWithTx(Long cartItemId) {
		// TODO Auto-generated method stub
		return cartItemWriteManage.minusNumWithTx(cartItemId);
	}

	@Override
	public int deleteCartItemByIdsWithTx(List<Long> cartItemIdList) {
		// TODO Auto-generated method stub
		return cartItemWriteManage.deleteCartItemByIdsWithTx(cartItemIdList);
	}
}
	