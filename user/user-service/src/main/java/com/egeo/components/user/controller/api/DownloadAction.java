package com.egeo.components.user.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.vo.DownloadVO;
import com.egeo.components.user.business.DownloadManage;
import com.egeo.components.user.converter.DownloadConverter;
import com.egeo.components.user.dto.DownloadDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.StringUtils;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/user/download")
public class DownloadAction extends BaseSpringController {
	
	@Resource(name="download")
	private DownloadManage downloadManage;


	// 业务方法：
	@RequestMapping(value = "/findDownloadById")
	@ResponseBody
	public JsonResult<DownloadVO> findDownloadById(Long id ) {
		
		DownloadDTO dto = new DownloadDTO();
		dto.setId(id);
		DownloadDTO rt = downloadManage.findDownloadById(dto);		
		return JsonResult.success(DownloadConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findDownloadAll")
	@ResponseBody
	public JsonResult<List<DownloadVO>> findDownloadAll(DownloadVO vo,HttpServletRequest req ) {
		DownloadDTO dto = DownloadConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<DownloadDTO> rt = downloadManage.findDownloadAll(dto);	
		return JsonResult.success(DownloadConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findDownloadOfPage")
	@ResponseBody
	public JsonResult<PageResult<DownloadVO>> findDownloadOfPage(DownloadVO vo,Pagination page,HttpServletRequest req ) {
		DownloadDTO dto = DownloadConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<DownloadDTO> rt = downloadManage.findDownloadOfPage(dto, page);
        List<DownloadVO> list = DownloadConverter.toVO(rt.getList());
        PageResult<DownloadVO> result = new PageResult<DownloadVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertDownloadWithTx")
	@ResponseBody
	public JsonResult<Long> insertDownloadWithTx(DownloadVO vo,HttpServletRequest req ) {
		DownloadDTO dto = DownloadConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		 if(StringUtils.isBlank(dto.getName())){
              return JsonResult.fail("推广渠道不能为空");
         }
         if(StringUtils.isBlank(dto.getSession())){
              return JsonResult.fail("session不能为空");
         }
         if(StringUtils.isBlank(dto.getUrl())){
              return JsonResult.fail("二维码链接不能为空");
         }
		Long rt = downloadManage.insertDownloadWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateDownloadByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateDownloadByIdWithTx(DownloadVO vo,HttpServletRequest req ) {
		DownloadDTO dto = DownloadConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		if(StringUtils.isEmpty(dto.getId())){
			return JsonResult.fail("id不能为空");
		}
		if(StringUtils.isBlank(dto.getName())){
            return JsonResult.fail("推广渠道不能为空");
        }
        if(StringUtils.isBlank(dto.getSession())){
            return JsonResult.fail("session不能为空");
        }
        if(StringUtils.isBlank(dto.getUrl())){
            return JsonResult.fail("二维码链接不能为空");
        }
		int rt = downloadManage.updateDownloadWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteDownloadWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteDownloadWithTx(DownloadVO vo,HttpServletRequest req ) {
		DownloadDTO dto = DownloadConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = downloadManage.deleteDownloadWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 扫描二维码后,记录session信息并返回对应的官方链接
	 * @param session
	 * @param user 使用方：1、c端 2、b端
	 * @return
	 */
	@RequestMapping(value = "/askDownloadUrl")
	@ResponseBody
	public JsonResult<Map<String,Object>> askDownloadUrl(String session,Integer user, HttpServletRequest req){
		//从请求头中取得参数
		/*String f=req.getHeader("f");	// 来源 0:安卓  1:ios
		if(StringUtils.isBlank(f))
			return JsonResult.fail("渠道参数缺失");
		Integer f_=Integer.parseInt(f);*/
		// 使用方为空则默认为c端
		if(StringUtils.isEmpty(user)){
			user = 1;
		}
		return downloadManage.askDownloadUrlWithTx(session, null,user);
	}
		
}
	