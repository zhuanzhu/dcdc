package com.egeo.components.pay.facade;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.finance.client.SoFreezeFubiClient;
import com.egeo.components.finance.client.UserAccountClient;
import com.egeo.components.finance.dto.UserAccountDTO;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.dto.UserDTO;

@Component
public class AccountFacade {

	@Autowired
	private UserAccountClient userAccountReadService;
	
	@Autowired
	private UserClient userReadService;
	
	@Resource
	private SoFreezeFubiClient soFreezeFubiReadService;

	public UserAccountDTO queryUserAccountByUserIdAndType(Long userId, Integer type) {
		return userAccountReadService.queryUserAccountByUserIdAndType(userId, type);
	}

	public UserDTO queryUserById(Long userId) {
		return userReadService.findUserByID(userId);
	}

	/**
	 * 查询订单冻结的积分数量
	 * @param id
	 * @return
	 */
	public BigDecimal querySoFreezeFubi(Long id) {
		
		return soFreezeFubiReadService.findSoFreezeBalanceBySoId(id);
	}
	
	
}
