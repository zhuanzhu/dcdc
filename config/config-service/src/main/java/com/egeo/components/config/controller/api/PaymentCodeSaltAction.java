package com.egeo.components.config.controller.api;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.config.converter.PaymentCodeSaltConverter;
import com.egeo.components.config.dto.PaymentCodeSaltDTO;
import com.egeo.components.config.service.read.PaymentCodeSaltReadService;
import com.egeo.components.config.service.write.PaymentCodeSaltWriteService;
import com.egeo.components.config.vo.PaymentCodeSaltVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/config/secret/paymentCodeSalt")
public class PaymentCodeSaltAction extends BaseSpringController {
	
	@Autowired
	private PaymentCodeSaltReadService paymentCodeSaltReadManage;
	@Autowired
	private PaymentCodeSaltWriteService paymentCodeSaltWriteManage;


	// 业务方法：
	@RequestMapping(value = "/findPaymentCodeSaltById")
	@ResponseBody
	public JsonResult<PaymentCodeSaltVO> findPaymentCodeSaltById(Long id ) {
		
		PaymentCodeSaltDTO dto = new PaymentCodeSaltDTO();
		dto.setId(id);
		PaymentCodeSaltDTO rt = paymentCodeSaltReadManage.findPaymentCodeSaltById(dto);		
		return success(PaymentCodeSaltConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findPaymentCodeSaltAll")
	@ResponseBody
	public JsonResult<List<PaymentCodeSaltVO>> findPaymentCodeSaltAll(PaymentCodeSaltVO vo,HttpServletRequest req ) {
		PaymentCodeSaltDTO dto = PaymentCodeSaltConverter.toDTO(vo);
		List<PaymentCodeSaltDTO> rt = paymentCodeSaltReadManage.findPaymentCodeSaltAll(dto);	
		return success(PaymentCodeSaltConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findPaymentCodeSaltOfPage")
	@ResponseBody
	public JsonResult<PageResult<PaymentCodeSaltVO>> findPaymentCodeSaltOfPage(PaymentCodeSaltVO vo,Pagination page,HttpServletRequest req ) {
		PaymentCodeSaltDTO dto = PaymentCodeSaltConverter.toDTO(vo);
		PageResult<PaymentCodeSaltDTO> rt = paymentCodeSaltReadManage.findPaymentCodeSaltOfPage(dto, page);
        List<PaymentCodeSaltVO> list = PaymentCodeSaltConverter.toVO(rt.getList());
        PageResult<PaymentCodeSaltVO> result = new PageResult<PaymentCodeSaltVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertPaymentCodeSaltWithTx")
	@ResponseBody
	public JsonResult<Long> insertPaymentCodeSaltWithTx(PaymentCodeSaltVO vo,HttpServletRequest req ) {
		PaymentCodeSaltDTO dto = PaymentCodeSaltConverter.toDTO(vo);
		Long rt = paymentCodeSaltWriteManage.insertPaymentCodeSaltWithTx(dto);	
		return success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updatePaymentCodeSaltByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updatePaymentCodeSaltByIdWithTx(PaymentCodeSaltVO vo,HttpServletRequest req ) {
		PaymentCodeSaltDTO dto = PaymentCodeSaltConverter.toDTO(vo);
		int rt = paymentCodeSaltWriteManage.updatePaymentCodeSaltWithTx(dto);	
		return success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deletePaymentCodeSaltWithTx")
	@ResponseBody
	public JsonResult<Integer> deletePaymentCodeSaltWithTx(PaymentCodeSaltVO vo,HttpServletRequest req ) {
		PaymentCodeSaltDTO dto = PaymentCodeSaltConverter.toDTO(vo);
		int rt = paymentCodeSaltWriteManage.deletePaymentCodeSaltWithTx(dto);	
		return success(rt);					 
	}
		
}
	