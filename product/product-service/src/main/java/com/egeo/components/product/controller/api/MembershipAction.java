package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.MembershipManage;
import com.egeo.components.product.vo.MembershipResultVO;
import com.egeo.components.product.vo.MembershipVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/membership")
public class MembershipAction extends BaseSpringController {
	
	@Resource(name="membership")
	private MembershipManage membershipManage;


	// 业务方法：
	@RequestMapping(value = "/findMembershipOfPage")
	@ResponseBody
	public JsonResult<PageResult<MembershipVO>> findMembershipOfPage(Long categoryId,
																	 String membershipName,
																	 String linkedSkuName,
																	 Pagination page,
																	 HttpServletRequest req ) {
		//校验platformId
		String str = req.getHeader("platformId");

		if(EmptyUtil.isEmpty(str)){
			return JsonResult.fail("platformId不能为null");

		}
		Long platformId=Long.valueOf(str);
		//去除收缩字段的前后空格
		if(EmptyUtil.isNotEmpty(membershipName)){
			membershipName = membershipName.trim();
			if(EmptyUtil.isBlank(membershipName)){
				membershipName = null;
			}
		}
		if(EmptyUtil.isNotEmpty(linkedSkuName)){
			linkedSkuName = linkedSkuName.trim();
			if(EmptyUtil.isBlank(linkedSkuName)){
				linkedSkuName = null;
			}
		}
		return membershipManage.findMembershipOfPage(categoryId,membershipName,linkedSkuName,platformId, page);


	}

	//根据membershipId查询membership
	@RequestMapping(value = "/findMembershipById")
	@ResponseBody
	public JsonResult<MembershipVO> findMembershipById(Long membershipId,HttpServletRequest req ) {
	//校验platformId
		String str = req.getHeader("platformId");

		if(EmptyUtil.isEmpty(str)){
			return JsonResult.fail("platformId不能为null");

		}
		Long platformId=Long.valueOf(str);

		return membershipManage.findMembershipById(membershipId,platformId);

	}

	@RequestMapping("/findAllMembership")
	@ResponseBody
	public JsonResult<List<MembershipResultVO>> findAllMembership(HttpServletRequest req){

		//校验platformId
		String str = req.getHeader("platformId");

		if(EmptyUtil.isEmpty(str)){
			return JsonResult.fail("platformId不能为null");

		}
		Long platformId=Long.valueOf(str);

		List<MembershipResultVO> membershipAll = membershipManage.findMembershipAll(platformId);
		if(membershipAll==null){
			return JsonResult.fail("不存在会籍,请添加");
		}

		return JsonResult.success(membershipAll);
	}


	/*// 业务方法：
	@RequestMapping(value = "/findMembershipById")
	@ResponseBody
	public JsonResult<MembershipVO> findMembershipById(Long id ) {
		
		MembershipDTO dto = new MembershipDTO();
		dto.setId(id);
		MembershipDTO rt = membershipManage.findMembershipById(dto);		
		return JsonResult.success(MembershipConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findMembershipAll")
	@ResponseBody
	public JsonResult<List<MembershipVO>> findMembershipAll(MembershipVO vo,HttpServletRequest req ) {
		MembershipDTO dto = MembershipConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<MembershipDTO> rt = membershipManage.findMembershipAll(dto);	
		return JsonResult.success(MembershipConverter.toVO(rt));
					 
	}	




	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertMembershipWithTx")
	@ResponseBody
	public JsonResult<Long> insertMembershipWithTx(MembershipVO vo,HttpServletRequest req ) {
		MembershipDTO dto = MembershipConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = membershipManage.insertMembershipWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateMembershipByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateMembershipByIdWithTx(MembershipVO vo,HttpServletRequest req ) {
		MembershipDTO dto = MembershipConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = membershipManage.updateMembershipWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteMembershipWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteMembershipWithTx(MembershipVO vo,HttpServletRequest req ) {
		MembershipDTO dto = MembershipConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = membershipManage.deleteMembershipWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		*/
}
	