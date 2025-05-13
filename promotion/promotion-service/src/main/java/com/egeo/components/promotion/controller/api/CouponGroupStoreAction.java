package com.egeo.components.promotion.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.business.CouponGroupStoreManage;
import com.egeo.components.promotion.converter.CouponGroupStoreConverter;
import com.egeo.components.promotion.dto.CouponGroupStoreDTO;
import com.egeo.components.promotion.vo.CouponGroupStoreVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/promotion/couponGroupStore")
public class CouponGroupStoreAction extends BaseSpringController {
	
	@Resource(name="couponGroupStore")
	private CouponGroupStoreManage couponGroupStoreManage;


	// 业务方法：
	@RequestMapping(value = "/findCouponGroupStoreById")
	@ResponseBody
	public JsonResult<CouponGroupStoreVO> findCouponGroupStoreById(Long id ) {
		
		CouponGroupStoreDTO dto = new CouponGroupStoreDTO();
		dto.setId(id);
		CouponGroupStoreDTO rt = couponGroupStoreManage.findCouponGroupStoreById(dto);		
		return success(CouponGroupStoreConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findCouponGroupStoreAll")
	@ResponseBody
	public JsonResult<List<CouponGroupStoreVO>> findCouponGroupStoreAll(CouponGroupStoreVO vo,HttpServletRequest req ) {
		CouponGroupStoreDTO dto = CouponGroupStoreConverter.toDTO(vo);
		List<CouponGroupStoreDTO> rt = couponGroupStoreManage.findCouponGroupStoreAll(dto);	
		return success(CouponGroupStoreConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findCouponGroupStoreOfPage")
	@ResponseBody
	public JsonResult<PageResult<CouponGroupStoreVO>> findCouponGroupStoreOfPage(CouponGroupStoreVO vo,Pagination page,HttpServletRequest req ) {
		CouponGroupStoreDTO dto = CouponGroupStoreConverter.toDTO(vo);
		PageResult<CouponGroupStoreDTO> rt = couponGroupStoreManage.findCouponGroupStoreOfPage(dto, page);
        List<CouponGroupStoreVO> list = CouponGroupStoreConverter.toVO(rt.getList());
        PageResult<CouponGroupStoreVO> result = new PageResult<CouponGroupStoreVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertCouponGroupStoreWithTx")
	@ResponseBody
	public JsonResult<Long> insertCouponGroupStoreWithTx(CouponGroupStoreVO vo,HttpServletRequest req ) {
		CouponGroupStoreDTO dto = CouponGroupStoreConverter.toDTO(vo);
		Long rt = couponGroupStoreManage.insertCouponGroupStoreWithTx(dto);	
		return success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateCouponGroupStoreByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateCouponGroupStoreByIdWithTx(CouponGroupStoreVO vo,HttpServletRequest req ) {
		CouponGroupStoreDTO dto = CouponGroupStoreConverter.toDTO(vo);
		int rt = couponGroupStoreManage.updateCouponGroupStoreWithTx(dto);	
		return success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteCouponGroupStoreWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteCouponGroupStoreWithTx(CouponGroupStoreVO vo,HttpServletRequest req ) {
		CouponGroupStoreDTO dto = CouponGroupStoreConverter.toDTO(vo);
		int rt = couponGroupStoreManage.deleteCouponGroupStoreWithTx(dto);	
		return success(rt);					 
	}
		
}
	