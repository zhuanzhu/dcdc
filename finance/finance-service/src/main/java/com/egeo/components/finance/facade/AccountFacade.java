package com.egeo.components.finance.facade;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.order.dto.*;
import com.egeo.components.promotion.client.BuyCardClient;
import com.egeo.components.promotion.dto.UserCardRecordDTO;
import com.egeo.components.third.client.ChannelServiceConfigClient;
import com.egeo.components.third.dto.RemoteExecuteDTO;
import com.egeo.components.third.enums.ChannelServiceNameEnum;
import com.egeo.components.third.enums.ChannelServiceTypeEnum;
import com.egeo.components.user.enums.UserChannelSourceEnum;
import com.egeo.web.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.common.BusinessExceptionConstant;
import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.config.client.PaymentCodeSaltClient;
import com.egeo.components.config.client.SaltClient;
import com.egeo.components.config.dto.CompanyConfigDTO;
import com.egeo.components.config.dto.SaltDTO;
import com.egeo.components.finance.business.AccountManage;
import com.egeo.components.finance.common.BusinessException;
import com.egeo.components.finance.constant.AccountConstant;
import com.egeo.components.finance.constant.FinBatchConstant;
import com.egeo.components.finance.constant.FlowTypeConstant;
import com.egeo.components.finance.dto.AccountBatchDTO;
import com.egeo.components.finance.dto.AccountBatchTmpDTO;
import com.egeo.components.finance.dto.AccountFlowDTO;
import com.egeo.components.finance.dto.AdjustReasonDTO;
import com.egeo.components.finance.dto.CashFlowAccountDTO;
import com.egeo.components.finance.dto.CashFlowResultDTO;
import com.egeo.components.finance.dto.CompanyAccountDTO;
import com.egeo.components.finance.dto.ReasonCompanyDTO;
import com.egeo.components.finance.dto.SoFreezeFubiDTO;
import com.egeo.components.finance.dto.TempRechargeDTO;
import com.egeo.components.finance.dto.UserAccountDTO;
import com.egeo.components.finance.service.read.AccountBatchReadService;
import com.egeo.components.finance.service.read.AccountBatchTmpReadService;
import com.egeo.components.finance.service.read.AccountFlowReadService;
import com.egeo.components.finance.service.read.AdjustReasonReadService;
import com.egeo.components.finance.service.read.CompanyAccountReadService;
import com.egeo.components.finance.service.read.ReasonCompanyReadService;
import com.egeo.components.finance.service.read.SoFreezeFubiReadService;
import com.egeo.components.finance.service.read.TempRechargeReadService;
import com.egeo.components.finance.service.read.UserAccountReadService;
import com.egeo.components.finance.service.write.AccountBatchTmpWriteService;
import com.egeo.components.finance.service.write.AccountBatchWriteService;
import com.egeo.components.finance.service.write.AccountFlowWriteService;
import com.egeo.components.finance.service.write.AdjustReasonWriteService;
import com.egeo.components.finance.service.write.CompanyAccountWriteService;
import com.egeo.components.finance.service.write.SoFreezeFubiWriteService;
import com.egeo.components.finance.service.write.TempRechargeWriteService;
import com.egeo.components.finance.service.write.UserAccountWriteService;
import com.egeo.components.order.client.CardClient;
import com.egeo.components.order.client.MerchantProdSalesRecordCoreClient;
import com.egeo.components.order.client.SoChildClient;
import com.egeo.components.order.client.SoClient;
import com.egeo.components.order.client.SoItemClient;
import com.egeo.components.order.client.SoRefundClient;
import com.egeo.components.pay.client.AwaitQueueClient;
import com.egeo.components.pay.enums.OrderConstant;
import com.egeo.components.product.client.CommodityProductUnitClient;
import com.egeo.components.product.client.MembershipUserClient;
import com.egeo.components.promotion.client.CouponUnitClient;
import com.egeo.components.promotion.client.ExchangeOrderRecordClient;
import com.egeo.components.promotion.dto.ExchangeOrderRecordDTO;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.client.CompanyCoreClient;
import com.egeo.components.user.client.CompanyUserDisabledClient;
import com.egeo.components.user.client.SendInfoClient;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.client.UserExtendClient;
import com.egeo.components.user.client.UserRoleClient;
import com.egeo.components.user.constant.InfoConstant;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.CompanyUserDisabledDTO;
import com.egeo.components.user.dto.InsertAndSendMessageDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.dto.UserRoleDTO;
import com.egeo.components.user.vo.InsertOrderPayStatusInfoAndSendVO;
import com.egeo.config.RuntimeContext;
import com.egeo.dto.HttpServletRequestDTO;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.util.security.MD5Support;
import com.egeo.util.security.MD5Util;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.SpringContextTool;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.http.HttpClientUtil;
import com.egeo.utils.log.XLogger;
import com.egeo.utils.pay.PayUtil;

@Component
public class AccountFacade {

	private static final XLogger logger = XLogger.getLogger(AccountFacade.class);

	@Autowired
	private AccountBatchReadService accountBatchReadService;

	@Autowired
	private AccountBatchWriteService accountBatchWriteService;

	@Autowired
	private AccountFlowReadService accountFlowReadService;

	@Autowired
	private AccountFlowWriteService accountFlowWriteService;

	@Autowired
	private AdjustReasonReadService adjustReasonReadService;

	@Autowired
	private AdjustReasonWriteService adjustReasonWriteService;

	@Autowired
	private CompanyAccountReadService companyAccountReadService;

	@Autowired
	private CompanyAccountWriteService companyAccountWriteService;

	@Autowired
	private UserAccountReadService userAccountReadService;

	@Autowired
	private TempRechargeReadService tempRechargeReadService;

	@Autowired
	private TempRechargeWriteService tempRechargeWriteService;

	@Autowired
	private AccountBatchTmpWriteService accountBatchTmpWriteService;

	@Autowired
	private AccountBatchTmpReadService accountBatchTmpReadService;

	@Autowired
	private SaltClient saltReadService;

	@Autowired
	private SoClient soReadService;

	@Autowired
	private UserAccountWriteService userAccountWriteService;

	@Autowired
	private SoClient soWriteService;
	@Autowired
	private SoChildClient soChildReadService;

	@Autowired
	private UserClient userReadService;

	@Autowired
	private SoItemClient soItemReadService;
	@Resource(name = "account")
	private AccountManage accountManage;
	@Autowired
	private UserExtendClient userExtService;
/*
	@Autowired
	private ECardClient eCardWriteService;

	@Autowired
	private ECardClient eCardReadService;*/

	@Autowired
	private SoFreezeFubiWriteService soFreezeFubiWriteService;

	@Autowired
	private SoFreezeFubiReadService soFreezeFubiReadService;
/*
	@Autowired
	private AwaitQueueClient awaitQueueReadService;

	@Autowired
	private MerchantProductVirtualStockClient merchantProductVirtualStockWriteService;

	@Autowired
	private CommodityProductUnitWarehouseStockClient commodityProductUnitWarehouseStockWriteService;*/

	@Autowired
	private CommodityProductUnitClient commodityProductUnitReadService;
/*
	@Autowired
	private MerchantProdSalesRecordClient merchantProdSalesRecordWriteService;

	@Autowired
	private MerchantProdSalesRecordClient merchantProdSalesRecordReadService;
*/
	@Autowired
	private PaymentCodeSaltClient paymentCodeSaltReadService;

	@Autowired
	private AwaitQueueClient awaitQueueWriteService;
/*
	@Autowired
	private SoChildClient soChildWriteService;*/

	@Autowired
	private ReasonCompanyReadService reasonCompanyReadService;

	@Autowired
	private CouponUnitClient couponUnitWriteService;

	@Autowired
	private SoRefundClient soRefundReadService;

	@Autowired
	private SoClient soService;

	@Autowired
	private CompanyClient companyReadService;

	@Autowired
	private CompanyCoreClient companyCoreReadService;

	@Autowired
	private CardClient cardWriteService;

	@Autowired
	private MerchantProdSalesRecordCoreClient merchantProdSalesRecordCoreWriteService;

	@Autowired
	private SendInfoClient sendInfoWriteService;/*
	@Autowired
	private MembershipClient membershipReadService;*/
	@Autowired
	private MembershipUserClient membershipUserWriteService;

	@Autowired
	private ExchangeOrderRecordClient exchangeOrderRecordWriteService;

	@Autowired
	private CompanyUserDisabledClient companyUserDisabledReadService;

	@Autowired
	private CompanyUserDisabledClient companyUserDisabledWriteService;

	@Autowired
	private ExchangeOrderRecordClient exchangeOrderRecordReadService;

	@Autowired
	private UserRoleClient userRoleReadService;/*
	@Autowired
	private JedisUtil jedisUtil;*/

	@Autowired
	private UserExtendClient userExtendWriteService;

	@Autowired
	private ChannelServiceConfigClient channelServiceConfigClient;

	/**
	 * 查询公司账户分页列表
	 *
	 * @param page
	 * @param accountName
	 * @param companyId
	 * @param disabled
	 * @return
	 */
	public PageResult<CompanyAccountDTO> queryCompanyAccountPage(Pagination page, String accountName, List<Long> companyId,
			Integer disabled,Long platformId) {

		return companyAccountReadService.queryCompanyAccountPage(page, accountName, companyId, disabled,platformId);
	}

	/**
	 * 查询所有调整原因
	 *
	 * @return
	 */
	public List<AdjustReasonDTO> queryAdjustReasons(Long companyId) {
		return adjustReasonReadService.queryAdjustReasons(companyId);
	}

	/**
	 * 查询所有普通公司账户
	 *
	 * @param platformId
	 * @return
	 */
	public List<CompanyAccountDTO> queryNormalAccounts(Long platformId,List<Long> companyId) {
		return companyAccountReadService.queryNormalAccounts(platformId,companyId);
	}

	/**
	 * 根据财务批次号查询批次
	 *
	 * @param finBatch
	 * @return
	 */
	public AccountBatchDTO queryAccountBatchByFinBatch(String finBatch) {
		return accountBatchReadService.queryAccountBatchByFinBatch(finBatch);
	}

	/**
	 * 充值/调整提交审核 创建一条批次和两条明细
	 *
	 * @param accountId
	 * @param sum
	 * @param reasonId
	 * @param finBatch
	 * @param remark
	 * @param platformId
	 * @param userId
	 * @param companyId
	 * @param type
	 *
	 * @return raBatch批次号
	 */
	public Long raSubmitForExam(Long accountId, Double sum, Long reasonId, String finBatch, String remark,
			Long platformId, Long userId, Long companyId, int type) {
		// 查询迩格积分账户
		CompanyAccountDTO fubiOutAccount = companyAccountReadService.querySpecialCompanyAccountByType(platformId,0);
		// 批次对象
		AccountBatchTmpDTO ab = new AccountBatchTmpDTO();
		ab.setOutflowAccountid(fubiOutAccount.getId());
		ab.setStatus(0);
		ab.setType(type);
		ab.setReasonId(reasonId);
		ab.setRemark(remark);
		ab.setStatus(0);
		ab.setCompanyId(companyId);
		ab.setInflowAccountid(accountId);
		ab.setOperatorId(userId);
		ab.setFinBatch(finBatch);
		BigDecimal sumPos = new BigDecimal(sum);
		ab.setSum(sumPos);
		ab.setPlatformId(platformId);
		Long batchId = accountBatchTmpWriteService.insertAccountBatchTmpWithTx(ab);
		return batchId;
	}

	/**
	 * 根据公司id查询公司账户id
	 *
	 * @param id
	 * @return
	 */
	public CompanyAccountDTO queryCompanyAccountById(Long id) {
		CompanyAccountDTO condition = new CompanyAccountDTO();
		condition.setId(id);
		return companyAccountReadService.findCompanyAccountById(condition);
	}

	/**
	 * 更改账户有效性为有效
	 *
	 * @param
	 */
	public int enableCompanyAccount(Long id , Long companyId) {
		//判断企业账号是否为有效
		CompanyDTO dto = companyReadService.findCompanyById(companyId);
		logger.info("公司状态："+dto.getStatus());
		if (dto.getStatus() == 1 ) {
			return 2;
		}
		// 判断员工账号是否恢复
		List<Long> userIds = com.egeo.utils.StringUtils.stringsToLongs(companyUserDisabledReadService.findUsersByCompanyId(companyId));
		if (EmptyUtil.isNotEmpty(userIds)) {
			Integer revalidation = companyUserDisabledReadService.findRevalidationByCompanyId(companyId);
			if (revalidation == 1) {
				return 1;
			}
		}
		//员工账户未恢复仅更改公司账户有效性
		companyAccountWriteService.updateAccountDisable(id, 0);
		//失效前金额清零
		CompanyAccountDTO companyAccountDTO = new CompanyAccountDTO();
		companyAccountDTO.setBeforeDisableBalance(new BigDecimal(0.00));
		companyAccountDTO.setId(id);
		companyAccountWriteService.updateCompanyAccountWithTx(companyAccountDTO);

		List<Long> ids = com.egeo.utils.StringUtils.stringsToLongs(companyUserDisabledReadService.findUsersByCompanyId(companyAccountDTO.getCompanyId()));
		if (EmptyUtil.isNotEmpty(ids)) {
			for (Long userID : ids) {
				userAccountWriteService.updateBeforeDisableBalanceWithTx(userID);
			}
		}
		return 0;
	}

	/**
	 * 不恢复企业和员工账户金额
	 * @param id
	 */
	public void doNotRecoverAccountBalance(Long id ) {
		//不恢复 仅切换企业账户为有效
		companyAccountWriteService.updateAccountDisable(id, 0);
		//失效前金额清零
		CompanyAccountDTO companyAccountDTO = new CompanyAccountDTO();
		companyAccountDTO.setBeforeDisableBalance(new BigDecimal(0.00));
		companyAccountDTO.setId(id);
		companyAccountWriteService.updateCompanyAccountWithTx(companyAccountDTO);

		List<Long> ids = com.egeo.utils.StringUtils.stringsToLongs(companyUserDisabledReadService.findUsersByCompanyId(companyAccountDTO.getCompanyId()));
		if (EmptyUtil.isNotEmpty(ids)) {
			for (Long userID : ids) {
				userAccountWriteService.updateBeforeDisableBalanceWithTx(userID);
			}
		}
	}

	/**
	 * 恢复企业和员工账户金额
	 * @param id
	 * @return
	 */
	public int recoverAccountBalance(Long id,Long userId,Long platformId) {
		// 调用统一资产流动接口，将失效前的企业账户金额从迩格的账户转回企业账户
		CompanyAccountDTO dto = new CompanyAccountDTO();
		dto.setId(id);
		CompanyAccountDTO ca = companyAccountReadService.findCompanyAccountById(dto);

		// 查询公司账户及盐
		String salt = saltReadService.querySaltByUUID(ca.getUuid()).getSaltValue();
		// 查询公司员工账户
		List<Long> ids = com.egeo.utils.StringUtils.stringsToLongs(companyUserDisabledReadService.findUsersByCompanyId(ca.getCompanyId()));
		BigDecimal allBalance = ca.getBeforeDisableBalance();
		if (EmptyUtil.isNotEmpty(ids)) {
			for (Long theId : ids) {
				if (theId != -1L) {
					BigDecimal beforeDisabledBalance = userAccountReadService.findBeforeDisabledBalance(theId);
					allBalance = allBalance.add(beforeDisabledBalance);
				}
			}
		}
		// 查询迩格积分发放账户及盐
		CompanyAccountDTO fubiGrantAcc = companyAccountReadService.querySpecialCompanyAccountByType(platformId,0);
		String grantAcsSalt = saltReadService.querySaltByUUID(fubiGrantAcc.getUuid()).getSaltValue();

		List<CashFlowAccountDTO> outFlowAccs_ = new ArrayList<>();
		CashFlowAccountDTO outFlowAcc = new CashFlowAccountDTO();
		outFlowAcc.setAccountId(fubiGrantAcc.getId());
		outFlowAcc.setSalt(grantAcsSalt);
		outFlowAcc.setSum(allBalance);
		outFlowAccs_.add(outFlowAcc);

		List<CashFlowAccountDTO> inFlowAccs_ = new ArrayList<>();
		CashFlowAccountDTO inFlowAcc = new CashFlowAccountDTO();
		inFlowAcc.setAccountId(id);
		inFlowAcc.setSalt(salt);
		inFlowAcc.setSum(allBalance);
		inFlowAccs_.add(inFlowAcc);
		accountBatchWriteService.unifiedCashFlow(outFlowAccs_, 0, inFlowAccs_, 0, true, platformId,
				FlowTypeConstant.CA_RECOVER.getStatus(), null, null, userId, FinBatchConstant.COMPANY_DESABLE_RECOVER, null,
				null, false, 0);

		// 将员工账户改为有效
		// 查询企业失效前所有在职用户id
		//List<Long> userIds = companyUserDisabledReadService.findUsersByCompanyId(ca.getCompanyId());
		if (EmptyUtil.isEmpty(ids) || ids.size() == 0) {
			//仅账户设为无效,账号有效
			UserDTO userDTO = new UserDTO();
			userDTO.setCompanyId(ca.getCompanyId());
			userDTO.setIsAvailable(1);
			List<UserDTO> users = userReadService.findUser(userDTO);
			for (UserDTO user : users) {
				if (user.getId() != -1L){
					ids.add(user.getId());
				}
			}
		}else {
			for (Long userID : ids) {
				userAccountWriteService.updateUserAccountDisabled(userID,0);
			}
		}

		if (EmptyUtil.isNotEmpty(ids) && ids.size() != 1) {
			List<UserAccountDTO> uas = userAccountReadService.queryUserAccountByUserIds(ids);
			// 将失效前的用户账户金额从企业账户转回各个员工账户内
			List<CashFlowAccountDTO> inFlowAccs = new ArrayList<>();
			// 员工账户
			for (UserAccountDTO ua : uas) {
				int uaType = ua.getType().intValue();
				CashFlowAccountDTO cfa = new CashFlowAccountDTO();
				if (ua.getType() == 0 || ua.getType() == 1) {
					cfa.setAccountId(ua.getId());
					cfa.setSum(ua.getBeforeDisableBalance());
					cfa.setUserAccountType(uaType);
					// 查询盐
					salt = saltReadService.querySaltByUUID(ua.getUuid()).getSaltValue();
					cfa.setSalt(salt);
					inFlowAccs.add(cfa);
				}
			}

			// 查询公司账户
			ca = companyAccountReadService.findCompanyAccountById(dto);
			salt = saltReadService.querySaltByUUID(ca.getUuid()).getSaltValue();
			CashFlowAccountDTO ccfa = new CashFlowAccountDTO();
			ccfa.setSalt(salt);
			ccfa.setAccountId(id);
			// 设置公司积分账户为流出账户
			List<CashFlowAccountDTO> outFlowAccs = new ArrayList<>();
			outFlowAccs.add(ccfa);
			// 调用统一资产流动接口，回收员工资产至公司积分账户
			if (outFlowAccs.size() > 0) {
				accountBatchWriteService.unifiedCashFlow(outFlowAccs, 0, inFlowAccs, 1, false, platformId,
						FlowTypeConstant.UP_RECOVER.getStatus(), null, null, userId,
						FinBatchConstant.USER_ACCOUNT_DISABLE_RECOVER, null, null, false, 1);

			}
		}
		// 企业账户设为有效
		companyAccountWriteService.updateAccountDisable(id, 0);

		//失效前金额清零
		CompanyAccountDTO companyAccountDTO = new CompanyAccountDTO();
		companyAccountDTO.setBeforeDisableBalance(new BigDecimal(0.00));
		companyAccountDTO.setId(id);
		companyAccountWriteService.updateCompanyAccountWithTx(companyAccountDTO);

		if (EmptyUtil.isNotEmpty(ids)) {
			for (Long userID : ids) {
				userAccountWriteService.updateBeforeDisableBalanceWithTx(userID);
			}
		}

		//清除失效员工记录
		CompanyUserDisabledDTO companyUserDisabledDTO = new CompanyUserDisabledDTO();
		companyUserDisabledDTO.setCompanyId(ca.getCompanyId());
		companyUserDisabledWriteService.deleteCompanyUserDisabledWithTx(companyUserDisabledDTO);

		return 0;
	}
	/**
	 * 查询账户批次分页列表 (后台账户审核页面使用)
	 *
	 * @param batchNo
	 * @param page
	 * @param keyWord
	 * @param companyId
	 * @param status
	 * @param type
	 * @return
	 */
	public PageResult<AccountBatchDTO> queryAccountBatchPage(Long accountId, String batchNo, Pagination page,
			String keyWord, Long companyId, Integer type, Integer status,Long platformId) {

		return accountBatchReadService.queryAccountBatchPage(accountId, batchNo, page, keyWord, companyId, type,
				status,platformId);
	}

	// public boolean check(){
	// return xxxUtil.xxFunction(t0,t1,t2,t3,type,thirdPartyReadService);
	// }

	/**
	 * 批量查询公司账户
	 *
	 * @param accountIds
	 * @return
	 */
	public List<CompanyAccountDTO> queryCompanyAccountsByIds(List<Long> accountIds) {

		return companyAccountReadService.queryCompanyAccountsByIds(accountIds);
	}

	/**
	 * 批次审核通过/不通过
	 *
	 * @param id
	 * @param pass
	 * @return
	 */
	public int batchExam(Long platformId,Long id, boolean pass, String outflowSalt) {

		return accountBatchWriteService.batchExam(platformId,id, pass, outflowSalt);
	}

	/**
	 * 根据批次id查询批次
	 *
	 * @param id
	 * @return
	 */
	public AccountBatchDTO queryAccountBatchById(Long id) {
		return accountBatchReadService.findAccountBatchById(id);
	}

	public CompanyAccountDTO querySpecialCompanyAccountByType(Long platformId,int type) {
		return companyAccountReadService.querySpecialCompanyAccountByType(platformId,type);
	}

	/**
	 * 查询批次流水分页列表
	 *
	 * @param batchId
	 * @param outflowAccount
	 * @param inflowAccount
	 * @param startTime
	 * @param endTime
	 * @param page
	 * @return
	 */
	public PageResult<AccountFlowDTO> queryAccountFlowPage(Long batchId, String outflowAccount, String inflowAccount,
			Long startTime, Long endTime, Long platformId, Pagination page) {
		return accountFlowReadService.queryAccountFlowPage(batchId, outflowAccount, inflowAccount, startTime, endTime,
				platformId,page);
	}

	/**
	 * 查询用户所有账户
	 *
	 * @param userId
	 * @return
	 */
	public List<UserAccountDTO> queryUserAccountByUserId(Long userId) {
		return userAccountReadService.queryUserAccountByUserId(userId);
	}

	/**
	 * 新建原因,并建立与公司的关系
	 *
	 * @param dto
	 * @param cIds
	 * @param cIds
	 * @return
	 */
	public Long createAdjustReason(AdjustReasonDTO dto, List<Long> cIds) {
		return adjustReasonWriteService.createAdjustReason(dto, cIds);
	}

	/**
	 * 编辑原因
	 *
	 * @param dto
	 */
	public int updateAdjustReason(AdjustReasonDTO dto) {
		return adjustReasonWriteService.updateAdjustReasonWithTx(dto);
	}

	/**
	 * 原因分页列表
	 *
	 * @param type
	 * @param companyId
	 * @param disabled
	 * @param page
	 * @return
	 */
	public PageResult<AdjustReasonDTO> queryAdjustReasonPage(Integer type, Long companyId, Integer disabled,
			Long platformId, Pagination page) {

		return adjustReasonReadService.queryAdjustReasonPage(type, companyId, disabled,platformId, page);
	}

	/**
	 * 查询用户的某个账户
	 *
	 * @param id
	 * @return
	 */
	public UserAccountDTO queryUserAccountByUserIdAndType(Long id, Integer type) {

		UserAccountDTO ua = userAccountReadService.queryUserAccountByUserIdAndType(id, type);
		if (ua == null) {
			return null;
		}
		UserExtendDTO extDTO = userExtService.userByUserId(id);
		List<CompanyConfigDTO> companyConfigs = userReadService.findUserCompanyConfigs(id);
		boolean isDlf = false;
		BigDecimal fanka =  new BigDecimal(0);
		BigDecimal jidian =  new BigDecimal(0);
		if(null !=extDTO && EmptyUtil.isNotEmpty(extDTO.getChannelSource()) && Objects.equals(extDTO.getChannelSource(), UserChannelSourceEnum.DLF.getChannelSource()) && (ua.getType().intValue()==0 || ua.getType().intValue()==5)){
			isDlf = true;
			RemoteExecuteDTO executeDTO = new RemoteExecuteDTO();
			executeDTO.setEnterpriseId(extDTO.getCompanyId().intValue());
			executeDTO.setChannelCode(extDTO.getChannelSource());
			executeDTO.setChannelServiceName(ChannelServiceNameEnum.USER_CASH.getChannelServiceName());
			executeDTO.setChannelServiceType(ChannelServiceTypeEnum.REQ.getChannelServiceType());
			executeDTO.setBizCode(extDTO.getMobile());
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("mobile",extDTO.getMobile());
			executeDTO.setJsonString(JSON.toJSONString(jsonObject));
			JsonResult jsonResult = channelServiceConfigClient.remoteExecute(executeDTO);
			if(jsonResult !=null && jsonResult.getData() !=null && jsonResult.getCode()==0){
				JSONObject parseObject =JSONObject.parseObject(JSON.toJSONString(jsonResult.getData()));
				if("1".equals(parseObject.getString("status"))){
					JSONObject dataJSONObject = parseObject.getJSONObject("data");
					fanka = dataJSONObject.getBigDecimal("fanka");
					jidian = dataJSONObject.getBigDecimal("jidian");
				}
			}
		}
		boolean isYd = false;
		if(null !=extDTO && EmptyUtil.isNotEmpty(extDTO.getChannelSource()) && Objects.equals(extDTO.getChannelSource(), UserChannelSourceEnum.YD.getChannelSource()) && ua.getType().intValue()==0){
			isYd = true;
			UserDTO userDTO = userReadService.findUserByID(id);
			RemoteExecuteDTO executeDTO = new RemoteExecuteDTO();
			executeDTO.setEnterpriseId(extDTO.getCompanyId().intValue());
			executeDTO.setChannelCode(extDTO.getChannelSource());
			executeDTO.setChannelServiceName(ChannelServiceNameEnum.USER_CASH.getChannelServiceName());
			executeDTO.setChannelServiceType(ChannelServiceTypeEnum.REQ.getChannelServiceType());
			executeDTO.setBizCode(extDTO.getMobile());
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("user_id",userDTO.getChannelUserUnique());
			executeDTO.setJsonString(JSON.toJSONString(jsonObject));
			JsonResult jsonResult = channelServiceConfigClient.remoteExecute(executeDTO);
			if(jsonResult !=null && jsonResult.getData() !=null && jsonResult.getCode()==0){
				JSONObject parseObject =JSONObject.parseObject(JSON.toJSONString(jsonResult.getData()));
				if("1".equals(parseObject.getString("status"))){
					JSONObject dataJSONObject = parseObject.getJSONObject("data");
					fanka = dataJSONObject.getBigDecimal("money");
				}
			}
		}
		if (ua.getType().intValue() == 0) {
			String name="积分";
			String url = "";
			Integer account0Type = 0;
			for(CompanyConfigDTO config : companyConfigs) {
				if(config.getKey().equalsIgnoreCase("account.0.name")) {
					name = config.getValue();
				}else if(config.getKey().equalsIgnoreCase("account.0.url")) {
					url = config.getValue();
				}else if(config.getKey().equalsIgnoreCase("account.0.type")) {
					account0Type = Integer.valueOf(config.getValue());
				}
			}
			if(account0Type.intValue()==1) {

				BigDecimal defaultBalance = new BigDecimal(0);
				ua.setBalance(defaultBalance);
				if(extDTO!=null&&extDTO.getRemark()!=null && !isDlf && !isYd) {
					String jsonStr = HttpClientUtil.doGet(url+extDTO.getRemark());
					JSONObject parseObject = JSONObject.parseObject(jsonStr);
					if(parseObject.containsKey("code") && parseObject.getInteger("code").intValue()==0) {
						JSONObject parseData = parseObject.getJSONObject("data");
						if(parseData.containsKey("balance") && parseData.getString("balance")!=null && parseData.getBigDecimal("balance")!=null) {
							BigDecimal thirdBalance = parseData.getBigDecimal("balance");
							ua.setBalance(thirdBalance);
						}
					}

				}else if(isDlf){
					ua.setName("餐卡");
					ua.setBalance(fanka);
				}else if(isYd){
					ua.setName(name);
					ua.setBalance(fanka);
				}

			}else if(isDlf){
				ua.setName("餐卡");
				ua.setBalance(fanka);
			}else if(isYd){
				ua.setName(name);
				ua.setBalance(fanka);
			}

		}else if(ua.getType().intValue() ==5){
			ua.setName("积点");
			ua.setBalance(jidian);
		}
		return ua;

	}

	/**
	 * 根据公司id查询公司普通账户
	 *
	 * @param companyId
	 * @return
	 */
	public CompanyAccountDTO queryNormalCompanyAccountByCompnayId(Long companyId) {

		return companyAccountReadService.queryNormalCompanyAccountByCompnayId(companyId);
	}

	/**
	 * 根据sn从草稿中查询本次总金额
	 *
	 * @param sn
	 * @return
	 */
	public double queryTempRechargeSummaryBySn(String sn) {
		return tempRechargeReadService.queryTempRechargeSummaryBySn(sn);
	}

	/**
	 * 查询一周内sum为sum的导入
	 *
	 * @param sum
	 * @return
	 */
	public List<AccountBatchDTO> querySameSumBatchInWeek(double sum) {
		return accountBatchReadService.querySameSumBatchInWeek(sum);
	}

	/**
	 * 根据sn查询草稿列表
	 *
	 * @param sn
	 * @return
	 */
	public List<TempRechargeDTO> queryTempRechargeBySn(String sn) {
		return tempRechargeReadService.queryTempRechargeBySn(sn);
	}

	/**
	 * 根据id查询原因
	 *
	 * @return
	 */
	public AdjustReasonDTO queryAdjustReasonById(Long id) {
		AdjustReasonDTO condition = new AdjustReasonDTO();
		condition.setId(id);
		return adjustReasonReadService.findAdjustReasonById(condition);
	}

	/**
	 * 查询当前sn在草稿中的记录数量,进而判断sn的重复性
	 *
	 * @param sn
	 * @return
	 */
	public int queryTempRechargeCountBySn(String sn) {
		return tempRechargeReadService.queryTempRechargeCountBySn(sn);
	}

	public int batchInsertTempRecharge(List<TempRechargeDTO> batchInsertList) {
		return tempRechargeWriteService.batchInsertTempRecharge(batchInsertList);
	}

	/**
	 * 批量操作公司账户充值至用户账户
	 *
	 * @param trList
	 * @param companySalt
	 * @return
	 */
	public int ca2uaBatchUpdate(List<TempRechargeDTO> trList, String companySalt, CompanyAccountDTO ca,
			BigDecimal summary, String finBatch, Long operatorId, Long reasonId, String remark, Integer accountType) {
		String batchNo = "";
		return tempRechargeWriteService.ca2uaBatchUpdate(trList, companySalt, ca, summary, batchNo, finBatch,
				operatorId, reasonId, remark, accountType);

	}

	/**
	 * 根据id查询用户账户id
	 *
	 * @param accountId
	 * @return
	 */
	public UserAccountDTO queryUserAccountById(Long accountId) {
		UserAccountDTO condition = new UserAccountDTO();
		condition.setId(accountId);
		return userAccountReadService.findUserAccountById(condition);
	}

	/**
	 * 查询账户流水分页列表
	 *
	 * @param page
	 * @param accountId
	 * @param mode
	 *            查询模式 null全部模式 0:收入模式 1:支出模式
	 * @return
	 */
	public PageResult<AccountFlowDTO> queryAccountFlowPageByAccountId(Pagination page, Long accountId, Integer mode) {
		return accountFlowReadService.queryAccountFlowPageByAccountId(page, accountId, mode);
	}

	/**
	 * 根据财务批次号查询账户批次草稿
	 *
	 * @param finBatch
	 * @return
	 */
	public AccountBatchTmpDTO queryAccountBatchTmpByFinBatch(String finBatch) {
		return accountBatchTmpReadService.queryAccountBatchTmpByFinBatch(finBatch);
	}

	/**
	 * 查询账户批次草稿分页列表(仅待审核和未通过)
	 *
	 * @param page
	 * @param keyWord
	 * @param companyId
	 * @param status
	 * @param type
	 * @return
	 */
	public PageResult<AccountBatchTmpDTO> queryAccountBatchTmpPage(Pagination page, String keyWord, Long companyId,
			Integer status, Integer type,Long platformId) {
		return accountBatchTmpReadService.queryAccountBatchTmpPage(page, keyWord, companyId, status, type,platformId);
	}

	public CashFlowResultDTO unifiedCashFlow(List<CashFlowAccountDTO> outFlowAccs, Integer outFlowAccType,
			List<CashFlowAccountDTO> inFlowAccs, Integer inFlowAccType, boolean nonNegLimit, Long platformId,
			Integer type, Long orderId, String orderCode, Long operatorId, int finBatchType, Long reasonId,
			String remark, boolean isRecharge, Integer operateType) {
		CashFlowResultDTO cashFlowResultDTO = accountBatchWriteService.unifiedCashFlow(outFlowAccs, outFlowAccType, inFlowAccs, inFlowAccType,
				nonNegLimit, platformId, type, orderId, orderCode, operatorId, finBatchType, reasonId, remark,
				isRecharge, operateType);
		if(outFlowAccType == 0){
			// 流入账户类型是公司(公司余额充值)
			if(inFlowAccType == 0){
				for (CashFlowAccountDTO cashFlowAccountDTO : outFlowAccs) {
					// 根据公司id查询该公司管理员信息集合
					List<Long> userIds = com.egeo.utils.StringUtils.stringsToLongs(userReadService.findUserIdsByCompanyId(cashFlowAccountDTO.getCompanyId(), 1));
					// 发送公司积分变更消息
					/***********发送消息*********/
					Map<String, String> params = new HashMap<>();
					params.put("变动金额", cashFlowAccountDTO.getSum() + "");
					params.put("变动原因内容", remark);
					params.put("年月日时分", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
					CompanyAccountDTO oca = new CompanyAccountDTO();
					oca.setId(cashFlowAccountDTO.getAccountId());
					CompanyAccountDTO oca1 = companyAccountReadService.findCompanyAccountById(oca);
					params.put("账户余额", oca1.getBalance() + "");

					Long infoTemplateId;
					if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(platformId)) {
						infoTemplateId = InfoConstant.MYY_COMPANY_BALANCE_DECREASE.getStatus();
						params.put("平台名称", "富宏云采");
					} else {
						infoTemplateId = InfoConstant.FGJ_COMPANY_BALANCE_DECREASE.getStatus();
						params.put("平台名称", "大厨管家");
					}
					for (Long userId : userIds) {
						sendInfoWriteService.insertAndSendMessage(new InsertAndSendMessageDTO(infoTemplateId, params, userId, null));
					}
					/***********发送消息*********/
//					sendInfoWriteService.insertCompanyFuBiInfoAndSend(
//							InfoConstant.COMPANY_FUBI_CHANGE_INFO_ID.getStatus(), cashFlowAccountDTO.getSum().negate(), remark, userIds);
				}
			}
			// 流入账户类型是个人（员工充值）
			if(inFlowAccType == 1){
				BigDecimal sum=BigDecimal.valueOf(0);
				for (CashFlowAccountDTO cashFlowAccountDTO : inFlowAccs) {
					if(cashFlowAccountDTO.getUserAccountType() == 0){
						sum = sum.add(cashFlowAccountDTO.getSum());
					}
				}
				if(!sum.equals(BigDecimal.valueOf(0.00))){
					// 根据公司id查询该公司管理员信息集合
					List<Long> userIds = com.egeo.utils.StringUtils.stringsToLongs(userReadService.findUserIdsByCompanyId(outFlowAccs.get(0).getCompanyId(), 1));
					// 发送公司积分变更消息
					/***********发送消息*********/
					CompanyAccountDTO dto = new CompanyAccountDTO();
					dto.setId(outFlowAccs.get(0).getAccountId());
					CompanyAccountDTO dbAccount = companyAccountReadService.findCompanyAccountById(dto);

					Map<String, String> params = new HashMap<>();
					params.put("年月日时分", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
					params.put("变动金额", sum + "");
					params.put("账户余额", dbAccount.getBalance() + "");
					params.put("变动原因内容", remark);
					Long infoTemplateId;
					if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(platformId)) {
						infoTemplateId = InfoConstant.MYY_COMPANY_BALANCE_DECREASE.getStatus();
						params.put("平台名称", "富宏云采");
					} else {
						infoTemplateId = InfoConstant.FGJ_COMPANY_BALANCE_DECREASE.getStatus();
						params.put("平台名称", "大厨管家");
					}
					for (Long userId : userIds) {
						sendInfoWriteService.insertAndSendMessage(new InsertAndSendMessageDTO(infoTemplateId, params, userId, null));
					}
					/***********发送消息*********/
//					sendInfoWriteService.insertCompanyFuBiInfoAndSend(
//							InfoConstant.COMPANY_FUBI_CHANGE_INFO_ID.getStatus(), sum.negate(), remark, userIds);
				}
			}

		}
		if(inFlowAccType == 0){
			for (CashFlowAccountDTO cashFlowAccountDTO : inFlowAccs) {
				// 根据公司id查询该公司管理员信息集合
				List<Long> userIds = com.egeo.utils.StringUtils.stringsToLongs(userReadService.findUserIdsByCompanyId(cashFlowAccountDTO.getCompanyId(), 1));
				// 发送公司积分变更消息
				/***********发送消息*********/
				CompanyAccountDTO dto = new CompanyAccountDTO();
				dto.setId(cashFlowAccountDTO.getAccountId());
				CompanyAccountDTO dbAccount = companyAccountReadService.findCompanyAccountById(dto);

				Map<String, String> params = new HashMap<>();
				params.put("年月日时分", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
				params.put("变动金额", outFlowAccs.get(0).getSum() + "");

				params.put("账户余额", dbAccount.getBalance() + "");
				params.put("变动原因内容", remark);
				Long infoTemplateId;
				if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(platformId)) {
					params.put("平台名称", "富宏云采");
					infoTemplateId = InfoConstant.MYY_COMPANY_BALANCE_RECHARGE.getStatus();
				} else {
					params.put("平台名称", "大厨管家");
					infoTemplateId = InfoConstant.FGJ_COMPANY_BALANCE_RECHARGE.getStatus();
				}
				for (Long userId : userIds) {
					sendInfoWriteService.insertAndSendMessage(new InsertAndSendMessageDTO(infoTemplateId, params, userId, null));
				}
				/***********发送消息*********/
//				sendInfoWriteService.insertCompanyFuBiInfoAndSend(
//						InfoConstant.COMPANY_FUBI_CHANGE_INFO_ID.getStatus(), outFlowAccs.get(0).getSum(), remark, userIds);
			}
		}
		if(inFlowAccType == 1){
			for (CashFlowAccountDTO cashFlowAccountDTO : inFlowAccs) {
				// 账户类型  0:积分账户 1:点赞福豆账户 2:积分冻结账户 3:现金支出账户
				if(cashFlowAccountDTO.getUserAccountType() == 0){
					// 发送积分变更消息
					/***********发送消息*********/
					Map<String, String> params = new HashMap<>();
					params.put("积分变动金额", cashFlowAccountDTO.getSum() + "");
					params.put("变动原因内容", remark);
					Long infoTemplateId;
					if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(platformId)) {
						infoTemplateId = InfoConstant.MYY_GIVE_POINTS.getStatus();
					} else {
						infoTemplateId = InfoConstant.FGJ_GIVE_POINTS.getStatus();
					}
					sendInfoWriteService.insertAndSendMessage(new InsertAndSendMessageDTO(infoTemplateId, params, cashFlowAccountDTO.getUserId(), null));
					/***********发送消息*********/
//					sendInfoWriteService.insertUserFuBiInfoAndSend(
//							InfoConstant.USER_FUBI_CHANGE_INFO_ID.getStatus(), cashFlowAccountDTO.getSum(), remark, cashFlowAccountDTO.getUserId());
				}
				if(cashFlowAccountDTO.getUserAccountType() == 1){
					// 发送点赞福豆变更消息
					/***********发送消息*********/
					Map<String, String> params = new HashMap<>();
					params.put("点赞福豆变动金额", cashFlowAccountDTO.getSum() + "");
					params.put("变动原因内容", remark);
					Long infoTemplateId;
					if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(platformId)) {
						infoTemplateId = InfoConstant.MYY_GIVE_FU_BEAN.getStatus();
					} else {
						infoTemplateId = InfoConstant.FGJ_GIVE_FU_BEAN.getStatus();
					}
					sendInfoWriteService.insertAndSendMessage(new InsertAndSendMessageDTO(infoTemplateId, params, cashFlowAccountDTO.getUserId(), null));
					/***********发送消息*********/
//					sendInfoWriteService.insertUserPraiseFuBiInfoAndSend(
//							InfoConstant.USER_PRAISE_FUBI_CHANGE_INFO_ID.getStatus(), cashFlowAccountDTO.getSum(), remark, cashFlowAccountDTO.getUserId());
				}

			}
		}
		return cashFlowResultDTO;
	}

	/**
	 * 查询单个批次草稿
	 *
	 * @param id
	 * @return
	 */
	public AccountBatchTmpDTO queryAccountBatchTmpById(Long id) {
		AccountBatchTmpDTO condition = new AccountBatchTmpDTO();
		condition.setId(id);
		return accountBatchTmpReadService.findAccountBatchTmpById(condition);
	}

	/**
	 * 更改草稿状态
	 *
	 * @param id
	 * @param reason
	 * @return
	 */
	public int changeAccountBatchTmpStatus(Long id, int status, String reason) {
		return accountBatchTmpWriteService.changeAccountBatchTmpStatus(id, status, reason);

	}

	/**
	 * 根据文件序号改变充值批次草稿状态
	 *
	 * @param sn
	 * @param status
	 * @return
	 */
	public int updateTempRechargeStatus(String sn, int status) {
		return tempRechargeWriteService.updateTempRechargeStatus(sn, status);

	}

	/**
	 * 查询充值记录分页列表
	 *
	 * @param page
	 * @param companyId
	 * @param batchNo
	 * @return
	 */
	public PageResult<AccountBatchDTO> queryRechargeAccountBatchPage(Pagination page, Long companyId, String batchNo) {

		return accountBatchReadService.queryRechargeAccountBatchPage(page, companyId, batchNo);
	}

	/**
	 * 冻结或扣除用户积分账户
	 *
	 * @param req
	 * @return
	 */
	public Integer foscoinAccountDeductWithTx(Long userId, String orderCode, Long platformId, String paymentPassword,
			String userName, String ip, String mac,Long companyId,Integer payType,String realName,String idCardNo,String cardIds, HttpServletRequest req) {
		BigDecimal zero = new BigDecimal(0.00);
		List<CompanyConfigDTO> companyConfigs = userReadService.findUserCompanyConfigs(userId);
		BigDecimal orderNeedPayment = BigDecimal.valueOf(0);
		// 根据订单编号查询订单
		SoDTO soDTO = soReadService.querySoByOrderCode(orderCode);
		if(soDTO.getOrderPayStatus()!=0||soDTO.getOrderStatus()!=0||soDTO.getOrderConfirmStatus()==2){
			logger.info("[积分支付订单状态异常],orderPayStatus="+soDTO.getOrderPayStatus()+",orderCode="+soDTO.getOrderCode()+",orderStatus="+soDTO.getOrderStatus());
			throw new BusinessException(BusinessExceptionConstant.ORDER_STATUS_EXCEPTION,"当前订单支付状态异常");
		}
		QmOrderDTO qmOrderDTO=null;
		if (Objects.nonNull(soDTO) && EmptyUtil.isNotEmpty(soDTO.getExt())){
			qmOrderDTO=soReadService.queryQmOrderBySoId(soDTO.getId());
			if (Objects.nonNull(qmOrderDTO) && Objects.nonNull(qmOrderDTO.getExpireTime())){
				if (qmOrderDTO.getExpireTime().before(new Date())){
					throw new BusinessException(BusinessExceptionConstant.ORDER_STATUS_EXCEPTION,"[1]订单支付超时");
				}
			}
		}
		boolean b=false;
		// 用户支付密码的验证和输入次数的验证
		if(soDTO.getOrderAmountPay().compareTo(BigDecimal.valueOf(0))==0){
			b=true;
		}else{
			//b = verifyPaymentPassword(userId, paymentPassword);
			String isCheckPaymentPassword = "1";
			for(CompanyConfigDTO config : companyConfigs) {
				if(config.getKey().equalsIgnoreCase("payment.password.valid")) {
					isCheckPaymentPassword = config.getValue();
					break;
				}
			}
			if(EmptyUtil.isNotEmpty(isCheckPaymentPassword) && Objects.equals(isCheckPaymentPassword,"0")){
				b = true;
				logger.info("用户id:{}对应的公司id:{}配置无需检查支付密码,自动检查通过",userId,companyId);
			}else{
				b = verifyPaymentPassword(userId, paymentPassword);
			}

		}
		if (b) {
			// // 根据订单编号查询是否处于支付等待列表
			// AwaitQueueDTO awaitQueue =
			// awaitQueueReadService.findByOrderCode(orderCode);
			// if (EmptyUtil.isNotEmpty(awaitQueue)) {
			// throw new
			// BusinessException(BusinessExceptionConstant.ORDER_IN_QUEUE,"订单正在处理，请稍后");
			// }

	        Integer companyType = null;
	    	if(RuntimeContext.cacheUser()==null || RuntimeContext.cacheUser().getCompanyType()==null) {
	    		companyType = companyCoreReadService.findCompanyTypeById(companyId);
	    	}else {
	    		companyType = RuntimeContext.cacheUser().getCompanyType();
	    	}

			// 订单的支付金额=订单金额-优惠金额
			soDTO.setOrderAmount(soDTO.getOrderAmount().subtract(soDTO.getOrderPromotionDiscount()));

			// 如果订单积分冻结金额不为空则取消订单冻结积分，并扣除用户积分账户
			cancelSoFreezeFubi(soDTO.getId(), orderCode, userId);

			// 根据用户id和账户类型查询用户积分账户 账户类型 0:积分账户 1:点赞福豆账户 2:积分冻结账户 4:现金支出账户
			UserAccountDTO userFubiAccountDTO =null;
			String accountName = "积分";
			if(payType == null || payType.intValue()==0){
				userFubiAccountDTO = queryUserAccountByUserIdAndType(userId, 0);
			}else if(payType !=null && payType.intValue()==5){
				userFubiAccountDTO = queryUserAccountByUserIdAndType(userId, 5);
				accountName ="积点";
			}else if(payType !=null && payType.intValue() ==6){
				userFubiAccountDTO = queryUserAccountByUserIdAndType(userId, 6,cardIds);
				accountName ="畅购卡";
			}else if(payType !=null && payType.intValue() ==7){
				userFubiAccountDTO = queryUserAccountByUserIdAndType(userId, 7,cardIds);
				accountName ="礼品卡";
			}
			UserAccountDTO userFdAccountDTO = queryUserAccountByUserIdAndType(userId, 4);

			if(EmptyUtil.isEmpty(userFubiAccountDTO)) {
				throw new BusinessException(BusinessException.ERR_ACCOUNT_NOTFOUND,"用户"+accountName+"账户未就绪");
			}



			Boolean checkAccount = true;
			Integer account0Type = 0;
			Integer account0Valid = 1;
			for(CompanyConfigDTO config : companyConfigs) {
				if(config.getKey().equalsIgnoreCase("account.0.valid")) {
					account0Valid = Integer.valueOf(config.getValue());
				}
				if(config.getKey().equalsIgnoreCase("account.0.type")) {
					account0Type = Integer.valueOf(config.getValue());
				}
			}
			if(account0Valid.intValue()==0) {
				checkAccount = false;
			}
			SaltDTO saltDTO = saltReadService.querySaltByUUID(userFubiAccountDTO.getUuid());
			SaltDTO fdSaltDTO = null;
			if(userFdAccountDTO!=null) {
				fdSaltDTO = saltReadService.querySaltByUUID(userFdAccountDTO.getUuid());
			}
			if(checkAccount &&payType !=null && !(payType.intValue()==6 || payType.intValue() ==7 || payType==5)) {
				// 根据uuid查询

				String ciphertext = MD5Util.MD5Salt(String.valueOf(userFubiAccountDTO.getBalance()), saltDTO.getSaltValue());
				if (!ciphertext.equals(userFubiAccountDTO.getCiphertext())) {
					throw new BusinessException(BusinessExceptionConstant.FOSCOIN_ACCOUNT_ABNORMAL, "积分账户异常");
				}
			}

			// 根据用户id和账户类型查询用户积分账户 账户类型 0:积分账户 1:点赞福豆账户 2:积分冻结账户 4:现金支出账户
			UserAccountDTO userFreezeAccount = queryUserAccountByUserIdAndType(userId, 2);
			if(EmptyUtil.isEmpty(userFreezeAccount)) {
				throw new BusinessException("用户积分冻结账户未就绪");
			}
			// 根据uuid查询
			SaltDTO salt = saltReadService.querySaltByUUID(userFreezeAccount.getUuid());
			String ciphertextSalt = MD5Util.MD5Salt(String.valueOf(userFreezeAccount.getBalance()), salt.getSaltValue());
			if (!ciphertextSalt.equals(userFreezeAccount.getCiphertext())) {
				throw new BusinessException(BusinessExceptionConstant.FOSCOIN_FREEZE_ACCOUNT_ABNORMAL, "积分冻结账户异常");
			}

			UserExtendDTO userExtendDTO = userExtService.findById(userId);
			boolean isDlfUser = false;
			if(userExtendDTO !=null && EmptyUtil.isNotEmpty(userExtendDTO.getChannelSource()) && Objects.equals(userExtendDTO.getChannelSource(),UserChannelSourceEnum.DLF.getChannelSource())){
				isDlfUser = true;
			}
			boolean isYdUser = false;
			if(userExtendDTO !=null && EmptyUtil.isNotEmpty(userExtendDTO.getChannelSource()) && Objects.equals(userExtendDTO.getChannelSource(),UserChannelSourceEnum.YD.getChannelSource())){
				isYdUser = true;
			}
			// 用户积分余额
			BigDecimal canUseFubi = userFubiAccountDTO.getBalance();
			//订单中可用积分结算金额(实付金额-最低现金支付金额=积分可抵扣金额)
			BigDecimal orderPayByFuBi =soDTO.getOrderAmountPay();
			if(!isDlfUser && !isYdUser){
				canUseFubi = userFubiAccountDTO.getBalance().add(userFdAccountDTO==null?zero:userFdAccountDTO.getBalance()).subtract(userFreezeAccount.getBalance());
				orderPayByFuBi = soDTO.getOrderAmountPay().subtract(soDTO.getLimitCashPayAmount());
			}

			// 如果用户积分余额大于积分可抵扣金额的积分余额直接扣除且没有必须使用现金支付的金额(仅需要积分支付)
			if (canUseFubi.compareTo(orderPayByFuBi) >= 0&&soDTO.getLimitCashPayAmount().compareTo(BigDecimal.valueOf(0))<=0) {
				/*if(soDTO.getLimitFuBiPayAmount().compareTo(decimal)>0){
					throw new BusinessException("订单存在仅积分支付商品,积分余额不足");

				}*/
				//判断用户是否有平台积分账户（退款到平台的账户）--优先扣除
				// 流出账户列表
				List<CashFlowAccountDTO> outFlowAccs = new ArrayList<>();
				List<CashFlowAccountDTO> inFlowAccs = new ArrayList<>();
				BigDecimal fundAccPayAmount ;
				BigDecimal userFubiAccPayAmount = orderPayByFuBi;

				// 流入账户列表
				CompanyAccountDTO fubiShouru = companyAccountReadService.querySpecialCompanyAccountByType(platformId,1);
				String caUUID = fubiShouru.getUuid();
				SaltDTO comSalt = saltReadService.querySaltByUUID(caUUID);

				if(userFdAccountDTO!=null && userFdAccountDTO.getBalance()!=null && userFdAccountDTO.getBalance().compareTo(zero) > 0 && !isDlfUser) {
					fundAccPayAmount = (userFdAccountDTO.getBalance().compareTo(orderPayByFuBi) >= 0)?orderPayByFuBi:userFdAccountDTO.getBalance();
					CashFlowAccountDTO cashFlowAccountDTO = new CashFlowAccountDTO();
					cashFlowAccountDTO.setAccountId(userFdAccountDTO.getId());
					cashFlowAccountDTO.setSalt(fdSaltDTO.getSaltValue());
					cashFlowAccountDTO.setSum(fundAccPayAmount);
					outFlowAccs.add(cashFlowAccountDTO);

					CashFlowAccountDTO cashFlowAccount = new CashFlowAccountDTO();
					cashFlowAccount.setAccountId(fubiShouru.getId());
					cashFlowAccount.setSalt(comSalt.getSaltValue());
					cashFlowAccount.setSum(fundAccPayAmount);
					inFlowAccs.add(cashFlowAccount);
					//有限扣除退款账户
					userFubiAccPayAmount = orderPayByFuBi.subtract(fundAccPayAmount);
				}
				if(userFubiAccPayAmount.compareTo(zero) > 0) {

					CashFlowAccountDTO cashFlowAccountDTO = new CashFlowAccountDTO();
					cashFlowAccountDTO.setAccountId(userFubiAccountDTO.getId());
					cashFlowAccountDTO.setSalt(saltDTO.getSaltValue());
					cashFlowAccountDTO.setSum(userFubiAccPayAmount);
					outFlowAccs.add(cashFlowAccountDTO);
					CashFlowAccountDTO cashFlowAccount = new CashFlowAccountDTO();
					cashFlowAccount.setAccountId(fubiShouru.getId());
					cashFlowAccount.setSalt(comSalt.getSaltValue());
					cashFlowAccount.setSum(userFubiAccPayAmount);
					inFlowAccs.add(cashFlowAccount);
				}

				Boolean success = true;
				//生成资金流水
				for(int index=0;index<outFlowAccs.size();index++) {
					List<CashFlowAccountDTO> outFlowAccsTmp = new ArrayList<CashFlowAccountDTO>();
					List<CashFlowAccountDTO> inFlowAccsTmp = new ArrayList<CashFlowAccountDTO>();
					outFlowAccsTmp.add(outFlowAccs.get(index));
					inFlowAccsTmp.add(inFlowAccs.get(index));
					logger.info("资金流动-流出："+JSON.toJSONString(outFlowAccsTmp));
					logger.info("资金流动-流入："+JSON.toJSONString(inFlowAccsTmp));
					int finBatchType = FinBatchConstant.ORDER_PAY_FUBI;
					if(payType !=null && payType.intValue()==5){
						finBatchType = FinBatchConstant.ORDER_PAY_JIDIAN;
					}else if(payType !=null && payType.intValue()==6){
						finBatchType = FinBatchConstant.ORDER_PAY_CGK;
					}else if(payType !=null && payType.intValue()==7){
						finBatchType = FinBatchConstant.ORDER_PAY_LPK;
					}
					CashFlowResultDTO cashFlowResultDTO = accountBatchWriteService.unifiedCashFlow(outFlowAccsTmp, 1,
							inFlowAccsTmp, 0, true, platformId, FlowTypeConstant.OP_FUBI.getStatus(), soDTO.getId(), orderCode,
							userId, finBatchType, null, "订单付款", false, 0,cardIds);
					success = false;
					if (cashFlowResultDTO.getResult()) {
						success = true;
					}else {
						throw new BusinessException("用户积分支付失败");
					}
				}
				if (success) {
					// 如果资金流动、分配unit、增加订单对应pu相应的销量、操作第三方状态、修改优惠卷状态成功则修改订单状态
					SoDTO orderUpdateCondition = new SoDTO();
					orderUpdateCondition.setId(soDTO.getId());
					orderUpdateCondition.setOrderCode(soDTO.getOrderCode());
					orderUpdateCondition.setOrderStatus(OrderConstant.ORDER_STATUS_PAYED.getStatus());
					orderUpdateCondition.setOrderPayStatus(OrderConstant.ORDER_STATUS_PAYED.getStatus());
					orderUpdateCondition.setOrderPaymentConfirmDate(new Date());
					orderUpdateCondition.setOrderPaymentConfirmType(0);
					List<SoChildDTO> soChildDTOList = soChildReadService.querySoChildListBySoId(soDTO.getId());
					boolean flag = false;
					for(SoChildDTO dto:soChildDTOList){
						if(dto.getThirdpartyType()==3){
							flag = true;
						}
					}
					if(flag){
						//京东订单需要等待京东的状态通知
						orderUpdateCondition.setOrderConfirmStatus(OrderConstant.ORDER_CONFIRM_STATUS_NO_CONFIRM.getStatus());
					}else{
						orderUpdateCondition.setOrderConfirmStatus(OrderConstant.ORDER_CONFIRM_STATUS_ALREADY_CONFIRM.getStatus());
					}
					if(payType ==null || payType.intValue()==0){
						orderUpdateCondition.setOrderPaidByFubi(orderPayByFuBi);
						orderUpdateCondition.setUseFubi(1);
						logger.info(soDTO.getId()+"餐卡支付:"+orderPayByFuBi);
					}if(payType !=null && (payType.intValue() == 6 || payType.intValue() ==7)){
						//卡劵支付
						orderUpdateCondition.setOrderCardPaid(orderPayByFuBi);
						orderUpdateCondition.setUseFubi(0);
						logger.info(soDTO.getId()+"卡劵支付:"+orderPayByFuBi);
					}else if(payType !=null && payType.intValue() == 5){
						orderUpdateCondition.setOrderPaidByJidian(orderPayByFuBi);
						orderUpdateCondition.setUseFubi(0);
						logger.info(soDTO.getId()+"积点支付:"+orderUpdateCondition.getOrderPaidByJidian()+"实际："+orderPayByFuBi);
					}
					logger.info("订单号:{},订单id:{}支付方式:{},支付金额餐卡（积分）金额:{},积点金额:{},卡劵金额:{},",orderCode,orderUpdateCondition.getId(),payType,orderUpdateCondition.getOrderPaidByFubi(),orderUpdateCondition.getOrderPaidByJidian(),orderUpdateCondition.getOrderCardPaid());

					soWriteService.updateOrderPaymentInfo(orderUpdateCondition);//积分支付时设置使用积分支付
					//如果是会籍购买则为会员分配会籍权限
					if(soDTO.getSaleWay()==5){
						giveMembership(soDTO);
					}
					// 发送订单支付状态变更消息
					InsertOrderPayStatusInfoAndSendVO vo1 = new InsertOrderPayStatusInfoAndSendVO();
					vo1.setInfoTemplateId(InfoConstant.ORDER_STATUS_PAYED_INFO_ID.getStatus());
					vo1.setOrderCode(orderCode);
					vo1.setOrderPayStatus(OrderConstant.ORDER_STATUS_PAYED.getStatus());
					vo1.setUserId(userId);
					//( Long infoTemplateId, String orderCode,  Integer orderPayStatus,  Long userId
					sendInfoWriteService.insertOrderPayStatusInfoAndSend(vo1);
					InsertAndSendMessageDTO dto = new InsertAndSendMessageDTO();
					dto.setInfoTemplateId(InfoConstant.USER_FUBI_CHANGE_INFO_ID.getStatus());
					dto.setUserId(userId);

					Map<String,String> infoMap = new HashMap<String,String>();
					infoMap.put("changeFuBi", orderPayByFuBi.toPlainString());
					infoMap.put("changeCause", AccountConstant.FUBI_CHANGE_BUY_SU);
					dto.setParams(infoMap);
					// 发送积分变更消息
					sendInfoWriteService.insertUserFuBiInfoAndSend(dto);

					// 分配unit
					cardWriteService.allocationCardAndTakeStock(soDTO.getId(), orderCode, userId, userName, ip, mac, companyType);

					// 增加订单对应pu相应的销量
					merchantProdSalesRecordCoreWriteService.recordSalesVolume(soDTO.getId());
					//以旧换新操作
					exchangeAction(soDTO,companyType,userName,userId,req);

					// 订单积分支付成功,判断订单的第三方订单的类型,并进行相应操作
					boolean isSuccessDealThirdpartyOrder = true;
					if(companyType == 0){

						isSuccessDealThirdpartyOrder = soService.dealThirdpartyOrderWithTx(userName,userId,soDTO.getId(), orderCode);
						logger.info("返回数据:"+isSuccessDealThirdpartyOrder);

					}
					if (isSuccessDealThirdpartyOrder) {
						// 支付成功,且第三方订单处理成功,若使用了优惠卷,优惠卷变为已使用
						couponUnitWriteService.updateCouponByPaySuccessWithTx(soDTO.getId());
					} else {
						// 提前生成退款单编号,以防第三方话费充值失败需取消订单自动退款
						List<String> soRefundNOList = soRefundReadService.genSoRefundNO();
						// 支付成功,但第三方订单处理失败,取消订单自动退款
						List<SoItemDTO> items = soItemReadService.querySoItemListBySoId(soDTO.getId());
						//查询新的so
						SoDTO newSoDTO = soReadService.findSoById(soDTO.getId());
						soService.cancelAndRefundOrderWithTx(new CancelAndRefundOrderWithTxDTO(userName, newSoDTO, items, userId, soRefundNOList.get(0),
								soRefundNOList.get(1), new HttpServletRequestDTO(req)));
					}

					orderNeedPayment = soDTO.getLimitCashPayAmount();

				} else {
					throw new BusinessException("用户积分支付失败");
				}
			} else {
				if(payType !=null && (payType.intValue() ==6 || payType.intValue() ==7)){
					throw new BusinessException(BusinessException.ERR_PAY_AMT_UN_ENOUGH,"用户支付失败,选择卡劵余额不足");
				}
				if(isDlfUser || isYdUser){
					throw new BusinessException(BusinessException.ERR_PAY_AMT_UN_ENOUGH,"用户支付失败,选择支付方式余额不足");
				}
				//积分+现金支付
				BigDecimal needPayment = BigDecimal.valueOf(0);
				UserAccountDTO userAccountDTO2 = new UserAccountDTO();
				SoFreezeFubiDTO soFreezeFubiDTO = new SoFreezeFubiDTO();
				if(orderPayByFuBi.compareTo(canUseFubi)>0){
					//积分全部冻结,积分可抵扣部分需要现金支付
					// 如果用户积分余额小于订单需支付的积分，直接冻结用户所有用户积分
					// 订单需支付积分减去用户剩余积分
					needPayment=soDTO.getOrderAmountPay().subtract(canUseFubi);

					// 冻结用户积分
					BigDecimal balance = userFreezeAccount.getBalance().add(canUseFubi);
					String ciphert = MD5Util.MD5Salt(String.valueOf(balance), salt.getSaltValue());
					// 根据用户积分冻结id修改冻结积分余额
					userAccountDTO2.setId(userFreezeAccount.getId());
					userAccountDTO2.setCiphertext(ciphert);
					userAccountDTO2.setBalance(balance);
					// 记录订单积分冻结金额
					soFreezeFubiDTO.setSoId(soDTO.getId());
					soFreezeFubiDTO.setUserId(soDTO.getUserId());
					soFreezeFubiDTO.setBalance(canUseFubi);
					/*if(soDTO.getLimitFuBiPayAmount().compareTo(decimal)>0){
						throw new BusinessException("订单存在仅积分支付商品,积分余额不足");

					}*/

				}else{
					//积分可抵扣部分全部积分支付
					//冻结可抵扣积分金额,必须现金的部分发起现金支付
					needPayment = soDTO.getOrderAmountPay().subtract(orderPayByFuBi);
					// 冻结用户积分
					BigDecimal balance = userFreezeAccount.getBalance().add(orderPayByFuBi);
					String ciphert = MD5Util.MD5Salt(String.valueOf(balance), salt.getSaltValue());
					// 根据用户积分冻结id修改冻结积分余额
					userAccountDTO2.setId(userFreezeAccount.getId());
					userAccountDTO2.setCiphertext(ciphert);
					userAccountDTO2.setBalance(balance);
					// 记录订单积分冻结金额
					soFreezeFubiDTO.setSoId(soDTO.getId());
					soFreezeFubiDTO.setUserId(soDTO.getUserId());
					soFreezeFubiDTO.setBalance(orderPayByFuBi);
					/*if(soDTO.getLimitFuBiPayAmount().compareTo(orderPayByFuBi)>0){
						throw new BusinessException("订单存在仅积分支付商品,积分余额不足");

					}*/
				}
				userAccountWriteService.updateUserAccountWithTx(userAccountDTO2);
				soFreezeFubiWriteService.insertSoFreezeFubiWithTx(soFreezeFubiDTO);
				orderNeedPayment = needPayment;
				/*// 如果用户积分余额小于订单需支付的积分，直接冻结用户所有用户积分
				// 订单需支付积分减去用户剩余积分
				BigDecimal needPayment = soDTO.getOrderAmount().subtract(decimal);

				// 冻结用户积分
				BigDecimal balance = userAccount.getBalance().add(decimal);
				String ciphert = MD5Util.MD5Salt(String.valueOf(balance), salt.getSaltValue());
				// 根据用户积分冻结id修改冻结积分余额
				UserAccountDTO userAccountDTO2 = new UserAccountDTO();
				userAccountDTO2.setId(userAccount.getId());
				userAccountDTO2.setCiphertext(ciphert);
				userAccountDTO2.setBalance(balance);
				userAccountWriteService.updateUserAccountWithTx(userAccountDTO2);

				orderNeedPayment = needPayment;

				// 记录订单积分冻结金额
				SoFreezeFubiDTO soFreezeFubiDTO = new SoFreezeFubiDTO();
				soFreezeFubiDTO.setSoId(soDTO.getId());
				soFreezeFubiDTO.setUserId(soDTO.getUserId());
				soFreezeFubiDTO.setBalance(decimal);
				soFreezeFubiWriteService.insertSoFreezeFubiWithTx(soFreezeFubiDTO);*/
			}
			Long date = new Date().getTime();
			Long createTime = soDTO.getCreateTime().getTime();
			Long residueTime = date - createTime;
			if (residueTime.longValue() > (SpringContextTool.isPrd()?PayUtil.ORDER_EXIST_TIME_MS:PayUtil.ORDER_EXIST_TIME_MS*1000)) {
				throw new BusinessException("订单支付超时");
			}
			if (Objects.nonNull(qmOrderDTO) && Objects.nonNull(qmOrderDTO.getExpireTime())){
				if (qmOrderDTO.getExpireTime().before(new Date())){
					throw new BusinessException(BusinessExceptionConstant.ORDER_STATUS_EXCEPTION,"[2]订单支付超时");
				}
			}
		}
		if (orderNeedPayment != null && orderNeedPayment.compareTo(BigDecimal.valueOf(0.00)) != 0) {
			logger.info("[积分支付结果,还需要现金支付]="+1);
			return 1;
		} else {
			logger.info("[积分支付结果,仅积分支付,且已结束]="+0);
			return 0;
		}

	}

	//进行以旧换新
	private void exchangeAction(SoDTO soDTO,Integer companyType,String userName,Long userId,HttpServletRequest req){
		//进行以旧换新操作
		if(soDTO.getSaleWay().equals(8)){
			boolean isSuccessExchange=true;
			if(companyType==0){
				isSuccessExchange=soService.exchangeOrderWithTx(soDTO.getId(),soDTO.getOrderCode(),userName,userId);
				logger.info("以旧换新加价兑换结果:"+isSuccessExchange);
			}
			if(!isSuccessExchange){
				//取消订单成功,如果是以旧换新更新相关记录
				ExchangeOrderRecordDTO recordDTO = new ExchangeOrderRecordDTO();
				recordDTO.setConversionStatus(Integer.valueOf(2));
				recordDTO.setOrderCode(soDTO.getOrderCode());
				int rt=exchangeOrderRecordWriteService.updateExchangeOrderRecordByOrderCodeWithTx(recordDTO);
				//如果是以旧换新订单需要释放旧unit
				List<ExchangeOrderRecordDTO> exchangeOrderRecordByOrderCode = exchangeOrderRecordReadService.findExchangeOrderRecordAllByOrderCode(soDTO.getOrderCode());
				if(EmptyUtil.isEmpty(exchangeOrderRecordByOrderCode)||exchangeOrderRecordByOrderCode.size()>1){
					logger.info("当前订单对应的以旧换新记录有误,orderCode="+soDTO.getOrderCode());
					throw new BusinessException("当前订单对应的以旧换新记录有误,请联系管理员");
				}
				JedisUtil jedisUtil = new JedisUtil();
/*				jedisUtil.setCONFIGFILE(JedisUtil.REDIS_CONFIG_PATH);
*/				jedisUtil.delLock(JedisUtil.COUPON_UNIT_LOCK_PRE+exchangeOrderRecordByOrderCode.get(0).getOldUnitCode());
				//int i = couponUnitWriteService.updateCouponUnitRemoveLock(exchangeOrderRecordByOrderCode.get(0).getOldUnitCode());
				logger.info("订单取消,释放unit锁");


				//以旧换新失败
				// 生成退款单编号
				List<String> soRefundNOList = soRefundReadService.genSoRefundNO();
				// 支付成功,但以旧换新失败,取消订单自动退款
				List<SoItemDTO> items = soItemReadService.querySoItemListBySoId(soDTO.getId());
				SoDTO soById = soReadService.findSoById(soDTO.getId());
				soService.cancelAndRefundOrderWithTx(new CancelAndRefundOrderWithTxDTO(userName,soById, items, userId, soRefundNOList.get(0),
						soRefundNOList.get(1), new HttpServletRequestDTO(req)));
			}
		}
	}
	//为用户分配会籍
	private void giveMembership(SoDTO so) {
		List<Long> puIdList=com.egeo.utils.StringUtils.stringsToLongs(soItemReadService.findPuIdBySoId(so.getId()));
		if(puIdList.size()!=1){
			logger.info("[会籍购买,订单对应的puId有误]puIdList:"+puIdList);
			throw new BusinessException("该订单对应的商品有误请联系管理员");
		}
		//根据puId查询skuId
		Long skuId=commodityProductUnitReadService.findSkuIdByPuId(puIdList.get(0));
		if(EmptyUtil.isEmpty(skuId)){
			logger.info("[会籍购买,订单对应的skuId有误]skuId:"+skuId);
			throw new BusinessException("该订单对应的sku有误请联系管理员");
		}
		membershipUserWriteService.giveUserMembershipByOrder(so.getUserId(),so.getPlatformId(),skuId);
	}



	private boolean verifyPaymentPassword(Long userId, String paymentPassword) {
		boolean isSucceed = false;
		// 根据用户id查询用户信息
		UserDTO userDTO = userReadService.findUserByID(userId);
		if(userDTO == null){
			throw new BusinessException("请先登录");
		}
		if (EmptyUtil.isEmpty(userDTO.getPaymentCodeUuid()) || EmptyUtil.isBlank(userDTO.getPaymentCodeUuid())
				|| EmptyUtil.isEmpty(userDTO.getPaymentCode()) || EmptyUtil.isBlank(userDTO.getPaymentCode())) {
			throw new BusinessException("请设置支付密码");
		}
		// 根据uuId查询用户支付密钥
		String salt = paymentCodeSaltReadService.findSaltByUUID(userDTO.getPaymentCodeUuid());
		String paymentCode = MD5Support.MD5(paymentPassword, salt);
		if (!paymentCode.equals(userDTO.getPaymentCode())) {
			throw new BusinessException(BusinessExceptionConstant.PAYMENTPASSWORD_NO_MATCH, "支付密码错误");
		}
		isSucceed = true;
		return isSucceed;
	}

	/**
	 * 原子处理公司账户和员工账户的失效回收
	 *
	 * @param companyId
	 * @param platformId
	 * @param operatorId
	 * @return
	 */

	public boolean disableCompanyAccount(Long id, Long companyId, Long platformId, Long operatorId) {
		// 将当前失效企业账号id及 从在职变为离职的员工存储起来
		/*UserDTO userDTO = new UserDTO();
		userDTO.setPlatformId(platformId);
		userDTO.setIsAvailable(1);
		List<UserDTO> users = userReadService.findUser(userDTO);
		for (UserDTO user : users) {
			CompanyUserDisabledDTO companyUserDisabledDTO = new CompanyUserDisabledDTO();
			companyUserDisabledDTO.setCompanyId(companyId);
			companyUserDisabledDTO.setUserId(user.getId());
			companyUserDisabledWriteService.insertCompanyUserDisabledWithTx(companyUserDisabledDTO);
		}*/
		//设置企业的企业失效前金额
		CompanyAccountDTO companyAccountDTO = new CompanyAccountDTO();
		companyAccountDTO.setCompanyId(companyId);
		List<CompanyAccountDTO> companyAccountDTO2 = companyAccountReadService.findCompanyAccountAll(companyAccountDTO);
		companyAccountDTO2.get(0).setBeforeDisableBalance(companyAccountDTO2.get(0).getBalance());
		companyAccountWriteService.updateCompanyAccountWithTx(companyAccountDTO2.get(0));

		// 查询公司之下的所有员工
		List<UserDTO> us = userReadService.findUsersByCompanyId(companyId);
		// 员工账户
		List<CashFlowAccountDTO> outFlowAccs = new ArrayList<>();// 待回收账户
		for (UserDTO u : us) {
			Long userId = u.getId();
			List<UserAccountDTO> uas = userAccountReadService.queryUserAccountByUserId(userId);
			for (UserAccountDTO ua : uas) {
				BigDecimal balance = ua.getBalance();

				//存储失效前的积分金额,是积分-冻结积分
				if (ua.getType() == 0) {
					UserAccountDTO dto = new UserAccountDTO();
					dto.setUserId(ua.getUserId());
					dto.setType(2);
					List<UserAccountDTO> userAccountDTO = userAccountReadService.queryUserAccountByParam(dto);
					balance = balance.subtract(userAccountDTO.get(0).getBalance());
				}
				//设置员工的企业失效前金额
				ua.setBeforeDisableBalance(balance);
				userAccountWriteService.updateUserAccountWithTx(ua);

				int uaType = ua.getType().intValue();
				CashFlowAccountDTO cfa = new CashFlowAccountDTO();
				cfa.setAccountId(ua.getId());
				cfa.setSum(balance);
				cfa.setUserAccountType(uaType);
				// 查询盐
				String salt = saltReadService.querySaltByUUID(ua.getUuid()).getSaltValue();
				cfa.setSalt(salt);
				outFlowAccs.add(cfa);
			}
		}

		// 查询公司账户
		CompanyAccountDTO ca = companyAccountReadService.queryNormalCompanyAccountByCompnayId(companyId);
		String salt = saltReadService.querySaltByUUID(ca.getUuid()).getSaltValue();
		CashFlowAccountDTO ccfa = new CashFlowAccountDTO();
		ccfa.setSalt(salt);
		ccfa.setAccountId(id);
		// 设置公司积分账户为流入账户
		List<CashFlowAccountDTO> inFlowAccs = new ArrayList<>();
		inFlowAccs.add(ccfa);
		// 调用统一资产流动接口，回收员工资产至公司积分账户
		if (outFlowAccs.size() > 0) {
			logger.info("调用统一资产流动接口，回收员工资产至公司积分账户");
			accountBatchWriteService.unifiedCashFlow(outFlowAccs, 1, inFlowAccs, 0, false, platformId,
					FlowTypeConstant.UA_LEAVE.getStatus(), null, null, operatorId,
					FinBatchConstant.USER_ACCOUNT_DISABLE, null, null, false, 2);

		}
		// 调用统一资产流动接口，回收公司资产至迩格积分发放账户
		// 重新查询公司账户,以刷新公司账户的状态
		ca = companyAccountReadService.queryNormalCompanyAccountByCompnayId(companyId);
		// 查询迩格积分收入账户及盐
		CompanyAccountDTO fubiGrantAcc = companyAccountReadService.querySpecialCompanyAccountByType(platformId,1);
		String grantAcsSalt = saltReadService.querySaltByUUID(fubiGrantAcc.getUuid()).getSaltValue();
		List<CashFlowAccountDTO> outFlowAccs_ = new ArrayList<>();
		CashFlowAccountDTO outFlowAcc = new CashFlowAccountDTO();
		outFlowAcc.setAccountId(id);
		outFlowAcc.setSalt(salt);
		outFlowAcc.setSum(ca.getBalance());
		outFlowAccs_.add(outFlowAcc);
		List<CashFlowAccountDTO> inFlowAccs_ = new ArrayList<>();
		CashFlowAccountDTO inFlowAcc = new CashFlowAccountDTO();
		inFlowAcc.setAccountId(fubiGrantAcc.getId());
		inFlowAcc.setSalt(grantAcsSalt);
		inFlowAccs_.add(inFlowAcc);
		logger.info("调用统一资产流动接口，回收公司资产至迩格积分发放账户");
		logger.info("失效企业ID："+outFlowAccs_.get(0).getAccountId());
		logger.info("失效企业余额："+outFlowAccs_.get(0).getSum());
		accountBatchWriteService.unifiedCashFlow(outFlowAccs_, 0, inFlowAccs_, 0, true, platformId,
				FlowTypeConstant.CA_DISABLE.getStatus(), null, null, operatorId, FinBatchConstant.COMPANY_DESABLE, null,
				null, false, 0);
		// 更改企业账户有效性
		companyAccountWriteService.updateAccountDisable(id, 1);
		// 更改企业员工账户有效性、 更新资产回收日期
		for (UserDTO u : us) {
			userAccountWriteService.updateUserAccountDisabled(u.getId(), 1);
			UserExtendDTO userExtendDTO = new UserExtendDTO();
			userExtendDTO.setId(u.getId());
			userExtendDTO.setInvalidTime(new Date());
			userExtendWriteService.updateInvalidTime(userExtendDTO);
		}
		return true;
	}


	/**
	 * 如果订单积分冻结金额不为空则取消订单冻结积分，并扣除用户积分账户
	 */
	public void cancelSoFreezeFubi(Long orderId, String orderCode, Long userId) {
		// 根据订单id查询订单冻结积分
		BigDecimal soFreezeBalance = soFreezeFubiReadService.findSoFreezeBalanceBySoId(orderId);
		if (EmptyUtil.isNotEmpty(soFreezeBalance)) {
			// 如果订单积分冻结金额不为空则取消订单冻结积分，并扣除用户积分账户

			// 根据订单id删除订单冻结积分
			soFreezeFubiWriteService.delBySoId(orderId);

			// 根据用户id和账户类型查询用户积分账户 账户类型 0:积分账户 1:点赞福豆账户 2:积分冻结账户 4:现金支出账户
			UserAccountDTO userFreezeAccount = queryUserAccountByUserIdAndType(userId, 2);
			// 根据uuid查询
			SaltDTO salt = saltReadService.querySaltByUUID(userFreezeAccount.getUuid());
			String ciphertextSalt = MD5Util.MD5Salt(String.valueOf(userFreezeAccount.getBalance()), salt.getSaltValue());
			if (!ciphertextSalt.equals(userFreezeAccount.getCiphertext())) {
				throw new BusinessException(BusinessExceptionConstant.FOSCOIN_FREEZE_ACCOUNT_ABNORMAL, "积分冻结账户异常");
			}

			BigDecimal newBalance = userFreezeAccount.getBalance().subtract(soFreezeBalance);
			String ciphert = MD5Util.MD5Salt(String.valueOf(newBalance), salt.getSaltValue());
			// 根据用户积分冻结id修改冻结积分余额
			UserAccountDTO userAccountDTO2 = new UserAccountDTO();
			userAccountDTO2.setId(userFreezeAccount.getId());
			userAccountDTO2.setCiphertext(ciphert);
			userAccountDTO2.setBalance(newBalance);
			userAccountWriteService.updateUserAccountWithTx(userAccountDTO2);
		}
	}

	/**
	 * 根据sn删除导入预览表中的旧记录
	 *
	 * @param sn
	 * @return
	 */
	public int deleteTempRechargeBySn(String sn) {
		return tempRechargeWriteService.deleteTempRechargeBySn(sn);
	}

	/**
	 * 根据订单编号查询订单
	 *
	 * @param orderCode
	 * @return
	 */
	public SoDTO querySoByOrderCode(String orderCode) {
		return soReadService.querySoByOrderCode(orderCode);
	}

	/**
	 * 根据订单id删除订单等待队列
	 *
	 * @param orderId
	 * @return
	 */
	public int delByOrderIdWithTx(Long orderId) {
		return awaitQueueWriteService.delByOrderIdWithTx(orderId);
	}

	/**
	 * 用户资产流水分页列表
	 *
	 * @param accountIdList
	 * @param page
	 * @param startTime
     *@param endTime @return
	 */
	public PageResult<AccountFlowDTO> userFinFlowPage(List<Long> accountIdList, Pagination page, Date startTime, Date endTime) {

		return accountFlowReadService.userFinFlowPage(accountIdList, page,startTime,endTime);
	}

	/**
	 * 查询几个指定类型的原因
	 *
	 *
	 * @param platformId
	 * @param typeList
	 * @param companyId
	 * @return
	 */
	public List<AdjustReasonDTO> queryAdjustReasonsByTypes(Long platformId, List<Integer> typeList, Long accountId, Long companyId) {
		return adjustReasonReadService.queryAdjustReasonsByTypes(platformId,typeList, accountId, companyId);
	}

	/**
	 * 更改原因,并更改与公司的关系
	 *
	 * @param dto
	 * @param cIds
	 * @param cIds
	 * @return
	 */
	public int editAdjustReason(AdjustReasonDTO dto, List<Long> cIds) {
		return adjustReasonWriteService.editAdjustReason(dto, cIds);
	}

	/**
	 * 根据原因id查询关联公司列表
	 *
	 * @param reasonId
	 * @return
	 */
	public List<ReasonCompanyDTO> queryReasonCompanyListByReasonId(Long reasonId) {
		ReasonCompanyDTO dto = new ReasonCompanyDTO();
		dto.setReasonId(reasonId);
		return reasonCompanyReadService.findReasonCompanyAll(dto);
	}

    public List<UserAccountDTO> queryUserAccountByUserIds(List<Long> userIdList) {
		return userAccountReadService.queryUserAccountByUserIds(userIdList);
    }

	public boolean checkUserByUserId(CacheUser user) {

		UserRoleDTO userRole = new UserRoleDTO();
		userRole.setUserId(user.getId());
		List<UserRoleDTO> roleDTOs = userRoleReadService.findUserRoleAll(userRole);
		for (UserRoleDTO userRoleDTO : roleDTOs) {
			//管理员 或者 技术部
			if(userRoleDTO.getRoleId() == 1 || userRoleDTO.getRoleId() == 10021) {
				return true;
			}
		}
		return false;
	}

	public void updateAccountFlowReadStatus(List<Long> ids) {
		accountFlowWriteService.updateAccountFlowReadStatus(ids);
	}


	/**
	 * 根据退款记录清除已退餐卡金额
	 * @param userId
	 * @param refundId
	 * @param platformId
	 * @return
	 */
	public Integer unRefundAmount(Long userId, Long refundId, Long platformId){
		SoRefundDTO soRefundDTO = soRefundReadService.getSoRefundById(refundId);
		if(soRefundDTO == null){
			throw new com.egeo.exception.BusinessException(BusinessExceptionConstant.DATA_ERROE, "未找到退款记录");
		}
		if(soRefundDTO.getRefundState() !=0){
			throw new com.egeo.exception.BusinessException(BusinessExceptionConstant.DATA_ERROE, "退款记录已操作，不能再次操作");
		}
		if(soRefundDTO.getSoRefundByFubi() ==null || soRefundDTO.getSoRefundByFubi().compareTo(BigDecimal.ZERO) <=0){
			throw new com.egeo.exception.BusinessException(BusinessExceptionConstant.DATA_ERROE, "退款记录金额不对不能操作");
		}
		BigDecimal soFreezeBalance  = soRefundDTO.getSoRefundByFubi();
		// 根据用户id和账户类型查询用户积分账户 账户类型 0:积分账户 1:点赞福豆账户 2:积分冻结账户 4:现金支出账户
		UserAccountDTO userFreezeAccount = queryUserAccountByUserIdAndType(soRefundDTO.getUserId(), 4);
		// 根据uuid查询
		SaltDTO salt = saltReadService.querySaltByUUID(userFreezeAccount.getUuid());
		String ciphertextSalt = MD5Util.MD5Salt(String.valueOf(userFreezeAccount.getBalance()), salt.getSaltValue());
		if (!ciphertextSalt.equals(userFreezeAccount.getCiphertext())) {
			throw new com.egeo.exception.BusinessException(BusinessExceptionConstant.DATA_ERROE, "退款冻结账户金额异常");
		}

		BigDecimal newBalance = userFreezeAccount.getBalance().subtract(soFreezeBalance);
		String ciphert = MD5Util.MD5Salt(String.valueOf(newBalance), salt.getSaltValue());
		// 根据用户积分冻结id修改冻结积分余额
		UserAccountDTO userAccountDTO2 = new UserAccountDTO();
		userAccountDTO2.setId(userFreezeAccount.getId());
		userAccountDTO2.setCiphertext(ciphert);
		userAccountDTO2.setBalance(newBalance);
		int i=  userAccountWriteService.updateUserAccountWithTx(userAccountDTO2);
		if(i <=0){
			throw new com.egeo.exception.BusinessException(BusinessExceptionConstant.DATA_ERROE, "减扣退款冻结金额失败");
		}
		SoRefundDTO editSoRefundDTO = new SoRefundDTO();
		editSoRefundDTO.setId(refundId);
		String remark="操作确认已退款金额:"+soFreezeBalance;
		if(EmptyUtil.isNotEmpty(soRefundDTO.getRemark())){
			editSoRefundDTO.setRemark(soRefundDTO.getRemark()+","+remark);
		}else{
			editSoRefundDTO.setRemark(remark);
		}
		editSoRefundDTO.setRefundState(1);
		soRefundReadService.changeRefundState(editSoRefundDTO);
		return i;
	}

	/**
	 * 根据订单编号查询订单
	 *
	 * @param orderCode
	 * @return
	 */
	public SoChildDTO querySoChildByOrderCode(String orderCode) {
		return soChildReadService.findSoChildByChildCode(orderCode);
	}

	/**
	 * 根据订单编号查询订单
	 *
	 * @param orderCode
	 * @return
	 */
	public SoDTO querySoByChildCode(String orderCode) {
		SoChildDTO soChildDTO = querySoChildByOrderCode(orderCode);
		if(soChildDTO == null){
			throw new BusinessException("子订单未找到");
		}
		return soService.findSoById(soChildDTO.getSoId());
	}

	@Resource
	private BuyCardClient buyCardClient;

	/**
	 * 查询用户的某个账户
	 *
	 * @param id
	 * @return
	 */
	public UserAccountDTO queryUserAccountByUserIdAndType(Long id, Integer type,String cardIds) {

		UserAccountDTO ua = userAccountReadService.queryUserAccountByUserIdAndType(id, type);
		if (ua == null) {
			return null;
		}
		if(EmptyUtil.isNotEmpty(cardIds)){
			List<Long> cardIdList = JSONArray.parseArray(cardIds, Long.class);
			UserCardRecordDTO dto = new UserCardRecordDTO();
			dto.setIds(cardIdList);
			BigDecimal cash = buyCardClient.sumUserCardCash(dto);
			ua.setBalance(cash);
		}
		return ua;
	}
}
