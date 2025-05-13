package com.egeo.components.promotion.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.config.client.CardSaltClient;
import com.egeo.components.config.client.SaltClient;
import com.egeo.components.config.dto.CardSaltDTO;
import com.egeo.components.config.dto.SaltDTO;
import com.egeo.components.finance.client.AccountBatchClient;
import com.egeo.components.finance.client.AccountFlowClient;
import com.egeo.components.finance.client.SoFreezeFubiClient;
import com.egeo.components.finance.client.UserAccountClient;
import com.egeo.components.finance.dto.UserAccountDTO;

@Component
public class AccountFacade {

	
	@Autowired
	private UserAccountClient userAccountReadService;
	
	@Autowired
	private UserAccountClient userAccountWriteService;
	
	@Autowired
	private SaltClient saltReadService;
	
	@Autowired
	private CardSaltClient cardSaltReadService;
	
	@Autowired
	private AccountFlowClient afReadService;
	
	@Autowired
	private AccountBatchClient abReadService;
	
	@Autowired
	private SoFreezeFubiClient ffReadService;
	
	@Autowired
	private SoFreezeFubiClient ffWriteService;

	/**
	 * 查询用户账户
	 * @param memberId
	 * @return
	 */
	public UserAccountDTO queryUserAccountByUserId(Long memberId,Integer type) {
		return userAccountReadService.queryUserAccountByUserIdAndType(memberId,type);
	}

	/**
	 * 根据uuid查询盐
	 * @param uuid
	 * @return
	 */
	public SaltDTO querySaltByUUID(String uuid) {
		return saltReadService.querySaltByUUID(uuid);
	}
	
	public CardSaltDTO queryCardSaltByUUID(String uuid) {
		return cardSaltReadService.queryCardSaltByUUID(uuid);
	}

}
