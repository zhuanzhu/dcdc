package com.egeo.components.order.scheduler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.config.client.SaltClient;
import com.egeo.components.config.dto.SaltDTO;
import com.egeo.components.finance.client.AccountBatchClient;
import com.egeo.components.finance.client.CompanyAccountClient;
import com.egeo.components.finance.client.UserAccountClient;
import com.egeo.components.finance.constant.AccountConstant;
import com.egeo.components.finance.constant.FinBatchConstant;
import com.egeo.components.finance.constant.FlowTypeConstant;
import com.egeo.components.finance.dto.CashFlowAccountDTO;
import com.egeo.components.finance.dto.CompanyAccountDTO;
import com.egeo.components.finance.dto.UnifiedCashFlowDTO;
import com.egeo.components.finance.dto.UserAccountDTO;
import com.egeo.components.user.client.SendInfoClient;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.client.UserCookieClient;
import com.egeo.components.user.client.UserExtendClient;
import com.egeo.components.user.client.UserQuitClient;
import com.egeo.components.user.constant.InfoConstant;
import com.egeo.components.user.dto.InsertAndSendMessageDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.dto.UserQuitDTO;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;

@Component
public class UserQuitShedulerFacade {

	Logger logger = LoggerFactory.getLogger("UserQuitShedulerFacade");

	@Autowired
	private UserQuitClient userQuitReadService;

	@Autowired
	private UserQuitClient userQuitWriteService;

	@Autowired
	private UserExtendClient userExtendWriteService;

	@Autowired
	private UserExtendClient userExtendReadService;

	@Autowired
	private UserClient userWriteService;

	@Autowired
	private UserAccountClient userAccountReadService;

	@Autowired
	private CompanyAccountClient companyAccountReadService;

	@Autowired
	private AccountBatchClient accountBatchWriteService;

	@Autowired
	private SaltClient saltReadService;

	@Autowired
	private UserCookieClient userCookieReadService;

	@Autowired
	private UserCookieClient userCookieWriteService;

	@Autowired
	private UserClient userReadService;

	@Autowired
	private SendInfoClient sendInfoWriteService;

	@Autowired
	private JedisUtil cache;

	public void aboutUserInvalid() {
		// 查询所有离职员工数据
		List<UserQuitDTO> UserQuit = userQuitReadService.findUserQuitAll(new UserQuitDTO());

		List<UserQuitDTO> userAndCompanyIdQuit = new ArrayList<>();
		List<UserQuitDTO> userAndCompanyIdInvalid = new ArrayList<>();

		// 离职员工与资产回收员工数据分离
		findQuitAndInvalidUser(UserQuit, userAndCompanyIdQuit, userAndCompanyIdInvalid);

		// 更新user离职状态
		updateInvalidUserInfo(userAndCompanyIdQuit);
		// user资产回收
		userAccountRecyclingInfo(userAndCompanyIdInvalid);
		for (UserQuitDTO userQuit : userAndCompanyIdInvalid) {

			// 通过userId查询用户离职信息
			UserQuitDTO userQuitDTO = new UserQuitDTO();
			userQuitDTO.setUserId(userQuit.getUserId());
			List<UserQuitDTO> userQuitDTOList = userQuitReadService.findUserQuitAll(userQuitDTO);
			// 根据用户离职信息id删除用户离职信息表
			for (UserQuitDTO userQuitDTO_ : userQuitDTOList) {
				userQuitWriteService.deleteUserQuitWithTx(userQuitDTO_);
			}
			// 2018/7/18版本不失效用户只回收用户资产信息
			// 根据用户id查询用户cookie信息
			// UserCookieDTO userCookie = userCookieReadService.getbyUserId(userQuit.getUserId());
			// 根据cookie值删除缓存信息
			// cache.del(userCookie.getCookieValue());
			// 根据用户id删除用户cookie信息
			// userCookieWriteService.deleteCookieUserbyUserIdWithTx(userQuit.getUserId());
		}
	}
	/**
	 * user资产回收
	 * @param userAndCompanyIdInvalid
	 */
	private void userAccountRecyclingInfo(List<UserQuitDTO> userAndCompanyIdInvalid) {
		for (UserQuitDTO userQuit : userAndCompanyIdInvalid) {
			// 账户资金流动
			cashFlowAsQuit(userQuit);
		}

	}

	/**
	 * 根据用户id集合离职信息表
	 *
	 * @param userAndCompanyIdQuit
	 */
	private void updateInvalidUserInfo(List<UserQuitDTO> userAndCompanyIdQuit) {
		for (UserQuitDTO user : userAndCompanyIdQuit) {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getUserId());
			userDTO.setIsAvailable(0);
			// 离职时间
			userDTO.setQuitTime(new Date());
			userWriteService.updateUserInfoWithTx(userDTO);
		}
	}

	private void cashFlowAsQuit(UserQuitDTO userQuit) {

		UserAccountDTO userAccountDTO = new UserAccountDTO();
		userAccountDTO.setUserId(userQuit.getUserId());
		// 根据userId找到所有的用户账户
		List<UserAccountDTO> userAccountAll = userAccountReadService.findUserAccountAll(userAccountDTO);

		List<CashFlowAccountDTO> outFlowAccs = new ArrayList<>();
		// 遍历用户的四个账户
		for (UserAccountDTO userAccount : userAccountAll) {
			SaltDTO salt = saltReadService.querySaltByUUID(userAccount.getUuid());
			if (salt == null) {
				logger.error("id为" + userAccount.getId() + "的用户账户盐不存在,无法执行失效操作");
				return;
			}
			CashFlowAccountDTO cashFlowAccountDTO = new CashFlowAccountDTO();
			cashFlowAccountDTO.setAccountId(userAccount.getId());
			cashFlowAccountDTO.setSalt(salt.getSaltValue());
			cashFlowAccountDTO.setSum(userAccount.getBalance());
			cashFlowAccountDTO.setUserAccountType(userAccount.getType());
			cashFlowAccountDTO.setUserId(userQuit.getUserId());
			outFlowAccs.add(cashFlowAccountDTO);
		}
		CompanyAccountDTO inflowAccount = companyAccountReadService
				.queryNormalCompanyAccountByCompnayId(userQuit.getCompanyId());
		if(inflowAccount == null){
			logger.error("id为" + userQuit.getCompanyId() + "的公司账户不存在,无法执行员工失效操作");
			return;
		}
		List<CashFlowAccountDTO> inFlowAccs = new ArrayList<CashFlowAccountDTO>();
		CashFlowAccountDTO cashFlowAccountDTO = new CashFlowAccountDTO();
		cashFlowAccountDTO.setAccountId(inflowAccount.getId());

		SaltDTO companySalt = saltReadService.querySaltByUUID(inflowAccount.getUuid());
		if (companySalt == null) {
			logger.error("id为" + inflowAccount.getId() + "的盐不存在,无法执行员工失效操作");
			return;
		}
		cashFlowAccountDTO.setCompanyId(inflowAccount.getCompanyId());
		cashFlowAccountDTO.setSalt(companySalt.getSaltValue());
		inFlowAccs.add(cashFlowAccountDTO);

		Integer outFlowAccType = 1;
		Integer inFlowAccType = 0;
		accountBatchWriteService.unifiedCashFlow(new UnifiedCashFlowDTO(outFlowAccs, outFlowAccType, inFlowAccs, inFlowAccType, false,
				userQuit.getPlatformId(), FlowTypeConstant.UA_LEAVE.getStatus(), null, null, null,
				FinBatchConstant.USER_ACCOUNT_DISABLE, null, AccountConstant.FUBI_CHANGE_ACCOUNT_RECYCLE, false, 2));

		// 根据公司id查询该公司管理员信息集合
		List<String> userIds = userReadService.findUserIdsByCompanyId(userQuit.getCompanyId(), 1);
		// 拼接公司流动总金额
		BigDecimal sum = BigDecimal.valueOf(0.00);
		for (CashFlowAccountDTO cashFlowAccountDTO2 : outFlowAccs) {
			// 账户类型 0:积分账户 1:点赞福豆账户
			if(cashFlowAccountDTO2.getUserAccountType() == 0 || cashFlowAccountDTO2.getUserAccountType() == 1){
				sum.add(cashFlowAccountDTO2.getSum());
			}
		}
		List<Long> userIdss = new ArrayList<Long>() ;
		String userIdsss = "";
		if(userIds!=null) {
			for(String one :userIds) {
				userIdss.add(Long.valueOf(one));
				if(userIdsss.length()>0) {
					userIdsss+=",";
				}
				userIdsss +=one;
			}
		}
		// 发送公司积分变更消息
		Map<String, String> params = new HashMap<>();
		params.put("userIds", userIdsss);
		params.put("changeFuBi", sum + "");
		params.put("changeCause", AccountConstant.ADD_COMPANY_FUBI_CHANGE_USER_ACCOUNT_RECYCLE);
		InsertAndSendMessageDTO vo1 = new InsertAndSendMessageDTO();
		vo1.setParams(params);
		vo1.setInfoTemplateId(InfoConstant.COMPANY_FUBI_CHANGE_INFO_ID.getStatus());

		sendInfoWriteService.insertCompanyFuBiInfoAndSend(vo1);

		for (CashFlowAccountDTO cashFlowAccountDTO2 : outFlowAccs) {
			//获取流出账户的用户账户类型
			Integer userAccountType=cashFlowAccountDTO2.getUserAccountType();
			// 积分账户发送积分变动消息
			if(userAccountType==0) {
				// 发送积分变更消息
				if(EmptyUtil.isNotEmpty(cashFlowAccountDTO.getSum())) {
					Map<String, String> params2 = new HashMap<>();
					params2.put("userIds", userIdsss);
					params2.put("changeFuBi", cashFlowAccountDTO.getSum().negate() + "");
					params2.put("changeCause", AccountConstant.FUBI_CHANGE_ACCOUNT_RECYCLE);
					InsertAndSendMessageDTO vo2 = new InsertAndSendMessageDTO();
					vo2.setParams(params2);
					vo2.setInfoTemplateId(InfoConstant.USER_FUBI_CHANGE_INFO_ID.getStatus());
					vo2.setUserId(cashFlowAccountDTO.getUserId());
					sendInfoWriteService.insertUserFuBiInfoAndSend(vo2);
				}

			}
			if(userAccountType == 1){
				// 发送点赞福豆变更消息
				//if(EmptyUtil.isNotEmpty(cashFlowAccountDTO.getSum()))
					//sendInfoWriteService.insertUserPraiseFuBiInfoAndSend(InfoConstant.USER_PRAISE_FUBI_CHANGE_INFO_ID.getStatus(), cashFlowAccountDTO.getSum().negate(), AccountConstant.FUBI_CHANGE_ACCOUNT_RECYCLE, cashFlowAccountDTO.getUserId());
			}
		}
	}

	/**
	 * 设置userExtend的AccountStatus=0且取消管理员身份
	 *
	 * @param userAndCompanyIdInvalid
	 */
	private void updateQuitUserInfo(List<UserQuitDTO> userAndCompanyIdInvalid) {
		for (UserQuitDTO user : userAndCompanyIdInvalid) {
			UserExtendDTO userExtendDTO = new UserExtendDTO();
			userExtendDTO.setId(user.getUserId());
			// userExtendDTO.setStatus(2);
			userExtendDTO.setAccountStatus(1);
			// 设置其不是管理员
			userExtendDTO.setIsAdministrator(0);
			userExtendWriteService.updateUserExtendWithTx(userExtendDTO);
		}
	}

	/**
	 * 离职员工与资产回收员工数据分离
	 *
	 * @param UserQuit
	 * @param userAndCompanyIdQuit
	 * @param userAndCompanyIdInvalid
	 * @return
	 */
	private void findQuitAndInvalidUser(List<UserQuitDTO> UserQuit, List<UserQuitDTO> userAndCompanyIdQuit,
			List<UserQuitDTO> userAndCompanyIdInvalid) {
		// 找到到了离职日期和失效日期的员工的userId
		for (UserQuitDTO quitStaff : UserQuit) {
			if(EmptyUtil.isEmpty(quitStaff.getUserId())){
				logger.error("找到到了离职日期和失效日期的员工的userId时，userId为空了");
				continue;
			}
			UserDTO uDto = userReadService.findUserByID(quitStaff.getUserId());
			if(uDto ==null){
				logger.error("找到到了离职日期和失效日期的员工的userId时,未找到用户:{}",quitStaff.getUserId());
				continue;
			}
			if(quitStaff.getQuitTime() ==null || uDto.getIsAvailable()==null){
				logger.error("找到到了离职日期和失效日期的员工的userId时:{},离职时间{}或是否在职{}为空",quitStaff.getUserId(),quitStaff.getQuitTime(),uDto.getIsAvailable());
				continue;
			}
			Date quitTime = quitStaff.getQuitTime();

			// 离职标志(已到达离职时间且尚未离职)
			boolean quitFlag = System.currentTimeMillis() >= quitTime.getTime() && uDto.getIsAvailable() == 1;
			if (quitFlag) {
				userAndCompanyIdQuit.add(quitStaff);
			}

			Date invalidTime = quitStaff.getInvalidTime();
			// 资产回收标志(已达到资产回收日期)
			boolean invalidFlag = System.currentTimeMillis() >= invalidTime.getTime();
			if (invalidFlag) {
				userAndCompanyIdInvalid.add(quitStaff);
			}
		}
	}

}
