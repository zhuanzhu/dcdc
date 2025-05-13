package com.egeo.components.stock.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.stock.business.StoreStockChangeApplyPictureManage;
import com.egeo.components.stock.converter.StoreStockChangeApplyPictureConverter;
import com.egeo.components.stock.vo.StoreStockChangeApplyPictureVO;
import com.egeo.components.stock.dto.StoreStockChangeApplyPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;



@Controller
@RequestMapping("/api/stock/storeStockChangeApplyPicture")
public class StoreStockChangeApplyPictureAction extends BaseSpringController {
	
	@Resource(name="storeStockChangeApplyPicture")
	private StoreStockChangeApplyPictureManage storeStockChangeApplyPictureManage;


	// 业务方法：
	@RequestMapping(value = "/findStoreStockChangeApplyPictureById")
	@ResponseBody
	public JsonResult<StoreStockChangeApplyPictureVO> findStoreStockChangeApplyPictureById(Long id ) {
		
		StoreStockChangeApplyPictureDTO dto = new StoreStockChangeApplyPictureDTO();
		dto.setId(id);
		StoreStockChangeApplyPictureDTO rt = storeStockChangeApplyPictureManage.findStoreStockChangeApplyPictureById(dto);		
		return JsonResult.success(StoreStockChangeApplyPictureConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStoreStockChangeApplyPictureAll")
	@ResponseBody
	public JsonResult<List<String>> findStoreStockChangeApplyPictureAll(StoreStockChangeApplyPictureVO vo,HttpServletRequest req ) {
		StoreStockChangeApplyPictureDTO dto = StoreStockChangeApplyPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<String> rt = storeStockChangeApplyPictureManage.findStoreStockChangeApplyPictureAll(dto);	
		return JsonResult.success(rt);
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStoreStockChangeApplyPictureOfPage")
	@ResponseBody
	public JsonResult<PageResult<StoreStockChangeApplyPictureVO>> findStoreStockChangeApplyPictureOfPage(StoreStockChangeApplyPictureVO vo,Pagination page,HttpServletRequest req ) {
		StoreStockChangeApplyPictureDTO dto = StoreStockChangeApplyPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StoreStockChangeApplyPictureDTO> rt = storeStockChangeApplyPictureManage.findStoreStockChangeApplyPictureOfPage(dto, page);
        List<StoreStockChangeApplyPictureVO> list = StoreStockChangeApplyPictureConverter.toVO(rt.getList());
        PageResult<StoreStockChangeApplyPictureVO> result = new PageResult<StoreStockChangeApplyPictureVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStoreStockChangeApplyPictureWithTx")
	@ResponseBody
	public JsonResult<Long> insertStoreStockChangeApplyPictureWithTx(StoreStockChangeApplyPictureVO vo,HttpServletRequest req ) {
		StoreStockChangeApplyPictureDTO dto = StoreStockChangeApplyPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = storeStockChangeApplyPictureManage.insertStoreStockChangeApplyPictureWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStoreStockChangeApplyPictureByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStoreStockChangeApplyPictureByIdWithTx(StoreStockChangeApplyPictureVO vo,HttpServletRequest req ) {
		StoreStockChangeApplyPictureDTO dto = StoreStockChangeApplyPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = storeStockChangeApplyPictureManage.updateStoreStockChangeApplyPictureWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStoreStockChangeApplyPictureWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStoreStockChangeApplyPictureWithTx(StoreStockChangeApplyPictureVO vo,HttpServletRequest req ) {
		StoreStockChangeApplyPictureDTO dto = StoreStockChangeApplyPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = storeStockChangeApplyPictureManage.deleteStoreStockChangeApplyPictureWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	