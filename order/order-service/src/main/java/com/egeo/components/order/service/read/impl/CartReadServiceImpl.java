package com.egeo.components.order.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.CartReadService;
import com.egeo.components.order.manage.read.CartReadManage;
import com.egeo.components.order.manage.write.CartWriteManage;
import com.egeo.components.order.converter.CartConverter;
import com.egeo.components.order.dto.CartDTO;
import com.egeo.components.order.po.CartPO;

import com.egeo.orm.PageResult;
import com.egeo.exception.BusinessException;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("cartReadService")
public class CartReadServiceImpl  implements CartReadService {
	@Autowired
	private CartReadManage cartReadManage;

	@Autowired
	private CartWriteManage cartWriteManage;
	
	@Override
	public CartDTO findCartById(CartDTO dto) {
		CartPO po = CartConverter.toPO(dto);
		CartPO list = cartReadManage.findCartById(po);		
		return CartConverter.toDTO(list);
	}

	@Override
	public PageResult<CartDTO> findCartOfPage(CartDTO dto, Pagination page) {
		CartPO po = CartConverter.toPO(dto);
        PageResult<CartPO> pageResult = cartReadManage.findCartOfPage(po, page);
        
        List<CartDTO> list = CartConverter.toDTO(pageResult.getList());
        PageResult<CartDTO> result = new PageResult<CartDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CartDTO> findCartAll(CartDTO dto) {
		CartPO po = CartConverter.toPO(dto);
		List<CartPO> list = cartReadManage.findCartAll(po);		
		return CartConverter.toDTO(list);
	}
	/**
	 * 根据用户id，平台id、门店id,销售类型查询用户购物车信息
	 * @param userId
	 * @param platformId
	 * @param clientId
	 * @return
	 */
	@Override
	public Long findByUserId(Long userId, Long platformId, Long storeId,Integer saleWay,Long clientId) {
		CartPO cartPO =  cartReadManage.findByUserId(userId, platformId,storeId, saleWay,clientId);
		if(EmptyUtil.isEmpty(cartPO)){
			CartPO cartPO2 = new CartPO();
			cartPO2.setUserid(userId);
			cartPO2.setPlatformId(platformId);
			cartPO2.setStoreId(storeId);
			cartPO2.setSaleWay(saleWay);
			cartPO2.setClientId(clientId);
			return cartWriteManage.insertCartWithTx(cartPO2);
		}else{
			return cartPO.getId();
		}
		
	}
}
	