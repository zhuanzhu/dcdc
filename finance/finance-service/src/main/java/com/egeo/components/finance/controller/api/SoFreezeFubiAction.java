package com.egeo.components.finance.controller.api;


import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.finance.business.SoFreezeFubiManage;
import com.egeo.components.finance.converter.SoFreezeFubiConverter;
import com.egeo.components.finance.dto.SoFreezeFubiDTO;
import com.egeo.components.finance.vo.SoFreezeFubiVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/finance/soFreezeFubi")
public class SoFreezeFubiAction extends BaseSpringController {
	
	@Resource(name="soFreezeFubi")
	private SoFreezeFubiManage soFreezeFubiManage;


	// 业务方法：
	@RequestMapping(value = "/findSoFreezeFubiById")
	@ResponseBody
	public JsonResult<SoFreezeFubiVO> findSoFreezeFubiById(Long id ) {
		
		SoFreezeFubiDTO dto = new SoFreezeFubiDTO();
		dto.setId(id);
		SoFreezeFubiDTO rt = soFreezeFubiManage.findSoFreezeFubiById(dto);		
		return success(SoFreezeFubiConverter.toVO(rt));
					 
	}
	
	/**
	 * 根据订单id查询订单冻结积分
	 * @param soId
	 * @return
	 */
	@RequestMapping(value = "/findSoFreezeBalanceBySoId")
	@ResponseBody
	public JsonResult<BigDecimal> findSoFreezeBalanceBySoId(Long soId ) {
		
		BigDecimal rt = soFreezeFubiManage.findSoFreezeBalanceBySoId(soId);		
		return success(rt);
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findSoFreezeFubiAll")
	@ResponseBody
	public JsonResult<List<SoFreezeFubiVO>> findSoFreezeFubiAll(SoFreezeFubiVO vo,HttpServletRequest req ) {
		SoFreezeFubiDTO dto = SoFreezeFubiConverter.toDTO(vo);
		List<SoFreezeFubiDTO> rt = soFreezeFubiManage.findSoFreezeFubiAll(dto);	
		return success(SoFreezeFubiConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findSoFreezeFubiOfPage")
	@ResponseBody
	public JsonResult<PageResult<SoFreezeFubiVO>> findSoFreezeFubiOfPage(SoFreezeFubiVO vo,Pagination page,HttpServletRequest req ) {
		SoFreezeFubiDTO dto = SoFreezeFubiConverter.toDTO(vo);
		PageResult<SoFreezeFubiDTO> rt = soFreezeFubiManage.findSoFreezeFubiOfPage(dto, page);
        List<SoFreezeFubiVO> list = SoFreezeFubiConverter.toVO(rt.getList());
        PageResult<SoFreezeFubiVO> result = new PageResult<SoFreezeFubiVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return success(result);
					 
	}

}
	