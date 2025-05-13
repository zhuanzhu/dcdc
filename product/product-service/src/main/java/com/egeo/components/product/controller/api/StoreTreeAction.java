package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StoreTreeManage;
import com.egeo.components.product.converter.StoreTreeConverter;
import com.egeo.components.product.dto.StoreTreeDTO;
import com.egeo.components.product.vo.StoreTreeVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/storeTree")
public class StoreTreeAction extends BaseSpringController {
	
	@Resource(name="storeTree")
	private StoreTreeManage storeTreeManage;


	// 业务方法：
	@RequestMapping(value = "/findStoreTreeById")
	@ResponseBody
	public JsonResult<StoreTreeVO> findStoreTreeById(Long id ) {
		
		StoreTreeDTO dto = new StoreTreeDTO();
		dto.setId(id);
		StoreTreeDTO rt = storeTreeManage.findStoreTreeById(dto);		
		return JsonResult.success(StoreTreeConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStoreTreeAll")
	@ResponseBody
	public JsonResult<List<StoreTreeVO>> findStoreTreeAll(StoreTreeVO vo,HttpServletRequest req ) {
		StoreTreeDTO dto = StoreTreeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StoreTreeDTO> rt = storeTreeManage.findStoreTreeAll(dto);	
		return JsonResult.success(StoreTreeConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStoreTreeOfPage")
	@ResponseBody
	public JsonResult<PageResult<StoreTreeVO>> findStoreTreeOfPage(StoreTreeVO vo,Pagination page,HttpServletRequest req ) {
		StoreTreeDTO dto = StoreTreeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StoreTreeDTO> rt = storeTreeManage.findStoreTreeOfPage(dto, page);
        List<StoreTreeVO> list = StoreTreeConverter.toVO(rt.getList());
        PageResult<StoreTreeVO> result = new PageResult<StoreTreeVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStoreTreeWithTx")
	@ResponseBody
	public JsonResult<Long> insertStoreTreeWithTx(StoreTreeVO vo,HttpServletRequest req ) {
		StoreTreeDTO dto = StoreTreeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = storeTreeManage.insertStoreTreeWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStoreTreeByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStoreTreeByIdWithTx(StoreTreeVO vo,HttpServletRequest req ) {
		StoreTreeDTO dto = StoreTreeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = storeTreeManage.updateStoreTreeWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStoreTreeWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStoreTreeWithTx(StoreTreeVO vo,HttpServletRequest req ) {
		StoreTreeDTO dto = StoreTreeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = storeTreeManage.deleteStoreTreeWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	