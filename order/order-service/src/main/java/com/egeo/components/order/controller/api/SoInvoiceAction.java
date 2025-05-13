package com.egeo.components.order.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.egeo.common.LogConstant;
import com.egeo.common.LogTypeConstant;
import com.egeo.components.order.business.SoInvoiceManage;
import com.egeo.components.order.dto.SoInvoiceDTO;
import com.egeo.entity.CacheUser;
import com.egeo.log.EgeoBusinessLogCommon;
import com.egeo.log.EgeoLog;
import com.egeo.utils.ActiveMQUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;


/**
 * 发票action
 * @author GRACIA
 *
 */
@Controller
@RequestMapping("/api/order/soInvoice")
public class SoInvoiceAction extends BaseSpringController {
	
	@Resource(name="soInvoice")
	private SoInvoiceManage soInvoiceManage;
	
	/**
	 * 根据id查询发票信息
	 * 客户端下单时调用
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/invoiceById")
	@ResponseBody
	public JsonResult<Map<String,Object>> invoiceById(Long id){
		return soInvoiceManage.invoiceById(id);
	}
	
	/**
	 * 根据母单id查询发票公共信息和子单关联发票信息
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findInvoiceBySoId")
	@ResponseBody
	public JsonResult<Map<String,Object>> findInvoiceBySoId(Long soId,HttpServletRequest req ) {
		return soInvoiceManage.findInvoiceBySoId(soId);
	}
	

	/**
	 * 后台添加/修改母订单发票信息
	 * 
	 * @param orderId
	 * @param titleType
	 * @param title
	 * @param invoiceType
	 * @param taxNo
	 * @return
	 */
	@RequestMapping(value = "/updateInvoiceInfo", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Map<String, Object>> updateInvoiceInfo(
			Long orderId, Integer titleType, String title,
			Integer invoiceType, String taxNo,
			Integer invoiceContentType,
			HttpServletRequest req) {
		logger.info("后台添加/修改母订单发票信息");
		String str = req.getHeader("platformId");
		if(EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.parseLong(str);
		
		// 新增或修改发票抬头记录日志
		SoInvoiceDTO oldInvoice = soInvoiceManage.queryMainSoInvoiceByOrderId(orderId);
		
		JsonResult<Map<String, Object>> result = soInvoiceManage.updateInvoiceInfo(orderId, titleType, title, invoiceType, taxNo,invoiceContentType, platformId);
		
		if (result.getCode() == 0) {
			SoInvoiceDTO newInvoice = EmptyUtil.isNotEmpty(oldInvoice) ? 
					soInvoiceManage.findSoInvoiceById(oldInvoice.getId()) : soInvoiceManage.queryMainSoInvoiceByOrderId(orderId);
			EgeoLog log = new EgeoLog();
			log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
			log.setOperObject("SoInvoiceAction_updateInvoiceInfo");
			log.setType(LogTypeConstant.INVOICE_INFO.getStatus());
			log.setOperatorObjId(newInvoice.getId());
			log.setOperatorObjCode(newInvoice.getInvoiceCode());
			log.setNewObj(newInvoice);
			if (EmptyUtil.isNotEmpty(oldInvoice)) {
				// 修改
				log.setMsgId(LogConstant.INVOICE_TITLE_MODIFY.getStatus());
				log.setOldObj(oldInvoice);
			} else {
				// 新增
				log.setMsgId(LogConstant.INVOICE_TITLE_NEW.getStatus());
			}
			
			EgeoBusinessLogCommon.fillLogValue(log, req);

			try {
				ActiveMQUtils.recordBusinessLog(log);
			}catch (Exception e) {
				// TODO: handle exception
				logger.error("发送日志消息失败："+ JSON.toJSONString(log));
			}
		}
		
		return result;
	}
	
	/**
	 * 根据母单id查询所有发票信息
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findAllInvoiceBySoId")
	@ResponseBody
	public JsonResult<List<SoInvoiceDTO>> findAllInvoiceBySoId(Long soId,HttpServletRequest req ) {
		
		SoInvoiceDTO dto = new SoInvoiceDTO();
		dto.setSoId(soId);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.valueOf(str);
		dto.setPlatformId(platformId);
		List<SoInvoiceDTO> rt = soInvoiceManage.findSoInvoiceAll(dto);
		return JsonResult.success(rt);
	}	

	/**
	 * 开具发票保存信息
	 * @param soChildId
	 * @param invoiceType 0:纸质发票  1:电子发票
	 * @param invoiceCode
	 * @param remark
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/drawInvoice")
	@ResponseBody
	public JsonResult<Map<String,Object>> drawInvoice(
			Long soChildId,Integer invoiceType,
			String invoiceCode,String remark, String invoiceAttcUrl,
			HttpServletRequest req ) {
		CacheUser userCache = this.getCacheUser();
		Long userId=userCache.getId();
		String str = req.getHeader("platformId");
		if(EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		
		// 新增或修改开具发票信息日志记录
		SoInvoiceDTO oldInvoice = soInvoiceManage.querySoInvoiceBySoChildId(soChildId);
		
		JsonResult<Map<String, Object>> result = soInvoiceManage.drawInvoice(soChildId,invoiceType,invoiceCode,remark,userId,invoiceAttcUrl);
		
		if (result.getCode() == 0) {
			SoInvoiceDTO newInvoice = soInvoiceManage.querySoInvoiceBySoChildId(soChildId);
			EgeoLog log = new EgeoLog();
			log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
			log.setOperObject("SoInvoiceAction_drawInvoice");
			log.setType(LogTypeConstant.INVOICE_INFO.getStatus());
			log.setOperatorObjId(newInvoice.getId());
			log.setOperatorObjCode(newInvoice.getInvoiceCode());
			log.setNewObj(newInvoice);
			if (EmptyUtil.isNotEmpty(oldInvoice)) {
				// 编辑
				log.setMsgId(LogConstant.INVOICE_INFO_MODIFY.getStatus());
				log.setOldObj(oldInvoice);
			} else {
				// 新增
				log.setMsgId(LogConstant.INVOICE_INFO_NEW.getStatus());
			}
			
			EgeoBusinessLogCommon.fillLogValue(log, req);

			try {
				ActiveMQUtils.recordBusinessLog(log);
			}catch (Exception e) {
				// TODO: handle exception
				logger.error("发送日志消息失败："+ JSON.toJSONString(log));
			}
		}
		
		return result;
	}
	
	/**
	 * 根据子订单id查询发票
	 * @param soChildId
	 * @return
	 */
	@RequestMapping(value = "/soChildInvoiceInfo")
	@ResponseBody
	public JsonResult<Map<String,Object>> soChildInvoiceInfo(Long soChildId){
		return soInvoiceManage.soChildInvoiceInfo(soChildId);
	}
	
	/**
	 * 更换公共发票信息
	 * @param orderId
	 * @param commonInvoiceId
	 * @return
	 */
	@RequestMapping(value = "/changeCommonInvoiceInfo")
	@ResponseBody
	public JsonResult<Integer> changeCommonInvoiceInfo(Long orderId, Long commonInvoiceId){
		
		return soInvoiceManage.changeCommonInvoiceInfoWithTx(orderId, commonInvoiceId);
	}
	
	/**
	 * 修改订单发票类型的信息
	 * @param orderId
	 * @param invoiceType
	 * @param invoiceContentType
	 * @return
	 */
	@RequestMapping(value = "/updateInvoiceTypeInfo")
	@ResponseBody
	public JsonResult<Integer> updateInvoiceTypeInfo(Long orderId, Integer invoiceType, Integer invoiceContentType){
		if (EmptyUtil.isEmpty(orderId))
			return JsonResult.fail("订单id不能为空");
		if (EmptyUtil.isEmpty(invoiceType))
			return JsonResult.fail("发票类型不能为空");
		if (EmptyUtil.isEmpty(invoiceContentType))
			return JsonResult.fail("发票内容不能为空");
	
		SoInvoiceDTO dto = new SoInvoiceDTO();
		dto.setInvoiceContentType(invoiceContentType);
		dto.setSoId(orderId);
		dto.setInvoiceForm(invoiceType);
		return soInvoiceManage.updateInvoiceTypeInfoWithTx(dto);
	}
	
	
	
}
	