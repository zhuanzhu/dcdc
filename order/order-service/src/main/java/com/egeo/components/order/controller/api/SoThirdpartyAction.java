package com.egeo.components.order.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.business.SoThirdpartyManage;
import com.egeo.components.order.converter.SoThirdpartyConverter;
import com.egeo.components.order.dto.SoThirdpartyDTO;
import com.egeo.components.order.vo.SoThirdpartyVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/order/soThirdparty")
public class SoThirdpartyAction extends BaseSpringController {
	
	@Resource(name="soThirdparty")
	private SoThirdpartyManage soThirdpartyManage;


	// 业务方法：
	@RequestMapping(value = "/findSoThirdpartyById")
	@ResponseBody
	public JsonResult<SoThirdpartyVO> findSoThirdpartyById(Long id ) {
		
		SoThirdpartyDTO dto = new SoThirdpartyDTO();
		dto.setId(id);
		SoThirdpartyDTO rt = soThirdpartyManage.findSoThirdpartyById(dto);		
		return JsonResult.success(SoThirdpartyConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findSoThirdpartyAll")
	@ResponseBody
	public JsonResult<List<SoThirdpartyVO>> findSoThirdpartyAll(SoThirdpartyVO vo,HttpServletRequest req ) {
		SoThirdpartyDTO dto = SoThirdpartyConverter.toDTO(vo);
		List<SoThirdpartyDTO> rt = soThirdpartyManage.findSoThirdpartyAll(dto);	
		return JsonResult.success(SoThirdpartyConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findSoThirdpartyOfPage")
	@ResponseBody
	public JsonResult<PageResult<SoThirdpartyVO>> findSoThirdpartyOfPage(SoThirdpartyVO vo,Pagination page,HttpServletRequest req ) {
		SoThirdpartyDTO dto = SoThirdpartyConverter.toDTO(vo);
		PageResult<SoThirdpartyDTO> rt = soThirdpartyManage.findSoThirdpartyOfPage(dto, page);
        List<SoThirdpartyVO> list = SoThirdpartyConverter.toVO(rt.getList());
        PageResult<SoThirdpartyVO> result = new PageResult<SoThirdpartyVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertSoThirdpartyWithTx")
	@ResponseBody
	public JsonResult<Long> insertSoThirdpartyWithTx(SoThirdpartyVO vo,HttpServletRequest req ) {
		SoThirdpartyDTO dto = SoThirdpartyConverter.toDTO(vo);
		Long rt = soThirdpartyManage.insertSoThirdpartyWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateSoThirdpartyByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateSoThirdpartyByIdWithTx(SoThirdpartyVO vo,HttpServletRequest req ) {
		SoThirdpartyDTO dto = SoThirdpartyConverter.toDTO(vo);
		int rt = soThirdpartyManage.updateSoThirdpartyWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteSoThirdpartyWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteSoThirdpartyWithTx(SoThirdpartyVO vo,HttpServletRequest req ) {
		SoThirdpartyDTO dto = SoThirdpartyConverter.toDTO(vo);
		int rt = soThirdpartyManage.deleteSoThirdpartyWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
    /**
     * 通过订单id查询第三方订单列表
     * @param soId
     * @return
     */
    @RequestMapping(value = "/findSoThirdpartyBySoId")
    @ResponseBody
    public JsonResult<List<Map<String,Object>>> findSoThirdpartyBySoId(Long soId) {
          
          JsonResult<List<Map<String,Object>>> rt = soThirdpartyManage.findSoThirdpartyBySoId(soId);
          return rt;
    }
		
}
	