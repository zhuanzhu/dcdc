package com.egeo.components.product.controller.api;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.MembershipAuthorityManage;
import com.egeo.components.product.converter.MembershipAuthorityConverter;
import com.egeo.components.product.vo.MembershipAuthorityVO;
import com.egeo.components.product.dto.MembershipAuthorityDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;



@Controller
@RequestMapping("/api/product/membershipAuthority")
public class MembershipAuthorityAction extends BaseSpringController {
	
	@Resource(name="membershipAuthority")
	private MembershipAuthorityManage membershipAuthorityManage;
	Gson gson=new Gson();


	// 根据会籍id查询会籍权限
	@RequestMapping(value = "/findMembershipAuthorityByMembershipId")
	@ResponseBody
	public JsonResult<PageResult<MembershipAuthorityVO>> findMembershipAuthorityByMembershipId(Pagination page,Long membershipId,HttpServletRequest req) {
			//校验platformId
			String str = req.getHeader("platformId");

			if(EmptyUtil.isEmpty(str)){
				fail("platformId不能为null");

			}
			Long platformId=Long.valueOf(str);

		return membershipAuthorityManage.findMembershipAuthorityByMembershipId(membershipId,platformId,page);

					 
	}


	//更新会籍信息
	@RequestMapping("/updateMembershipAuthorityByMembershipId")
	@ResponseBody
	public JsonResult<Object> updateMembershipAuthorityByMembershipId(Long membershipId,
																	   String membershipCode,
																	   String membershipName,
																	   String membershipLogImgUrl,
																	   Long linkedSkuId,
																	   Long categoryId,
																	   String categoryName,
																	   Integer validPeriodVal,
																	   Integer validPeriodUnit,
																	   String authorityIds,
																	   String remarks,
																	   HttpServletRequest req
																	   ){
		//校验platformId
		String str = req.getHeader("platformId");

		if(EmptyUtil.isEmpty(str)){
			fail("platformId不能为null");

		}
		Long platformId=Long.valueOf(str);
		List<Long> list = new ArrayList<>();
		if(EmptyUtil.isNotEmpty(authorityIds)){
			list=gson.fromJson(authorityIds,new TypeToken<List<Long>>(){}.getType());
		}else{
			list = null;
		}





		return membershipAuthorityManage.updateMembershipAuthorityByMembershipId(membershipId,
				membershipCode,
				membershipName,
				membershipLogImgUrl,
				linkedSkuId,
				categoryId,
				categoryName,
				validPeriodVal,
				validPeriodUnit,
				list,
				remarks,platformId);
	}




/*

	// 业务方法：
	@RequestMapping(value = "/findMembershipAuthorityAll")
	@ResponseBody
	public JsonResult<List<MembershipAuthorityVO>> findMembershipAuthorityAll(MembershipAuthorityVO vo,HttpServletRequest req ) {
		MembershipAuthorityDTO dto = MembershipAuthorityConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<MembershipAuthorityDTO> rt = membershipAuthorityManage.findMembershipAuthorityAll(dto);	
		return JsonResult.success(MembershipAuthorityConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findMembershipAuthorityOfPage")
	@ResponseBody
	public JsonResult<PageResult<MembershipAuthorityVO>> findMembershipAuthorityOfPage(MembershipAuthorityVO vo,Pagination page,HttpServletRequest req ) {
		MembershipAuthorityDTO dto = MembershipAuthorityConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<MembershipAuthorityDTO> rt = membershipAuthorityManage.findMembershipAuthorityOfPage(dto, page);
        List<MembershipAuthorityVO> list = MembershipAuthorityConverter.toVO(rt.getList());
        PageResult<MembershipAuthorityVO> result = new PageResult<MembershipAuthorityVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertMembershipAuthorityWithTx")
	@ResponseBody
	public JsonResult<Long> insertMembershipAuthorityWithTx(MembershipAuthorityVO vo,HttpServletRequest req ) {
		MembershipAuthorityDTO dto = MembershipAuthorityConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = membershipAuthorityManage.insertMembershipAuthorityWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateMembershipAuthorityByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateMembershipAuthorityByIdWithTx(MembershipAuthorityVO vo,HttpServletRequest req ) {
		MembershipAuthorityDTO dto = MembershipAuthorityConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = membershipAuthorityManage.updateMembershipAuthorityWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteMembershipAuthorityWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteMembershipAuthorityWithTx(MembershipAuthorityVO vo,HttpServletRequest req ) {
		MembershipAuthorityDTO dto = MembershipAuthorityConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = membershipAuthorityManage.deleteMembershipAuthorityWithTx(dto);	
		return JsonResult.success(rt);					 
	}
*/

}
	