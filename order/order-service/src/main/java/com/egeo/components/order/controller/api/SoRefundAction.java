package com.egeo.components.order.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.business.SoRefundManage;
import com.egeo.components.order.converter.SoRefundConverter;
import com.egeo.components.order.dto.SoRefundDTO;
import com.egeo.components.order.vo.SoRefundVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/order/soRefund")
public class SoRefundAction extends BaseSpringController {
	
	@Resource(name="soRefund")
	private SoRefundManage soRefundManage;

	/**
	 * 退款单列表
	 * 
	 * @param soRefundCode
	 * @param orderCode
	 * @param mail
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findSoRefundOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findSoRefundOfPage(String soRefundCode, String orderCode,
			String mail,Integer refundState,Pagination page,HttpServletRequest req ) {
		String str = req.getHeader("platformId");	
		Long platformId = null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		PageResult<Map<String, Object>> rt = soRefundManage.findSoRefundOfPage(soRefundCode, orderCode, mail,refundState, platformId, page);
		
		return JsonResult.success(rt);
		
	}
	
	/**
	 * 更新退款单备注
	 * 
	 * @param id
	 * @param remark
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/updateSoRefundRemarkWithTx")
	@ResponseBody
	public JsonResult<Integer> updateSoRefundRemarkWithTx(Long id, String remark, HttpServletRequest req) {
		logger.info("修改退款单备注");
		
		if (EmptyUtil.isEmpty(id))
			return JsonResult.fail("退款单id不能为空");
		// 退款单备注允许空
		if (EmptyUtil.isEmpty(remark))
			remark = "";
		if (remark.length() > 100) 
			return JsonResult.fail("备注长度不能超过100");
		
		SoRefundDTO dto = new SoRefundDTO();
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		dto.setId(id);
		dto.setRemark(remark);
		return JsonResult.success(soRefundManage.updateSoRefundWithTx(dto));
	}

	// 业务方法：
	@RequestMapping(value = "/findSoRefundById")
	@ResponseBody
	public JsonResult<SoRefundVO> findSoRefundById(Long id ) {
		
		SoRefundDTO dto = new SoRefundDTO();
		dto.setId(id);
		SoRefundDTO rt = soRefundManage.findSoRefundById(dto);		
		return JsonResult.success(SoRefundConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findSoRefundAll")
	@ResponseBody
	public JsonResult<List<SoRefundVO>> findSoRefundAll(SoRefundVO vo,HttpServletRequest req ) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		SoRefundDTO dto = SoRefundConverter.toDTO(vo);
		List<SoRefundDTO> rt = soRefundManage.findSoRefundAll(dto);	
		return JsonResult.success(SoRefundConverter.toVO(rt));
					 
	}	



	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertSoRefundWithTx")
	@ResponseBody
	public JsonResult<Long> insertSoRefundWithTx(SoRefundVO vo,HttpServletRequest req ) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		SoRefundDTO dto = SoRefundConverter.toDTO(vo);
		Long rt = soRefundManage.insertSoRefundWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateSoRefundByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateSoRefundByIdWithTx(SoRefundVO vo,HttpServletRequest req ) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		SoRefundDTO dto = SoRefundConverter.toDTO(vo);
		int rt = soRefundManage.updateSoRefundWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteSoRefundWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteSoRefundWithTx(SoRefundVO vo,HttpServletRequest req ) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		SoRefundDTO dto = SoRefundConverter.toDTO(vo);
		int rt = soRefundManage.deleteSoRefundWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	