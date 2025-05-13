package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.product.business.StoreMenuNodeStandardUnitManage;
import com.egeo.components.product.converter.StoreMenuNodeStandardUnitConverter;
import com.egeo.components.product.dto.StoreMenuNodeStandardUnitDTO;
import com.egeo.components.product.vo.StoreMenuNodeStandardUnitVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/storeMenuNodeStandardUnit")
public class StoreMenuNodeStandardUnitAction extends BaseSpringController {
	
	@Resource(name="storeMenuNodeStandardUnit")
	private StoreMenuNodeStandardUnitManage storeMenuNodeStandardUnitManage;


	// 业务方法：
	@RequestMapping(value = "/findStoreMenuNodeStandardUnitById")
	@ResponseBody
	public JsonResult<StoreMenuNodeStandardUnitVO> findStoreMenuNodeStandardUnitById(Long id ) {
		
		StoreMenuNodeStandardUnitDTO dto = new StoreMenuNodeStandardUnitDTO();
		dto.setId(id);
		StoreMenuNodeStandardUnitDTO rt = storeMenuNodeStandardUnitManage.findStoreMenuNodeStandardUnitById(dto);		
		return JsonResult.success(StoreMenuNodeStandardUnitConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStoreMenuNodeStandardUnitAll")
	@ResponseBody
	public JsonResult<List<StoreMenuNodeStandardUnitVO>> findStoreMenuNodeStandardUnitAll(StoreMenuNodeStandardUnitVO vo,HttpServletRequest req ) {
		StoreMenuNodeStandardUnitDTO dto = StoreMenuNodeStandardUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StoreMenuNodeStandardUnitDTO> rt = storeMenuNodeStandardUnitManage.findStoreMenuNodeStandardUnitAll(dto);	
		return JsonResult.success(StoreMenuNodeStandardUnitConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStoreMenuNodeStandardUnitOfPage")
	@ResponseBody
	public JsonResult<PageResult<StoreMenuNodeStandardUnitVO>> findStoreMenuNodeStandardUnitOfPage(StoreMenuNodeStandardUnitVO vo,Pagination page,HttpServletRequest req ) {
		StoreMenuNodeStandardUnitDTO dto = StoreMenuNodeStandardUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StoreMenuNodeStandardUnitDTO> rt = storeMenuNodeStandardUnitManage.findStoreMenuNodeStandardUnitOfPage(dto, page);
        List<StoreMenuNodeStandardUnitVO> list = StoreMenuNodeStandardUnitConverter.toVO(rt.getList());
        PageResult<StoreMenuNodeStandardUnitVO> result = new PageResult<StoreMenuNodeStandardUnitVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStoreMenuNodeStandardUnitWithTx")
	@ResponseBody
	public JsonResult<Long> insertStoreMenuNodeStandardUnitWithTx(StoreMenuNodeStandardUnitVO vo,HttpServletRequest req ) {
		StoreMenuNodeStandardUnitDTO dto = StoreMenuNodeStandardUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = storeMenuNodeStandardUnitManage.insertStoreMenuNodeStandardUnitWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 批量插入门店与su商品关系
	 * @param standardUnitIdList
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/insertAllWithTx")
	@ResponseBody
	public JsonResult<Integer> insertStoreMenuNodeStandardUnitWithTx(Long storeMenuNodeId,String standardUnitIdList,HttpServletRequest req ) {
		if(EmptyUtil.isEmpty(standardUnitIdList))
			return JsonResult.fail("请选择一个商品");
		String str = req.getHeader("platformId");		
		Long platformId = null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		List<Long> standardUnitIds = null;
		if(EmptyUtil.isNotEmpty(standardUnitIdList))
			standardUnitIds = JSONArray.parseArray(standardUnitIdList, Long.class);
		int rt = storeMenuNodeStandardUnitManage.insertAllWithTx(storeMenuNodeId,standardUnitIds,platformId);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStoreMenuNodeStandardUnitByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStoreMenuNodeStandardUnitByIdWithTx(StoreMenuNodeStandardUnitVO vo,HttpServletRequest req ) {
		StoreMenuNodeStandardUnitDTO dto = StoreMenuNodeStandardUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = storeMenuNodeStandardUnitManage.updateStoreMenuNodeStandardUnitWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStoreMenuNodeStandardUnitWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStoreMenuNodeStandardUnitWithTx(StoreMenuNodeStandardUnitVO vo,HttpServletRequest req ) {
		StoreMenuNodeStandardUnitDTO dto = StoreMenuNodeStandardUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = storeMenuNodeStandardUnitManage.deleteStoreMenuNodeStandardUnitWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	