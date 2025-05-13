package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.product.business.VideoManage;
import com.egeo.components.product.converter.VideoConverter;
import com.egeo.components.product.dto.VideoDTO;
import com.egeo.components.product.vo.VideoVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/video")
public class VideoAction extends BaseSpringController {
	
	@Resource(name="video")
	private VideoManage videoManage;


	// 业务方法：
	@RequestMapping(value = "/findVideoById")
	@ResponseBody
	public JsonResult<VideoVO> findVideoById(Long id ) {
		
		VideoDTO dto = new VideoDTO();
		dto.setId(id);
		VideoDTO rt = videoManage.findVideoById(dto);		
		return JsonResult.success(VideoConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findVideoAll")
	@ResponseBody
	public JsonResult<List<VideoVO>> findVideoAll(VideoVO vo,HttpServletRequest req ) {
		VideoDTO dto = VideoConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<VideoDTO> rt = videoManage.findVideoAll(dto);	
		return JsonResult.success(VideoConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findVideoOfPage")
	@ResponseBody
	public JsonResult<PageResult<VideoVO>> findVideoOfPage(VideoVO vo,Pagination page,HttpServletRequest req ) {
		VideoDTO dto = VideoConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<VideoDTO> rt = videoManage.findVideoOfPage(dto, page);
        List<VideoVO> list = VideoConverter.toVO(rt.getList());
        PageResult<VideoVO> result = new PageResult<VideoVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertVideoWithTx")
	@ResponseBody
	public JsonResult<Long> insertVideoWithTx(VideoVO vo,HttpServletRequest req ) {
		VideoDTO dto = VideoConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = videoManage.insertVideoWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateVideoByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateVideoByIdWithTx(VideoVO vo,HttpServletRequest req ) {
		VideoDTO dto = VideoConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = videoManage.updateVideoWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteVideoWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteVideoWithTx(VideoVO vo,HttpServletRequest req ) {
		VideoDTO dto = VideoConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		String stringText = vo.getStringText();
		List<String> list = JSONArray.parseArray(stringText, String.class);
		for (String string : list) {
			System.out.println(string);
		}
		int rt = videoManage.deleteVideoWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	