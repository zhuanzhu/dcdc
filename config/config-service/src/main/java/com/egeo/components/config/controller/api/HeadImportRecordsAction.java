package com.egeo.components.config.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.config.business.HeadImportRecordsManage;
import com.egeo.components.config.converter.HeadImportRecordsConverter;
import com.egeo.components.config.dto.HeadImportRecordsDTO;
import com.egeo.components.config.vo.HeadImportRecordsVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/config/headImportRecords")
public class HeadImportRecordsAction extends BaseSpringController {
	
	@Resource(name="headImportRecords")
	private HeadImportRecordsManage headImportRecordsManage;


	// 业务方法：
	@RequestMapping(value = "/findHeadImportRecordsById")
	@ResponseBody
	public JsonResult<HeadImportRecordsVO> findHeadImportRecordsById(Long id ) {
		
		HeadImportRecordsDTO dto = new HeadImportRecordsDTO();
		dto.setId(id);
		HeadImportRecordsDTO rt = headImportRecordsManage.findHeadImportRecordsById(dto);		
		return success(HeadImportRecordsConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findHeadImportRecordsAll")
	@ResponseBody
	public JsonResult<List<HeadImportRecordsVO>> findHeadImportRecordsAll(HeadImportRecordsVO vo,HttpServletRequest req ) {
		HeadImportRecordsDTO dto = HeadImportRecordsConverter.toDTO(vo);
		List<HeadImportRecordsDTO> rt = headImportRecordsManage.findHeadImportRecordsAll(dto);	
		return success(HeadImportRecordsConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findHeadImportRecordsOfPage")
	@ResponseBody
	public JsonResult<PageResult<HeadImportRecordsVO>> findHeadImportRecordsOfPage(HeadImportRecordsVO vo,Pagination page,HttpServletRequest req ) {
		HeadImportRecordsDTO dto = HeadImportRecordsConverter.toDTO(vo);
		PageResult<HeadImportRecordsDTO> rt = headImportRecordsManage.findHeadImportRecordsOfPage(dto, page);
        List<HeadImportRecordsVO> list = HeadImportRecordsConverter.toVO(rt.getList());
        PageResult<HeadImportRecordsVO> result = new PageResult<HeadImportRecordsVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertHeadImportRecordsWithTx")
	@ResponseBody
	public JsonResult<Long> insertHeadImportRecordsWithTx(HeadImportRecordsVO vo,HttpServletRequest req ) {
		HeadImportRecordsDTO dto = HeadImportRecordsConverter.toDTO(vo);
		Long rt = headImportRecordsManage.insertHeadImportRecordsWithTx(dto);	
		return success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateHeadImportRecordsByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateHeadImportRecordsByIdWithTx(HeadImportRecordsVO vo,HttpServletRequest req ) {
		HeadImportRecordsDTO dto = HeadImportRecordsConverter.toDTO(vo);
		int rt = headImportRecordsManage.updateHeadImportRecordsWithTx(dto);	
		return success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteHeadImportRecordsWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteHeadImportRecordsWithTx(HeadImportRecordsVO vo,HttpServletRequest req ) {
		HeadImportRecordsDTO dto = HeadImportRecordsConverter.toDTO(vo);
		int rt = headImportRecordsManage.deleteHeadImportRecordsWithTx(dto);	
		return success(rt);					 
	}
		
}
	