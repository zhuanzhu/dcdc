package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.BlessingCoinBannerCompanyManage;
import com.egeo.components.product.converter.BlessingCoinBannerCompanyConverter;
import com.egeo.components.product.vo.BlessingCoinBannerCompanyVO;
import com.egeo.components.product.dto.BlessingCoinBannerCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/blessingCoinBannerCompany")
public class BlessingCoinBannerCompanyAction extends BaseSpringController {
	
	@Resource(name="blessingCoinBannerCompany")
	private BlessingCoinBannerCompanyManage blessingCoinBannerCompanyManage;


	// 业务方法：
	@RequestMapping(value = "/findBlessingCoinBannerCompanyById")
	@ResponseBody
	public JsonResult<BlessingCoinBannerCompanyVO> findBlessingCoinBannerCompanyById(Long id ) {
		
		BlessingCoinBannerCompanyDTO dto = new BlessingCoinBannerCompanyDTO();
		dto.setId(id);
		BlessingCoinBannerCompanyDTO rt = blessingCoinBannerCompanyManage.findBlessingCoinBannerCompanyById(dto);		
		return JsonResult.success(BlessingCoinBannerCompanyConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findBlessingCoinBannerCompanyAll")
	@ResponseBody
	public JsonResult<List<BlessingCoinBannerCompanyVO>> findBlessingCoinBannerCompanyAll(BlessingCoinBannerCompanyVO vo,HttpServletRequest req ) {
		BlessingCoinBannerCompanyDTO dto = BlessingCoinBannerCompanyConverter.toDTO(vo);
		List<BlessingCoinBannerCompanyDTO> rt = blessingCoinBannerCompanyManage.findBlessingCoinBannerCompanyAll(dto);	
		return JsonResult.success(BlessingCoinBannerCompanyConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findBlessingCoinBannerCompanyOfPage")
	@ResponseBody
	public JsonResult<PageResult<BlessingCoinBannerCompanyVO>> findBlessingCoinBannerCompanyOfPage(BlessingCoinBannerCompanyVO vo,Pagination page,HttpServletRequest req ) {
		BlessingCoinBannerCompanyDTO dto = BlessingCoinBannerCompanyConverter.toDTO(vo);
		PageResult<BlessingCoinBannerCompanyDTO> rt = blessingCoinBannerCompanyManage.findBlessingCoinBannerCompanyOfPage(dto, page);
        List<BlessingCoinBannerCompanyVO> list = BlessingCoinBannerCompanyConverter.toVO(rt.getList());
        PageResult<BlessingCoinBannerCompanyVO> result = new PageResult<BlessingCoinBannerCompanyVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertBlessingCoinBannerCompanyWithTx")
	@ResponseBody
	public JsonResult<Long> insertBlessingCoinBannerCompanyWithTx(BlessingCoinBannerCompanyVO vo,HttpServletRequest req ) {
		BlessingCoinBannerCompanyDTO dto = BlessingCoinBannerCompanyConverter.toDTO(vo);
		Long rt = blessingCoinBannerCompanyManage.insertBlessingCoinBannerCompanyWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateBlessingCoinBannerCompanyByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateBlessingCoinBannerCompanyByIdWithTx(BlessingCoinBannerCompanyVO vo,HttpServletRequest req ) {
		BlessingCoinBannerCompanyDTO dto = BlessingCoinBannerCompanyConverter.toDTO(vo);
		int rt = blessingCoinBannerCompanyManage.updateBlessingCoinBannerCompanyWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteBlessingCoinBannerCompanyWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteBlessingCoinBannerCompanyWithTx(BlessingCoinBannerCompanyVO vo,HttpServletRequest req ) {
		BlessingCoinBannerCompanyDTO dto = BlessingCoinBannerCompanyConverter.toDTO(vo);
		int rt = blessingCoinBannerCompanyManage.deleteBlessingCoinBannerCompanyWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	