package com.egeo.components.finance.business.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.third.client.ChannelServiceConfigClient;
import com.egeo.components.third.dto.RemoteExecuteDTO;
import com.egeo.components.third.enums.ChannelServiceNameEnum;
import com.egeo.components.third.enums.ChannelServiceTypeEnum;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.enums.UserChannelSourceEnum;
import com.egeo.util.security.MD5Util;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.egeo.common.BusinessExceptionConstant;
import com.egeo.components.config.dto.CompanyConfigDTO;
import com.egeo.components.config.dto.HeadImportRecordsDTO;
import com.egeo.components.config.dto.ImportRecordsDTO;
import com.egeo.components.config.dto.SaltDTO;
import com.egeo.components.finance.business.AccountManage;
import com.egeo.components.finance.common.BusinessException;
import com.egeo.components.finance.common.CiphertextUtil;
import com.egeo.components.finance.constant.FinBatchConstant;
import com.egeo.components.finance.constant.FlowTypeConstant;
import com.egeo.components.finance.dto.AccountBatchDTO;
import com.egeo.components.finance.dto.AccountBatchTmpDTO;
import com.egeo.components.finance.dto.AccountFlowDTO;
import com.egeo.components.finance.dto.AdjustReasonDTO;
import com.egeo.components.finance.dto.CashFlowAccountDTO;
import com.egeo.components.finance.dto.CompanyAccountDTO;
import com.egeo.components.finance.dto.ReasonCompanyDTO;
import com.egeo.components.finance.dto.TempRechargeDTO;
import com.egeo.components.finance.dto.UserAccountDTO;
import com.egeo.components.finance.facade.AccountFacade;
import com.egeo.components.finance.facade.ImportRecordsFacade;
import com.egeo.components.finance.facade.SecretFacade;
import com.egeo.components.finance.facade.UserFacade;
import com.egeo.components.finance.vo.AccountBalanceVO;
import com.egeo.components.finance.vo.AccountBatchVO;
import com.egeo.components.finance.vo.AccountFlowVO;
import com.egeo.components.finance.vo.AdjustReasonVO;
import com.egeo.components.finance.vo.AssetVO;
import com.egeo.components.finance.vo.CompanyAccountDetailVO;
import com.egeo.components.finance.vo.CompanyAccountVO;
import com.egeo.components.finance.vo.HeadInfoVO;
import com.egeo.components.finance.vo.RechargePreviewVO;
import com.egeo.components.finance.vo.RechargeRecDetailVO;
import com.egeo.components.finance.vo.RechargeRecVO;
import com.egeo.components.finance.vo.SimpleAccountVO;
import com.egeo.components.finance.vo.UserAccountVO;
import com.egeo.components.finance.vo.UserFinFlowVO;
import com.egeo.components.finance.vo.UserRechargeExcelVO;
import com.egeo.components.order.client.SoClient;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.client.UserExtendClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.utils.DateUtil;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.Upload;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.excel2.ExcelHeadChecker;
import com.egeo.utils.excel2.ExcelTmplConstant;
import com.egeo.utils.excel2.PropblemReportRowVO;
import com.egeo.utils.http.HttpClientUtil;
import com.egeo.utils.log.XLogger;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.JsonResult;

@Service("account")
public class AccountManageImpl implements AccountManage {

	@Resource(name = "accountFacade")
	private AccountFacade accountFacade;

	@Resource(name = "userFacade")
	private UserFacade userFacade;

	@Resource(name = "secretFacade")
	private SecretFacade secretFacade;

	@Autowired
	private SoClient soService;

	@Resource(name = "importRecordsFacade")
	private ImportRecordsFacade importRecordsFacade;

	@Resource
	private JedisUtil jedisUtil;
	@Autowired
	private Upload uploadService;

	@Autowired
	private UserExtendClient userExtService;
	@Autowired
	private UserClient userService;

	@Autowired
	private ChannelServiceConfigClient channelServiceConfigClient;

	private static final XLogger logger = XLogger.getLogger(AccountManageImpl.class);

	@Override
	public JsonResult<Map<String, Object>> companyAccountPage(Integer pageNo, Integer pageSize, String accountName,
			List<Long> companyId, Integer disabled, Long platformId) {
		if (pageNo == null)
			pageNo = 1;
		if (pageSize == null)
			pageSize = 20;
		if (EmptyUtil.isBlank(accountName))
			accountName = null;
		Pagination page = new Pagination(pageNo, pageSize);
		PageResult<CompanyAccountDTO> caPage = accountFacade.queryCompanyAccountPage(page, accountName, companyId,
				disabled,platformId);
		List<CompanyAccountDTO> caList = caPage.getList();
		List<CompanyAccountVO> cavList = new ArrayList<>();
		if (caList.size() > 0) {
			// 查询所有公司
			List<CompanyDTO> coms = userFacade.queryCompanyList(platformId);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			for (CompanyAccountDTO ca : caList) {
				CompanyAccountVO vo = new CompanyAccountVO();
				vo.setBalance(ca.getBalance());
				long companyId_ = ca.getCompanyId();
				vo.setCompanyId(companyId_);
				for (CompanyDTO c : coms) {
					if (companyId_ == c.getId().longValue()) {
						vo.setCompanyName(ca.getName());
						break;
					}
				}
				vo.setCreateTime(sdf.format(ca.getCreateTime()));
				vo.setDisabled(ca.getDisabled());
				vo.setId(ca.getId());
				vo.setName(ca.getName());
				vo.setType(ca.getType());
				cavList.add(vo);
			}
		}
		PageResult<CompanyAccountVO> voPage = new PageResult<>();
		voPage.setList(cavList);
		voPage.copy(caPage);
		return JsonResult.success("accountPage", voPage);
	}

	@Override
	public JsonResult<Map<String, Object>> accountOperateReasons(Long companyId) {
		List<AdjustReasonDTO> dtoList = accountFacade.queryAdjustReasons(companyId);
		return JsonResult.success("reasons", dtoList);
	}

	@Override
	public JsonResult<Map<String, Object>> raSubmitForExam(Long accountId, Double sum, Long reasonId, String remark,
			String finBatch, Long platformId, Long userId) {
		// 参数校验
		if (accountId == null)
			return JsonResult.fail("请选择账户");
		if (sum == null || sum.doubleValue() == 0d)
			return JsonResult.fail("请输入非零金额");
		if (reasonId == null) {
			return JsonResult.fail("请输入原因");
		}
		CompanyAccountDTO ca = accountFacade.queryCompanyAccountById(accountId);
		// 根据原因查看type
		AdjustReasonDTO reason = accountFacade.queryAdjustReasonById(reasonId);
		if (reason == null) {
			return JsonResult.fail("原因不存在");
		}
		int type = reason.getType().intValue();
		if (type == FlowTypeConstant.CA_RECHARGE.getStatus()) {
			// 如果是充值.sum>0
			if (sum.doubleValue() <= 0) {
				return JsonResult.fail("根据您选择的原因,金额必须是正数");
			}
		} else if (type == FlowTypeConstant.CA_ADJUST_IN.getStatus()) {
			// 调整收入
			if (sum.doubleValue() <= 0) {
				return JsonResult.fail("根据您选择的原因,金额必须是正数");
			}
		} else if (type == FlowTypeConstant.CA_ADJUST_OUT.getStatus()) {
			if (sum.doubleValue() >= 0) {
				return JsonResult.fail("根据您选择的原因,金额必须是负数");
			}
			//判断绝对值
			if(Math.abs(sum)>ca.getBalance().doubleValue()){
				return JsonResult.fail("调整金额超过账户余额");
			}
		} else {
			return JsonResult.fail("您选择了一个不合适的原因");
		}

		if (EmptyUtil.isNotBlank(remark) && remark.length() > 200) {
			return JsonResult.fail("备注不得超过200字");
		}
		// 验证批次号重复性
		if (EmptyUtil.isNotBlank(finBatch)) {
			AccountBatchDTO ab = accountFacade.queryAccountBatchByFinBatch(finBatch);
			if (ab != null) {
				return JsonResult.fail("财务批次号重复");
			}
			AccountBatchTmpDTO abt = accountFacade.queryAccountBatchTmpByFinBatch(finBatch);
			if (abt != null) {
				return JsonResult.fail("财务批次号重复");
			}
		}
		// 验证目标账户的类型是否是普通账户
		if (ca == null)
			return JsonResult.fail("目标账户不存在");
		if (ca.getType().intValue() != 20)
			return JsonResult.fail("目标账户不是普通账户");
		// 将结果提交至审核表
		Long companyId = ca.getCompanyId();

		//判断是否存在账户锁
		String accountKey = jedisUtil.getString(JedisUtil.ACCOUNT_DISABLED_LOCK_KEY_PRE + companyId);
		if(EmptyUtil.isNotEmpty(accountKey)){
			logger.info("正在进行企业账户失效操作，请稍后再试,code="+BusinessExceptionConstant.ACCOUNT_LOCKED);
			return JsonResult.fail("企业账户已冻结，无法进行账户充值或调整",BusinessExceptionConstant.ACCOUNT_LOCKED);
		}

		accountFacade.raSubmitForExam(accountId, sum, reasonId, finBatch, remark, platformId, userId, companyId, type);
		return JsonResult.success();
	}

	@Override
	public JsonResult<Map<String, Object>> companyAccountDetail(Long id) {
		if (id == null)
			return JsonResult.fail("请选择账户");
		CompanyAccountDTO ca = accountFacade.queryCompanyAccountById(id);
		if (ca == null)
			return JsonResult.fail("账户不存在");
		CompanyAccountDetailVO cad = new CompanyAccountDetailVO();
		cad.setBalance(ca.getBalance().doubleValue());
		cad.setDisabled(ca.getDisabled());
		cad.setId(ca.getId());
		Date lastRecharge = ca.getLastRechargeTime();
		cad.setLastRecharge(lastRecharge == null ? null : lastRecharge.getTime());
		cad.setName(ca.getName());
		cad.setType(ca.getType());
		return JsonResult.success("account", cad);
	}

	@Override
	public JsonResult<Map<String, Object>> changeAccountDisable(Long id, Integer disabled, Long userId, Long platformId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dialog",0);
		if (id == null)
			return JsonResult.fail("请选择账户");
		if (disabled == null)
			return JsonResult.fail("参数错误");
		int dis = disabled.intValue();
		CompanyAccountDTO ca = accountFacade.queryCompanyAccountById(id);
		if (ca == null)
			return JsonResult.fail("账户不存在");
		if (ca.getType().intValue() != 20)
			return JsonResult.fail("特殊账户不能更改有效性");
		if (dis == 0) {
			// 改为有效
			int flag = accountFacade.enableCompanyAccount(id,ca.getCompanyId());
			if (flag == 2) {
				return JsonResult.fail("请先将企业账号设为有效");
			} else if (flag ==1) {
				map.put("dialog",1);
			}
		} else if (dis == 1) {
			// 改为失效

			// 设为失效期间(资金的回收)上锁
			boolean lock = true;
			try {
				lock = jedisUtil.lockWithParam(JedisUtil.ACCOUNT_DISABLED_LOCK_KEY_PRE+ca.getCompanyId(),userId.toString(),JedisUtil.ACCOUNT_DISABLED_LOCK_EXPIRE_TIME);
			} catch (InterruptedException e) {
				logger.info("获取锁异常");
				jedisUtil.delLock(JedisUtil.ACCOUNT_DISABLED_LOCK_KEY_PRE+ca.getCompanyId());
				e.printStackTrace();
			}
			if (lock) {
				try {
					accountFacade.disableCompanyAccount(id, ca.getCompanyId(), platformId, userId);
				}catch (Exception e) {
					logger.info("企业正在失效操作异常,请重试.code = " + BusinessExceptionConstant.ACCOUNT_DISABLED_ERROR);
					jedisUtil.delLock(JedisUtil.ACCOUNT_DISABLED_LOCK_KEY_PRE+ca.getCompanyId());
					e.printStackTrace();
				}
			}else {
				logger.info("该企业正在进行失效操作,请稍后.code="+BusinessExceptionConstant.ACCOUNT_LOCKED);
				return JsonResult.fail("该企业正在进行失效中，请稍后",BusinessExceptionConstant.ACCOUNT_LOCKED);
			}

			// 失效流程正常结束,释放锁
			jedisUtil.delLock(JedisUtil.ACCOUNT_DISABLED_LOCK_KEY_PRE+ca.getCompanyId());
		} else {
			return JsonResult.fail("未知的参数选项");
		}

		return JsonResult.success(map);
	}

	/**
	 * 恢复企业和员工账户金额
	 * @param id
	 * @return
	 */
	@Override
	public JsonResult<Map<String, Object>> recoverAccountBalance(Long id,Integer recover, Long userId,Long platformId) {
		if (id == null) {
			return JsonResult.fail("请选择账户");
		}
		if (recover == 0) {
			//不恢复
			accountFacade.doNotRecoverAccountBalance(id);
		} else if (recover ==1) {
			//恢复
			accountFacade.recoverAccountBalance(id,userId,platformId);
		} else {
			return JsonResult.fail("未知的参数选项");
		}
		return JsonResult.success();
	}
//	@Override
//	public JsonResult<Map<String, Object>> accountDetailPage(Long accountId, String batchNo, Integer type,
//			Integer status, Integer pageNo, Integer pageSize, Long platformId) {
//		if (accountId == null)
//			return JsonResult.fail("请选择账户");
//		if (EmptyUtil.isBlank(batchNo))
//			batchNo = null;
//		if (pageNo == null)
//			pageNo = 1;
//		if (pageSize == null)
//			pageSize = 20;
//		Pagination page = new Pagination(pageNo, pageSize);
//		PageResult<AccountDetailDTO> dtoPage = accountFacade.queryAccountDetailPage(page, accountId, batchNo, type,
//				status);
//		PageResult<AccountDetailVO> voPage = new PageResult<>();
//		List<AccountDetailDTO> dtoList = dtoPage.getList();
//		List<AccountDetailVO> voList = new ArrayList<>();
//		if (dtoList.size() > 0) {
//			// 准备所有的reason
//			List<AdjustReasonDTO> reasons = accountFacade.queryAdjustReasons(null);
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//			for (AccountDetailDTO ad : dtoList) {
//				AccountDetailVO vo = new AccountDetailVO();
//				vo.setCreateTime(sdf.format(ad.getCreateTime()));
//				vo.setFinBatch(ad.getFinBatch());
//				vo.setFlowAmount(ad.getFlowAmount());
//				vo.setId(ad.getId());
//				vo.setRaBatch(ad.getRaBatch());
//				long reasonId = ad.getReasonId();
//				for (AdjustReasonDTO r : reasons) {
//					if (r.getId().longValue() == reasonId) {
//						vo.setReason(r.getName());
//						vo.setType(FlowTypeConstant.translate(r.getType()));
//						break;
//					}
//				}
//				vo.setRemark(ad.getRemark());
//				vo.setStatus(ad.getStatus());
//				vo.setSum(ad.getSum());
//				voList.add(vo);
//			}
//		}
//		voPage.setList(voList);
//		voPage.copy(dtoPage);
//		return JsonResult.success("accountDetailPage", voPage);
//	}

	@Override
	public JsonResult<Map<String, Object>> batchTmpPage(Integer pageNo, Integer pageSize, String keyWord,
			Long companyId, Integer status, Integer type, Long platformId) {
		if (pageNo == null)
			pageNo = 1;
		if (pageSize == null)
			pageSize = 20;
		if (EmptyUtil.isBlank(keyWord))
			keyWord = null;
		Pagination page = new Pagination(pageNo, pageSize);
		PageResult<AccountBatchTmpDTO> dtoPage = accountFacade.queryAccountBatchTmpPage(page, keyWord, companyId,
				status, type,platformId);
		PageResult<AccountBatchVO> voPage = new PageResult<>();
		List<AccountBatchTmpDTO> dtoList = dtoPage.getList();
		List<AccountBatchVO> voList = new ArrayList<>();
		if (dtoList.size() > 0) {
			// 准备所有的reason
			List<AdjustReasonDTO> reasons = accountFacade.queryAdjustReasons(null);
			Set<Long> accountIds_ = new HashSet<>();
			List<Long> accountIds = new ArrayList<>();
			Set<Long> companyIds_ = new HashSet<>();
			List<Long> companyIds = new ArrayList<>();

			for (AccountBatchTmpDTO ab : dtoList) {
				accountIds_.add(ab.getInflowAccountid());
				accountIds_.add(ab.getOutflowAccountid());
				companyIds_.add(ab.getCompanyId());
			}
			companyIds.addAll(companyIds_);
			accountIds.addAll(accountIds_);
			List<CompanyAccountDTO> cas = accountFacade.queryCompanyAccountsByIds(accountIds);
			List<CompanyDTO> cs = userFacade.queryCompaniesByIds(companyIds);
			for (AccountBatchTmpDTO ab : dtoList) {
				AccountBatchVO vo = new AccountBatchVO();
				long inFlowAccId = ab.getInflowAccountid();
				vo.setInFlowAccountId(inFlowAccId);
				for (CompanyAccountDTO ca : cas) {
					if (inFlowAccId == ca.getId().longValue()) {
						vo.setInFlowAccountName(ca.getName());
						break;
					}
				}
				long outFlowAccId = ab.getOutflowAccountid();
				vo.setOutFlowAccountId(outFlowAccId);
				for (CompanyAccountDTO ca : cas) {
					if (outFlowAccId == ca.getId().longValue()) {
						vo.setOutFlowAccountName(ca.getName());
						break;
					}
				}
				vo.setFinBatch(ab.getFinBatch());
				vo.setId(ab.getId());
				long reasonId = ab.getReasonId();
				for (AdjustReasonDTO r : reasons) {
					if (r.getId().longValue() == reasonId) {
						vo.setReason(r.getName());
						vo.setType(FlowTypeConstant.translate(r.getType()));
						// int转String
						vo.setCurrencyType(currencyTypeTOString(r.getType()));
						break;
					}
				}
				vo.setReasonId(ab.getReasonId());
				vo.setRemark(ab.getRemark());
				vo.setStatus(ab.getStatus());
				vo.setSum(ab.getSum());
				long cid = ab.getCompanyId();
				vo.setCompanyId(cid);
				for (CompanyDTO c : cs) {
					if (cid == c.getId()) {
						vo.setCompanyName(c.getCompanyName());
					}
				}
				voList.add(vo);
			}
		}
		voPage.setList(voList);
		voPage.copy(dtoPage);
		return JsonResult.success("batchTmpPage", voPage);
	}

	private String currencyTypeTOString(Integer type) {
		if (EmptyUtil.isEmpty(type)) {
			return null;
		}
		// 类型 0:企业账户充值 1:企业账户调整收入 2:企业账户失效 3:订单现金付款 4:订单积分付款 5:订单现金退款 6:订单积分退款 7:员工积分充值
		// 8:员工点赞福豆充值 9:员工账户失效 10:员工点赞 11企业账户调整支出 13:企业账户恢复 14:员工账户恢复
		switch (type) {
		case 0:
		case 1:
		case 11:
		case 13:
			return "现金";
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 14:
			return null;
		default:
			throw new BusinessException("未定义货币类型");
		}
	}

	@Override
	public JsonResult<Map<String, Object>> fullBatchPage(Integer pageNo, Integer pageSize, Integer tableType,
			String batchNo, Integer type, Integer status, Long platformId) {
		if (pageNo == null)
			pageNo = 1;
		if (pageSize == null)
			pageSize = 20;
		if (EmptyUtil.isBlank(batchNo))
			batchNo = null;
		if (tableType == null)
			return JsonResult.fail("表未指定");
		boolean isTmp = tableType == 0;
		if (isTmp) {
			batchNo = null;
		}
		Pagination page = new Pagination(pageNo, pageSize);
		PageResult<AccountBatchVO> voPage = new PageResult<>();
		List<AccountBatchVO> voList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if (isTmp) {
			PageResult<AccountBatchTmpDTO> dtoPage = accountFacade.queryAccountBatchTmpPage(page, null, null, status,
					type,platformId);
			List<AccountBatchTmpDTO> dtoList = dtoPage.getList();
			if (dtoList.size() > 0) {
				// 准备所有的reason
				List<AdjustReasonDTO> reasons = accountFacade.queryAdjustReasons(null);
				for (AccountBatchTmpDTO ab : dtoList) {
					AccountBatchVO vo = new AccountBatchVO();
					vo.setFinBatch(ab.getFinBatch());
					vo.setId(ab.getId());
					Long reasonId = ab.getReasonId();
					if (reasonId != null) {
						for (AdjustReasonDTO r : reasons) {
							if (r.getId().longValue() == reasonId) {
								vo.setReason(r.getName());
								break;
							}
						}
					}
					vo.setType(FlowTypeConstant.translate(ab.getType()));
					vo.setReasonId(ab.getReasonId());
					vo.setRemark(ab.getRemark());
					vo.setStatus(ab.getStatus());
					vo.setSum(ab.getSum());
					long cid = ab.getCompanyId();
					vo.setCompanyId(cid);
					vo.setCreateTime(sdf.format(ab.getCreateTime()));
					vo.setCurrencyType("现金");
					voList.add(vo);
				}
			}
			voPage.copy(dtoPage);
		} else {
			PageResult<AccountBatchDTO> dtoPage = accountFacade.queryAccountBatchPage(null, batchNo, page, null, null,
					type, null,platformId);
			List<AccountBatchDTO> dtoList = dtoPage.getList();
			if (dtoList.size() > 0) {
				// 准备所有的reason
				List<AdjustReasonDTO> reasons = accountFacade.queryAdjustReasons(null);
				for (AccountBatchDTO ab : dtoList) {
					AccountBatchVO vo = new AccountBatchVO();
					vo.setFinBatch(ab.getFinBatch());
					vo.setRaBatch(ab.getRaBatch());
					vo.setId(ab.getId());
					Long reasonId = ab.getReasonId();
					if (reasonId != null) {
						for (AdjustReasonDTO r : reasons) {
							if (r.getId().longValue() == reasonId) {
								vo.setReason(r.getName());
								break;
							}
						}
					}
					vo.setType(FlowTypeConstant.translate(ab.getType()));
					vo.setReasonId(ab.getReasonId());
					vo.setRemark(ab.getRemark());
					vo.setStatus(1);// 写死为已通过
					vo.setSum(ab.getSum());
					Long cid = ab.getCompanyId();
					vo.setCompanyId(cid);
					vo.setFlowAmount(ab.getFlowAmount());
					vo.setCreateTime(sdf.format(ab.getCreateTime()));
					vo.setCurrencyType(currencyTypeToString(ab.getCurrencyType()));
					voList.add(vo);
				}
			}
			voPage.copy(dtoPage);
		}
		voPage.setList(voList);
		return JsonResult.success("batchPage", voPage);
	}

	@Override
	public JsonResult<Map<String, Object>> batchExam(Long id, Integer option, String reason, Long platformId) {
		if (id == null)
			return JsonResult.fail("请选择批次");
		if (option == null)
			return JsonResult.fail("错误的选择项");
		if (option != 0 && option != 1)
			return JsonResult.fail("错误的选择项");
		boolean pass = option == 0;
		if (!pass && EmptyUtil.isBlank(reason))
			return JsonResult.fail("请给出不通过的原因");
		AccountBatchTmpDTO ab = accountFacade.queryAccountBatchTmpById(id);
		if (ab == null)
			return JsonResult.fail("该批次不存在");
		CompanyAccountDTO inflowAccount = accountFacade.queryCompanyAccountById(ab.getInflowAccountid());
		if (inflowAccount == null)
			return JsonResult.fail("流入账户不存在");
		SaltDTO inSalt = secretFacade.querySaltByUUID(inflowAccount.getUuid());
		if (inSalt == null)
			return JsonResult.fail("流入账户加密信息有误");
		CompanyAccountDTO outflowAccount = accountFacade.queryCompanyAccountById(ab.getOutflowAccountid());
		if (outflowAccount == null)
			return JsonResult.fail("流出账户不存在");
		SaltDTO outSalt = secretFacade.querySaltByUUID(outflowAccount.getUuid());
		if (outSalt == null)
			return JsonResult.fail("流出账户加密信息有误");
		if (pass) {
			//判断是否存在账户锁
			CompanyAccountDTO ca = accountFacade.queryCompanyAccountById(id);
			String accountKey1 = jedisUtil.getString(JedisUtil.ACCOUNT_DISABLED_LOCK_KEY_PRE + inflowAccount.getCompanyId());
			String accountKey2 = jedisUtil.getString(JedisUtil.ACCOUNT_DISABLED_LOCK_KEY_PRE + outflowAccount.getCompanyId());
			if(EmptyUtil.isNotEmpty(accountKey1) && EmptyUtil.isNotEmpty(accountKey2)){
				logger.info("正在进行企业账户失效操作，请稍后再试,code="+BusinessExceptionConstant.ACCOUNT_LOCKED);
				return JsonResult.fail("企业账户已冻结，无法进行账户充值或调整",BusinessExceptionConstant.ACCOUNT_LOCKED);
			}
			// 参数准备
			// 单账户->单账户:在outFlowAccs[0]的sum字段指定金额
			List<CashFlowAccountDTO> outFlowAccs = new ArrayList<>();
			CashFlowAccountDTO cfaOut = new CashFlowAccountDTO();
			cfaOut.setAccountId(outflowAccount.getId());
			cfaOut.setSalt(outSalt.getSaltValue());
			cfaOut.setSum(ab.getSum());
			cfaOut.setCompanyId(outflowAccount.getCompanyId());
			outFlowAccs.add(cfaOut);
			List<CashFlowAccountDTO> inFlowAccs = new ArrayList<>();
			CashFlowAccountDTO cfaIn = new CashFlowAccountDTO();
			cfaIn.setAccountId(inflowAccount.getId());
			cfaIn.setSalt(inSalt.getSaltValue());
			cfaIn.setCompanyId(inflowAccount.getCompanyId());
			cfaIn.setSum(ab.getSum());
			inFlowAccs.add(cfaIn);
			int type = ab.getType();
			boolean isRecharge = type == FlowTypeConstant.CA_RECHARGE.getStatus();
			/*
			 * 根据批次类型推断财务批次号类型: 需要审核的批次目前只有账户调整收入和账户调整支出
			 */
			int finBathType = FinBatchConstant.translateFlowType(type);
			// 形成资金流动,生成批次和流水
			accountFacade.unifiedCashFlow(outFlowAccs, 0, inFlowAccs, 0, false, platformId, type, null, null,
					ab.getOperatorId(), finBathType, ab.getReasonId(), ab.getRemark(), isRecharge, 0);

		}
		// 更改草稿状态
		// accountFacade.batchExam(id, pass, outflowCipher, inflowCipher);
		accountFacade.changeAccountBatchTmpStatus(id, pass ? 1 : 2, reason);
		// 如果是不通过,将不通过的原因发送给申请人,这步暂略
		return JsonResult.success();
	}

	@Override
	public JsonResult<Map<String, Object>> accountBatchPage(Long accountId, String batchNo, Integer type,
			Integer pageNo, Integer pageSize, Long platformId) {
		if (accountId == null)
			return JsonResult.fail("请选择账户");
		if (pageNo == null)
			pageNo = 1;
		if (pageSize == null)
			pageSize = 20;
		if (EmptyUtil.isBlank(batchNo))
			batchNo = null;
		Pagination page = new Pagination(pageNo, pageSize);
		PageResult<AccountBatchDTO> dtoPage = accountFacade.queryAccountBatchPage(accountId, batchNo, page, null, null,
				type, null,platformId);
		PageResult<AccountBatchVO> voPage = new PageResult<>();
		List<AccountBatchDTO> dtoList = dtoPage.getList();
		List<AccountBatchVO> voList = new ArrayList<>();
		if (dtoList.size() > 0) {
			// 准备所有的reason
			List<AdjustReasonDTO> reasons = accountFacade.queryAdjustReasons(null);
			Set<Long> accountIds_ = new HashSet<>();
			List<Long> accountIds = new ArrayList<>();
			for (AccountBatchDTO ab : dtoList) {
				accountIds_.add(ab.getInflowAccountid());
				accountIds_.add(ab.getOutflowAccountid());
			}
			accountIds.addAll(accountIds_);
			List<CompanyAccountDTO> cas = accountFacade.queryCompanyAccountsByIds(accountIds);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			for (AccountBatchDTO ab : dtoList) {
				AccountBatchVO vo = new AccountBatchVO();
				Long inFlowAccId = ab.getInflowAccountid();
				if (inFlowAccId != null) {
					vo.setInFlowAccountId(inFlowAccId);
					for (CompanyAccountDTO ca : cas) {
						if (inFlowAccId.longValue() == ca.getId().longValue()) {
							vo.setInFlowAccountName(ca.getName());
							break;
						}
					}
				}
				Long outFlowAccId = ab.getOutflowAccountid();
				vo.setOutFlowAccountId(outFlowAccId);
				if (outFlowAccId != null) {
					for (CompanyAccountDTO ca : cas) {
						if (outFlowAccId == ca.getId().longValue()) {
							vo.setOutFlowAccountName(ca.getName());
							break;
						}
					}
				}
				vo.setFinBatch(ab.getFinBatch());
				vo.setId(ab.getId());
				vo.setRaBatch(ab.getRaBatch());
				Long reasonId = ab.getReasonId();
				if (reasonId != null) {
					for (AdjustReasonDTO r : reasons) {
						if (r.getId().longValue() == reasonId) {
							vo.setReason(r.getName());
							break;
						}
					}
				}
				vo.setType(FlowTypeConstant.translate(ab.getType()));
				vo.setRemark(ab.getRemark());
				vo.setStatus(ab.getStatus());
				vo.setSum(ab.getSum());
				vo.setFlowAmount(ab.getFlowAmount());
				vo.setCreateTime(sdf.format(ab.getCreateTime()));
				vo.setCurrencyType(currencyTypeToString(ab.getCurrencyType()));
				voList.add(vo);
			}
		}
		voPage.setList(voList);
		voPage.copy(dtoPage);
		return JsonResult.success("accountBatchPage", voPage);
	}

	@Override
	public JsonResult<Map<String, Object>> batchFlowPage(Long batchId, String outflowAccount, String inflowAccount,
			Long startTime, Long endTime, Integer pageNo, Integer pageSize, Long platformId) {
		if (batchId == null)
			return JsonResult.fail("请选择批次");
		if (pageNo == null)
			pageNo = 1;
		if (pageSize == null)
			pageSize = 20;
		if (EmptyUtil.isBlank(outflowAccount))
			outflowAccount = null;
		if (EmptyUtil.isBlank(inflowAccount))
			inflowAccount = null;

		Pagination page = new Pagination(pageNo, pageSize);
		PageResult<AccountFlowDTO> dtoPage = accountFacade.queryAccountFlowPage(batchId, outflowAccount, inflowAccount,
				startTime, endTime,platformId, page);
		PageResult<AccountFlowVO> poPage = new PageResult<>();
		List<AccountFlowDTO> dtoList = dtoPage.getList();
		List<AccountFlowVO> voList = new ArrayList<>();
		if (dtoList.size() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			for (AccountFlowDTO f : dtoList) {
				AccountFlowVO vo = new AccountFlowVO();
				vo.setCreateTime(sdf.format(f.getCreateTime()));
				vo.setId(f.getId());
				Long ifaId = f.getInflowAccountid();
				Integer ifaType = f.getInflowAccounttype();
				String ifaName = null;
				Long ofaId = f.getOutflowAccountid();
				Integer ofaType = f.getOutflowAccounttype();
				String ofaName = null;
				vo.setInflowAccountid(ifaId);
				vo.setInflowAccounttype(ifaType);
				if (ifaType == 0) {
					CompanyAccountDTO ca = accountFacade.queryCompanyAccountById(ifaId);
					if (ca != null) {
						ifaName = ca.getName();
					}
				} else {
					UserAccountDTO ua = accountFacade.queryUserAccountById(ifaId);
					if (ua != null) {
						ifaName = ua.getName();
					}
				}
				if (ofaType == 0) {
					CompanyAccountDTO ca = accountFacade.queryCompanyAccountById(ofaId);
					if (ca != null) {
						ofaName = ca.getName();
					}
				} else {
					UserAccountDTO ua = accountFacade.queryUserAccountById(ofaId);
					if (ua != null) {
						ofaName = ua.getName();
					}
				}
				vo.setInflowAccountName(ifaName);
				vo.setOutflowAccountid(ofaId);
				vo.setOutflowAccountName(ofaName);
				vo.setOutflowAccounttype(ofaType);
				Long reasonId = f.getReasonId();
				AdjustReasonDTO reason = accountFacade.queryAdjustReasonById(reasonId);
				if (reason != null) {
					vo.setReason(reason.getName());
				}
				vo.setRemark(f.getRemark());
				vo.setSum(f.getSum());
				vo.setType(FlowTypeConstant.translate(f.getType()));
				vo.setCurrencyType(currencyTypeToString(f.getCurrencyType()));
				voList.add(vo);
			}
		}
		poPage.setList(voList);
		poPage.copy(dtoPage);
		return JsonResult.success("flowPage", poPage);
	}

	@Override
	public JsonResult<Map<String, Object>> flowPage(Long startTime, Long endTime, String outflowAccount,
			String inflowAccount, Integer pageNo, Integer pageSize, Long platformId) {
		if (pageNo == null)
			pageNo = 1;
		if (pageSize == null)
			pageSize = 20;
		if (EmptyUtil.isBlank(outflowAccount))
			outflowAccount = null;
		if (EmptyUtil.isBlank(inflowAccount))
			inflowAccount = null;
		if (endTime != null) {
			// 将非空的结束时转化为当天的23:59:59
			endTime += 86399000l;
		}
		Pagination page = new Pagination(pageNo, pageSize);
		PageResult<AccountFlowDTO> dtoPage = accountFacade.queryAccountFlowPage(null, outflowAccount, inflowAccount,
				startTime, endTime, platformId, page);
		PageResult<AccountFlowVO> poPage = new PageResult<>();
		List<AccountFlowDTO> dtoList = dtoPage.getList();
		List<AccountFlowVO> voList = new ArrayList<>();
		if (dtoList.size() > 0) {
			// 准备所有的reason
			List<AdjustReasonDTO> reasons = accountFacade.queryAdjustReasons(null);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			for (AccountFlowDTO f : dtoList) {
				AccountFlowVO vo = new AccountFlowVO();
				vo.setCreateTime(sdf.format(f.getCreateTime()));
				vo.setId(f.getId());
				Long inAccId = f.getInflowAccountid();
				vo.setInflowAccountid(inAccId);
				// 查询流入账户名
				vo.setInflowAccounttype(f.getInflowAccounttype());
				if (f.getInflowAccounttype().intValue() == 0) {
					CompanyAccountDTO inAcc = accountFacade.queryCompanyAccountById(inAccId);
					vo.setInflowAccountName(inAcc.getName());
				} else {
					UserAccountDTO inAcc = accountFacade.queryUserAccountById(inAccId);
					vo.setInflowAccountName(inAcc.getName());
				}
				Long outAccId = f.getOutflowAccountid();
				Integer outAccType = f.getOutflowAccounttype();
				String outAccName = null;
				if (outAccType == 0) {
					// 公司账户
					outAccName = accountFacade.queryCompanyAccountById(outAccId).getName();
				} else {
					// 用户账户
					outAccName = accountFacade.queryUserAccountById(outAccId).getName();
				}
				vo.setOutflowAccountid(outAccId);
				vo.setOutflowAccountName(outAccName);
				vo.setOutflowAccounttype(outAccType);
				Long reasonId = f.getReasonId();
				if (reasonId != null) {
					for (AdjustReasonDTO r : reasons) {
						if (r.getId().longValue() == reasonId.longValue()) {
							vo.setReason(r.getName());
							break;
						}
					}
				}
				vo.setType(FlowTypeConstant.translate(f.getType()));
				vo.setRemark(f.getRemark());
				vo.setSum(f.getSum());
				vo.setCurrencyType(currencyTypeToString(f.getCurrencyType()));
				voList.add(vo);
			}
		}
		poPage.setList(voList);
		poPage.copy(dtoPage);
		return JsonResult.success("flowPage", poPage);
	}

	@Override
	public JsonResult<Map<String, Object>> normalAccounts(Long platformId,List<Long> companyId) {
		List<CompanyAccountDTO> cas = accountFacade.queryNormalAccounts(platformId,companyId);
		List<SimpleAccountVO> vos = new ArrayList<>();
		for (CompanyAccountDTO ca : cas) {
			SimpleAccountVO sa = new SimpleAccountVO();
			sa.setId(ca.getId());
			sa.setName(ca.getName());
			vos.add(sa);
		}
		Map<String, Object> data = new HashMap<>();
		data.put("accounts", vos);
		return JsonResult.success(data);
	}

	@Override
	public JsonResult<Map<String, Object>> userAccountPage(String name, String email, Long companyId, Integer pageNo,
			Integer pageSize, Long platformId,Long userCompanyId) {
		if (EmptyUtil.isBlank(name))
			name = null;
		if (EmptyUtil.isBlank(email))
			email = null;
		if (pageNo == null)
			pageNo = 1;
		if (pageSize == null)
			pageSize = 20;
		Pagination page = new Pagination(pageNo, pageSize);

		UserExtendDTO condition = new UserExtendDTO();
		condition.setName(name);
		condition.setCompanyId(companyId);
		condition.setMail(email);
		condition.setPlatformId(platformId);
		// 分页查询员工列表
		PageResult<UserExtendDTO> uePage = userFacade.queryFullUserExtendPage(page, condition,userCompanyId,platformId);
		List<UserExtendDTO> ues = uePage.getList();
		PageResult<UserAccountVO> voPage = new PageResult<>();
		/*List<UserAccountVO> voList = new ArrayList<>();
		if (ues.size() > 0) {
			// 查询所有公司
			List<CompanyDTO> cs = userFacade.queryCompanyList(platformId);
			for (UserExtendDTO ue : ues) {
				UserAccountVO vo = new UserAccountVO();
				Long cId = ue.getCompanyId();
				if (cId != null) {
					for (CompanyDTO c : cs) {
						if (cId == c.getId().longValue()) {
							vo.setCompanyName(c.getCompanyName());
							break;
						}
					}
				}
				vo.setEmail(ue.getMail());
				// 查询该用户的几个账户
				List<UserAccountDTO> uas = accountFacade.queryUserAccountByUserId(ue.getId());
				for (UserAccountDTO ua : uas) {
					int type = ua.getType().intValue();
					if (type == 0) {
						vo.setFbAcc(ua.getBalance().doubleValue());
					}
					if (type == 1) {
						vo.setPpAcc(ua.getBalance().doubleValue());
					}
					if (type == 2) {
						vo.setFbfAcc(ua.getBalance().doubleValue());
					}
					if (type == 3) {
						vo.setcAcc(ua.getBalance().doubleValue());
					}
				}
				vo.setMemberCode(ue.getMemberCode());
				vo.setName(ue.getName());
				vo.setUserId(ue.getId());
				vo.setEmail(ue.getMail());
				voList.add(vo);
			}
		}*/
		List<UserAccountVO> voList = getUserAccountList(ues, platformId);
		voPage.setList(voList);
		voPage.copy(uePage);
		return JsonResult.success("userAccountPage", voPage);
	}
//根据用户列表查询用户账户信息
	private List<UserAccountVO> getUserAccountList(List<UserExtendDTO> ues,Long platformId){
		List<UserAccountVO> voList = new ArrayList<>();
		if (ues.size() > 0) {
			// 查询所有公司
			List<CompanyDTO> cs = userFacade.queryCompanyList(platformId);
			for (UserExtendDTO ue : ues) {
				UserAccountVO vo = new UserAccountVO();
				Long cId = ue.getCompanyId();
				if (cId != null) {
					for (CompanyDTO c : cs) {
						if (cId == c.getId().longValue()) {
							vo.setCompanyName(c.getCompanyName());
							break;
						}
					}
				}
				vo.setEmail(ue.getMail());
				// 查询该用户的几个账户
				List<UserAccountDTO> uas = accountFacade.queryUserAccountByUserId(ue.getId());
				for (UserAccountDTO ua : uas) {
					int type = ua.getType().intValue();
					if (type == 0) {
						vo.setFbAcc(ua.getBalance().doubleValue());
					}
					if (type == 1) {
						vo.setPpAcc(ua.getBalance().doubleValue());
					}
					if (type == 2) {
						vo.setFbfAcc(ua.getBalance().doubleValue());
					}
					if (type == 3) {
						vo.setcAcc(ua.getBalance().doubleValue());
					}
				}
				vo.setMemberCode(ue.getMemberCode());
				vo.setName(ue.getName());
				vo.setUserId(ue.getId());
				vo.setEmail(ue.getMail());
				voList.add(vo);
			}
		}
		logger.info("根据用户列表查询用户账户信息结束,列表voList："+voList);
		return voList;
	}



	@Override
	public JsonResult<Map<String, Object>> saveReason(Long id, String reason, Integer type, String companyIds,
			Integer disabled, Long platformId) {
		if (EmptyUtil.isBlank(reason))
			return JsonResult.fail("请填写原因");
		if (reason.length() > 20)
			return JsonResult.fail("原因不超过20字");
		if (type == null)
			return JsonResult.fail("请选择类型");
		if (type != 0 && type != 1 && type != 7 && type != 8 && type!=11) {
			return JsonResult.fail("错误的类型");
		}
		if (EmptyUtil.isBlank(companyIds))
			return JsonResult.fail("请至少选择一个公司");
		List<Long> cIds = StringUtils.stringWithBorder2IdList(companyIds, ",");
		if (cIds.size() == 0)
			return JsonResult.fail("请至少选择一个公司");
		if (disabled == null)
			return JsonResult.fail("错误的有效状态");
		if (disabled != 0 && disabled != 1)
			return JsonResult.fail("错误的有效状态");
		AdjustReasonDTO dto = new AdjustReasonDTO();
		dto.setName(reason);
		dto.setType(type);
		dto.setDisabled(disabled);
		dto.setPlatformId(platformId);
		if (id == null) {
			// 新建
			id = accountFacade.createAdjustReason(dto, cIds);
		} else {
			// 编辑
			dto.setId(id);
			accountFacade.editAdjustReason(dto, cIds);
		}
		return JsonResult.success();
	}

	@Override
	public JsonResult<Map<String, Object>> reasons(Integer type, Long companyId, Integer disabled, Integer pageNo,
			Integer pageSize, Long platformId) {
		// type in (0,1,7,8)
		if (type != null && type != 0 && type != 1 && type != 7 && type != 8) {
			return JsonResult.fail("错误的类型");
		}
		if (pageNo == null)
			pageNo = 1;
		if (pageSize == null)
			pageSize = 20;
		Pagination page = new Pagination(pageNo, pageSize);
		PageResult<AdjustReasonDTO> dtoPage = accountFacade.queryAdjustReasonPage(type, companyId, disabled,platformId, page);
		List<AdjustReasonDTO> dtoList = dtoPage.getList();
		PageResult<AdjustReasonVO> voPage = new PageResult<>();
		List<AdjustReasonVO> voList = new ArrayList<>();
		if (dtoList.size() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			// 查询所有公司
			for (AdjustReasonDTO dto : dtoList) {
				AdjustReasonVO vo = new AdjustReasonVO();
				vo.setCreateTime(sdf.format(dto.getCreateTime()));
				vo.setDisabled(dto.getDisabled());
				vo.setId(dto.getId());
				vo.setName(dto.getName());
				int type_ = dto.getType();
				vo.setType(dto.getType());
				vo.setTypeName(FlowTypeConstant.translate(type_));
				voList.add(vo);
			}
		}
		voPage.copy(dtoPage);
		voPage.setList(voList);
		return JsonResult.success("reasonPage", voPage);
	}

	@Override
	public JsonResult<Map<String, Object>> userAccountRechargeRec(Long companyId, String batchNo, Integer pageNo,
			Integer pageSize, Long platformId) {
		if (pageNo == null)
			pageNo = 1;
		if (pageSize == null)
			pageSize = 20;
		Pagination page = new Pagination(pageNo, pageSize);
		// 类型是员工充值的批次记录
		PageResult<AccountBatchDTO> dtoPage = accountFacade.queryRechargeAccountBatchPage(page, companyId, batchNo);
		List<AccountBatchDTO> dtoList = dtoPage.getList();
		PageResult<RechargeRecVO> voPage = new PageResult<>();
		List<RechargeRecVO> voList = new ArrayList<>();
		if (dtoList.size() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			List<CompanyDTO> cs = userFacade.queryCompanyList(platformId);
			// 查询所有原因
			List<AdjustReasonDTO> rs = accountFacade.queryAdjustReasons(null);
			for (AccountBatchDTO dto : dtoList) {
				RechargeRecVO vo = new RechargeRecVO();
				vo.setAccountType(dto.getType());
				long cid = dto.getCompanyId();
				for (CompanyDTO c : cs) {
					if (cid == c.getId().longValue()) {
						vo.setCompanyName(c.getCompanyName());
						break;
					}
				}
				vo.setCreate_time(sdf.format(dto.getCreateTime()));
				vo.setFinBatch(dto.getFinBatch());
				vo.setRaBatch(dto.getRaBatch());
				vo.setId(dto.getId());
				long rId = dto.getReasonId();
				for (AdjustReasonDTO r : rs) {
					if (rId == r.getId().longValue()) {
						vo.setReason(r.getName());
						break;
					}
				}
				vo.setSum(dto.getSum().doubleValue());
				voList.add(vo);
			}
		}
		voPage.setList(voList);
		voPage.copy(dtoPage);
		return JsonResult.success("recPage", voPage);
	}

	@Override
	public JsonResult<Map<String, Object>> userAccountRecharge(Double totalAmount, Long companyId, Integer accountType,
			Long reasonId, String remark, String finBatch, Long platformId, List<Map<String, Object>> valueList,CacheUser user) {

		// 数量校验
		if (valueList.size() > 1002)
			return JsonResult.fail("单次导入数据不能超过1000条");
		// 检查头文件
		int tmplType = ExcelTmplConstant.staffRecharge.getTmplType();
		String err = ExcelHeadChecker.chechHeader(valueList, tmplType, false);
		if (EmptyUtil.isNotBlank(err))
			return JsonResult.fail(err);
		// 公司校验
		CompanyDTO c = userFacade.queryCompanyById(companyId);
		if (c == null)
			return JsonResult.fail("公司不存在");
		String companyName_ = valueList.get(0).get("CELL2").toString();
		if (!c.getCompanyName().equals(companyName_))
			return JsonResult.fail("公司信息错误");
		// 批次原因
		AdjustReasonDTO rea = accountFacade.queryAdjustReasonById(reasonId);
		if (rea == null)
			return JsonResult.fail("所选原因不存在");
		// 序列号校验
		Map<String, Object> row0 = valueList.get(0);
		String sn = row0.get("CELL8").toString();
		// 序列在头文件记录中不能存在
		HeadImportRecordsDTO recordsDTO = new HeadImportRecordsDTO();
		recordsDTO.setFileSequenceNumber(sn);
		recordsDTO.setTemplateType(ExcelTmplConstant.staffRecharge.getTmplName());
		List<HeadImportRecordsDTO> rec = importRecordsFacade.queryRecordByParams(recordsDTO);
		if (EmptyUtil.isNotEmpty(rec))
			return JsonResult.fail("表头信息重复，请检查是否已导入过此文件");
		// 列表文件校验
		List<PropblemReportRowVO<UserRechargeExcelVO>> problemRep = new ArrayList<>();
        BigDecimal summary = BigDecimal.ZERO;
		// 查重set
		Set<UserRechargeExcelVO> checkRepeatSet = new HashSet<>();
		// 预览信息list
		List<TempRechargeDTO> trList = new ArrayList<>();
		// 头信息备用
		String fileCreateTime_ = row0.get("CELL6").toString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date fileCreateTime = null;
		try {
			fileCreateTime = sdf.parse(fileCreateTime_);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String tmplName = row0.get("CELL4").toString();
		for (int i = 2; i < valueList.size(); i++) {
			int rowNum = i + 1;
			// 内部不重复,空值校验
			TempRechargeDTO tr = new TempRechargeDTO();
			UserRechargeExcelVO vo = row2Bean(valueList.get(i), problemRep, rowNum);
			tr.setAccountType(accountType);
			tr.setFinBatch(finBatch);
			tr.setBatchReason(rea.getName());
			tr.setCompanyId(companyId);
			tr.setCompanyName(c.getCompanyName());
			tr.setEmail(vo.getEmail().toLowerCase());
			tr.setFlowReason(vo.getReason());
			tr.setGeneratedTime(fileCreateTime);
			tr.setImpRes(0);
			tr.setMemberCode(vo.getMemberCode().toLowerCase());
			tr.setPlatformId(platformId);
			tr.setReasonId(reasonId);
			tr.setRemark(vo.getRemark());
			tr.setRownum(rowNum);
			tr.setSn(sn);
			tr.setSum(new BigDecimal(vo.getSum()));
			if(tr.getSum().compareTo(BigDecimal.ZERO)<=0){
				problemRep.add(new PropblemReportRowVO<>("金额异常", rowNum, vo));

			}
			tr.setTmplName(tmplName);
			tr.setTmplType(tmplType);
			tr.setStatus(0);
			if (!checkRepeatSet.add(vo)) {
				problemRep.add(new PropblemReportRowVO<>("此数据与前面的行重复", rowNum, vo));
			}
			// 数据库查询用户信息
			String email = vo.getEmail().toLowerCase();
			if (email != null) {
				UserExtendDTO ue = userFacade.queryUserExtendByEmail(email);
				if (ue == null) {
					problemRep.add(new PropblemReportRowVO<>("邮箱为" + email + "的用户不存在", rowNum, vo));
				} else {
					tr.setUserId(ue.getId());
					tr.setUserName(ue.getName());
					// 员工编号校验
					if (!vo.getMemberCode().equals(ue.getMemberCode()))
						problemRep.add(new PropblemReportRowVO<>("邮箱为" + email + "的用户员工编号不正确", rowNum, vo));
					// 账户非空校验
					UserAccountDTO ua = accountFacade.queryUserAccountByUserIdAndType(ue.getId(), accountType);
					if (ua == null) {
						problemRep.add(new PropblemReportRowVO<>("邮箱为" + email + "的用户账户尚未建立", rowNum, vo));
					} else {
						// 校验用户账户加密
						String uuid = ua.getUuid();
						SaltDTO salt = secretFacade.querySaltByUUID(uuid);
						tr.setSalt(salt.getSaltValue());
						tr.setAccountId(ua.getId());
						boolean balanceValid = CiphertextUtil.balanceValidate(ua.getBalance(), salt,
								ua.getCiphertext());
						if (!balanceValid) {
							problemRep.add(new PropblemReportRowVO<>("邮箱为" + email + "的用户账户余额疑似被篡改", rowNum, vo));
						}
					}
				}
			}
			// 总额与输入一致
            summary = summary.add(new BigDecimal(String.valueOf(vo.getSum())));
			trList.add(tr);
		}
		BigDecimal summaryBig =summary.setScale(2,BigDecimal.ROUND_DOWN);
		BigDecimal totalBig = new BigDecimal(String.valueOf(totalAmount)).setScale(2,BigDecimal.ROUND_DOWN);
		if (summaryBig.compareTo(totalBig)!=0) {
			problemRep.add(new PropblemReportRowVO<UserRechargeExcelVO>("输入总金额与实际总金额不一致", 1, null));
		}
		// 目标账户余额是否充足
		// 若选择所属公司的账户余额不足提示：账户余额不足，请先给企业账户充值
		CompanyAccountDTO ca = accountFacade.queryNormalCompanyAccountByCompnayId(companyId);
		if (ca == null)
			return JsonResult.fail("公司账户尚未建立");
		String uuid = ca.getUuid();
		SaltDTO caSalt = secretFacade.querySaltByUUID(uuid);
		if (!CiphertextUtil.balanceValidate(ca.getBalance(), caSalt, ca.getCiphertext())) {
			return JsonResult.fail("公司账户余额疑似被篡改");
		}
		BigDecimal caBalance = ca.getBalance();
		if (caBalance.compareTo(summary)<0) {
			return JsonResult.fail("账户余额不足，请先给企业账户充值");
		}
		/*
		 * 结果集包含内容包含内容: 错误报告url(导入失败) 导入预览批次(导入成功用于查询预览)
		 */
		if (problemRep.size() > 0) {
			// 生成问题报告.xlsx上传至dfs
			String repUrl = genAndUploadRep(problemRep);
			return JsonResult.fail(repUrl, BusinessExceptionConstant.IMPORTFILEERROR);
		} else {
			// 根据删除头文件预览表以前的记录
			importRecordsFacade.deleteBySn(sn);
			// 根据sn删除导入预览表以前的记录
			accountFacade.deleteTempRechargeBySn(sn);
			// 插入预览表
			// 每100条进行一次批量插入
			List<TempRechargeDTO> batchInsertList = new ArrayList<>();
			for (int i = 0; i < trList.size(); i++) {
				TempRechargeDTO tr = trList.get(i);
				batchInsertList.add(tr);
				if ((i + 1) % 100 == 0) {
					accountFacade.batchInsertTempRecharge(batchInsertList);
					batchInsertList.clear();
				}
			}
			// 插入最后不足100条记录
			if (batchInsertList.size() > 0) {
				accountFacade.batchInsertTempRecharge(batchInsertList);
			}
			// 插入头文件预览表
			ImportRecordsDTO impRec = new ImportRecordsDTO();
			impRec.setCompanyName(companyName_);
			impRec.setFileSequenceNumber(sn);
			impRec.setGeneratedTime(fileCreateTime);
			impRec.setTemplateType(tmplName);
			importRecordsFacade.insert(impRec);
			HeadInfoVO head = new HeadInfoVO();
			head.setCompanyName(companyName_);
			head.setFileSequenceNumber(sn);
			head.setSum(totalAmount);
			head.setAccountType(translateAccountType(accountType));
			head.setTemplateType(tmplName);
			// 查询预览列表作为返回值
			List<TempRechargeDTO> trs = accountFacade.queryTempRechargeBySn(sn);
			if (trs.size() == 0)
				return JsonResult.fail("没有可以预览的数据");
			// 转vo
			List<RechargePreviewVO> preVos = new ArrayList<>();
			for (TempRechargeDTO tr : trs) {
				RechargePreviewVO vo = new RechargePreviewVO();
				vo.setCompanyName(tr.getCompanyName());
				vo.setEmail(tr.getEmail());
				vo.setId(tr.getId());
				vo.setImpRes(tr.getImpRes().intValue() == 0 ? "正常" : "异常");
				vo.setMemberCode(tr.getMemberCode());
				vo.setRownum(tr.getRownum());
				vo.setSum(tr.getSum());
				vo.setUserName(tr.getUserName());
				preVos.add(vo);
			}
			Map<String, Object> data = new HashMap<>();
			data.put("headInfo", head);
			data.put("overView", preVos);
			return JsonResult.success(data);
		}
	}

	/**
	 * 翻译账户类型
	 *
	 * @param accountType
	 * @return
	 */
	private String translateAccountType(Integer accountType) {
		// '账户类型 0:积分账户 1:点赞福豆账户 2:积分冻结账户 3:现金支出账户',
		switch (accountType) {
		case 0:
			return "积分账户";
		case 1:
			return "点赞福豆账户";
		case 2:
			return "积分冻结账户";
		case 3:
			return "现金支出账户";
		}
		return "";
	}

	/**
	 * 生成和上传问题报告
	 *
	 * @param problemRep
	 * @return
	 */
	private String genAndUploadRep(List<PropblemReportRowVO<UserRechargeExcelVO>> problemRep) {
		Workbook wb = new XSSFWorkbook();
		Sheet sh = wb.createSheet("员工充值导入问题报告");
		for (int i = 0; i < problemRep.size(); i++) {
			PropblemReportRowVO<UserRechargeExcelVO> vo = problemRep.get(i);
			Row r = sh.createRow(i);
			UserRechargeExcelVO lineMeta = vo.getLineMeta();
			if (lineMeta != null) {
				Cell c0 = r.createCell(0);
				c0.setCellType(Cell.CELL_TYPE_STRING);
				c0.setCellValue(lineMeta.getMemberCode());
				r.createCell(1).setCellValue(lineMeta.getEmail());
				r.createCell(2).setCellValue(lineMeta.getSum());
				r.createCell(3).setCellValue(lineMeta.getReason());
				r.createCell(4).setCellValue(lineMeta.getRemark());
			}
			r.createCell(5).setCellValue(vo.getProblem());
			r.createCell(6).setCellValue("在第" + vo.getLineNo() + "行");
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			wb.write(bos);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("问题报告生成失败");
		}
		// 文件上传至文件服务器
		String filePath = uploadService.fastDFSUpload(bos.toByteArray(), "xlsx");
		return filePath;
	}

	/**
	 * 行转对象,进行空值校验和重复性校验 如果发现问题,存入报告列表中
	 * @param row
	 * @param problemRep
	 * @param rowNo
	 * @return
	 */
	private UserRechargeExcelVO row2Bean(Map<String, Object> row,
			List<PropblemReportRowVO<UserRechargeExcelVO>> problemRep, int rowNo) {
		UserRechargeExcelVO vo = new UserRechargeExcelVO();
		Object memberCode = row.get("CELL1");
		if (memberCode == null) {
			problemRep.add(new PropblemReportRowVO<>("员工编号为空", rowNo, vo));
		} else {
			vo.setMemberCode(memberCode.toString().trim());
		}
		Object email = row.get("CELL2");
		if (email == null) {
			problemRep.add(new PropblemReportRowVO<>("邮箱为空", rowNo, vo));
		} else {
			vo.setEmail(email.toString().trim());
		}
		Object sum = row.get("CELL3");
		if (sum == null) {
			problemRep.add(new PropblemReportRowVO<>("充值金额为空", rowNo, vo));
		} else {
			vo.setSum(Double.parseDouble(sum.toString().trim()));
		}
		Object reason = row.get("CELL4");
		if (reason == null) {
			problemRep.add(new PropblemReportRowVO<>("充值原因为空", rowNo, vo));
		} else {
			vo.setReason(reason.toString().trim());
		}
		Object remark = row.get("CELL5");
		if (remark != null) {
			vo.setRemark(remark.toString().trim());
		}
		return vo;
	}

	@Override
	public JsonResult<Map<String, Object>> impConfirm(String sn, Integer confirm, Long userId) {
		// confirm 0:跳过检查 1:需要检查确认
		if (confirm == null)
			confirm = 1;
		if (confirm == 1) {
			double sum = accountFacade.queryTempRechargeSummaryBySn(sn);
			logger.info("金额:"+sum);
			// 校验最近一周是否有相同金额的导入
			// 查询本次导入的总金额
			// 查询一周内导入的批次中sum相等的
			List<AccountBatchDTO> batchInWeek = accountFacade.querySameSumBatchInWeek(sum);
			if (EmptyUtil.isNotEmpty(batchInWeek)&&batchInWeek.size() > 0) {
				logger.info("相同批次:"+batchInWeek.get(0).getId());
				return JsonResult.fail("最近一周存在相同金额的导入，请确认是否存在重复导入", BusinessExceptionConstant.SIMILAR_RECHARGE_RECORD_FOUND);
			}
		}
		// 查询正式头信息 确保没有重复导入
		List<HeadImportRecordsDTO> impRec = importRecordsFacade.queryRecordsBySn(sn);
		if (impRec.size() > 0)
			return JsonResult.fail("该文件序列已经存在,请勿重复导入");
		// 从头信息草稿中查询这条头信息
		ImportRecordsDTO impRecTemp = importRecordsFacade.queryRecordTempBySn(sn);
		// 查询预览信息
		List<TempRechargeDTO> trList = accountFacade.queryTempRechargeBySn(sn);
		if (trList.size() == 0)
			return JsonResult.fail("预览表数据为空");
		ImportRecordsDTO ir = new ImportRecordsDTO();
		ir.setCompanyName(impRecTemp.getCompanyName());
		ir.setFileSequenceNumber(impRecTemp.getFileSequenceNumber());
		ir.setGeneratedTime(impRecTemp.getGeneratedTime());
		ir.setTemplateType(impRecTemp.getTemplateType());
		TempRechargeDTO tr0 = trList.get(0);
		logger.info("金额"+tr0.getSum());
		Long companyId = tr0.getCompanyId();
		CompanyAccountDTO ca = accountFacade.queryNormalCompanyAccountByCompnayId(companyId);
		if (ca == null)
			return JsonResult.fail("公司账户不存在");
		SaltDTO caSalt = secretFacade.querySaltByUUID(ca.getUuid());
		if (caSalt == null)
			return JsonResult.fail("公司账户加密数据未就绪");
		Long reasonId = tr0.getReasonId();
		AdjustReasonDTO reason = accountFacade.queryAdjustReasonById(reasonId);
		String remark = tr0.getRemark();
		Long platformId = tr0.getPlatformId();

		//判断是否存在账户锁
		String accountKey = jedisUtil.getString(JedisUtil.ACCOUNT_DISABLED_LOCK_KEY_PRE + companyId);
		if(EmptyUtil.isNotEmpty(accountKey)){
			logger.info("正在进行企业账户失效操作，请稍后再试,code="+BusinessExceptionConstant.ACCOUNT_LOCKED);
			return JsonResult.fail("企业账户已冻结，无法进行员工账户充值",BusinessExceptionConstant.ACCOUNT_LOCKED);
		}

		// 账户参数准备
		// 转出账户
		List<CashFlowAccountDTO> outFlowAccs = new ArrayList<>();
			CashFlowAccountDTO ca_ = new CashFlowAccountDTO();
			ca_.setAccountId(ca.getId());
			ca_.setSalt(caSalt.getSaltValue());
			ca_.setCompanyId(ca.getCompanyId());
			ca_.setSum(tr0.getSum());
			logger.info("金额1:"+tr0.getSum());
			outFlowAccs.add(ca_);

		// 转入账户
		List<CashFlowAccountDTO> inFlowAccs = new ArrayList<>();
		for (TempRechargeDTO tr : trList) {
			CashFlowAccountDTO ua_ = new CashFlowAccountDTO();
			ua_.setAccountId(tr.getAccountId());
			ua_.setSalt(tr.getSalt());
			ua_.setSum(tr.getSum());
			logger.info("金额2:"+tr.getSum());
			ua_.setSubRemark(tr.getFlowReason());
			ua_.setUserId(tr.getUserId());
			ua_.setUserAccountType(tr.getAccountType());
			inFlowAccs.add(ua_);
		}

		int finBatchType = FinBatchConstant.translateFlowType(reason.getType());
		accountFacade.unifiedCashFlow(outFlowAccs, 0, inFlowAccs, 1, true, platformId, reason.getType(), null, null,
				userId, finBatchType, reasonId, remark, true, 1);

		// 将导入文件的头信息加入正式头信息列表
		HeadImportRecordsDTO formalHead = new HeadImportRecordsDTO();
		formalHead.setId(impRecTemp.getId());
		formalHead.setCompanyName(impRecTemp.getCompanyName());
		formalHead.setFileSequenceNumber(impRecTemp.getFileSequenceNumber());
		formalHead.setGeneratedTime(impRecTemp.getGeneratedTime());
		formalHead.setTemplateType(impRecTemp.getTemplateType());
		importRecordsFacade.insertHeadImportRecords(formalHead);
		return JsonResult.success();
	}

	@Override
	public JsonResult<Map<String, Object>> userAssets(Long userId) {
		List<UserAccountDTO> uas = accountFacade.queryUserAccountByUserId(userId);
		if (uas.size() == 0)
			return JsonResult.fail("暂无数据");
		AccountBalanceVO fbAcc = new AccountBalanceVO();
		AccountBalanceVO ppAcc = new AccountBalanceVO();
		AccountBalanceVO cpAcc = new AccountBalanceVO();
		AccountBalanceVO fdAcc = new AccountBalanceVO();
		AccountBalanceVO jiDianAcc = new AccountBalanceVO();

		UserExtendDTO extDTO = userExtService.userByUserId(userId);
		JSONObject dataJSONObject = null;
		BigDecimal fanka =  new BigDecimal(0);
		BigDecimal jidian =  new BigDecimal(0);
		boolean isDlf = false;
		if(extDTO !=null && EmptyUtil.isNotEmpty(extDTO.getChannelSource()) && Objects.equals(extDTO.getChannelSource(), UserChannelSourceEnum.DLF.getChannelSource()) && EmptyUtil.isNotEmpty(extDTO.getMobile())){
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
				logger.info("返回-{}",JSON.toJSONString(jsonResult));
				JSONObject parseObject =JSONObject.parseObject(JSON.toJSONString(jsonResult.getData()));
				if("1".equals(parseObject.getString("status"))){
					dataJSONObject = parseObject.getJSONObject("data");
					fanka = dataJSONObject.getBigDecimal("fanka");
					jidian = dataJSONObject.getBigDecimal("jidian");
				}
			}
		}
		boolean isYd = false;
		if(extDTO !=null && EmptyUtil.isNotEmpty(extDTO.getChannelSource()) && Objects.equals(extDTO.getChannelSource(), UserChannelSourceEnum.YD.getChannelSource())){
			isYd = true;
			UserDTO userDTO = userService.findUserByID(userId);
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
				logger.info("渠道{}返回-{}",extDTO.getChannelSource(),JSON.toJSONString(jsonResult));
				JSONObject parseObject =JSONObject.parseObject(JSON.toJSONString(jsonResult.getData()));
				if("1".equals(parseObject.getString("status"))){
					dataJSONObject = parseObject.getJSONObject("data");
					fanka = dataJSONObject.getBigDecimal("money");
				}
			}
		}

		List<CompanyConfigDTO> companyConfigs = userService.findUserCompanyConfigs(userId);
		for (UserAccountDTO ua : uas) {
			if (ua.getType().intValue() == 0) {
				String name="积分";
				String url = "";
				Integer type=0;
				Integer detail=1;
				for(CompanyConfigDTO config : companyConfigs) {
					if(config.getKey().equalsIgnoreCase("account.0.name")) {
						name = config.getValue();
					}else if(config.getKey().equalsIgnoreCase("account.0.url")) {
						url = config.getValue();
					}else if(config.getKey().equalsIgnoreCase("account.0.type")) {
						type = Integer.valueOf(config.getValue());
					}else if(config.getKey().equalsIgnoreCase("account.0.detail")) {
						detail = Integer.valueOf(config.getValue());
					}
				}
				if(type.intValue()==1) {
					fbAcc.setAccountId(ua.getId());
					fbAcc.setAccountName(name);

					BigDecimal defaultBalance = new BigDecimal(0);
					fbAcc.setBalance(defaultBalance);
					if(isDlf){
						fbAcc.setBalance(fanka);
					}else if(isYd){
						fbAcc.setBalance(fanka);
					}else if(extDTO!=null&&extDTO.getRemark()!=null && !isDlf && !isYd) {
						String jsonStr = HttpClientUtil.doGet(url+extDTO.getRemark());
						JSONObject parseObject = JSONObject.parseObject(jsonStr);
						if(parseObject.containsKey("code") && parseObject.getInteger("code").intValue()==0) {
							JSONObject parseData = parseObject.getJSONObject("data");
							if(parseData.containsKey("balance") && parseData.getString("balance")!=null && parseData.getBigDecimal("balance")!=null) {
								BigDecimal thirdBalance = parseData.getBigDecimal("balance");
								fbAcc.setBalance(thirdBalance);
							}
						}

					}
				}else if(type.intValue()==2){
					fbAcc.setBalance(fanka);
				}else {
					if(isDlf){
						fbAcc.setAccountId(ua.getId());
						fbAcc.setAccountName("餐卡");
						fbAcc.setBalance(fanka);
					}else if(isYd){
						fbAcc.setAccountId(ua.getId());
						fbAcc.setAccountName("余额");
						fbAcc.setBalance(fanka);
					}else{
						fbAcc.setAccountId(ua.getId());
						fbAcc.setAccountName("积分");
						fbAcc.setBalance(ua.getBalance());
					}

				}
				fbAcc.setDetail(detail);
			}
			if (ua.getType().intValue() == 1) {

				String name="点赞福豆";
				String url = "";
				Integer type=0;
				Integer detail=1;
				for(CompanyConfigDTO config : companyConfigs) {
					if(config.getKey().equalsIgnoreCase("account.1.name")) {
						name = config.getValue();
					}else if(config.getKey().equalsIgnoreCase("account.1.url")) {
						url = config.getValue();
					}else if(config.getKey().equalsIgnoreCase("account.1.type")) {
						type = Integer.valueOf(config.getValue());
					}else if(config.getKey().equalsIgnoreCase("account.1.detail")) {
						detail = Integer.valueOf(config.getValue());
					}
				}
				ppAcc.setAccountId(ua.getId());
				ppAcc.setAccountName(name);
				ppAcc.setBalance(ua.getBalance());

				ppAcc.setDetail(detail);
			}
			if (ua.getType().intValue() == 3) {

				String name="现金支出";
				String url = "";
				Integer type=0;
				Integer detail=1;
				for(CompanyConfigDTO config : companyConfigs) {
					if(config.getKey().equalsIgnoreCase("account.3.name")) {
						name = config.getValue();
					}else if(config.getKey().equalsIgnoreCase("account.3.url")) {
						url = config.getValue();
					}else if(config.getKey().equalsIgnoreCase("account.3.type")) {
						type = Integer.valueOf(config.getValue());
					}		else if(config.getKey().equalsIgnoreCase("account.3.detail")) {
						detail = Integer.valueOf(config.getValue());
					}
				}
				cpAcc.setAccountId(ua.getId());
				cpAcc.setAccountName(name);
				cpAcc.setBalance(ua.getBalance().negate());
				cpAcc.setDetail(detail);
			}
			if (ua.getType().intValue() == 4) {

				String name="退回积分";
				String url = "";
				Integer type=0;
				Integer detail=1;
				for(CompanyConfigDTO config : companyConfigs) {
					if(config.getKey().equalsIgnoreCase("account.4.name")) {
						name = config.getValue();
					}else if(config.getKey().equalsIgnoreCase("account.4.url")) {
						url = config.getValue();
					}else if(config.getKey().equalsIgnoreCase("account.4.type")) {
						type = Integer.valueOf(config.getValue());
					}	else if(config.getKey().equalsIgnoreCase("account.4.detail")) {
						detail = Integer.valueOf(config.getValue());
					}
				}
				fdAcc.setAccountId(ua.getId());
				fdAcc.setAccountName(name);
				fdAcc.setBalance(ua.getBalance());
				fdAcc.setDetail(detail);
			}

			if (ua.getType().intValue() == 5) {

				String name="积点";
				String url = "";
				Integer type=0;
				Integer detail=1;
				for(CompanyConfigDTO config : companyConfigs) {
					if(config.getKey().equalsIgnoreCase("account.5.name")) {
						name = config.getValue();
					}else if(config.getKey().equalsIgnoreCase("account.5.url")) {
						url = config.getValue();
					}else if(config.getKey().equalsIgnoreCase("account.5.type")) {
						type = Integer.valueOf(config.getValue());
					}	else if(config.getKey().equalsIgnoreCase("account.5.detail")) {
						detail = Integer.valueOf(config.getValue());
					}
				}
				jiDianAcc.setAccountId(ua.getId());
				jiDianAcc.setAccountName(name);
				jiDianAcc.setBalance(jidian);
				jiDianAcc.setDetail(detail);
			}
		}
		Map<String, Object> data = new HashMap<>();
		data.put("fb", fbAcc);
		data.put("pp", ppAcc);
		data.put("cp", cpAcc);
		data.put("fd", fdAcc);
		data.put("jidian", jiDianAcc);
		return JsonResult.success(data);
	}

	@Override
	public UserAccountDTO userAccount(Long userId ,Integer type) {
		UserAccountDTO ua = accountFacade.queryUserAccountByUserIdAndType(userId,type);
		return ua;
	}
	@Override
	public JsonResult<Map<String, Object>> userAccountFlowPage(Long accountId, Integer mode, Integer pageNo,
			Integer pageSize, Long userId) {
		if (accountId == null)
			return JsonResult.fail("请选择账户");
		if (pageNo == null)
			pageNo = 1;
		if (pageSize == null)
			pageSize = 20;
		UserAccountDTO ua = accountFacade.queryUserAccountById(accountId);
		if (ua == null)
			return JsonResult.fail("账户不存在");
		if (ua.getUserId().longValue() != userId)
			return JsonResult.fail("只能查看自己的账户");
		Integer accountType = ua.getType();
		Map<String, Object> data = new HashMap<>();
		// 仅查询第一页时查询账户余额信息
		data.put("balance", Math.abs(ua.getBalance().doubleValue()));

		UserDTO userDTO = userService.findUserByID(userId);
		UserExtendDTO extDTO = userExtService.userByUserId(userId);
		if(userDTO != null && extDTO!=null &&  EmptyUtil.isNotEmpty(userDTO.getChannelSource())){
			JSONObject dataJSONObject = null;
			BigDecimal fanka =  new BigDecimal(0);
			BigDecimal jidian =  new BigDecimal(0);
			if(Objects.equals(userDTO.getChannelSource(), UserChannelSourceEnum.DLF.getChannelSource())){
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
					logger.info("返回-{}",JSON.toJSONString(jsonResult));
					JSONObject parseObject =JSONObject.parseObject(JSON.toJSONString(jsonResult.getData()));
					if("1".equals(parseObject.getString("status"))){
						dataJSONObject = parseObject.getJSONObject("data");
						fanka = dataJSONObject.getBigDecimal("fanka");
						jidian = dataJSONObject.getBigDecimal("jidian");
					}
				}
				if(accountType !=null && accountType.intValue() ==0){
					data.put("balance", fanka);
				}else if(accountType !=null && accountType.intValue() ==5){
					data.put("balance", jidian);
				}
			}
			if(extDTO !=null && EmptyUtil.isNotEmpty(extDTO.getChannelSource()) && Objects.equals(extDTO.getChannelSource(), UserChannelSourceEnum.YD.getChannelSource())){
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
					logger.info("渠道{}返回-{}",extDTO.getChannelSource(),JSON.toJSONString(jsonResult));
					JSONObject parseObject =JSONObject.parseObject(JSON.toJSONString(jsonResult.getData()));
					if("1".equals(parseObject.getString("status"))){
						dataJSONObject = parseObject.getJSONObject("data");
						fanka = dataJSONObject.getBigDecimal("money");
					}
				}
				if(accountType !=null && accountType.intValue() ==0){
					data.put("balance", fanka);
				}
			}
		}
		switch (accountType) {
		case 0:
			data.put("name", "积分余额");
			break;
		case 1:
			data.put("name", "点赞福豆余额");
			break;
		case 4:
			data.put("name", "累计现金支出");
			break;
			case 5:
				data.put("name", "积点余额");
				break;
		default:
			data.put("name", "未知账户");
		}
		logger.info("用户id:{},账户类型:{}账户流水明细中展示:{}",userId,accountType,JSONObject.toJSONString(data));
		// 查询分页列表
		Pagination page = new Pagination(pageNo, pageSize);
		PageResult<AccountFlowDTO> dtoPage = accountFacade.queryAccountFlowPageByAccountId(page, accountId, mode);
		List<AccountFlowDTO> dtoList = dtoPage.getList();
		PageResult<AssetVO> voPage = new PageResult<>();
		List<AssetVO> voList = new ArrayList<>();
		Integer allHasNew = 0;
		Integer inHasNew = 0;
		Integer outHasNew = 0;
		if (dtoList.size() > 0) {
			for (AccountFlowDTO dto : dtoList) {
				AssetVO vo = new AssetVO();
				vo.setIsRead(dto.getIsRead());
				vo.setCreateTime(dto.getCreateTime().getTime());
				vo.setId(dto.getId());
				vo.setOrderCode(dto.getOrderCode());
				int type = dto.getType();
				vo.setType(type);
				boolean isOut = dto.getOutflowAccountid().longValue() == accountId;
				if (dto.getIsRead() == 0) {
					allHasNew = 1;
					if (isOut) {
						outHasNew = 1;
					} else {
						inHasNew = 1;
					}
				}

				if (type == FlowTypeConstant.UP_PRAISE.getStatus()) {
					// 点赞记录
					if (isOut) {
						// 点赞
						vo.setRemark("为同事点赞");
					} else {
						// 收获点赞
						vo.setRemark("同事点赞奖励");
					}
				} else if (type == FlowTypeConstant.OP_FUBI.getStatus()
						|| type == FlowTypeConstant.OP_CASH.getStatus()) {
					// 积分/现金支付记录
					vo.setRemark("订单付款");
				} else if (type == FlowTypeConstant.OR_FUBI.getStatus()
						|| type == FlowTypeConstant.OR_CASH.getStatus()) {
					vo.setRemark("订单退款");
				} else if(type == FlowTypeConstant.UA_LEAVE.getStatus()){
					vo.setRemark("账户失效资产回收");
				} else{
					vo.setRemark(dto.getRemark());
				}
				vo.setSum((isOut ? -1 : 1) * dto.getSum().doubleValue());
				// int转String
				vo.setCurrencyType(currencyTypeToString(dto.getCurrencyType()));
				voList.add(vo);
			}
		}
		voPage.setList(voList);
		voPage.copy(dtoPage);
		data.put("detailPage", voPage);
		data.put("allHasNew", allHasNew);
		data.put("outHasNew", outHasNew);
		data.put("inHasNew", inHasNew);

		return JsonResult.success(data);
	}

	/**
	 * int转String
	 *
	 * @param currencyType
	 * @return
	 */
	private String currencyTypeToString(Integer currencyType) {
		if (EmptyUtil.isEmpty(currencyType)) {
			return null;
		}
		// 货币类型 0:积分 1:点赞福豆 2:现金
		switch (currencyType) {
		case 0:
			return "积分";
		case 1:
			return "点赞福豆";
		case 2:
			return "现金";
		default:
			return "多种货币类型";
		}
	}

	/**
	 * 判断账户明细金额的正负
	 *
	 * @param doubleValue
	 * @param detailType
	 *            明细类型 (参见FlowTypeConstant)
	 * @param accountType
	 *            账户类型 0积分账户 1:点赞福豆账户 3:现金账户
	 * @return
	 */
	private double appendPosNeg2Num(double doubleValue, Integer detailType, Integer accountType) {
		int signal = 1;
		switch (accountType) {
		case 0:
			switch (detailType) {
			case 6:
			case 7:
			case 10:
				signal = 1;
				break;
			case 4:
			case 9:
				signal = -1;
				break;
			}
			break;
		case 1:
			switch (detailType) {
			case 8:
				signal = 1;
				break;
			case 9:
			case 10:
				signal = -1;
				break;
			}
			break;
		case 3:
			switch (detailType) {
			case 3:
				signal = -1;
				break;

			case 5:
				signal = 1;
				break;
			}

			break;
		}
		return signal * doubleValue;
	}

	/**
	 * 冻结或扣除用户积分账户
	 *
	 * @param req
	 * @return
	 */
	@Override
	public Integer foscoinAccountDeductWithTx(Long userId, String orderCode, Long platformId, String paymentPassword,
			String userName,String ip,String mac,Long companyId,Integer payType,String realName,String idCardNo,String cardIds, HttpServletRequest req) {

		// 冻结或扣除用户积分账户
		return accountFacade.foscoinAccountDeductWithTx(userId, orderCode, platformId, paymentPassword,userName,ip,mac,companyId,payType,realName,idCardNo,cardIds,req);
	}


	@Override
	public JsonResult<Map<String, Object>> userAccountRechargeRecDetail(Long batchId, Integer pageNo, Integer pageSize,
			Long platformId) {
		if (batchId == null)
			return JsonResult.fail("请选择批次");
		if (pageNo == null)
			pageNo = 1;
		if (pageSize == null)
			pageSize = 20;
		Pagination page = new Pagination(pageNo, pageSize);
		PageResult<AccountFlowDTO> dtoPage = accountFacade.queryAccountFlowPage(batchId, null, null, null, null,platformId, page);
		PageResult<RechargeRecDetailVO> voPage = new PageResult<>();
		List<AccountFlowDTO> dtoList = dtoPage.getList();
		List<RechargeRecDetailVO> voList = new ArrayList<>();
		if (dtoList.size() > 0) {
			for (AccountFlowDTO dto : dtoList) {
				RechargeRecDetailVO vo = new RechargeRecDetailVO();
				// 获取用户账户id
				Long userAccId = dto.getInflowAccountid();
				UserAccountDTO ua = accountFacade.queryUserAccountById(userAccId);
				Long userId = ua.getUserId();
				UserExtendDTO ue = userFacade.queryFullUserExtendById(userId);
				Long companyId = ue.getCompanyId();
				CompanyDTO company = userFacade.queryCompanyById(companyId);
				vo.setCompanyName(company.getCompanyName());
				vo.setEmail(ue.getMail());
				vo.setFlowId(dto.getId());
				vo.setMemberCode(ue.getMemberCode());
				vo.setSum(dto.getSum().doubleValue());
				vo.setUserName(ue.getName());
				voList.add(vo);
			}
		}
		voPage.setList(voList);
		voPage.copy(dtoPage);
		return JsonResult.success("detailPage", voPage);
	}

	/**
	 * 根据订单编号解冻订单积分
	 *
	 * @param userId
	 * @param orderCode
	 * @param platformId
	 * @return
	 */
	@Override
	public Integer unfreezeSoFubi(Long userId, String orderCode, Long platformId) {
		SoDTO soDTO = accountFacade.querySoByOrderCode(orderCode);
		accountFacade.cancelSoFreezeFubi(soDTO.getId(), orderCode, userId);
		accountFacade.delByOrderIdWithTx(soDTO.getId());
		return 1;
	}

	/**
	 * 根据订单编号解冻订单积分
	 *
	 * @param userId
	 * @param orderCode
	 * @param platformId
	 * @return
	 */
	@Override
	public Integer unfreezeSoChildFubi(Long userId, String orderCode, Long platformId) {
		SoDTO soDTO = accountFacade.querySoByChildCode(orderCode);
		accountFacade.cancelSoFreezeFubi(soDTO.getId(), orderCode, userId);
		accountFacade.delByOrderIdWithTx(soDTO.getId());
		return 1;
	}

	@Override
	public Integer checkBalance(Long userId, String month, Long platformId) {
		//同步month及month前一个月的数据
		String thisMonth = DateUtil.stampToDateMonth(new Date());


		Calendar calendar = Calendar.getInstance();//日历对象

		calendar.setTime(new Date());//设置当前日期

		calendar.add(Calendar.MONTH, -1);//月份减一
		String lastMonth = DateUtil.stampToDateMonth(calendar.getTime());
		soService.querySoByCreateMonth(thisMonth);
		soService.querySoByCreateMonth(lastMonth);

		return 1;
	}
	@Override
	public JsonResult<Map<String, Object>> userFinFlowPage(Long userId, Integer accountType, Integer pageNo,
			Integer pageSize) {
		if (userId == null)
			return JsonResult.fail("请选择用户");
		List<Long> accountIdList = new ArrayList<>();
		if (accountType != null) {
			// 查询对应类型的账户
			UserAccountDTO ua = accountFacade.queryUserAccountByUserIdAndType(userId, accountType);
			if (ua == null) {
				return JsonResult.fail("用户账户不存在");
			}
			accountIdList.add(ua.getId());
		} else {
			// 查询用户所有账户
			List<UserAccountDTO> uas = accountFacade.queryUserAccountByUserId(userId);
			for (UserAccountDTO ua : uas) {
				accountIdList.add(ua.getId());
			}
		}
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		Pagination page = new Pagination(pageNo, pageSize);
		//注释方法已经抽离成独立的方法了
		/*PageResult<UserFinFlowVO> voPage = new PageResult<>();
		List<UserFinFlowVO> voList = new ArrayList<>();
		if (accountIdList.size() > 0) {
			// 分页查询与涉及到的账户有关的流水
			PageResult<AccountFlowDTO> flowPage = accountFacade.userFinFlowPage(accountIdList, page);
			List<AccountFlowDTO> dtoList = flowPage.getList();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (AccountFlowDTO dto : dtoList) {
				UserFinFlowVO vo = new UserFinFlowVO();
				vo.setFlowId(dto.getId());
				vo.setFlowType(FlowTypeConstant.translate(dto.getType()));
				// 账户类型 0:公司账户 1:员工账户,
				Integer ifaType = dto.getInflowAccounttype();
				Integer ofaType = dto.getOutflowAccounttype();
				Long ifaId = dto.getInflowAccountid();
				Long ofaId = dto.getOutflowAccountid();
				String ifaName = null;
				String ofaName = null;
				// 正负标志
				int posNeg = 1;
				if (ifaType == 0) {
					CompanyAccountDTO ca = accountFacade.queryCompanyAccountById(dto.getInflowAccountid());
					if (ca != null) {
						ifaName = ca.getName();
					}
				} else {
					UserAccountDTO ua = accountFacade.queryUserAccountById(dto.getInflowAccountid());
					if (ua != null) {
						ifaName = ua.getName();
					}
					// 检查是否在当前账户id范围内
					boolean idFound = idInRange(ifaId, accountIdList);
					// 若是,则金额是增加
					if (idFound)
						posNeg = 1;

				}
				if (ofaType == 0) {
					CompanyAccountDTO ca = accountFacade.queryCompanyAccountById(dto.getOutflowAccountid());
					if (ca != null) {
						ofaName = ca.getName();
					}
				} else {
					UserAccountDTO ua = accountFacade.queryUserAccountById(dto.getOutflowAccountid());
					if (ua != null) {
						ofaName = ua.getName();
					}
					// 检查是否在当前账户id范围内
					boolean idFound = idInRange(ofaId, accountIdList);
					// 若是,则金额是减少
					if (idFound)
						posNeg = -1;
				}
				vo.setInFlowAccount(ifaName);
				vo.setOutFlowAccount(ofaName);
				Long reasonId = dto.getReasonId();
				AdjustReasonDTO reason = accountFacade.queryAdjustReasonById(reasonId);
				if (reason != null) {
					vo.setReason(reason.getName());
				}
				vo.setRemark(dto.getRemark());
				vo.setSum(dto.getSum().doubleValue() * posNeg);
				vo.setCreateTime(sdf.format(dto.getCreateTime()));
				vo.setCurrencyType(currencyTypeToString(dto.getCurrencyType()));
				voList.add(vo);
			}
			voPage.copy(flowPage);
		}*/
		PageResult<UserFinFlowVO> voPage=getAccountList(accountIdList,page, null, null);
		return JsonResult.success("page", voPage);
	}

	/**
	 * 根据账户id集合查询出账户流水
	 * @param accountIdList
	 * @param page
	 * @param startTime
	 *@param endTime @return
	 */
	private PageResult<UserFinFlowVO> getAccountList(List<Long> accountIdList, Pagination page, Date startTime, Date endTime){
		PageResult<UserFinFlowVO> voPage = new PageResult<>();
		List<UserFinFlowVO> voList = new ArrayList<>();
		if (accountIdList.size() > 0) {
			// 分页查询与涉及到的账户有关的流水
			PageResult<AccountFlowDTO> flowPage = accountFacade.userFinFlowPage(accountIdList, page,startTime,endTime);
			List<AccountFlowDTO> dtoList = flowPage.getList();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (AccountFlowDTO dto : dtoList) {
				UserFinFlowVO vo = new UserFinFlowVO();
				vo.setFlowId(dto.getId());
				vo.setFlowType(FlowTypeConstant.translate(dto.getType()));
				// 账户类型 0:公司账户 1:员工账户,
				Integer ifaType = dto.getInflowAccounttype();
				Integer ofaType = dto.getOutflowAccounttype();
				Long ifaId = dto.getInflowAccountid();
				Long ofaId = dto.getOutflowAccountid();
				String ifaName = null;
				String ofaName = null;
				// 正负标志
				int posNeg = 1;
				if (ifaType == 0) {
					CompanyAccountDTO ca = accountFacade.queryCompanyAccountById(dto.getInflowAccountid());
					if (ca != null) {
						ifaName = ca.getName();
					}
				} else {
					UserAccountDTO ua = accountFacade.queryUserAccountById(dto.getInflowAccountid());
					if (ua != null) {
						ifaName = ua.getName();
					}
					// 检查是否在当前账户id范围内
					boolean idFound = idInRange(ifaId, accountIdList);
					// 若是,则金额是增加
					if (idFound)
						posNeg = 1;

				}
				if (ofaType == 0) {
					CompanyAccountDTO ca = accountFacade.queryCompanyAccountById(dto.getOutflowAccountid());
					if (ca != null) {
						ofaName = ca.getName();
					}
				} else {
					UserAccountDTO ua = accountFacade.queryUserAccountById(dto.getOutflowAccountid());
					if (ua != null) {
						ofaName = ua.getName();
					}
					// 检查是否在当前账户id范围内
					boolean idFound = idInRange(ofaId, accountIdList);
					// 若是,则金额是减少
					if (idFound)
						posNeg = -1;
				}
				vo.setInFlowAccount(ifaName);
				vo.setOutFlowAccount(ofaName);
				Long reasonId = dto.getReasonId();
				AdjustReasonDTO reason = accountFacade.queryAdjustReasonById(reasonId);
				if (reason != null) {
					vo.setReason(reason.getName());
				}
				vo.setRemark(dto.getRemark());
				vo.setSum(dto.getSum().doubleValue() * posNeg);
				vo.setCreateTime(sdf.format(dto.getCreateTime()));
				vo.setCurrencyType(currencyTypeToString(dto.getCurrencyType()));
				voList.add(vo);
			}
			voPage.copy(flowPage);
		}
		voPage.setList(voList);
		return voPage;
	}

	/**
	 * id是否存在与id列表中
	 *
	 * @param tarId
	 * @param accountIdList
	 * @return
	 */
	private boolean idInRange(Long tarId, List<Long> accountIdList) {
		for (Long id : accountIdList) {
			if (tarId.longValue() == id.longValue()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public JsonResult<Map<String, Object>> flowTypeList() {
		FlowTypeConstant[] typeArr = FlowTypeConstant.values();
		List<Map<String, Object>> list = new ArrayList<>();
		for (int i = 0; i < typeArr.length; i++) {
			FlowTypeConstant t = typeArr[i];
			Map<String, Object> map = new HashMap<>();
			map.put("type", t.getStatus());
			map.put("name", t.getComment());
			list.add(map);
		}
		return JsonResult.success("list", list);
	}

	@Override
	public JsonResult<Map<String, Object>> reasonDetail(Long reasonId) {
		if (reasonId == null) {
			return JsonResult.fail("请选择原因");
		}
		AdjustReasonDTO dto = accountFacade.queryAdjustReasonById(reasonId);
		if (dto == null) {
			return JsonResult.fail("该原因不存在");
		}
		AdjustReasonVO vo = new AdjustReasonVO();
		vo.setDisabled(dto.getDisabled());
		vo.setId(dto.getId());
		vo.setName(dto.getName());
		int type = dto.getType();
		vo.setType(dto.getType());
		vo.setTypeName(FlowTypeConstant.translate(type));
		List<ReasonCompanyDTO> rcs = accountFacade.queryReasonCompanyListByReasonId(reasonId);
		List<Long> companyIds = new ArrayList<>();
		for (ReasonCompanyDTO rc : rcs) {
			companyIds.add(rc.getCompanyId());
		}
		vo.setCompanyIds(companyIds);
		return JsonResult.success("reason", vo);
	}

	@Override
	public JsonResult<Map<String, Object>> certainTypeReasons(Long platformId, String types, Long accountId, Long companyId) {
		if (EmptyUtil.isBlank(types))
			return JsonResult.fail("请指定类型");
		List<Integer> typeList = new ArrayList<>();
		String[] typeStrArray = types.split(",");
		if (typeStrArray.length == 0)
			return JsonResult.fail("请至少指定一个类型");
		for (String s : typeStrArray) {
			typeList.add(Integer.parseInt(s));
		}
		List<AdjustReasonDTO> dtoList = accountFacade.queryAdjustReasonsByTypes(platformId,typeList, accountId,companyId);
		return JsonResult.success("reasons", dtoList);
	}

	@Override
	public SoDTO querySoByOrderCode(String orderCode) {
		return accountFacade.querySoByOrderCode(orderCode);
	}

	@Override
	public JsonResult<Map<String, Object>> batchExamReason(Long id) {
		if (id == null) {
			return JsonResult.fail("请选择批次");
		}
		AccountBatchTmpDTO tmp = accountFacade.queryAccountBatchTmpById(id);
		if (tmp == null) {
			return JsonResult.fail("批次不存在");
		}
		return JsonResult.success("reason", tmp.getExamReason());
	}

	@Override
	public JsonResult<Map<String, Object>> userFlowExportBackStage(List<Long> userIdList, Long platformId, Date startTime, Date endTime) {
		//组织要导出的数据
		//1.查询用户账户信息
		List<UserExtendDTO> ues = userFacade.queryFullUserExtend(userIdList);
		if(EmptyUtil.isEmpty(ues)){
			return JsonResult.fail("用户不存在");
		}
		List<UserAccountVO> userAccountList = getUserAccountList(ues, platformId);

		//2.查询每个用户的账户流水
		if(EmptyUtil.isEmpty(userAccountList)){
			return JsonResult.fail("没有查询到用户相关账户");
		}
		List<UserFinFlowVO> userFinFlowVOS = new ArrayList<>();
		for(UserAccountVO vo:userAccountList){
			List<Long> accountList = new ArrayList<>();
			List<UserAccountDTO> userAccountDTOS = accountFacade.queryUserAccountByUserId(vo.getUserId());//null
			if(EmptyUtil.isEmpty(userAccountDTOS)){
				return JsonResult.fail("没有查询到用户相关账户");
			}
			for(UserAccountDTO dto:userAccountDTOS){
				accountList.add(dto.getId());
			}
			PageResult<UserFinFlowVO> accountList1 = getAccountList(accountList, null,startTime,endTime);
			List<UserFinFlowVO> list = accountList1.getList();
			if(EmptyUtil.isNotEmpty(list)){
				for(UserFinFlowVO flowVO:list){
					flowVO.setCompanyName(vo.getCompanyName());
					flowVO.setEmail(vo.getEmail());
					flowVO.setName(vo.getName());
					flowVO.setUserCode(vo.getMemberCode());
					userFinFlowVOS.add(flowVO);
				}
			}
		}

		//创建xml文件
		Workbook wb = new XSSFWorkbook();
		Sheet s = wb.createSheet("员工资产管理明细");
		Sheet s2 = wb.createSheet("员工流水明细");
		String[] header={"姓名","员工编号","邮箱","所属公司","积分账户","点赞福豆账户","积分冻结账户","现金支出账户"};
//		String[] header2={"姓名","员工编号","邮箱","所属公司","积分账户","点赞福豆账户","积分冻结账户","现金支出账户","流入方","流出方","金额","时间","货币类型","流水类型","原因","备注"};
		String[] header2={"姓名","员工编号","邮箱","所属公司","流入方","流出方","金额","时间","货币类型","流水类型","原因","备注"};
		Row head=s.createRow(0);
		Row head2=s2.createRow(0);
		for (int i = 0; i < header.length; i++) {
			head.createCell(i).setCellValue(header[i]);
		}
		for(int i=0;i<header2.length;i++){
			head2.createCell(i).setCellValue(header2[i]);
		}
		completeUserAccountVOSheet(s,userAccountList);
		completeUserFinFlowVOSheet(s2,userFinFlowVOS);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			wb.write(bos);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail("导出失败!");
		}
		String upload = uploadService.fastDFSUpload(bos.toByteArray(), "xlsx");
		return JsonResult.success("url", upload);
	}

	private void completeUserFinFlowVOSheet(Sheet s,List<UserFinFlowVO> userFinFlowVOS){
		for (int i = 0; i < userFinFlowVOS.size(); i++) {
			Row r = s.createRow(i + 1);
			UserFinFlowVO vo = userFinFlowVOS.get(i);
			r.createCell(0).setCellValue(vo.getName());
			r.createCell(1).setCellValue(vo.getUserCode());
			r.createCell(2).setCellValue(vo.getEmail());
			r.createCell(3).setCellValue(vo.getCompanyName());
			r.createCell(4).setCellValue(vo.getInFlowAccount());
			r.createCell(5).setCellValue(vo.getOutFlowAccount());
			r.createCell(6).setCellValue(vo.getSum());
			r.createCell(7).setCellValue(vo.getCreateTime());
			r.createCell(8).setCellValue(vo.getCurrencyType());
			r.createCell(9).setCellValue(vo.getFlowType());
			r.createCell(10).setCellValue(vo.getReason());
			r.createCell(11).setCellValue(vo.getRemark());

		}
	}

	private void completeUserAccountVOSheet(Sheet s,List<UserAccountVO> userAccountList){
			for (int i = 0; i < userAccountList.size(); i++) {
				Row r = s.createRow(i + 1);
				UserAccountVO vo = userAccountList.get(i);
				r.createCell(0).setCellValue(vo.getName());
				r.createCell(1).setCellValue(vo.getMemberCode());
				r.createCell(2).setCellValue(vo.getEmail());
				r.createCell(3).setCellValue(vo.getCompanyName());
				r.createCell(4).setCellValue(vo.getFbAcc());
				r.createCell(5).setCellValue(vo.getPpAcc());
				r.createCell(6).setCellValue(vo.getFbfAcc());
				r.createCell(7).setCellValue(vo.getcAcc());
			}

	}

	@Override
	public Map<String, Object> AccountDisabledLock(Long accountId,CacheUser user) {
		Map<String, Object> map = new HashMap<>();
		//查询账户所属公司
		CompanyAccountDTO companyAccountDTO = accountFacade.queryCompanyAccountById(accountId);
		Long companyId = companyAccountDTO.getCompanyId();

		String key = "AccountLock-" + companyId;

		long i = jedisUtil.setnx(key, user.getLoginName());

		String currentUser = jedisUtil.getString(key);
		//如果获取锁失败，并不是本人持有锁，判断当前用户是否有权限解锁
		//如果有权限 role 为true 返回当前持锁人
		//如果没有权限 role 为false 返回当前持锁人
		//根据userId 判断该用户是否为管理员

		if(i < 1 && !user.getLoginName().equals(currentUser)) {

			boolean role = accountFacade.checkUserByUserId(user);

			map.put("isSuccess", false);
			map.put("data", currentUser);
			map.put("role", role);
			logger.info("获取用户锁失败：当前持有锁用户：{}",currentUser);
		} else {
			map.put("isSuccess", true);
			map.put("data", "");
			map.put("role", true);
			logger.info("获取用户锁成功：当前持有锁用户：{}",currentUser);
		}

		return map;
	}

	@Override
	public void releaseAccountLock(Long id,int type,CacheUser user) {
		Long companyId = 0L;
		if (type == 0) {
			companyId = id ;
		}else {
			//查询账户所属公司
			CompanyAccountDTO companyAccountDTO = accountFacade.queryCompanyAccountById(id);
			id = companyAccountDTO.getCompanyId();
		}
		String key = "AccountLock-" + companyId;
		String currentUser = jedisUtil.getString(key);

		if(StringUtils.isBlank(currentUser)) {
			return;
		}

		if(user.getLoginName().equals(currentUser)) {
			jedisUtil.del(key);
			return;
		}
		boolean role = accountFacade.checkUserByUserId(user);
		if(role) {
			jedisUtil.del(key);

		}

	}

	@Override
	public Boolean judgeAccountLock(Long id,int type,CacheUser user) {
		Long companyId = 0L;
		if (type == 0) {
			companyId = id ;
		}else {
			//查询账户所属公司
			CompanyAccountDTO companyAccountDTO = accountFacade.queryCompanyAccountById(id);
			id = companyAccountDTO.getCompanyId();
		}
		String key = "AccountLock-" + companyId;
		String currentUser = jedisUtil.getString(key);

		if(StringUtils.isBlank(currentUser)) {
			return false;
		}

		if(!user.getLoginName().equals(currentUser)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void updateAccountFlowReadStatus(List<Long> ids) {
		accountFacade.updateAccountFlowReadStatus(ids);
	}

	@Override
	public Integer unRefundAmount(Long userId, Long refundId, Long platformId) {


		return accountFacade.unRefundAmount(userId,refundId,platformId);
	}
}
