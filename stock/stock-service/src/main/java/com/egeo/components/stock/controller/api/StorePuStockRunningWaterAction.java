package com.egeo.components.stock.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.stock.business.StorePuStockRunningWaterManage;
import com.egeo.components.stock.converter.StorePuStockRunningWaterConverter;
import com.egeo.components.stock.vo.StorePuStockRunningWaterVO;
import com.egeo.components.stock.dto.StorePuStockRunningWaterDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;



@Controller
@RequestMapping("/api/stock/storePuStockRunningWater")
public class StorePuStockRunningWaterAction extends BaseSpringController {
	
	@Resource(name="storePuStockRunningWater")
	private StorePuStockRunningWaterManage storePuStockRunningWaterManage;


	// 业务方法：
	@RequestMapping(value = "/findStorePuStockRunningWaterById")
	@ResponseBody
	public JsonResult<StorePuStockRunningWaterVO> findStorePuStockRunningWaterById(Long id ) {
		
		StorePuStockRunningWaterDTO dto = new StorePuStockRunningWaterDTO();
		dto.setId(id);
		StorePuStockRunningWaterDTO rt = storePuStockRunningWaterManage.findStorePuStockRunningWaterById(dto);		
		return JsonResult.success(StorePuStockRunningWaterConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStorePuStockRunningWaterAll")
	@ResponseBody
	public JsonResult<List<StorePuStockRunningWaterVO>> findStorePuStockRunningWaterAll(StorePuStockRunningWaterVO vo,HttpServletRequest req ) {
		StorePuStockRunningWaterDTO dto = StorePuStockRunningWaterConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StorePuStockRunningWaterDTO> rt = storePuStockRunningWaterManage.findStorePuStockRunningWaterAll(dto);	
		return JsonResult.success(StorePuStockRunningWaterConverter.toVO(rt));
					 
	}	

	/**
	 * 分页查询门店pu库存流水
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findStorePuStockRunningWaterOfPage")
	@ResponseBody
	public JsonResult<PageResult<StorePuStockRunningWaterVO>> findStorePuStockRunningWaterOfPage(StorePuStockRunningWaterVO vo, Long storeId,Pagination page,HttpServletRequest req ) {
		StorePuStockRunningWaterDTO dto = StorePuStockRunningWaterConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StorePuStockRunningWaterDTO> rt = storePuStockRunningWaterManage.findStorePuStockRunningWaterOfPage(dto, storeId, page);
        List<StorePuStockRunningWaterVO> list = StorePuStockRunningWaterConverter.toVO(rt.getList());
        PageResult<StorePuStockRunningWaterVO> result = new PageResult<StorePuStockRunningWaterVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStorePuStockRunningWaterWithTx")
	@ResponseBody
	public JsonResult<Long> insertStorePuStockRunningWaterWithTx(StorePuStockRunningWaterVO vo,HttpServletRequest req ) {
		StorePuStockRunningWaterDTO dto = StorePuStockRunningWaterConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = storePuStockRunningWaterManage.insertStorePuStockRunningWaterWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStorePuStockRunningWaterByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStorePuStockRunningWaterByIdWithTx(StorePuStockRunningWaterVO vo,HttpServletRequest req ) {
		StorePuStockRunningWaterDTO dto = StorePuStockRunningWaterConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = storePuStockRunningWaterManage.updateStorePuStockRunningWaterWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStorePuStockRunningWaterWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStorePuStockRunningWaterWithTx(StorePuStockRunningWaterVO vo,HttpServletRequest req ) {
		StorePuStockRunningWaterDTO dto = StorePuStockRunningWaterConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = storePuStockRunningWaterManage.deleteStorePuStockRunningWaterWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	