package com.egeo.components.finance.controller.api;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.common.BusinessExceptionConstant;
import com.egeo.common.LogConstant;
import com.egeo.common.LogTypeConstant;
import com.egeo.components.finance.business.AccountManage;
import com.egeo.components.finance.common.BusinessException;
import com.egeo.components.finance.dao.read.UserAccountReadDAO;
import com.egeo.components.finance.dao.write.UserAccountWriteDAO;
import com.egeo.components.finance.po.UserAccountPO;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.entity.CacheUser;
import com.egeo.log.EgeoBusinessLogCommon;
import com.egeo.log.EgeoLog;
import com.egeo.util.security.MD5Util;
import com.egeo.utils.ActiveMQUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.HostUtils;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/api/finance/account")
public class AccountAction extends BaseSpringController{
	@Resource(name = "account")
	private AccountManage accountManage;
	@Autowired
	private UserAccountReadDAO read;
	@Autowired
	private UserAccountWriteDAO write;

	@Autowired
	private JedisUtil jedisUtil;
	private static String PAY_LOCK_VALUE = "order_pay";

	/**
	 * 用户资产
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/userAssets")
	@ResponseBody
	public JsonResult<Map<String, Object>> userAssets(HttpServletRequest req){
		CacheUser userCache = this.getCacheUser();
		Long userId = userCache.getId();
		return accountManage.userAssets(userId);
	}
	@RequestMapping(value = "/refresh")
	@ResponseBody
	public JsonResult<Map<String, Object>> refresh(HttpServletRequest req){
		CacheUser userCache = this.getCacheUser();
		List<UserAccountPO> prdAll = read.queryPrdUserAccountAll();
		Map<String,UserAccountPO> prdMap = new HashMap<String,UserAccountPO>();
		List<UserAccountPO> nowAll = read.queryNowUserAccountAll();
		Map<String,UserAccountPO> nowMap = new HashMap<String,UserAccountPO>();
		//1.首先要prd的数据在now里面都有
		for(UserAccountPO ua: prdAll) {
			if(prdMap.containsKey(ua.getUuid())) {
				logger.info(ua.getId()+" prd is exist 1");
				continue;
			}else {
				prdMap.put(ua.getUuid(), ua);
			}

		}
		for(UserAccountPO ua: nowAll) {
			if(nowMap.containsKey(ua.getUuid())) {
				logger.info(ua.getId()+" now is exist 2");
				continue;
			}else {
				nowMap.put(ua.getUuid(), ua);
			}

		}

		for(UserAccountPO ua: prdAll) {
			if(!nowMap.containsKey(ua.getUuid())) {
				logger.info(ua.getId()+" now is not exist 3");
			}
			UserAccountPO nua = nowMap.get(ua.getUuid());

			if(nua.getBalance().compareTo(ua.getBalance())==0) {
				UserAccountPO po = new UserAccountPO();
				po.setId(nua.getId());
				if(nua.getCiphertext().equalsIgnoreCase("7a0afa28430d1e35376ee56acb27eb98")) {
					po.setCiphertext(ua.getCiphertext());
					write.update(po);
					logger.info(ua.getUuid()+"refresh Ciphertext");
				}else if(!nua.getCiphertext().equalsIgnoreCase(ua.getCiphertext())) {
					logger.info(ua.getUuid()+" Ciphertext Error");
				}
			}else{
				if(nua.getUserId().longValue()!=ua.getUserId().longValue()) {
					logger.info(ua.getUuid()+"refresh2 userIdError");
					continue;
				}
				if(nua.getType().intValue()!=ua.getType().intValue()) {
					logger.info(ua.getUuid()+"refresh2 TypeError");
					continue;
				}
				UserAccountPO po = new UserAccountPO();
				po.setId(nua.getId());
				po.setCiphertext(ua.getCiphertext());
				po.setBalance(ua.getBalance());
				write.update(po);
				logger.info(ua.getUuid()+"refresh2 Ciphertext");
			}
		}


		return accountManage.userAssets(1l);
	}

	/**
	 * 用户账户流水分页列表
	 * @param accountId
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/accountFlowPage")
	@ResponseBody
	public JsonResult<Map<String, Object>> accountFlowPage(
			Long accountId,Integer mode,Integer pageNo,
			Integer pageSize,HttpServletRequest req){
		CacheUser userCache = this.getCacheUser();
		Long userId = userCache.getId();
		return accountManage.userAccountFlowPage(accountId, mode, pageNo, pageSize,
				userId);
	}

	/**
	 * 冻结或扣除用户积分账户
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/foscoinAccountDeduct")
	@ResponseBody
	public JsonResult<Map<String,Object>> foscoinAccountDeductWithTx(String orderCode,String paymentPassword,Integer payType,String realName,String idCardNo,String cardIds,HttpServletRequest req) throws Exception{
		logger.info("订单号:{}发起积分支付,支付类型:{}",orderCode,payType);
		//校验锁是否存在
		String clientId = req.getHeader("clientId");
		String keyValue = jedisUtil.getString(JedisUtil.PAY_LOCK_KEY_PRE + orderCode);
		if(EmptyUtil.isNotEmpty(keyValue)&&!keyValue.equals(clientId)){
			logger.info("该订单已在支付流程中，请稍后再试,code="+BusinessExceptionConstant.PAY_LOCK_REMOVED);
			return fail(BusinessExceptionConstant.PAY_LOCK_REMOVED,"该订单已在支付流程中，请稍后再试");
		}
		if(payType !=null && (payType.intValue() ==6 || payType.intValue()==7)){
			if(EmptyUtil.isEmpty(cardIds)){
				return fail("缺失卡劵id");
			}
		}


		/*boolean b = false;
		try {
			b = jedisUtil.lockWithParam(JedisUtil.PAY_LOCK_KEY_PRE + orderCode, PAY_LOCK_VALUE, JedisUtil.ORDER_PAY_LOCK_EXPIRE_TIME);
		} catch (InterruptedException e) {
			logger.info("获取锁异常");
			jedisUtil.delLock(JedisUtil.PAY_LOCK_KEY_PRE+orderCode);
			e.printStackTrace();
		}
		if(!b){
			return fail("支付不可执行,当前订单已存在支付行为");
		}*/
		CacheUser userCache = this.getCacheUser();
		String str = req.getHeader("platformId");
    	Long platformId = null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		//用户id
		Long userId=userCache.getId();
		Long companyId = userCache.getCompanyId();
		String userName = userCache.getName();
		String ip=HostUtils.getClientIP(req);
		//根据ip获取mac地址
		String mac;
		try {
			mac = HostUtils.getLocalMac(ip);
		} catch (Exception e) {
			throw new BusinessException("获取mac地址异常" + e.getMessage());
		}

		// 记录订单积分支付成功日志
		SoDTO oldSoDTO = accountManage.querySoByOrderCode(orderCode);

		Integer map =null;
		try {

			String accountKey = jedisUtil.getString(JedisUtil.ACCOUNT_DISABLED_LOCK_KEY_PRE + companyId);
			if(EmptyUtil.isNotEmpty(accountKey)){
				logger.info("正在进行企业账户失效操作，请稍后再试,code="+BusinessExceptionConstant.ACCOUNT_LOCKED);
				return fail(BusinessExceptionConstant.ACCOUNT_LOCKED,"您的积分账户已冻结，请使用现金支付");
			}

			map=accountManage.foscoinAccountDeductWithTx(userId,orderCode,platformId,paymentPassword,userName,ip,mac,companyId,payType, realName,idCardNo,cardIds, req);
		}catch (Exception e){
			e.printStackTrace();
			logger.info("积分支付出现异常:{}",e);
			//释放锁
		/*	if(e instanceof BusinessException){
				BusinessException businessException = (BusinessException) e;
				//支付密码错误不解锁
				if(BusinessExceptionConstant.PAYMENTPASSWORD_NO_MATCH!=businessException.getCode()){*/
					jedisUtil.delLock(JedisUtil.PAY_LOCK_KEY_PRE+orderCode);
					logger.info("积分支付异常,订单解锁成功,orderCode="+orderCode);
		/*}	*/
			throw e;


		}

			if (Integer.valueOf(0).equals(map)) {
				//仅需积分支付且已经支付成功,进行解锁行为
				jedisUtil.delLock(JedisUtil.PAY_LOCK_KEY_PRE+orderCode);
				logger.info("订单解锁成功,orderCode="+orderCode);
				// map为0表示积分支付成功
				SoDTO newSoDTO = accountManage.querySoByOrderCode(orderCode);
				EgeoLog log = new EgeoLog();
				log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
				log.setOperObject("AccountAction_foscoinAccountDeduct");
				log.setMsgId(LogConstant.ORDER_PAY_SUCCESS.getStatus());
				log.setType(LogTypeConstant.SO.getStatus());
				log.setOperatorObjId(newSoDTO.getId());
				log.setOperatorObjCode(newSoDTO.getOrderCode());
				log.setNewObj(newSoDTO);
				log.setOldObj(oldSoDTO);

				EgeoBusinessLogCommon.fillLogValue(log, req);
				ActiveMQUtils.recordBusinessLog(log);
			}else if(Integer.valueOf(1).equals(map)){
				//还需要现金支付
				//校验锁是否还有效存在
				String clientId2 = req.getHeader("clientId");
				String keyValue2 = jedisUtil.getString(JedisUtil.PAY_LOCK_KEY_PRE + orderCode);
				if(EmptyUtil.isNotEmpty(keyValue2)&&!keyValue2.equals(clientId2)){
					logger.info("该订单已在支付流程中，请稍后再试,code="+BusinessExceptionConstant.PAY_LOCK_REMOVED);
					return fail(BusinessExceptionConstant.PAY_LOCK_REMOVED,"该订单已在支付流程中，请稍后再试");
				}

			}
			Map<String, Object> result = new HashMap<>();
			result.put("payStatus",map);
			result.put("orderType",oldSoDTO.getSaleWay());
			logger.info("[结果返回]payStatus="+map);
			logger.info("[结果返回]orderType="+oldSoDTO.getSaleWay());
		logger.info("[结果返回]订单号:{}发起积分支付,支付类型:{},正常结束",orderCode,payType);
			return success(result);

	}

	/**
	 * 根据订单编号解冻订单积分
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/unfreezeSoFubi")
	@ResponseBody
	public JsonResult<Integer> unfreezeSoFubi(String orderCode,HttpServletRequest req){
		CacheUser userCache = this.getCacheUser();
		String str = req.getHeader("platformId");
    	Long platformId = null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		//用户id
		Long userId=userCache.getId();
		Integer map = accountManage.unfreezeSoFubi(userId,orderCode,platformId);
		return success(map);
	}

	/**
	 * 根据订单编号解冻订单积分
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/unfreezeSoChildFubi")
	@ResponseBody
	public JsonResult<Integer> unfreezeSoChildFubi(String orderCode,HttpServletRequest req){
		CacheUser userCache = this.getCacheUser();
		String str = req.getHeader("platformId");
		Long platformId = null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		//用户id
		Long userId=userCache.getId();
		Integer map = accountManage.unfreezeSoChildFubi(userId,orderCode,platformId);
		return success(map);
	}

	@RequestMapping(value = "/checkBalance")
	@ResponseBody
	public JsonResult<Integer> checkBalance(String month,HttpServletRequest req){
		CacheUser userCache = this.getCacheUser();
		String str = req.getHeader("platformId");
    	Long platformId = null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		//用户id
		Long userId=userCache.getId();
		Integer map = accountManage.checkBalance(userId,month,platformId);
		return success(map);
	}
	@RequestMapping(value = "/updateAccountFlowReadStatus")
	@ResponseBody
	public JsonResult<String> updateAccountFlowReadStatus(String ids,HttpServletRequest req){
		logger.info("ids:" + ids);
		if (EmptyUtil.isNotEmpty(ids)) {
			List<Long> idList = JSONArray.parseArray(ids, Long.class);
			logger.info("idList:" + idList);
			logger.info("idList:" + idList.size());
			logger.info("idList:" + idList.get(0));
			accountManage.updateAccountFlowReadStatus(idList);
		}
		return success("");
	}

	public static void main(String[] args) {
		BigDecimal d=new BigDecimal("123.33");
		System.out.println(d.toString());
		System.out.println(String.valueOf(d));
		String salt="@$asf123";
		System.out.println(MD5Util.MD5Salt(d.toString(),  salt));
		System.out.println(MD5Util.MD5Salt(String.valueOf(d),  salt));
	}

	/**
	 * 根据退款记录清除已退餐卡金额
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/unRefundAmount")
	@ResponseBody
	public JsonResult<Integer> unRefundAmount(Long refundId,HttpServletRequest req){
		CacheUser userCache = this.getCacheUser();
		String str = req.getHeader("platformId");
		Long platformId = null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		//用户id
		Long userId=userCache.getId();
		try {
			Integer map = accountManage.unRefundAmount(userId,refundId,platformId);
			return success(map);
		}catch (Exception e){
			e.printStackTrace();
			return JsonResult.fail("确认退款失败"+e.getMessage());
		}

	}
}
