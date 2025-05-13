package com.egeo.components.finance.manage.write.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.egeo.components.finance.constant.FinBatchConstant;
import com.egeo.components.order.client.SoClient;
import com.egeo.components.order.vo.BuyCardPayReqVO;
import com.egeo.components.order.vo.BuyCardPayRespVO;
import com.egeo.components.third.client.ChannelServiceConfigClient;
import com.egeo.components.third.dto.RemoteExecuteDTO;
import com.egeo.components.third.enums.ChannelServiceNameEnum;
import com.egeo.components.third.enums.ChannelServiceTypeEnum;
import com.egeo.components.user.enums.UserChannelSourceEnum;
import com.egeo.utils.StringUtils;
import com.egeo.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.config.dto.CompanyConfigDTO;
import com.egeo.components.finance.bean.SoDataDTO;
import com.egeo.components.finance.common.BusinessException;
import com.egeo.components.finance.constant.AccountConstant;
import com.egeo.components.finance.dao.read.AccountBatchReadDAO;
import com.egeo.components.finance.dao.read.CompanyAccountReadDAO;
import com.egeo.components.finance.dao.read.UserAccountReadDAO;
import com.egeo.components.finance.dao.write.AccountBatchWriteDAO;
import com.egeo.components.finance.dao.write.AccountFlowWriteDAO;
import com.egeo.components.finance.dao.write.CompanyAccountWriteDAO;
import com.egeo.components.finance.dao.write.UserAccountWriteDAO;
import com.egeo.components.finance.facade.OrderFacade;
import com.egeo.components.finance.manage.write.AccountBatchWriteManage;
import com.egeo.components.finance.po.AccountBatchPO;
import com.egeo.components.finance.po.AccountFlowPO;
import com.egeo.components.finance.po.CashFlowAccountPO;
import com.egeo.components.finance.po.CashFlowResultPO;
import com.egeo.components.finance.po.CompanyAccountPO;
import com.egeo.components.finance.po.UserAccountPO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.client.UserExtendClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.util.security.MD5Util;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.SequenceUtil;
import com.egeo.utils.http.HttpClientUtil;

import javax.annotation.Resource;

@Service
public class AccountBatchWriteManageImpl implements AccountBatchWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AccountBatchWriteDAO accountBatchWriteDAO;
	@Autowired
	private CompanyAccountReadDAO companyAccountReadDAO;
	@Autowired
	private CompanyAccountWriteDAO companyAccountWriteDAO;
	@Autowired
	private AccountBatchReadDAO accountBatchReadDAO;
	@Autowired
	private AccountFlowWriteDAO accountFlowWriteDAO;
	@Autowired
	private UserAccountWriteDAO userAccountWriteDAO;
	@Autowired
	private UserAccountReadDAO userAccountReadDAO;

	@Autowired
	private OrderFacade orderFacade;
	@Autowired
	private CompanyClient companyClient;
	@Autowired
	private UserClient userClient;
	@Autowired
	private UserExtendClient userExtendClient;

	@Autowired
	private ChannelServiceConfigClient channelServiceConfigClient;

	@Override
	public Long insertAccountBatchWithTx(AccountBatchPO po) {

		int i;
		try {
			i = accountBatchWriteDAO.insert(po);
			if (i == 0)
				throw new BusinessException("未能成功插入数据!");
		} catch (DuplicateKeyException e) {
			logger.error("", e);
			throw new BusinessException("路径必须唯一!");
		}
		return po.getId();
	}

	@Override
	public int updateAccountBatchWithTx(AccountBatchPO po) {
		int i;
		i = accountBatchWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteAccountBatchWithTx(AccountBatchPO po) {
		int i;
		i = accountBatchWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int batchExamWithTx(Long platformId, Long id, boolean pass, String outflowSalt) {
		// 根据批次id,更改批次状态,更改明细状态,如果通过,生成流水,改变账户余额
		AccountBatchPO condition = new AccountBatchPO();
		condition.setId(id);
		AccountBatchPO batch = accountBatchReadDAO.findById(condition);
		if (batch == null)
			throw new BusinessException("批次信息有误");
		// 查询迩格积分充值账户
		CompanyAccountPO fubiAccount = companyAccountReadDAO.querySpecialCompanyAccountByType(1,platformId);
		if (fubiAccount == null)
			throw new BusinessException("账户信息尚未就绪");

		if (pass) {
			AccountFlowPO flow = new AccountFlowPO();
			flow.setBatchId(id);
			// flow.setInflowAccountid(batch.getAccountId());
			// 定死为普通公司账户类型
			flow.setInflowAccounttype(AccountConstant.ACCOUNT_TYPE_NORMAL);
			flow.setOutflowAccountid(fubiAccount.getId());
			flow.setOutflowAccounttype(AccountConstant.ACCOUNT_TYPE_FUBI_OUT);
			flow.setReasonId(batch.getReasonId());
			flow.setRemark(batch.getRemark());
			flow.setSum(batch.getSum());
			accountFlowWriteDAO.insert(flow);
			accountBatchWriteDAO.updateBatchStatusById(id, AccountConstant.ACCOUNT_STATUS_PASSED);
			BigDecimal sum_ = batch.getSum();
			// 更改账户余额
			// companyAccountWriteDAO.updateAccountBalance(batch.getAccountId(),sum_,inflowCipher,true);
			companyAccountWriteDAO.increaseAccountBalance(fubiAccount.getId(), sum_.negate(), false,outflowSalt);
		} else {
			// 改变状态为不通过
			accountBatchWriteDAO.updateBatchStatusById(id, AccountConstant.ACCOUNT_STATUS_FAIL);
		}
		return 1;
	}

	@Override
	public CashFlowResultPO 	unifiedCashFlowWithTx(List<CashFlowAccountPO> outFlowAccs, Integer outFlowAccType,
			List<CashFlowAccountPO> inFlowAccs, Integer inFlowAccType, boolean nonNegLimit, Long platformId,
			Integer type, Long orderId, String orderCode, Long operatorId, int finBatchType, Long reasonId,
			String remark, boolean isRecharge, Integer operateType,String otherInfo) {
		if (outFlowAccs.size() == 0 || inFlowAccs.size() == 0) {
			throw new BusinessException("流出方账户或流入方账户未定义");
		}
		CashFlowResultPO res = null;
		if(orderCode!=null) {

		}
		switch (operateType) {
		case 0:
			/*
			 * 单对单 可能的情况: 迩格积分发放账户->公司账户 员工积分账户->迩格积分收入账户 员工现金账户->迩格现金收入账户 员工点赞福豆账户->员工积分账户
			 * ....想不起来了 待续
			 *
			 */
			res = o2oWithTx(outFlowAccs, outFlowAccType, inFlowAccs, inFlowAccType, nonNegLimit, platformId, type,
					orderId, orderCode, operatorId, finBatchType, reasonId, remark, isRecharge, operateType,otherInfo);
			break;
		case 1:
			/**
			 * 单对多(目前仅公司账户向员工账户充值)
			 */
			res = o2mWithTx(outFlowAccs, outFlowAccType, inFlowAccs, inFlowAccType, nonNegLimit, platformId, type,
					orderId, orderCode, operatorId, finBatchType, reasonId, remark, isRecharge, operateType);
			break;
		case 2:
			/**
			 * 多对单(目前仅员工离职资产账户清零)
			 */
			res = m2oWithTx(outFlowAccs, outFlowAccType, inFlowAccs, inFlowAccType, nonNegLimit, platformId, type,
					orderId, orderCode, operatorId, finBatchType, reasonId, remark, isRecharge, operateType);
			break;
		case 3:
			/**
			 * 多对单(目前仅员工离职资产账户清零)
			 */
			res = m2oWithTx2(outFlowAccs, outFlowAccType, inFlowAccs, inFlowAccType, nonNegLimit, platformId, type,
					orderId, orderCode, operatorId, finBatchType, reasonId, remark, isRecharge, operateType);
			break;


		default:
			throw new BusinessException("未定义的资金流操作");
		}
		return res;
	}

	private CashFlowResultPO m2oWithTx2(List<CashFlowAccountPO> outFlowAccs, Integer outFlowAccType,
			List<CashFlowAccountPO> inFlowAccs, Integer inFlowAccType, boolean nonNegLimit, Long platformId,
			Integer type, Long orderId, String orderCode, Long operatorId, int finBatchType, Long reasonId,
			String remark, boolean isRecharge, Integer operateType) {
		// 统计批次总金额
		BigDecimal sum =BigDecimal.valueOf(0.00);
		// 统计批次总流水条数
		int flowAmount = 0;
		//在多对单流动中,每笔流动金额记录再流出账户列表中
		for (CashFlowAccountPO cfa : outFlowAccs) {
			//退款账户
			if (cfa.getUserAccountType() <= 1 || cfa.getUserAccountType().intValue()==4) {
				BigDecimal outSum=cfa.getSum();
				if(outSum.compareTo(BigDecimal.valueOf(0.00))==1) {
					//sum大于0才计算流水
					sum = sum.add(outSum);
					flowAmount++;
				}
			}
		}
		if(sum.equals(BigDecimal.valueOf(0.00))) {
			return new CashFlowResultPO(false);
		}

		//流入账户信息
		CashFlowAccountPO inFlowAcc=inFlowAccs.get(0);

		//流入账户加密校验
		checkAccountCipher(inFlowAcc,inFlowAccType);
		//流出账户加密校验
		for (CashFlowAccountPO cfa : outFlowAccs) {
			checkAccountCipher(cfa,outFlowAccType);
		}
		// 流入账户id
		Long inFlowAccId = inFlowAcc.getAccountId();

		// 生成批次
		String raBatch = SequenceUtil.genRaBatchNo();
		String finBatch=SequenceUtil.genFinBatchNo(finBatchType);
		//多对一批次不关心流出账户id
		AccountBatchPO ab = new AccountBatchPO();
		ab.setFlowAmount(flowAmount);
		ab.setInflowAccountid(inFlowAccId);
		ab.setOperatorId(operatorId);
		ab.setPlatformId(platformId);
		ab.setRaBatch(raBatch);
		ab.setFinBatch(finBatch);
		ab.setReasonId(reasonId);
		ab.setRemark(remark);
		ab.setSum(sum);
		ab.setType(type);
		// 货币类型，为空进行一次赋值、如果下次判断不一样则为多种货币类型
		Integer currencyType = null;
		for (CashFlowAccountPO ofa : outFlowAccs) {
			if(currencyType == null){
				currencyType = ofa.getUserAccountType();
			}else{
				if(currencyType != ofa.getUserAccountType()){
					currencyType = 3;
				}
			}
		}
		//资金类型 根据流水类型进行判断
		ab.setCurrencyType(currencyType);
		accountBatchWriteDAO.insert(ab);

		Long batchId = ab.getId();

		// 循环(查询账户,加密校验,余额变更+加密赋值,生成流水)
		for (CashFlowAccountPO ofa : outFlowAccs) {

			//目前多对一资金流动,仅应用于企业和用户失效
			//该循环中的单步资金流动逻辑应仅对积分账户和点赞福豆账户生效
			// 对于积分冻结结账户和现金支付账户,应使用单步资产清零方法
			if (ofa.getUserAccountType() <= 1) {
				singleStepCashFlowWithTx(ofa, outFlowAccType, inFlowAcc, inFlowAccType,platformId,type,orderId, orderCode, operatorId,reasonId, remark,isRecharge, batchId,null);
//				singleStepCashFlowWithTx(ofa, outFlowAccType, ifa, inFlowAccType, platformId, type, orderId, orderCode, operatorId, reasonId, remark, isRecharge, batchId);
			} else if (ofa.getUserAccountType() == 3){
				singleStepAssetsClearWithTx(ofa);
			}
		}
		CashFlowResultPO res = new CashFlowResultPO();
		res.setBatchId(batchId);
		res.setRaBatchNo(raBatch);
		res.setResult(true);
		return res;
	}
	/**
	 * 事务处理多对单资金流动
	 *
	 * @return
	 */
	private CashFlowResultPO m2oWithTx(List<CashFlowAccountPO> outFlowAccs, Integer outFlowAccType,
											   List<CashFlowAccountPO> inFlowAccs, Integer inFlowAccType, boolean nonNegLimit, Long platformId,
											   Integer type, Long orderId, String orderCode, Long operatorId, int finBatchType, Long reasonId,
											   String remark, boolean isRecharge, Integer operateType) {
				// 统计批次总金额
				BigDecimal sum =BigDecimal.valueOf(0.00);
		// 统计批次总流水条数
		int flowAmount = 0;
		//在多对单流动中,每笔流动金额记录再流出账户列表中
		for (CashFlowAccountPO cfa : outFlowAccs) {
			//退款账户
			if (cfa.getUserAccountType() <= 1 || cfa.getUserAccountType().intValue()==4) {
				BigDecimal outSum=cfa.getSum();
				if(outSum.compareTo(BigDecimal.valueOf(0.00))==1) {
					//sum大于0才计算流水
					sum = sum.add(outSum);
					flowAmount++;
				}
			}
		}
		if(sum.equals(BigDecimal.valueOf(0.00))) {
			return new CashFlowResultPO(false);
		}


		//流入账户信息
		CashFlowAccountPO inFlowAcc=inFlowAccs.get(0);

		//流入账户加密校验
		checkAccountCipher(inFlowAcc,inFlowAccType);
		//流出账户加密校验
		for (CashFlowAccountPO cfa : outFlowAccs) {
			checkAccountCipher(cfa,outFlowAccType);
		}
		// 流入账户id
		Long inFlowAccId = inFlowAcc.getAccountId();

		// 生成批次
		String raBatch = SequenceUtil.genRaBatchNo();
		String finBatch=SequenceUtil.genFinBatchNo(finBatchType);
		//多对一批次不关心流出账户id
		AccountBatchPO ab = new AccountBatchPO();
		ab.setFlowAmount(flowAmount);
		ab.setInflowAccountid(inFlowAccId);
		ab.setOperatorId(operatorId);
		ab.setPlatformId(platformId);
		ab.setRaBatch(raBatch);
		ab.setFinBatch(finBatch);
		ab.setReasonId(reasonId);
		ab.setRemark(remark);
		ab.setSum(sum);
		ab.setType(type);
		// 货币类型，为空进行一次赋值、如果下次判断不一样则为多种货币类型
		Integer currencyType = null;
		for (CashFlowAccountPO ofa : outFlowAccs) {
			if(currencyType == null){
				currencyType = ofa.getUserAccountType();
			}else{
				if(currencyType != ofa.getUserAccountType()){
					currencyType = 3;
				}
			}
		}
		//资金类型 根据流水类型进行判断
		ab.setCurrencyType(currencyType);
		accountBatchWriteDAO.insert(ab);
		Long batchId = ab.getId();

		// 循环(查询账户,加密校验,余额变更+加密赋值,生成流水)
		for (CashFlowAccountPO ofa : outFlowAccs) {

			//目前多对一资金流动,仅应用于企业和用户失效
			//该循环中的单步资金流动逻辑应仅对积分账户和点赞福豆账户生效
			// 对于积分冻结结账户和现金支付账户,应使用单步资产清零方法
			if (ofa.getUserAccountType() <= 1) {
				singleStepCashFlowWithTx(
						ofa, outFlowAccType, inFlowAcc, inFlowAccType,
						platformId,type,
						orderId, orderCode, operatorId,
						reasonId, remark,
						isRecharge, batchId,null);
			} else if (ofa.getUserAccountType() == 3){
				singleStepAssetsClearWithTx(ofa);
			}
		}
		CashFlowResultPO res = new CashFlowResultPO();
		res.setBatchId(batchId);
		res.setRaBatchNo(raBatch);
		res.setResult(true);
		return res;
	}

	/**
	 * 单步资产清零接口
	 */
	private void singleStepAssetsClearWithTx(CashFlowAccountPO outFlowAcc) {
		//资产清零不关心加密是否正确
		//将账户的余额降为0
		Long accountId=outFlowAcc.getAccountId();
		UserAccountPO ua=userAccountReadDAO.queryUserAccountById(accountId);
		if(ua!=null) {
			userAccountWriteDAO.clearUserAccount(accountId,outFlowAcc.getSalt());
		}
	}
	@Resource
private SoClient soClient;
	/**
	 * 单步资金流动方法:<br>
	 * 任何一种资金流动形式(单对单 单对多 多对单),都可以拆分成一对一的流动<br>
	 * 在调用之前,注意进行:
	 * 1.金额加密的校验
	 * 2.非负校验,
	 * 3.统计流水条数(考虑0资金流动)
	 * 这个方法统一执行这些操作:<br>
	 * 1.两个账户的余额变更和加密值变更,加密值变更使用mySql的md5函数与余额增减同步赋值<br>
	 * 所以,如果更改了java中MD5方法的逻辑,一定要同时更换mysql函数
	 * 2.生成一条流水<br>
	 */
	private void singleStepCashFlowWithTx(
			CashFlowAccountPO outFlowAcc,Integer outFlowAccType,
			CashFlowAccountPO inFlowAcc,Integer inFlowAccType,
			Long platformId, Integer type, Long orderId, String orderCode,
			Long operatorId, Long reasonId, String remark, boolean isRecharge,Long batchId,String otherInfo) {


		//单步流水金额,按照前面的规则,流水金额可能被记录在流出账户或流入账户中
		//通过累加可以获取这次单步流动的实际金额
		BigDecimal sum=BigDecimal.valueOf(0.00);

		if(EmptyUtil.isNotEmpty(inFlowAcc.getSum())&&inFlowAcc.getSum().compareTo(BigDecimal.ZERO)>0){
			sum=inFlowAcc.getSum();
		}else{
			sum=outFlowAcc.getSum();
		}
		logger.info("sum"+sum);
		//如果资金流动量为0,不执行操作
		if(sum.compareTo(BigDecimal.valueOf(0.00))==0) {
			return;
		}
		//判断资金类型,需要用户账户类型进行辅助判断的情况仅员工账户失效时的资金回收
		//故获取流出账户的用户账户类型
		Integer userAccountType=outFlowAcc.getUserAccountType();
		if (type == 14) {
			userAccountType = inFlowAcc.getUserAccountType();
		}
		//流出账户的新加密值
		if(outFlowAccType==0) {
			//企业账户
			companyAccountWriteDAO.increaseAccountBalance(outFlowAcc.getAccountId(), sum.negate(), isRecharge,outFlowAcc.getSalt());
		}else {
			//用户账户
			UserAccountPO queryUA = new UserAccountPO();
			queryUA.setId(outFlowAcc.getAccountId());
			UserAccountPO userAccountPo = userAccountReadDAO.findById(queryUA);
			UserExtendDTO userExtDTO = userExtendClient.userByUserId(userAccountPo.getUserId());
			boolean isDlfUser = false;
			if(null !=userExtDTO && EmptyUtil.isNotEmpty(userExtDTO.getChannelSource()) && Objects.equals(userExtDTO.getChannelSource(), UserChannelSourceEnum.DLF.getChannelSource())){
				isDlfUser = true;
			}
			boolean isYd = false;
			if(null !=userExtDTO && EmptyUtil.isNotEmpty(userExtDTO.getChannelSource()) && Objects.equals(userExtDTO.getChannelSource(), UserChannelSourceEnum.YD.getChannelSource())){
				isYd = true;
			}
			if(userAccountPo.getType().intValue()==0) {
				//如果是扣款账户
				Integer account0Type = 0;
				List<CompanyConfigDTO> companyConfigs = userClient.findUserCompanyConfigs(userAccountPo.getUserId());
				for(CompanyConfigDTO config : companyConfigs) {
					if(config.getKey().equalsIgnoreCase("account.0.type")) {
						account0Type = Integer.valueOf(config.getValue());
					}
				}

				if(account0Type.intValue()==1 && !isDlfUser && !isYd){
					Boolean paySuccess = false;
					//外部扣款，后续需要优化bean
					String payType = null;
					String payUrl = null;
					for(CompanyConfigDTO config :companyConfigs){
						if(config.getKey().equalsIgnoreCase("account.0.payurl")){
							payUrl = config.getValue();
						}else if(config.getKey().equalsIgnoreCase("account.0.paytype")){
							payType = config.getValue();
						}
					}
					if(payType.equalsIgnoreCase("shxsyy")&& payUrl!=null){
						String queryAccountUrl="";
						for(CompanyConfigDTO config :companyConfigs) {
							if(config.getKey().equalsIgnoreCase("account.0.url")){
								queryAccountUrl=config.getValue();
							}
						}
						if(queryAccountUrl!=null) {
							UserExtendDTO extDTO = userExtendClient.userByUserId(userAccountPo.getUserId());
							if(extDTO!=null&&extDTO.getRemark()!=null) {
								logger.info("第三方查询账号："+queryAccountUrl+extDTO.getRemark());
								String queryAccountJsonStr = HttpClientUtil.doGet(queryAccountUrl+extDTO.getRemark());
								JSONObject parseQueryAccountObject = JSONObject.parseObject(queryAccountJsonStr);
								logger.info("第三方查询账号结果："+queryAccountJsonStr);
								if(parseQueryAccountObject.containsKey("code") && parseQueryAccountObject.getInteger("code").intValue()==0) {
									JSONObject queryAccountparseData = parseQueryAccountObject.getJSONObject("data");
									if(queryAccountparseData.containsKey("cardNo") && queryAccountparseData.getString("cardNo")!=null) {
										String cardNo = queryAccountparseData.getString("cardNo");

										logger.info("第三方扣款："+payUrl.replace("replaceCardNo", cardNo).replace("replacemoney", sum.toPlainString()));
										String payJsonStr = HttpClientUtil.doGet(payUrl.replace("replaceCardNo", cardNo).replace("replacemoney", sum.toPlainString()));
										logger.info("第三方扣款结果："+payJsonStr);
										JSONObject parsePayObject = JSONObject.parseObject(payJsonStr);
										if(parsePayObject.containsKey("code") && parsePayObject.getInteger("code").intValue()==0) {
											paySuccess = true;
										}

									}
								}
							}
						}
					}

					if(!paySuccess) {
						throw new BusinessException("扣款失败");
					}
				}else if(isDlfUser){
					ThirdUserPay(userExtDTO, ChannelServiceNameEnum.USER_FANKA_PAY,sum,orderCode);
				}else if(isYd){
					ThirdYdUserPay(userExtDTO, ChannelServiceNameEnum.USER_FANKA_PAY,sum,orderCode);
				}else {
					userAccountWriteDAO.increaseUserAccount(outFlowAcc.getAccountId(), sum.negate(), isRecharge,outFlowAcc.getSalt());
				}
			}else if(userAccountPo.getType().intValue()==5 && isDlfUser){
				ThirdUserPay(userExtDTO, ChannelServiceNameEnum.USER_JIDIAN_PAY,sum,orderCode);
			}else if(userAccountPo.getType().intValue()==6){
				useCard(orderId, orderCode, otherInfo);
			}else if(userAccountPo.getType().intValue()==7){
				useCard(orderId, orderCode, otherInfo);
			} else {
				//其他账户
				userAccountWriteDAO.increaseUserAccount(outFlowAcc.getAccountId(), sum.negate(), isRecharge,outFlowAcc.getSalt());
			}





		}

		if(inFlowAccType==0) {
			//企业账户
			companyAccountWriteDAO.increaseAccountBalance(inFlowAcc.getAccountId(), sum, isRecharge,inFlowAcc.getSalt());
		}else {

			/*//用户账户
			UserAccountPO queryUA = new UserAccountPO();
			queryUA.setId(outFlowAcc.getAccountId());
			UserAccountPO userAccountPo = userAccountReadDAO.findById(queryUA);
			if(userAccountPo.getType().intValue()==0) {
				//如果往用户积分账户存钱
				//如果account.refund.type==4  退款就往退款账户存钱

			}else {

			}*/
			List<Integer> list = Arrays.asList(FinBatchConstant.ORDER_REFUND_JIDIAN,FinBatchConstant.ORDER_REFUND_CGK);
			if(!list.contains(type)){
				userAccountWriteDAO.increaseUserAccount(inFlowAcc.getAccountId(), sum, isRecharge,inFlowAcc.getSalt());
			}

		}
		//生成流水
		AccountFlowPO flow=new AccountFlowPO();
		if(orderId!=null) {
			SoDataDTO order = orderFacade.querySoData(orderId);
			flow.setEnterpriseId(order.getEnterpriseId());
			flow.setCompanyId(order.getOrder().getCompanyId());
			List<SoItemDTO> items = order.getItems();
			BigDecimal ledgerPlateformTotal = new BigDecimal(0);
			BigDecimal ledgerEnterpriseTotal = new BigDecimal(0);
			for(SoItemDTO item : items) {
				if(item.getSource()!=null && item.getSource()==3 && item.getSnapshot()!=null) {
					JdProductDTO jdProduct = JSON.parseObject(item.getSnapshot(), JdProductDTO.class);
					String ledger = jdProduct.getLedger();
					if(ledger!=null && ledger.length()>5) {
						JSONObject ledgerObj = JSON.parseObject(ledger);
						BigDecimal ledgerPlateform = ledgerObj.getBigDecimal("ledgerPlateform");
						BigDecimal ledgerEnterprise = ledgerObj.getBigDecimal("ledgerEnterprise");
						ledgerPlateform.setScale(2, RoundingMode.HALF_UP);
						ledgerEnterprise.setScale(2, RoundingMode.HALF_UP);
						ledgerPlateformTotal = ledgerPlateformTotal.add(ledgerPlateform);
						ledgerEnterpriseTotal = ledgerEnterpriseTotal.add(ledgerEnterprise);
					}
				}
			}

			flow.setLedgerEnterprise(ledgerEnterpriseTotal);
			flow.setLedgerPlateform(ledgerPlateformTotal);
		}else {

			if(type==0) {
				Long accountId = inFlowAcc.getAccountId();
				CompanyAccountPO po = new  CompanyAccountPO();
				po.setId(accountId);
				CompanyAccountPO company = companyAccountReadDAO.findById(po);
				CompanyDTO companyDto = companyClient.findCompanyById(company.getCompanyId());
				flow.setEnterpriseId(companyDto.getEnterpriseId());
				flow.setCompanyId(companyDto.getId());
			}else if(type==7||type==8||type==10) {
				Long accountId = inFlowAcc.getAccountId();
				UserAccountPO po = new  UserAccountPO();
				po.setId(accountId);
				UserAccountPO user = userAccountReadDAO.findById(po);
				UserDTO userDto = userClient.findUserByID(user.getUserId());
				flow.setEnterpriseId(userDto.getEnterpriseId());
				flow.setCompanyId(userDto.getCompanyId());
			}else if(type==9) {
				Long accountId = outFlowAcc.getAccountId();
				UserAccountPO po = new  UserAccountPO();
				po.setId(accountId);
				UserAccountPO user = userAccountReadDAO.findById(po);
				UserDTO userDto = userClient.findUserByID(user.getUserId());
				flow.setEnterpriseId(userDto.getEnterpriseId());
				flow.setCompanyId(userDto.getCompanyId());
			}
		}
		flow.setBatchId(batchId);
		flow.setInflowAccountid(inFlowAcc.getAccountId());
		flow.setInflowAccounttype(inFlowAccType);
		flow.setOrderCode(orderCode);
		flow.setOrderId(orderId);
		flow.setOutflowAccountid(outFlowAcc.getAccountId());
		flow.setOutflowAccounttype(outFlowAccType);
		flow.setPlatformId(platformId);
		flow.setReasonId(reasonId);
		flow.setRemark(remark);
		if(inFlowAcc.getSubRemark()!=null) {
			flow.setRemark(inFlowAcc.getSubRemark());
		}
		//资金类型 根据流水类型进行判断
		flow.setCurrencyType(judgeCurrencyType(type,userAccountType));
		flow.setSum(sum);
		flow.setType(type);
		accountFlowWriteDAO.insert(flow);
	}

	private void useCard(Long orderId, String orderCode, String otherInfo) {
		logger.info("订单:{}执行到卡劵支付扣款流程",orderCode);
		BuyCardPayReqVO vo = new BuyCardPayReqVO();
		vo.setOrderId(orderId);
		vo.setOrderCode(orderCode);
		vo.setCardIds(otherInfo);
		BuyCardPayRespVO respVO = soClient.buyCardPay(vo);
		if(!respVO.getResult()){
			throw new BusinessException(BusinessException.ERR_ACCOUNT_NOTFOUND,"扣款失败"+respVO.getMsg());
		}
	}

	private void ThirdUserPay(UserExtendDTO userExtDTO, ChannelServiceNameEnum userJidianPay,BigDecimal sum,String orderSN) {
		RemoteExecuteDTO executeDTO = new RemoteExecuteDTO();
		executeDTO.setEnterpriseId(userExtDTO.getCompanyId().intValue());
		executeDTO.setChannelCode(userExtDTO.getChannelSource());
		executeDTO.setChannelServiceName(userJidianPay.getChannelServiceName());
		executeDTO.setChannelServiceType(ChannelServiceTypeEnum.REQ.getChannelServiceType());
		executeDTO.setBizCode(orderSN);
		executeDTO.setNextBizCode(userExtDTO.getMobile());
		//{\"mobile\":\"18616010367\",\"paymoney\":\"0.01\",\"ordersn\":\"aaaa\"}
		JSONObject dataJson = new JSONObject();
		dataJson.put("mobile",userExtDTO.getMobile());
		dataJson.put("paymoney",sum.toPlainString());
		dataJson.put("ordersn",orderSN);
		executeDTO.setJsonString(JSON.toJSONString(dataJson));
		JsonResult jsonResult = channelServiceConfigClient.remoteExecute(executeDTO);
		if (jsonResult == null || jsonResult.getCode() != 0 || null == jsonResult.getData()) {
			throw new BusinessException(BusinessException.ERR_ACCOUNT_NOTFOUND,"扣款失败");
		}
		JSONObject parseObject = JSONObject.parseObject(JSON.toJSONString(jsonResult.getData()));
		if (!"1".equals(parseObject.getString("status"))) {
			logger.error("扣款失败" + (parseObject.containsKey("msg") ? parseObject.getString("msg") : ""));
			throw new BusinessException(BusinessException.ERR_ACCOUNT_NOTFOUND,"扣款失败");
		}
	}


	private void ThirdYdUserPay(UserExtendDTO userExtDTO, ChannelServiceNameEnum userPay,BigDecimal sum,String orderSN) {
		UserDTO userDTO = userClient.findUserByID(userExtDTO.getId());
		RemoteExecuteDTO executeDTO = new RemoteExecuteDTO();
		executeDTO.setEnterpriseId(userExtDTO.getCompanyId().intValue());
		executeDTO.setChannelCode(userExtDTO.getChannelSource());
		executeDTO.setChannelServiceName(userPay.getChannelServiceName());
		executeDTO.setChannelServiceType(ChannelServiceTypeEnum.REQ.getChannelServiceType());
		executeDTO.setBizCode(orderSN);
		executeDTO.setNextBizCode(StringUtils.isNotEmpty(userExtDTO.getMobile())?userExtDTO.getMobile():userDTO.getChannelUserUnique());
		JSONObject dataJson = new JSONObject();
		dataJson.put("user_id",userDTO.getChannelUserUnique());
		dataJson.put("paymoney",sum.toPlainString());
		dataJson.put("type","1");
		dataJson.put("ordersn",orderSN);
		executeDTO.setJsonString(JSON.toJSONString(dataJson));
		JsonResult jsonResult = channelServiceConfigClient.remoteExecute(executeDTO);
		if (jsonResult == null || jsonResult.getCode() != 0 || null == jsonResult.getData()) {
			throw new BusinessException(BusinessException.ERR_ACCOUNT_NOTFOUND,"扣款失败");
		}
		JSONObject parseObject = JSONObject.parseObject(JSON.toJSONString(jsonResult.getData()));
		if (!"1".equals(parseObject.getString("status"))) {
			logger.error("扣款失败" + (parseObject.containsKey("msg") ? parseObject.getString("msg") : ""));
			throw new BusinessException(BusinessException.ERR_ACCOUNT_NOTFOUND,"扣款失败");
		}
	}

	/**
	 * 根据流水类型判断货币类型
	 * type字段的意义见account.adjust_reason表的type字段注释
	 * 如果type=9:员工账户失效
	 * 再根据流出账户的员工账户类型进一步判断
	 * @param type
	 * @param userAccountType
	 * @return 货币类型 0:积分 1:点赞福豆 2:现金
	 */
	private Integer judgeCurrencyType(Integer type, Integer userAccountType) {
		switch (type) {
		case 0:
		case 1:
		case 2:
		case 3:
		case 5:
		case 11:
		case 13:
			return 2;
		case 4:
		case 6:
		case 7:
			return 0;
		case 8:
		case 10:
			return 1;
		case 9:
		case 14:
			if(userAccountType==null) {
				return null;
			}else if(userAccountType==0){
				return 0;
			}else {
				return 1;
			}
		}
		return null;
	}

	/**
	 * 事务处理单对多资金流动
	 *
	 * @return
	 */
	private CashFlowResultPO o2mWithTx(List<CashFlowAccountPO> outFlowAccs, Integer outFlowAccType,
			List<CashFlowAccountPO> inFlowAccs, Integer inFlowAccType, boolean nonNegLimit, Long platformId,
			Integer type, Long orderId, String orderCode, Long operatorId, int finBatchType, Long reasonId,
			String remark, boolean isRecharge, Integer operateType) {
		// 统计批次总金额
		BigDecimal sum=BigDecimal.valueOf(0);
		// 统计批次总流水条数
		int flowCount=0;
		CashFlowAccountPO ofa=outFlowAccs.get(0);
		Long accountId=ofa.getAccountId();
		CompanyAccountPO ca=companyAccountReadDAO.queryCompanyAccountById(accountId);
		if(ca==null)
			throw new BusinessException("流出账户不存在");
		for(CashFlowAccountPO cfa:inFlowAccs) {
			BigDecimal inSum=cfa.getSum();
			if(inSum.compareTo(BigDecimal.valueOf(0.00))==1) {
				//sum大于0才算流水
				sum=sum.add(inSum);
				flowCount++;
			}
		}
		if(sum.equals(BigDecimal.valueOf(0.00)))
			return new CashFlowResultPO(false);

		//加密校验(流出账户)
		checkAccountCipher(ofa,outFlowAccType);
		//加密校验(流入账户)
		for(CashFlowAccountPO ifa:inFlowAccs) {
			checkAccountCipher(ifa,inFlowAccType);
		}

		// 生成批次
		//单对多不关心流入账户的id
		//单对多关心公司id,从流出账户中取出公司id
		String raBatch = SequenceUtil.genRaBatchNo();
		String finBatch=SequenceUtil.genFinBatchNo(finBatchType);
		AccountBatchPO ab = new AccountBatchPO();
		ab.setFinBatch(finBatch);
		ab.setFlowAmount(flowCount);
		ab.setOperatorId(operatorId);
		ab.setOutflowAccountid(accountId);
		ab.setPlatformId(platformId);
		ab.setRaBatch(raBatch);
		ab.setReasonId(reasonId);
		ab.setRemark(remark);
		ab.setSum(sum);
		ab.setType(type);
		//资金类型 根据流水类型进行判断
		// 货币类型，为空进行一次赋值、如果下次判断不一样则为多种货币类型
		Integer currencyType = null;
		for (CashFlowAccountPO ifa : inFlowAccs) {
			if(currencyType == null){
				currencyType = ifa.getUserAccountType();
			}else{
				if(currencyType != ofa.getUserAccountType()){
					currencyType = 3;
				}
			}
		}
		ab.setCurrencyType(judgeCurrencyType(type,currencyType));
		ab.setCompanyId(ca.getCompanyId());
		accountBatchWriteDAO.insert(ab);
		Long batchId = ab.getId();
		// 循环(查询账户,加密校验,余额变更+加密赋值,如果是冻结账户或者现金支付账户,直接清零,生成流水)
		for(CashFlowAccountPO cfa:inFlowAccs) {
			singleStepCashFlowWithTx(ofa, outFlowAccType,cfa, inFlowAccType, platformId, type, orderId, orderCode, operatorId, reasonId, remark, isRecharge, batchId,null);
		}
		CashFlowResultPO res = new CashFlowResultPO();
		res.setBatchId(null);
		res.setRaBatchNo(null);
		res.setResult(true);
		return res;
	}

	/**
	 * 事务处理单对单资金流动
	 *
	 * @return
	 */
	private CashFlowResultPO o2oWithTx(List<CashFlowAccountPO> outFlowAccs, Integer outFlowAccType,
			List<CashFlowAccountPO> inFlowAccs, Integer inFlowAccType, boolean nonNegLimit, Long platformId,
			Integer type, Long orderId, String orderCode, Long operatorId, int finBatchType, Long reasonId,
			String remark, boolean isRecharge, Integer operateType,String otherInfo) {
		CashFlowAccountPO ofa=outFlowAccs.get(0);
		CashFlowAccountPO ifa=inFlowAccs.get(0);
		// 统计批次总金额
		BigDecimal sum=ofa.getSum();
		if(sum.equals(BigDecimal.valueOf(0.00)))
			return new CashFlowResultPO(false);

		//加密校验(流出账户)
		checkAccountCipher(ofa,outFlowAccType);
		//加密校验(流入账户)
		checkAccountCipher(ifa,inFlowAccType);

		// 统计批次总流水条数
		int flowCount=1;
		// 生成批次
		String raBatch = SequenceUtil.genRaBatchNo();
		String finBatch=SequenceUtil.genFinBatchNo(finBatchType);
		AccountBatchPO ab = new AccountBatchPO();
		ab.setFinBatch(finBatch);
		ab.setFlowAmount(flowCount);
		ab.setInflowAccountid(ifa.getAccountId());
		ab.setOperatorId(operatorId);
		ab.setOutflowAccountid(ofa.getAccountId());
		ab.setPlatformId(platformId);
		ab.setRaBatch(raBatch);
		ab.setReasonId(reasonId);
		ab.setRemark(remark);
		ab.setSum(sum);
		ab.setType(type);
		//资金类型 根据流水类型进行判断
		ab.setCurrencyType(judgeCurrencyType(type,null));
		accountBatchWriteDAO.insert(ab);
		Long batchId = ab.getId();
		// 单步方法
		singleStepCashFlowWithTx(ofa, outFlowAccType, ifa, inFlowAccType, platformId, type, orderId, orderCode, operatorId, reasonId, remark, isRecharge, batchId,otherInfo);

		CashFlowResultPO res = new CashFlowResultPO();
		res.setBatchId(batchId);
		res.setRaBatchNo(raBatch);
		res.setResult(true);
		return res;
	}

	/**
	 * 校验账户加密值
	 * @param account
	 * @param accountType 账户类型 0:企业 1:用户
	 */
	private void checkAccountCipher(CashFlowAccountPO account,Integer accountType) {
		Long accountId=account.getAccountId();
		String salt=account.getSalt();
		if(accountType==0) {
			//企业
			CompanyAccountPO ca=companyAccountReadDAO.queryCompanyAccountById(accountId);
			BigDecimal balance=ca.getBalance();
			String cipher=ca.getCiphertext();
			if(!MD5Util.md5Valid(balance.toString(), salt, cipher)) {
				throw new BusinessException("id为"+accountId+"的企业账户余额意思被篡改");
			}
		}else {

			//用户
			UserAccountPO ua=userAccountReadDAO.queryUserAccountById(accountId);
			if(ua.getType().intValue()==5 || ua.getType().intValue()==6 || ua.getType().intValue()==7){
				//TODO 先测试通过
				return;
			}
			BigDecimal balance=ua.getBalance();
			String cipher=ua.getCiphertext();
			if(!MD5Util.md5Valid(balance.toString(), salt, cipher)) {
				throw new BusinessException("id为"+accountId+"的企业账户余额意思被篡改");
			}
		}
	}
}
