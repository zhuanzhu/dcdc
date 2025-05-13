package com.egeo.components.order.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.business.CartManage;
import com.egeo.components.order.converter.CartConverter;
import com.egeo.components.order.dto.CartDTO;
import com.egeo.components.order.vo.CartVO;
import com.egeo.components.utils.qc.QCUtil;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/order/cart")
public class CartAction extends BaseSpringController {
	
	@Resource(name="cart")
	private CartManage cartManage;


	@Autowired
	private QCUtil qCUtil;
	// 业务方法：
	@RequestMapping(value = "/findCartById")
	@ResponseBody
	public JsonResult<CartVO> findCartById(Long id ) {
		
		CartDTO dto = new CartDTO();
		dto.setId(id);
		CartDTO rt = cartManage.findCartById(dto);		
		return JsonResult.success(CartConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findCartAll")
	@ResponseBody
	public JsonResult<List<CartVO>> findCartAll(CartVO vo,HttpServletRequest req ) {
		CartDTO dto = CartConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<CartDTO> rt = cartManage.findCartAll(dto);	
		return JsonResult.success(CartConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findCartOfPage")
	@ResponseBody
	public JsonResult<PageResult<CartVO>> findCartOfPage(CartVO vo,Pagination page,HttpServletRequest req ) {
		CartDTO dto = CartConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<CartDTO> rt = cartManage.findCartOfPage(dto, page);
        List<CartVO> list = CartConverter.toVO(rt.getList());
        PageResult<CartVO> result = new PageResult<CartVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertCartWithTx")
	@ResponseBody
	public JsonResult<Long> insertCartWithTx(CartVO vo,HttpServletRequest req ) {
		CartDTO dto = CartConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = cartManage.insertCartWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateCartByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateCartByIdWithTx(CartVO vo,HttpServletRequest req ) {
		CartDTO dto = CartConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = cartManage.updateCartWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteCartWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteCartWithTx(CartVO vo,HttpServletRequest req ) {
		CartDTO dto = CartConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = cartManage.deleteCartWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	