package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.AuthorityManage;
import com.egeo.components.product.converter.AuthorityConverter;
import com.egeo.components.product.vo.AuthorityVO;
import com.egeo.components.product.dto.AuthorityDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;



@Controller
@RequestMapping("/api/product/authority")
public class AuthorityAction extends BaseSpringController {
	
	@Resource(name="authority")
	private AuthorityManage authorityManage;


	// 业务方法：
	@RequestMapping(value = "/findAuthorityById")
	@ResponseBody
	public JsonResult<AuthorityVO> findAuthorityById(Long id ) {
		
		AuthorityDTO dto = new AuthorityDTO();
		dto.setId(id);
		AuthorityDTO rt = authorityManage.findAuthorityById(dto);		
		return JsonResult.success(AuthorityConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findAuthorityAll")
	@ResponseBody
	public JsonResult<List<AuthorityVO>> findAuthorityAll(AuthorityVO vo,HttpServletRequest req ) {
		AuthorityDTO dto = AuthorityConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<AuthorityDTO> rt = authorityManage.findAuthorityAll(dto);	
		return JsonResult.success(AuthorityConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findAuthorityOfPage")
	@ResponseBody
	public JsonResult<PageResult<AuthorityVO>> findAuthorityOfPage(AuthorityVO vo,Pagination page,HttpServletRequest req ) {
		AuthorityDTO dto = AuthorityConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<AuthorityDTO> rt = authorityManage.findAuthorityOfPage(dto, page);
        List<AuthorityVO> list = AuthorityConverter.toVO(rt.getList());
        PageResult<AuthorityVO> result = new PageResult<AuthorityVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertAuthorityWithTx")
	@ResponseBody
	public JsonResult<Long> insertAuthorityWithTx(AuthorityVO vo,HttpServletRequest req ) {
		AuthorityDTO dto = AuthorityConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = authorityManage.insertAuthorityWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateAuthorityByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateAuthorityByIdWithTx(AuthorityVO vo,HttpServletRequest req ) {
		AuthorityDTO dto = AuthorityConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = authorityManage.updateAuthorityWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteAuthorityWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteAuthorityWithTx(AuthorityVO vo,HttpServletRequest req ) {
		AuthorityDTO dto = AuthorityConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = authorityManage.deleteAuthorityWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	