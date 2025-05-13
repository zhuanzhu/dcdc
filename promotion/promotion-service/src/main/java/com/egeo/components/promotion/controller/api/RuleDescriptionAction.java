package com.egeo.components.promotion.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.business.RuleDescriptionManage;
import com.egeo.components.promotion.converter.RuleDescriptionConverter;
import com.egeo.components.promotion.dto.RuleDescriptionDTO;
import com.egeo.components.promotion.vo.RuleDescriptionVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.log.XLogger;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/promotion/ruleDescription")
public class RuleDescriptionAction extends BaseSpringController {
	private static final XLogger logger = XLogger.getLogger(RuleDescriptionAction.class);
	
	@Resource(name="ruleDescription")
	private RuleDescriptionManage ruleDescriptionManage;


	// 业务方法：查询规则说明(前后台公用),默认查询第一条,仅有一条
	@RequestMapping(value = "/findRuleDescription")
	@ResponseBody
	public JsonResult<RuleDescriptionVO> findRuleDescription(HttpServletRequest req) {
		logger.info("查询规则说明(前后台公用)");
		String str=req.getHeader("platformId");
		if(EmptyUtil.isEmpty(str)){
			return fail("platformId不能为空");
		}
		Long platformId = Long.valueOf(str);
		RuleDescriptionDTO dto = new RuleDescriptionDTO();
		dto.setPlatformId(platformId);
		if(platformId.equals(2L)){
			//慢有优初始化为2
			dto.setId(2L);
		}else if(platformId.equals(7L)){
			//福管加初始化为1
			dto.setId(1L);
		}
		RuleDescriptionDTO rt = ruleDescriptionManage.findRuleDescriptionById(dto);
		if(EmptyUtil.isEmpty(rt)){
			return fail("该平台不存在规则说明");
		}
		return success(RuleDescriptionConverter.toVO(rt));

	}

	// 业务方法：编辑规则说明()
	@RequestMapping(value = "/updateRuleDescriptionByIdWithTx")
	@ResponseBody
	public JsonResult<String> updateRuleDescriptionByIdWithTx(String description,HttpServletRequest req ) {
		logger.info("[编辑规则说明]参数:description="+description);
		String str = req.getHeader("platformId");
		if(EmptyUtil.isEmpty(str)){
			return fail("platformId不能为空");
		}
		Long platformId = Long.valueOf(str);
		RuleDescriptionDTO ruleDescriptionDTO = new RuleDescriptionDTO();
		if(platformId.equals(2L)){
			//慢有优初始化为2
			ruleDescriptionDTO.setId(2L);
		}else if(platformId.equals(7L)){
			//福管加初始化为1
			ruleDescriptionDTO.setId(1L);
		}
		ruleDescriptionDTO.setPlatformId(platformId);
		ruleDescriptionDTO.setDescription(description);
		int rt = ruleDescriptionManage.updateRuleDescriptionWithTx(ruleDescriptionDTO);
		return success("更新成功");
	}







	// 业务方法：
	@RequestMapping(value = "/findRuleDescriptionAll")
	@ResponseBody
	public JsonResult<List<RuleDescriptionVO>> findRuleDescriptionAll(RuleDescriptionVO vo,HttpServletRequest req ) {
		RuleDescriptionDTO dto = RuleDescriptionConverter.toDTO(vo);
		List<RuleDescriptionDTO> rt = ruleDescriptionManage.findRuleDescriptionAll(dto);	
		return success(RuleDescriptionConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findRuleDescriptionOfPage")
	@ResponseBody
	public JsonResult<PageResult<RuleDescriptionVO>> findRuleDescriptionOfPage(RuleDescriptionVO vo,Pagination page,HttpServletRequest req ) {
		RuleDescriptionDTO dto = RuleDescriptionConverter.toDTO(vo);
		PageResult<RuleDescriptionDTO> rt = ruleDescriptionManage.findRuleDescriptionOfPage(dto, page);
        List<RuleDescriptionVO> list = RuleDescriptionConverter.toVO(rt.getList());
        PageResult<RuleDescriptionVO> result = new PageResult<RuleDescriptionVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertRuleDescriptionWithTx")
	@ResponseBody
	public JsonResult<Long> insertRuleDescriptionWithTx(RuleDescriptionVO vo,HttpServletRequest req ) {
		RuleDescriptionDTO dto = RuleDescriptionConverter.toDTO(vo);
		Long rt = ruleDescriptionManage.insertRuleDescriptionWithTx(dto);	
		return success(rt);					 
	}
	


	// 业务方法：
	@RequestMapping(value = "/deleteRuleDescriptionWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteRuleDescriptionWithTx(RuleDescriptionVO vo,HttpServletRequest req ) {
		RuleDescriptionDTO dto = RuleDescriptionConverter.toDTO(vo);
		int rt = ruleDescriptionManage.deleteRuleDescriptionWithTx(dto);	
		return success(rt);					 
	}


		
}
	