package com.egeo.components.order.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.business.SoPackageItemManage;
import com.egeo.components.order.converter.SoPackageItemConverter;
import com.egeo.components.order.dto.SoPackageItemDTO;
import com.egeo.components.order.vo.SoPackageItemVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/order/soPackageItem")
public class SoPackageItemAction extends BaseSpringController {
	
	@Resource(name="soPackageItem")
	private SoPackageItemManage soPackageItemManage;


	// 业务方法：
	@RequestMapping(value = "/findSoPackageItemById")
	@ResponseBody
	public JsonResult<SoPackageItemVO> findSoPackageItemById(Long id ) {
		
		SoPackageItemDTO dto = new SoPackageItemDTO();
		dto.setId(id);
		SoPackageItemDTO rt = soPackageItemManage.findSoPackageItemById(dto);		
		return JsonResult.success(SoPackageItemConverter.toVO(rt));
					 
	}

	// 业务方法：
	@RequestMapping(value = "/findSoPackageItemAll")
	@ResponseBody
	public JsonResult<List<SoPackageItemVO>> findSoPackageItemAll(SoPackageItemVO vo,HttpServletRequest req ) {
		SoPackageItemDTO dto = SoPackageItemConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<SoPackageItemDTO> rt = soPackageItemManage.findSoPackageItemAll(dto);	
		return JsonResult.success(SoPackageItemConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findSoPackageItemOfPage")
	@ResponseBody
	public JsonResult<PageResult<SoPackageItemVO>> findSoPackageItemOfPage(SoPackageItemVO vo,Pagination page,HttpServletRequest req ) {
		SoPackageItemDTO dto = SoPackageItemConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<SoPackageItemDTO> rt = soPackageItemManage.findSoPackageItemOfPage(dto, page);
        List<SoPackageItemVO> list = SoPackageItemConverter.toVO(rt.getList());
        PageResult<SoPackageItemVO> result = new PageResult<SoPackageItemVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：
	@RequestMapping(value = "/insertSoPackageItemWithTx")
	@ResponseBody
	public JsonResult<Integer> insertSoPackageItemWithTx(SoPackageItemVO vo,HttpServletRequest req ) {
		SoPackageItemDTO dto = SoPackageItemConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = soPackageItemManage.insertSoPackageItemWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateSoPackageItemByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateSoPackageItemByIdWithTx(SoPackageItemVO vo,HttpServletRequest req ) {
		SoPackageItemDTO dto = SoPackageItemConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = soPackageItemManage.updateSoPackageItemWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteSoPackageItemWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteSoPackageItemWithTx(SoPackageItemVO vo,HttpServletRequest req ) {
		SoPackageItemDTO dto = SoPackageItemConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = soPackageItemManage.deleteSoPackageItemWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	