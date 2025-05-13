package com.egeo.components.order.business.impl;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoInvoiceManage;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoInvoiceDTO;
import com.egeo.components.order.facade.SoFacade;
import com.egeo.components.order.facade.SoInvoiceFacade;
import com.egeo.components.order.vo.InvoiceDetailVO;
import com.egeo.components.order.vo.InvoiceHintVO;
import com.egeo.components.order.vo.SoChildInvoiceVO;
import com.egeo.components.order.vo.SoInvoiceVO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.DateUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.FileUtil;
import com.egeo.utils.SendMail;
import com.egeo.web.JsonResult;

@Service("soInvoice")
public class SoInvoiceManageImpl implements SoInvoiceManage {

	@Resource(name = "soInvoiceFacade")
	private SoInvoiceFacade invoiceFacade;

	@Autowired
	private SendMail sendMail;
	
	@Resource(name = "soFacade")
	private SoFacade soFacade;


	@Override
	public SoInvoiceDTO findSoInvoiceById(Long id) {
		return invoiceFacade.findSoInvoiceById(id);
	}

	@Override
	public PageResult<SoInvoiceDTO> findSoInvoiceOfPage(SoInvoiceDTO dto, Pagination page) {
		return invoiceFacade.findSoInvoiceOfPage(dto, page);
	}

	@Override
	public List<SoInvoiceDTO> findSoInvoiceAll(SoInvoiceDTO dto) {
		return invoiceFacade.findSoInvoiceAll(dto);
	}

	@Override
	public JsonResult<Map<String, Object>> drawInvoice(Long soChildId, Integer invoiceType, String invoiceCode,
			String remark, Long userId, String invoiceAttcUrl) {
		if (soChildId == null)
			return JsonResult.fail("请选择子订单");
		if (invoiceType == null)
			return JsonResult.fail("请指定发票类型");// 0:纸质发票  1:电子发票 2:增值税发票 
		if (invoiceType != 0 && invoiceType != 1 && invoiceType != 2)
			return JsonResult.fail("错误的发票类型");
		if (EmptyUtil.isBlank(invoiceCode))
			return JsonResult.fail("请填写发票号码");
		if (EmptyUtil.isBlank(remark))
			remark = null;
		// 查询子订单信息
		SoChildDTO sc = soFacade.findSoChildById(soChildId);
		if (sc == null)
			return JsonResult.fail("子订单不存在");
		// 查询该订单的公共发票信息
		SoInvoiceDTO mainInvoice = invoiceFacade.queryMainSoInvoiceByOrderId(sc.getSoId());
		if (mainInvoice == null)
			return JsonResult.fail("订单公共发票信息暂无,请优先编辑");
		// 根据子订单查询发票
		SoInvoiceDTO childInvoice = invoiceFacade.querySoInvoiceBySoChildId(soChildId);
		if (childInvoice == null) {
			// 该子订单尚无发票,新增
			SoInvoiceDTO insertInvoice = new SoInvoiceDTO();
			insertInvoice.setInvoiceCode(invoiceCode);
			insertInvoice.setInvoiceFinType(0);
			insertInvoice.setInvoiceForm(invoiceType);
			insertInvoice.setInvoiceRemark(remark);
			insertInvoice.setInvoiceStatus(1);
			insertInvoice.setInvoiceTaxType(mainInvoice.getInvoiceTaxType());
			insertInvoice.setInvoiceTitleContent(mainInvoice.getInvoiceTitleContent());
			insertInvoice.setInvoiceTitleType(mainInvoice.getInvoiceTitleType());
			insertInvoice.setIsNeedDetails(mainInvoice.getIsNeedDetails());
			insertInvoice.setPlatformId(mainInvoice.getPlatformId());
			insertInvoice.setReceiverMail(mainInvoice.getReceiverMail());
			insertInvoice.setSoChildId(soChildId);
			insertInvoice.setSoId(mainInvoice.getSoId());
			insertInvoice.setTaxpayerIdentificationCode(mainInvoice.getTaxpayerIdentificationCode());
			insertInvoice.setUserId(mainInvoice.getUserId());
			
			insertInvoice.setInvoiceAttcUrl(invoiceAttcUrl);
			insertInvoice.setRegisterAddr(mainInvoice.getRegisterAddr());
			insertInvoice.setRegisterTel(mainInvoice.getRegisterTel());
			insertInvoice.setDepositBank(mainInvoice.getDepositBank());
			insertInvoice.setBankAccount(mainInvoice.getBankAccount());
			insertInvoice.setBusinessLicenceUrl(mainInvoice.getBusinessLicenceUrl());
			insertInvoice.setInvoiceValue(sc.getAmount());
			// 开具发票,并新增订单操作流水
			invoiceFacade.insertSoInvoiceAndOperateFlow(insertInvoice, userId);
		} else {
			// 该子订单已有发票,修改
			SoInvoiceDTO updateInvoice = new SoInvoiceDTO();
			updateInvoice.setId(childInvoice.getId());
			updateInvoice.setInvoiceForm(invoiceType);
			updateInvoice.setInvoiceCode(invoiceCode);
			updateInvoice.setInvoiceRemark(remark);
			updateInvoice.setInvoiceStatus(1);
			
			updateInvoice.setInvoiceAttcUrl(invoiceAttcUrl);
			updateInvoice.setInvoiceValue(sc.getAmount());
			invoiceFacade.updateSoInvoiceWithTx(updateInvoice);
		}
		return JsonResult.success();
	}

	@Override
	public int updateSoInvoiceWithTx(SoInvoiceDTO dto) {
		return invoiceFacade.updateSoInvoiceWithTx(dto);
	}

	@Override
	public int deleteSoInvoiceWithTx(SoInvoiceDTO dto) {
		return invoiceFacade.deleteSoInvoiceWithTx(dto);
	}

	@Override
	public JsonResult<Map<String, Object>> saveInvoice(Long id,Integer invoiceForm, Integer invoiceTitleType,
			Integer contentType, Long userId, String title, Long commonInvoiceId,Long platformId) {
		if (invoiceForm == null)
			return JsonResult.fail("请选择发票类型");
		if (invoiceTitleType == null)
			return JsonResult.fail("请选择抬头类型");
		if (contentType == null)
			return JsonResult.fail("请选择发票内容");
		
		if (invoiceTitleType.intValue() == 0) {
			// 个人: 校验姓名 title (暂无规则)
			commonInvoiceId = null;
		} else {
			if (commonInvoiceId == null)
				return JsonResult.fail("公共发票id不能为空");
		}
		
		SoInvoiceDTO inv = new SoInvoiceDTO();
		inv.setInvoiceContentType(contentType);
		inv.setInvoiceFinType(0);
		inv.setInvoiceForm(invoiceForm);
		inv.setInvoiceStatus(0);
		inv.setInvoiceTaxType(1);
		inv.setInvoiceTitleContent(title);
		inv.setInvoiceTitleType(invoiceTitleType);
		inv.setIsNeedDetails(1);
		inv.setPlatformId(platformId);
		inv.setUserId(userId);
		if(id==null || id.equals(0L)) {
			//新增
			id = invoiceFacade.insertSoInvoiceWithTx(inv, commonInvoiceId);
		}else {
			//更新
			inv.setId(id);
			invoiceFacade.updateSoInvoiceWithTx(inv, commonInvoiceId);
		}
		return JsonResult.success("invoiceId", id);
	}

	@Override
	public JsonResult<Map<String, Object>> invoiceHint(Long platformId, Long userId) {
		List<SoInvoiceDTO> dtoList = invoiceFacade.queryDistinctInvoiceByUserId(userId, platformId);
		// List<InvoiceHintVO> voList=new ArrayList<>();
		Set<InvoiceHintVO> voSet = new HashSet<>();
		// 同名发票排重,出现较晚的排在前面,先加入set,排在后面的同名发票将无法加入set
		for (SoInvoiceDTO dto : dtoList) {
			InvoiceHintVO vo = new InvoiceHintVO();
			vo.setTitle(dto.getInvoiceTitleContent());
			vo.setTpid(dto.getTaxpayerIdentificationCode());
			voSet.add(vo);
		}
		return JsonResult.success("hints", voSet);
	}

	@Override
	public JsonResult<Map<String, Object>> invoiceDetail(Long id) {
		SoInvoiceDTO inv = invoiceFacade.findSoInvoiceById(id);
		if (inv == null)
			return JsonResult.fail("发票不存在");
		InvoiceDetailVO vo = new InvoiceDetailVO();
		vo.setId(inv.getId());
		vo.setInvoiceContentType(inv.getInvoiceContentType());
		vo.setInvoiceForm(inv.getInvoiceForm());
		vo.setInvoiceTaxType(inv.getInvoiceTaxType());
		vo.setInvoiceTitleContent(inv.getInvoiceTitleContent());
		vo.setTaxpayerIdentificationCode(inv.getTaxpayerIdentificationCode());
		vo.setUserId(inv.getUserId());
		return JsonResult.success("inv", vo);
	}

	@Override
	public JsonResult<Map<String, Object>> findInvoiceBySoId(Long soId) {
		if (soId == null)
			return JsonResult.fail("请选择订单");
		// 查询所有子订单
		List<SoChildDTO> scs = soFacade.querySoChildListBySoId(soId);
		List<SoChildInvoiceVO> voList = new ArrayList<>();
		for (SoChildDTO sc : scs) {
			SoChildInvoiceVO vo = new SoChildInvoiceVO();
			vo.setChildCode(sc.getChildCode());
			vo.setId(sc.getId());
			SoInvoiceDTO inv = invoiceFacade.querySoInvoiceBySoChildId(sc.getId());
			if (inv != null) {
				vo.setInvoiceId(inv.getId());
				vo.setInvoiceCode(inv.getInvoiceCode());
				vo.setFinanceStatus(inv.getInvoiceStatus());
				vo.setRemark(inv.getInvoiceRemark());
				vo.setInvoiceType(inv.getInvoiceForm());
				vo.setInvoiceAttcUrl(inv.getInvoiceAttcUrl());
			}
			voList.add(vo);
		}

		// 查询公共信息
		SoInvoiceDTO publicInvoice = invoiceFacade.queryMainSoInvoiceByOrderId(soId);
		Map<String, Object> data = new HashMap<>();
		data.put("list", voList);
		if (publicInvoice == null) {
			data.put("invoiceExists", false);
			data.put("titleType", null);
			data.put("title", null);
			data.put("invoiceType", null);
			data.put("invoiceContentType", null);
			data.put("tpic", null);
			data.put("registerTel", null);
			data.put("registerAddr", null);
			data.put("depositBank", null);
			data.put("bankAccount", null);
			data.put("businessLicenceUrl", null);
		} else {
			data.put("invoiceExists", true);
			data.put("invoiceType", publicInvoice.getInvoiceForm());
			data.put("titleType", publicInvoice.getInvoiceTitleType());
			data.put("title", publicInvoice.getInvoiceTitleContent());
			data.put("invoiceContentType", publicInvoice.getInvoiceContentType());
			data.put("tpic", publicInvoice.getTaxpayerIdentificationCode());
			data.put("registerTel", publicInvoice.getRegisterTel());
			data.put("registerAddr", publicInvoice.getRegisterAddr());
			data.put("depositBank", publicInvoice.getDepositBank());
			data.put("bankAccount", publicInvoice.getBankAccount());
			data.put("businessLicenceUrl", publicInvoice.getBusinessLicenceUrl());
		}
		return JsonResult.success(data);
	}
	

	@Override
	public JsonResult<Map<String, Object>> soChildInvoiceInfo(Long soChildId) {
		if(soChildId==null)
			return JsonResult.fail("请选择子订单");
		SoChildDTO soChildDTO = soFacade.findSoChildById(soChildId);
		if (soChildDTO == null)
			return JsonResult.fail("子订单不存在");
		
		SoInvoiceDTO inv = invoiceFacade.querySoInvoiceBySoChildId(soChildId);
		SoChildInvoiceVO vo=new SoChildInvoiceVO();
		vo.setId(soChildId);
		if(inv!=null) {
			vo.setInvoiceCode(inv.getInvoiceCode());
			vo.setInvoiceId(inv.getId());
			vo.setInvoiceType(inv.getInvoiceForm());
			vo.setRemark(inv.getInvoiceRemark());
			vo.setInvoiceAttcUrl(inv.getInvoiceAttcUrl());
			vo.setInvoiceTitleContent(inv.getInvoiceTitleContent());
			if(EmptyUtil.isNotEmpty(soChildDTO.getDeliveryFee())){
				vo.setInvoiceValue(soChildDTO.getAmount().add(soChildDTO.getDeliveryFee()));
			}else{
				vo.setInvoiceValue(soChildDTO.getAmount());
			}
		}
		return JsonResult.success("invoice", vo);
	}

	@Override
	public JsonResult<Map<String, Object>> updateInvoiceInfo(Long orderId, Integer titleType, String title,
			Integer invoiceType, String taxNo, Integer invoiceContentType, Long platformId) {
		if (orderId == null)
			return JsonResult.fail("请选择订单");
		if (titleType == null)
			return JsonResult.fail("请指定抬头类型");
		if (titleType != 0 && titleType != 1) {
			return JsonResult.fail("错误的抬头类型");
		}
		if (StringUtil.isBlank(title))
			return JsonResult.fail("请填写发票抬头");
		if (invoiceType == null)
			return JsonResult.fail("请指定发票类型");
		if (titleType == 1 && StringUtil.isBlank(taxNo))
			return JsonResult.fail("请填写纳税人识别号");
		if (invoiceContentType == null)
			return JsonResult.fail("请选择发票内容");
		if (invoiceContentType != 0 && invoiceContentType != 1) {
			return JsonResult.fail("错误的发票内容类型");
		}
		// 如果抬头类型是个人,将纳税人识别号清除
		if (titleType == 0) {
			taxNo = null;
		}
		SoDTO so = soFacade.querySoById(orderId);
		if (so == null)
			return JsonResult.fail("订单不存在");
		// 查询订单主发票是否存在
		SoInvoiceDTO mainInvoice = invoiceFacade.queryMainSoInvoiceByOrderId(orderId);
		if (mainInvoice == null) {
			// 如果不存在则插入
			SoInvoiceDTO insertInvoice = new SoInvoiceDTO();
			insertInvoice.setInvoiceFinType(0);
			insertInvoice.setInvoiceForm(invoiceType);
			insertInvoice.setInvoiceStatus(0);
			insertInvoice.setInvoiceTaxType(1);
			insertInvoice.setInvoiceTitleContent(title);
			insertInvoice.setPlatformId(platformId);
			insertInvoice.setSoId(orderId);
			insertInvoice.setTaxpayerIdentificationCode(taxNo);
			insertInvoice.setUserId(so.getUserId());
			insertInvoice.setInvoiceContentType(invoiceContentType);
			insertInvoice.setInvoiceTitleType(titleType);
			invoiceFacade.insertSoInvoiceWithTx(insertInvoice);
		} else {
			// 如果存在则修改
			invoiceFacade.updateSoInvoiceBySoId(orderId, titleType, title, invoiceType, taxNo, invoiceContentType);
		}
		return JsonResult.success();
	}

	@Override
	public JsonResult<Map<String, Object>> invoiceById(Long id) {
		SoInvoiceVO inv=new SoInvoiceVO();
		if(id!=null) {
			SoInvoiceDTO dto=invoiceFacade.findSoInvoiceById(id);
			if(dto!=null) {
				inv.setId(dto.getId());
				inv.setInvoiceContentType(dto.getInvoiceContentType());
				inv.setInvoiceForm(dto.getInvoiceForm());
				inv.setInvoiceTitleContent(dto.getInvoiceTitleContent());
				inv.setInvoiceTitleType(dto.getInvoiceTitleType());
				inv.setTpic(dto.getTaxpayerIdentificationCode());
			}
		}
		return JsonResult.success("invoice", inv);
	}

	@Override
	public SoInvoiceDTO queryMainSoInvoiceByOrderId(Long orderId) {
		// 查询订单主发票是否存在
		return invoiceFacade.queryMainSoInvoiceByOrderId(orderId);
	}

	@Override
	public SoInvoiceDTO querySoInvoiceBySoChildId(Long soChildId) {
		return invoiceFacade.querySoInvoiceBySoChildId(soChildId);
	}

	@Override
	public List<Map<String, Object>> queryElecInvoice(SoInvoiceDTO dto) {
		dto.setInvoiceStatus(1);
		dto.setInvoiceForm(1);
		List<SoInvoiceDTO> soInvoiceList = invoiceFacade.findSoInvoiceAll(dto);
		List<Map<String, Object>> list = new ArrayList<>();
		for (SoInvoiceDTO soInvoice : soInvoiceList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", soInvoice.getId());
			map.put("invoiceTitleContent", soInvoice.getInvoiceTitleContent());
			map.put("invoiceValue", soInvoice.getInvoiceValue());
			map.put("offeredTime", soInvoice.getUpdateTime().getTime());
			
			list.add(map);
		}
		
		return list;
	}

	@Override
	public JsonResult<Long> downloadInvoice(List<Long> invoiceIdList, String email, String loginName) {
		
		List<File> attachments = new ArrayList<>();
		Long orderId = null;
		SoInvoiceDTO invoice = new SoInvoiceDTO();
		for (Long invoiceId : invoiceIdList) {
			SoInvoiceDTO dto = invoiceFacade.findSoInvoiceById(invoiceId);
			if (dto != null) {
				orderId = orderId == null ?  dto.getSoId() : orderId;
				invoice.setInvoiceTitleContent(dto.getInvoiceTitleContent());
				invoice.setInvoiceValue(invoice.getInvoiceValue() != null && dto.getInvoiceValue() != null ? 
						dto.getInvoiceValue().add(invoice.getInvoiceValue()) : dto.getInvoiceValue());
				invoice.setUpdateTime(dto.getUpdateTime());
			}
			File invoiceAttc = FileUtil.loadFileFromURL(dto.getInvoiceAttcUrl());
			if (invoiceAttc != null) 
				attachments.add(invoiceAttc);
		}
		
		// 查询订单信息
		SoDTO soDTO = new SoDTO();
		soDTO.setId(orderId);
		soDTO = soFacade.findSoById(soDTO);
		
		String invoiceTitleContent = invoice.getInvoiceTitleContent() != null ? invoice.getInvoiceTitleContent() : StringUtils.EMPTY;
		String invoiceValue = invoice.getInvoiceValue() != null ? invoice.getInvoiceValue().toString() : StringUtils.EMPTY;
		String updateTime = DateUtils.getDefaultDate(invoice.getUpdateTime());
		String orderCode = soDTO != null ? soDTO.getOrderCode() : StringUtils.EMPTY;
		
		String html = "<html><head></head><body>尊敬的"+loginName 
		+ "您好：<br/>感谢您在大厨管家购物！<br/>大厨管家已为您开具订单" + orderCode
		+ "的电子普通发票，请见附件。<br/>发票抬头   " + invoiceTitleContent
		+ "<br/>发票金额   " + invoiceValue
		+ "<br/>开票时间   " + updateTime
		+ "<br/><br/><br/>大厨管家团队<br/>" + DateUtils.getDefaultDate(new Date())
		+ "</body></html>";
		String subject = "您的大厨管家订单电子发票已开具";
		
		//发送邮箱
    	new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					sendMail.sendMail(email, subject, html, attachments);
				} catch (Exception e) {
					throw new BusinessException("发送邮箱失败");	
				}
				
				// 删除临时文件
				for (File file : attachments) {
					FileUtil.deleteFile(file);
				}
				
			}
		}).start();
		
		return JsonResult.success(null);
	}

	@Override
	public JsonResult<Integer> changeCommonInvoiceInfoWithTx(Long orderId, Long commonInvoiceId) {
		if (orderId == null) 
			return JsonResult.fail("订单id不能为空");
		if (commonInvoiceId == null)
			return JsonResult.fail("公共发票信息id不能为空");
		
		return JsonResult.success(invoiceFacade.changeCommonInvoiceInfoWithTx(orderId, commonInvoiceId));
	}

	@Override
	public JsonResult<Integer> updateInvoiceTypeInfoWithTx(SoInvoiceDTO dto) {
		
		return JsonResult.success(invoiceFacade.updateInvoiceTypeInfoWithTx(dto));
	}

}
