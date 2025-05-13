package com.egeo.components.finance.controller.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.egeo.components.finance.bean.AccountFlowDetailVO;
import com.egeo.components.finance.bean.DetailEnterpriseVO;
import com.egeo.components.finance.bean.ReconciliationVO;
import com.egeo.components.finance.business.AccountManage;
import com.egeo.components.finance.common.BusinessException;
import com.egeo.components.finance.converter.AccountFlowConverter;
import com.egeo.components.finance.dto.AccountFlowDTO;
import com.egeo.components.finance.service.read.AccountFlowReadService;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.utils.DateUtil;
import com.egeo.config.RuntimeContext;
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
@RequestMapping("/api/finance/reconciliation")
public class ReconciliationAction extends BaseSpringController{
	@Resource(name = "account")
	private AccountManage accountManage;
	@Autowired
	private AccountFlowReadService flowService;
	
	@Autowired
	private CompanyClient companyClient;
	
	@Autowired
	private JedisUtil jedisUtil;
	private static String PAY_LOCK_VALUE = "order_pay";

	/**
	 * 用户资产
	 * @param req
	 * @return
	 */
	public ReconciliationVO reconciliationOneEnterprise(Long id, Date start){
		ReconciliationVO vo = new ReconciliationVO();
		Calendar rightNow = Calendar.getInstance();  
        rightNow.setTime(start);  
        rightNow.add(Calendar.MONTH, 1);  
        Date end = rightNow.getTime();    
		
		List<AccountFlowDTO> flows = flowService.findAccountFlowOfEnterprise(id, start,end);
		BigDecimal ledgerEnterprise = new BigDecimal(0);
		BigDecimal ledgerPlateform = new BigDecimal(0);
		BigDecimal chongzhi = new BigDecimal(0);
		BigDecimal tuikuan = new BigDecimal(0);
		BigDecimal refundFubi = new BigDecimal(0);
		BigDecimal refundMoney = new BigDecimal(0);
		BigDecimal refundLedgerEnterprise = new BigDecimal(0);
		BigDecimal refundLedgerPlateform = new BigDecimal(0);
		BigDecimal buyFubi = new BigDecimal(0);
		BigDecimal buyMoney = new BigDecimal(0);
		BigDecimal enterpriseProductSell = new BigDecimal(0);
		HashMap<String,Long> buyOrderCode = new HashMap<String,Long>();
		HashMap<String,Long> refundOrderCode = new HashMap<String,Long>();
		for(AccountFlowDTO flow : flows) {
			if(flow.getType()==3 ||flow.getType()==4) {
				if(flow.getOrderCode()!=null && flow.getOrderId()!=null) {
					if(flow.getType()==3) {
						buyMoney = buyMoney.add(flow.getSum());
					}

					if(flow.getType()==4) {
						buyFubi = buyFubi.add(flow.getSum());
					}					
					//计算订单的分账，对于积分+现金 会有两笔流水，只计算一次
					if(buyOrderCode.containsKey(flow.getOrderCode())) {
						continue;
					}
					buyOrderCode.put(flow.getOrderCode(), flow.getOrderId());
					if(flow.getLedgerEnterprise()!=null && flow.getLedgerEnterprise().compareTo(BigDecimal.ZERO) > 0) {
						ledgerEnterprise = ledgerEnterprise.add(flow.getLedgerEnterprise());
					}
					if(flow.getLedgerPlateform()!=null && flow.getLedgerPlateform().compareTo(BigDecimal.ZERO) > 0) {
						ledgerPlateform = ledgerPlateform.add(flow.getLedgerPlateform());
					}
				}
				
			}else if(flow.getType()==5 ||flow.getType()==6) {
				if(flow.getOrderCode()!=null && flow.getOrderId()!=null) {
					if(flow.getType()==5) {
						refundMoney = refundMoney.add(flow.getSum());
					}

					if(flow.getType()==6) {
						refundFubi = refundFubi.add(flow.getSum());
					}					
					//计算订单的分账，对于积分+现金 会有两笔流水，只计算一次
					if(refundOrderCode.containsKey(flow.getOrderCode())) {
						continue;
					}
					refundOrderCode.put(flow.getOrderCode(), flow.getOrderId());
					if(flow.getLedgerEnterprise()!=null && flow.getLedgerEnterprise().compareTo(BigDecimal.ZERO) > 0) {
						refundLedgerEnterprise = refundLedgerEnterprise.add(flow.getLedgerEnterprise());
					}
					if(flow.getLedgerPlateform()!=null && flow.getLedgerPlateform().compareTo(BigDecimal.ZERO) > 0) {
						refundLedgerPlateform = refundLedgerPlateform.add(flow.getLedgerPlateform());
					}
				}
				
			}else if(flow.getType()==7 ) {
				chongzhi = chongzhi.add(flow.getSum());
			}
		}
		vo.setLedgerEnterprise(ledgerEnterprise.subtract(refundLedgerEnterprise).toEngineeringString());
		vo.setLedgerPlateform(ledgerPlateform.subtract(refundLedgerPlateform).toEngineeringString());
		vo.setChongzhi(chongzhi.toEngineeringString());
		vo.setBuyFubi(buyFubi.toEngineeringString());
		vo.setBuyMoney(buyMoney.toEngineeringString());
		vo.setRefund(refundFubi.add(refundMoney).toEngineeringString());
		vo.setEnterpriseProductSell(enterpriseProductSell.toEngineeringString());
		return vo;
	}
	public ReconciliationVO reconciliationOneCompany(Long id, Date start){
		ReconciliationVO vo = new ReconciliationVO();
		Calendar rightNow = Calendar.getInstance();  
        rightNow.setTime(start);  
        rightNow.add(Calendar.MONTH, 1);  
        Date end = rightNow.getTime();    
		
		List<AccountFlowDTO> flows = flowService.findAccountFlowOfCompany(id, start,end);
		BigDecimal ledgerEnterprise = new BigDecimal(0);
		BigDecimal ledgerPlateform = new BigDecimal(0);
		BigDecimal chongzhi = new BigDecimal(0);
		BigDecimal tuikuan = new BigDecimal(0);
		BigDecimal refundFubi = new BigDecimal(0);
		BigDecimal refundMoney = new BigDecimal(0);
		BigDecimal refundLedgerEnterprise = new BigDecimal(0);
		BigDecimal refundLedgerPlateform = new BigDecimal(0);
		BigDecimal buyFubi = new BigDecimal(0);
		BigDecimal buyMoney = new BigDecimal(0);
		BigDecimal enterpriseProductSell = new BigDecimal(0);
		HashMap<String,Long> buyOrderCode = new HashMap<String,Long>();
		HashMap<String,Long> refundOrderCode = new HashMap<String,Long>();
		for(AccountFlowDTO flow : flows) {
			if(flow.getType()==3 ||flow.getType()==4) {
				if(flow.getOrderCode()!=null && flow.getOrderId()!=null) {
					if(flow.getType()==3) {
						buyMoney = buyMoney.add(flow.getSum());
					}

					if(flow.getType()==4) {
						buyFubi = buyFubi.add(flow.getSum());
					}					
					//计算订单的分账，对于积分+现金 会有两笔流水，只计算一次
					if(buyOrderCode.containsKey(flow.getOrderCode())) {
						continue;
					}
					buyOrderCode.put(flow.getOrderCode(), flow.getOrderId());
					if(flow.getLedgerEnterprise()!=null && flow.getLedgerEnterprise().compareTo(BigDecimal.ZERO) > 0) {
						ledgerEnterprise = ledgerEnterprise.add(flow.getLedgerEnterprise());
					}
					if(flow.getLedgerPlateform()!=null && flow.getLedgerPlateform().compareTo(BigDecimal.ZERO) > 0) {
						ledgerPlateform = ledgerPlateform.add(flow.getLedgerPlateform());
					}
				}
				
			}else if(flow.getType()==5 ||flow.getType()==6) {
				if(flow.getOrderCode()!=null && flow.getOrderId()!=null) {
					if(flow.getType()==5) {
						refundMoney = refundMoney.add(flow.getSum());
					}

					if(flow.getType()==6) {
						refundFubi = refundFubi.add(flow.getSum());
					}					
					//计算订单的分账，对于积分+现金 会有两笔流水，只计算一次
					if(refundOrderCode.containsKey(flow.getOrderCode())) {
						continue;
					}
					refundOrderCode.put(flow.getOrderCode(), flow.getOrderId());
					if(flow.getLedgerEnterprise()!=null && flow.getLedgerEnterprise().compareTo(BigDecimal.ZERO) > 0) {
						refundLedgerEnterprise = refundLedgerEnterprise.add(flow.getLedgerEnterprise());
					}
					if(flow.getLedgerPlateform()!=null && flow.getLedgerPlateform().compareTo(BigDecimal.ZERO) > 0) {
						refundLedgerPlateform = refundLedgerPlateform.add(flow.getLedgerPlateform());
					}
				}
				
			}else if(flow.getType()==7 ) {
				chongzhi = chongzhi.add(flow.getSum());
			}
		}
		vo.setLedgerEnterprise(ledgerEnterprise.subtract(refundLedgerEnterprise).toEngineeringString());
		vo.setLedgerPlateform(ledgerPlateform.subtract(refundLedgerPlateform).toEngineeringString());
		vo.setChongzhi(chongzhi.toEngineeringString());
		vo.setBuyFubi(buyFubi.toEngineeringString());
		vo.setBuyMoney(buyMoney.toEngineeringString());
		vo.setRefund(refundFubi.add(refundMoney).toEngineeringString());
		vo.setEnterpriseProductSell(enterpriseProductSell.toEngineeringString());
		return vo;
	}
	@RequestMapping(value = "/enterprise")
	@ResponseBody
	public JsonResult<List<ReconciliationVO>> reconciliation(Long id, String month,String endMonth){
		List<ReconciliationVO> rslt = new ArrayList<ReconciliationVO>();

		Date start =DateUtil.parseDate(DateUtil.YearMonthDay, month);
		Date monthDate = start;
		
		Date end =DateUtil.parseDate(DateUtil.YearMonthDay, endMonth);
		if(start.compareTo(end)>0) {
			return JsonResult.success(rslt);
		}
		
		boolean add = false;
		while(monthDate.compareTo(end)<=0) {
			if(add) {
				Calendar rightNow = Calendar.getInstance();  
		        rightNow.setTime(monthDate);  
		        rightNow.add(Calendar.MONTH, 1);   
		        monthDate = rightNow.getTime();
		        if(monthDate.compareTo(end)>0) {
		        	continue;
		        }
			}
			add = true;
	        ReconciliationVO vo = reconciliationOneEnterprise(id, monthDate);
	        vo.setMonth(DateUtil.formatDate(DateUtil.YearMonth, monthDate));
			rslt.add(vo);
		}
		
		return JsonResult.success(rslt);
	}

	@RequestMapping(value = "/enterpriseSelf")
	@ResponseBody
	public JsonResult<List<ReconciliationVO>> reconciliation(String month,String endMonth){
		CacheUser user = RuntimeContext.cacheUser();
		List<ReconciliationVO> rslt = new ArrayList<ReconciliationVO>();
		if(user.getType()==null || user.getType()!=2) {
			return JsonResult.success(rslt);
		}
		Date start =DateUtil.parseDate(DateUtil.YearMonthDay, month);
		Date monthDate = start;
		
		Date end =DateUtil.parseDate(DateUtil.YearMonthDay, endMonth);
		if(start.compareTo(end)>0) {
			return JsonResult.success(rslt);
		}
		
		boolean add = false;
		while(monthDate.compareTo(end)<=0) {
			if(add) {
				Calendar rightNow = Calendar.getInstance();  
		        rightNow.setTime(monthDate);  
		        rightNow.add(Calendar.MONTH, 1);   
		        monthDate = rightNow.getTime();
		        if(monthDate.compareTo(end)>0) {
		        	continue;
		        }
			}
			add = true;
	        ReconciliationVO vo = reconciliationOneEnterprise(user.getEnterpriseId(), monthDate);
	        vo.setMonth(DateUtil.formatDate(DateUtil.YearMonth, monthDate));
			rslt.add(vo);
		}
		
		return JsonResult.success(rslt);
	}
	/**
	 * 用户账户流水分页列表
	 * @param accountId
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/detail")
	@ResponseBody
	public JsonResult<List<AccountFlowDetailVO>> accountFlowPage(
			Long id,String month,HttpServletRequest req){

		Date start =DateUtil.parseDate(DateUtil.YearMonth, month);
		Calendar rightNow = Calendar.getInstance();  
        rightNow.setTime(start);  
        rightNow.add(Calendar.MONTH, 1);  
        Date end = rightNow.getTime();   
		List<AccountFlowDTO> flows = flowService.findAccountFlowOfEnterprise(id, start,end);
		List<AccountFlowDetailVO> rslt = new ArrayList<AccountFlowDetailVO>();
		for(AccountFlowDTO flow : flows) {
			AccountFlowDetailVO vo = AccountFlowConverter.toDetailVO(flow);	
			rslt.add(vo);
		}
		
		
		return JsonResult.success(rslt);
	}
	@RequestMapping(value = "/detailEnterprise")
	@ResponseBody
	public JsonResult<DetailEnterpriseVO> detailSelf(
			String month,HttpServletRequest req){
		CacheUser user = RuntimeContext.cacheUser();
		if(user.getType()==null || user.getType()!=2) {
			return JsonResult.success(new DetailEnterpriseVO());
		}
		Date monthDate =DateUtil.parseDate(DateUtil.YearMonth, month);
		ReconciliationVO enterpriseMonthData = reconciliationOneEnterprise(user.getEnterpriseId(), monthDate);
		DetailEnterpriseVO vo = new DetailEnterpriseVO();
		enterpriseMonthData.setMonth(month);
		vo.setEnterprise(enterpriseMonthData);
		List<CompanyDTO> companys = companyClient.findCompanyByEnterpriseId(user.getEnterpriseId());
		List<ReconciliationVO> companyVos = new ArrayList<ReconciliationVO>();
		for(CompanyDTO company : companys) {
			ReconciliationVO companyVo = reconciliationOneCompany(company.getId(), monthDate);
			companyVos.add(companyVo);
			companyVo.setMonth(company.getCompanyName());
		}
		vo.setCompanys(companyVos);
		
		return JsonResult.success(vo);
	}

	@RequestMapping(value = "/detailCompany")
	@ResponseBody
	public JsonResult<List<AccountFlowDetailVO>> detailCompany(
			Long id,String month){
		CacheUser user = RuntimeContext.cacheUser();
		List<AccountFlowDetailVO> rslt = new ArrayList<AccountFlowDetailVO>();
		if(user.getType()==null || user.getType()!=2) {
			return JsonResult.success(rslt);
		}
		Date start =DateUtil.parseDate(DateUtil.YearMonth, month);		
		
		Calendar rightNow = Calendar.getInstance();  
        rightNow.setTime(start);  
        rightNow.add(Calendar.MONTH, 1);  
        Date end = rightNow.getTime();   
		List<AccountFlowDTO> flows = flowService.findAccountFlowOfCompany(user.getEnterpriseId(), start,end);
		for(AccountFlowDTO flow : flows) {
			AccountFlowDetailVO vo = AccountFlowConverter.toDetailVO(flow);	
			rslt.add(vo);
		}
		
		
		return JsonResult.success(rslt);
	}
}
