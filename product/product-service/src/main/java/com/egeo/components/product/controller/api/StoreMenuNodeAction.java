package com.egeo.components.product.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StoreMenuNodeManage;
import com.egeo.components.product.converter.StoreMenuNodeConverter;
import com.egeo.components.product.dto.StoreMenuNodeDTO;
import com.egeo.components.product.vo.StoreMenuNodeVO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/storeMenuNode")
public class StoreMenuNodeAction extends BaseSpringController {
	
	@Resource(name="storeMenuNode")
	private StoreMenuNodeManage storeMenuNodeManage;


	// 业务方法：
	@RequestMapping(value = "/findStoreMenuNodeById")
	@ResponseBody
	public JsonResult<Map<String, Object>> findStoreMenuNodeById(Long id ) {
		
		StoreMenuNodeDTO dto = new StoreMenuNodeDTO();
		dto.setId(id);
		Map<String, Object> rt = storeMenuNodeManage.findStoreMenuNodeById(dto);		
		return JsonResult.success(rt);
					 
	}



	/**
	 * 查询门店菜单
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findStoreMenuNodeAll")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> findStoreMenuNodeAll(StoreMenuNodeVO vo,HttpServletRequest req ) {
		this.getCacheUser();
		StoreMenuNodeDTO dto = StoreMenuNodeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<Map<String, Object>> rt = storeMenuNodeManage.findStoreMenuNodeAll(dto);	
		return JsonResult.success(rt);
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStoreMenuNodeOfPage")
	@ResponseBody
	public JsonResult<PageResult<StoreMenuNodeVO>> findStoreMenuNodeOfPage(StoreMenuNodeVO vo,Pagination page,HttpServletRequest req ) {
		StoreMenuNodeDTO dto = StoreMenuNodeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StoreMenuNodeDTO> rt = storeMenuNodeManage.findStoreMenuNodeOfPage(dto, page);
        List<StoreMenuNodeVO> list = StoreMenuNodeConverter.toVO(rt.getList());
        PageResult<StoreMenuNodeVO> result = new PageResult<StoreMenuNodeVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}
	
	/**
	 * 根据门店查询门店菜单
	 * @param storeId
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findByStoreIdOfPage")
	@ResponseBody
	public JsonResult<Map<String, Object>> findByStoreIdOfPage(Long storeId,Pagination page,HttpServletRequest req ) {
		String str = req.getHeader("platformId");	
		Long platformId = null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		Map<String, Object> map = storeMenuNodeManage.findByStoreIdOfPage(storeId,platformId, page);
		return JsonResult.success(map);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStoreMenuNodeWithTx")
	@ResponseBody
	public JsonResult<Long> insertStoreMenuNodeWithTx(StoreMenuNodeVO vo,HttpServletRequest req ) {
		StoreMenuNodeDTO dto = StoreMenuNodeConverter.toDTO(vo);
		// 默认为0
		dto.setParentId(0L);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		if(EmptyUtil.isEmpty(vo.getName())){
			throw new BusinessException("请填写分类名称");
		}
		if(vo.getName().length() > 8){
			throw new BusinessException("菜单名称不能超过8个字");
		}
		Long rt = storeMenuNodeManage.insertStoreMenuNodeWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStoreMenuNodeByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStoreMenuNodeByIdWithTx(StoreMenuNodeVO vo,HttpServletRequest req ) {
		StoreMenuNodeDTO dto = StoreMenuNodeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		if(EmptyUtil.isEmpty(vo.getName())){
			throw new BusinessException("请填写分类名称");
		}
		if(vo.getName().length() > 8){
			throw new BusinessException("菜单名称不能超过8个字");
		}
		int rt = storeMenuNodeManage.updateStoreMenuNodeWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStoreMenuNodeWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStoreMenuNodeWithTx(StoreMenuNodeVO vo,HttpServletRequest req ) {
		StoreMenuNodeDTO dto = StoreMenuNodeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = storeMenuNodeManage.deleteStoreMenuNodeWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	