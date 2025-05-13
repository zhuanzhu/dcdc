package com.egeo.components.finance.controller.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.egeo.common.BusinessExceptionConstant;
import com.egeo.components.finance.business.AccountManage;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.utils.JsonUtils;
import com.egeo.config.RuntimeContext;
import com.egeo.entity.CacheUser;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.excel2.ExcelUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/api/finance/accountBackStage")
public class AccountBackStageAction extends BaseSpringController {

	@Resource(name = "account")
	private AccountManage accountManage;
	@Autowired
	private CompanyClient companyClient;

	@Resource
	private JedisUtil jedisUtil;
	/**
	 * 公司账户分页列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param accountName
	 * @param companyId
	 * @param disabled
	 * @return
	 */
	@RequestMapping(value = "/companyAccountPage")
	@ResponseBody
	public JsonResult<Map<String, Object>> companyAccountPage(Integer pageNo, Integer pageSize, String accountName,
			Long companyId, Integer disabled, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str))
			return fail("platformId为空");
		Long platformId = Long.valueOf(str);
		List<Long> companyIds = new ArrayList<Long>();
		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null) {
			if(RuntimeContext.cacheUser().getType().intValue()==3 || RuntimeContext.cacheUser().getType().intValue()==4) {
				companyId = RuntimeContext.cacheUser().getCompanyId();
				companyIds.add(companyId);
			}else if(RuntimeContext.cacheUser().getType().intValue()==2) {
				//获取所有的公司信息
				List<CompanyDTO> companys = companyClient.findCompanyByEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
				if(companyId!=null) {
					for(CompanyDTO one : companys) {
						if(one.getId().equals(companyId)) {
							companyIds.add(companyId);
						}
					}
				}else {
					for(CompanyDTO one : companys) {
						companyIds.add(one.getId());
					}
				}
				if(companyIds==null || companyIds.size()==0) {
					companyIds = null;
				}
			}else if(RuntimeContext.cacheUser().getType().intValue()==1) {
				if(companyId!=null) {
					companyIds.add(companyId);
				}else {
					companyIds = null;
				}
			}else {
				return JsonResult.fail("获取数据为空 403");
			}

			return accountManage.companyAccountPage(pageNo, pageSize, accountName, companyIds, disabled, platformId);
		}
		
		return JsonResult.fail("获取数据为空 403");
		
	}

	/**
	 * 获取账户操作原因列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/accountOperateReasons")
	@ResponseBody
	public JsonResult<Map<String, Object>> accountOperateReasons(Long companyId) {
		return accountManage.accountOperateReasons(companyId);
	}
	
	/**
	 * 获取指定类型的账户操作原因列表
	 * @param types 形如1,2,3的原因类型数组
	 * @return
	 */
	@RequestMapping(value = "/certainTypeReasons")
	@ResponseBody
	public JsonResult<Map<String, Object>> certainTypeReasons(String types, Long accountId,Long companyId,HttpServletRequest req){
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str))
			return fail("platformId为空");
		Long platformId = Long.valueOf(str);
		return accountManage.certainTypeReasons(platformId,types, accountId,companyId);
	}

	/**
	 * 普通公司账户列表 下拉列表框使用
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/normalAccounts")
	@ResponseBody
	public JsonResult<Map<String, Object>> normalAccounts(HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str))
			return fail("platformId为空");
		Long platformId = Long.valueOf(str);
		
		List<Long> companyIds = new ArrayList<Long>();
		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null) {
			if(RuntimeContext.cacheUser().getType().intValue()==3 || RuntimeContext.cacheUser().getType().intValue()==4) {
				companyIds.add(RuntimeContext.cacheUser().getCompanyId());
			}else if(RuntimeContext.cacheUser().getType().intValue()==2) {
				//获取所有的公司信息
				List<CompanyDTO> companys = companyClient.findCompanyByEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
				for(CompanyDTO one : companys) {
					companyIds.add(one.getId());
				}
				if(companyIds==null || companyIds.size()==0) {
					companyIds.add(-99l);
				}
			}else if(RuntimeContext.cacheUser().getType().intValue()==1) {
				companyIds = null;
			}else {
				return JsonResult.fail("获取数据为空 403");
			}

			return accountManage.normalAccounts(platformId,companyIds);
		}
		
		return JsonResult.fail("获取数据为空 403");
		
		
		
	}

	/**
	 * 账户充值/调整提交审核
	 * 
	 * @param accountId
	 * @param sum
	 * @param reasonId
	 * @param remark
	 *
	 * @param finBatch
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/raSubmitForExam")
	@ResponseBody
	public JsonResult<Map<String, Object>> raSubmitForExam(Long accountId, Double sum, Long reasonId, String remark,
			String finBatch, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str))
			return fail("platformId为空");
		Long platformId = Long.valueOf(str);
		CacheUser userCache = this.getCacheUser();
		Long userId = userCache.getId();

		return accountManage.raSubmitForExam(accountId, sum, reasonId, remark, finBatch, platformId, userId);
	}

	/**
	 * 查询账户详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/companyAccountDetail")
	@ResponseBody
	public JsonResult<Map<String, Object>> companyAccountDetail(Long id) {
		return accountManage.companyAccountDetail(id);
	}

	/**
	 * 改变公司账户有效性
	 * 
	 * @param id  公司账户id
	 * @param disabled
	 * @return
	 */
	@RequestMapping(value = "/changeAccountDisable")
	@ResponseBody
	public JsonResult<Map<String, Object>> changeAccountDisable(
			Long id, Integer disabled, HttpServletRequest req) {
		CacheUser userCache = this.getCacheUser();
		Long userId = userCache.getId();
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str))
			return fail("platformId为空");
		Long platformId = Long.valueOf(str);

		return accountManage.changeAccountDisable(id, disabled,userId,platformId);
	}

	/**
	 * 恢复企业和员工账户金额
	 * @param id
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/recoverAccountBalance")
	@ResponseBody
	public JsonResult<Map<String, Object>> recoverAccountBalance(
			Long id, Integer recover, HttpServletRequest req) {
		CacheUser userCache = this.getCacheUser();
		Long userId = userCache.getId();
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str))
			return fail("platformId为空");
		Long platformId = Long.valueOf(str);
		return accountManage.recoverAccountBalance(id,recover,userId,platformId);
	}

	/**
	 * 账户批次分页列表
	 * 
	 * @param batchNo
	 * @param reasonId
	 * @param status
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/accountBatchPage")
	@ResponseBody
	public JsonResult<Map<String, Object>> accountBatchPage(Long accountId, String batchNo, Integer type,
			Integer pageNo, Integer pageSize, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str))
			return fail("platformId为空");
		Long platformId = Long.valueOf(str);
		return accountManage.accountBatchPage(accountId, batchNo, type, pageNo, pageSize, platformId);
	}

	/**
	 * 待审核账户充值与金额调整记录分页列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param keyWord
	 * @param companyuId
	 * @param status
	 * @param reasonId
	 * @param req
	 * @return
	 */

	@RequestMapping(value = "/batchTmpPage")
	@ResponseBody
	public JsonResult<Map<String, Object>> batchTmpPage(Integer pageNo, Integer pageSize, String keyWord,
			Long companyId, Integer status, Integer type, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str))
			return fail("platformId为空");
		Long platformId = Long.valueOf(str);
		return accountManage.batchTmpPage(pageNo, pageSize, keyWord, companyId, status, type, platformId);
	}

	/**
	 * 全部批次分页列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param tableType
	 *            查询表类型 0:草稿 1:正式
	 * @param batchNo
	 * @param type
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/fullBatchPage")
	@ResponseBody
	public JsonResult<Map<String, Object>> fullBatchPage(Integer pageNo, Integer pageSize, Integer tableType,
			String batchNo, Integer type,Integer status, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str))
			return fail("platformId为空");
		Long platformId = Long.valueOf(str);
		return accountManage.fullBatchPage(pageNo, pageSize, tableType, batchNo, type,status, platformId);
	}

	/**
	 * 批次审核通过/不通过
	 * 
	 * @param id
	 * @param option
	 *            0:通过 1:不通过
	 * @param reason
	 *            不通过给出原因
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/batchExam")
	@ResponseBody
	public JsonResult<Map<String, Object>> batchExam(Long id, Integer option, String reason, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str))
			return fail("platformId为空");
		Long platformId = Long.valueOf(str);
		return accountManage.batchExam(id, option, reason, platformId);
	}
	
	/**
	 * 查询批次审核通过/不通过的原因
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/batchExamReason")
	@ResponseBody
	public JsonResult<Map<String, Object>> batchExamReason(Long id) {
		
		return accountManage.batchExamReason(id);
	}


	/**
	 * 批次流水分页列表
	 * 
	 * @param batchId
	 * @param outflowAccount
	 * @param inflowAccount
	 * @param startTime
	 * @param endTime
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "batchFlowPage")
	@ResponseBody
	public JsonResult<Map<String, Object>> batchFlowPage(
			Long batchId, String outflowAccount, String inflowAccount,
			Long startTime, Long endTime, Integer pageNo, Integer pageSize, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str))
			return fail("platformId为空");
		Long platformId = Long.valueOf(str);
		return accountManage.batchFlowPage(batchId, outflowAccount, inflowAccount, startTime, endTime, pageNo, pageSize,
				platformId);
	}

	/**
	 * 全部流水分页列表
	 * 
	 * @param startTime
	 * @param endTime
	 * @param outflowAccount
	 * @param inflowAccount
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "flowPage")
	@ResponseBody
	public JsonResult<Map<String, Object>> flowPage(
			Long startTime, Long endTime, String outflowAccount,
			String inflowAccount, Integer pageNo, Integer pageSize, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str))
			return fail("platformId为空");
		Long platformId = Long.valueOf(str);
		return accountManage.flowPage(startTime, endTime, outflowAccount, inflowAccount, pageNo, pageSize, platformId);
	}

	/**
	 * 员工账户分页列表
	 * 
	 * @param name
	 * @param email
	 * @param companyId
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "userAccountPage")
	@ResponseBody
	public JsonResult<Map<String, Object>> userAccountPage(String name, String mail, Long companyId, Integer pageNo,
			Integer pageSize, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str))
			return fail("platformId为空");
		Long platformId = Long.valueOf(str);
		Long userCompanyId = companyId;
		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null) {
			if(RuntimeContext.cacheUser().getType().intValue()==3 || RuntimeContext.cacheUser().getType().intValue()==4) {
				companyId = RuntimeContext.cacheUser().getCompanyId();
				userCompanyId = companyId;
			}
		}
		/*CacheUser cacheUser = getCacheUser();
		*/
		logger.info("当前登录人companyId:"+companyId);
		return accountManage.userAccountPage(name, mail, companyId, pageNo, pageSize, platformId,userCompanyId);
	}
	
	/**
	 * 员工账户流水分页列表
	 * @param accountId 账户id
	 * @return
	 */
	@RequestMapping(value = "userFinFlowPage")
	@ResponseBody
	public JsonResult<Map<String, Object>> userFinFlowPage(
			Long userId,Integer accountType,
			Integer pageNo,Integer pageSize){
		return accountManage.userFinFlowPage(userId,accountType,pageNo,pageSize);
	}

	/**
	 * 员工账户充值导入
	 * 
	 * @param totalAmount
	 * @param companyId
	 * @param accountType
	 *            0:积分账户 1:点赞福豆账户
	 * @param reasonId
	 * @param remark
	 * @param finBatch
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "userAccountRecharge")
	@ResponseBody
	public JsonResult<Map<String, Object>> userAccountRecharge(String totalAmount, Long companyId, Integer accountType,
			Long reasonId, String remark, String finBatch, HttpServletRequest req) {
		//判断是否存在账户锁
		String accountKey = jedisUtil.getString(JedisUtil.ACCOUNT_DISABLED_LOCK_KEY_PRE + companyId);
		if(EmptyUtil.isNotEmpty(accountKey)){
			logger.info("正在进行企业账户失效操作，请稍后再试,code="+BusinessExceptionConstant.ACCOUNT_LOCKED);
			return fail(BusinessExceptionConstant.ACCOUNT_LOCKED,"企业账户已冻结，无法进行员工账户充值");
		}
		// 请求头必加enctype=multipart/form-data
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str))
			return fail("platformId为空");
		Long platformId = Long.valueOf(str);
		if (StringUtils.isBlank(totalAmount))
			return fail("请填写积分总额");
		
		Double totalAmount_;
		try{
			totalAmount_ = Double.parseDouble(totalAmount);
		}catch(Exception e){
			return fail("积分总额必须为数值型");
		}
		if (companyId == null)
			return fail("请选择公司");
		if (accountType == null)
			return fail("请选择账户类型");
		// 账户类型码校验(积分账户和点赞福豆账户)
		if (reasonId == null)
			return fail("请选择充值原因");
		if (remark != null && remark.length() > 200) {
			return fail("备注不超过200字");
		}
		// 从请求体中获取文件
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) req;
		Iterator<String> iter = multiRequest.getFileNames();

		MultipartFile file = multiRequest.getFile(iter.next());
		if (file == null)
			return fail("未发现Excel文件");
		List<Map<String, Object>> valueList = null;
		try {
			valueList = ExcelUtil.readExcelData(0, 0, file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			return fail("Excel文件读取发生异常");
		}
		CacheUser user = this.getCacheUser();
		return accountManage.userAccountRecharge(totalAmount_, companyId, accountType, reasonId, remark, finBatch,
				platformId, valueList,user);
	}

	/**
	 * 确定导入
	 * 
	 * @param sn
	 *            导入文件序列号
	 * @param confirm 0:跳过检查 1:需要检查确认
	 * @return
	 */
	@RequestMapping(value = "impConfirm")
	@ResponseBody
	public JsonResult<Map<String, Object>> impConfirm(String sn, Integer confirm, HttpServletRequest req) {
		CacheUser userCache = this.getCacheUser();
		Long userId = userCache.getId();
		return accountManage.impConfirm(sn, confirm, userId);
	}

	/**
	 * 充值记录
	 * 
	 * @param companyId
	 * @param batchNo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "userAccountRechargeRec")
	@ResponseBody
	public JsonResult<Map<String, Object>> userAccountRechargeRec(Long companyId, String batchNo, Integer pageNo,
			Integer pageSize, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str))
			return fail("platformId为空");
		Long platformId = Long.valueOf(str);

		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null &&RuntimeContext.cacheUser().getType().intValue()==4) {
			return fail("异常");
		}
		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null &&RuntimeContext.cacheUser().getType().intValue()==3) {
			companyId = RuntimeContext.cacheUser().getCompanyId();
		}
		return accountManage.userAccountRechargeRec(companyId, batchNo, pageNo, pageSize, platformId);
	}

	/**
	 * 充值记录明细分页列表
	 * 
	 * @param batchId
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "userAccountRechargeRecDetail")
	@ResponseBody
	public JsonResult<Map<String, Object>> userAccountRechargeRecDetail(Long batchId, Integer pageNo, Integer pageSize,
			HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str))
			return fail("platformId为空");
		Long platformId = Long.valueOf(str);
		return accountManage.userAccountRechargeRecDetail(batchId, pageNo, pageSize, platformId);
	}

	/**
	 * 账户变动原因分页列表
	 * 
	 * @param type
	 * @param companyId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "reasons")
	@ResponseBody
	public JsonResult<Map<String, Object>> reasons(Integer type, Long companyId, Integer disabled, Integer pageNo,
			Integer pageSize, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str))
			return fail("platformId为空");
		Long platformId = Long.valueOf(str);
		return accountManage.reasons(type, companyId, disabled, pageNo, pageSize, platformId);
	}
	
	/**
	 * 账户变动原因详情
	 * @param reasonId
	 * @return
	 */
	@RequestMapping(value = "reasonDetail")
	@ResponseBody
	public JsonResult<Map<String, Object>> reasonDetail(Long reasonId){
		return accountManage.reasonDetail(reasonId);
	}

	/**
	 * 编辑/新建财务调整原因
	 * 
	 * @param id
	 * @param reason
	 * @param type
	 *            类型 0:企业账户充值 1:企业账户调整收入 2:企业失效 3:订单现金付款 4:订单积分付款 5:订单现金退款 6:订单积分退款
	 *            7:员工积分充值 8:员工点赞福豆充值 9:员工离职 10:员工点赞 11:企业账户调整收入
	 * @param companyIds
	 * @param disabled
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "saveReason")
	@ResponseBody
	public JsonResult<Map<String, Object>> saveReason(Long id, String reason, Integer type, String companyIds,
			Integer disabled, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str))
			return fail("platformId为空");
		Long platformId = Long.valueOf(str);
		return accountManage.saveReason(id, reason, type, companyIds, disabled, platformId);
	}
	
	/**
	 * 查询所有流水类型
	 * @return
	 */
	@RequestMapping(value = "flowTypeList")
	@ResponseBody
	public JsonResult<Map<String, Object>> flowTypeList(){
		return accountManage.flowTypeList();
	}


	/**
	 * 员工流水导出
	 */
	@RequestMapping(value = "/userFlowExportBackStage")
	@ResponseBody
	public JsonResult<Map<String, Object>> userFlowExportBackStage(String userIds,Long begin,Long end,HttpServletRequest req){
		logger.info("员工流水导出接口入口");
		logger.info("接口接受参数:" + userIds);
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str))
			return fail("platformId为空");
		Long platformId = Long.valueOf(str);
		if(EmptyUtil.isEmpty(userIds)){
			return fail("请选择员工");
		}
		Date startTime = new Date();
		Date endTime = new Date();
		if(EmptyUtil.isNotEmpty(begin)){
			startTime=new Date(begin);
		}
		if(EmptyUtil.isNotEmpty(end)){
			endTime = new Date(end);
		}
		if(startTime.compareTo(endTime)>0){
			return fail("后面的时间必须晚于前面的时间");
		}
		List<Long> userIdList = JsonUtils.jsonToList(userIds,Long.class);
		JsonResult<Map<String, Object>> result=accountManage.userFlowExportBackStage(userIdList,platformId,startTime,endTime);
		logger.info("员工流水导出接口出口");
		logger.info("返回参数状态码:"+result.getCode());
		logger.info("返回参数错误信息:"+result.getError());
		logger.info("返回参数的数据:" + result.getData());
		return result;

	}

	/**
	 * 校验当前是否有锁
	 * merchantId
	 * spuId
	 * @return
	 */
	@RequestMapping(value = "/AccountDisabledLock")
	@ResponseBody
	public JsonResult<Map<String, Object>> AccountDisabledLock(Long id,HttpServletRequest req){

		CacheUser user = this.getCacheUser();
		user.getLoginName();

		Map<String, Object> map = accountManage.AccountDisabledLock(id,user);

		return success(map);
	}

	/**
	 * 释放账户锁
	 * @return
	 */
	@RequestMapping(value = "/releaseAccountLock")
	@ResponseBody
	public JsonResult<Integer> releaseAccountLock(Long id,int type,HttpServletRequest req){

		CacheUser user = this.getCacheUser();
		user.getLoginName();
		accountManage.releaseAccountLock(id,type,user);
		return success(1);
	}

	/**
	 * 判断是否正在进行企业账户有效性操作
	 */
	@RequestMapping(value = "/judgeAccountLock")
	@ResponseBody
	public boolean judgeAccountLock(Long id,int type,HttpServletRequest req){

		CacheUser user = this.getCacheUser();
		user.getLoginName();

		boolean flag = accountManage.judgeAccountLock(id,type,user);

		return flag;
	}

}
