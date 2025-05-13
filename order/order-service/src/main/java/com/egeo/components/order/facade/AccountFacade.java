package com.egeo.components.order.facade;

import java.math.BigDecimal;
import java.util.List;

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
import com.egeo.components.finance.dto.AccountBatchDTO;
import com.egeo.components.finance.dto.AccountFlowDTO;
import com.egeo.components.finance.dto.UserAccountDTO;
import com.egeo.exception.BusinessException;
import com.egeo.util.security.MD5Util;

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

	/**
	 * 查询订单退款流水
	 * @param orderId
	 * @return
	 */
	public List<AccountFlowDTO> queryOrderRefundFlow(Long orderId) {
		return afReadService.queryOrderRefundFlow(orderId);
	}

	/**
	 * 根据id查询批次
	 * @param batchId
	 * @return
	 */
	public AccountBatchDTO queryBatchById(Long batchId) {
		return abReadService.findAccountBatchById(batchId);
	}

	/**
	 * 根据订单id查询冻结积分
	 * @param orderId
	 * @return
	 */
	public BigDecimal queryFreezeFubiByOrderId(Long orderId) {
		return ffReadService.findSoFreezeBalanceBySoId(orderId);
	}

	/**
	 * 取消订单冻结的积分
	 * @param orderId
	 * @param ff
	 * @param long1 
	 */
	public void cancelOrderFreezeFubi(Long orderId, BigDecimal ff, Long userId) {
		//减少积分冻结账户余额,并完成密文赋值
		UserAccountDTO ua=userAccountReadService.queryUserAccountByUserIdAndType(userId, 2);
		if(ua==null)
			throw new BusinessException("用户积分冻结账户不存在");
		SaltDTO salt=saltReadService.querySaltByUUID(ua.getUuid());
		if(salt==null)
			throw new BusinessException("用户积分冻结账户加密信息有误,请联系管理员");
		boolean cipherValid=MD5Util.md5Valid(ua.getBalance().toString(), salt.getSaltValue(), ua.getCiphertext());
		if(!cipherValid)
			throw new BusinessException("用户积分冻结账户存在被篡改嫌疑,请联系管理员");
		//更新余额
		BigDecimal balance = ua.getBalance().subtract(ff);
		String cipher = MD5Util.MD5Salt(balance.toString(),salt.getSaltValue());
		// 根据用户积分冻结id修改冻结积分余额
		UserAccountDTO upCond = new UserAccountDTO();
		upCond.setId(ua.getId());
		upCond.setCiphertext(cipher);
		upCond.setBalance(balance);
		userAccountWriteService.updateUserAccountWithTx(upCond);
		//删除积分冻结
		ffWriteService.delBySoId(orderId);
	}
	
	
}
