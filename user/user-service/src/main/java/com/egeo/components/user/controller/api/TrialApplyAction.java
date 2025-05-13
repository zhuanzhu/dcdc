package com.egeo.components.user.controller.api;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.business.TrialApplyManage;
import com.egeo.components.user.converter.TrialApplyConverter;
import com.egeo.components.user.dto.TrialApplyDTO;
import com.egeo.components.user.vo.TrialApplyVO;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.StringUtils;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/user/trialApply")
public class TrialApplyAction extends BaseSpringController {
	
	@Resource(name="trialApply")
	private TrialApplyManage trialApplyManage;


	/**
	 * 后台根据id查询申请试用信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findTrialApplyById")
	@ResponseBody
	public JsonResult<TrialApplyVO> findTrialApplyById(Long id ) {
		TrialApplyDTO dto = new TrialApplyDTO();
		dto.setId(id);
		TrialApplyDTO rt = trialApplyManage.findTrialApplyById(dto);		
		return JsonResult.success(TrialApplyConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findTrialApplyAll")
	@ResponseBody
	public JsonResult<List<TrialApplyVO>> findTrialApplyAll(TrialApplyVO vo,HttpServletRequest req ) {
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		TrialApplyDTO dto = TrialApplyConverter.toDTO(vo);
		List<TrialApplyDTO> rt = trialApplyManage.findTrialApplyAll(dto);	
		return JsonResult.success(TrialApplyConverter.toVO(rt));
					 
	}	

	/**
	 * 后台查询试用申请列表
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findTrialApplyOfPage")
	@ResponseBody
	public JsonResult<PageResult<TrialApplyVO>> findTrialApplyOfPage(TrialApplyVO vo,Pagination page,HttpServletRequest req ) {
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		TrialApplyDTO dto = TrialApplyConverter.toDTO(vo);
		if (vo.getCreateTimeStamp() != null) 
			dto.setCreateTime(new Date(vo.getCreateTimeStamp()));
		PageResult<TrialApplyDTO> rt = trialApplyManage.findTrialApplyOfPage(dto, page);
        List<TrialApplyVO> list = TrialApplyConverter.toVO(rt.getList());
        PageResult<TrialApplyVO> result = new PageResult<TrialApplyVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	/**
	 * 客户端申请试用
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/insertTrialApplyWithTx")
	@ResponseBody
	public JsonResult<Long> insertTrialApplyWithTx(TrialApplyVO vo,HttpServletRequest req ) {
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		if (StringUtils.isEmpty(vo.getClientType()))
			return JsonResult.fail("数据来源不能为空");
		if (StringUtils.isBlank(vo.getCompanyName())) 
			return JsonResult.fail("请填写企业名称");
		if (StringUtils.isBlank(vo.getUserName()))
			return JsonResult.fail("请填写姓名");
		if (StringUtils.isBlank(vo.getPhone()))
			return JsonResult.fail("请填写电话");
		if (StringUtils.isBlank(vo.getMail()))
			return JsonResult.fail("请填写邮箱");
		if (StringUtils.isEmpty(vo.getCompanyScale()))
			return JsonResult.fail("请选择企业规模");
		
		TrialApplyDTO dto = TrialApplyConverter.toDTO(vo);
		Long rt = trialApplyManage.insertTrialApplyWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateTrialApplyByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateTrialApplyByIdWithTx(TrialApplyVO vo,HttpServletRequest req ) {
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		TrialApplyDTO dto = TrialApplyConverter.toDTO(vo);
		int rt = trialApplyManage.updateTrialApplyWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteTrialApplyWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteTrialApplyWithTx(TrialApplyVO vo,HttpServletRequest req ) {
		TrialApplyDTO dto = TrialApplyConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}		
		int rt = trialApplyManage.deleteTrialApplyWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 后台处理试用申请
	 * @param id
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/dealTrialApply")
	@ResponseBody
	public JsonResult<Integer> dealTrialApply(Long id,HttpServletRequest req ) {
		TrialApplyDTO dto = new TrialApplyDTO();
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		dto.setId(id);
		TrialApplyDTO dto_ = trialApplyManage.findTrialApplyById(dto);
		if (StringUtils.isEmpty(dto_))
			return JsonResult.fail("此试用申请不存在");
		if (Integer.valueOf(1).equals(dto_.getStatus()))
			return JsonResult.fail("此试用申请已经受理");
		CacheUser user = getCacheUser();
		dto.setStatus(Integer.valueOf(1));
		dto.setDealTime(new Date());
		dto.setDealUser(user.getId());
		int rt = trialApplyManage.updateTrialApplyWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
}
	