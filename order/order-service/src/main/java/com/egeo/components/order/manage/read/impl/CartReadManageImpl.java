package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.CartReadManage;
import com.egeo.components.order.dao.read.CartReadDAO;
import com.egeo.components.order.po.CartPO;
import com.egeo.orm.PageResult;
import com.egeo.exception.BusinessException;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Service
public class CartReadManageImpl implements CartReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CartReadDAO cartReadDAO;
	
	public CartPO findCartById(CartPO po) {
		CartPO cartpo = new CartPO();
		cartpo.setId(po.getId());
		return cartReadDAO.findById(cartpo);
	}

	public PageResult<CartPO> findCartOfPage(CartPO po, Pagination page) {
		
		PageResult<CartPO> pageResult = new PageResult<CartPO>();
		List<CartPO> list = null;

		int cnt = cartReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = cartReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CartPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CartPO> findCartAll(CartPO po) {

		return cartReadDAO.findAll(po,null);
	}
	/**
	 * 根据用户id，平台id、客户端id查询用户购物车信息
	 * @param userId
	 * @param platformId
	 * @param clientId
	 * @return
	 */
	@Override
	public CartPO findByUserId(Long userId, Long platformId, Long storeId,Integer saleWay,Long clientId) {
		if(EmptyUtil.isEmpty(userId)){
			throw new BusinessException("用户id不能为空");
		}
		if(EmptyUtil.isEmpty(platformId)){
			throw new BusinessException("平台id不能为空");
		}
		if(EmptyUtil.isEmpty(saleWay)){
			throw new BusinessException("销售方式不能为空");
		}
		CartPO byUserId = cartReadDAO.findByUserId(userId, platformId, storeId, saleWay,clientId);
		return byUserId;
	}
	
}
	