package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StoreMenuTreeManage;
import com.egeo.components.product.converter.StoreMenuTreeConverter;
import com.egeo.components.product.dto.StoreMenuTreeDTO;
import com.egeo.components.product.vo.StoreMenuTreeVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/storeMenuTree")
public class StoreMenuTreeAction extends BaseSpringController {
	
	@Resource(name="storeMenuTree")
	private StoreMenuTreeManage storeMenuTreeManage;


	// 业务方法：
	@RequestMapping(value = "/findStoreMenuTreeById")
	@ResponseBody
	public JsonResult<StoreMenuTreeVO> findStoreMenuTreeById(Long id ) {
		
		StoreMenuTreeDTO dto = new StoreMenuTreeDTO();
		dto.setId(id);
		StoreMenuTreeDTO rt = storeMenuTreeManage.findStoreMenuTreeById(dto);		
		return JsonResult.success(StoreMenuTreeConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStoreMenuTreeAll")
	@ResponseBody
	public JsonResult<List<StoreMenuTreeVO>> findStoreMenuTreeAll(StoreMenuTreeVO vo,HttpServletRequest req ) {
		StoreMenuTreeDTO dto = StoreMenuTreeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StoreMenuTreeDTO> rt = storeMenuTreeManage.findStoreMenuTreeAll(dto);	
		return JsonResult.success(StoreMenuTreeConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStoreMenuTreeOfPage")
	@ResponseBody
	public JsonResult<PageResult<StoreMenuTreeVO>> findStoreMenuTreeOfPage(StoreMenuTreeVO vo,Pagination page,HttpServletRequest req ) {
		StoreMenuTreeDTO dto = StoreMenuTreeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StoreMenuTreeDTO> rt = storeMenuTreeManage.findStoreMenuTreeOfPage(dto, page);
        List<StoreMenuTreeVO> list = StoreMenuTreeConverter.toVO(rt.getList());
        PageResult<StoreMenuTreeVO> result = new PageResult<StoreMenuTreeVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStoreMenuTreeWithTx")
	@ResponseBody
	public JsonResult<Long> insertStoreMenuTreeWithTx(StoreMenuTreeVO vo,HttpServletRequest req ) {
		StoreMenuTreeDTO dto = StoreMenuTreeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = storeMenuTreeManage.insertStoreMenuTreeWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStoreMenuTreeByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStoreMenuTreeByIdWithTx(StoreMenuTreeVO vo,HttpServletRequest req ) {
		StoreMenuTreeDTO dto = StoreMenuTreeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = storeMenuTreeManage.updateStoreMenuTreeWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStoreMenuTreeWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStoreMenuTreeWithTx(StoreMenuTreeVO vo,HttpServletRequest req ) {
		StoreMenuTreeDTO dto = StoreMenuTreeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = storeMenuTreeManage.deleteStoreMenuTreeWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	