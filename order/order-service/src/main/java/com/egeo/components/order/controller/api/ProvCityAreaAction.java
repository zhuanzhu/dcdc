package com.egeo.components.order.controller.api;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.business.ProvCityAreaManage;
import com.egeo.components.order.converter.ProvCityAreaConverter;
import com.egeo.components.order.dto.ProvCityAreaDTO;
import com.egeo.components.order.vo.ProvCityAreaVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/api/order/provCityArea")
public class ProvCityAreaAction extends BaseSpringController {

	@Resource(name = "provCityArea")
	private ProvCityAreaManage provCityAreaManage;

	// 业务方法：
	@RequestMapping(value = "/findProvCityAreaById")
	@ResponseBody
	public JsonResult<ProvCityAreaVO> findProvCityAreaById(Long id) {
		ProvCityAreaDTO rt = provCityAreaManage.findProvCityAreaById(id);
		return JsonResult.success(ProvCityAreaConverter.toVO(rt));

	}

	// 业务方法：
	@RequestMapping(value = "/findProvCityAreaAll")
	@ResponseBody
	public JsonResult<List<ProvCityAreaVO>> findProvCityAreaAll(ProvCityAreaVO vo, HttpServletRequest req) {
		ProvCityAreaDTO dto = ProvCityAreaConverter.toDTO(vo);
		List<ProvCityAreaDTO> rt = provCityAreaManage.findProvCityAreaAll(dto);
		return JsonResult.success(ProvCityAreaConverter.toVO(rt));
	}

	// 业务方法：
	@RequestMapping(value = "/findProvCityAreaOfPage")
	@ResponseBody
	public JsonResult<PageResult<ProvCityAreaVO>> findProvCityAreaOfPage(ProvCityAreaVO vo, Pagination page,
			HttpServletRequest req) {
		ProvCityAreaDTO dto = ProvCityAreaConverter.toDTO(vo);
		PageResult<ProvCityAreaDTO> rt = provCityAreaManage.findProvCityAreaOfPage(dto, page);
		List<ProvCityAreaVO> list = ProvCityAreaConverter.toVO(rt.getList());
		PageResult<ProvCityAreaVO> result = new PageResult<ProvCityAreaVO>();
		result.setList(list);
		result.setPageNo(rt.getPageNo());
		result.setPageSize(rt.getPageSize());
		result.setTotalSize(rt.getTotalSize());

		return JsonResult.success(result);

	}

	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertProvCityAreaWithTx")
	@ResponseBody
	public JsonResult<Long> insertProvCityAreaWithTx(ProvCityAreaVO vo, HttpServletRequest req) {
		ProvCityAreaDTO dto = ProvCityAreaConverter.toDTO(vo);
		Long rt = provCityAreaManage.insertProvCityAreaWithTx(dto);
		return JsonResult.success(rt);
	}

	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateProvCityAreaByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateProvCityAreaByIdWithTx(ProvCityAreaVO vo, HttpServletRequest req) {
		ProvCityAreaDTO dto = ProvCityAreaConverter.toDTO(vo);
		int rt = provCityAreaManage.updateProvCityAreaWithTx(dto);
		return JsonResult.success(rt);
	}

	// 业务方法：
	@RequestMapping(value = "/deleteProvCityAreaWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteProvCityAreaWithTx(ProvCityAreaVO vo, HttpServletRequest req) {
		ProvCityAreaDTO dto = ProvCityAreaConverter.toDTO(vo);
		int rt = provCityAreaManage.deleteProvCityAreaWithTx(dto);
		return JsonResult.success(rt);
	}

}
