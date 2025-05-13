package com.egeo.components.order.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.business.LimitRuleRecordManage;
import com.egeo.components.order.converter.LimitRuleRecordConverter;
import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.order.vo.LimitRuleRecordVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/order/limitRuleRecord")
public class LimitRuleRecordAction extends BaseSpringController {
	
	@Resource(name="limitRuleRecord")
	private LimitRuleRecordManage limitRuleRecordManage;

	/**
	 * 分页查询限购记录信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findLimitRuleRecordOfPage")
	@ResponseBody
	public JsonResult<PageResult<LimitRuleRecordVO>> findLimitRuleRecordOfPage(LimitRuleRecordVO vo,Pagination page,HttpServletRequest req ) {
		LimitRuleRecordDTO dto = LimitRuleRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<LimitRuleRecordDTO> rt = limitRuleRecordManage.findLimitRuleRecordOfPage(dto, page);
        List<LimitRuleRecordVO> list = LimitRuleRecordConverter.toVO(rt.getList());

        PageResult<LimitRuleRecordVO> result = new PageResult<LimitRuleRecordVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
	}

}
	